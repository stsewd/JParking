/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.gui;

import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaYaExistenteException;
import edu.ucue.jparking.srv.CampusService;
import edu.ucue.jparking.srv.PuertaService;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.Puerta;
import java.awt.JobAttributes;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lara
 */
public class AdministrarPuertasGUI extends javax.swing.JDialog {

    /**
     * Creates new form AdministrarPuertasGUI
     */
    public AdministrarPuertasGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //cargando campus en el combo
        cargarCampusCB();
        try {
            //cargando las puertas
            listarPuertas();
        } catch (CampusNoExistenteException ex) {
        } catch (CodigoNoValidoException ex) {
        }
        
        //Centrar ventana
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        CampusCB = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        CrearPuertaBtn = new javax.swing.JButton();
        ModificarPuertaBtn = new javax.swing.JButton();
        EliminarPuertabtn = new javax.swing.JButton();
        IngresarBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaPuertas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administar Puertas");
        setMinimumSize(new java.awt.Dimension(350, 350));
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jLabel1.setText("Campus:");

        CampusCB.setOpaque(false);
        CampusCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampusCBActionPerformed(evt);
            }
        });

        CrearPuertaBtn.setText("Crear");
        CrearPuertaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearPuertaBtnActionPerformed(evt);
            }
        });

        ModificarPuertaBtn.setText("Modificar");
        ModificarPuertaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarPuertaBtnActionPerformed(evt);
            }
        });

        EliminarPuertabtn.setText("Eliminar");
        EliminarPuertabtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarPuertabtnActionPerformed(evt);
            }
        });

        IngresarBtn.setText("Ingresar");
        IngresarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IngresarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(CrearPuertaBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ModificarPuertaBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EliminarPuertabtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(IngresarBtn)
                .addContainerGap(126, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CrearPuertaBtn)
                    .addComponent(ModificarPuertaBtn)
                    .addComponent(EliminarPuertabtn)
                    .addComponent(IngresarBtn)))
        );

        TablaPuertas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Id", "Ubicacion"
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
        jScrollPane2.setViewportView(TablaPuertas);
        if (TablaPuertas.getColumnModel().getColumnCount() > 0) {
            TablaPuertas.getColumnModel().getColumn(0).setMinWidth(30);
            TablaPuertas.getColumnModel().getColumn(0).setPreferredWidth(30);
            TablaPuertas.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CampusCB, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampusCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Puertas", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarCampusCB(){
        //Cargar parqueaderos en combo box
        CampusCB.removeAllItems();
        CampusService campusService = new CampusService();
        for(Campus c : campusService.getCampus()){
            CampusCB.addItem(c.getNombre());
        }
    }
    
    public void listarPuertas() throws CampusNoExistenteException, CodigoNoValidoException{
        
        PuertaService puertaService = new PuertaService();

        String nombreCampus = (String) CampusCB.getSelectedItem();
        
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            return;
        
        Set<Puerta> puertas = puertaService.getPuertas(nombreCampus);

        DefaultTableModel model = (DefaultTableModel) TablaPuertas.getModel();

        //Borrar elementos anteriores
        for(int i = model.getRowCount() - 1; i >= 0 ; i--)
            model.removeRow(i);

        int n = 1;
        for(Puerta p : puertas)
            model.addRow(new Object[]{n++, p.getId(), p.getUbicacion()});
    }

    private void CampusCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampusCBActionPerformed
        try {
            listarPuertas();
        } catch (CampusNoExistenteException ex) {
        } catch (CodigoNoValidoException ex) {
        }
    }//GEN-LAST:event_CampusCBActionPerformed

    private void CrearPuertaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearPuertaBtnActionPerformed
        // TODO add your handling code here:
        
        String campus = (String)CampusCB.getSelectedItem();
        if(campus==null || campus.trim().length()==0){
            JOptionPane.showMessageDialog(rootPane, "No se ha selccionado ningun campus.", "Error", JOptionPane.OK_OPTION);
            return;
        }
        
        CrearPuertaGUI crearPuertaGUI = new CrearPuertaGUI(null, rootPaneCheckingEnabled);
        crearPuertaGUI.setLocationRelativeTo(this);
        crearPuertaGUI.asignarCampus(campus);
        crearPuertaGUI.setVisible(true);
        try {
            listarPuertas();
        } catch (CampusNoExistenteException | CodigoNoValidoException ex) {
        }
    }//GEN-LAST:event_CrearPuertaBtnActionPerformed

    private void EliminarPuertabtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarPuertabtnActionPerformed
        // TODO add your handling code here:
        String nombreCampus = (String) CampusCB.getSelectedItem();
        if(nombreCampus==null){
            JOptionPane.showMessageDialog(rootPane, "No se a selecionado ningun campus", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        int row = TablaPuertas.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado una puerta.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        String codigo = (String) TablaPuertas.getValueAt(row, 1);
        EliminarPuertaGUI eliminarPuertaGUI = new EliminarPuertaGUI(null, rootPaneCheckingEnabled);
        eliminarPuertaGUI.setLocationRelativeTo(this);
        eliminarPuertaGUI.cargarCodigo(codigo,nombreCampus);
        eliminarPuertaGUI.setVisible(true);
        try {
            listarPuertas();
        } catch (CampusNoExistenteException ex) {
        } catch (CodigoNoValidoException ex) {
        }
    }//GEN-LAST:event_EliminarPuertabtnActionPerformed

    private void ModificarPuertaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarPuertaBtnActionPerformed
        // TODO add your handling code here:
        
        String nombreCampus = (String) CampusCB.getSelectedItem();
        if(nombreCampus==null){
            JOptionPane.showMessageDialog(rootPane, "No se a selecionado ningun campus", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        int row = TablaPuertas.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado una puerta.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        String codigo = (String) TablaPuertas.getValueAt(row, 1);
        EditarPuertaGUI editarPuertaGUI = new EditarPuertaGUI(null, rootPaneCheckingEnabled);
        editarPuertaGUI.setLocationRelativeTo(this);
        try {
            editarPuertaGUI.CargarDatos(codigo);
            editarPuertaGUI.HabilitarCampos();
            editarPuertaGUI.setVisible(true);
        } catch (CodigoNoValidoException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (PuertaNoExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }catch(IllegalArgumentException ex){
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
        try {
            listarPuertas();
        } catch (CampusNoExistenteException ex) {
        } catch (CodigoNoValidoException ex) {
        }
    }//GEN-LAST:event_ModificarPuertaBtnActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        try {
            // TODO add your handling code here:
            listarPuertas();
        } catch (CampusNoExistenteException ex) {
        } catch (CodigoNoValidoException ex) {
        }
    }//GEN-LAST:event_formFocusGained

    private void IngresarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IngresarBtnActionPerformed
        // TODO add your handling code here:
        String nombreCampus = (String) CampusCB.getSelectedItem();
        if(nombreCampus==null){
            JOptionPane.showMessageDialog(rootPane, "No se a selecionado ningun campus", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        int row = TablaPuertas.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado una puerta.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        
        String codigo = (String) TablaPuertas.getValueAt(row, 1);
        
        AutenticarUsuarioGUI augui = new AutenticarUsuarioGUI(null, true);
        augui.cargarDatos(nombreCampus, codigo);
        augui.setLocationRelativeTo(this);
        augui.setVisible(true);
    }//GEN-LAST:event_IngresarBtnActionPerformed

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
            java.util.logging.Logger.getLogger(AdministrarPuertasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministrarPuertasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministrarPuertasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministrarPuertasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AdministrarPuertasGUI dialog = new AdministrarPuertasGUI(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CampusCB;
    private javax.swing.JButton CrearPuertaBtn;
    private javax.swing.JButton EliminarPuertabtn;
    private javax.swing.JButton IngresarBtn;
    private javax.swing.JButton ModificarPuertaBtn;
    private javax.swing.JTable TablaPuertas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
