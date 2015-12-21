/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.gui;

import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PorteroNoExistenteException;
import edu.ucue.jparking.srv.CampusService;
import edu.ucue.jparking.srv.PorterosService;
import edu.ucue.jparking.srv.PuertaService;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.Portero;
import edu.ucue.jparking.srv.objetos.Puerta;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lara
 */
public class AdministarPorterosGUI extends javax.swing.JDialog {

    /**
     * Creates new form AdministarPorterosGUI
     */
    public AdministarPorterosGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        //carga el CampusCB
        //cargarCampusCB();
        cargarCampusCB();
        
        
        /////
        try {
            //Lista porteros
            listarPorteros();
        } catch (CampusNoExistenteException ex) {
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        CampusCB = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        CrearPorteroBtn = new javax.swing.JButton();
        ModificarPorteroBtn = new javax.swing.JButton();
        EliminarPorterobtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaPorteros = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Porteros");
        setMinimumSize(new java.awt.Dimension(350, 350));
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jTabbedPane1.setMinimumSize(new java.awt.Dimension(350, 350));

        jLabel1.setText("Campus:");

        CampusCB.setOpaque(false);
        CampusCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampusCBActionPerformed(evt);
            }
        });

        CrearPorteroBtn.setText("Crear");
        CrearPorteroBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearPorteroBtnActionPerformed(evt);
            }
        });

        ModificarPorteroBtn.setText("Modificar");
        ModificarPorteroBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarPorteroBtnActionPerformed(evt);
            }
        });

        EliminarPorterobtn.setText("Eliminar");
        EliminarPorterobtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarPorterobtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(CrearPorteroBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ModificarPorteroBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EliminarPorterobtn)
                .addContainerGap(231, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CrearPorteroBtn)
                    .addComponent(ModificarPorteroBtn)
                    .addComponent(EliminarPorterobtn)))
        );

        TablaPorteros.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(TablaPorteros);
        if (TablaPorteros.getColumnModel().getColumnCount() > 0) {
            TablaPorteros.getColumnModel().getColumn(0).setMinWidth(30);
            TablaPorteros.getColumnModel().getColumn(0).setPreferredWidth(30);
            TablaPorteros.getColumnModel().getColumn(0).setMaxWidth(30);
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Porteros", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    
    private void listarPorteros() throws CampusNoExistenteException{
        
        PorterosService porterosService = new PorterosService();

        String nombreCampus = (String) CampusCB.getSelectedItem();
        
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            return;
        
        Set<Portero> porteros = porterosService.getPorteros(nombreCampus);

        DefaultTableModel model = (DefaultTableModel) TablaPorteros.getModel();

        //Borrar elementos anteriores
        for(int i = 0; i < model.getRowCount(); i++)
            model.removeRow(i);

        int n = 1;
        for(Portero p : porteros)
            model.addRow(new Object[]{n++,p.getCedula(),p.getNombres()+" "+p.getApellidos()});
    }
    
    private void CampusCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampusCBActionPerformed
        try {
            listarPorteros();
        } catch (CampusNoExistenteException ex) {
        }
    }//GEN-LAST:event_CampusCBActionPerformed

    private void CrearPorteroBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearPorteroBtnActionPerformed
        // TODO add your handling code here:
        String nombreCampus = (String) CampusCB.getSelectedItem();
        if(nombreCampus==null){
            JOptionPane.showMessageDialog(rootPane, "No se a selecionado ningun campus", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        CrearPorteroGUI crearPorteroGUI = new CrearPorteroGUI(null, rootPaneCheckingEnabled);
        crearPorteroGUI.setLocationRelativeTo(this);
        crearPorteroGUI.Cargar(nombreCampus);
        crearPorteroGUI.setVisible(true);
    }//GEN-LAST:event_CrearPorteroBtnActionPerformed

    private void ModificarPorteroBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarPorteroBtnActionPerformed
        // TODO add your handling code here:
        String nombreCampus = (String) CampusCB.getSelectedItem();
        if(nombreCampus==null){
            JOptionPane.showMessageDialog(rootPane, "No se a selecionado ningun campus", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        int row = TablaPorteros.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado una puerta.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        String cedula = (String) TablaPorteros.getValueAt(row, 1);
        
        EditarPorteroGUI editarPorteroGUI = new EditarPorteroGUI(null, rootPaneCheckingEnabled);
        editarPorteroGUI.setLocationRelativeTo(this);
        try {
            editarPorteroGUI.CargarDatos(cedula);
            editarPorteroGUI.HabilitarCampos();
            editarPorteroGUI.setVisible(true);
            
        } catch (CedulaNoValidaException ex) {
            Logger.getLogger(AdministarPorterosGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        editarPorteroGUI.setVisible(true);
        try {
            listarPorteros();
        } catch (CampusNoExistenteException ex) {
        }
    }//GEN-LAST:event_ModificarPorteroBtnActionPerformed

    private void EliminarPorterobtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarPorterobtnActionPerformed
        // TODO add your handling code here:
        String nombreCampus = (String) CampusCB.getSelectedItem();
        if(nombreCampus==null){
            JOptionPane.showMessageDialog(rootPane, "No se a selecionado ningun campus", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        int row = TablaPorteros.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado una puerta.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        String cedula = (String) TablaPorteros.getValueAt(row, 1);
        EliminarPorteroGUI eliminarPorteroGUI = new EliminarPorteroGUI(null, rootPaneCheckingEnabled);
        eliminarPorteroGUI.setLocationRelativeTo(this);
        eliminarPorteroGUI.CargarCedula(cedula);
        eliminarPorteroGUI.setVisible(true);
        try {
            listarPorteros();
        } catch (CampusNoExistenteException ex) {
        }
        
        
    }//GEN-LAST:event_EliminarPorterobtnActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        try {
            // TODO add your handling code here:
            listarPorteros();
        } catch (CampusNoExistenteException ex) {
        }
    }//GEN-LAST:event_formFocusGained

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
            java.util.logging.Logger.getLogger(AdministarPorterosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministarPorterosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministarPorterosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministarPorterosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AdministarPorterosGUI dialog = new AdministarPorterosGUI(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton CrearPorteroBtn;
    private javax.swing.JButton EliminarPorterobtn;
    private javax.swing.JButton ModificarPorteroBtn;
    private javax.swing.JTable TablaPorteros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
