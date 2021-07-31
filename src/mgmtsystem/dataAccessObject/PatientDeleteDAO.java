package mgmtsystem.dataAccessObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mgmtsystem.DBUtility.DataBaseConnection;
import mgmtsystem.GUI.DeleteDoctor;
import mgmtsystem.GUI.DeletePatient;

public class PatientDeleteDAO {
    public static void displaydata()
    {
          DefaultTableModel model=(DefaultTableModel)DeletePatient.jTable1.getModel();
          Connection con=DataBaseConnection.getConnection();
          try
          {
               Statement st=con.createStatement();
               ResultSet rs= st.executeQuery("Select * from patient");
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
     public static int PatientBackup()
    {
        int j=0;
        int row[]=DeletePatient.jTable1.getSelectedRows();
        for(int i=0;i<row.length;i++)
        {
              String value=DeletePatient.jTable1.getModel().getValueAt(row[i], 0).toString();
              Connection con = DataBaseConnection.getConnection();
              try
              {
                         Statement st=con.createStatement();
                         ResultSet rs=st.executeQuery("select * from patient where Patient_Id="+value);
                         if(rs.next())
                         {
                                String Patient_Id=rs.getString("Patient_Id");
                                String First_Name=rs.getString("First_Name");
                                String Last_Name=rs.getString("Last_Name");
                                String Email=rs.getString("Email");
                                String Age=rs.getString("age");
                                String DOB=rs.getString("DOB");
                                String Phone=rs.getString("Phone");
                                String Gender=rs.getString("Gender");                               
                                String Address=rs.getString("Address");
                                String Joining_Date=rs.getString("Joining_Date");
                                String Joining_Time=rs.getString("Joining_Time");
                                 DateFormat df=new SimpleDateFormat("dd/MM/yy");
                                 Date d= new Date();
                                 String Discharge_Date=df.format(d);
                                 DateFormat df1=new SimpleDateFormat("HH:mm:SS");
                                  java.util.Date date1= new Date();
                                  String Discharge_Time=df1.format(date1);
                                String sql="insert into delete_Patient values("+Patient_Id+",' "+First_Name+" ',' "+Last_Name+" ',' "+Email+" ',' "+Age+" ',' "+DOB+ " ', "+Phone+" ,' "+Gender+" ',' "+Address+" ',' "+Joining_Date+" ','"+Joining_Time+" ',' "+Discharge_Date+" ',' "+Discharge_Time+" ' )";
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
    public static int DeletePatient()
    {
        int n=0;
        int row[]=DeletePatient.jTable1.getSelectedRows();
        for(int i=0;i<row.length;i++)
        {
              String value=DeletePatient.jTable1.getModel().getValueAt(row[i], 0).toString();
              Connection con = DataBaseConnection.getConnection();
              Statement st;
              try
              {
              st = con.createStatement();
              String sql="delete from Patient where Patient_Id="+value;
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
