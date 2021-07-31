package mgmtsystem.dataAccessObject;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mgmtsystem.DBUtility.DataBaseConnection;
import mgmtsystem.GUI.AddDoctor;
import mgmtsystem.PO.DoctorAdd;

public class DoctorAddDAO {
     
    public static void displaydata()
    {
          DefaultTableModel model=(DefaultTableModel)AddDoctor.jTable1.getModel();
          Connection con=DataBaseConnection.getConnection();
          try
          {
               Statement st=con.createStatement();
               ResultSet rs= st.executeQuery("Select * from doctor ");
               model.setRowCount(0);
               while(rs.next())
               {
                    model.addRow(new Object[]{rs.getString("DoctorId"),rs.getString("First_Name"),rs.getString("Last_Name"),rs.getString("Email"),rs.getString("DOB"),rs.getString("Age"),rs.getString("Gender"),rs.getString("Position"),rs.getString("Contact"),rs.getString("Current_Address"),rs.getString("Permanant_Address")});
               }
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,e.getMessage());
          }
    }
     public static int InsertDoctor(DoctorAdd ad) throws SQLException
     {
         Connection con=DataBaseConnection.getConnection();
         DateFormat df=new SimpleDateFormat("dd/MM/yy");
         Date d= new Date();
         PreparedStatement pst= con.prepareStatement("insert into doctor(First_Name,Last_Name,Email,DOB,Age,Gender,Position,Contact,Current_Address,Permanant_Address,Joining_Date) values(?,?,?,?,?,?,?,?,?,?,?)");
         pst.setString(1,ad.getFirst_Name());
         pst.setString(2, ad.getLast_Name());
         pst.setString(3, ad.getEmail());
         pst.setString(4,ad.getDOB());
         pst.setString(5,String.valueOf(ad.getAge()));
         pst.setString(6, ad.getGender());
         pst.setString(7, ad.getPosition());
         pst.setString(8, String.valueOf(ad.getContact()));
         pst.setString(9,ad.getCurrent_Address());
         pst.setString(10,ad.getPermanant_Address());
         pst.setString(11, df.format(d));
         int i =pst.executeUpdate();
         return i;
     }
      
}
