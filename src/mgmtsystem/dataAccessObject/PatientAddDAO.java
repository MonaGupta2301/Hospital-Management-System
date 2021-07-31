package mgmtsystem.dataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mgmtsystem.DBUtility.DataBaseConnection;
import mgmtsystem.GUI.AddDoctor;
import mgmtsystem.GUI.AddPatient;
import mgmtsystem.PO.PatientAdd;

public class PatientAddDAO {
    public static void displaydata()
    {
          DefaultTableModel model=(DefaultTableModel)AddPatient.jTable1.getModel();
          Connection con=DataBaseConnection.getConnection();
          try
          {
               Statement st=con.createStatement();
               ResultSet rs= st.executeQuery("Select * from patient ");
               model.setRowCount(0);
               while(rs.next())
               {
                    model.addRow(new Object[]{rs.getString("Patient_Id"),rs.getString("First_Name"),rs.getString("Last_Name"),rs.getString("Email"),rs.getString("Age"),rs.getString("DOB"),rs.getString("Phone"),rs.getString("Gender"),rs.getString("Address")});
               }
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,e.getMessage());
          }
    }
     public static int InsertPatient(PatientAdd ad) throws SQLException
     {
         int i=0;
          try
          {
         Connection con=DataBaseConnection.getConnection();
         DateFormat df=new SimpleDateFormat("dd/MM/yy");
         java.util.Date date= new Date();
         DateFormat df1=new SimpleDateFormat("HH:mm:SS");
         java.util.Date date1= new Date();
         PreparedStatement pst= con.prepareStatement("insert into patient(First_Name,Last_Name,Email,age,DOB,Phone,Gender,Address,Joining_Date,Joining_Time) values(?,?,?,?,?,?,?,?,?,?)");
         pst.setString(1,ad.getFirst_Name());
         pst.setString(2, ad.getLast_Name());
         pst.setString(3, ad.getEmail());
         pst.setString(4,String.valueOf(ad.getAge()));
         pst.setString(5,ad.getDOB());
         pst.setString(6, String.valueOf(ad.getPhone()));
         pst.setString(7, ad.getGender());
         pst.setString(8,ad.getAddress());
         pst.setString(9, df.format(date));
         pst.setString(10,df1.format(date1) );
          i =pst.executeUpdate();
          }
          catch(Exception e)
          {
               JOptionPane.showMessageDialog(null, e);
          }
          
         return i;
     }
}
