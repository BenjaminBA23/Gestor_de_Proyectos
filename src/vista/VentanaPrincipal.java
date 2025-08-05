/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;
import modelo.Usuario;
import servicio.UsuarioServicio;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.List;
import modelo.Proyecto;
import modelo.Tarea;
import servicio.ProyectoServicio;
import servicio.TareaServicio;

/**
 *
 * @author Ben
 */
public class VentanaPrincipal extends javax.swing.JFrame {
private UsuarioServicio servicio = new UsuarioServicio();
private DefaultTableModel modelo;
private TareaServicio tareaServicio = new TareaServicio();
private DefaultTableModel modeloTarea;
private List<Usuario> listaUsuarios;
private List<Proyecto> listaProyectos;
private int filaSeleccionada = -1;
private int idUsuarioSeleccionado = -1;

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        cargarProyectosEnTabla();
        cargarComboProyecto();      // si lo tienes
        cargarUsuariosEnTabla();
        cargarComboUsuario();         // si lo tienes
        cargarUsuariosEnTabla();
        cargarComboUsuario();       // si lo tienes
        cargarComboProyecto();         // para tareas
        cargarComboUsuario();          // para tareas
    }
    
    //cargar tabla pero para usuario
    private void cargarUsuariosEnTabla() {
    modelo = (DefaultTableModel) tablaUsuarios.getModel();
    modelo.setRowCount(0); // limpia la tabla

    List<Usuario> lista = servicio.listarUsuarios();

    for (Usuario u : lista) {
        modelo.addRow(new Object[]{
            u.getId(), u.getNombre(), u.getCorreo()
        });
    }
    }
    //cagar tabla pero para tareas 
private void cargarTablaTareas() {
    DefaultTableModel modelo = (DefaultTableModel) tablaTareas.getModel();
    modelo.setRowCount(0);  // Limpiar la tabla antes de llenarla

    TareaServicio servicio = new TareaServicio();
    List<Tarea> lista = servicio.listarTareas(); // Obtener lista de tareas

    for (Tarea t : lista) {
        modelo.addRow(new Object[]{
            t.getId(), // ID de tarea
            t.getNombre(), // Nombre de la tarea
            t.getUsuarioNombre(),  // Nombre del usuario
            t.getProyectoNombre(), // Nombre del proyecto
            t.getDescripcion(), // Descripción
            t.getFechaInicio(), // Fecha de inicio
            t.getFechaFin(), // Fecha de fin
            t.getEstado(), // Estado
            t.getPrioridad() // Prioridad
        });
    }
}
  
    
    private void listarProyectos() {
    ProyectoServicio servicio = new ProyectoServicio();
    List<Proyecto> lista = servicio.listarProyectos();

    DefaultTableModel modelo = new DefaultTableModel();
    // Aquí es donde DEBES poner las columnas correctas
    modelo.setColumnIdentifiers(new String[]{"ID", "Nombre", "Descripcion", "FechaInicio", "FechaFin"});

    for (Proyecto p : lista) {
        modelo.addRow(new Object[]{
            p.getId(),
            p.getNombre(),
            p.getDescripcion(),
            p.getFechaInicio(),
            p.getFechaFin()
        });
    }

    tablaProyectos.setModel(modelo); // Esto asigna el modelo a la tabla
}
    
    //ahora para cargar el proyecto
    private void cargarProyectosEnTabla() {
    DefaultTableModel modelo = (DefaultTableModel) tablaProyectos.getModel();
    modelo.setRowCount(0); // limpia tabla

    ProyectoServicio servicio = new ProyectoServicio();
    List<Proyecto> lista = servicio.listarProyectos();

        for (Proyecto p : lista) {
            modelo.addRow(new Object[]{
                p.getId(), p.getNombre(), p.getDescripcion(), p.getFechaInicio(), p.getFechaFin()
            });
        }
    }
    
    
    
    
    private void cargarComboUsuario() {
    comboUsuario.removeAllItems();
    UsuarioServicio usuarioServicio = new UsuarioServicio();
    listaUsuarios = usuarioServicio.listarUsuarios();

    for (Usuario u : listaUsuarios) {
        comboUsuario.addItem(u.getNombre());
    }
    }

    private void cargarComboProyecto() {
        comboProyecto.removeAllItems();
        ProyectoServicio proyectoServicio = new ProyectoServicio();
        listaProyectos = proyectoServicio.listarProyectos();

        for (Proyecto p : listaProyectos) {
            comboProyecto.addItem(p.getNombre());
        }
    }
    //ediatar usuario
    private void editarUsuario() {
    int fila = tablaUsuarios.getSelectedRow();
    if (fila != -1) {
        txtNombreUsuario.setText(tablaUsuarios.getValueAt(fila, 1).toString());
        txtCorreoUsuario.setText(tablaUsuarios.getValueAt(fila, 2).toString());
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un usuario para editar.");
    }
    }
    //actualizar usuario
    private void actualizarUsuario() {
    int fila = tablaUsuarios.getSelectedRow();
    if (fila != -1) {
        try {
            int id = Integer.parseInt(tablaUsuarios.getValueAt(fila, 0).toString());
            String nombre = txtNombreUsuario.getText().trim();
            String correo = txtCorreoUsuario.getText().trim();

            if (nombre.isEmpty() || correo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debes llenar todos los campos.");
                return;
            }

            Usuario u = new Usuario(id, nombre, correo);
            UsuarioServicio servicio = new UsuarioServicio();
            servicio.actualizarUsuario(u);

            cargarUsuariosEnTabla();
        cargarComboUsuario();
            limpiarCamposUsuario();
            JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar usuario.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona una fila para actualizar.");
    }
}
    
    //limpiar usuario
    private void limpiarCamposUsuario() {
    txtNombreUsuario.setText("");
    txtCorreoUsuario.setText("");
    tablaUsuarios.clearSelection(); // Deselecciona cualquier fila seleccionada
    }
    //para limpiar campos en proyecto
    private void limpiarCamposProyecto() {
    txtNombreProyecto.setText("");
    txtDescripcionProyecto.setText("");
    txtInicioProyecto.setText("");
    txtFinProyecto.setText("");
    tablaProyectos.clearSelection();
    }
        
    //limpiar para usuario
    private void limpiarCampos() {
    txtNombreUsuario.setText("");
    txtCorreoUsuario.setText("");
    tablaUsuarios.clearSelection();
    }
    
    //limpiar pero tareas
    private void limpiarCamposTarea() {
    txtNombreTarea.setText("");
    txtDescripcionTarea.setText("");
    txtFechaInicioTarea.setText("");
    txtFechaFinTarea.setText("");
    comboEstadoTarea.setSelectedIndex(0);
    comboPrioridadTarea.setSelectedIndex(0);
    comboProyecto.setSelectedIndex(0);
    tablaTareas.clearSelection();
}
    private boolean validarCamposProyecto() {
    if (txtNombreProyecto.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "El campo 'Nombre' es obligatorio.");
        txtNombreProyecto.requestFocus();
        return false;
    }
    if (txtDescripcionProyecto.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "El campo 'Descripción' es obligatorio.");
        txtDescripcionProyecto.requestFocus();
        return false;
    }
    if (txtInicioProyecto.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "La 'Fecha de Inicio' es obligatoria.");
        txtInicioProyecto.requestFocus();
        return false;
    }
    if (txtFinProyecto.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "La 'Fecha de Fin' es obligatoria.");
        txtFinProyecto.requestFocus();
        return false;
    }
    return true;
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        tabPanel = new javax.swing.JTabbedPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnAgregarProyecto = new javax.swing.JButton();
        btnEditarProyecto = new javax.swing.JButton();
        btnEliminarProyecto = new javax.swing.JButton();
        btnActualizarProyecto = new javax.swing.JButton();
        txtNombreProyecto = new javax.swing.JTextField();
        txtDescripcionProyecto = new javax.swing.JTextField();
        txtInicioProyecto = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaProyectos = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtFinProyecto = new javax.swing.JTextField();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnAgregarUsuario = new javax.swing.JButton();
        btnEditarUsuario = new javax.swing.JButton();
        btnEliminarUsuario = new javax.swing.JButton();
        txtNombreUsuario = new javax.swing.JTextField();
        txtCorreoUsuario = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        ActualizarUsuario = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        btnAgregarTarea = new javax.swing.JButton();
        btnEditarTarea = new javax.swing.JButton();
        btnEliminarTarea = new javax.swing.JButton();
        btnActualizarTareas = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        comboProyecto = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDescripcionTarea = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFechaInicioTarea = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtFechaFinTarea = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        comboEstadoTarea = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaTareas = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        comboPrioridadTarea = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtNombreTarea = new javax.swing.JTextField();
        comboUsuario = new javax.swing.JComboBox<>();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setText("Nombre:");

        jLabel9.setText("Descripcion:");

        jLabel10.setText("Fecha Inicio:");

        btnAgregarProyecto.setText("Agregar");
        btnAgregarProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProyectoActionPerformed(evt);
            }
        });

        btnEditarProyecto.setText("Editar");
        btnEditarProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProyectoActionPerformed(evt);
            }
        });

        btnEliminarProyecto.setText("Eliminar");
        btnEliminarProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProyectoActionPerformed(evt);
            }
        });

        btnActualizarProyecto.setText("Actualizar");
        btnActualizarProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarProyectoActionPerformed(evt);
            }
        });

        txtDescripcionProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionProyectoActionPerformed(evt);
            }
        });

        tablaProyectos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Descripcion", "FechaInicio", "FechaFin"
            }
        ));
        tablaProyectos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProyectosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaProyectos);

        jLabel11.setText("Fecha Fin:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombreProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDescripcionProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtInicioProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFinProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregarProyecto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditarProyecto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnActualizarProyecto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(btnEliminarProyecto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(txtNombreProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtDescripcionProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtInicioProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(txtFinProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAgregarProyecto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarProyecto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarProyecto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizarProyecto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("", jPanel1);

        tabPanel.addTab("Proyectos", jTabbedPane1);

        jLabel12.setText("Nombre:");

        jLabel13.setText("Correo:");

        btnAgregarUsuario.setText("Agregar");
        btnAgregarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUsuarioActionPerformed(evt);
            }
        });

        btnEditarUsuario.setText("Editar");
        btnEditarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarUsuarioActionPerformed(evt);
            }
        });

        btnEliminarUsuario.setText("Eliminar");
        btnEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUsuarioActionPerformed(evt);
            }
        });

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Correo"
            }
        ));
        jScrollPane4.setViewportView(tablaUsuarios);

        ActualizarUsuario.setText("Actualizar");
        ActualizarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCorreoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                            .addComponent(txtNombreUsuario))
                        .addGap(87, 87, 87)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEditarUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ActualizarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(113, 113, 113))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtCorreoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAgregarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ActualizarUsuario)
                        .addGap(1, 1, 1)))
                .addComponent(btnEliminarUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("", jPanel3);

        tabPanel.addTab("Usuarios", jTabbedPane3);

        btnAgregarTarea.setText("Agregar");
        btnAgregarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTareaActionPerformed(evt);
            }
        });

        btnEditarTarea.setText("Editar");
        btnEditarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarTareaActionPerformed(evt);
            }
        });

        btnEliminarTarea.setText("Eliminar");
        btnEliminarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTareaActionPerformed(evt);
            }
        });

        btnActualizarTareas.setText("Actualizar");
        btnActualizarTareas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarTareasActionPerformed(evt);
            }
        });

        jLabel1.setText("Proyecto:");

        comboProyecto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Usuario:");

        jLabel3.setText("Descripcion:");

        jLabel4.setText("Fecha de Inicio:");

        jLabel5.setText("Fecha Fin:");

        jLabel6.setText("Estado:");

        comboEstadoTarea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Activo", "Inactivo" }));

        tablaTareas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Usuario", "Descripcion", "Proyecto", "Fecha Inicio", "Fecha Fin", "Estado", "Prioridad"
            }
        ));
        tablaTareas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaTareasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaTareas);

        jLabel7.setText("Prioridad:");

        comboPrioridadTarea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Importante", "Intermedio" }));

        jLabel14.setText("Nombre:");

        comboUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFechaInicioTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboEstadoTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(56, 56, 56)
                                        .addComponent(jLabel7))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(comboPrioridadTarea, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(txtFechaFinTarea)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(24, 24, 24))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDescripcionTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(comboUsuario, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtNombreTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(78, 78, 78)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnActualizarTareas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarTarea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditarTarea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregarTarea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarTarea)
                    .addComponent(jLabel1)
                    .addComponent(comboProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditarTarea)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(comboUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnEliminarTarea)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizarTareas))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescripcionTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtFechaInicioTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtFechaFinTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(comboPrioridadTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboEstadoTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("", jPanel2);

        tabPanel.addTab("Tareas", jTabbedPane2);

        getContentPane().add(tabPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDescripcionProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionProyectoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionProyectoActionPerformed

    private void btnAgregarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUsuarioActionPerformed
          String nombre = txtNombreUsuario.getText();
    String correo = txtCorreoUsuario.getText();

    Usuario u = new Usuario();
    u.setNombre(nombre);
    u.setCorreo(correo);

    if (servicio.registrarUsuario(u)) {
        JOptionPane.showMessageDialog(this, "Usuario registrado correctamente.");
        cargarUsuariosEnTabla();
        cargarComboUsuario();
        limpiarCampos();
    } else {
        JOptionPane.showMessageDialog(this, "Error al registrar.");
    }
    }//GEN-LAST:event_btnAgregarUsuarioActionPerformed

    private void btnEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUsuarioActionPerformed
        int fila = tablaUsuarios.getSelectedRow();
    if (fila != -1) {
        int id = (int) tablaUsuarios.getValueAt(fila, 0);
        if (servicio.eliminarUsuario(id)) {
            JOptionPane.showMessageDialog(this, "Usuario eliminado.");
            cargarUsuariosEnTabla();
        cargarComboUsuario();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Seleccione un usuario.");
    }
    }//GEN-LAST:event_btnEliminarUsuarioActionPerformed

    private void btnEditarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarUsuarioActionPerformed
       int fila = tablaUsuarios.getSelectedRow();
    if (fila != -1) {
        idUsuarioSeleccionado = Integer.parseInt(tablaUsuarios.getValueAt(fila, 0).toString());
        txtNombreUsuario.setText(tablaUsuarios.getValueAt(fila, 1).toString());
        txtCorreoUsuario.setText(tablaUsuarios.getValueAt(fila, 2).toString());
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un usuario para editar.");
    }
    }//GEN-LAST:event_btnEditarUsuarioActionPerformed

    private void ActualizarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarUsuarioActionPerformed
    String nombre = txtNombreUsuario.getText().trim();
    String correo = txtCorreoUsuario.getText().trim();

    if (idUsuarioSeleccionado == -1) {
        JOptionPane.showMessageDialog(this, "Primero selecciona un usuario para editar.");
        return;
    }

    if (nombre.isEmpty() || correo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Rellena todos los campos.");
        return;
    }

    Usuario u = new Usuario(idUsuarioSeleccionado, nombre, correo);
    UsuarioServicio servicio = new UsuarioServicio();
    servicio.actualizarUsuario(u);

    cargarUsuariosEnTabla();
        cargarComboUsuario(); // vuelve a mostrar los datos
    limpiarCamposUsuario();
    idUsuarioSeleccionado = -1;
    }//GEN-LAST:event_ActualizarUsuarioActionPerformed

    private void btnAgregarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTareaActionPerformed
      Tarea t = new Tarea();
    t.setNombre(txtNombreTarea.getText());
    t.setDescripcion(txtDescripcionTarea.getText());
    t.setFechaInicio(txtFechaInicioTarea.getText());
    t.setFechaFin(txtFechaFinTarea.getText());
    t.setEstado(comboEstadoTarea.getSelectedItem().toString());
    t.setPrioridad(comboPrioridadTarea.getSelectedItem().toString());

    // Obtener ID de usuario
    String nombreUsuario = comboUsuario.getSelectedItem().toString();
    for (Usuario u : listaUsuarios) {
        if (u.getNombre().equals(nombreUsuario)) {
            t.setIdUsuario(u.getId());
            break;
        }
    }

    // Obtener ID de proyecto
    String nombreProyecto = comboProyecto.getSelectedItem().toString();
    for (Proyecto p : listaProyectos) {
        if (p.getNombre().equals(nombreProyecto)) {
            t.setIdProyecto(p.getId());
            break;
        }
    }

    // Guardar tarea
    if (tareaServicio.registrarTarea(t)) {
        JOptionPane.showMessageDialog(this, "Tarea agregada correctamente.");
        cargarTablaTareas();  // Llama a este método para recargar la tabla
        limpiarCamposTarea(); // Limpiar los campos
    } else {
        JOptionPane.showMessageDialog(this, "Error al agregar tarea.");
    }
    }//GEN-LAST:event_btnAgregarTareaActionPerformed

    private void btnEliminarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTareaActionPerformed
        int fila = tablaTareas.getSelectedRow();
    if (fila != -1) {
        int id = (int) tablaTareas.getValueAt(fila, 0);
        if (tareaServicio.eliminarTarea(id)) {
            JOptionPane.showMessageDialog(this, "Tarea eliminada.");
            cargarUsuariosEnTabla();
        cargarComboUsuario();
            limpiarCamposTarea();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar tarea.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Seleccione una tarea.");
    }
    }//GEN-LAST:event_btnEliminarTareaActionPerformed

    private void btnEditarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarTareaActionPerformed
     int fila = tablaTareas.getSelectedRow();
    if (fila != -1) {
        int id = (int) tablaTareas.getValueAt(fila, 0);

        Tarea t = new Tarea();
        t.setId(id);
        t.setNombre(txtNombreTarea.getText());
        t.setDescripcion(txtDescripcionTarea.getText());
        t.setFechaInicio(txtFechaInicioTarea.getText());
        t.setFechaFin(txtFechaFinTarea.getText());
        t.setEstado(comboEstadoTarea.getSelectedItem().toString());
        t.setPrioridad(comboPrioridadTarea.getSelectedItem().toString());

        // ID usuario
        String nombreUsuario = comboUsuario.getSelectedItem().toString();
        for (Usuario u : listaUsuarios) {
            if (u.getNombre().equals(nombreUsuario)) {
                t.setIdUsuario(u.getId());
                break;
            }
        }

        // ID proyecto
        String nombreProyecto = comboProyecto.getSelectedItem().toString();
        for (Proyecto p : listaProyectos) {
            if (p.getNombre().equals(nombreProyecto)) {
                t.setIdProyecto(p.getId());
                break;
            }
        }

        if (tareaServicio.actualizarTarea(t)) {
            JOptionPane.showMessageDialog(this, "Tarea actualizada.");
            cargarUsuariosEnTabla();
        cargarComboUsuario();
            limpiarCamposTarea();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar tarea.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Seleccione una tarea.");
    }
    }//GEN-LAST:event_btnEditarTareaActionPerformed

    private void btnActualizarTareasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarTareasActionPerformed
           cargarUsuariosEnTabla();
        cargarComboUsuario();
           limpiarCamposTarea();
    }//GEN-LAST:event_btnActualizarTareasActionPerformed

    private void tablaTareasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaTareasMouseClicked
    int fila = tablaTareas.getSelectedRow();

    if (fila != -1) {
        // Cargar campos de texto con protección contra null
        Object nombreObj = tablaTareas.getValueAt(fila, 1);
        txtNombreTarea.setText(nombreObj != null ? nombreObj.toString() : "");

        Object descripcionObj = tablaTareas.getValueAt(fila, 2);
        txtDescripcionTarea.setText(descripcionObj != null ? descripcionObj.toString() : "");

        Object fechaInicioObj = tablaTareas.getValueAt(fila, 3);
        txtFechaInicioTarea.setText(fechaInicioObj != null ? fechaInicioObj.toString() : "");

        Object fechaFinObj = tablaTareas.getValueAt(fila, 4);
        txtFechaFinTarea.setText(fechaFinObj != null ? fechaFinObj.toString() : "");

        // Estado y Prioridad
        Object estadoObj = tablaTareas.getValueAt(fila, 5);
        int estadoSeleccionado = estadoObj != null ? Integer.parseInt(estadoObj.toString()) : 0;
        comboEstadoTarea.setSelectedIndex(estadoSeleccionado);

        Object prioridadObj = tablaTareas.getValueAt(fila, 6);
        int prioridadSeleccionada = prioridadObj != null ? Integer.parseInt(prioridadObj.toString()) : 0;
        comboPrioridadTarea.setSelectedIndex(prioridadSeleccionada);

        // Proyecto y Usuario
        Object idProyectoObj = tablaTareas.getValueAt(fila, 7);
        int idProyectoTabla = idProyectoObj != null ? Integer.parseInt(idProyectoObj.toString()) : -1;

        Object idUsuarioObj = tablaTareas.getValueAt(fila, 8);
        int idUsuarioTabla = idUsuarioObj != null ? Integer.parseInt(idUsuarioObj.toString()) : -1;

        for (Proyecto p : listaProyectos) {
            if (p.getId() == idProyectoTabla) {
                comboProyecto.setSelectedItem(p.getNombre());
                break;
            }
        }

        for (Usuario u : listaUsuarios) {
            if (u.getId() == idUsuarioTabla) {
                comboUsuario.setSelectedItem(u.getNombre());
                break;
            }
        }
    }
    }//GEN-LAST:event_tablaTareasMouseClicked

    private void btnAgregarProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProyectoActionPerformed
    if (!validarCamposProyecto()) {
           return; // no continua si hay error
       }

       Proyecto p = new Proyecto();
       p.setNombre(txtNombreProyecto.getText());
       p.setDescripcion(txtDescripcionProyecto.getText());
       p.setFechaInicio(txtInicioProyecto.getText());
       p.setFechaFin(txtFinProyecto.getText());

       ProyectoServicio servicio = new ProyectoServicio();
       if (servicio.registrarProyecto(p)) {
           JOptionPane.showMessageDialog(this, "Proyecto registrado correctamente.");
           cargarProyectosEnTabla();
        cargarComboProyecto();
           limpiarCamposProyecto();
       } else {
           JOptionPane.showMessageDialog(this, "Error al registrar proyecto.");
       }
    }//GEN-LAST:event_btnAgregarProyectoActionPerformed

    private void btnEditarProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProyectoActionPerformed
       if (filaSeleccionada != -1) {
        txtNombreProyecto.setText(tablaProyectos.getValueAt(filaSeleccionada, 1).toString());
        txtDescripcionProyecto.setText(tablaProyectos.getValueAt(filaSeleccionada, 2).toString());
        txtInicioProyecto.setText(tablaProyectos.getValueAt(filaSeleccionada, 3).toString());
        txtFinProyecto.setText(tablaProyectos.getValueAt(filaSeleccionada, 4).toString());
    } else {
        JOptionPane.showMessageDialog(this, "Seleccione un proyecto de la tabla primero.");
    }
    }//GEN-LAST:event_btnEditarProyectoActionPerformed

    private void btnEliminarProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProyectoActionPerformed
        int fila = tablaProyectos.getSelectedRow();
    if (fila != -1) {
        int id = (int) tablaProyectos.getValueAt(fila, 0);

        ProyectoServicio servicio = new ProyectoServicio();
        if (servicio.eliminarProyecto(id)) {
            JOptionPane.showMessageDialog(this, "Proyecto eliminado.");
            cargarProyectosEnTabla();
        cargarComboProyecto();
            limpiarCamposProyecto();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar proyecto.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Seleccione un proyecto.");
    }
    }//GEN-LAST:event_btnEliminarProyectoActionPerformed

    private void btnActualizarProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarProyectoActionPerformed
         // Validar si los campos no están vacíos
    if (txtNombreTarea.getText().trim().isEmpty() || txtDescripcionTarea.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.");
        return;
    }

    // Crear un nuevo objeto Tarea con los datos de los campos
    Tarea t = new Tarea();
    t.setId(Integer.parseInt(tablaTareas.getValueAt(tablaTareas.getSelectedRow(), 0).toString()));  // ID de la tarea
    t.setNombre(txtNombreTarea.getText());
    t.setDescripcion(txtDescripcionTarea.getText());
    t.setFechaInicio(txtFechaInicioTarea.getText());
    t.setFechaFin(txtFechaFinTarea.getText());
    t.setEstado(comboEstadoTarea.getSelectedItem().toString());
    t.setPrioridad(comboPrioridadTarea.getSelectedItem().toString());

    // Obtener ID de Usuario
    String nombreUsuario = comboUsuario.getSelectedItem().toString();
    for (Usuario u : listaUsuarios) {
        if (u.getNombre().equals(nombreUsuario)) {
            t.setIdUsuario(u.getId());
            break;
        }
    }

    // Obtener ID de Proyecto
    String nombreProyecto = comboProyecto.getSelectedItem().toString();
    for (Proyecto p : listaProyectos) {
        if (p.getNombre().equals(nombreProyecto)) {
            t.setIdProyecto(p.getId());
            break;
        }
    }

    // Actualizar tarea
    TareaServicio servicio = new TareaServicio();
    if (servicio.actualizarTarea(t)) {
        JOptionPane.showMessageDialog(this, "Tarea actualizada correctamente.");
        cargarTablaTareas();  // Recargar la tabla de tareas
        limpiarCamposTarea(); // Limpiar los campos
    } else {
        JOptionPane.showMessageDialog(this, "Error al actualizar la tarea.");
    }
    }//GEN-LAST:event_btnActualizarProyectoActionPerformed

    private void tablaProyectosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProyectosMouseClicked
    filaSeleccionada = tablaProyectos.getSelectedRow();
    }//GEN-LAST:event_tablaProyectosMouseClicked

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ActualizarUsuario;
    private javax.swing.JButton btnActualizarProyecto;
    private javax.swing.JButton btnActualizarTareas;
    private javax.swing.JButton btnAgregarProyecto;
    private javax.swing.JButton btnAgregarTarea;
    private javax.swing.JButton btnAgregarUsuario;
    private javax.swing.JButton btnEditarProyecto;
    private javax.swing.JButton btnEditarTarea;
    private javax.swing.JButton btnEditarUsuario;
    private javax.swing.JButton btnEliminarProyecto;
    private javax.swing.JButton btnEliminarTarea;
    private javax.swing.JButton btnEliminarUsuario;
    private javax.swing.JComboBox<String> comboEstadoTarea;
    private javax.swing.JComboBox<String> comboPrioridadTarea;
    private javax.swing.JComboBox<String> comboProyecto;
    private javax.swing.JComboBox<String> comboUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JTable tablaProyectos;
    private javax.swing.JTable tablaTareas;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField txtCorreoUsuario;
    private javax.swing.JTextField txtDescripcionProyecto;
    private javax.swing.JTextField txtDescripcionTarea;
    private javax.swing.JTextField txtFechaFinTarea;
    private javax.swing.JTextField txtFechaInicioTarea;
    private javax.swing.JTextField txtFinProyecto;
    private javax.swing.JTextField txtInicioProyecto;
    private javax.swing.JTextField txtNombreProyecto;
    private javax.swing.JTextField txtNombreTarea;
    private javax.swing.JTextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables

}
