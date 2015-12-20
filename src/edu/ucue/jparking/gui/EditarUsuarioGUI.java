/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.gui;

import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaExistenteException;
import edu.ucue.jparking.srv.UsuarioService;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author stsewd
 */
public class EditarUsuarioGUI extends javax.swing.JFrame {

    /**
     * Creates new form EditarUsuarioGUI
     */
    public EditarUsuarioGUI() {
        initComponents();
        CedulaTFInputMethodTextChanged(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        NombresTF = new javax.swing.JTextField();
        TelefonoTF = new javax.swing.JTextField();
        CedulaTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        DireccionTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        CancelarBtn1 = new javax.swing.JButton();
        EditarBtn = new javax.swing.JButton();
        ApellidosTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        EstadoCK = new javax.swing.JCheckBox();

        setTitle("Editar usuario");

        jLabel2.setText("Nombres:");

        jLabel1.setText("Cédula:");

        CedulaTF.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                CedulaTFInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        CedulaTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CedulaTFActionPerformed(evt);
            }
        });
        CedulaTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CedulaTFKeyPressed(evt);
            }
        });

        jLabel3.setText("Apellidos:");

        DireccionTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DireccionTFActionPerformed(evt);
            }
        });

        jLabel6.setText("Telefono:");

        jLabel5.setText("Dirección:");

        CancelarBtn1.setText("Cancelar");
        CancelarBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarBtn1ActionPerformed(evt);
            }
        });

        EditarBtn.setText("Editar");
        EditarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarBtnActionPerformed(evt);
            }
        });

        jLabel4.setText("Estado:");

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
                        .addComponent(CancelarBtn1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(38, 38, 38))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(56, 56, 56)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EstadoCK)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(CedulaTF)
                                .addComponent(ApellidosTF)
                                .addComponent(NombresTF)
                                .addComponent(DireccionTF)
                                .addComponent(TelefonoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(CedulaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(NombresTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(ApellidosTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addComponent(DireccionTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TelefonoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(EstadoCK))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CancelarBtn1)
                    .addComponent(EditarBtn))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CedulaTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaTFActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_CedulaTFActionPerformed

    private void DireccionTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DireccionTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DireccionTFActionPerformed

    private void CancelarBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarBtn1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_CancelarBtn1ActionPerformed

    private void EditarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarBtnActionPerformed
        // TODO add your handling code here:
        String nombre = NombresTF.getText();
        String apellido = ApellidosTF.getText();
        String direccion = DireccionTF.getText();
        String telefono = TelefonoTF.getText();
        String cedula = CedulaTF.getText();
        boolean estado = EstadoCK.isSelected();
        UsuarioService usuarioService = new UsuarioService();
        try {
            usuarioService.mod(cedula, nombre, apellido, direccion, telefono, true);
            JOptionPane.showMessageDialog(rootPane, "Usuario modificado exitosamente!!!", "Usuario", JOptionPane.OK_OPTION);
            this.setVisible(false);
        }catch (IllegalArgumentException ex){
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (CedulaNoValidaException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (UsuarioNoExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_EditarBtnActionPerformed

    private void CedulaTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaTFKeyPressed
        // TODO add your handling code here:
        
        UsuarioService usuarioService = new UsuarioService();
                   
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            try {
                Usuario usuario = usuarioService.get(CedulaTF.getText());
                NombresTF.setText(usuario.getNombres());
                ApellidosTF.setText(usuario.getApellidos());
                DireccionTF.setText(usuario.getDireccion());
                TelefonoTF.setText(usuario.getTelefono());
                
            } catch (UsuarioNoExistenteException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
            } catch (CedulaNoValidaException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
            }catch(IllegalArgumentException ex){
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
            }
        }
    }//GEN-LAST:event_CedulaTFKeyPressed

    private void CedulaTFInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_CedulaTFInputMethodTextChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_CedulaTFInputMethodTextChanged

    private void EstadoCKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstadoCKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EstadoCKActionPerformed

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
            java.util.logging.Logger.getLogger(EditarUsuarioGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarUsuarioGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarUsuarioGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarUsuarioGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarUsuarioGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ApellidosTF;
    private javax.swing.JButton CancelarBtn1;
    private javax.swing.JTextField CedulaTF;
    private javax.swing.JTextField DireccionTF;
    private javax.swing.JButton EditarBtn;
    private javax.swing.JCheckBox EstadoCK;
    private javax.swing.JTextField NombresTF;
    private javax.swing.JTextField TelefonoTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
