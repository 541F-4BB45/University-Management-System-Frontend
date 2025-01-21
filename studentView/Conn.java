package studentView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn {

        public static Connection connection;
        Statement statement;
        public void call(){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/schoolmanagementsystem";
                String username = "hamza";
                String password = "123456";
                connection = DriverManager.getConnection(url , username , password);
                statement = connection.createStatement();
                System.out.println("Connection Established");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

}

