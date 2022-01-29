package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

public class return_form {
    public static void book_return() {

        Frame return_form = new Frame("Return a book");

        Calendar future_date = Calendar.getInstance();
        Calendar current_date = Calendar.getInstance();
        future_date.setTime(new Date());
        future_date.add(Calendar.DATE, 7);
        future_date.get(Calendar.DATE);

        String current_day = Integer.toString(current_date.get(Calendar.DATE));
        String current_month = Integer.toString(current_date.get(Calendar.MONTH) + 1);
        String current_year = Integer.toString(current_date.get(Calendar.YEAR));

        Checkbox check = new Checkbox("Fine Paid");
        check.setSize(20, 20);
        check.setBounds(250, 190, 100, 40);

        JButton btn_return = new JButton("Return");
        btn_return.setBounds(330, 260, 100, 40);
        btn_return.setVisible(true);

        JButton check_fine = new JButton("Check Fine");
        check_fine.setBounds(180, 260, 100, 40);
        check_fine.setVisible(true);

        Label label_isbn = new Label("Enter book isbn number over here");
        label_isbn.setBounds(50, 50, 180, 20);

        Label label_issued_to = new Label("Enter id of the issued person");
        label_issued_to.setBounds(50, 80, 180, 20);

        Label label_issuing_date = new Label("Issuing Date");
        label_issuing_date.setBounds(50, 110, 180, 20);

        Label label_return_date = new Label("Returning date");
        label_return_date.setBounds(50, 140, 180, 20);

        Label label_returned_date = new Label("Returned on");
        label_return_date.setBounds(50, 170, 180, 20);

        TextField isbn = new TextField();
        isbn.setBounds(240, 50, 100, 20);

        TextField issued_to = new TextField();
        issued_to.setBounds(240, 80, 100, 20);

        TextField issuing_date = new TextField();
        issuing_date.setEditable(false);
        issuing_date.setBounds(240, 110, 180, 20);

        TextField return_date = new TextField();
        return_date.setEditable(false);
        return_date.setBounds(240, 140, 180, 20);

        TextField returned_date = new TextField();
        returned_date.setEditable(false);
        returned_date.setBounds(240, 170, 180, 20);
        returned_date.setText(current_year + "/" + current_month + "/" + current_day);

        Label lbl = new Label();
        lbl.setBounds(50, 220, 300, 40);

        check_fine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input_user_id = issued_to.getText();
                int input_book_id = Integer.parseInt(isbn.getText());
                int fine = 0;
                try {
                    //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/library","root","Tu^sh1234");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Gogopal@123");
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("select * from issued_book where user_id=" + input_user_id);
                    while (resultSet.next()) {
                        issuing_date.setText(resultSet.getDate("issued_date").toString());
                        return_date.setText(resultSet.getDate("return_date").toString());
                    }
                    ResultSet resultSet1 = statement.executeQuery("select Datediff(curdate(),return_date) as dd from issued_book where user_id=" + input_user_id);
                    while (resultSet1.next()) {
                        fine = resultSet1.getInt("dd") * 10;
                        if(fine >0){
                            lbl.setText("Total fine to pay is " + fine);
                        }
                        else{
                            lbl.setText("No fine is applicable");
                        }
                    }
                } catch (Exception a) {
                    error("Some error");                }
            }
        });

        btn_return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input_user_id = issued_to.getText();
                int input_book_id = Integer.parseInt(isbn.getText().toString());
                if (check.isEnabled()) {
                    try {
                        //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/library","root","Tu^sh1234");
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Gogopal@123");
                        Statement statement = connection.createStatement();
                        int resultSet = statement.executeUpdate("delete from issued_book where user_id=" + input_user_id);
                        ResultSet resultSet1 = statement.executeQuery("select * from books where book_id=" + input_book_id);
                        int count =0;
                        while (resultSet1.next()) {
                            count = resultSet1.getInt("no_copies");
                        }
                        count = count+1;
                        int resultSet2 = statement.executeUpdate("update issued_books set count="+count+"where book_id ="+input_book_id);
                        error("Book has been returned");
                    } catch (Exception a) {
                        error("Some error");
                        System.out.println(a);
                    }
                }
                else{
                    if(lbl.getText().toString().contains("No")){
                        error("Book has been returned");
                    }
                    else{
                        error("Fine is not yet paid");
                    }
                }
            }
        });


        return_form.setSize(600, 350);
        return_form.setLayout(null);
        return_form.setVisible(true);


        return_form.add(check);
        return_form.add(lbl);
        return_form.add(label_isbn);
        return_form.add(isbn);
        ;
        return_form.add(issuing_date);
        return_form.add(return_date);
        return_form.add(label_issuing_date);
        return_form.add(label_issued_to);
        return_form.add(issued_to);
        return_form.add(label_return_date);
        return_form.add(label_returned_date);
        return_form.add(returned_date);
        return_form.add(btn_return);
        return_form.add(check_fine);


        return_form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                return_form.dispose();
            }
        });

    }

    public static void error(String e) {
        JFrame error_frame = new JFrame();
        JDialog error_dialog = new JDialog(error_frame, "Error", true);
        Label error = new Label();
        error.setText(e);
        error.setBounds(100, 20, 220, 20);

        JButton ok = new JButton("Ok");
        ok.setBounds(125, 100, 150, 30);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                error_dialog.dispose();
            }
        });

        error_dialog.add(error);
        error_dialog.add(ok);

        error_dialog.setSize(400, 200);
        error_dialog.setVisible(true);
        error_dialog.setLayout(null);

        error_dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                error_dialog.dispose();
            }
        });


    }
}
