package com.company;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
public class issued{
    public static void issued() {
        JFrame frame2; //creating object of second JFrame
        DefaultTableModel defaultTableModel;//creating object of DefaultTableModel
        JTable table;//Creating object of JTable
        int flag = 0;
        frame2 = new JFrame("Database Results");
        frame2.setLayout(new FlowLayout());
        frame2.setSize(800, 800);
        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(800, 500));
        table.setFillsViewportHeight(true);
        frame2.add(new JScrollPane(table));
        defaultTableModel.addColumn("User Id");
        defaultTableModel.addColumn("Book id");
        defaultTableModel.addColumn("Issued date");
        defaultTableModel.addColumn("Return date");
//        defaultTableModel.addColumn("Fine");
        try {
//               Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/library","root","Tu^sh1234");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Gogopal@123");
        // Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","8879");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from issued_book ");
        while (resultSet.next()) {

            //Retrieving details from the database and storing it in the String variables
            String name = resultSet.getString("user_id");
            String roll = resultSet.getString("Book_Id");
            String idate = resultSet.getString("Issued_Date");
            String rdate = resultSet.getString("Return_Date");
//            String fine = resultSet.getString("Fine");
                defaultTableModel.addRow(new Object[]{name, roll, idate,rdate});//Adding row in Table
                frame2.setVisible(true);//Setting the visibility of second Frame
                frame2.validate();
        }


    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }

    }
}