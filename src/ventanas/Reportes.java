package ventanas;

import clases.Colores;
import clases.Conexion;
import clases.ConnectionPool;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Nico
 */
public class Reportes extends javax.swing.JFrame {

    public static String user_update = "";
    Colores render = new Colores();

    public Reportes() {
        initComponents();

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos Informatica A");

        jDateChooser.setLocale(new Locale("es"));
        jDateChooser.setDateFormatString("yyyy-MM-dd");

        jDateChooser1.setLocale(new Locale("es"));
        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        Mostrar(0, "", "", "", "", "", "", null, 0, 0);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/administracion.png")));

        jTextFieldLab.setEnabled(false);
        jTextFieldCurso.setEnabled(false);
        jTextFieldAlumno.setEnabled(false);
        jTextFieldAlumno2.setEnabled(false);
        jComboBoxProblemas.setEnabled(false);
        jTextFieldPC.setEnabled(false);
        jDateChooser.setEnabled(false);
        jTextFieldFastNombre.setEnabled(false);
        jTextFieldFastApellido.setEnabled(false);
        jDateChooser1.setEnabled(false);

        jLabelLab.setForeground(new Color(196, 204, 204));
        jLabelCurso.setForeground(new Color(196, 204, 204));
        jLabelNombre.setForeground(new Color(196, 204, 204));
        jLabelApellido.setForeground(new Color(196, 204, 204));
        jLabelPC.setForeground(new Color(196, 204, 204));
        jLabelProblema.setForeground(new Color(196, 204, 204));
        jLabelFecha.setForeground(new Color(196, 204, 204));

        jLabelFastName.setForeground(new Color(196, 204, 204));
        jLabelFastSurname.setForeground(new Color(196, 204, 204));

        jTable.setDefaultRenderer(jTable.getColumnClass(0), render);

        TableColumn columna = jTable.getColumn("Fecha");
        columna.setPreferredWidth(140);

        TableColumn columnaID = jTable.getColumn("ID");
        columnaID.setMaxWidth(30);
        columnaID.setMinWidth(30);

        TableColumn columnaLab = jTable.getColumn("Lab");
        columnaLab.setMaxWidth(50);
        columnaLab.setMinWidth(50);

        TableColumn columnaPC = jTable.getColumn("PC");
        columnaPC.setMaxWidth(30);
        columnaPC.setMinWidth(30);

        TableColumn columnaExtra = jTable.getColumn("CategoriaExtra");
        columnaExtra.setPreferredWidth(5);

        TableColumn columnaCategoria = jTable.getColumn("Categoria");
        columnaCategoria.setPreferredWidth(5);

        TableColumn columnaComentario = jTable.getColumn("Comentario");
        columnaComentario.setPreferredWidth(5);

    }

    public void Eliminar() {
        int fila = jTable.getSelectedRow();
        String value = jTable.getValueAt(fila, 0).toString();

        if (value.equals("")) {
            JOptionPane.showMessageDialog(this, "Seleccione un campo");
        } else {
            ConnectionPool metodospool = new ConnectionPool();
            java.sql.Connection con = null;
            try {
                con = metodospool.dataSource.getConnection();
                PreparedStatement delete = con.prepareStatement("DELETE FROM reportes WHERE id = '" + value + "'");

                delete.executeUpdate();

                Mostrar(0, "", "", "", "", "", "", null, 0, 0);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Hubo un error al procesar la operacion, contacte con el administrador");
                System.out.println("El error es: " + e);
            }
        }
    }

    public void enable() {

        if (jCheckBoxAvanzado.isSelected()) {

            jTextFieldLab.setEnabled(true);
            jTextFieldPC.setEnabled(true);
            jTextFieldAlumno.setEnabled(true);
            jTextFieldAlumno2.setEnabled(true);
            jTextFieldPC.setEnabled(true);
            jTextFieldCurso.setEnabled(true);
            jComboBoxProblemas.setEnabled(true);
            jDateChooser.setEnabled(true);
            jComboBoxFast.setEnabled(false);
            jTextFieldFast.setEnabled(false);

            jLabelLab.setForeground(Color.BLACK);
            jLabelCurso.setForeground(Color.BLACK);
            jLabelNombre.setForeground(Color.BLACK);
            jLabelApellido.setForeground(Color.BLACK);
            jLabelPC.setForeground(Color.BLACK);
            jLabelProblema.setForeground(Color.BLACK);
            jLabelFecha.setForeground(Color.BLACK);
            jLabelFecha.setForeground(Color.BLACK);

        } else {

            jTextFieldLab.setEnabled(false);
            jTextFieldPC.setEnabled(false);
            jTextFieldAlumno.setEnabled(false);
            jTextFieldAlumno2.setEnabled(false);
            jTextFieldPC.setEnabled(false);
            jTextFieldCurso.setEnabled(false);
            jComboBoxProblemas.setEnabled(false);
            jComboBoxFast.setEnabled(true);
            jTextFieldFast.setEnabled(true);
            jDateChooser.setEnabled(false);

            jLabelLab.setForeground(new Color(196, 204, 204));
            jLabelCurso.setForeground(new Color(196, 204, 204));
            jLabelNombre.setForeground(new Color(196, 204, 204));
            jLabelApellido.setForeground(new Color(196, 204, 204));
            jLabelPC.setForeground(new Color(196, 204, 204));
            jLabelProblema.setForeground(new Color(196, 204, 204));
            jLabelFecha.setForeground(new Color(196, 204, 204));
            jLabelFecha.setForeground(new Color(196, 204, 204));

        }

    }

    public void Mostrar(int parameter, String labValue, String alumnoValue, String alumno2Value, String pcValue, String problemaValue,
            String cursosValue,
            Date fecha,
            int contador,
            int update) {

        ConnectionPool metodospool = new ConnectionPool();
        java.sql.Connection con = null;

        try {
            String codigosql = "";
            DefaultTableModel modelo = new DefaultTableModel();
            con = metodospool.dataSource.getConnection();
            jTable.setModel(modelo);

            modelo.addColumn("ID"); //agregamos las columnas
            modelo.addColumn("Lab");
            modelo.addColumn("Categoria");
            modelo.addColumn("CategoriaExtra");
            modelo.addColumn("Comentario");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Curso");
            modelo.addColumn("PC");
            modelo.addColumn("Fecha");
            modelo.addColumn("Solucionado");

            TableColumn columna = jTable.getColumn("Fecha");
            columna.setPreferredWidth(140);

            TableColumn columnaID = jTable.getColumn("ID");
            columnaID.setMaxWidth(30);
            columnaID.setMinWidth(30);

            TableColumn columnaLab = jTable.getColumn("Lab");
            columnaLab.setMaxWidth(50);
            columnaLab.setMinWidth(50);

            TableColumn columnaPC = jTable.getColumn("PC");
            columnaPC.setMaxWidth(30);
            columnaPC.setMinWidth(30);

            TableColumn columnaSolucionado = jTable.getColumn("Solucionado");
            columnaSolucionado.setMaxWidth(90);
            columnaSolucionado.setMinWidth(90);

            TableColumn columnaNombre = jTable.getColumn("Nombre");
            columnaNombre.setMaxWidth(100);
            columnaNombre.setMinWidth(100);

            TableColumn columnaApellido = jTable.getColumn("Apellido");
            columnaApellido.setMaxWidth(100);
            columnaApellido.setMinWidth(100);

            TableColumn columnaFecha = jTable.getColumn("Fecha");
            columnaFecha.setMaxWidth(140);
            columnaFecha.setMinWidth(140);

            TableColumn columnaCurso = jTable.getColumn("Curso");
            columnaCurso.setMaxWidth(140);
            columnaCurso.setMinWidth(140);

            TableColumn columnaComentario = jTable.getColumn("Comentario");
            columnaComentario.setMaxWidth(400);
            columnaComentario.setMinWidth(400);

            TableColumn columnaExtra = jTable.getColumn("CategoriaExtra");
            columnaExtra.setMaxWidth(140);
            columnaExtra.setMinWidth(140);

            TableColumn columnaCategoria = jTable.getColumn("Categoria");
            columnaCategoria.setMaxWidth(140);
            columnaCategoria.setMinWidth(140);

            PreparedStatement pst = null;
            ResultSet rs = null;

            switch (parameter) {
                case 0:
                    codigosql = "SELECT ID,Lab,Categoria,CategoriaExtra,Comentario,Nombre,Apellido,Curso,PC,Fecha,Solucionado FROM reportes";
                    break;
                case 1:
                    codigosql = "SELECT ID,Lab,Categoria,CategoriaExtra,Comentario,Nombre,Apellido,Curso,PC,Fecha,Solucionado FROM reportes WHERE Categoria = '" + problemaValue + "'";
                    break;
                case 2:
                    codigosql = "SELECT ID,Lab,Categoria,CategoriaExtra,Comentario,Nombre,Apellido,Curso,PC,Fecha,Solucionado FROM reportes WHERE Lab = '" + labValue + "'";
                    break;
                case 3:
                    codigosql = "SELECT ID,Lab,Categoria,CategoriaExtra,Comentario,Nombre,Apellido,Curso,PC,Fecha,Solucionado FROM reportes WHERE PC = '" + pcValue + "'";
                    break;
                case 4:
                    codigosql = "SELECT ID,Lab,Categoria,CategoriaExtra,Comentario,Nombre,Apellido,Curso,PC,Fecha,Solucionado FROM reportes WHERE Nombre = '" + alumnoValue + "' AND Apellido ='" + alumno2Value + "'";
                    break;
                case 5:
                    codigosql = "SELECT ID,Lab,Categoria,CategoriaExtra,Comentario,Nombre,Apellido,Curso,PC,Fecha,Solucionado FROM reportes WHERE Curso = '" + cursosValue + "'";
                    break;
                case 7:
                    codigosql = "SELECT ID,Lab,Categoria,CategoriaExtra,Comentario,Nombre,Apellido,Curso,PC,Fecha,Solucionado FROM reportes WHERE Fecha LIKE = '%" + fecha + "%'";
                    break;
                case 111:
                    codigosql = "SELECT ID,Lab,Categoria,CategoriaExtra,Comentario,Nombre,Apellido,Curso,PC,Fecha,Solucionado FROM reportes WHERE Fecha LIKE = '%" + fecha + "%'";
                    break;

            }

            pst = con.prepareStatement(codigosql);

            rs = pst.executeQuery();

            ResultSetMetaData rsMD = rs.getMetaData(); // Pasar resultado de la consulta al obj rs 

            int cantidadColumnas = rsMD.getColumnCount();

            while (rs.next()) { //Recorre los datos de la consulta, proporciona los datos de 1 fila por cada ciclo

                Object[] filas = new Object[cantidadColumnas]; //Objetos que va a almacenar los registros de la bd

                for (int i = 0; i < cantidadColumnas; i++) { //Pasa los datos al objeto
                    filas[i] = rs.getObject(i + 1); //Le asignamos a cada fila (objeto) lo que recopilo el metodo rs
                }

                modelo.addRow(filas); //Agregamos tantas filas como datos hayan sido recopilados
            }

        } catch (SQLException e) {
            System.out.println("El error es: " + e);
            Mostrar(0, "", "", "", "", "", "", null, 0, 0);
            JOptionPane.showMessageDialog(this, "Hubo un error al procesar los datos, contacte con el administrador");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldLab = new javax.swing.JTextField();
        jTextFieldPC = new javax.swing.JTextField();
        jTextFieldCurso = new javax.swing.JTextField();
        jTextFieldAlumno = new javax.swing.JTextField();
        jComboBoxProblemas = new javax.swing.JComboBox<>();
        jTextFieldAlumno2 = new javax.swing.JTextField();
        jLabelNombre = new javax.swing.JLabel();
        jLabelApellido = new javax.swing.JLabel();
        jButtonFiltrar = new javax.swing.JButton();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jComboBoxFast = new javax.swing.JComboBox<>();
        jTextFieldFast = new javax.swing.JTextField();
        jCheckBoxAvanzado = new javax.swing.JCheckBox();
        jLabelLab = new javax.swing.JLabel();
        jLabelCurso = new javax.swing.JLabel();
        jLabelFecha = new javax.swing.JLabel();
        jLabelPC = new javax.swing.JLabel();
        jLabelProblema = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldFastApellido = new javax.swing.JTextField();
        jTextFieldFastNombre = new javax.swing.JTextField();
        jLabelFastName = new javax.swing.JLabel();
        jLabelFastSurname = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButtonEliminar = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable);

        jComboBoxProblemas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion", "Software", "Hardware", "Teams", "Windows", "Perifericos" }));

        jLabelNombre.setText("Nombre");

        jLabelApellido.setText("Apellido");

        jButtonFiltrar.setText("Filtrar");
        jButtonFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFiltrarActionPerformed(evt);
            }
        });

        jComboBoxFast.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion", "Reporte", "Laboratorio", "PC", "Alumno", "Curso", "Fecha" }));
        jComboBoxFast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFastActionPerformed(evt);
            }
        });

        jTextFieldFast.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jTextFieldFast.setMinimumSize(new java.awt.Dimension(131, 20));
        jTextFieldFast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFastActionPerformed(evt);
            }
        });
        jTextFieldFast.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldFastKeyReleased(evt);
            }
        });

        jCheckBoxAvanzado.setText("Filtrado avanzado");
        jCheckBoxAvanzado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAvanzadoActionPerformed(evt);
            }
        });

        jLabelLab.setText("Laboratorio");

        jLabelCurso.setText("Curso");

        jLabelFecha.setText("Fecha");

        jLabelPC.setText("PC");

        jLabelProblema.setText("Problema");

        jLabel3.setText("Filtrado rapido");

        jTextFieldFastApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFastApellidoActionPerformed(evt);
            }
        });

        jTextFieldFastNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFastNombreActionPerformed(evt);
            }
        });
        jTextFieldFastNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldFastNombreKeyReleased(evt);
            }
        });

        jLabelFastName.setText("Nombre");

        jLabelFastSurname.setText("Apellido");

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonSave.setText("Guardar");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldLab, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelLab))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelCurso)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBoxProblemas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelProblema))
                            .addGap(30, 30, 30)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelPC)
                                .addComponent(jTextFieldPC, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNombre)
                            .addComponent(jTextFieldAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelApellido)
                            .addComponent(jTextFieldAlumno2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jCheckBoxAvanzado)
                    .addComponent(jLabelFecha)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jButtonFiltrar)))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelFastName)
                        .addGap(53, 53, 53)
                        .addComponent(jLabelFastSurname)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonEliminar)
                                .addGap(43, 43, 43)
                                .addComponent(jButtonSave))
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboBoxFast, javax.swing.GroupLayout.Alignment.LEADING, 0, 146, Short.MAX_VALUE)
                                .addComponent(jTextFieldFast, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jTextFieldFastNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextFieldFastApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 994, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCheckBoxAvanzado)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCurso)
                    .addComponent(jComboBoxFast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLab))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldLab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFastSurname, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelPC)
                        .addComponent(jLabelFastName)
                        .addComponent(jLabelProblema)))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldFastNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldFastApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBoxProblemas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelApellido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldAlumno2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonFiltrar)
                            .addComponent(jButtonEliminar)
                            .addComponent(jButtonSave))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 233, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldFastNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFastNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFastNombreActionPerformed

    private void jTextFieldFastApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFastApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFastApellidoActionPerformed

    private void jCheckBoxAvanzadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAvanzadoActionPerformed
        // TODO add your handling code here:
        enable();
    }//GEN-LAST:event_jCheckBoxAvanzadoActionPerformed

    private void jTextFieldFastKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFastKeyReleased
        // TODO add your handling code here:
        String valueText = jTextFieldFast.getText();

        if (valueText.isEmpty()) {
            Mostrar(0, "", "", "", "", "", "", null, 0, 0);
        }
    }//GEN-LAST:event_jTextFieldFastKeyReleased

    private void jTextFieldFastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFastActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFastActionPerformed

    private void jComboBoxFastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFastActionPerformed
        // TODO add your handling code here:
        switch (jComboBoxFast.getSelectedIndex()) {
            case 4:
                jTextFieldFastNombre.setEnabled(true);
                jTextFieldFastApellido.setEnabled(true);
                jTextFieldFast.setEnabled(false);
                jLabelFastName.setForeground(Color.BLACK);
                jLabelFastSurname.setForeground(Color.BLACK);
                break;
            case 6:
                jDateChooser1.setEnabled(true);
                jTextFieldFast.setEnabled(false);
                break;
            default:
                jTextFieldFast.setEnabled(true);
                jDateChooser1.setEnabled(false);
                jTextFieldFastNombre.setEnabled(false);
                jTextFieldFastApellido.setEnabled(false);
                jLabelFastName.setForeground(new Color(196, 204, 204));
                jLabelFastSurname.setForeground(new Color(196, 204, 204));
                break;
        }
    }//GEN-LAST:event_jComboBoxFastActionPerformed

    private void jButtonFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFiltrarActionPerformed

        int parameter = 0;

        String value = jTextFieldFast.getText().trim();

        if (jCheckBoxAvanzado.isSelected()) {
            String labValue = jTextFieldLab.getText().trim();
            String alumnoValue = jTextFieldAlumno.getText().trim();
            String alumno2Value = jTextFieldAlumno2.getText().trim();
            String pcValue = jTextFieldPC.getText().trim();
            String problemaValue = jTextFieldLab.getText().trim();
            String cursosValue = jTextFieldCurso.getText().trim();

            parameter = 7;
            Mostrar(parameter, labValue, alumnoValue, alumno2Value, pcValue, problemaValue, cursosValue, null, 0, 0);

        } else if (jComboBoxFast.getSelectedIndex() == 1) {
            parameter = 1;
            Mostrar(parameter, "", "", "", "", value, "", null, 0, 0);
        } else if (jComboBoxFast.getSelectedIndex() == 2) {
            parameter = 2;
            Mostrar(parameter, value, "", "", "", "", "", null, 0, 0);
        } else if (jComboBoxFast.getSelectedIndex() == 3) {
            parameter = 3;
            Mostrar(parameter, "", "", "", value, "", "", null, 0, 0);
        } else if (jComboBoxFast.getSelectedIndex() == 4) {
            String valueNom = jTextFieldFastNombre.getText().trim();
            String valueAp = jTextFieldFastApellido.getText().trim();
            parameter = 4;
            Mostrar(parameter, "", valueNom, valueAp, "", "", "", null, 0, 0);
        } else if (jComboBoxFast.getSelectedIndex() == 5) {
            parameter = 5;
            Mostrar(parameter, "", "", "", "", "", value, null, 0, 0);
        } else if (jComboBoxFast.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una opcion");
        } else if (jComboBoxFast.getSelectedIndex() == 6) {
            parameter = 6;
            Date date = jDateChooser1.getDate();

            Mostrar(parameter, "", "", "", "", "", "", date, 0, 0);

        }
    }//GEN-LAST:event_jButtonFiltrarActionPerformed

    private void jTextFieldFastNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFastNombreKeyReleased
        // TODO add your handling code here:
        String valueText = jTextFieldFastNombre.getText();
        String valueText2 = jTextFieldFastApellido.getText();

        if (valueText.isEmpty() && valueText2.isEmpty()) {
            Mostrar(0, "", "", "", "", "", "", null, 0, 0);
        }
    }//GEN-LAST:event_jTextFieldFastNombreKeyReleased

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        // TODO add your handling code here:
        Eliminar();
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        // TODO add your handling code here:

        for (int i = 0; i < jTable.getRowCount(); i++) {
            if (jTable.getValueAt(i, 10).toString().equals("Si") || jTable.getValueAt(i, 10).toString().equals("si") || jTable.getValueAt(i, 10).toString().equals("sI") || jTable.getValueAt(i, 10).toString().equals("SI")) {
                int ID = (int) jTable.getValueAt(i, 0);
                Update(ID);
            } else {
                
            }
        }

        Mostrar(0, "", "", "", "", "", "", null, 0, 111);

    }//GEN-LAST:event_jButtonSaveActionPerformed

    public void Update(int id) {
        ConnectionPool metodospool = new ConnectionPool();
        java.sql.Connection con = null;

        try {

            con = metodospool.dataSource.getConnection();

            String sentence = "UPDATE reportes SET Solucionado = 'Si' WHERE ID =" + id;

            PreparedStatement pst = null;
            int rs = 0;

            pst = con.prepareStatement(sentence);
            rs = pst.executeUpdate();

        } catch (Exception e) {
            System.out.println("El error es: " + e);
        }
    }

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
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reportes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonFiltrar;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBoxAvanzado;
    private javax.swing.JComboBox<String> jComboBoxFast;
    private javax.swing.JComboBox<String> jComboBoxProblemas;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelApellido;
    private javax.swing.JLabel jLabelCurso;
    private javax.swing.JLabel jLabelFastName;
    private javax.swing.JLabel jLabelFastSurname;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelLab;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelPC;
    private javax.swing.JLabel jLabelProblema;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextFieldAlumno;
    private javax.swing.JTextField jTextFieldAlumno2;
    private javax.swing.JTextField jTextFieldCurso;
    private javax.swing.JTextField jTextFieldFast;
    private javax.swing.JTextField jTextFieldFastApellido;
    private javax.swing.JTextField jTextFieldFastNombre;
    private javax.swing.JTextField jTextFieldLab;
    private javax.swing.JTextField jTextFieldPC;
    // End of variables declaration//GEN-END:variables
}
