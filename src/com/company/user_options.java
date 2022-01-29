
package com.company;
//import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class user_options {

  public static void logout_dialog(){
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

  public static void useroption(String user_id) {
    Frame frm = new Frame("User Options");
    frm.setSize(500, 300);
    frm.setVisible(true);
    frm.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    Panel p = new Panel();
    Panel p1 = new Panel();

    Button Allbooks = new Button("See all books");
    p.add(Allbooks);
    Allbooks.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String sql = "select * from BOOKS";
        try {
          Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Gogopal@123");
          Statement stmt = connection.createStatement(); //connect to database
          stmt.executeUpdate("USE LIBRARY"); // use librabry
          stmt = connection.createStatement();
          ResultSet rs = stmt.executeQuery(sql);
          JTable book_list = new JTable(); //show data in table format
//        book_list.setModel(DbUtils.resultSetToTableModel(rs));
          DefaultTableModel model = new DefaultTableModel(new String[]{"book_id", "book_name", "author_name", "no_copies", "cost", "genre"}, 0);
          while (rs.next()) {
            String id = rs.getString("book_id");
            String name = rs.getString("book_name");
            String aname = rs.getString("author_name");
            String no_copies = rs.getString("no_copies");
            String cost = rs.getString("cost");
            String genre = rs.getString("genre");

            model.addRow(new Object[]{id, name, aname, no_copies, cost, genre});
            book_list.setModel(model);
          }

          JScrollPane scrollPane = new JScrollPane(book_list); //enable scroll bar
          JFrame p2 = new JFrame("Books Available");
          p2.add(scrollPane); //add scroll bar
          p2.setSize(800, 400); //set dimensions of view books frame
          p2.setVisible(true);
          p2.setLocationRelativeTo(null);
        } catch (SQLException e1) {
          JOptionPane.showMessageDialog(null, e1);
        }

      }
    });




    Button bookwm = new Button("Books with me");
    p.add(bookwm);

    bookwm.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
//      stmt.executeUpdate("USE LIBRARY"); //Use the database with the name "Library"
        try {
          Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Gogopal@123");
          Statement stmt = connection.createStatement(); //connect to database

          ResultSet rs = stmt.executeQuery("select book_id,issued_date,return_date,book_name from issued_book natural join books where user_id=" + user_id + " ;");
          JTable book_list = new JTable(); //show data in table format
//        book_list.setModel(DbUtils.resultSetToTableModel(rs));
          DefaultTableModel model = new DefaultTableModel(new String[]{"Book Name","Issued date","Return date"}, 0);


          int c = 0;
          while (rs.next()) {
            String name = rs.getString("book_name");
            String idat = rs.getString("issued_date");
            String rdat = rs.getString("return_date");

            model.addRow(new Object[]{name, idat, rdat});
            book_list.setModel(model);
            c++;
          }
          JScrollPane scrollPane = new JScrollPane(book_list); //enable scroll bar
          JFrame p2 = new JFrame("Books with you");
          if (c == 0) {
            p2.setSize(200, 100); //set dimensions of view books frame
            JLabel no_book = new JLabel("No book found");
            p2.add(no_book);
          } else {
            p2.setSize(800, 400); //set dimensions of view books frame
            p2.add(scrollPane); //add scroll bar
          }
          p2.setVisible(true);
          p2.setLocationRelativeTo(null);
        } catch (SQLException e1) {
          JOptionPane.showMessageDialog(null, e1);
        }
      }
    });

    Button logout = new Button("Logout");
    p.add(logout);
    logout.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frm.dispose();
        logout_dialog();
        LoginMain.Login();
      }
    });

//     Button retbook=new Button("Pay fine");
//     p.add(retbook);
//     retbook.addActionListener(new ActionListener() {
//       @Override
//       public void actionPerformed(ActionEvent e) {
// //       stmt.executeUpdate("USE LIBRARY"); //Use the database with the name "Library"
//         String st = ("select fine from issued_book where issued_book.user_id="+user_id+" ;");
//         try {
//           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Gogopal@123");
//           Statement stmt = connection.createStatement(); //connect to database
//           stmt.executeUpdate("USE LIBRARY"); // use librabry
//           stmt = connection.createStatement();
//           ResultSet rs = stmt.executeQuery(st);
//           JTable book_list = new JTable(); //show data in table format
//           DefaultTableModel model = new DefaultTableModel(new String[]{"fine"}, 0);
//           int c = 0;
//           while (rs.next()) {
//             String name = rs.getString("fine");
//             model.addRow(new Object[]{name});
//             book_list.setModel(model);
//             c++;
//           }
//           JScrollPane scrollPane = new JScrollPane(book_list); //enable scroll bar
//           JFrame p2 = new JFrame("Fines");
//           if (c == 0) {
//             p2.setSize(200, 100); //set dimensions of view books frame
//             JLabel no_book = new JLabel("No fine found");
//             p2.add(no_book);
//           } else {
//             p2.setSize(800, 400); //set dimensions of view books frame
//             p2.add(scrollPane); //add scroll bar
//           }
//           p2.setVisible(true);
//           p2.setLocationRelativeTo(null);
//         }catch (SQLException e1) {
//           JOptionPane.showMessageDialog(null, e1);
//         }
//       }
//     });

    GridLayout newGrid=new GridLayout(4,1);
    newGrid.setVgap(10);
    p.setLayout(newGrid);
    p1.add(p);
    frm.add(p1,BorderLayout.NORTH);
    frm.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        frm.dispose();
      }
    });
  }
}
