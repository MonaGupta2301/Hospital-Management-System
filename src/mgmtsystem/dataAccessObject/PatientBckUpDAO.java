package mgmtsystem.dataAccessObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mgmtsystem.DBUtility.DataBaseConnection;
import mgmtsystem.GUI.PatientBckUp;

public class PatientBckUpDAO {
   public static void displaydata()
    {
          DefaultTableModel model=(DefaultTableModel)PatientBckUp.jTable1.getModel();
          Connection con=DataBaseConnection.getConnection();
          try
          {
               Statement st=con.createStatement();
               ResultSet rs= st.executeQuery("Select * from delete_patient ");
               model.setRowCount(0);
               while(rs.next())
               {
                    model.addRow(new Object[]{rs.getString("Patient_Id"),rs.getString("First_Name"),rs.getString("Last_Name"),rs.getString("Email"),rs.getString("Age"),rs.getString("DOB"),rs.getString("Phone"),rs.getString("Gender"),rs.getString("Address"),rs.getString("Joining_Date"),rs.getString("Joining_Time"),rs.getString("Discharge_Date"),rs.getString("Discharge_Time")});
               }
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,e.getMessage());
          }
    }    
}
