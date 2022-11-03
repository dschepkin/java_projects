package com.dschepkin;

import com.jcraft.jsch.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.Properties;

import javax.swing.*;

public class Main extends JFrame {
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 300;
    String command;
    String title;

    Main(String target) {
        command = "/home/dschepkin/check_daily_thor/scripts_for_desktop/" + target + ".sh";
        this.title = target;
    }

    public void getData() {
        //JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame02 = new JFrame();
        frame02.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame02.setBounds(200,100,1000,1000);
        frame02.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

        final JTextArea ta01 = new JTextArea();
        ta01.setBounds(5,5,950,1000);
        ta01.setFont(ta01.getFont().deriveFont(18f));
        ta01.setLineWrap(true); //выполняется перенос
        ta01.setWrapStyleWord(true);//слова не разрывать

        try {
            JSch jsch = new JSch();

            String host = "oracle@93.183.24.222";
            String passwd = "rdfh10nbhf";

            String user = host.substring(0, host.indexOf('@'));
            host = host.substring(host.indexOf('@') + 1);

            Session session = jsch.getSession(user, host, 22);
            session.setPassword(passwd);

            Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();

            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);

            //channel.setInputStream(System.in);
            channel.setInputStream(null);

            //channel.setOutputStream(System.out);

            //FileOutputStream fos=new FileOutputStream("/tmp/stderr");
            //((ChannelExec)channel).setErrStream(fos);
            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();

            channel.connect();

            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(in));
                ta01.read(input, "Reading file :-)");
            } catch (Exception e) {
                e.printStackTrace();
            }

/*            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    System.out.print(new String(tmp, 0, i));

                }
                if (channel.isClosed()) {
                    if (in.available() > 0) continue;
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }*/
            channel.disconnect();
            session.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }

        final JScrollPane scrollPane = new JScrollPane(ta01);
        scrollPane.setBounds(3,3,950,1000);

        frame02.getContentPane().add(scrollPane);

        ta01.setEditable(false);
        frame02.setTitle(title);
        //frame02.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame02.pack();
        frame02.setVisible(true);
    }
}
