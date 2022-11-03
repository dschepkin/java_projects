package com.nokia;

import com.alibaba.fastjson.JSONObject;
import io.github.hengyunabc.zabbix.api.DefaultZabbixApi;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.ZabbixApi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Integrations {

    String v_templateName;
    String url;
    String user;
    String password;
    String filename;

    public Integrations(String a, String b, String c, String d, String e) {
        v_templateName = a;
        url = b;
        user = c;
        password = d;
        filename = e;
    }

    ZabbixApi zabbixApi;

    public void before() {
        zabbixApi = new DefaultZabbixApi(url);
        zabbixApi.init();
    }

    public void login() {
        boolean login = zabbixApi.login(user, password);
    }

    public void actions() {
        File file = new File(filename); //поток подключения к источнику

        JSONObject filter = new JSONObject();

        filter.put("host", v_templateName); //здесь указываем имя шаблона, вместо имени хоста

        Request templateIdGet = RequestBuilder.newBuilder()
                .method("template.get")
                .paramEntry("filter", filter)
                .build();

        JSONObject response = zabbixApi.call(templateIdGet);
        String templateid = response.getJSONArray("result").getJSONObject(0).getString("templateid");

        try {
            Scanner scanner01 = new Scanner(file);
            while (scanner01.hasNextLine()) {
                String line = scanner01.nextLine();
                String[] list01 = line.trim().split("\\s+");
                int arrayLength = list01.length;

                String itServiceName = list01[0] + "." + list01[1];

                //Create IiService of the integration
                Request itServiceCreate = RequestBuilder.newBuilder()
                        .method("service.create")
                        .paramEntry("name", itServiceName)
                        .paramEntry("algorithm", "1")
                        .paramEntry("showsla", 1)
                        .paramEntry("goodsla", 99.99)
                        .paramEntry("sortorder", 1)
                        .build();

                JSONObject responseitServiceCreate = zabbixApi.call(itServiceCreate);

                //Get IdItService Created on previous step
                filter.put("name", itServiceName);
                Request itServiceGet = RequestBuilder.newBuilder()
                        .method("service.get")
                        .paramEntry("filter", filter)
                        .build();

                JSONObject responseitServiceGet = zabbixApi.call(itServiceGet);
                String itServiceid = responseitServiceGet.getJSONArray("result").getJSONObject(0).getString("serviceid");

                String itServiceParent = null;
                String applicationsid = null;

                if (list01[2].equals("MTS")) {
                    itServiceParent = "4";
                    applicationsid = "639";
                } else if (list01[2].equals("Nokia")) {
                    itServiceParent = "169";
                    applicationsid = "697";
                } else if (list01[2].equals("VEON")) {
                    itServiceParent = "3";
                    applicationsid = "638";
                }

                Request itAdddependicies = RequestBuilder.newBuilder()
                        .method("service.update")
                        .paramEntry("serviceid", itServiceid)
                        .paramEntry("parentid", itServiceParent)
                        .build();

                JSONObject responseitAdddependicies = zabbixApi.call(itAdddependicies);

                for (int i = 5; i < arrayLength; i++) {

                    String itemType = null;

                    if (list01[4].equals("JOB")) {
                        itemType = "job";
                    } else {
                        itemType = "intgr";
                    }

                    String phaseIntgr = list01[3];
                    String itemNameValue = list01[0] + "." + phaseIntgr + "." + list01[i];
                    String itemKeyValue = "custom." + itemType + "." + list01[0] + "." + list01[i];

                    Request itemCreate = RequestBuilder.newBuilder()
                            .method("item.create")
                            .paramEntry("name", itemNameValue)
                            .paramEntry("key_", itemKeyValue)
                            .paramEntry("hostid", templateid)
                            .paramEntry("type", 0)
                            .paramEntry("value_type", 0)
                            .paramEntry("applications", new String[] {applicationsid})
                            .paramEntry("delay", 27)
                            .build();

                    JSONObject responseItemCreate = zabbixApi.call(itemCreate);

                    String triggerDesctiptionValue = list01[0] + "." + phaseIntgr + "." + list01[1] + "." + list01[i];
                    String triggerExpressionValue = "{" + v_templateName + ":" + itemKeyValue + ".last()}=0";

                    Request triggerCreate = RequestBuilder.newBuilder()
                            .method("trigger.create")
                            .paramEntry("description", triggerDesctiptionValue)
                            .paramEntry("expression", triggerExpressionValue)
                            .build();

                    JSONObject responsetriggerCreate = zabbixApi.call(triggerCreate);

                    JSONObject search = (JSONObject) responsetriggerCreate.get("result");
                    String entry = search.getJSONArray("triggerids").getString(0).toString();

                    Request triggerUpdate = RequestBuilder.newBuilder()
                            .method("trigger.update")
                            .paramEntry("priority", 4)
                            .paramEntry("triggerid", entry)
                            .paramEntry("status", 1)
                            .build();

                    JSONObject responsetriggerUpdate = zabbixApi.call(triggerUpdate);

                    //Create itService for item
                    String itServiceNameItem = list01[0] + "." + list01[i];

                    Request itServiceCreateItem = RequestBuilder.newBuilder()
                            .method("service.create")
                            .paramEntry("name", itServiceNameItem)
                            .paramEntry("algorithm", "1")
                            .paramEntry("showsla", 1)
                            .paramEntry("goodsla", 99.99)
                            .paramEntry("sortorder", 1)
                            .paramEntry("triggerid", entry)
                            .build();

                    JSONObject responseitServiceCreateItem = zabbixApi.call(itServiceCreateItem);

                    //Get ServiceCreateItem Created on previous step
                    filter.put("name", itServiceNameItem);
                    Request itServiceNameItemGet = RequestBuilder.newBuilder()
                            .method("service.get")
                            .paramEntry("filter", filter)
                            .build();

                    JSONObject responseitServiceNameItemGet = zabbixApi.call(itServiceNameItemGet);
                    String itServiceItemid = responseitServiceNameItemGet.getJSONArray("result").getJSONObject(0).getString("serviceid");

                    Request itAdddependiciesToItem = RequestBuilder.newBuilder()
                            .method("service.update")
                            .paramEntry("serviceid", itServiceItemid)
                            .paramEntry("parentid", itServiceid)
                            .build();

                    JSONObject responseitAdddependiciesToItem = zabbixApi.call(itAdddependiciesToItem);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
