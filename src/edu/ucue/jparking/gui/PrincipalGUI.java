/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.gui;

//import static javafx.application.Platform.exit;

import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoAgregadoException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaAgregadoException;
import edu.ucue.jparking.srv.CampusService;
import edu.ucue.jparking.srv.ParqueaderoService;
import edu.ucue.jparking.srv.UsuarioService;
import edu.ucue.jparking.srv.Validaciones;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Franklin Lara
 * @author Santos Gallegos
 */
public class PrincipalGUI extends javax.swing.JFrame {

    /**
     * Creates new form PrincipalGUI
     */
    public PrincipalGUI() {
        initComponents();        
        
        //Establecer icono de aplicacion
        ImageIcon imgIcon = new ImageIcon("img/transport122.png");
        this.setIconImage(imgIcon.getImage());
        
        //Listar usuarios en tabla
        listarUsuarios();
        try {
            listarParqueaderos();
        } catch (CampusNoExistenteException | IllegalArgumentException  ex) {
        }
        
        //Cargar campus en combobox
        cargarCampusCB();
        
        //Centrar ventana
        setLocationRelativeTo(null);
        
        //Botones
        VerBtn.setIcon(new ImageIcon("img/more16.png"));
        CrearUsuarioBtn.setIcon(new ImageIcon("img/add139.png"));
        ModificarUsuarioBtn.setIcon(new ImageIcon("img/pencil41.png"));
        EliminarUsuarioBtn.setIcon(new ImageIcon("img/close7.png"));
        AgregarBtn.setIcon(new ImageIcon("img/keyboard53.png"));
        
        CrearParqueaderoBtn.setIcon(new ImageIcon("img/add139.png"));
        EliminarParqueaderoBtn.setIcon(new ImageIcon("img/close7.png"));
        ModificarParqueaderoBtn.setIcon(new ImageIcon("img/pencil41.png"));
        UsuariosParqBtn.setIcon(new ImageIcon("img/user7.png"));
        PuertasBtn.setIcon(new ImageIcon("img/barriers.png"));
        
        VerBtn.setText(null);
        CrearUsuarioBtn.setText(null);
        ModificarUsuarioBtn.setText(null);
        EliminarUsuarioBtn.setText(null);
        AgregarBtn.setText(null);
        PuertasBtn.setText(null);
        
        CrearParqueaderoBtn.setText(null);
        EliminarParqueaderoBtn.setText(null);
        ModificarParqueaderoBtn.setText(null);
        UsuariosParqBtn.setText(null);
    }
    
    public void cargarCampusCB(){
        //Cargar parqueaderos en combo box
        CampusCB.removeAllItems();
        CampusService campusService = new CampusService();
        for(Campus c : campusService.getCampus()){
            CampusCB.addItem(c.getNombre());
        }
    }
    
    public void listarUsuarios(){        
        UsuarioService usuarioService = new UsuarioService();
        
        String tipoUsuario = (String) TipoUsuarioCB.getSelectedItem();
        Set<Usuario> usuarios = null;
        switch(tipoUsuario){
            case "Todos": {
                usuarios = usuarioService.getLista();
                break;
            }
            case "Estudiante":{
                usuarios = usuarioService.getLista(TipoUsuario.ESTUDIANTE);
                break;
            }
            case "Docente": {
                usuarios = usuarioService.getLista(TipoUsuario.DOCENTE);
                break;
            }
            case "Empleado": {
                usuarios = usuarioService.getLista(TipoUsuario.EMPLEADO);
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
    
    public void listarParqueaderos() throws CampusNoExistenteException{
        ParqueaderoService parqueaderoService = new ParqueaderoService();

        String nombreCampus = (String) CampusCB.getSelectedItem();
        
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            return;
        
        Set<Parqueadero> parqueaderos = parqueaderoService.getParqueaderos(nombreCampus);

        DefaultTableModel model = (DefaultTableModel) TablaParqueaderos.getModel();

        //Borrar elementos anteriores
        for(int i = model.getRowCount() - 1; i >= 0 ; i--)
            model.removeRow(i);

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
        jToolBar2 = new javax.swing.JToolBar();
        VerBtn = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        CrearUsuarioBtn = new javax.swing.JButton();
        EliminarUsuarioBtn = new javax.swing.JButton();
        ModificarUsuarioBtn = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
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
        jMenu1 = new javax.swing.JMenu();
        AutenticacionItem = new javax.swing.JMenuItem();
        RegistrosMenu = new javax.swing.JMenu();
        ListarRegistrosMenuItem = new javax.swing.JMenuItem();
        AyudaMenu = new javax.swing.JMenu();
        AcercaDeMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JParking- Gestion de los Parqueaderos de la Universidad de Cuenca");
        setMinimumSize(new java.awt.Dimension(800, 430));

        jSplitPane1.setDividerLocation(350);
        jSplitPane1.setDividerSize(15);
        jSplitPane1.setAutoscrolls(true);
        jSplitPane1.setOneTouchExpandable(true);

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
                "#", "Id", "Ubicación", "Espacios", "Espacios ocupados", "Activo"
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

        CrearParqueaderoBtn.setText("Crear");
        CrearParqueaderoBtn.setToolTipText("Crear nuevo parqueadero");
        CrearParqueaderoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearParqueaderoBtnActionPerformed(evt);
            }
        });
        jToolBar3.add(CrearParqueaderoBtn);

        EliminarParqueaderoBtn.setText("Eliminar");
        EliminarParqueaderoBtn.setToolTipText("Eliminar parqueadero");
        EliminarParqueaderoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarParqueaderoBtnActionPerformed(evt);
            }
        });
        jToolBar3.add(EliminarParqueaderoBtn);

        ModificarParqueaderoBtn.setText("Modificar");
        ModificarParqueaderoBtn.setToolTipText("Modificar parqueadero");
        ModificarParqueaderoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarParqueaderoBtnActionPerformed(evt);
            }
        });
        jToolBar3.add(ModificarParqueaderoBtn);
        jToolBar3.add(jSeparator7);

        UsuariosParqBtn.setText("Usuarios");
        UsuariosParqBtn.setToolTipText("Ver usuarios de parqueadero");
        UsuariosParqBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuariosParqBtnActionPerformed(evt);
            }
        });
        jToolBar3.add(UsuariosParqBtn);

        PuertasBtn.setText("Puertas");
        PuertasBtn.setToolTipText("Ver puertas de acceso del parqueadero");
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jToolBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
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

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        VerBtn.setText("Ver");
        VerBtn.setToolTipText("Ver usuario");
        VerBtn.setFocusable(false);
        VerBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        VerBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        VerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerBtnActionPerformed(evt);
            }
        });
        jToolBar2.add(VerBtn);
        jToolBar2.add(jSeparator2);

        CrearUsuarioBtn.setText("Crear");
        CrearUsuarioBtn.setToolTipText("Crear nuevo usuario");
        CrearUsuarioBtn.setFocusable(false);
        CrearUsuarioBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CrearUsuarioBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CrearUsuarioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearUsuarioBtnActionPerformed(evt);
            }
        });
        jToolBar2.add(CrearUsuarioBtn);

        EliminarUsuarioBtn.setText("Eliminar");
        EliminarUsuarioBtn.setToolTipText("Eliminar usuario");
        EliminarUsuarioBtn.setFocusable(false);
        EliminarUsuarioBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EliminarUsuarioBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EliminarUsuarioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarUsuarioBtnActionPerformed(evt);
            }
        });
        jToolBar2.add(EliminarUsuarioBtn);

        ModificarUsuarioBtn.setText("Modificar");
        ModificarUsuarioBtn.setToolTipText("Modificar usuario");
        ModificarUsuarioBtn.setFocusable(false);
        ModificarUsuarioBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ModificarUsuarioBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ModificarUsuarioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarUsuarioBtnActionPerformed(evt);
            }
        });
        jToolBar2.add(ModificarUsuarioBtn);
        jToolBar2.add(jSeparator3);

        AgregarBtn.setText("Agregar >>");
        AgregarBtn.setToolTipText("Agregar usuario a parqueadero");
        AgregarBtn.setFocusable(false);
        AgregarBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AgregarBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AgregarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarBtnActionPerformed(evt);
            }
        });
        jToolBar2.add(AgregarBtn);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(TipoUsuarioCB, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jToolBar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        GenerarOrdenPagoItem.setText("Generar Orden de Pago");
        GenerarOrdenPagoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarOrdenPagoItemActionPerformed(evt);
            }
        });
        PagosMenu.add(GenerarOrdenPagoItem);

        jMenuBar1.add(PagosMenu);

        jMenu1.setMnemonic('a');
        jMenu1.setText("Autenticar");

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
        // TODO add your handling code here:
        EliminarUsuarioGUI eliminarUsuarioGUI = new EliminarUsuarioGUI(this, true);
        eliminarUsuarioGUI.setLocationRelativeTo(this);
        eliminarUsuarioGUI.setVisible(true);
    }//GEN-LAST:event_EliminarUsuarioMenuItemActionPerformed

    private void CrearParqueaderoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearParqueaderoMenuItemActionPerformed
        // TODO add your handling code here:
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
        // TODO add your handling code here:
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
        // TODO add your handling code here:
        AcercaDeGUI ad = new AcercaDeGUI(this, true);
        ad.setLocationRelativeTo(this);
        ad.cargarImagen();
        ad.setVisible(true);
    }//GEN-LAST:event_AcercaDeMenuItemActionPerformed

    private void EliminarParqueaderoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarParqueaderoBtnActionPerformed
        // TODO add your handling code here:
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
        Validaciones validaciones = new Validaciones();
        try {
            validaciones.ComprobarCampus(campus);
            EliminarParqueaderoGUI eliminarParqueaderoGUI = new EliminarParqueaderoGUI(this, true);
            eliminarParqueaderoGUI.cargarDatos(idParqueadero, campus);
            eliminarParqueaderoGUI.setLocationRelativeTo(this);
            eliminarParqueaderoGUI.setVisible(true);
        } catch (IllegalArgumentException | CampusNoExistenteException | CampusInactivoException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }  catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado paso...", "Mensaje", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_EliminarParqueaderoBtnActionPerformed

    private void CrearParqueaderoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearParqueaderoBtnActionPerformed
        // TODO add your handling code here:
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
        // TODO add your handling code here:
        CrearUsuarioGUI crearUsuarioGUI = new CrearUsuarioGUI(this, rootPaneCheckingEnabled);
        crearUsuarioGUI.setLocationRelativeTo(this);
        crearUsuarioGUI.setVisible(true);
    }//GEN-LAST:event_CrearUsuarioMenuItemActionPerformed

    private void ModificarUsuarioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarUsuarioMenuItemActionPerformed
        // TODO add your handling code here:
        EditarUsuarioGUI editarUsuarioGUI = new EditarUsuarioGUI(this, rootPaneCheckingEnabled);
        editarUsuarioGUI.setLocationRelativeTo(this);
        editarUsuarioGUI.setVisible(true);
    }//GEN-LAST:event_ModificarUsuarioMenuItemActionPerformed

    private void CrearUsuarioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearUsuarioBtnActionPerformed
        CrearUsuarioGUI crearUsuarioGUI = new CrearUsuarioGUI(this, rootPaneCheckingEnabled);
        crearUsuarioGUI.setLocationRelativeTo(this);
        crearUsuarioGUI.setVisible(true);
    }//GEN-LAST:event_CrearUsuarioBtnActionPerformed
    
    private void ModificarUsuarioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarUsuarioBtnActionPerformed
        int row = TablaUsuarios.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un usuario.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        String cedula = (String) TablaUsuarios.getValueAt(row, 1);
        
        EditarUsuarioGUI editarUsuarioGUI = new EditarUsuarioGUI(this, rootPaneCheckingEnabled);
        editarUsuarioGUI.setLocationRelativeTo(this);
        
        try {
            editarUsuarioGUI.cargarDatos(cedula);
            editarUsuarioGUI.habilitarCampos();
            editarUsuarioGUI.setVisible(true);
        } catch (IllegalArgumentException | UsuarioNoExistenteException | CedulaNoValidaException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado paso...", "Mensaje", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_ModificarUsuarioBtnActionPerformed

    private void TipoUsuarioCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoUsuarioCBActionPerformed
        listarUsuarios();
    }//GEN-LAST:event_TipoUsuarioCBActionPerformed

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
        }
    }//GEN-LAST:event_CampusCBActionPerformed

    private void EliminarUsuarioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarUsuarioBtnActionPerformed
        // TODO add your handling code here:
        int row = TablaUsuarios.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un usuario.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        String cedula = (String) TablaUsuarios.getValueAt(row, 1);
        EliminarUsuarioGUI eliminarUsuarioGUI = new EliminarUsuarioGUI(this, rootPaneCheckingEnabled);
        eliminarUsuarioGUI.cargarDatos(cedula);
        eliminarUsuarioGUI.setLocationRelativeTo(this);
        eliminarUsuarioGUI.setVisible(true);
    }//GEN-LAST:event_EliminarUsuarioBtnActionPerformed

    private void SalirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_SalirItemActionPerformed

    private void EliminarCampusMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarCampusMenuItemActionPerformed
        // TODO add your handling code here:
        String nombreCampus = (String) CampusCB.getSelectedItem();
        if(nombreCampus==null){
            JOptionPane.showMessageDialog(rootPane, "No se a selecionado ningun campus", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        CampusService campusService = new CampusService();
        try {
            campusService.delCampus((String) CampusCB.getSelectedItem());
        } catch (CampusNoExistenteException | IllegalArgumentException | ParqueaderoNoExistenteException | UsuarioNoExistenteException | UsuarioNoAgregadoException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
        
        cargarCampusCB();
    }//GEN-LAST:event_EliminarCampusMenuItemActionPerformed

    private void ModicarCampusMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModicarCampusMenuItemActionPerformed
        // TODO add your handling code here:
        String nombreCampus = (String) CampusCB.getSelectedItem();
        if(nombreCampus==null){
            JOptionPane.showMessageDialog(rootPane, "No se a selecionado ningun campus", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        EditarCampusGUI editarCampusGUI = new EditarCampusGUI(this, rootPaneCheckingEnabled);
        editarCampusGUI.setLocationRelativeTo(this);
        try {
            editarCampusGUI.CargarDatos((String) CampusCB.getSelectedItem());
            editarCampusGUI.setVisible(true);
        } catch (CampusNoExistenteException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado paso...", "Mensaje", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_ModicarCampusMenuItemActionPerformed

    private void CrearCampusMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearCampusMenuItemActionPerformed
        // TODO add your handling code here:
        CrearCampusGUI crearCampusGUI = new CrearCampusGUI(this, rootPaneCheckingEnabled);
        crearCampusGUI.setLocationRelativeTo(this);
        crearCampusGUI.setVisible(true);
    }//GEN-LAST:event_CrearCampusMenuItemActionPerformed

    private void AdministarPuertasMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdministarPuertasMenuItemActionPerformed
        // TODO add your handling code here:
        AdministrarPuertasGUI administrarPuertasGUI = new AdministrarPuertasGUI(this, true);
        administrarPuertasGUI.setLocationRelativeTo(this);
        administrarPuertasGUI.setVisible(true);
    }//GEN-LAST:event_AdministarPuertasMenuItemActionPerformed

    private void AdministarPorterosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdministarPorterosMenuItemActionPerformed
        // TODO add your handling code here:
        AdministarPorterosGUI administarPorterosGUI = new AdministarPorterosGUI(this, rootPaneCheckingEnabled);
        administarPorterosGUI.setLocationRelativeTo(this);
        administarPorterosGUI.setVisible(true);
    }//GEN-LAST:event_AdministarPorterosMenuItemActionPerformed

    private void ModificarParqueaderoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarParqueaderoBtnActionPerformed
        // TODO add your handling code here:
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
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado paso...", "Mensaje", JOptionPane.OK_OPTION);
        }
        
    }//GEN-LAST:event_ModificarParqueaderoBtnActionPerformed

    private void ModificarParqueaderoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarParqueaderoMenuItemActionPerformed
        // TODO add your handling code here:
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
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado paso...", "Mensaje", JOptionPane.OK_OPTION);
        }
        
    }//GEN-LAST:event_ModificarParqueaderoMenuItemActionPerformed

    private void VerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerBtnActionPerformed
        // TODO add your handling code here:
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
        } catch (UsuarioNoExistenteException | CedulaNoValidaException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado paso...", "Mensaje", JOptionPane.OK_OPTION);
        }
        
        
    }//GEN-LAST:event_VerBtnActionPerformed

    private void PuertasAccesoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PuertasAccesoItemActionPerformed
        // TODO add your handling code here:
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
        AdministrarPuertaAccesoGUI puertaAcceso = new AdministrarPuertaAccesoGUI(this, rootPaneCheckingEnabled);
        puertaAcceso.setLocationRelativeTo(this);
        try {
            puertaAcceso.CargarDatos(nombreCampus, idParqueadero);
            puertaAcceso.setVisible(true);
        } catch (ParqueaderoNoExistenteException | CodigoNoValidoException | IllegalArgumentException | CampusNoExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado paso...", "Mensaje", JOptionPane.OK_OPTION);
        }
    }
    
    private void UsuariosParqueaderoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuariosParqueaderoItemActionPerformed
        // TODO add your handling code here:      
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
        AdministrarUsuariosParqueaderoGUI usuariosParqueadero = new AdministrarUsuariosParqueaderoGUI(this, rootPaneCheckingEnabled);
        usuariosParqueadero.setLocationRelativeTo(this);
        try {
            usuariosParqueadero.CargarDatos(nombreCampus, idParqueadero);
            usuariosParqueadero.setVisible(true);
        } catch (CampusInactivoException | ParquaderoInactivoException | ParqueaderoNoExistenteException | CampusNoExistenteException | CodigoNoValidoException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado paso...", "Mensaje", JOptionPane.OK_OPTION);
        }
    }
    
    private void GenerarOrdenPagoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarOrdenPagoItemActionPerformed
        // TODO add your handling code here:
        OrdenPagoGUI opgui = new OrdenPagoGUI(this, true);
        opgui.setLocationRelativeTo(this);
        opgui.setVisible(true);
    }//GEN-LAST:event_GenerarOrdenPagoItemActionPerformed

    private void PuertasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PuertasBtnActionPerformed
        // TODO add your handling code here:
        puertasAccesoParqueadero();
    }//GEN-LAST:event_PuertasBtnActionPerformed

    private void UsuariosParqBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuariosParqBtnActionPerformed
        // TODO add your handling code here:
        usuariosParqueadero();
    }//GEN-LAST:event_UsuariosParqBtnActionPerformed

    private void AgregarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarBtnActionPerformed
        // TODO add your handling code here:
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
        
        ParqueaderoService parqueaderoService = new ParqueaderoService();
        
        
        try {
            parqueaderoService.addUsuario(campus, idParqueadero, cedula);
            JOptionPane.showMessageDialog(rootPane, "Usuario agregado a paqueadero.", "Mensaje", JOptionPane.OK_OPTION);
            listarParqueaderos();
        } catch (CedulaNoValidaException | CampusInactivoException | CampusNoExistenteException | UsuarioInactivoException | NumeroParqueaderosNoDisponiblesException | CodigoNoValidoException | IllegalArgumentException | ParqueaderoNoExistenteException | UsuarioYaAgregadoException | UsuarioNoExistenteException | ParquaderoInactivoException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado paso...", "Mensaje", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_AgregarBtnActionPerformed

    private void AutenticacionItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AutenticacionItemActionPerformed
        // TODO add your handling code here:
        AutenticarGUI autenticarGUI = new AutenticarGUI(this, true);
        autenticarGUI.setLocationRelativeTo(this);
        autenticarGUI.setVisible(true);
    }//GEN-LAST:event_AutenticacionItemActionPerformed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    // End of variables declaration//GEN-END:variables
}
