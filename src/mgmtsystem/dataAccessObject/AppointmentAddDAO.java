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
import mgmtsystem.GUI.AddAppointment;
import mgmtsystem.GUI.AddDoctor;
import mgmtsystem.GUI.UpdateAppoint;
import mgmtsystem.PO.AppointmentAdd;

public class AppointmentAddDAO {

    public static void displaydata()
    {
          DefaultTableModel model=(DefaultTableModel)AddAppointment.jTable1.getModel();
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
     public static int InsertAppointment(AppointmentAdd ad) 
     { 
         int i = 0;
         try
         {
         Connection con=DataBaseConnection.getConnection();
         DateFormat fd=new SimpleDateFormat();
         Date d= new Date();
         PreparedStatement pst= con.prepareStatement("insert into appointment(First_Name,Last_Name,Age,Phone,Appointment_Date,Appointment_Time,Gender,Address) values(?,?,?,?,?,?,?,?)");
         pst.setString(1,ad.getFirst_Name());
         pst.setString(2, ad.getLast_Name());
         pst.setString(3,String.valueOf(ad.getAge()));
         pst.setString(4, String.valueOf(ad.getPhone()));
         pst.setString(5,ad.getAppointment_Date());
         pst.setString(6, ad.getAppointment_Time());
         pst.setString(7, ad.getGender());
         pst.setString(8,ad.getAddress());
          i =pst.executeUpdate();
     
         }
         catch(Exception e)
         {
               JOptionPane.showMessageDialog(null, e.getMessage());
         }
             return i;
     }
          
}
