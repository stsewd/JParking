/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.gui;

import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.srv.JP;
import edu.ucue.jparking.srv.JPInterface;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author lara
 */
public class EditarUsuarioGUI extends javax.swing.JDialog {
    private PrincipalGUI padre;
    
    JPInterface jp = JP.getInstance();
    /**
     * Creates new form EditarUsuario
     */
    public EditarUsuarioGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.padre = (PrincipalGUI) parent;
        
        this.getRootPane().setDefaultButton(EditarBtn);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        CancelarBtn1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        EditarBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ApellidosTF = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        NombresTF = new javax.swing.JTextField();
        EstadoCK = new javax.swing.JCheckBox();
        TelefonoTF = new javax.swing.JTextField();
        TipoUsuarioTF = new javax.swing.JTextField();
        CedulaTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        DireccionTF = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Usuario");
        setResizable(false);

        jLabel6.setText("Telefono:");

        jLabel5.setText("Dirección:");

        CancelarBtn1.setText("Cancelar");
        CancelarBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarBtn1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombres:");

        EditarBtn.setText("Editar");
        EditarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Cédula:");

        ApellidosTF.setEnabled(false);
        ApellidosTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ApellidosTFKeyTyped(evt);
            }
        });

        jLabel4.setText("Estado:");

        NombresTF.setEnabled(false);
        NombresTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NombresTFKeyTyped(evt);
            }
        });

        EstadoCK.setText("Activo");
        EstadoCK.setEnabled(false);
        EstadoCK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EstadoCKActionPerformed(evt);
            }
        });

        TelefonoTF.setEnabled(false);
        TelefonoTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TelefonoTFKeyTyped(evt);
            }
        });

        TipoUsuarioTF.setEditable(false);
        TipoUsuarioTF.setEnabled(false);
        TipoUsuarioTF.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                TipoUsuarioTFInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        TipoUsuarioTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoUsuarioTFActionPerformed(evt);
            }
        });
        TipoUsuarioTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TipoUsuarioTFKeyPressed(evt);
            }
        });

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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CedulaTFKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CedulaTFKeyPressed(evt);
            }
        });

        jLabel8.setText("Tipo usuario:");

        jLabel3.setText("Apellidos:");

        DireccionTF.setEnabled(false);
        DireccionTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DireccionTFActionPerformed(evt);
            }
        });
        DireccionTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                DireccionTFKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(180, Short.MAX_VALUE)
                .addComponent(EditarBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CancelarBtn1)
                .addGap(12, 12, 12))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CedulaTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DireccionTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(EstadoCK)
                                .addComponent(TelefonoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TipoUsuarioTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NombresTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(ApellidosTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CedulaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TipoUsuarioTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NombresTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ApellidosTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DireccionTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TelefonoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EstadoCK)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CancelarBtn1)
                    .addComponent(EditarBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelarBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarBtn1ActionPerformed
        // TODO addRegistro your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_CancelarBtn1ActionPerformed

    private void EditarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarBtnActionPerformed
        // TODO addRegistro your handling code here:
        String nombre = NombresTF.getText();
        String apellido = ApellidosTF.getText();
        String direccion = DireccionTF.getText();
        String telefono = TelefonoTF.getText();
        String cedula = CedulaTF.getText();
        boolean estado = EstadoCK.isSelected();
        try {
            jp.modUsuario(cedula, nombre, apellido, direccion, telefono, estado);
            JOptionPane.showMessageDialog(rootPane, "Usuario modificado exitosamente.", "Usuario", JOptionPane.OK_OPTION);
            this.setVisible(false);

            getPadre().listarUsuarios();

        }catch (IllegalArgumentException | CedulaNoValidaException | UsuarioNoExistenteException ex){
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado pasó.", "Mensaje", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_EditarBtnActionPerformed

    private void EstadoCKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstadoCKActionPerformed
        // TODO addRegistro your handling code here:
    }//GEN-LAST:event_EstadoCKActionPerformed

    private void TipoUsuarioTFInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_TipoUsuarioTFInputMethodTextChanged
        // TODO addRegistro your handling code here:
    }//GEN-LAST:event_TipoUsuarioTFInputMethodTextChanged

    private void TipoUsuarioTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoUsuarioTFActionPerformed
        // TODO addRegistro your handling code here:
    }//GEN-LAST:event_TipoUsuarioTFActionPerformed

    private void TipoUsuarioTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TipoUsuarioTFKeyPressed
        // TODO addRegistro your handling code here:
    }//GEN-LAST:event_TipoUsuarioTFKeyPressed

    private void CedulaTFInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_CedulaTFInputMethodTextChanged
        // TODO addRegistro your handling code here:

    }//GEN-LAST:event_CedulaTFInputMethodTextChanged

    private void CedulaTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaTFActionPerformed
        // TODO addRegistro your handling code here:

    }//GEN-LAST:event_CedulaTFActionPerformed

    public PrincipalGUI getPadre() {
        return padre;
    }
    
    public void cargarDatos(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException{
        Usuario usuario = jp.getUsuario(cedula);
        
        CedulaTF.setText(cedula);
        TipoUsuarioTF.setText(usuario.getTipoUsuarioString());
        NombresTF.setText(usuario.getNombres());
        ApellidosTF.setText(usuario.getApellidos());
        DireccionTF.setText(usuario.getDireccion());
        TelefonoTF.setText(usuario.getTelefono());
        EstadoCK.setSelected(usuario.isActivo());
        
    }
    
    public void habilitarCampos() {
        CedulaTF.setEditable(false);
        TipoUsuarioTF.setEnabled(true);
        NombresTF.setEnabled(true);
        ApellidosTF.setEnabled(true);
        DireccionTF.setEnabled(true);
        TelefonoTF.setEnabled(true);
        EstadoCK.setEnabled(true);
        EditarBtn.setEnabled(true);
    }
    
    private void CedulaTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaTFKeyPressed
        // TODO addRegistro your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            try {
                cargarDatos(CedulaTF.getText());
                habilitarCampos();
            } catch (UsuarioNoExistenteException | CedulaNoValidaException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
            }  catch(Exception ex){
                JOptionPane.showMessageDialog(rootPane, "Algo inesperado pasó.", "Mensaje", JOptionPane.OK_OPTION);
            }
        }
    }//GEN-LAST:event_CedulaTFKeyPressed

    private void DireccionTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DireccionTFActionPerformed
        // TODO addRegistro your handling code here:
    }//GEN-LAST:event_DireccionTFActionPerformed

    private void CedulaTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaTFKeyTyped
        // TODO addRegistro your handling code here:
        if(CedulaTF.getText().length()==10){
            evt.consume();
        }
    }//GEN-LAST:event_CedulaTFKeyTyped

    private void NombresTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombresTFKeyTyped
        // TODO addRegistro your handling code here:
        if(NombresTF.getText().length()==70){
            evt.consume();
        }
    }//GEN-LAST:event_NombresTFKeyTyped

    private void ApellidosTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ApellidosTFKeyTyped
        // TODO addRegistro your handling code here:
        if(ApellidosTF.getText().length()==70){
            evt.consume();
        }
    }//GEN-LAST:event_ApellidosTFKeyTyped

    private void DireccionTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DireccionTFKeyTyped
        // TODO addRegistro your handling code here:
        if(DireccionTF.getText().length()==70){
            evt.consume();
        }
    }//GEN-LAST:event_DireccionTFKeyTyped

    private void TelefonoTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TelefonoTFKeyTyped
        // TODO addRegistro your handling code here:
    }//GEN-LAST:event_TelefonoTFKeyTyped

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
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditarUsuarioGUI dialog = new EditarUsuarioGUI(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField ApellidosTF;
    private javax.swing.JButton CancelarBtn1;
    private javax.swing.JTextField CedulaTF;
    private javax.swing.JTextField DireccionTF;
    private javax.swing.JButton EditarBtn;
    private javax.swing.JCheckBox EstadoCK;
    private javax.swing.JTextField NombresTF;
    private javax.swing.JTextField TelefonoTF;
    private javax.swing.JTextField TipoUsuarioTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
