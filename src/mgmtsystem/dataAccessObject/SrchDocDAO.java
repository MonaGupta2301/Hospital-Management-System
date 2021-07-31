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
import mgmtsystem.GUI.AddDoctor;
import mgmtsystem.GUI.DoctorView;

public class SrchDocDAO {
     public static void displaydata()
    {
          DefaultTableModel model=(DefaultTableModel)DoctorView.jTable1.getModel();
          Connection con=DataBaseConnection.getConnection();
          try
          {
               Statement st=con.createStatement();
               ResultSet rs= st.executeQuery("Select * from doctor ");
               model.setRowCount(0);
               while(rs.next())
               {
                    model.addRow(new Object[]{rs.getString("DoctorId"),rs.getString("First_Name"),rs.getString("Last_Name"),rs.getString("Email"),rs.getString("DOB"),rs.getString("Age"),rs.getString("Gender"),rs.getString("Position"),rs.getString("Contact"),rs.getString("Current_Address"),rs.getString("Permanant_Address"),rs.getString("Joining_Date")});
               }
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,e.getMessage());
          }
    }
      public static void SearchDoctor(String getdata)
    {
           DefaultTableModel model=(DefaultTableModel)DoctorView.jTable1.getModel();
           TableRowSorter sort=new  TableRowSorter(model);
           DoctorView.jTable1.setRowSorter(sort);
           sort.setRowFilter(RowFilter.regexFilter(getdata));
    }
      public static void display()
      {
                                                  
        String value;
        int Rows[] = DoctorView.jTable1.getSelectedRows();
        for(int i=0;i<Rows.length;i++)
        {
             int ModelRow=DoctorView.jTable1.convertRowIndexToModel(Rows[i]);
             value=DoctorView.jTable1.getModel().getValueAt(ModelRow, 0).toString();
        try
        {
              String sql="Select * From doctor where DoctorId= "+value;
              Connection con=DataBaseConnection.getConnection();
              PreparedStatement pst = con.prepareStatement(sql);
              ResultSet rs=pst.executeQuery();
              while(rs.next())
              {
                     String Id = rs.getString("DoctorId");
                     String First_Name=rs.getString("First_Name");
                     String Last_Name=rs.getString("Last_Name");
                     String Email=rs.getString("Email");
                     String DOB = rs.getString("DOB");
                     String Age=rs.getString("Age");
                     String Gender =rs.getString("Gender");
                     String Position =rs.getString("Position");
                     String Contact=rs.getString("Contact");
                     String Current_Address=rs.getString("Current_Address");
                     String Permanant_Address=rs.getString("Permanant_Address");
                                         
                     DoctorView.jTextField7.setText(Id);
                     DoctorView.jTextField1.setText(First_Name);
                     DoctorView.jTextField2.setText(Last_Name);
                     DoctorView.jTextField8.setText(DOB);
                     DoctorView.jTextField9.setText(Gender);
                     DoctorView.jTextField4.setText(Age);
                     DoctorView.jTextField5.setText(Email);
                     DoctorView.jTextField6.setText(Position);
                     DoctorView.jTextField3.setText(Contact);
                     DoctorView.jTextArea1.setText(Current_Address);
                     DoctorView.jTextArea2.setText(Permanant_Address);
              }
              
        }
        catch(Exception e)
        {
               //JOptionPane.showMessageDialog(this, e.getMessage());
        }
        }

      }
}
