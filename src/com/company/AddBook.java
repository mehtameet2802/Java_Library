package com.company;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AddBook{
    public static void Book() {
        Frame frm=new Frame("Add a book");
        Label lbl = new Label(" ");
        frm.add(lbl);
        frm.setSize(450,450);
        frm.setVisible(true);
        frm.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                frm.dispose();
            }
        });
        Panel p = new Panel();
        Panel p1 = new Panel();
        Label lbookName = new Label("Book Name");
        TextField tbookName = new TextField(20);
        Label lbookId =new Label("Book ID");
        TextField tbookId=new TextField(20);
        Label lauthorName =new Label("Author Name");
        TextField tauthorName=new TextField(20);
        Label lnoOfCopies =new Label("No. of copies");
        TextField tnOfCopies=new TextField(20);
        Label lcost =new Label("Price");
        TextField tcost=new TextField(20);
        Label lgenre =new Label("Genre");
        TextField tgenre=new TextField(20);
        GridLayout newGrid = new GridLayout(0,2);
        newGrid.setHgap(10);
        newGrid.setVgap(10);
        p.setLayout(newGrid);
        p.add(lbookName);
        p.add(tbookName);
        p.add(lbookId);
        p.add(tbookId);
        p.add(lauthorName);
        p.add(tauthorName);
        p.add(lnoOfCopies);
        p.add(tnOfCopies);
        p.add(lcost);
        p.add(tcost);
        p.add(lgenre);
        p.add(tgenre);
        Button Submit=new Button("Add Book");
        Submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(     tauthorName.getText().isEmpty() ||
                        tbookName.getText().isEmpty() ||
                        tbookId.getText().isEmpty() ||
                        tnOfCopies.getText().isEmpty() ||
                        tcost.getText().isEmpty() ||
                        tgenre.getText().isEmpty())
                {
                    lbl.setText("Please Enter all the fields");
                }
                else{
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Gogopal@123");
                        Statement statement = connection.createStatement();
                        String query="insert into books (book_id,book_name,author_name,no_copies,cost,genre) values ("+tbookId.getText()+","+tbookName.getText()+","+tauthorName.getText()+","+tnOfCopies.getText()+","+tcost.getText()+","+tgenre.getText()+")";
                        statement.executeUpdate("insert into books (book_id,book_name,author_name,no_copies,cost,genre) values ("+tbookId.getText()+",'"+tbookName.getText()+"','"+tauthorName.getText()+"',"+Integer.parseInt(tnOfCopies.getText())+","+tcost.getText()+",'"+tgenre.getText()+"')");
                        lbl.setText("Book Added");
                    }
                    catch(Exception ecx){
                        lbl.setText("Oops! Something went wrong");
                        System.out.println(ecx);
                    }
                }
            }
        });
        p.add(Submit);
        p1.add(p);
        frm.add(p1,BorderLayout.NORTH);
    }
}