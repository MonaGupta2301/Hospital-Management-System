package mgmtsystem.DBUtility;
import java.sql.*;
import javax.swing.JOptionPane;
public class DataBaseConnection {
    private static Connection con;
    static
    {
         try
         {
               Class.forName("com.mysql.jdbc.Driver");
               con=DriverManager.getConnection("jdbc:mysql://localhost:3307/hospitalmanagement","root","");
                //JOptionPane.showMessageDialog(null," Connection Created","Information",JOptionPane.INFORMATION_MESSAGE);
         }
         catch(ClassNotFoundException e)
         {
             JOptionPane.showMessageDialog(null,"Driver is not get Registed","Error",JOptionPane.ERROR_MESSAGE);
         }
         catch(SQLException e)
         {
              JOptionPane.showMessageDialog(null," Connection Not Created","Error",JOptionPane.ERROR_MESSAGE);
         }
        
    }
     public static Connection getConnection()
     {
             return con;
     }
     public static void closedConnection()
     {
         if(con!=null)
         {
             try
             {
                  con.close();
             }
             catch(Exception e)
             {
                   JOptionPane.showMessageDialog(null," Connection Not Created","Error",JOptionPane.ERROR_MESSAGE);
             }
         }
     }
     public static void main(String arg[])
     {
         
     }
}
