package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class librarian {

    public static void library_options() {
        Frame lib_f1 = new Frame("Librarian");

        Button book_new = new Button("Add new Book");
        book_new.setBounds(250, 50, 100, 40);

        Button book_available = new Button("Available Books");
        book_available.setBounds(250, 110, 100, 40);

        Button book_issued = new Button("Issued Books");
        book_issued.setBounds(250, 170, 100, 40);

        Button book_issue = new Button("Issue Book");
        book_issue.setBounds(250, 230, 100, 40);

        Button list_defaulter = new Button("Defaulter's List");
        list_defaulter.setBounds(250, 290, 100, 40);

        Button book_return = new Button("Return Book");
        book_return.setBounds(250, 350, 100, 40);

        Button logout = new Button("Logout");
        logout.setBounds(250, 410, 100, 40);

        book_issued.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                issued.issued();
            }
        });

        book_available.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                available_books.avail();
            }
        });

        list_defaulter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaulter.defaul();
            }
        });

        book_new.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBook.Book();
            }
        });

        book_issue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                issue_form.form();
            }
        });

        book_return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                return_form.book_return();
            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lib_f1.dispose();
                logout_dialog();
                LoginMain.Login();
            }
        });

        lib_f1.add(book_new);
        lib_f1.add(book_available);
        lib_f1.add(book_issued);
        lib_f1.add(book_issue);
        lib_f1.add(book_return);
        lib_f1.add(list_defaulter);
        lib_f1.add(logout);

        lib_f1.setSize(600, 550);
        lib_f1.setLayout(null);
        lib_f1.setVisible(true);
        lib_f1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                lib_f1.dispose();
            }
        });
    }

    public static void logout_dialog() {
        JFrame dialog_frame = new JFrame();
        JDialog dialog = new JDialog(dialog_frame, "Confirmation", true);

        Label text = new Label();
        text.setText("You have been successfully logged out ");
        text.setBounds(100, 20, 220, 20);

        JButton ok = new JButton("Ok");
        ok.setBounds(125, 100, 150, 30);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.add(text);
        dialog.add(ok);

        dialog.setSize(400, 200);
        dialog.setVisible(true);
        dialog.setLayout(null);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.dispose();
            }
        });

    }
}
