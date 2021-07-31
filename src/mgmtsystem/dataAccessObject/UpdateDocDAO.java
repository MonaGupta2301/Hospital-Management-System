package mgmtsystem.dataAccessObject;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mgmtsystem.DBUtility.DataBaseConnection;
import mgmtsystem.GUI.AddDoctor;
import mgmtsystem.GUI.UpdateDoctor;
import mgmtsystem.PO.DoctorAdd;
import mgmtsystem.PO.UpdateDoc;

public class UpdateDocDAO {
    public static void displaydata()
    {
          DoctorAdd da=new DoctorAdd();
          Connection con=DataBaseConnection.getConnection();
          try
          {
               Statement st=con.createStatement();
               UpdateDoc up=new UpdateDoc();
                int s= Integer.parseInt(UpdateDoctor.jTextField7.getText());
               DefaultTableModel model=(DefaultTableModel)UpdateDoctor.jTable1.getModel();
               int row=UpdateDoctor.jTable1.getSelectedRow();
               ResultSet rs= st.executeQuery("Select First_Name,Last_Name,Email,DOB,Age,Gender,Position,Contact,Current_Address,Permanant_Address from doctor where DoctorId="+s);
               
               while(rs.next())
               {
                     //UpdateDoctor.jTextField1.setText(rs.getString("DoctorId"));
                    UpdateDoctor.jTextField1.setText(rs.getString("First_Name"));
                    UpdateDoctor.jTextField2.setText(rs.getString("Last_Name"));
                    UpdateDoctor.jTextField5.setText(rs.getString("Email"));
                    java.util.Date date1=new SimpleDateFormat("d MMM, yyyy").parse(rs.getString("DOB"));
                    UpdateDoctor.jDateChooser1.setDate(date1);
                    UpdateDoctor.jTextField4.setText(rs.getString("Age"));
                    if(rs.getString("Gender").equals("Female"))
                    {
                        UpdateDoctor.jComboBox1.setSelectedIndex(1);
                    }
                    else if(rs.getString("Gender").equals("Male"))
                    {
                        UpdateDoctor.jComboBox1.setSelectedIndex(0);
                    }
                    else
                    {
                        UpdateDoctor.jComboBox1.setSelectedIndex(2);
                    }
                    UpdateDoctor.jTextField6.setText(rs.getString("Position"));
                    UpdateDoctor.jTextField3.setText(rs.getString("Contact"));
                    UpdateDoctor.jTextArea1.setText(rs.getString("Current_Address"));
                    UpdateDoctor.jTextArea2.setText(rs.getString("Permanant_Address"));
               }
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,e.getMessage());
          }
    }
    
    public static void displaydataOnTable()
    {
          DefaultTableModel model=(DefaultTableModel)UpdateDoctor.jTable1.getModel();
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
    public static int UdateDoctor(UpdateDoc ud)
     {  int i=0; 
         try
         {
               java.util.Date date=UpdateDoctor.jDateChooser1.getDate();
               String setDate=DateFormat.getDateInstance().format(date);
               
               Connection con=DataBaseConnection.getConnection();
               PreparedStatement pst= con.prepareStatement("update doctor set First_Name=?,Last_Name=?,Email=?,DOB=?,Age=?,Gender=?,Position=?,Contact=?,Current_Address=?,Permanant_Address=? where DoctorId="+ud.getId());
               DoctorAdd da=new DoctorAdd();
               pst.setString(1,UpdateDoctor.jTextField1.getText());
               pst.setString(2,UpdateDoctor.jTextField2.getText());
               pst.setString(3,UpdateDoctor.jTextField5.getText());
               pst.setString(4,setDate);
               pst.setString(5,UpdateDoctor.jTextField4.getText());
               pst.setString(6,UpdateDoctor.jComboBox1.getItemAt(UpdateDoctor.jComboBox1.getSelectedIndex()));
               pst.setString(7,UpdateDoctor.jTextField6.getText());
               pst.setString(8,UpdateDoctor.jTextField3.getText());
               pst.setString(9,UpdateDoctor.jTextArea1.getText());
               pst.setString(10,UpdateDoctor.jTextArea2.getText());
               i=pst.executeUpdate();
         }
          catch(Exception e)
          {
               JOptionPane.showMessageDialog(null, e.getMessage());
          }
              return i;
     }
}
