//package com.company;
//import java.awt.*;
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.sql.*;
//
//public class defaulter{
//    public static void defaul() {
//        JFrame frame2; //creating object of second JFrame
//        DefaultTableModel defaultTableModel;//creating object of DefaultTableModel
//        JTable table;//Creating object of JTable
//
//        frame2 = new JFrame("Defaulter");
//        frame2.setLayout(new FlowLayout());
//        frame2.setSize(1000, 1000);
//
//        defaultTableModel = new DefaultTableModel();
//        table = new JTable(defaultTableModel);
//        table.setPreferredScrollableViewportSize(new Dimension(500, 500));
//        table.setFillsViewportHeight(true);
//        frame2.add(new JScrollPane(table));
//        defaultTableModel.addColumn("User_id");
//        defaultTableModel.addColumn("Book_id");
//        defaultTableModel.addColumn("User Name");
//        defaultTableModel.addColumn("Fine");
//        try {
////        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/library","root","Tu^sh1234");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Gogopal@123");
////            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","8879");
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from issued_book natural join user where Datediff(return_date,curdate())<0");
//            while (resultSet.next()) {
//
//                //Retrieving details from the database and storing it in the String variables
//                String name = resultSet.getString("user_id");
//                String roll = resultSet.getString("book_id");
//                String dept = resultSet.getString("user_name");
//                String fn = resultSet.getString("fine");
//
//                defaultTableModel.addRow(new Object[]{name, roll, dept,fn});//Adding row in Table
//                frame2.setVisible(true);//Setting the visibility of second Frame
//                frame2.validate();
//
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//}


package com.company;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class defaulter{
    public static void defaul() {
        JFrame frame2; //creating object of second JFrame
        DefaultTableModel defaultTableModel;//creating object of DefaultTableModel
        JTable table;//Creating object of JTable

        frame2 = new JFrame("Defaulter");
        frame2.setLayout(new FlowLayout());
        frame2.setSize(1000, 1000);

        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 500));
        table.setFillsViewportHeight(true);
        frame2.add(new JScrollPane(table));
        defaultTableModel.addColumn("User_id");
        defaultTableModel.addColumn("Book_id");
        defaultTableModel.addColumn("User Name");
        defaultTableModel.addColumn("Fine");
        try {
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/library","root","Tu^sh1234");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Gogopal@123");
//            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","8879");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select *,Datediff(curdate(),return_date)*10 as fin from issued_book natural join user where Datediff(return_date,curdate())<0");
            while (resultSet.next()) {
//select *,Datediff(curdate(),return_date)*10 from issued_book natural join user where Datediff(return_date,curdate())<0;
                //Retrieving details from the database and storing it in the String variables
                String name = resultSet.getString("user_id");
                String roll = resultSet.getString("book_id");
                String dept = resultSet.getString("user_name");
                String fn = resultSet.getString("fin");

                defaultTableModel.addRow(new Object[]{name, roll, dept,fn});//Adding row in Table
                frame2.setVisible(true);//Setting the visibility of second Frame
                frame2.validate();

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
