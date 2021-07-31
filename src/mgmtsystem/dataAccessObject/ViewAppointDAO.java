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
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import mgmtsystem.DBUtility.DataBaseConnection;
import mgmtsystem.GUI.AddAppointment;
import mgmtsystem.GUI.PatientView;
import mgmtsystem.GUI.ViewAppoint;

/**
 *
 * @author Hp
 */
public class ViewAppointDAO {
     public static void displaydata()
    {
          DefaultTableModel model=(DefaultTableModel)ViewAppoint.jTable1.getModel();
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
      
      public static void SearchAppoint(String getdata)
    {
           DefaultTableModel model=(DefaultTableModel)ViewAppoint.jTable1.getModel();
           TableRowSorter sort=new  TableRowSorter(model);
           ViewAppoint.jTable1.setRowSorter(sort);
           sort.setRowFilter(RowFilter.regexFilter(getdata));
    }
      
      public static void display()
      {
                                                  
        String value;
        int Rows[] = ViewAppoint.jTable1.getSelectedRows();
        for(int i=0;i<Rows.length;i++)
        {
             int ModelRow=ViewAppoint.jTable1.convertRowIndexToModel(Rows[i]);
             value=ViewAppoint.jTable1.getModel().getValueAt(ModelRow, 0).toString();
        try
        {
              String sql="Select * From appointment where Patient_No= "+value;
              Connection con=DataBaseConnection.getConnection();
              PreparedStatement pst = con.prepareStatement(sql);
              ResultSet rs=pst.executeQuery();
              while(rs.next())
              {
                     String Id = rs.getString("Patient_No");
                     String First_Name=rs.getString("First_Name");
                     String Last_Name=rs.getString("Last_Name");
                     String Age=rs.getString("Age");
                     String Phone=rs.getString("Phone");
                     String AD = rs.getString("Appointment_Date");
                     String AT = rs.getString("Appointment_Time");
                     String Gender =rs.getString("Gender");
                     String Address=rs.getString("Address");
                                         
                     ViewAppoint.jTextField9.setText(Id);
                     ViewAppoint.jTextField1.setText(First_Name);
                     ViewAppoint.jTextField2.setText(Last_Name);
                     ViewAppoint.jTextField7.setText(Gender);
                     ViewAppoint.jTextField4.setText(Age);
                     ViewAppoint.jTextField3.setText(Phone);
                     ViewAppoint.jTextField6.setText(AD);
                     ViewAppoint.jTextField5.setText(AT);
                     ViewAppoint.jTextArea1.setText(Address);
              }
              
        }
        catch(Exception e)
        {
               JOptionPane.showMessageDialog(null, e);
        }
   }
 }
}
