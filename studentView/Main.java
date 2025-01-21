package studentView;

import java.sql.SQLException;

public class Main 
{
public static void main(String [] args , Student student)
{
try {
    new HomeFrame(student);
} catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}
}
}