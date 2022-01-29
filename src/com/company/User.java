package com.company;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.*;
import javax.swing.*;
public class User {
      public static void UserLogin() {
//          JFrame f=new JFrame("UserLogin");//creating instance of JFrame
//          JLabel l1,l2;
//          l1=new JLabel("Username");  //Create label Username
//          l1.setBounds(130,15, 100,30); //x axis, y axis, width, height
//
//          JTextField F_user = new JTextField(); //Create text field for username
//          F_user.setBounds(210, 15, 200, 30);
//
//          l2=new JLabel("Password");  //Create label Password
//          l2.setBounds(130,50, 100,30);
//
//          JPasswordField F_pass=new JPasswordField(); //Create text field for password
//          F_pass.setBounds(210, 50, 200, 30);
//
//          JButton login_but=new JButton("Login");//creating instance of JButton for Login Button
//          login_but.setBounds(130,90,80,25);

          JFrame f = new JFrame("User Login");
          JLabel l1, l2;
          l1 = new JLabel("Username");  //Create label Username
          l1.setBounds(30, 15, 100, 30);

          TextField F_user = new TextField(); //Create text field for username
          F_user.setBounds(110, 15, 200, 30);

          l2 = new JLabel("Password");  //Create label Password
          l2.setBounds(30, 50, 100, 30);

          JPasswordField F_pass = new JPasswordField(); //Create text field for password
          F_pass.setBounds(110, 50, 200, 30);

          Button login_but = new Button("Login");//creating instance of Button for Login Button
          login_but.setBounds(130, 90, 80, 25);

          login_but.addActionListener(new ActionListener() {  //Perform action
              public void actionPerformed(ActionEvent e) {
                  String username = F_user.getText(); //Store username entered by the user in the variable "username"
                  String password = F_pass.getText(); //Store password entered by the user in the variable "password"

                  if (username.isEmpty()) //If username is null
                  {
                      error("Please enter username"); //Display dialog box with the message
                  } else if (password.isEmpty()) //If password is null
                  {
                      error("Please enter password"); //Display dialog box with the message
                  } else { //If both the fields are present then to login the user, check wether the user exists already
                      //System.out.println("Login connect");

//                    Connection connection = Database();  //Connect to the database
                      try {
                          Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Gogopal@123");
                          Statement stmt = connection.createStatement();

                          ResultSet resultSet = stmt.executeQuery("SELECT * FROM user WHERE user_name='"+ username+"'"); //Execute query
                          String pass = null;
                          String id = null;
                          while(resultSet.next()){
                              pass = resultSet.getString("user_pass");
                              id = resultSet.getString("user_id");
                          }
                          if (!pass.equals(password)) { //Move pointer below
                              error("Wrong Username/Password!"); //Display Message
                          } else {
                              f.dispose();
                              user_options.useroption(id);
                          }
                      } catch (Exception ex) {
                          error("Some error");
                          System.out.println(ex.toString());
                      }
                  }
              }
          });
          f.add(F_pass); //add password
          f.add(login_but);//adding button in JFrame
          f.add(F_user);  //add user
          f.add(l1);  // add label1 i.e. for username
          f.add(l2); // add label2 i.e. for password

          f.setSize(400, 180);//400 width and 500 height
          f.setLayout(null);//using no layout managers
          f.setVisible(true);//making the frame visible
          f.setLocationRelativeTo(null);
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




