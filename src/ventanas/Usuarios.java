package ventanas;

import clases.ConnectionPool;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

public class Usuarios extends javax.swing.JFrame {

    public Usuarios() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Interfaz de seleccion - ADMIN");
        setSize(614, 275);

        mostrarUsuarios();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUsuarios = new javax.swing.JTable();
        jTextFieldUser = new javax.swing.JTextField();
        jTextFieldPass = new javax.swing.JTextField();
        jButtonDelete = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jLabelPass = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelUser = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(570, 275));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableUsuarios);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 283, 275));
        getContentPane().add(jTextFieldUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, 90, 30));
        getContentPane().add(jTextFieldPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, 90, 30));

        jButtonDelete.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jButtonDelete.setText("Eliminar");
        jButtonDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonDelete.setMinimumSize(new java.awt.Dimension(73, 23));
        jButtonDelete.setPreferredSize(new java.awt.Dimension(73, 23));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 165, 90, 30));

        jButtonAdd.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jButtonAdd.setText("Agregar");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 165, 90, 30));

        jLabelPass.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabelPass.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPass.setText("Contraseña");
        getContentPane().add(jLabelPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, -1, -1));

        jPanel1.setBackground(new java.awt.Color(89, 145, 249));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("AGREGAR/ELIMINAR USUARIOS");

        jLabelUser.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabelUser.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUser.setText("Usuario");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(351, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(65, 65, 65))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelUser)
                        .addGap(209, 209, 209))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addGap(47, 47, 47)
                .addComponent(jLabelUser)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 280));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        // TODO add your handling code here:

        agregarUsuario();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:

        String valor1 = jTextFieldUser.getText();
        String valor2 = jTextFieldPass.getText();

        if (valor1.isEmpty() || valor2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escriba un usuario a eliminar");
        } else {
            eliminarUsuario(valor1, valor2);
        }

    }//GEN-LAST:event_jButtonDeleteActionPerformed

    public void eliminarUsuario(String usuario, String password) {

        try {
            Connection c = ConnectionPool.getInstance().getConnection();

            String sql = "DELETE FROM users WHERE Usuario = '" + usuario + "' AND Clave = '" + password + "'";

            PreparedStatement pst = c.prepareStatement(sql);
            int rs = pst.executeUpdate();

            mostrarUsuarios();

            jTextFieldUser.setText("");
            jTextFieldPass.setText("");

        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, e);
        }

    }


    private void jTableUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableUsuariosMouseClicked
        DefaultTableModel tableModel = (DefaultTableModel) jTableUsuarios.getModel();

        String usuario = tableModel.getValueAt(jTableUsuarios.getSelectedRow(), 1).toString();
        String clave = tableModel.getValueAt(jTableUsuarios.getSelectedRow(), 2).toString();

        jTextFieldUser.setText(usuario);
        jTextFieldPass.setText(clave);


    }//GEN-LAST:event_jTableUsuariosMouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuarios().setVisible(true);
            }
        });
    }

    public void mostrarUsuarios() {
        try {
            Connection c = ConnectionPool.getInstance().getConnection();

            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;
                }
            };

            jTableUsuarios.setModel(modelo);

            modelo.addColumn("ID"); //agregamos las columnas
            modelo.addColumn("Usuario");
            modelo.addColumn("Clave");

            String codigosql = "SELECT * FROM users";

            PreparedStatement pst = null;
            ResultSet rs = null;

            pst = c.prepareStatement(codigosql);

            rs = pst.executeQuery();

            ResultSetMetaData rsMD = rs.getMetaData(); // Pasar resultado de la consulta al obj rs 

            int cantidadColumnas = rsMD.getColumnCount();

            while (rs.next()) { //Recorre los datos de la consulta, proporciona los datos de 1 fila por cada ciclo

                Object[] filas = new Object[cantidadColumnas]; //Objetos que va a almacenar los registros de la bd

                for (int i = 0; i < cantidadColumnas; i++) { //Pasa los datos al objeto
                    filas[i] = rs.getObject(i + 1); //Le asignamos a cada fila (objeto) lo que recopilo el metodo rs
                }

                System.out.println(rsMD);

                modelo.addRow(filas); //Agregamos tantas filas como datos hayan sido recopilados
            }

            modelo.addTableModelListener(jTableUsuarios);

        } catch (SQLException e) {
            System.out.println("El error es: " + e);
            mostrarUsuarios();
            JOptionPane.showMessageDialog(this, "Hubo un error al procesar los datos, contacte con el administrador");
        }

    }

    public void agregarUsuario() {

        try {
            Connection c = ConnectionPool.getInstance().getConnection();

            String user = jTextFieldUser.getText().trim();
            String pass = jTextFieldPass.getText().trim();

            if (user.equals("") || pass.equals("")) {
                JOptionPane.showMessageDialog(this, "Complete los campos");
            } else {

                String sql = "INSERT INTO users (Usuario, Clave) VALUES('" + user + "','" + pass + "')";

                PreparedStatement pst = null;
                int rs = 0;

                pst = c.prepareStatement(sql);

                rs = pst.executeUpdate();

                mostrarUsuarios();

                jTextFieldUser.setText("");
                jTextFieldPass.setText("");

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelPass;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableUsuarios;
    private javax.swing.JTextField jTextFieldPass;
    private javax.swing.JTextField jTextFieldUser;
    // End of variables declaration//GEN-END:variables
}
