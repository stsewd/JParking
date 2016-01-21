/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.gui;

import edu.ucue.jparking.Test;
import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.CampusExistenteExeption;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoYaExistenteException;
import edu.ucue.jparking.dao.excepciones.PersonaYaRegistradaComoUsuarioException;
import edu.ucue.jparking.dao.excepciones.PersonaYaRegistradoComoPorteroException;
import edu.ucue.jparking.dao.excepciones.PorteroYaExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaYaExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaExistenteException;
import edu.ucue.jparking.srv.excepciones.ClaveNoValidaException;
import edu.ucue.jparking.srv.JP;
import edu.ucue.jparking.srv.excepciones.CampusInactivoException;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.srv.excepciones.ParquaderoInactivoException;
import edu.ucue.jparking.srv.excepciones.PuertaInactivaException;
import edu.ucue.jparking.srv.excepciones.TelefonoNoValidoException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;

/**
 *
 * @author Franklin Lara
 */
public class LoginGUI extends javax.swing.JFrame {

    JP jp = JP.getInstance();
    /**
     * Creates new form LoginGUI
     */
    public LoginGUI() {
        initComponents();
        
        //centra la ventana
        setLocationRelativeTo(null);
        
        this.getRootPane().setDefaultButton(IniciarBtn);
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
        IniciarBtn = new javax.swing.JButton();
        CancelarBtn = new javax.swing.JButton();
        UsuarioTF = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        ContraseTF = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio de Sesión");
        setIconImage((new javax.swing.ImageIcon(getClass().getResource("/edu/ucue/jparking/img/transport122.png"))).getImage());
        setMinimumSize(new java.awt.Dimension(378, 206));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("JParking - Inicio de Sesión");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/ucue/jparking/img/transport122.png"))); // NOI18N

        jLabel3.setText("Usuario: ");

        jLabel4.setText("Contraseña: ");

        IniciarBtn.setText("Ingresar");
        IniciarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciarBtnActionPerformed(evt);
            }
        });

        CancelarBtn.setText("Salir");
        CancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarBtnActionPerformed(evt);
            }
        });

        UsuarioTF.setEditable(false);
        UsuarioTF.setText("Administrador");

        ContraseTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContraseTFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(IniciarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CancelarBtn))
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(UsuarioTF, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(ContraseTF))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(UsuarioTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(ContraseTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IniciarBtn)
                    .addComponent(CancelarBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarBtnActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_CancelarBtnActionPerformed
  
    private void iniciar(){
        PrincipalGUI pgui = new PrincipalGUI();
        //Inicio de tests
        try {
            Test.cargarUsuarios();
            pgui.listarUsuarios();
        }catch (UsuarioYaExistenteException | CedulaNoValidaException | TelefonoNoValidoException | PersonaYaRegistradoComoPorteroException | UsuarioNoExistenteException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (ObjectSizeException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            Test.cargarCampus();
            pgui.cargarCampusCB();
        }catch (CampusExistenteExeption ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            Test.cargarParqueaderos(35);
            pgui.listarParqueaderos();
        }catch (ParqueaderoYaExistenteException | CampusInactivoException | CampusNoExistenteException | CodigoNoValidoException ex) {
            System.out.println(ex.getMessage());
        } 
        
        try {
            Test.cargarPorteros();
        }catch (CedulaNoValidaException | CampusNoExistenteException | PorteroYaExistenteException | TelefonoNoValidoException | PersonaYaRegistradaComoUsuarioException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException | ClassNotFoundException | ObjectSizeException ex) {
        }
        
        try {
            Test.cargarPuertas(35);
        }catch (CodigoNoValidoException | PuertaYaExistenteException | CampusNoExistenteException ex) {
            System.out.println(ex.getMessage());
        }
        
        /*
        try {
            Test.cargarUsuariosParqueaderos();
            pgui.listarParqueaderos();
        } catch (CedulaNoValidaException | CodigoNoValidoException | ParqueaderoNoExistenteException | UsuarioNoExistenteException | ParquaderoInactivoException | NumeroParqueaderosNoDisponiblesException | UsuarioInactivoException | CampusNoExistenteException ex) {
            System.out.println(ex.getMessage());
        }
        */
        try {
            Test.cargarPuertasParqueaderos();
        } catch (CampusInactivoException | CampusNoExistenteException | ParqueaderoNoExistenteException | PuertaNoExistenteException | CodigoNoValidoException | ParquaderoInactivoException | PuertaYaExistenteException | PuertaInactivaException ex) {
            System.out.println(ex.getMessage());
        }
        // Fin de tests
        pgui.setVisible(true);
    }
    private void IniciarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarBtnActionPerformed
        // Inicio de sesion 
        char[] pass = ContraseTF.getPassword();
        String password = new String(pass);
        try {
            jp.validarClave(UsuarioTF.getText(), password);
            iniciar();
            this.setVisible(false);
            return;
        }catch(ClaveNoValidaException ex){
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
            ContraseTF.setText("");
        }catch(NoSuchAlgorithmException| NoSuchPaddingException| IOException| ClassNotFoundException| InvalidKeyException| IllegalBlockSizeException| BadPaddingException ex){
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
            ContraseTF.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado sucedio.", "Error", JOptionPane.OK_OPTION);
            ContraseTF.setText("");
        }
    }//GEN-LAST:event_IniciarBtnActionPerformed

    private void ContraseTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContraseTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ContraseTFActionPerformed

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
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelarBtn;
    private javax.swing.JPasswordField ContraseTF;
    private javax.swing.JButton IniciarBtn;
    private javax.swing.JTextField UsuarioTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
