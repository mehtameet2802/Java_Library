package com.company;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
public class available_books{
    public static void avail() {
        JFrame frame2; //creating object of second JFrame
        DefaultTableModel defaultTableModel;//creating object of DefaultTableModel
        JTable table;//Creating object of JTable

        frame2 = new JFrame("Available Books");
        frame2.setLayout(new FlowLayout());
        frame2.setSize(1000, 1000);

        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 500));
        table.setFillsViewportHeight(true);
        frame2.add(new JScrollPane(table));
        defaultTableModel.addColumn("Book Name");
        defaultTableModel.addColumn("Author Name");
        defaultTableModel.addColumn("Genre");
        try {
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/library","root","Tu^sh1234");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Gogopal@123");
//        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","8879");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from books ");
        while (resultSet.next()) {

            //Retrieving details from the database and storing it in the String variables
            String name = resultSet.getString("book_name");
            String roll = resultSet.getString("author_name");
            String dept = resultSet.getString("genre");

                defaultTableModel.addRow(new Object[]{name, roll, dept});//Adding row in Table
                frame2.setVisible(true);//Setting the visibility of second Frame
                frame2.validate();

        }


    } catch (SQLException e) {
        e.printStackTrace();
    }

    }
}