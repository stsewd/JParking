/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.gui;

import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.srv.CampusService;
import edu.ucue.jparking.srv.objetos.Campus;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author stsewd
 */
public class EditarCampusGUI extends javax.swing.JFrame {

    /**
     * Creates new form EditarCampusGUI
     */
    public EditarCampusGUI() {
        initComponents();
        DireccionTextField.setEditable(false);
        EstadoCK.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NombreTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        DireccionTextField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        EditarBtn = new javax.swing.JButton();
        CancelarBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        EstadoCK = new javax.swing.JCheckBox();

        setTitle("Editar campus");

        NombreTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                NombreTextFieldCaretUpdate(evt);
            }
        });
        NombreTextField.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                NombreTextFieldInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        NombreTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreTextFieldActionPerformed(evt);
            }
        });
        NombreTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NombreTextFieldKeyPressed(evt);
            }
        });

        jLabel1.setText("Nombre:");

        jLabel2.setText("Dirección:");

        EditarBtn.setText("Editar");
        EditarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarBtnActionPerformed(evt);
            }
        });

        CancelarBtn.setText("Cancelar");
        CancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarBtnActionPerformed(evt);
            }
        });

        jLabel3.setText("Estado:");

        EstadoCK.setText("Activo");
        EstadoCK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EstadoCKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(EditarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CancelarBtn))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(EstadoCK)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(NombreTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .addComponent(DireccionTextField))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(NombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(DireccionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(EstadoCK))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelarBtn)
                    .addComponent(EditarBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NombreTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreTextFieldActionPerformed

    private void CancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarBtnActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_CancelarBtnActionPerformed
    
    
    private void EditarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarBtnActionPerformed
        // TODO add your handling code here:
        String nombre = NombreTextField.getText();
        String direccion = DireccionTextField.getText();
        CampusService campusService = new CampusService();
        boolean estado = EstadoCK.isSelected();
        try {
            campusService.modCampus(nombre, direccion, estado);
            JOptionPane.showMessageDialog(rootPane, "Campus modificado con exito!!", "Campus", JOptionPane.OK_OPTION);
            this.setVisible(false);
        } catch (CampusNoExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }catch(IllegalArgumentException ex){
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_EditarBtnActionPerformed

    private void EstadoCKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstadoCKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EstadoCKActionPerformed

    private void NombreTextFieldInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_NombreTextFieldInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreTextFieldInputMethodTextChanged

    private void NombreTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_NombreTextFieldCaretUpdate
        // TODO add your handling code here:
        
    }//GEN-LAST:event_NombreTextFieldCaretUpdate

    private void NombreTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreTextFieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            try {
                CargarDatos(NombreTextField.getText());
            }catch (CampusNoExistenteException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
            }catch (IllegalArgumentException ex){
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
            }

        }
    }//GEN-LAST:event_NombreTextFieldKeyPressed

    public void CargarDatos(String nombre) throws CampusNoExistenteException{
        CampusService campusService = new CampusService();
        Campus campus = campusService.getCampus(nombre);
        NombreTextField.setText(nombre);
        NombreTextField.setEditable(false);
        DireccionTextField.setEditable(true);
        EstadoCK.setEnabled(true);
        DireccionTextField.setText(campus.getDireccion());
        EstadoCK.setSelected(campus.isActivo());
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
            java.util.logging.Logger.getLogger(EditarCampusGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarCampusGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarCampusGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarCampusGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarCampusGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelarBtn;
    private javax.swing.JTextField DireccionTextField;
    private javax.swing.JButton EditarBtn;
    private javax.swing.JCheckBox EstadoCK;
    private javax.swing.JTextField NombreTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
