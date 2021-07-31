package mgmtsystem.GUI;

import java.awt.Color;

public class ProgressBar extends javax.swing.JFrame {
    ProgressBar1 sp;
    public ProgressBar() {
        initComponents();
        sp=new ProgressBar1();
       
        sp.start();
    }
   public class ProgressBar1 extends Thread
   {
          public void run()
          {
                for(int i =1;i<=100;i++)
                {
                    try
                    {
                           if(i<50)
                           {
                          ProgressBar1.sleep(180);
                          jProgressBar1.setValue(i);
                          jProgressBar1.setStringPainted(true);
                          String j =String.valueOf(i);
                          jProgressBar1.setString(j+"%");
                    }
                          if(i>=50)
                          {
                                 ProgressBar1.sleep(100);
                                 jProgressBar1.setValue(i);
                                 jProgressBar1.setStringPainted(true);
                                 jProgressBar1.setForeground(Color.BLACK);
                                 String j =String.valueOf(i);
                                 jLabel1.setText("Loading...");
                                 jProgressBar1.setString(j+"%");
                                 
                                 
                          }
                    }
                    catch( Exception e)
                    {
                        
                    }
                }
                ProgressBar.this.dispose();
                LoginMS lg=new LoginMS();
                lg.show();
          }
         
   }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Load...");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 90, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/hosp.png"))); // NOI18N
        jLabel2.setText("Mona");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 490));

        jProgressBar1.setBackground(new java.awt.Color(255, 255, 0));
        jProgressBar1.setOpaque(true);
        getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 550, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProgressBar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProgressBar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProgressBar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProgressBar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProgressBar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
