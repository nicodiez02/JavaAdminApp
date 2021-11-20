package ventanas;

import clases.Colores;
import clases.ConnectionPool;
import java.awt.Color;
import java.awt.Component;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.*;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.event.TableModelEvent;
import static javax.swing.event.TableModelEvent.UPDATE;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Nico
 */
public class Reportes extends javax.swing.JFrame {

    public static String user_update = "";
    Colores render = new Colores();
    TableRowSorter<DefaultTableModel> sorter;

    public Reportes() {
        initComponents();

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos Informatica A");

        setExtendedState(MAXIMIZED_BOTH);

        jDateChooser.setLocale(new Locale("es"));
        jDateChooser.setDateFormatString("yyyy-MM-dd");

        jDateChooser1.setLocale(new Locale("es"));
        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        Mostrar();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/administracion.png")));

        jLabelSol.setForeground(Color.BLACK);

        jTextFieldLab.setEnabled(false);
        jTextFieldCurso.setEnabled(false);
        jTextFieldAlumno.setEnabled(false);
        jTextFieldAlumno2.setEnabled(false);
        jTextFieldProblema.setEnabled(false);
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

        jLabelFastSurname.setForeground(new Color(196, 204, 204));
        jLabelFastName1.setForeground(new Color(196, 204, 204));

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
        columnaExtra.setMaxWidth(30);
        columnaExtra.setMinWidth(30);

        TableColumn columnaCategoria = jTable.getColumn("Categoria");
        columnaCategoria.setMaxWidth(30);
        columnaCategoria.setMinWidth(30);

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
                PreparedStatement delete = con.prepareStatement("DELETE FROM reportes WHERE ID = '" + value + "'");

                delete.executeUpdate();

                Mostrar();
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
            jTextFieldProblema.setEnabled(true);
            jDateChooser.setEnabled(true);
            jComboBoxFast.setEnabled(false);
            jTextFielfFiltro.setEnabled(false);

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
            jTextFieldProblema.setEnabled(false);
            jComboBoxFast.setEnabled(true);
            jTextFielfFiltro.setEnabled(true);
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

    public void Mostrar() {

        ConnectionPool metodospool = new ConnectionPool();
        java.sql.Connection con = null;

        try {
            String codigosql = "";
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;
                }
            };
            con = metodospool.dataSource.getConnection();
            jTable.setModel(modelo);
            jTable.setAutoCreateRowSorter(true);
            sorter = new TableRowSorter<>(modelo);
            jTable.setRowSorter(sorter);

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

            codigosql = "SELECT ID, Aula, Categoria, CategoriaExtra, Comentario, Nombre, Apellido, curso_name, PC, Fecha, Solucionado\n"
                    + "FROM reportes \n"
                    + "JOIN cursos \n"
                    + "ON CursoID = id_curso\n"
                    + "JOIN laboratorios \n"
                    + "ON Lab_ID = id_Lab;";

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

            modelo.addTableModelListener(jTable);

        } catch (SQLException e) {
            System.out.println("El error es: " + e);
            Mostrar();
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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButtonEliminar = new javax.swing.JButton();
        jButtonFiltrar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabelProblema = new javax.swing.JLabel();
        jLabelCurso = new javax.swing.JLabel();
        jLabelLab = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jTextFieldCurso = new javax.swing.JTextField();
        jTextFieldLab = new javax.swing.JTextField();
        jTextFieldAlumno = new javax.swing.JTextField();
        jLabelApellido = new javax.swing.JLabel();
        jTextFieldAlumno2 = new javax.swing.JTextField();
        jTextFieldPC = new javax.swing.JTextField();
        jLabelPC = new javax.swing.JLabel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jLabelFecha = new javax.swing.JLabel();
        jCheckBoxAvanzado = new javax.swing.JCheckBox();
        jTextFieldProblema = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabelFastSurname = new javax.swing.JLabel();
        jTextFieldFastNombre = new javax.swing.JTextField();
        jTextFieldFastApellido = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jComboBoxFast = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabelFastName1 = new javax.swing.JLabel();
        jTextFielfFiltro = new javax.swing.JTextField();
        jTextFieldSolucionado = new javax.swing.JTextField();
        jLabelSol = new javax.swing.JLabel();
        jButtonUpdate = new javax.swing.JButton();
        jButtonAct = new javax.swing.JButton();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(219, 235, 243));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable.setBackground(new java.awt.Color(92, 92, 92));
        jTable.setForeground(new java.awt.Color(92, 92, 92));
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 307));

        jPanel2.setBackground(new java.awt.Color(164, 164, 164));

        jPanel3.setBackground(new java.awt.Color(250, 251, 253));

        jButtonEliminar.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.setMaximumSize(new java.awt.Dimension(71, 23));
        jButtonEliminar.setMinimumSize(new java.awt.Dimension(71, 23));
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonFiltrar.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jButtonFiltrar.setText("Filtrar");
        jButtonFiltrar.setMaximumSize(new java.awt.Dimension(71, 23));
        jButtonFiltrar.setMinimumSize(new java.awt.Dimension(71, 23));
        jButtonFiltrar.setPreferredSize(new java.awt.Dimension(71, 23));
        jButtonFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFiltrarActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(250, 251, 253));

        jLabelProblema.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabelProblema.setText("Problema");

        jLabelCurso.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabelCurso.setText("Curso");

        jLabelLab.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabelLab.setText("Laboratorio");

        jLabelNombre.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabelNombre.setText("Nombre");

        jLabelApellido.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabelApellido.setText("Apellido");

        jLabelPC.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabelPC.setText("PC");

        jLabelFecha.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabelFecha.setText("Fecha");

        jCheckBoxAvanzado.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jCheckBoxAvanzado.setText("FILTRADO AVANZADO");
        jCheckBoxAvanzado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAvanzadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLab)
                    .addComponent(jTextFieldLab, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabelCurso)
                        .addGap(38, 38, 38))
                    .addComponent(jTextFieldCurso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabelNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelApellido)
                .addGap(28, 28, 28))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabelProblema, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldProblema))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelPC))
                    .addComponent(jTextFieldPC, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                        .addComponent(jTextFieldAlumno2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabelFecha)
                        .addGap(33, 33, 33))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jCheckBoxAvanzado)
                .addGap(52, 52, 52))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jCheckBoxAvanzado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabelLab)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabelCurso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelProblema)
                    .addComponent(jTextFieldProblema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre)
                    .addComponent(jLabelApellido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAlumno2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelFecha)
                            .addComponent(jLabelPC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(250, 251, 253));

        jLabelFastSurname.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabelFastSurname.setText("Apellido");

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

        jTextFieldFastApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFastApellidoActionPerformed(evt);
            }
        });

        jDateChooser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jDateChooser1MousePressed(evt);
            }
        });
        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        jComboBoxFast.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion", "Reporte", "Laboratorio", "PC", "Alumno", "Curso", "Fecha" }));
        jComboBoxFast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFastActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel3.setText("FILTRADO RAPIDO");

        jLabelFastName1.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabelFastName1.setText("Nombre");

        jTextFielfFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFielfFiltroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFielfFiltroKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jComboBoxFast, 0, 160, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldFastNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFastName1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelFastSurname)
                        .addGap(31, 31, 31))
                    .addComponent(jTextFieldFastApellido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(35, 35, 35))
            .addComponent(jTextFielfFiltro)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxFast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jTextFielfFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFastSurname)
                    .addComponent(jLabelFastName1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldFastApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFastNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jTextFieldSolucionado.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jTextFieldSolucionado.setMinimumSize(new java.awt.Dimension(131, 20));
        jTextFieldSolucionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSolucionadoActionPerformed(evt);
            }
        });
        jTextFieldSolucionado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSolucionadoKeyReleased(evt);
            }
        });

        jLabelSol.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabelSol.setText("¿Solucionado?");

        jButtonUpdate.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jButtonUpdate.setText("Actualizar");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonAct.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jButtonAct.setText("Volver a cargar");
        jButtonAct.setMaximumSize(new java.awt.Dimension(71, 23));
        jButtonAct.setMinimumSize(new java.awt.Dimension(71, 23));
        jButtonAct.setPreferredSize(new java.awt.Dimension(71, 23));
        jButtonAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(jButtonAct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonFiltrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelSol)
                                .addGap(9, 9, 9))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jButtonUpdate)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldSolucionado, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 275, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jButtonAct, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(59, 59, 59)
                            .addComponent(jLabelSol)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldSolucionado, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(328, 328, 328)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(378, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        // TODO add your handling code here:
        Eliminar();
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jCheckBoxAvanzadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAvanzadoActionPerformed
        // TODO add your handling code here:
        enable();
    }//GEN-LAST:event_jCheckBoxAvanzadoActionPerformed

    private void jComboBoxFastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFastActionPerformed
        // TODO add your handling code here:
        switch (jComboBoxFast.getSelectedIndex()) {
            case 4:
                jTextFieldFastApellido.setEnabled(true);
                jTextFieldFastNombre.setEnabled(true);

                jTextFielfFiltro.setEnabled(false);
                jDateChooser1.setEnabled(false);

                jLabelFastName1.setForeground(Color.BLACK);
                jLabelFastSurname.setForeground(Color.BLACK);
                break;
            case 6:
                jDateChooser1.setEnabled(true);
                jTextFielfFiltro.setEnabled(false);

                jTextFieldFastApellido.setEnabled(false);
                jTextFieldFastNombre.setEnabled(false);

                jLabelFastSurname.setForeground(new Color(196, 204, 204));
                jLabelFastName1.setForeground(new Color(196, 204, 204));

                break;

            default:
                jTextFielfFiltro.setEnabled(true);
                jDateChooser1.setEnabled(false);
                jTextFieldFastNombre.setEnabled(false);
                jTextFieldFastApellido.setEnabled(false);
                jLabelFastSurname.setForeground(new Color(196, 204, 204));
                jLabelFastName1.setForeground(new Color(196, 204, 204));

                break;
        }
    }//GEN-LAST:event_jComboBoxFastActionPerformed

    private void jTextFieldFastApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFastApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFastApellidoActionPerformed

    private void jTextFieldFastNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFastNombreKeyReleased
        // TODO add your handling code here:
        String valueText = jTextFieldFastNombre.getText();
        String valueText2 = jTextFieldFastApellido.getText();

        if (valueText.isEmpty() && valueText2.isEmpty()) {
            Mostrar();
        }
    }//GEN-LAST:event_jTextFieldFastNombreKeyReleased

    private void jTextFieldFastNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFastNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFastNombreActionPerformed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        // TODO add your handling code here:

        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();

        String solucion = tableModel.getValueAt(jTable.getSelectedRow(), 10).toString();

        jTextFieldSolucionado.setText(solucion);

    }//GEN-LAST:event_jTableMouseClicked

    private void jTextFieldSolucionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSolucionadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSolucionadoActionPerformed

    private void jTextFieldSolucionadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSolucionadoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSolucionadoKeyReleased

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();

        if (jTable.getSelectedRowCount() == 1) {

            String solucionado = jTextFieldSolucionado.getText().trim();

            if (solucionado.equals("Si") || solucionado.equals("sI") || solucionado.equals("SI") || solucionado.equals("si")
                    || solucionado.equals("no") || solucionado.equals("NO") || solucionado.equals("nO") || solucionado.equals("No")) {

                ConnectionPool metodospool = new ConnectionPool();
                java.sql.Connection con = null;

                try {
                    int id = (int) tableModel.getValueAt(jTable.getSelectedRow(), 0);

                    con = metodospool.dataSource.getConnection();
                    String sentence = "";

                    sentence = "UPDATE reportes SET Solucionado ='" + solucionado + "'WHERE ID =" + id;

                    PreparedStatement pst = null;
                    int rs = 0;

                    pst = con.prepareStatement(sentence);
                    rs = pst.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Actualizacion realizada con exito!");

                    tableModel.setValueAt(solucionado, jTable.getSelectedRow(), 10);
                } catch (Exception e) {
                    System.out.println("El error es: " + e);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Revise lo que introdujo; Recuerde que debe ingresar Si/No");
            }
        }


    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActActionPerformed
        // TODO add your handling code here:
        Mostrar();
    }//GEN-LAST:event_jButtonActActionPerformed


    private void jTextFielfFiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFielfFiltroKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFielfFiltroKeyTyped

    private void jTextFielfFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFielfFiltroKeyReleased
        // TODO add your handling code here:
        int parameter = jComboBoxFast.getSelectedIndex();
        filtrar(parameter);

    }//GEN-LAST:event_jTextFielfFiltroKeyReleased

    private void jDateChooser1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser1MousePressed
        // TODO add your handling code here:
           
        
        String fecha;
        java.util.Date date = new java.util.Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        fecha = f.format(jDateChooser1.getDate());
        
        if (fecha.length() > 0) {
            System.out.println(fecha);
            sorter.setRowFilter(RowFilter.regexFilter(fecha, 9));
        }
        
    }//GEN-LAST:event_jDateChooser1MousePressed

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jButtonFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFiltrarActionPerformed
        
        if (jCheckBoxAvanzado.isSelected()) {
            String labValue = jTextFieldLab.getText().trim();
            String alumnoValue = jTextFieldAlumno.getText().trim();
            String alumno2Value = jTextFieldAlumno2.getText().trim();
            String pcValue = jTextFieldPC.getText().trim();
            String problemaValue = jTextFieldProblema.getText().trim();
            String cursosValue = jTextFieldCurso.getText().trim();

            cursosValue.toUpperCase();

            String fecha;
            java.util.Date date = new java.util.Date();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

            fecha = f.format(jDateChooser.getDate());
            Mostrar();
        }

            
    }//GEN-LAST:event_jButtonFiltrarActionPerformed

    private void filtrar(int parameter) {
        try {

            switch (parameter) {
                case 1:
                    sorter.setRowFilter(RowFilter.regexFilter(jTextFielfFiltro.getText().trim(), 2));
                    break;

                case 2:
                    sorter.setRowFilter(RowFilter.regexFilter(jTextFielfFiltro.getText().trim(), 1));
                    break;
                case 3:
                    sorter.setRowFilter(RowFilter.regexFilter(jTextFielfFiltro.getText().trim(), 8));
                    break;
                case 4:
                    sorter.setRowFilter(RowFilter.regexFilter(jTextFielfFiltro.getText().trim().toUpperCase(), 3));
                    break;
                case 5:
                    JOptionPane.showMessageDialog(this, "entre");
                    sorter.setRowFilter(RowFilter.regexFilter(jTextFielfFiltro.getText().trim(), 7));
                    break;
                case 0:
                    JOptionPane.showMessageDialog(this, "Seleccione una opcion de filtrado");
                    break;

            }

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
    private javax.swing.JButton jButtonAct;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonFiltrar;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBoxAvanzado;
    private javax.swing.JComboBox<String> jComboBoxFast;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelApellido;
    private javax.swing.JLabel jLabelCurso;
    private javax.swing.JLabel jLabelFastName1;
    private javax.swing.JLabel jLabelFastSurname;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelLab;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelPC;
    private javax.swing.JLabel jLabelProblema;
    private javax.swing.JLabel jLabelSol;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextFieldAlumno;
    private javax.swing.JTextField jTextFieldAlumno2;
    private javax.swing.JTextField jTextFieldCurso;
    private javax.swing.JTextField jTextFieldFastApellido;
    private javax.swing.JTextField jTextFieldFastNombre;
    private javax.swing.JTextField jTextFieldLab;
    private javax.swing.JTextField jTextFieldPC;
    private javax.swing.JTextField jTextFieldProblema;
    private javax.swing.JTextField jTextFieldSolucionado;
    private javax.swing.JTextField jTextFielfFiltro;
    // End of variables declaration//GEN-END:variables
}
