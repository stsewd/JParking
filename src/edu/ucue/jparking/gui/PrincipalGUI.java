/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.gui;

//import static javafx.application.Platform.exit;

import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoAgregadoException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaAgregadoException;
import edu.ucue.jparking.srv.JP;
import edu.ucue.jparking.srv.JPInterface;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import edu.ucue.jparking.srv.excepciones.CampusInactivoException;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.srv.excepciones.NumeroParqueaderosNoDisponiblesException;
import edu.ucue.jparking.srv.excepciones.ParquaderoInactivoException;
import edu.ucue.jparking.srv.excepciones.UsuarioInactivoException;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.awt.datatransfer.*;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Franklin Lara
 * @author Santos Gallegos
 */
public class PrincipalGUI extends javax.swing.JFrame {

    JPInterface jp = JP.getInstance();

    /**
     * Creates new form PrincipalGUI
     */
    public PrincipalGUI() {
        
        //Establecer icono de aplicacion
        /*
        ImageIcon imgIcon = new javax.swing.ImageIcon(getClass().getResource("/edu/ucue/jparking/img/transport122.png"));
        this.setIconImage(imgIcon.getImage());
        */
        
        initComponents();        

        try {
            //Listar usuarios en tabla
            listarUsuarios();
        } catch (IOException | ClassNotFoundException | ObjectSizeException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
        
        //Cargar campus en combobox
        try{
            cargarCampusCB();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (ObjectSizeException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
        
        try {
            listarParqueaderos();
        } catch (CampusNoExistenteException | IllegalArgumentException  ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (ObjectSizeException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
        
        //Centrar ventana
        setLocationRelativeTo(null);
    }
    
    public void cargarCampusCB() throws IOException, ClassNotFoundException, ObjectSizeException{
        //Cargar parqueaderos en combo box
        CampusCB.removeAllItems();
        for(Campus c : jp.getCampus()){
            CampusCB.addItem(c.getNombre());
        }
    }
    
    public void listarUsuarios() throws IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException{                
        String tipoUsuario = (String) TipoUsuarioCB.getSelectedItem();
        Set<Usuario> usuarios = null;
        switch(tipoUsuario){
            case "Todos": {
                usuarios = jp.getUsuarios();
                break;
            }
            case "Estudiante":{
                usuarios = jp.getUsuarios(TipoUsuario.ESTUDIANTE);
                break;
            }
            case "Docente": {
                usuarios = jp.getUsuarios(TipoUsuario.DOCENTE);
                break;
            }
            case "Empleado": {
                usuarios = jp.getUsuarios(TipoUsuario.EMPLEADO);
                break;
            }
        }
        
        DefaultTableModel model = (DefaultTableModel) TablaUsuarios.getModel();
        
        //Borrar elementos anteriores
        for(int i = model.getRowCount() - 1; i >= 0 ; i--)
            model.removeRow(i);
        
        int n = 1;
        for(Usuario u : usuarios)
            model.addRow(new Object[]{n++, u.getCedula(), u.getApellidos()+ " " + u.getNombres()});
    }
    
    public void listarParqueaderos() throws CampusNoExistenteException, IOException, ClassNotFoundException, ObjectSizeException{

        String nombreCampus = (String) CampusCB.getSelectedItem();
        DefaultTableModel model = (DefaultTableModel) TablaParqueaderos.getModel();
        
        //Borrar elementos anteriores
        for(int i = model.getRowCount() - 1; i >= 0 ; i--)
            model.removeRow(i);
        
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            return;
        
        Set<Parqueadero> parqueaderos = jp.getParqueaderos(nombreCampus);

        int n = 1;
        for(Parqueadero p : parqueaderos)
            model.addRow(new Object[]{n++, p.getId(), p.getUbicacion(), p.getNumeroLugares(), p.getNumeroLugaresOcupados(), p.isActivo()});
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        CampusCB = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaParqueaderos = new javax.swing.JTable();
        jToolBar3 = new javax.swing.JToolBar();
        CrearParqueaderoBtn = new javax.swing.JButton();
        EliminarParqueaderoBtn = new javax.swing.JButton();
        ModificarParqueaderoBtn = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        CopyIDParqBtn = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        UsuariosParqBtn = new javax.swing.JButton();
        PuertasBtn = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaUsuarios = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        TipoUsuarioCB = new javax.swing.JComboBox();
        jToolBar1 = new javax.swing.JToolBar();
        VerBtn = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        CrearUsuarioBtn = new javax.swing.JButton();
        EliminarUsuarioBtn = new javax.swing.JButton();
        ModificarUsuarioBtn = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        CopyCedulaBtn = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        AgregarBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        CampusMenu = new javax.swing.JMenu();
        CrearCampusMenuItem = new javax.swing.JMenuItem();
        ModicarCampusMenuItem = new javax.swing.JMenuItem();
        EliminarCampusMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        AdministarPuertasMenuItem = new javax.swing.JMenuItem();
        AdministarPorterosMenuItem = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        SalirItem = new javax.swing.JMenuItem();
        UsuariosMenu = new javax.swing.JMenu();
        CrearUsuarioMenuItem = new javax.swing.JMenuItem();
        ModificarUsuarioMenuItem = new javax.swing.JMenuItem();
        EliminarUsuarioMenuItem = new javax.swing.JMenuItem();
        ParqueaderosMenu = new javax.swing.JMenu();
        CrearParqueaderoMenuItem = new javax.swing.JMenuItem();
        ModificarParqueaderoMenuItem = new javax.swing.JMenuItem();
        EliminarParqueaderoMenuItem = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        PuertasAccesoItem = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        UsuariosParqueaderoItem = new javax.swing.JMenuItem();
        PagosMenu = new javax.swing.JMenu();
        GenerarOrdenPagoItem = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        AutenticacionItem = new javax.swing.JMenuItem();
        RegistrosMenu = new javax.swing.JMenu();
        ListarRegistrosMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        CopiaSeguridadItem = new javax.swing.JMenuItem();
        restaurarBackUpItem = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        cambiarClaveItem = new javax.swing.JMenuItem();
        AyudaMenu = new javax.swing.JMenu();
        AcercaDeMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JParking - Administrador de parqueaderos");
        setIconImage((new javax.swing.ImageIcon(getClass().getResource("/edu/ucue/jparking/img/transport122.png"))).getImage());
        setMinimumSize(new java.awt.Dimension(800, 430));

        jSplitPane1.setDividerLocation(350);
        jSplitPane1.setDividerSize(15);
        jSplitPane1.setAutoscrolls(true);

        jLabel1.setText("Campus:");

        CampusCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampusCBActionPerformed(evt);
            }
        });

        TablaParqueaderos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Id", "Ubicación", "Espacios", "Num. Usuarios", "Activo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaParqueaderos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TablaParqueaderos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TablaParqueaderos);
        if (TablaParqueaderos.getColumnModel().getColumnCount() > 0) {
            TablaParqueaderos.getColumnModel().getColumn(0).setMinWidth(30);
            TablaParqueaderos.getColumnModel().getColumn(0).setPreferredWidth(30);
            TablaParqueaderos.getColumnModel().getColumn(0).setMaxWidth(30);
            TablaParqueaderos.getColumnModel().getColumn(5).setMinWidth(60);
            TablaParqueaderos.getColumnModel().getColumn(5).setPreferredWidth(60);
            TablaParqueaderos.getColumnModel().getColumn(5).setMaxWidth(60);
        }

        jToolBar3.setFloatable(false);
        jToolBar3.setRollover(true);

        CrearParqueaderoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/ucue/jparking/img/add139.png"))); // NOI18N
        CrearParqueaderoBtn.setToolTipText("Crear nuevo parqueadero");
        CrearParqueaderoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearParqueaderoBtnActionPerformed(evt);
            }
        });
        jToolBar3.add(CrearParqueaderoBtn);

        EliminarParqueaderoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/ucue/jparking/img/close7.png"))); // NOI18N
        EliminarParqueaderoBtn.setToolTipText("Eliminar parqueadero");
        EliminarParqueaderoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarParqueaderoBtnActionPerformed(evt);
            }
        });
        jToolBar3.add(EliminarParqueaderoBtn);

        ModificarParqueaderoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/ucue/jparking/img/pencil41.png"))); // NOI18N
        ModificarParqueaderoBtn.setToolTipText("Modificar parqueadero");
        ModificarParqueaderoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarParqueaderoBtnActionPerformed(evt);
            }
        });
        jToolBar3.add(ModificarParqueaderoBtn);
        jToolBar3.add(jSeparator9);

        CopyIDParqBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/ucue/jparking/img/copy12.png"))); // NOI18N
        CopyIDParqBtn.setToolTipText("Copiar id de parqueadero al portapepeles");
        CopyIDParqBtn.setFocusable(false);
        CopyIDParqBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CopyIDParqBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CopyIDParqBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CopyIDParqBtnActionPerformed(evt);
            }
        });
        jToolBar3.add(CopyIDParqBtn);
        jToolBar3.add(jSeparator7);

        UsuariosParqBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/ucue/jparking/img/user7.png"))); // NOI18N
        UsuariosParqBtn.setToolTipText("Agregar/Eliminar usuarios a parqueadero (ctrl + u)");
        UsuariosParqBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuariosParqBtnActionPerformed(evt);
            }
        });
        jToolBar3.add(UsuariosParqBtn);

        PuertasBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/ucue/jparking/img/barriers.png"))); // NOI18N
        PuertasBtn.setToolTipText("Agregar/Eliminar puertas de acceso a parqueadero (ctrl + p)");
        PuertasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PuertasBtnActionPerformed(evt);
            }
        });
        jToolBar3.add(PuertasBtn);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CampusCB, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jToolBar3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampusCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        jTabbedPane1.addTab("Parqueaderos", jPanel2);

        jSplitPane1.setRightComponent(jTabbedPane1);

        TablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Cedula", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaUsuarios.setMaximumSize(new java.awt.Dimension(2147483647, 16));
        TablaUsuarios.setMinimumSize(new java.awt.Dimension(60, 16));
        TablaUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TablaUsuarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(TablaUsuarios);
        if (TablaUsuarios.getColumnModel().getColumnCount() > 0) {
            TablaUsuarios.getColumnModel().getColumn(0).setMinWidth(30);
            TablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(30);
            TablaUsuarios.getColumnModel().getColumn(0).setMaxWidth(30);
            TablaUsuarios.getColumnModel().getColumn(1).setMinWidth(105);
            TablaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(105);
            TablaUsuarios.getColumnModel().getColumn(1).setMaxWidth(105);
        }

        jLabel4.setText("Tipo Usuario:");

        TipoUsuarioCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Docente", "Empleado", "Estudiante" }));
        TipoUsuarioCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoUsuarioCBActionPerformed(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        VerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/ucue/jparking/img/more16.png"))); // NOI18N
        VerBtn.setToolTipText("Ver usuario");
        VerBtn.setFocusable(false);
        VerBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        VerBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        VerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(VerBtn);
        jToolBar1.add(jSeparator8);

        CrearUsuarioBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/ucue/jparking/img/add139.png"))); // NOI18N
        CrearUsuarioBtn.setToolTipText("Crear nuevo usuario");
        CrearUsuarioBtn.setFocusable(false);
        CrearUsuarioBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CrearUsuarioBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CrearUsuarioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearUsuarioBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(CrearUsuarioBtn);

        EliminarUsuarioBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/ucue/jparking/img/close7.png"))); // NOI18N
        EliminarUsuarioBtn.setToolTipText("Eliminar usuario");
        EliminarUsuarioBtn.setFocusable(false);
        EliminarUsuarioBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EliminarUsuarioBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EliminarUsuarioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarUsuarioBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(EliminarUsuarioBtn);

        ModificarUsuarioBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/ucue/jparking/img/pencil41.png"))); // NOI18N
        ModificarUsuarioBtn.setToolTipText("Modificar usuario");
        ModificarUsuarioBtn.setFocusable(false);
        ModificarUsuarioBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ModificarUsuarioBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ModificarUsuarioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarUsuarioBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(ModificarUsuarioBtn);
        jToolBar1.add(jSeparator3);

        CopyCedulaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/ucue/jparking/img/copy12.png"))); // NOI18N
        CopyCedulaBtn.setToolTipText("Copiar cédula de usuario al portapepeles");
        CopyCedulaBtn.setFocusable(false);
        CopyCedulaBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CopyCedulaBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CopyCedulaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CopyCedulaBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(CopyCedulaBtn);
        jToolBar1.add(jSeparator2);

        AgregarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/ucue/jparking/img/keyboard53.png"))); // NOI18N
        AgregarBtn.setToolTipText("Agregar usuario a parqueadero");
        AgregarBtn.setFocusable(false);
        AgregarBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AgregarBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AgregarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(AgregarBtn);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(TipoUsuarioCB, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TipoUsuarioCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Usuarios", jPanel1);

        jSplitPane1.setLeftComponent(jTabbedPane2);

        CampusMenu.setMnemonic('c');
        CampusMenu.setText("Campus");

        CrearCampusMenuItem.setText("Crear Campus");
        CrearCampusMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearCampusMenuItemActionPerformed(evt);
            }
        });
        CampusMenu.add(CrearCampusMenuItem);

        ModicarCampusMenuItem.setText("Editar Campus");
        ModicarCampusMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModicarCampusMenuItemActionPerformed(evt);
            }
        });
        CampusMenu.add(ModicarCampusMenuItem);

        EliminarCampusMenuItem.setText("Eliminar Campus");
        EliminarCampusMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarCampusMenuItemActionPerformed(evt);
            }
        });
        CampusMenu.add(EliminarCampusMenuItem);
        CampusMenu.add(jSeparator1);

        AdministarPuertasMenuItem.setText("Administrar Puertas");
        AdministarPuertasMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdministarPuertasMenuItemActionPerformed(evt);
            }
        });
        CampusMenu.add(AdministarPuertasMenuItem);

        AdministarPorterosMenuItem.setText("Administrar Porteros");
        AdministarPorterosMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdministarPorterosMenuItemActionPerformed(evt);
            }
        });
        CampusMenu.add(AdministarPorterosMenuItem);
        CampusMenu.add(jSeparator6);

        SalirItem.setText("Salir");
        SalirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirItemActionPerformed(evt);
            }
        });
        CampusMenu.add(SalirItem);

        jMenuBar1.add(CampusMenu);

        UsuariosMenu.setMnemonic('u');
        UsuariosMenu.setText("Usuarios");

        CrearUsuarioMenuItem.setText("Crear Usuario");
        CrearUsuarioMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearUsuarioMenuItemActionPerformed(evt);
            }
        });
        UsuariosMenu.add(CrearUsuarioMenuItem);

        ModificarUsuarioMenuItem.setText("Modificar Usuario");
        ModificarUsuarioMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarUsuarioMenuItemActionPerformed(evt);
            }
        });
        UsuariosMenu.add(ModificarUsuarioMenuItem);

        EliminarUsuarioMenuItem.setText("Eliminar  Usuario");
        EliminarUsuarioMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarUsuarioMenuItemActionPerformed(evt);
            }
        });
        UsuariosMenu.add(EliminarUsuarioMenuItem);

        jMenuBar1.add(UsuariosMenu);

        ParqueaderosMenu.setMnemonic('p');
        ParqueaderosMenu.setText("Parqueaderos");

        CrearParqueaderoMenuItem.setText("Crear Parqueadero");
        CrearParqueaderoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearParqueaderoMenuItemActionPerformed(evt);
            }
        });
        ParqueaderosMenu.add(CrearParqueaderoMenuItem);

        ModificarParqueaderoMenuItem.setText("Modificar Parqueadero");
        ModificarParqueaderoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarParqueaderoMenuItemActionPerformed(evt);
            }
        });
        ParqueaderosMenu.add(ModificarParqueaderoMenuItem);

        EliminarParqueaderoMenuItem.setText("Eliminar Parqueadero");
        EliminarParqueaderoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarParqueaderoMenuItemActionPerformed(evt);
            }
        });
        ParqueaderosMenu.add(EliminarParqueaderoMenuItem);
        ParqueaderosMenu.add(jSeparator4);

        PuertasAccesoItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        PuertasAccesoItem.setText("Puertas Acceso");
        PuertasAccesoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PuertasAccesoItemActionPerformed(evt);
            }
        });
        ParqueaderosMenu.add(PuertasAccesoItem);
        ParqueaderosMenu.add(jSeparator5);

        UsuariosParqueaderoItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        UsuariosParqueaderoItem.setText("Usuarios Paqueadero");
        UsuariosParqueaderoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuariosParqueaderoItemActionPerformed(evt);
            }
        });
        ParqueaderosMenu.add(UsuariosParqueaderoItem);

        jMenuBar1.add(ParqueaderosMenu);

        PagosMenu.setMnemonic('g');
        PagosMenu.setText("Pagos");

        GenerarOrdenPagoItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        GenerarOrdenPagoItem.setText("Generar Orden de Pago");
        GenerarOrdenPagoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarOrdenPagoItemActionPerformed(evt);
            }
        });
        PagosMenu.add(GenerarOrdenPagoItem);

        jMenuItem1.setText("Listar ordenes de pago");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        PagosMenu.add(jMenuItem1);

        jMenuBar1.add(PagosMenu);

        jMenu1.setMnemonic('a');
        jMenu1.setText("Autenticar");

        AutenticacionItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        AutenticacionItem.setText("Autenticación");
        AutenticacionItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AutenticacionItemActionPerformed(evt);
            }
        });
        jMenu1.add(AutenticacionItem);

        jMenuBar1.add(jMenu1);

        RegistrosMenu.setMnemonic('r');
        RegistrosMenu.setText("Registros");

        ListarRegistrosMenuItem.setText("Listar registros");
        ListarRegistrosMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListarRegistrosMenuItemActionPerformed(evt);
            }
        });
        RegistrosMenu.add(ListarRegistrosMenuItem);

        jMenuBar1.add(RegistrosMenu);

        jMenu2.setMnemonic('h');
        jMenu2.setText("Herramientas");

        CopiaSeguridadItem.setText("Crear copia de seguridad");
        CopiaSeguridadItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CopiaSeguridadItemActionPerformed(evt);
            }
        });
        jMenu2.add(CopiaSeguridadItem);

        restaurarBackUpItem.setText("Restaurar copia de seguridad");
        restaurarBackUpItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restaurarBackUpItemActionPerformed(evt);
            }
        });
        jMenu2.add(restaurarBackUpItem);
        jMenu2.add(jSeparator10);

        cambiarClaveItem.setText("Cambiar contraseña");
        cambiarClaveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarClaveItemActionPerformed(evt);
            }
        });
        jMenu2.add(cambiarClaveItem);

        jMenuBar1.add(jMenu2);

        AyudaMenu.setMnemonic('y');
        AyudaMenu.setText("Ayuda");

        AcercaDeMenuItem.setText("Acerca de");
        AcercaDeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcercaDeMenuItemActionPerformed(evt);
            }
        });
        AyudaMenu.add(AcercaDeMenuItem);

        jMenuBar1.add(AyudaMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EliminarUsuarioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarUsuarioMenuItemActionPerformed
        // TODO addRegistro your handling code here:
        EliminarUsuarioGUI eliminarUsuarioGUI = new EliminarUsuarioGUI(this, true);
        eliminarUsuarioGUI.setLocationRelativeTo(this);
        eliminarUsuarioGUI.setVisible(true);
    }//GEN-LAST:event_EliminarUsuarioMenuItemActionPerformed

    private void CrearParqueaderoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearParqueaderoMenuItemActionPerformed
        // TODO addRegistro your handling code here:
        String campus = (String) CampusCB.getSelectedItem();
        if(campus == null || campus.trim().length() == 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un campus.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        CrearParqueaderoGUI crearParqueaderoGUI = new CrearParqueaderoGUI(this, true);
        crearParqueaderoGUI.setLocationRelativeTo(this);
        crearParqueaderoGUI.CargarDatos(campus);
        crearParqueaderoGUI.setVisible(true);
        
    }//GEN-LAST:event_CrearParqueaderoMenuItemActionPerformed

    private void EliminarParqueaderoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarParqueaderoMenuItemActionPerformed
        // TODO addRegistro your handling code here:
        String campus = (String) CampusCB.getSelectedItem();
        if(campus == null || campus.trim().length() == 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un campus.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        EliminarParqueaderoGUI eliminarParqueaderoGUI = new EliminarParqueaderoGUI(this, true);
        eliminarParqueaderoGUI.setLocationRelativeTo(this);
        eliminarParqueaderoGUI.CargarDatos(campus);
        eliminarParqueaderoGUI.setVisible(true);
    }//GEN-LAST:event_EliminarParqueaderoMenuItemActionPerformed

    private void AcercaDeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcercaDeMenuItemActionPerformed
        // TODO addRegistro your handling code here:
        AcercaDeGUI ad = new AcercaDeGUI(this, true);
        ad.setLocationRelativeTo(this);
        ad.setVisible(true);
    }//GEN-LAST:event_AcercaDeMenuItemActionPerformed

    private void EliminarParqueaderoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarParqueaderoBtnActionPerformed
        // TODO addRegistro your handling code here:
        int row = TablaParqueaderos.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un parqueadero.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        String campus = (String) CampusCB.getSelectedItem();
        if(campus == null || campus.trim().length() == 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un campus.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        String idParqueadero = (String) TablaParqueaderos.getValueAt(row, 1);
        try {
            //jp.ComprobarCampus(campus);
            EliminarParqueaderoGUI eliminarParqueaderoGUI = new EliminarParqueaderoGUI(this, true);
            eliminarParqueaderoGUI.cargarDatos(idParqueadero, campus);
            eliminarParqueaderoGUI.setLocationRelativeTo(this);
            eliminarParqueaderoGUI.setVisible(true);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }  catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado pasó.", "Mensaje", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_EliminarParqueaderoBtnActionPerformed

    private void CrearParqueaderoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearParqueaderoBtnActionPerformed
        // TODO addRegistro your handling code here:
        String campus = (String) CampusCB.getSelectedItem();
        if(campus == null || campus.trim().length() == 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un campus.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        CrearParqueaderoGUI crearParqueaderoGUI = new CrearParqueaderoGUI(this, true);
        crearParqueaderoGUI.setLocationRelativeTo(this);
        crearParqueaderoGUI.CargarDatos(campus);
        crearParqueaderoGUI.setVisible(true);
        
    }//GEN-LAST:event_CrearParqueaderoBtnActionPerformed

    private void CrearUsuarioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearUsuarioMenuItemActionPerformed
        // TODO addRegistro your handling code here:
        CrearUsuarioGUI crearUsuarioGUI = new CrearUsuarioGUI(this, true);
        crearUsuarioGUI.setLocationRelativeTo(this);
        crearUsuarioGUI.setVisible(true);
    }//GEN-LAST:event_CrearUsuarioMenuItemActionPerformed

    private void ModificarUsuarioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarUsuarioMenuItemActionPerformed
        // TODO addRegistro your handling code here:
        EditarUsuarioGUI editarUsuarioGUI = new EditarUsuarioGUI(this, true);
        editarUsuarioGUI.setLocationRelativeTo(this);
        editarUsuarioGUI.setVisible(true);
    }//GEN-LAST:event_ModificarUsuarioMenuItemActionPerformed
    
    private void ListarRegistrosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarRegistrosMenuItemActionPerformed
        RegistrosGUI registrosGUI = new RegistrosGUI(this, true);
        registrosGUI.setLocationRelativeTo(this);
        registrosGUI.setVisible(true);
    }//GEN-LAST:event_ListarRegistrosMenuItemActionPerformed

    private void CampusCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampusCBActionPerformed
        try {
            listarParqueaderos();
        } catch (CampusNoExistenteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (ObjectSizeException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_CampusCBActionPerformed

    private void SalirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_SalirItemActionPerformed

    private void EliminarCampusMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarCampusMenuItemActionPerformed
        // TODO addRegistro your handling code here:
        String nombreCampus = (String) CampusCB.getSelectedItem();
        if(nombreCampus==null){
            JOptionPane.showMessageDialog(rootPane, "No se a selecionado ningun campus", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        try {
            jp.delCampus((String) CampusCB.getSelectedItem());
            JOptionPane.showMessageDialog(rootPane, "Campus eliminado exitosamente.", "Mensaje", JOptionPane.OK_OPTION);
        } catch (CampusNoExistenteException | IllegalArgumentException | ParqueaderoNoExistenteException | UsuarioNoExistenteException | UsuarioNoAgregadoException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }catch(ClassNotFoundException | FileNotFoundException | ObjectSizeException ex){
            JOptionPane.showMessageDialog(rootPane, "Se produjo un error al leer o guardar en los datos.", "Mensaje", JOptionPane.OK_OPTION);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado paso.", "Mensaje", JOptionPane.OK_OPTION);
        }
        
        try{
            cargarCampusCB();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (ObjectSizeException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_EliminarCampusMenuItemActionPerformed

    private void ModicarCampusMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModicarCampusMenuItemActionPerformed
        // TODO addRegistro your handling code here:
        String nombreCampus = (String) CampusCB.getSelectedItem();
        if(nombreCampus==null){
            JOptionPane.showMessageDialog(rootPane, "No se a selecionado ningun campus", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        EditarCampusGUI editarCampusGUI = new EditarCampusGUI(this, true);
        editarCampusGUI.setLocationRelativeTo(this);
        try {
            editarCampusGUI.CargarDatos((String) CampusCB.getSelectedItem());
            editarCampusGUI.setVisible(true);
        } catch (CampusNoExistenteException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado pasó.", "Mensaje", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_ModicarCampusMenuItemActionPerformed

    private void CrearCampusMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearCampusMenuItemActionPerformed
        // TODO addRegistro your handling code here:
        CrearCampusGUI crearCampusGUI = new CrearCampusGUI(this, true);
        crearCampusGUI.setLocationRelativeTo(this);
        crearCampusGUI.setVisible(true);
    }//GEN-LAST:event_CrearCampusMenuItemActionPerformed

    private void AdministarPuertasMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdministarPuertasMenuItemActionPerformed
        // TODO addRegistro your handling code here:
        AdministrarPuertasGUI administrarPuertasGUI = new AdministrarPuertasGUI(this, true);
        administrarPuertasGUI.setLocationRelativeTo(this);
        administrarPuertasGUI.setVisible(true);
    }//GEN-LAST:event_AdministarPuertasMenuItemActionPerformed

    private void AdministarPorterosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdministarPorterosMenuItemActionPerformed
        // TODO addRegistro your handling code here:
        AdministarPorterosGUI administarPorterosGUI = new AdministarPorterosGUI(this, true);
        administarPorterosGUI.setLocationRelativeTo(this);
        administarPorterosGUI.setVisible(true);
    }//GEN-LAST:event_AdministarPorterosMenuItemActionPerformed

    private void ModificarParqueaderoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarParqueaderoBtnActionPerformed
        // TODO addRegistro your handling code here:
        String campus = (String) CampusCB.getSelectedItem();
        if(campus == null || campus.trim().length() == 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un campus.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        int row = TablaParqueaderos.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un parqueadero.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        String idParqueadero = (String) TablaParqueaderos.getValueAt(row, 1);
        try {
            EditarParqueaderoGUI editarParqueaderoGUI = new EditarParqueaderoGUI(this, true);
            editarParqueaderoGUI.setLocationRelativeTo(this);
            editarParqueaderoGUI.cargarDatos(idParqueadero, campus);
            editarParqueaderoGUI.habilitarCampos();
            editarParqueaderoGUI.setVisible(true);
        } catch (ParqueaderoNoExistenteException | CampusNoExistenteException | IllegalArgumentException | CodigoNoValidoException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }  catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado pasó", "Mensaje", JOptionPane.OK_OPTION);
        }
        
    }//GEN-LAST:event_ModificarParqueaderoBtnActionPerformed

    private void ModificarParqueaderoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarParqueaderoMenuItemActionPerformed
        // TODO addRegistro your handling code here:
        int row = TablaParqueaderos.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un parqueadero.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        String idParqueadero = (String) TablaParqueaderos.getValueAt(row, 1);
        String nombreCampus = (String) CampusCB.getSelectedItem();
        try {
            EditarParqueaderoGUI editarParqueaderoGUI = new EditarParqueaderoGUI(this, true);
            editarParqueaderoGUI.setLocationRelativeTo(this);
            editarParqueaderoGUI.cargarDatos(idParqueadero, nombreCampus);
            editarParqueaderoGUI.setVisible(true);
        } catch (ParqueaderoNoExistenteException | CampusNoExistenteException | IllegalArgumentException | CodigoNoValidoException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado pasó", "Mensaje", JOptionPane.OK_OPTION);
        }
        
    }//GEN-LAST:event_ModificarParqueaderoMenuItemActionPerformed

    private void PuertasAccesoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PuertasAccesoItemActionPerformed
        // TODO addRegistro your handling code here:
        puertasAccesoParqueadero();
    }//GEN-LAST:event_PuertasAccesoItemActionPerformed

    public void puertasAccesoParqueadero(){
        String nombreCampus = (String) CampusCB.getSelectedItem();
        if(nombreCampus==null){
            JOptionPane.showMessageDialog(rootPane, "No se a selecionado ningun campus", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        int row = TablaParqueaderos.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un parqueadero.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        String idParqueadero = (String) TablaParqueaderos.getValueAt(row, 1);
        AdministrarPuertaAccesoGUI puertaAcceso = new AdministrarPuertaAccesoGUI(this, true);
        puertaAcceso.setLocationRelativeTo(this);
        try {
            puertaAcceso.CargarDatos(nombreCampus, idParqueadero);
            puertaAcceso.setVisible(true);
        } catch (ParqueaderoNoExistenteException | CodigoNoValidoException | IllegalArgumentException | CampusNoExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado pasó", "Mensaje", JOptionPane.OK_OPTION);
        }
    }
    
    private void UsuariosParqueaderoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuariosParqueaderoItemActionPerformed
        // TODO addRegistro your handling code here:      
        usuariosParqueadero();
    }//GEN-LAST:event_UsuariosParqueaderoItemActionPerformed

    public void usuariosParqueadero(){
        String nombreCampus = (String) CampusCB.getSelectedItem();
        if(nombreCampus==null){
            JOptionPane.showMessageDialog(rootPane, "No se a selecionado ningun campus", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        int row = TablaParqueaderos.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un parqueadero.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        String idParqueadero = (String) TablaParqueaderos.getValueAt(row, 1);
        AdministrarUsuariosParqueaderoGUI usuariosParqueadero = new AdministrarUsuariosParqueaderoGUI(this, true);
        usuariosParqueadero.setLocationRelativeTo(this);
        try {
            usuariosParqueadero.CargarDatos(nombreCampus, idParqueadero);
            usuariosParqueadero.setVisible(true);
        } catch (CampusInactivoException | ParquaderoInactivoException | ParqueaderoNoExistenteException | CampusNoExistenteException | CodigoNoValidoException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado pasó.", "Mensaje", JOptionPane.OK_OPTION);
        }
    }
    
    private void GenerarOrdenPagoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarOrdenPagoItemActionPerformed
        // TODO addRegistro your handling code here:
        OrdenPagoGUI opgui = new OrdenPagoGUI(this, true);
        opgui.setLocationRelativeTo(this);
        opgui.setVisible(true);
    }//GEN-LAST:event_GenerarOrdenPagoItemActionPerformed

    private void PuertasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PuertasBtnActionPerformed
        // TODO addRegistro your handling code here:
        puertasAccesoParqueadero();
    }//GEN-LAST:event_PuertasBtnActionPerformed

    private void UsuariosParqBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuariosParqBtnActionPerformed
        // TODO addRegistro your handling code here:
        usuariosParqueadero();
    }//GEN-LAST:event_UsuariosParqBtnActionPerformed

    private void AutenticacionItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AutenticacionItemActionPerformed
        // TODO addRegistro your handling code here:
        AutenticarGUI autenticarGUI = new AutenticarGUI(this, true);
        autenticarGUI.setLocationRelativeTo(this);
        autenticarGUI.setVisible(true);
    }//GEN-LAST:event_AutenticacionItemActionPerformed

    private void CopyIDParqBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CopyIDParqBtnActionPerformed
        // TODO addRegistro your handling code here:
        int row = TablaParqueaderos.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un parqueadero.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        String idParqueadero = (String) TablaParqueaderos.getValueAt(row, 1);
        StringSelection stringSelection = new StringSelection(idParqueadero);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
    }//GEN-LAST:event_CopyIDParqBtnActionPerformed

    private void AgregarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarBtnActionPerformed
        // TODO addRegistro your handling code here:
        String campus = (String) CampusCB.getSelectedItem();
        int row = TablaParqueaderos.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un parqueadero.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }

        String idParqueadero = (String) TablaParqueaderos.getValueAt(row, 1);

        row = TablaUsuarios.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un usuario.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        String cedula = (String) TablaUsuarios.getValueAt(row, 1);


        try {
            jp.addUsuario(campus, idParqueadero, cedula);
            JOptionPane.showMessageDialog(rootPane, "Usuario " + cedula + " agregado a paqueadero " + idParqueadero + ".", "Mensaje", JOptionPane.OK_OPTION);
            listarParqueaderos();
        } catch(ClassNotFoundException | FileNotFoundException | ObjectSizeException ex){
            JOptionPane.showMessageDialog(rootPane, "Se produjo un error al leer o guardar en los datos.", "Mensaje", JOptionPane.OK_OPTION);
        } catch (CedulaNoValidaException | CampusInactivoException | CampusNoExistenteException | UsuarioInactivoException | NumeroParqueaderosNoDisponiblesException | CodigoNoValidoException | IllegalArgumentException | ParqueaderoNoExistenteException | UsuarioYaAgregadoException | UsuarioNoExistenteException | ParquaderoInactivoException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado pasó.", "Mensaje", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_AgregarBtnActionPerformed

    private void CopyCedulaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CopyCedulaBtnActionPerformed
        // TODO addRegistro your handling code here:
        int row = TablaUsuarios.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un usuario.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        String cedula = (String) TablaUsuarios.getValueAt(row, 1);
        StringSelection stringSelection = new StringSelection(cedula);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
    }//GEN-LAST:event_CopyCedulaBtnActionPerformed

    private void ModificarUsuarioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarUsuarioBtnActionPerformed
        int row = TablaUsuarios.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un usuario.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        String cedula = (String) TablaUsuarios.getValueAt(row, 1);

        EditarUsuarioGUI editarUsuarioGUI = new EditarUsuarioGUI(this, true);
        editarUsuarioGUI.setLocationRelativeTo(this);

        try {
            editarUsuarioGUI.cargarDatos(cedula);
            editarUsuarioGUI.habilitarCampos();
            editarUsuarioGUI.setVisible(true);
        } catch(ClassNotFoundException | FileNotFoundException | ObjectSizeException ex){
            JOptionPane.showMessageDialog(rootPane, "Se produjo un error al leer o guardar en los datos.", "Mensaje", JOptionPane.OK_OPTION);
        } catch (IllegalArgumentException | UsuarioNoExistenteException | CedulaNoValidaException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado pasó.", "Mensaje", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_ModificarUsuarioBtnActionPerformed

    private void EliminarUsuarioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarUsuarioBtnActionPerformed
        // TODO addRegistro your handling code here:
        int row = TablaUsuarios.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un usuario.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        String cedula = (String) TablaUsuarios.getValueAt(row, 1);
        EliminarUsuarioGUI eliminarUsuarioGUI = new EliminarUsuarioGUI(this, true);
        eliminarUsuarioGUI.cargarDatos(cedula);
        eliminarUsuarioGUI.setLocationRelativeTo(this);
        eliminarUsuarioGUI.setVisible(true);
    }//GEN-LAST:event_EliminarUsuarioBtnActionPerformed

    private void CrearUsuarioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearUsuarioBtnActionPerformed
        CrearUsuarioGUI crearUsuarioGUI = new CrearUsuarioGUI(this, true);
        crearUsuarioGUI.setLocationRelativeTo(this);
        crearUsuarioGUI.setVisible(true);
    }//GEN-LAST:event_CrearUsuarioBtnActionPerformed

    private void VerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerBtnActionPerformed
        // TODO addRegistro your handling code here:
        int row = TablaUsuarios.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un usuario.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        String cedula = (String) TablaUsuarios.getValueAt(row, 1);
        UsuarioGUI usuarioGUI = new UsuarioGUI(this, true);

        try {
            usuarioGUI.cargarDatos(cedula);
            usuarioGUI.setLocationRelativeTo(this);
            usuarioGUI.setVisible(true);
        } catch(ClassNotFoundException | FileNotFoundException | ObjectSizeException ex){
            JOptionPane.showMessageDialog(rootPane, "Se produjo un error al leer o guardar en los datos.", "Mensaje", JOptionPane.OK_OPTION);
        } catch (UsuarioNoExistenteException | CedulaNoValidaException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado pasó.", "Mensaje", JOptionPane.OK_OPTION);
        }

    }//GEN-LAST:event_VerBtnActionPerformed

    private void TipoUsuarioCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoUsuarioCBActionPerformed
        try {
            listarUsuarios();
        } catch (IOException | ClassNotFoundException | ObjectSizeException ex) {
        }
    }//GEN-LAST:event_TipoUsuarioCBActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        OrdenesPagoGUI opgui = new OrdenesPagoGUI(this, true);
        opgui.setLocationRelativeTo(this);
        opgui.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void CopiaSeguridadItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CopiaSeguridadItemActionPerformed
        
        FileNameExtensionFilter filtroB = new FileNameExtensionFilter("*.DAT", "dat");
        int ax = JOptionPane.showConfirmDialog(null, "Desea hacer un respaldo de su informacion?", "Alerta!", JOptionPane.YES_NO_OPTION);
        if(ax == JOptionPane.YES_OPTION){
            String fileName = "data";
         
            //Use the makeZip se utilia para crear un archivo de tipo .zip.
            File file = new File(fileName);
            if(file.exists()){
                try {
                    jp.makeZip(fileName);
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileFilter(filtroB);
                    int opcion = fileChooser.showSaveDialog(this);
                    if(opcion == JFileChooser.APPROVE_OPTION){
                        File directorio = fileChooser.getSelectedFile();
                        String destino = directorio.getAbsolutePath();
                        jp.generarClavesRSA(directorio.toPath());
                    }
                    JOptionPane.showMessageDialog(rootPane, "Su backup se ha generado exitosamente", "Mensaje", JOptionPane.OK_OPTION);
                }
                catch (Exception  e) {
                    JOptionPane.showMessageDialog(rootPane,"Algo inesperado pasó", "Error", JOptionPane.OK_OPTION);
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, "Ud no tiene la carpeta de datos", "Mensaje", JOptionPane.OK_OPTION);
          }
        }
        //else if(ax == JOptionPane.NO_OPTION)
          //  this.setVisible(false);
        
        
    }//GEN-LAST:event_CopiaSeguridadItemActionPerformed

    private FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.ZIP", "zip");
    private void restaurarBackUpItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restaurarBackUpItemActionPerformed
        FileNameExtensionFilter filtroB = new FileNameExtensionFilter("*.DAT", "dat");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filtro);
        int opcion = fileChooser.showOpenDialog(this);
        if(opcion==JFileChooser.APPROVE_OPTION){
            File archivoSeleccionado = fileChooser.getSelectedFile();
            String url = archivoSeleccionado.getAbsolutePath();
            String nombreArchivo = archivoSeleccionado.getName();
            System.out.println(nombreArchivo.substring(0, 12));
            if(!nombreArchivo.substring(0, 12).equals("dataJparking")){
                JOptionPane.showMessageDialog(fileChooser, "El archivo que escogio no corresponde \n"
                        + "a un backup de datos del programa", url, JOptionPane.OK_OPTION);
            
            }else{
            fileChooser.setFileFilter(filtroB);
            int opcion2 = fileChooser.showOpenDialog(this);
            if(opcion2 == JFileChooser.APPROVE_OPTION){
                File archivoClaveSelecionado = fileChooser.getSelectedFile();
                String urlClave = archivoClaveSelecionado.getAbsolutePath();
                try {
                    if(jp.validarClaveRSA(urlClave)==false)
                        JOptionPane.showMessageDialog(fileChooser, "El archivo de la clave no es el correcto.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(fileChooser, "El archivo de la clave no es el correcto.");
                }
            }
            int ax = JOptionPane.showConfirmDialog(fileChooser,"Esta seguro que desea remplazar los datos.", "Alerta", JOptionPane.OK_CANCEL_OPTION);
            if(ax==JOptionPane.OK_OPTION){
                
                File filenew = new File(url);
		// El Directorio de destino tras la extracción de la
                int lon = filenew.getAbsolutePath().length();
		String dir = filenew.getAbsolutePath().substring(0, lon-33);
                try {
                    jp.unZipFiles(filenew, dir);
                    listarUsuarios();
                    listarParqueaderos();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(fileChooser, "Ha existido un error mientras se descomprime", "Error", JOptionPane.OK_OPTION);
                } catch (ClassNotFoundException | ObjectSizeException | CampusNoExistenteException ex) {
                }
            }else if ( ax == JOptionPane.CANCEL_OPTION){
                
            }
            }
        }else if (opcion==JFileChooser.CANCEL_OPTION){
            this.setVisible(false);
        }
        
    }//GEN-LAST:event_restaurarBackUpItemActionPerformed

    private void cambiarClaveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarClaveItemActionPerformed
        CambiarClaveGUI cambiarClaveGUI = new CambiarClaveGUI(this, true);
        cambiarClaveGUI.setVisible(true);
    }//GEN-LAST:event_cambiarClaveItemActionPerformed

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
            java.util.logging.Logger.getLogger(PrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AcercaDeMenuItem;
    private javax.swing.JMenuItem AdministarPorterosMenuItem;
    private javax.swing.JMenuItem AdministarPuertasMenuItem;
    private javax.swing.JButton AgregarBtn;
    private javax.swing.JMenuItem AutenticacionItem;
    private javax.swing.JMenu AyudaMenu;
    private javax.swing.JComboBox CampusCB;
    private javax.swing.JMenu CampusMenu;
    private javax.swing.JMenuItem CopiaSeguridadItem;
    private javax.swing.JButton CopyCedulaBtn;
    private javax.swing.JButton CopyIDParqBtn;
    private javax.swing.JMenuItem CrearCampusMenuItem;
    private javax.swing.JButton CrearParqueaderoBtn;
    private javax.swing.JMenuItem CrearParqueaderoMenuItem;
    private javax.swing.JButton CrearUsuarioBtn;
    private javax.swing.JMenuItem CrearUsuarioMenuItem;
    private javax.swing.JMenuItem EliminarCampusMenuItem;
    private javax.swing.JButton EliminarParqueaderoBtn;
    private javax.swing.JMenuItem EliminarParqueaderoMenuItem;
    private javax.swing.JButton EliminarUsuarioBtn;
    private javax.swing.JMenuItem EliminarUsuarioMenuItem;
    private javax.swing.JMenuItem GenerarOrdenPagoItem;
    private javax.swing.JMenuItem ListarRegistrosMenuItem;
    private javax.swing.JMenuItem ModicarCampusMenuItem;
    private javax.swing.JButton ModificarParqueaderoBtn;
    private javax.swing.JMenuItem ModificarParqueaderoMenuItem;
    private javax.swing.JButton ModificarUsuarioBtn;
    private javax.swing.JMenuItem ModificarUsuarioMenuItem;
    private javax.swing.JMenu PagosMenu;
    private javax.swing.JMenu ParqueaderosMenu;
    private javax.swing.JMenuItem PuertasAccesoItem;
    private javax.swing.JButton PuertasBtn;
    private javax.swing.JMenu RegistrosMenu;
    private javax.swing.JMenuItem SalirItem;
    private javax.swing.JTable TablaParqueaderos;
    private javax.swing.JTable TablaUsuarios;
    private javax.swing.JComboBox TipoUsuarioCB;
    private javax.swing.JMenu UsuariosMenu;
    private javax.swing.JButton UsuariosParqBtn;
    private javax.swing.JMenuItem UsuariosParqueaderoItem;
    private javax.swing.JButton VerBtn;
    private javax.swing.JMenuItem cambiarClaveItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar.Separator jSeparator9;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JMenuItem restaurarBackUpItem;
    // End of variables declaration//GEN-END:variables
}
