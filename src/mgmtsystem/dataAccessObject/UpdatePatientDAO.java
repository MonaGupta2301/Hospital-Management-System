
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
import mgmtsystem.GUI.UpdateDoctor;
import mgmtsystem.GUI.UpdatePatient;
import mgmtsystem.PO.DoctorAdd;
import mgmtsystem.PO.UpdateDoc;
import mgmtsystem.PO.UpdatePatientPO;

/**
 *
 * @author Hp
 */
public class UpdatePatientDAO {
     public static void displaydata()
    {
          DoctorAdd da=new DoctorAdd();
          Connection con=DataBaseConnection.getConnection();
          DefaultTableModel model=(DefaultTableModel)UpdatePatient.jTable1.getModel();
          int row=UpdatePatient.jTable1.getSelectedRow();
          try
          {
               Statement st=con.createStatement();
               
               String s=UpdatePatient.jTable1.getValueAt(UpdatePatient.jTable1.getSelectedRow(), 0).toString();
               int s1= Integer.parseInt(s);
               ResultSet rs= st.executeQuery("Select Patient_Id ,First_Name,Last_Name,Email,Age,DOB,Gender,Phone,Gender,Address from patient where Patient_Id="+s1);
               
               while(rs.next())
               {
                    UpdatePatient.jTextField6.setText(rs.getString("Patient_Id"));
                    UpdatePatient.jTextField1.setText(rs.getString("First_Name"));
                    UpdatePatient.jTextField2.setText(rs.getString("Last_Name"));
                    UpdatePatient.jTextField5.setText(rs.getString("Email"));
                 /*   Date date =new SimpleDateFormat("d , MMM yyyy").parse((String)model.getValueAt(row,5).toString());
                    UpdatePatient.jDateChooser1.setDate(date);*/
                    UpdatePatient.jTextField4.setText(rs.getString("Age"));
                    if(rs.getString("Gender").equals("Female"))
                    {
                        UpdatePatient.jComboBox1.setSelectedIndex(1);
                    }
                    else if(rs.getString("Gender").equals("Male"))
                    {
                        UpdatePatient.jComboBox1.setSelectedIndex(0);
                    }
                    else
                    {
                        UpdatePatient.jComboBox1.setSelectedIndex(2);
                    }
                    UpdatePatient.jTextField3.setText(rs.getString("Phone"));
                    UpdatePatient.jTextArea1.setText(rs.getString("Address"));
                    
               }
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,e.getMessage());
          }
    }
     public static void displaydataOnTable()
    {
          DefaultTableModel model=(DefaultTableModel)UpdatePatient.jTable1.getModel();
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
    public static int UdatePatient(UpdatePatientPO up)
     {  int i=0; 
         try
         {
               java.util.Date date=UpdatePatient.jDateChooser1.getDate();
               String setDate=DateFormat.getDateInstance().format(date);
               
               Connection con=DataBaseConnection.getConnection();
               int row=UpdatePatient.jTable1.getSelectedRow();
               String s=UpdatePatient.jTable1.getModel().getValueAt(row, 0).toString();
               PreparedStatement pst= con.prepareStatement("update patient set First_Name=?,Last_Name=?,Email=?,age=?,DOB=?,Phone=?,Gender=?,Address=? where Patient_Id="+Integer.parseInt(s));
       
               pst.setString(1,UpdatePatient.jTextField1.getText());
               pst.setString(2,UpdatePatient.jTextField2.getText());
               pst.setString(3,UpdatePatient.jTextField5.getText());
               pst.setString(4,UpdatePatient.jTextField4.getText());
               pst.setString(5,setDate);
               pst.setString(6,UpdatePatient.jTextField3.getText());
               pst.setString(7,UpdatePatient.jComboBox1.getItemAt(UpdateDoctor.jComboBox1.getSelectedIndex()));
               pst.setString(8,UpdatePatient.jTextArea1.getText());
               i=pst.executeUpdate();
         }
          catch(Exception e)
          {
               JOptionPane.showMessageDialog(null, e);
          }
              return i;
     }     

}
