package dschepkin.com;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.Properties;

import javax.swing.*;

class Gui extends JFrame {
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 300;
    String command;
    String title;

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

