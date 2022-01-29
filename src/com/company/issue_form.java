package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import javax.swing.*;

public class issue_form {

    public static void form(){

        Frame issue_form = new Frame("Issue a book");

        Calendar future_date = Calendar.getInstance();
        Calendar current_date = Calendar.getInstance();
        future_date.setTime(new Date());
        future_date.add(Calendar.DATE,7);
        future_date.get(Calendar.DATE);

        String current_day = Integer.toString(current_date.get(Calendar.DATE));
        String current_month = Integer.toString(current_date.get(Calendar.MONTH)+1);
        String current_year = Integer.toString(current_date.get(Calendar.YEAR));

        String future_day = Integer.toString(future_date.get(Calendar.DATE));
        String future_month = Integer.toString(future_date.get(Calendar.MONTH)+1);
        String future_year = Integer.toString(future_date.get(Calendar.YEAR));


        Button issue = new Button("Issue");
        issue.setBounds(250,360,100,40);

        Button search_book  = new Button("Search Book");
        search_book.setBounds(250,140,100,30);

        Button search_user  = new Button("Search User");
        search_user.setBounds(250,240,100,30);


        Label label_isbn = new Label("Enter book isbn number over here");
        label_isbn.setBounds(50,50,180,20);

        Label label_name = new Label("Enter book name over here");
        label_name.setBounds(50,80,180,20);

        Label label_author = new Label("Enter book author over here");
        label_author.setBounds(50,110,180,20);

        Label label_issued_to = new Label("Enter id of the issuing person");
        label_issued_to.setBounds(50,180,180,20);

        Label label_user_name = new Label("Enter name of issuing person");
        label_user_name.setBounds(50,210,180,20);

        Label label_issue_date = new Label("Issuing Date");
        label_issue_date.setBounds(50,280,180,20);

        Label label_return_date = new Label("Returning date");
        label_return_date.setBounds(50,310,180,20);


        TextField isbn = new TextField();
        isbn.setBounds(240,50,100,20);

        TextField name = new TextField();
        name.setBounds(240,80,180,20);

        TextField author = new TextField();
        author.setBounds(240,110,180,20);

        TextField issued_to = new TextField();
        issued_to.setBounds(240,180,100,20);

        TextField issued_to_name = new TextField();
        issued_to_name.setBounds(240,210,180,20);

        TextField issue_date = new TextField();
        issue_date.setEditable(false);
        issue_date.setBounds(240,280,180,20);
        issue_date.setText(current_day+"/"+current_month+"/"+current_year);

        TextField return_date = new TextField();
        return_date.setEditable(false);
        return_date.setBounds(240,310,180,20);
        return_date.setText(future_day+"/"+future_month+"/"+future_year);



        issue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input_isbn = isbn.getText();
                String input_user_id = issued_to.getText();
//                String issued_date = issue_date.getText();
//                String returning_date = return_date.getText();
                int count = 0;
                int id = Integer.parseInt(isbn.getText());
                try{
//                          Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/library","root","Tu^sh1234");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Gogopal@123");
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("select * from books where book_id="+id);
                    while(resultSet.next()){
                        count = Integer.parseInt(resultSet.getString("no_copies"));
                    }
                }
                catch(Exception a){
                    error("Some error");
                }
                if(count<=0){
                    error("All the available books are issued");
                }
                else{
                    try{
//                          Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/library","root","Tu^sh1234");
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Gogopal@123");
                        Statement statement = connection.createStatement();
                        statement.executeUpdate("insert into issued_book values("+input_user_id + "," + input_isbn + ", curdate(),date_add(curdate(),interval 7 day),"+0+")");

                        System.out.println(count);
                        count = count-1;
                        statement.execute("update books set no_copies="+count+" where book_id="+input_isbn);
                        issue_dialog();
                    }
                    catch(Exception a){
//                        System.out.println(a.toString());
                        error("Same user cant be issued multiple books");
                    }
                }
            }
        });


        search_book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input_isbn = isbn.getText();
                String input_bname = name.getText();
                String input_bauthor = author.getText();
                if (input_isbn.isBlank()) {
                    if (input_bauthor.isBlank() || input_bname.isBlank()) {
                        error("* Check the input field of book details *");
                    }
                    else{
                        try{
//                          Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/library","root","Tu^sh1234");
                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Gogopal@123");
                            Statement statement = connection.createStatement();
                            ResultSet resultSet = statement.executeQuery("select * from books where book_name="+input_bname+"and author_name="+input_bauthor);
                            while(resultSet.next()){
                                isbn.setText(resultSet.getString("book_id"));
                            }
                        }
                        catch(Exception a){
                            error("Some error");
                        }
                    }
                }
                else{
                    try{
//                          Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/library","root","Tu^sh1234");
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Gogopal@123");
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("select * from books where book_id="+input_isbn);
                        while(resultSet.next()){
                            name.setText(resultSet.getString("book_name"));
                            author.setText(resultSet.getString("author_name"));
                        }
                    }
                    catch(Exception a){
                        error("Some error");
                    }
                }

            }
        });

        search_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String input_user_id = issued_to.getText();
                String input_user_name = issued_to_name.getText();

                if (input_user_id.isBlank()) {
                    if (input_user_name.isBlank()) {
                        error("* Check the input field of user details *");
                    }
                    else{
                        try{
//                          Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/library","root","Tu^sh1234");
                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Gogopal@123");
                            Statement statement = connection.createStatement();
                            ResultSet resultSet = statement.executeQuery("select * from user where user_name="+input_user_name);
                            while(resultSet.next()){
                                issued_to.setText(resultSet.getString("user_id"));
                            }
                        }
                        catch(Exception a){
                            error("Some error");
                        }
                    }
                }
                else{
                    try{
//                      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/library","root","Tu^sh1234");
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Gogopal@123");
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("select * from user where user_id="+input_user_id);
                        while(resultSet.next()){
                            issued_to_name.setText(resultSet.getString("user_name"));
                        }
                    }
                    catch(Exception a){
                        error("Some error");
                    }
                }
            }
        });




        issue_form.add(issue);
        issue_form.add(label_isbn);
        issue_form.add(isbn);
        issue_form.add(label_name);
        issue_form.add(name);
        issue_form.add(label_author);
        issue_form.add(author);
        issue_form.add(search_book);
        issue_form.add(issue_date);
        issue_form.add(return_date);
        issue_form.add(label_issue_date);
        issue_form.add(label_issued_to);
        issue_form.add(issued_to);
        issue_form.add(label_return_date);
        issue_form.add(search_user);
        issue_form.add(label_user_name);
        issue_form.add(issued_to_name);


        issue_form.setSize(600,430);
        issue_form.setLayout(null);
        issue_form.setVisible(true);
        issue_form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                issue_form.dispose();
            }
        });

    }

    public static void issue_dialog(){
        JFrame dialog_frame = new JFrame();
        JDialog dialog = new JDialog(dialog_frame,"Confirmation",true);

        Label text = new Label();
        text.setText("The book has been successfully issued ");
        text.setBounds(100,20,220,20);

        JButton ok = new JButton("Ok");
        ok.setBounds(125,100,150,30);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.add(text);
        dialog.add(ok);

        dialog.setSize(400,200);
        dialog.setVisible(true);
        dialog.setLayout(null);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.dispose();
            }
        });

    }

    public static void error(String e){
        JFrame error_frame = new JFrame();
        JDialog error_dialog = new JDialog(error_frame,"Error",true);
        Label error = new Label();
        error.setText(e);
        error.setBounds(100,20,220,20);

        JButton ok = new JButton("Ok");
        ok.setBounds(125,100,150,30);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                error_dialog.dispose();
            }
        });

        error_dialog.add(error);
        error_dialog.add(ok);

        error_dialog.setSize(400,200);
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
