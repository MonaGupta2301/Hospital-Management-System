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
import mgmtsystem.GUI.AddPatient;
import mgmtsystem.GUI.DoctorView;
import mgmtsystem.GUI.PatientView;

/**
 *
 * @author Hp
 */
public class PatientViewDAO {
    public static void displaydata()
    {
          DefaultTableModel model=(DefaultTableModel)PatientView.jTable1.getModel();
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
    
      public static void SearchPatient(String getdata)
    {
           DefaultTableModel model=(DefaultTableModel)PatientView.jTable1.getModel();
           TableRowSorter sort=new  TableRowSorter(model);
           PatientView.jTable1.setRowSorter(sort);
           sort.setRowFilter(RowFilter.regexFilter(getdata));
    }
      
      public static void display()
      {
                                                  
        String value;
        int Rows[] = PatientView.jTable1.getSelectedRows();
        for(int i=0;i<Rows.length;i++)
        {
             int ModelRow=PatientView.jTable1.convertRowIndexToModel(Rows[i]);
             value=PatientView.jTable1.getModel().getValueAt(ModelRow, 0).toString();
        try
        {
              String sql="Select * From patient where Patient_Id= "+value;
              Connection con=DataBaseConnection.getConnection();
              PreparedStatement pst = con.prepareStatement(sql);
              ResultSet rs=pst.executeQuery();
              while(rs.next())
              {
                     String Id = rs.getString("Patient_Id");
                     String First_Name=rs.getString("First_Name");
                     String Last_Name=rs.getString("Last_Name");
                     String Email=rs.getString("Email");
                     String Age=rs.getString("age");
                     String DOB = rs.getString("DOB");
                     String Phone=rs.getString("Phone"); 
                     String Gender =rs.getString("Gender");
                     String Address=rs.getString("Address");
                                         
                     PatientView.jTextField9.setText(Id);
                     PatientView.jTextField1.setText(First_Name);
                     PatientView.jTextField2.setText(Last_Name);
                     PatientView.jTextField6.setText(DOB);
                     PatientView.jTextField7.setText(Gender);
                     PatientView.jTextField4.setText(Age);
                     PatientView.jTextField3.setText(Email);
                     PatientView.jTextArea1.setText(Address);
              }
              
        }
        catch(Exception e)
        {
               JOptionPane.showMessageDialog(null, e);
        }
   }
 }
}


