
package ventanas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PrincipalInterface extends javax.swing.JFrame {
    
    FondoPanel fondo = new FondoPanel();
    
    public PrincipalInterface() {
 
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(Color.WHITE);

    }

    @SuppressWarnings("unchecked")
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new FondoPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        buttonUsers = new javax.swing.JButton();
        subtitle = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buttonReportes1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(430, 430));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(219, 235, 243));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("INSTITUTO SAN LEONARDO MURIALDO");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/LOGOMURIALDO.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 500));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonUsers.setBackground(new java.awt.Color(0, 0, 0));
        buttonUsers.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        buttonUsers.setForeground(new java.awt.Color(255, 255, 255));
        buttonUsers.setText("REPORTES");
        buttonUsers.setBorder(null);
        buttonUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUsersActionPerformed(evt);
            }
        });
        jPanel2.add(buttonUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, 210, 50));

        subtitle.setBackground(new java.awt.Color(255, 255, 255));
        subtitle.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        subtitle.setText("INTERFAZ ADMINISTRADOR");
        jPanel2.add(subtitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 470, -1, -1));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel2.setText("SISTEMA DE REPORTES");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        buttonReportes1.setBackground(new java.awt.Color(0, 0, 0));
        buttonReportes1.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        buttonReportes1.setForeground(new java.awt.Color(255, 255, 255));
        buttonReportes1.setText("USUARIOS");
        buttonReportes1.setBorder(null);
        buttonReportes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReportes1ActionPerformed(evt);
            }
        });
        jPanel2.add(buttonReportes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 210, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 0, 380, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUsersActionPerformed

        Reportes ventanaInfoA = new Reportes();
        ventanaInfoA.setVisible(true);
    }//GEN-LAST:event_buttonUsersActionPerformed

    private void buttonReportes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReportes1ActionPerformed
   
        Usuarios users = new Usuarios();
        users.setVisible(true);
    }//GEN-LAST:event_buttonReportes1ActionPerformed

    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalInterface().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonReportes1;
    private javax.swing.JButton buttonUsers;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel subtitle;
    // End of variables declaration//GEN-END:variables
}

class FondoPanel extends JPanel{

    private Image imagen;
    private String url;

    @Override
    public void paint(Graphics g){
        url = "/imagenes/PrincipalImagev2.png";
        imagen = new ImageIcon(getClass().getResource(url)).getImage();
        g.drawImage(imagen, 0, 0,getWidth(),getHeight(), this);
        
        setOpaque(false);
        
        super.paint(g);
    }
    
   
    
}