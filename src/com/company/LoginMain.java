package com.company;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LoginMain {
    public static void Login() {
        JFrame frame = new JFrame("Login");//creating instance of JFrame
        frame.setSize(300, 300);

        JButton lib_login = new JButton("Librarian Login");
        lib_login.setBounds(50, 50, 150, 40);

        JButton user_login = new JButton("Uer Login");
        user_login.setBounds(50, 120, 150, 40);

        lib_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin.login();
            }
        });

        user_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.UserLogin();
            }
        });

        frame.add(lib_login);
        frame.add(user_login);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

    }
}