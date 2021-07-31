/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgmtsystem.dataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mgmtsystem.DBUtility.DataBaseConnection;
import mgmtsystem.GUI.AddAppointment;
import mgmtsystem.GUI.UpdateAppoint;
import mgmtsystem.GUI.UpdateDoctor;
import mgmtsystem.PO.UpdateAppointPO;
import mgmtsystem.PO.UpdateDoc;

/**
 *
 * @author Hp
 */
public class UpdateAppointDAO {
    public static void displaydataOnTable()
    {
          DefaultTableModel model=(DefaultTableModel)UpdateAppoint.jTable1.getModel();
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
     public static void displaydata()
    {
          Connection con=DataBaseConnection.getConnection();
          DefaultTableModel model=(DefaultTableModel)UpdateAppoint.jTable1.getModel();
          int row=UpdateAppoint.jTable1.getSelectedRow();
          try
          {
               Statement st=con.createStatement();
               UpdateAppointPO up=new UpdateAppointPO();
               String s=UpdateAppoint.jTextField6.getText();
               ResultSet rs= st.executeQuery("Select Patient_No,First_Name,Last_Name,Age,Phone,Appointment_Date,Appointment_Time,Gender,Address from appointment where Patient_No="+UpdateAppoint.jTable1.getValueAt(row, 0).toString());
               
               while(rs.next())
               {
                    UpdateAppoint.jTextField6.setText(rs.getString("Patient_No"));
                    UpdateAppoint.jTextField1.setText(rs.getString("First_Name"));
                    UpdateAppoint.jTextField2.setText(rs.getString("Last_Name"));
                    UpdateAppoint.jTextField4.setText(rs.getString("Age"));
                    UpdateAppoint.jTextField3.setText(rs.getString("Phone")); 
                    Date date =new SimpleDateFormat("d MMM, yyyy").parse((String)model.getValueAt(row,5).toString());
                    UpdateAppoint.jDateChooser1.setDate(date);
                    UpdateAppoint.jTextField5.setText(rs.getString("Appointment_Time"));
                    if(rs.getString("Gender").equals("Female"))
                    {
                        UpdateAppoint.jComboBox1.setSelectedIndex(1);
                    }
                    else if(rs.getString("Gender").equals("Male"))
                    {
                        UpdateAppoint.jComboBox1.setSelectedIndex(0);
                    }
                    else
                    {
                        UpdateAppoint.jComboBox1.setSelectedIndex(2);
                    }
                    
                    UpdateAppoint.jTextArea1.setText(rs.getString("Address"));
                    
               }
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,e.getMessage());
          }
    }
public static int UdateAppointment(UpdateAppointPO up)
     {  int i=0; 
         try
         {
               java.util.Date date=UpdateAppoint.jDateChooser1.getDate();
               String setDate=DateFormat.getDateInstance().format(date);
               
               Connection con=DataBaseConnection.getConnection();
               PreparedStatement pst= con.prepareStatement("update appointment set First_Name=?,Last_Name=?,Age=?,Phone=?,Appointment_Date=?,Appointment_Time=?,Gender=?,Address=? where Patient_No="+Integer.parseInt(up.getId()));
                pst.setString(1,UpdateAppoint.jTextField1.getText());
                pst.setString(2,UpdateAppoint.jTextField2.getText());
                pst.setString(3,UpdateAppoint.jTextField4.getText());
                pst.setString(4,UpdateAppoint.jTextField3.getText());
                 pst.setString(5,setDate);
                 pst.setString(6,UpdateAppoint.jTextField5.getText());
                 pst.setString(7,UpdateAppoint.jComboBox1.getItemAt(UpdateAppoint.jComboBox1.getSelectedIndex()));
                 pst.setString(8, UpdateAppoint.jTextArea1.getText());
                 i=pst.executeUpdate();
         }
         catch(Exception e)
         {
               JOptionPane.showMessageDialog(null, e);
         }
         return i;
     }
}
