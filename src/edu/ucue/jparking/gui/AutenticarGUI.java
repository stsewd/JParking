/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.gui;

import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.srv.CampusService;
import edu.ucue.jparking.srv.ParqueaderoService;
import edu.ucue.jparking.srv.PuertaService;
import edu.ucue.jparking.srv.UsuarioService;
import edu.ucue.jparking.srv.excepciones.AccesoNoAutorizadoException;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.Puerta;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author lara
 */
public class AutenticarGUI extends javax.swing.JDialog {

    /**
     * Creates new form AutenticarGUI
     */
    public AutenticarGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        PuertasCB.setEditable(false);
        CedulaTF.setEditable(false);
        cargarCampusCB();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CampusCB = new javax.swing.JComboBox();
        PuertasCB = new javax.swing.JComboBox();
        AutenticarBtn = new javax.swing.JButton();
        CedulaTF = new javax.swing.JTextField();
        CerrarBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        idPuertaCB = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Autenticar Acceso");
        setPreferredSize(new java.awt.Dimension(300, 174));
        setResizable(false);

        jLabel1.setText("Campus:");

        jLabel2.setText("Puerta:");

        jLabel3.setText("Cedula:");

        CampusCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CampusCBItemStateChanged(evt);
            }
        });
        CampusCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampusCBActionPerformed(evt);
            }
        });

        AutenticarBtn.setText("Autenticar");
        AutenticarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AutenticarBtnActionPerformed(evt);
            }
        });

        CedulaTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CedulaTFKeyTyped(evt);
            }
        });

        CerrarBtn.setText("Cerrar");
        CerrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CampusCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PuertasCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CedulaTF)))
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 122, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(idPuertaCB, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AutenticarBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CerrarBtn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(CampusCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(PuertasCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CedulaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AutenticarBtn)
                    .addComponent(CerrarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(idPuertaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CampusCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampusCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampusCBActionPerformed

    private void AutenticarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AutenticarBtnActionPerformed
        // TODO add your handling code here:
        String campus = (String) CampusCB.getSelectedItem();
        if(campus == null || campus.trim().length() == 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un campus.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        int num = PuertasCB.getSelectedIndex();
        String idPuerta = (String) idPuertaCB.getItemAt(num);
        if(idPuerta == null || idPuerta.trim().length() == 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado una puerta.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        
        UsuarioService usuarioService = new UsuarioService();
        try {
            usuarioService.autenticarUsuario(campus, idPuerta, CedulaTF.getText());
            JOptionPane.showMessageDialog(rootPane, "Se autenticado exitosamente.", "Mensaje", JOptionPane.OK_OPTION);
            this.setVisible(false);
        } catch (IllegalArgumentException | CedulaNoValidaException | UsuarioNoExistenteException | CodigoNoValidoException | ParqueaderoNoExistenteException | AccesoNoAutorizadoException | CampusNoExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado paso", "Mensaje", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_AutenticarBtnActionPerformed

    
    private void CerrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarBtnActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_CerrarBtnActionPerformed

    private void CampusCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CampusCBItemStateChanged
        try {
            // TODO add your handling code here:
            cargarPuertasCB();
        } catch (IllegalArgumentException | CampusNoExistenteException ex) {
        }
        CedulaTF.setEditable(true);
    }//GEN-LAST:event_CampusCBItemStateChanged

    private void CedulaTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaTFKeyTyped
        // TODO add your handling code here:
        if(CedulaTF.getText().length()==10){
            evt.consume();
        }
    }//GEN-LAST:event_CedulaTFKeyTyped

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
            java.util.logging.Logger.getLogger(AutenticarGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AutenticarGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AutenticarGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AutenticarGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AutenticarGUI dialog = new AutenticarGUI(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton AutenticarBtn;
    private javax.swing.JComboBox CampusCB;
    private javax.swing.JTextField CedulaTF;
    private javax.swing.JButton CerrarBtn;
    private javax.swing.JComboBox PuertasCB;
    private javax.swing.JComboBox idPuertaCB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables


public void cargarCampusCB(){
        //Cargar parqueaderos en combo box
        CampusCB.removeAllItems();
        CampusService campusService = new CampusService();
        for(Campus c : campusService.getCampus()){
            CampusCB.addItem(c.getNombre());
        }
}

public void cargarPuertasCB() throws CampusNoExistenteException{
        //Cargar parqueaderos en combo box
        PuertasCB.removeAllItems();
        idPuertaCB.removeAllItems();
        String campus = (String) CampusCB.getSelectedItem();
        PuertaService puertaService = new PuertaService();
        for(Puerta c : puertaService.getPuertas(campus)){
            PuertasCB.addItem(c.getUbicacion());
            idPuertaCB.addItem(c.getId());
        }
}
}