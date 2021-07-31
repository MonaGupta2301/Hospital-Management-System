package mgmtsystem.dataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import mgmtsystem.DBUtility.DataBaseConnection;
import mgmtsystem.PO.SearchPO;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import mgmtsystem.GUI.AddDoctor;
import mgmtsystem.GUI.Search;
import mgmtsystem.GUI.ViewAppoint;

public class SearchDAO 
{
     public static void displaydata()
    {
          DefaultTableModel model=(DefaultTableModel)Search.jTable1.getModel();
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
           DefaultTableModel model=(DefaultTableModel)Search.jTable1.getModel();
           TableRowSorter sort=new  TableRowSorter(model);
           Search.jTable1.setRowSorter(sort);
           sort.setRowFilter(RowFilter.regexFilter(getdata));
    }
 
    public static void SearchDoctorData(SearchPO ap)    
    {
             DefaultTableModel model=(DefaultTableModel) Search.jTable1.getModel();
            Connection con= DataBaseConnection.getConnection();
          try
          {
                 String Date=ap.getDate();
                 Statement st= con.createStatement();
                 //ResultSet rs= st.executeQuery("select  DoctorId,First_Name,Last_Name,Email,DOB,Age,Gender,Position,Contact,Current_Address,Permanant_Address,Joining_Date from doctor where Joining_Date="+Date);
               ResultSet rs= st.executeQuery("Select * from doctor");
                 model.setRowCount(0);
                 while(rs.next())
                 {
                     if(rs.getString("Joining_Date").equals(Date))
                     {
                           model.addRow(new Object[]{rs.getString("DoctorId"),rs.getString("First_Name"),rs.getString("Last_Name"),rs.getString("Email"),rs.getString("DOB"),rs.getString("Age"),rs.getString("Gender"),rs.getString("Position"),rs.getString("Contact"),rs.getString("Current_Address"),rs.getString("Permanant_Address"),rs.getString("Joining_Date")});
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
        int Rows[] = Search.jTable1.getSelectedRows();
        for(int i=0;i<Rows.length;i++)
        {
             int ModelRow=Search.jTable1.convertRowIndexToModel(Rows[i]);
             value=Search.jTable1.getModel().getValueAt(ModelRow, 0).toString();
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
                     String Position=rs.getString("Position");
                     String JD = rs.getString("Joining_Date");
                     String Address=rs.getString("Permanant_Address");
                                         
                     Search.jTextField2.setText(Id);
                     Search.jTextField3.setText(First_Name+" "+Last_Name);
                     Search.jTextField4.setText(Email);
                     Search.jTextField5.setText(Address);
                     Search.jTextField6.setText(JD);
                     Search.jTextField7.setText(Position);
                     
              }
              
        }
        catch(Exception e)
        {
               JOptionPane.showMessageDialog(null, e);
        }
   }
 }
}
