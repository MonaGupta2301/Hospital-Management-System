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
import mgmtsystem.GUI.AddAppointment;
import mgmtsystem.GUI.DeleteAppointment;
import mgmtsystem.GUI.DeletePatient;

public class AppointmentDeleteDAO {
        public static void displaydata()
    {
          DefaultTableModel model=(DefaultTableModel)DeleteAppointment.jTable1.getModel();
          Connection con=DataBaseConnection.getConnection();
          try
          {
               Statement st=con.createStatement();
               ResultSet rs= st.executeQuery("Select * from appointment ");
               model.setRowCount(0);
               while(rs.next())
               {
                    model.addRow(new Object[]{rs.getString("Patient_No"),rs.getString("First_Name"),rs.getString("Last_Name"),rs.getString("Age"),rs.getString("Phone"),rs.getString("Appointment_Date"),rs.getString("Appointment_Time"),rs.getString("Gender"),rs.getString("Address")});
               }
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,e.getMessage());
          }
    }

 public static int DeleteAppintment()
    {
        int n=0;
        int row[]=DeleteAppointment.jTable1.getSelectedRows();
        for(int i=0;i<row.length;i++)
        {
              String value=DeleteAppointment.jTable1.getModel().getValueAt(row[i], 0).toString();
              Connection con = DataBaseConnection.getConnection();
              Statement st;
              try
              {
              st = con.createStatement();
              String sql="delete from appointment where Patient_No="+value;
               n=st.executeUpdate(sql);   
              }
              catch(Exception e)
              {
                  JOptionPane.showMessageDialog(null, e.getMessage());
              }
        }
        
        return n;
    }    
      public static int AppointBackup()
    {
        int j=0;
        int row[]=DeleteAppointment.jTable1.getSelectedRows();
        for(int i=0;i<row.length;i++)
        {
              String value=DeleteAppointment.jTable1.getModel().getValueAt(row[i], 0).toString();
              Connection con = DataBaseConnection.getConnection();
              try
              {
                         Statement st=con.createStatement();
                         ResultSet rs=st.executeQuery("select * from appointment where Patient_No="+value);
                         if(rs.next())
                         {
                                String Patient_No=rs.getString("Patient_No");
                                String First_Name=rs.getString("First_Name");
                                String Last_Name=rs.getString("Last_Name");
                                String Age=rs.getString("Age");
                                String Phone=rs.getString("Phone");
                                String Appointment_Date=rs.getString("Appointment_Date");
                                String Appointment_Time=rs.getString("Appointment_Time");
                                String Gender=rs.getString("Gender");                               
                                String Address=rs.getString("Address");
                                
                                DateFormat df=new SimpleDateFormat("dd/MM/yy");
                                Date d= new Date();
                                String Appointment_Delete=df.format(d);
                                String sql="insert into delete_Appointment values("+Patient_No+",' "+First_Name+" ',' "+Last_Name+" ',' "+Age+" ', "+Phone+ " ,' "+Appointment_Date+" ','"+Appointment_Time+" ',' "+Gender+" ',' "+Address+" ',' "+Appointment_Delete+" ' )";
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
}
