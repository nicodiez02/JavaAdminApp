/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clases.Conexion;
import clases.ConnectionPool;
import java.awt.Toolkit;
import java.sql.DriverManager;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author Nico
 */
public class interfazInfoA extends javax.swing.JFrame {

    public static String user_update = "";

    public interfazInfoA() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos Informatica A");
        Mostrar("", "");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/administracion.png")));

    }

    public void Eliminar() {
        int fila = jTableInfoA.getSelectedRow();
        String value = jTableInfoA.getValueAt(fila, 0).toString();

        if (value.equals("")) {
            JOptionPane.showMessageDialog(this, "Seleccione un campo");
        } else {
              ConnectionPool metodospool = new ConnectionPool();
                java.sql.Connection con = null;
            try {
                con = metodospool.dataSource.getConnection();
                PreparedStatement delete = con.prepareStatement("DELETE FROM InformaticaA WHERE id = '" + value + "'");

                delete.executeUpdate();

                Mostrar("", "");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Hubo un error al procesar la operacion, contacte con el administrador");
                System.out.println("El error es: " + e);
            }
        }
    }

    public void Mostrar(String filtro, String parametro) {
        ConnectionPool metodospool = new ConnectionPool();
        java.sql.Connection con = null;
        
        try {

            String codigosql;
            DefaultTableModel modelo = new DefaultTableModel();
            con = metodospool.dataSource.getConnection();
            jTableInfoA.setModel(modelo);

            PreparedStatement pst = null;
            ResultSet rs = null;

            switch (parametro) {
                case "R":
                    codigosql = "SELECT ID, Nombre, Apellido, Reporte, PC, Reporte2, Año, Laboratorio FROM InformaticaA WHERE Reporte2 LIKE '%" + filtro + "%'";
                    break;
                case "ID":
                    codigosql = "SELECT ID, Nombre, Apellido, Reporte, PC, Reporte2, Año, Laboratorio FROM InformaticaA WHERE ID LIKE '%" + filtro + "%'";
                    break;
                case "N":
                    codigosql = "SELECT ID, Nombre, Apellido, Reporte, PC, Reporte2, Año, Laboratorio FROM InformaticaA WHERE Nombre LIKE '%" + filtro + "%'";
                    break;
                case "A":
                    codigosql = "SELECT ID, Nombre, Apellido, Reporte, PC, Reporte2, Año, Laboratorio FROM InformaticaA WHERE Apellido LIKE '%" + filtro + "%'";
                    break;
                case "P":
                    codigosql = "SELECT ID, Nombre, Apellido, Reporte, PC, Reporte2, Año, Laboratorio FROM InformaticaA WHERE PC LIKE '%" + filtro + "%'";
                    break;
                case "L":
                    codigosql = "SELECT ID, Nombre, Apellido, Reporte, PC, Reporte2, Año, Laboratorio FROM InformaticaA WHERE Laboratorio LIKE '%" + filtro + "%'";
                    break;
                default:
                    codigosql = "SELECT ID, Nombre, Apellido, Reporte, PC, Reporte2, Año, Laboratorio FROM InformaticaA";
                    break;
            }

            pst = con.prepareStatement(codigosql);

            rs = pst.executeQuery();

            ResultSetMetaData rsMD = rs.getMetaData(); // Pasar resultado de la consulta al obj rs 

            int cantidadColumnas = rsMD.getColumnCount();

            modelo.addColumn("ID"); //agregamos las columnas
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Reporte");
            modelo.addColumn("PC");
            modelo.addColumn("Reporte2");
            modelo.addColumn("Año");
            modelo.addColumn("Laboratorio");
             modelo.addColumn("Comentario");

            while (rs.next()) { //Recorre los datos de la consulta, proporciona los datos de 1 fila por cada ciclo

                Object[] filas = new Object[cantidadColumnas]; //Objetos que va a almacenar los registros de la bd

                for (int i = 0; i < cantidadColumnas; i++) { //Pasa los datos al objeto
                    filas[i] = rs.getObject(i + 1); //Le asignamos a cada fila (objeto) lo que recopilo el metodo rs
                }

                modelo.addRow(filas); //Agregamos tantas filas como datos hayan sido recopilados
            }

        } catch (SQLException e) {
            System.out.println("El error es: " + e);
            JOptionPane.showMessageDialog(this, "Hubo un error al procesar los datos, contacte con el administrador");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBoxFiltrar = new javax.swing.JComboBox<>();
        jTextFieldFiltrar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableInfoA = new javax.swing.JTable();
        jButtonDelete = new javax.swing.JButton();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBoxFiltrar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione opcion de filtrado", "Reporte", "ID", "Nombre", "Apellido", "PC", "Laboratorio" }));

        jTextFieldFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFiltrarActionPerformed(evt);
            }
        });
        jTextFieldFiltrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldFiltrarKeyReleased(evt);
            }
        });

        jTableInfoA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Apellido", "Reporte", "PC", "Reporte2", "Año", "Laboratorio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableInfoA);

        jButtonDelete.setText("Eliminar Registro");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jComboBoxFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jButtonDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDelete))
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldFiltrarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFiltrarKeyReleased
        int Eleccion = jComboBoxFiltrar.getSelectedIndex();
        String parametro = "";

        if (Eleccion == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una opcion de filtrado");
        } else if (Eleccion == 1) {
            parametro = "R";
            Mostrar(jTextFieldFiltrar.getText(), parametro);
        } else if (Eleccion == 2) {
            parametro = "ID";
            Mostrar(jTextFieldFiltrar.getText(), parametro);
        } else if (Eleccion == 3) {
            parametro = "N";
            Mostrar(jTextFieldFiltrar.getText(), parametro);
        } else if (Eleccion == 4) {
            parametro = "A";
            Mostrar(jTextFieldFiltrar.getText(), parametro);
        }else if (Eleccion == 5) {
            parametro = "P";
            Mostrar(jTextFieldFiltrar.getText(), parametro);
        }else if (Eleccion == 6) {
            parametro = "L";
            Mostrar(jTextFieldFiltrar.getText(), parametro);
        }

    }//GEN-LAST:event_jTextFieldFiltrarKeyReleased

    private void jTextFieldFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFiltrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFiltrarActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        Eliminar();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(interfazInfoA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(interfazInfoA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(interfazInfoA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(interfazInfoA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new interfazInfoA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBoxFiltrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableInfoA;
    private javax.swing.JTextField jTextFieldFiltrar;
    // End of variables declaration//GEN-END:variables
}
