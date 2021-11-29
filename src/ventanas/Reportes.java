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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import clases.HeaderCellRenderer;
import clases.CellRenderer;
import java.util.TimeZone;

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

        mostrar(0);

        JTableHeader jtableHeader = jTable.getTableHeader();
        jtableHeader.setDefaultRenderer(new HeaderCellRenderer());
        jTable.setTableHeader(  jtableHeader );
        /** propiedades para las celdas */
        jTable.setSelectionBackground( new Color( 231, 247 , 252) );
        jTable.setSelectionForeground( new Color( 0,0,0) );        
        jTable.setGridColor(new java.awt.Color(221, 221, 221));        
        jTable.setDefaultRenderer (Object.class, new CellRenderer());

       
        jDateChooser1.setLocale(new Locale("es"));
        jDateChooser1.setDateFormatString("yyyy-MM-dd");
       
        jLabelSol.setForeground(Color.BLACK);

        jTextFieldFastNombre.setEnabled(false);
        jTextFieldFastApellido.setEnabled(false);
        jDateChooser1.setEnabled(false);
        jButtonFiltrar.setEnabled(false);

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

    public void eliminar() {
        int fila = jTable.getSelectedRow();
        String value = jTable.getValueAt(fila, 0).toString();

        if (value.equals("")) {
            JOptionPane.showMessageDialog(this, "Seleccione un campo");
        } else {
            try {
                Connection c = ConnectionPool.getInstance().getConnection();
                if (c != null) {

                    PreparedStatement delete = c.prepareStatement("DELETE FROM reportes WHERE ID = '" + value + "'");
                    delete.executeUpdate();

                    mostrar(0);
                } else {
                    System.out.println("No se pudo conectar");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Hubo un error al procesar la operacion, contacte con el administrador");
                System.out.println("El error es: " + e);
            }
        }
    }

    public void mostrar(int seleccion) {

        try {
            Connection c = ConnectionPool.getInstance().getConnection();

            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;
                }
            };

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
            columnaID.setMaxWidth(50);
            columnaID.setMinWidth(50);

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

            String codigosql = "";

            PreparedStatement pst = null;
            ResultSet rs = null;
            if (seleccion == 4) {

                String value1 = jTextFieldFastNombre.getText().trim();
                String value2 = jTextFieldFastApellido.getText().trim();

                codigosql = "	 SELECT ID, Aula, Categoria, CategoriaExtra, Comentario, Nombre, Apellido, curso_name, PC, Fecha, Solucionado\n"
                        + "                         FROM reportes\n"
                        + "                         JOIN cursos\n"
                        + "                         ON CursoID = id_curso\n"
                        + "                         JOIN laboratorios\n"
                        + "                         ON Lab_ID = id_Lab\n"
                        + "                         WHERE Nombre = '" + value1 + "' AND \n"
                        + "                         Apellido = '" + value2 + "'";

            } else if (seleccion == 6) {

                String fecha;
                java.util.Date date = new java.util.Date();
                                
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

                fecha = f.format(jDateChooser1.getDate());

                codigosql = "	 SELECT ID, Aula, Categoria, CategoriaExtra, Comentario, Nombre, Apellido, curso_name, PC, Fecha, Solucionado\n"
                        + "                         FROM reportes\n"
                        + "                         JOIN cursos\n"
                        + "                         ON CursoID = id_curso\n"
                        + "                         JOIN laboratorios\n"
                        + "                         ON Lab_ID = id_Lab\n"
                        + "                         WHERE Fecha LIKE '%" + fecha + "%'";

            } else {
                codigosql = "SELECT ID, Aula, Categoria, CategoriaExtra, Comentario, Nombre, Apellido, curso_name, PC, Fecha, Solucionado\n"
                        + "FROM reportes \n"
                        + "JOIN cursos \n"
                        + "ON CursoID = id_curso\n"
                        + "JOIN laboratorios \n"
                        + "ON Lab_ID = id_Lab;";

            }

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

            modelo.addTableModelListener(jTable);

        } catch (SQLException e) {
            System.out.println("El error es: " + e);
            mostrar(0);
            JOptionPane.showMessageDialog(this, "Hubo un error al procesar los datos, contacte con el administrador");
        }

    }

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
                    sorter.setRowFilter(RowFilter.regexFilter(jTextFielfFiltro.getText().trim().toUpperCase(), 7));
                    break;
                case 0:
                    JOptionPane.showMessageDialog(this, "Seleccione una opcion de filtrado");
                    break;
            }

        } catch (Exception e) {
            System.out.println("El error es: " + e);

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
        jPanel1 = new javax.swing.JPanel();
        jLabelFastSurname = new javax.swing.JLabel();
        jTextFieldFastNombre = new javax.swing.JTextField();
        jTextFieldFastApellido = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jComboBoxFast = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabelFastName1 = new javax.swing.JLabel();
        jTextFielfFiltro = new javax.swing.JTextField();
        jButtonFiltrar = new javax.swing.JButton();
        jTextFieldSolucionado = new javax.swing.JTextField();
        jLabelSol = new javax.swing.JLabel();
        jButtonUpdate = new javax.swing.JButton();
        jButtonAct = new javax.swing.JButton();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(219, 235, 243));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable.setBackground(new java.awt.Color(153, 255, 51));
        jTable.setForeground(new java.awt.Color(128, 128, 128));
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable.setGridColor(new java.awt.Color(255, 255, 255));
        jTable.setSelectionBackground(new java.awt.Color(0, 255, 51));
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 307));

        jPanel2.setBackground(new java.awt.Color(51, 87, 161));

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
        jLabel3.setText("FILTRADO");

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
            .addComponent(jTextFielfFiltro)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jButtonFiltrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(18, 18, 18)
                .addComponent(jButtonFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabelSol)
                        .addGap(175, 175, 175))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAct, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(jTextFieldSolucionado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(144, 144, 144)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jButtonAct, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelSol)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSolucionado, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(301, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(359, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(350, 350, 350))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        // TODO add your handling code here:

        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();

        String solucion = tableModel.getValueAt(jTable.getSelectedRow(), 10).toString();

        jTextFieldSolucionado.setText(solucion);

    }//GEN-LAST:event_jTableMouseClicked

    private void jButtonActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActActionPerformed
        // TODO add your handling code here:
        mostrar(0);
    }//GEN-LAST:event_jButtonActActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();

        String solucionado = jTextFieldSolucionado.getText().trim();
        if (solucionado.equals("")) {
            JOptionPane.showMessageDialog(this, "Seleccione un campo");
        } else {

            if (jTable.getSelectedRowCount() == 1) {

                if (solucionado.equals("Si") || solucionado.equals("sI") || solucionado.equals("SI") || solucionado.equals("si")
                    || solucionado.equals("no") || solucionado.equals("NO") || solucionado.equals("nO") || solucionado.equals("No")) {

                    try {
                        Connection c = ConnectionPool.getInstance().getConnection();
                        int id = (int) tableModel.getValueAt(jTable.getSelectedRow(), 0);

                        String sentence = "";

                        sentence = "UPDATE reportes SET Solucionado ='" + solucionado + "'WHERE ID =" + id;

                        PreparedStatement pst = null;
                        int rs = 0;

                        pst = c.prepareStatement(sentence);
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
        }

    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jTextFieldSolucionadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSolucionadoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSolucionadoKeyReleased

    private void jTextFieldSolucionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSolucionadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSolucionadoActionPerformed

    private void jButtonFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFiltrarActionPerformed

         if (jComboBoxFast.getSelectedIndex() == 4) {
            mostrar(jComboBoxFast.getSelectedIndex());

        } else if (jComboBoxFast.getSelectedIndex() == 6) {
            mostrar(jComboBoxFast.getSelectedIndex());

        }
    }//GEN-LAST:event_jButtonFiltrarActionPerformed

    private void jTextFielfFiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFielfFiltroKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFielfFiltroKeyTyped

    private void jTextFielfFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFielfFiltroKeyReleased
        // TODO add your handling code here:
        int parameter = jComboBoxFast.getSelectedIndex();
        filtrar(parameter);
    }//GEN-LAST:event_jTextFielfFiltroKeyReleased

    private void jComboBoxFastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFastActionPerformed
        // TODO add your handling code here:
        switch (jComboBoxFast.getSelectedIndex()) {
            case 4:
            jTextFieldFastApellido.setEnabled(true);
            jTextFieldFastNombre.setEnabled(true);
            jButtonFiltrar.setEnabled(true);

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
            jButtonFiltrar.setEnabled(true);

            jLabelFastSurname.setForeground(new Color(196, 204, 204));
            jLabelFastName1.setForeground(new Color(196, 204, 204));

            break;

            default:
            jTextFielfFiltro.setEnabled(true);
            jDateChooser1.setEnabled(false);
            jTextFieldFastNombre.setEnabled(false);
            jTextFieldFastApellido.setEnabled(false);
            jButtonFiltrar.setEnabled(false);
            jLabelFastSurname.setForeground(new Color(196, 204, 204));
            jLabelFastName1.setForeground(new Color(196, 204, 204));

            break;
        }
    }//GEN-LAST:event_jComboBoxFastActionPerformed

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange

    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jDateChooser1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser1MousePressed
        // TODO add your handling code
    }//GEN-LAST:event_jDateChooser1MousePressed

    private void jTextFieldFastApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFastApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFastApellidoActionPerformed

    private void jTextFieldFastNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFastNombreKeyReleased
        // TODO add your handling code here:
        String valueText = jTextFieldFastNombre.getText();
        String valueText2 = jTextFieldFastApellido.getText();

        if (valueText.isEmpty() && valueText2.isEmpty()) {
            mostrar(0);
        }
    }//GEN-LAST:event_jTextFieldFastNombreKeyReleased

    private void jTextFieldFastNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFastNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFastNombreActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
    }//GEN-LAST:event_jButtonEliminarActionPerformed


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
    private javax.swing.JComboBox<String> jComboBoxFast;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelFastName1;
    private javax.swing.JLabel jLabelFastSurname;
    private javax.swing.JLabel jLabelSol;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextFieldFastApellido;
    private javax.swing.JTextField jTextFieldFastNombre;
    private javax.swing.JTextField jTextFieldSolucionado;
    private javax.swing.JTextField jTextFielfFiltro;
    // End of variables declaration//GEN-END:variables
}
