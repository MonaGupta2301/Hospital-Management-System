package mgmtsystem.dataAccessObject;
import java.sql.*;
import javax.swing.JOptionPane;
import mgmtsystem.DBUtility.DataBaseConnection;

public class HospitaDAO {
     public static int DoctorRows()
     {
         int count=0;
          try
          {
                Connection con = DataBaseConnection.getConnection();
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery("Select * from doctor");
                while(rs.next())
                {
                     count++;
                }
          }
          catch(Exception e)
          {
               JOptionPane.showMessageDialog(null, e);
          }
           return count;
     }
      public static int PatientRows()
     {
         int count=0;
          try
          {
                Connection con = DataBaseConnection.getConnection();
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery("Select * from patient");
                while(rs.next())
                {
                     count++;
                }
          }
          catch(Exception e)
          {
               JOptionPane.showMessageDialog(null, e);
          }
           return count;
     }
       public static int AppoinmentRows()
     {
         int count=0;
          try
          {
                Connection con = DataBaseConnection.getConnection();
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery("Select * from appointment");
                while(rs.next())
                {
                     count++;
                }
          }
          catch(Exception e)
          {
               JOptionPane.showMessageDialog(null, e);
          }
           return count;
     }
}
