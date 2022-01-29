package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class request_form {
  public static void reqbook() {
    Frame frm=new Frame("Request a book");
    Label lbl = new Label("Please fill all blank");
    frm.add(lbl);
    frm.setSize(500,250);
    frm.setVisible(true);
    frm.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
        System.exit(0);
      }
    });
    Panel p = new Panel();
    Panel p1 = new Panel();
    Label jBookname = new Label("Book Name");
    TextField lBookname = new TextField(20);
    Label jBookno = new Label("Author name");
    TextField lBookno = new TextField(20);
    Label jStartdate =new Label("Start date");
    TextField lStartdate=new TextField(20);
    Label jNod =new Label("No of days");
    TextField lNod=new TextField(20);
    p.setLayout(new GridLayout(5,1));
    p.add(jBookname);
    p.add(lBookname);
    p.add(jBookno);
    p.add(lBookno);
    p.add(jStartdate);
    p.add(lStartdate);
    p.add(jNod);
    p.add(lNod);
    Button Submit=new Button("Submit");
    p.add(Submit);
    p1.add(p);
    frm.add(p1,BorderLayout.NORTH);
    frm.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        frm.dispose();
      }
    });

    String Bookname = lBookname.getText();
    String Authorname = lBookno.getText();
    String date = lStartdate.getText();
    String nod = lNod.getText();

//     try {
//     Connection connection = DriverManager.getConnection(jdbcmysqllocalhost3306library, root, Swapnil10);
//     Statement stmt = connection.createStatement(); connect to database
//     stmt.executeUpdate(USE LIBRARY);  use librabry
//     stmt=connection.createStatement();
//     String st =  insert into request values (Bookname+,+Authorname +,+date,nod);;
//     stmt.executeQuery(st);
//     } catch (SQLException var15) {
//       JOptionPane.showMessageDialog((Component)null, var15);
//     }

// use library
//     insert into request values (Bookname+,+Authorname +,+date,nod);
  }
}