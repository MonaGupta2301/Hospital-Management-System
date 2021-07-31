package mgmtsystem.dataAccessObject;
import java.sql.*;
import javax.swing.JOptionPane;
import mgmtsystem.DBUtility.DataBaseConnection;
import mgmtsystem.PO.User;
public class UserDAO {
    
    public static String ValidateUser(User us) throws SQLException
    {
        Connection con=DataBaseConnection.getConnection();
        PreparedStatement pst=con.prepareStatement("Select UserName From admin where UserName=? and Password=?");
        pst.setString(1, us.getUserName());
        pst.setString(2, us.getPassword());
        ResultSet rs=pst.executeQuery();
        String UserName=null;
        String Password=null;
        if(rs.next())
        {
             UserName=rs.getString("UserName");
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Incorrect User Name and PassWord", "Information",JOptionPane.INFORMATION_MESSAGE);
        }
        return UserName;
    }
    
}
