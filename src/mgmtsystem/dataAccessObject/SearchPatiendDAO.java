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
import mgmtsystem.GUI.SearchPatient;
import mgmtsystem.PO.SearchPatientPO;

public class SearchPatiendDAO {
public static void displaydata()
    {
          DefaultTableModel model=(DefaultTableModel)SearchPatient.jTable2.getModel();
          Connection con=DataBaseConnection.getConnection();
          try
          {
               Statement st=con.createStatement();
               ResultSet rs= st.executeQuery("Select * from patient ");
               model.setRowCount(0);
               while(rs.next())
               {
                    model.addRow(new Object[]{rs.getString("Patient_Id"),rs.getString("First_Name"),rs.getString("Last_Name"),rs.getString("Email"),rs.getString("age"),rs.getString("DOB"),rs.getString("Phone"),rs.getString("Gender"),rs.getString("Address"),rs.getString("Joining_Date"),rs.getString("Joining_Time")});
               }
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,e.getMessage());
          }
    }  
public static void SearchPatient(String getdata)
    {
           DefaultTableModel model=(DefaultTableModel)SearchPatient.jTable2.getModel();
           TableRowSorter sort=new  TableRowSorter(model);
           SearchPatient.jTable2.setRowSorter(sort);
           sort.setRowFilter(RowFilter.regexFilter(getdata));
    }
public static void SearchPatientData(SearchPatientPO ap)    
    {
             DefaultTableModel model=(DefaultTableModel) SearchPatient.jTable2.getModel();
            Connection con= DataBaseConnection.getConnection();
          try
          {
                 String Date=ap.getGetDate();
                 Statement st= con.createStatement();
                 //ResultSet rs= st.executeQuery("select  DoctorId,First_Name,Last_Name,Email,DOB,Age,Gender,Position,Contact,Current_Address,Permanant_Address,Joining_Date from doctor where Joining_Date="+Date);
               ResultSet rs= st.executeQuery("Select * from patient");
                 model.setRowCount(0);
                 while(rs.next())
                 {
                     if(rs.getString("Joining_Date").equals(Date))
                     {
                           model.addRow(new Object[]{rs.getString("Patient_Id"),rs.getString("First_Name"),rs.getString("Last_Name"),rs.getString("Email"),rs.getString("age"),rs.getString("DOB"),rs.getString("Phone"),rs.getString("Gender"),rs.getString("Address"),rs.getString("Joining_Date"),rs.getString("Joining_Time")});
                      }   
                 }
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null, e);
          }
    }
  public static void display()
      {
                                                  
        String value;
        int Rows[] = SearchPatient.jTable2.getSelectedRows();
        for(int i=0;i<Rows.length;i++)
        {
             int ModelRow=SearchPatient.jTable2.convertRowIndexToModel(Rows[i]);
             value=SearchPatient.jTable2.getModel().getValueAt(ModelRow, 0).toString();
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
                     String JT=rs.getString("Joining_Time");
                     String JD = rs.getString("Joining_Date");
                     String Address=rs.getString("Address");
                                         
                     SearchPatient.jTextField2.setText(Id);
                     SearchPatient.jTextField3.setText(First_Name+" "+Last_Name);
                     SearchPatient.jTextField4.setText(Email);
                     SearchPatient.jTextField5.setText(Address);
                     SearchPatient.jTextField6.setText(JD);
                     SearchPatient.jTextField7.setText(JT);
                     
              }
              
        }
        catch(Exception e)
        {
               JOptionPane.showMessageDialog(null, e);
        }
   }
 }
}
