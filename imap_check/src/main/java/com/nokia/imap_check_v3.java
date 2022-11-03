package com.nokia;

import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;

public class imap_check_v3 {
    public static void main(String[] args) {

        String email_id = args[0];
        String password = "empty";

        if (email_id == "bot.panda@nokia.com") {
            password = "nvtwydfgddcqwfxg";
        }else if (email_id == "gdc_ru.flm_mts@nokia.com") {
            password = "tcqffvtwhnlmwfhs";
        }else if (email_id == "gdc_ru.flm_vc@nokia.com") {
            password = "ksrypfvwtrbyqlmf";
        }else if (email_id == "cmtn.rostov@nokia.com") {
            password = "pnmssrqpvmxthqms";
        }else if (email_id == "mts_tn.cm@nokia.com") {
            password = "xtnjyllvfmbgwhmm";
        }else if (email_id == "mts_bss.cm@nokia.com") {
            password = "kgkbglynqnfrqfby";
        }else if (email_id == "mts_sss.cm@nokia.com") {
            password = "hxydpggslsjvmlpr";
        }else if (email_id == "am.request@nokia.com") {
            password = "qlwpjklhdtckhrts";
        }else {
            System.out.println("Wrong email!");
        }

        Properties properties = new Properties();

        properties.put("mail.store.protocol", "imaps");                     //You can use imap or imaps , *s -Secured
        properties.put("mail.imaps.host", "outlook.office365.com");         //Host Address of Your Mail
        properties.put("mail.imaps.port", "993");                           //Port number of your Mail Host

        try {

            Session session = Session.getDefaultInstance(properties, null); //create a session
            Store store = session.getStore("imaps");                        //SET the store for IMAPS

            store.connect(email_id, password);                              //Trying to connect IMAP server

            Folder inbox = store.getFolder("inbox");                        //Get inbox folder
            inbox.open(Folder.READ_ONLY);                                   //SET readonly format (*You can set read and write)

            int messageCountUnread = inbox.getNewMessageCount();
            System.out.println(messageCountUnread);

            inbox.close(true);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}