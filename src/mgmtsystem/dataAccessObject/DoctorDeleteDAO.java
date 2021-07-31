package mgmtsystem.dataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mgmtsystem.DBUtility.DataBaseConnection;
import mgmtsystem.GUI.AddDoctor;
import mgmtsystem.GUI.DeleteDoctor;

public class DoctorDeleteDAO {
    public static void displaydata()
    {
          DefaultTableModel model=(DefaultTableModel)DeleteDoctor.jTable1.getModel();
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
    public static int DoctorBackup()
    {
        int j=0;
        int row[]=DeleteDoctor.jTable1.getSelectedRows();
        for(int i=0;i<row.length;i++)
        {
              String value=DeleteDoctor.jTable1.getModel().getValueAt(row[i], 0).toString();
              Connection con = DataBaseConnection.getConnection();
              try
              {
                         Statement st=con.createStatement();
                         ResultSet rs=st.executeQuery("select * from Doctor where DoctorId="+value);
                         if(rs.next())
                         {
                                String Doctor_Id=rs.getString("DoctorId");
                                String First_Name=rs.getString("First_Name");
                                String Last_Name=rs.getString("Last_Name");
                                String Email=rs.getString("Email");
                                String DOB=rs.getString("DOB");
                                String Age=rs.getString("Age");
                                String Gender=rs.getString("Gender");
                                String Position=rs.getString("Position");
                                String Contact=rs.getString("Contact");
                                String Current_Address=rs.getString("Current_Address");
                                String Permanant_Address=rs.getString("Permanant_Address");
                                String Joining_Date=rs.getString("Joining_Date");
                                 DateFormat df=new SimpleDateFormat("dd/MM/yy");
                                 Date d= new Date();
                                 String Leaving_Date=df.format(d);
                                String sql="insert into delete_doctor values("+Doctor_Id+",' "+First_Name+" ',' "+Last_Name+" ',' "+Email+" ',' "+DOB+" ', "+Age+" ,' "+Gender+" ',' "+Position+" ',"+Contact+",'"+Current_Address+" ','"+Permanant_Address+" ','"+Joining_Date+" ',' "+Leaving_Date+" ' )";
                                 j = st.executeUpdate(sql);
                         }
              }
              catch(Exception e)
              {
                    JOptionPane.showMessageDialog(null, e);
              }
        }
        return j ;
    }
    public static int DeleteDoctor()
    {
        int n=0;
        int row[]=DeleteDoctor.jTable1.getSelectedRows();
        for(int i=0;i<row.length;i++)
        {
              String value=DeleteDoctor.jTable1.getModel().getValueAt(row[i], 0).toString();
              Connection con = DataBaseConnection.getConnection();
              Statement st;
              try
              {
              st = con.createStatement();
              String sql="delete from doctor where DoctorId="+value;
               n=st.executeUpdate(sql);   
              }
              catch(Exception e)
              {
                  JOptionPane.showMessageDialog(null, e.getMessage());
              }
        }
        
        return n;
    }
}
