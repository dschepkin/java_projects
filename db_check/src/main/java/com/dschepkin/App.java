package com.dschepkin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App
{
    public static void main( String[] args )
    {
        JFrame frame01 = new JFrame();
        frame01.setBounds(10,10,110,310);
        JFrame.setDefaultLookAndFeelDecorated(true);
        JButton button00 = new JButton("thor");
        button00.setBounds(20,10,110,40);
        JButton button01 = new JButton("thortest");
        button01.setBounds(20,60,110,40);
        JButton button02 = new JButton("thordev");
        button02.setBounds(20,110,110,40);
        JButton button03 = new JButton("thostaging");
        button03.setBounds(20,160,110,40);
        JButton button04 = new JButton("Ericsson CM");
        button04.setBounds(20,210,110,40);

        frame01.add(button00);
        frame01.add(button01);
        frame01.add(button02);
        frame01.add(button03);
        frame01.add(button04);

        button00.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main main = new Main("DB_thor");
                main.getData();
            }
        });

        button01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main main = new Main("DB_thortest");
                main.getData();
            }
        });

        button02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main main = new Main("DB_thordev");
                main.getData();
            }
        });

        button03.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main main = new Main("DB_thorstaging");
                main.getData();
            }
        });

        button04.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main main = new Main("Ericsson_CM");
                main.getData();
            }
        });

        frame01.setTitle("Check databases...");
        frame01.setSize(110,310);
        frame01.setLayout(null);
        frame01.setVisible(true);
        frame01.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
