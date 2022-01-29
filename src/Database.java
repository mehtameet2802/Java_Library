import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    public static void main(String[] args){
        try{
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/library","root","Tu^sh1234");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Gogopal@123");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from books");

            while(resultSet.next()){
                System.out.println(resultSet.getString("book_id"));
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}


