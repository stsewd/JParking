/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.gui;

import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaExistenteException;
import edu.ucue.jparking.srv.UsuarioService;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.objetos.OrdenPago;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Franklin
 */
public class CrearUsuarioGUI extends javax.swing.JFrame {

    /**
     * Creates new form CrearUsuario
     */
    public CrearUsuarioGUI() {
        initComponents();
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
        jLabel4 = new javax.swing.JLabel();
        CedulaTF = new javax.swing.JTextField();
        NombresTF = new javax.swing.JTextField();
        ApellidosTF = new javax.swing.JTextField();
        TipoUsuarioCB = new javax.swing.JComboBox();
        CrearBtn = new javax.swing.JButton();
        CancelarBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        DireccionTF = new javax.swing.JTextField();
        TelefonoTF = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();

        setTitle("Crear Usuario");
        setResizable(false);

        jLabel1.setText("Cédula:");

        jLabel2.setText("Nombres:");

        jLabel3.setText("Apellidos:");

        jLabel4.setText("Tipo Usuario:");

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

        TipoUsuarioCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DOCENTE", "EMPLEADO", "ESTUDIANTE" }));

        CrearBtn.setText("Crear");
        CrearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearBtnActionPerformed(evt);
            }
        });

        CancelarBtn.setText("Cancelar");
        CancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarBtnActionPerformed(evt);
            }
        });

        jLabel5.setText("Dirección:");

        jLabel6.setText("Telefono:");

        DireccionTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DireccionTFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(CrearBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CancelarBtn))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CedulaTF, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(ApellidosTF)
                            .addComponent(NombresTF)
                            .addComponent(DireccionTF)
                            .addComponent(TelefonoTF)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TipoUsuarioCB, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(TipoUsuarioCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CancelarBtn)
                    .addComponent(CrearBtn))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CedulaTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CedulaTFActionPerformed

    private void CrearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearBtnActionPerformed
        // TODO add your handling code here:
        String nombre = NombresTF.getText();
        String apellido = ApellidosTF.getText();
        String direccion = DireccionTF.getText();
        String telefono = TelefonoTF.getText();
        String cedula = CedulaTF.getText();
        String tipoUsuario =  (String) TipoUsuarioCB.getSelectedItem();
        UsuarioService usuarioService = new UsuarioService();
        try {
            usuarioService.add(cedula, nombre, apellido, direccion, telefono, tipoUsuario);
            JOptionPane.showMessageDialog(rootPane, "Usuario creado exitosamente!!!", "Usuario", JOptionPane.OK_OPTION);
            this.setVisible(false);
        } catch (UsuarioYaExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
            //Logger.getLogger(CrearUsuarioGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CedulaNoValidaException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
            //Logger.getLogger(CrearUsuarioGUI.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IllegalArgumentException ex){
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
        
    }//GEN-LAST:event_CrearBtnActionPerformed

    private void DireccionTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DireccionTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DireccionTFActionPerformed

    private void CancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarBtnActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_CancelarBtnActionPerformed

    private void CedulaTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaTFKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_CedulaTFKeyPressed

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
            java.util.logging.Logger.getLogger(CrearUsuarioGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearUsuarioGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearUsuarioGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearUsuarioGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrearUsuarioGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ApellidosTF;
    private javax.swing.JButton CancelarBtn;
    private javax.swing.JTextField CedulaTF;
    private javax.swing.JButton CrearBtn;
    private javax.swing.JTextField DireccionTF;
    private javax.swing.JTextField NombresTF;
    private javax.swing.JTextField TelefonoTF;
    private javax.swing.JComboBox TipoUsuarioCB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
