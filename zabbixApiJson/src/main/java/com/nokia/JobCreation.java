package com.nokia;

import com.alibaba.fastjson.JSONObject;
import io.github.hengyunabc.zabbix.api.DefaultZabbixApi;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.ZabbixApi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JobCreation {
    ZabbixApi zabbixApi;

    //String v_templateName = "nsn_testAPITemplate";
    String v_templateName = "nsn_integrations";

    public static void main(String[] args) {
        JobCreation tc = new JobCreation();
        tc.before();
        tc.testLogin();
        tc.actions01();
    }

    public void before() {
        String url = "http://93.183.24.200/zabbix.nokia.com/public_html/api_jsonrpc.php";
        zabbixApi = new DefaultZabbixApi(url);
        zabbixApi.init();
    }

    public void testLogin() {
        String user = "Admin";
        String password = "&Ty56_*rt";
        boolean login = zabbixApi.login(user, password);
        System.out.println("login result:" + login);

    }

    public void actions01() {
        String filename = "c:\\notebook_work_dschepkin\\dschepkin\\tmp\\test_itemsList.txt";
        File file = new File(filename); //поток подключения к источнику

        JSONObject filter = new JSONObject();

        filter.put("host", v_templateName); //здесь указываем имя шаблона, вместо имени хоста

        Request templateIdGet = RequestBuilder.newBuilder()
                .method("template.get")
                .paramEntry("filter", filter)
                .build();

        JSONObject response = zabbixApi.call(templateIdGet);
        String templateid = response.getJSONArray("result").getJSONObject(0).getString("templateid");
        System.out.println(response);
        System.out.println("templateid: " + templateid);

        try {
            Scanner scanner01 = new Scanner(file);
            while (scanner01.hasNextLine()) {
                String line = scanner01.nextLine();
                String[] list01 = line.trim().split("\\s+");
                int arrayLength = list01.length;

                String itServiceName = list01[0] + "." + list01[1];
                //String GraphName = itServiceName;
                System.out.println("itServiceName: " + itServiceName);
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
                System.out.println("itServiceIdCreate: " + responseitServiceCreate);

                //Get IdItService Created on previous step
                filter.put("name", itServiceName);
                Request itServiceGet = RequestBuilder.newBuilder()
                        .method("service.get")
                        .paramEntry("filter", filter)
                        .build();

                JSONObject responseitServiceGet = zabbixApi.call(itServiceGet);
                String itServiceid = responseitServiceGet.getJSONArray("result").getJSONObject(0).getString("serviceid");
                System.out.println("itServiceid: " + itServiceid);

                String itServiceParent = "169"; //ITserviceid=169 - Nokia
                Request itAdddependicies = RequestBuilder.newBuilder()
                        .method("service.update")
                        .paramEntry("serviceid", itServiceid)
                        .paramEntry("parentid", itServiceParent)
                        .build();

                JSONObject responseitAdddependicies = zabbixApi.call(itAdddependicies);
                System.out.println("responseitAdddependicies: " + responseitAdddependicies);
/*
                //Create graph
                Request graphCreate = RequestBuilder.newBuilder()
                        .method("graph.create")
                        .paramEntry("name", GraphName)
                        .paramEntry("width", 800)
                        .paramEntry("height", 300)
                        .paramEntry("graphtype", 0)
                        .paramEntry("templateid", templateid)
                        .paramEntry("itemid", new String[] {"itemid" "30067","color" "00AA00"})
                        .build();

                JSONObject responsegraphCreate = zabbixApi.call(graphCreate);
                System.out.println("graphCreate: " + responsegraphCreate);*/
                for (int i = 3; i < arrayLength; i++) {
                    System.out.println("Element # " + i + " value: " + list01[i]);

                    String itemType;

                    if (list01[2].equals("JOB")) {
                        itemType = "job";
                    } else {
                        itemType = "intgr";
                    }

                    String itemNameValue = list01[0] + "." + list01[i];
                    String itemKeyValue = "custom." + itemType + "." + list01[0] + "." + list01[i];

                    System.out.println(itemNameValue);
                    System.out.println(itemKeyValue);
                    System.out.println("");

                    Request itemCreate = RequestBuilder.newBuilder()
                            .method("item.create")
                            .paramEntry("name", itemNameValue)
                            .paramEntry("key_", itemKeyValue)
                            .paramEntry("hostid", templateid)
                            .paramEntry("type", 0)
                            .paramEntry("value_type", 0)
                            .paramEntry("applications", new String[] {"697"})
                            .paramEntry("delay", 27)
                            .build();

                    JSONObject responseItemCreate = zabbixApi.call(itemCreate);
                    System.out.println(responseItemCreate);

                    String triggerDesctiptionValue = list01[0] + "." + list01[1] + "." + list01[i];
                    String triggerExpressionValue = "{" + v_templateName + ":" + itemKeyValue + ".last()}=0";

                    Request triggerCreate = RequestBuilder.newBuilder()
                            .method("trigger.create")
                            .paramEntry("description", triggerDesctiptionValue)
                            .paramEntry("expression", triggerExpressionValue)
                            .build();

                    JSONObject responsetriggerCreate = zabbixApi.call(triggerCreate);
                    System.out.println(responsetriggerCreate);

                    JSONObject search = (JSONObject) responsetriggerCreate.get("result");
                    String entry = search.getJSONArray("triggerids").getString(0).toString();
                    System.out.println(entry);

                    Request triggerUpdate = RequestBuilder.newBuilder()
                            .method("trigger.update")
                            .paramEntry("priority", 4)
                            .paramEntry("triggerid", entry)
                            .paramEntry("status", 1)
                            .build();

                    JSONObject responsetriggerUpdate = zabbixApi.call(triggerUpdate);
                    System.out.println(responsetriggerUpdate);
                    //Create itService for item
                    String itServiceNameItem = list01[0] + "." + list01[i];
                    System.out.println("itServiceNameItem: " + itServiceNameItem);

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
                    System.out.println("itServiceIdCreate: " + responseitServiceCreateItem);

                    //Get ServiceCreateItem Created on previous step
                    filter.put("name", itServiceNameItem);
                    Request itServiceNameItemGet = RequestBuilder.newBuilder()
                            .method("service.get")
                            .paramEntry("filter", filter)
                            .build();

                    JSONObject responseitServiceNameItemGet = zabbixApi.call(itServiceNameItemGet);
                    String itServiceItemid = responseitServiceNameItemGet.getJSONArray("result").getJSONObject(0).getString("serviceid");
                    System.out.println("itServiceItemid: " + itServiceItemid);

                    Request itAdddependiciesToItem = RequestBuilder.newBuilder()
                            .method("service.update")
                            .paramEntry("serviceid", itServiceItemid)
                            .paramEntry("parentid", itServiceid)
                            .build();

                    JSONObject responseitAdddependiciesToItem = zabbixApi.call(itAdddependiciesToItem);
                    System.out.println("responseitAdddependiciesToItem: " + responseitAdddependiciesToItem);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
