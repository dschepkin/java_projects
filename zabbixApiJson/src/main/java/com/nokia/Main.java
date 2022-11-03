package com.nokia;

import io.github.hengyunabc.zabbix.api.DefaultZabbixApi;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.ZabbixApi;
import io.github.hengyunabc.zabbix.api.RequestBuilder;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.BasicConfigurator;

public class Main {
    public static void main(String[] args) {

        String templateName = "nsn_integrations";
        String url = "http://93.183.24.200/zabbix.nokia.com/public_html/api_jsonrpc.php";
        String user = "Admin";
        String password = "&Ty56_*rt";
        //String filename = "c:\\notebook_work_dschepkin\\dschepkin\\tmp\\test_itemsList.txt";
        String filename = "test_itemsList.txt";

        Integrations integr01 = new Integrations(templateName,url,user,password,filename);
        integr01.before();
        integr01.login();
        integr01.actions();
    }
}
