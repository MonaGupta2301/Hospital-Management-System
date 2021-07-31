package mgmtsystem.dataAccessObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mgmtsystem.DBUtility.DataBaseConnection;
import mgmtsystem.GUI.DoctorBckUp;

public class DoctorBckUpDAO {
    
    public static void displaydata()
    {
          DefaultTableModel model=(DefaultTableModel)DoctorBckUp.jTable1.getModel();
          Connection con=DataBaseConnection.getConnection();
          try
          {
               Statement st=con.createStatement();
               ResultSet rs= st.executeQuery("Select * from delete_doctor ");
               model.setRowCount(0);
               while(rs.next())
               {
                    model.addRow(new Object[]{rs.getString("Doctor_Id"),rs.getString("First_Name"),rs.getString("Last_Name"),rs.getString("Email"),rs.getString("DOB"),rs.getString("Age"),rs.getString("Gender"),rs.getString("Position"),rs.getString("Contact"),rs.getString("Current_Address"),rs.getString("Permanant_Address"),rs.getString("Joining_Date"),rs.getString("Leaving_Date")});
               }
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,e.getMessage());
          }
    }
}
