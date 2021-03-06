/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.gui;

import com.itextpdf.text.DocumentException;
import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.srv.JP;
import edu.ucue.jparking.srv.JPInterface;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.ContratoNoEstablecidoException;
import edu.ucue.jparking.srv.excepciones.FueraDelDiaDePagoException;
import edu.ucue.jparking.srv.excepciones.PagoYaRealizadoException;
import edu.ucue.jparking.srv.objetos.OrdenPago;
import edu.ucue.jparking.srv.objetos.Usuario;
import edu.ucue.jparking.srv.excepciones.UsuarioNoRegistradoEnUnParqueaderoException;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Franklin Lara
 */
public class OrdenPagoGUI extends javax.swing.JDialog {

    JPInterface jp = JP.getInstance();
    /**
     * Creates new form OrdenPago
     */
    public OrdenPagoGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        CedulaTF.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        TipoUsuarioTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TelefonoTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        NombreTF = new javax.swing.JTextField();
        DireccionTF = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        CerrarBtn = new javax.swing.JButton();
        PagarBtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        CedulaTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ValorTF = new javax.swing.JTextField();
        ApellidoTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        fechaContratoTF = new javax.swing.JTextField();
        ImprimirBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Orden de Pago");
        setResizable(false);

        jLabel4.setText("Tipo de usuario:");

        TipoUsuarioTF.setEditable(false);
        TipoUsuarioTF.setEnabled(false);
        TipoUsuarioTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoUsuarioTFActionPerformed(evt);
            }
        });

        jLabel3.setText("Nombre:");

        TelefonoTF.setEditable(false);
        TelefonoTF.setEnabled(false);
        TelefonoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TelefonoTFActionPerformed(evt);
            }
        });

        jLabel2.setText("Cédula:");

        NombreTF.setEditable(false);
        NombreTF.setEnabled(false);
        NombreTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreTFActionPerformed(evt);
            }
        });

        DireccionTF.setEditable(false);
        DireccionTF.setEnabled(false);
        DireccionTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DireccionTFActionPerformed(evt);
            }
        });

        CerrarBtn.setText("Cerrar");
        CerrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarBtnActionPerformed(evt);
            }
        });

        PagarBtn.setText("Pagar");
        PagarBtn.setEnabled(false);
        PagarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PagarBtnActionPerformed(evt);
            }
        });

        jLabel7.setText("Telefono:");

        jLabel6.setText("Dirección:");

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

        jLabel5.setText("Valor a pagar:");

        ValorTF.setEditable(false);
        ValorTF.setEnabled(false);
        ValorTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValorTFActionPerformed(evt);
            }
        });

        ApellidoTF.setEditable(false);
        ApellidoTF.setEnabled(false);
        ApellidoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApellidoTFActionPerformed(evt);
            }
        });

        jLabel8.setText("Apellido:");

        jLabel10.setText("Fecha ult pago:");

        fechaContratoTF.setEditable(false);
        fechaContratoTF.setEnabled(false);
        fechaContratoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaContratoTFActionPerformed(evt);
            }
        });

        ImprimirBtn.setText("Exportar");
        ImprimirBtn.setEnabled(false);
        ImprimirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImprimirBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ValorTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CedulaTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ApellidoTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TipoUsuarioTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TelefonoTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DireccionTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NombreTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaContratoTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(PagarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ImprimirBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CerrarBtn)))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(CedulaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(NombreTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(ApellidoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(DireccionTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TelefonoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TipoUsuarioTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(fechaContratoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ValorTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PagarBtn)
                    .addComponent(CerrarBtn)
                    .addComponent(ImprimirBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TipoUsuarioTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoUsuarioTFActionPerformed
        // TODO addRegistro your handling code here:
    }//GEN-LAST:event_TipoUsuarioTFActionPerformed

    private void TelefonoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TelefonoTFActionPerformed
        // TODO addRegistro your handling code here:
    }//GEN-LAST:event_TelefonoTFActionPerformed

    private void NombreTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreTFActionPerformed
        // TODO addRegistro your handling code here:
    }//GEN-LAST:event_NombreTFActionPerformed

    private void DireccionTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DireccionTFActionPerformed
        // TODO addRegistro your handling code here:
    }//GEN-LAST:event_DireccionTFActionPerformed

    private void CerrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarBtnActionPerformed
        // TODO addRegistro your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_CerrarBtnActionPerformed

    private void CedulaTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaTFActionPerformed
        // TODO addRegistro your handling code here:
    }//GEN-LAST:event_CedulaTFActionPerformed

    private void ValorTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValorTFActionPerformed
        // TODO addRegistro your handling code here:
    }//GEN-LAST:event_ValorTFActionPerformed

    private void PagarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PagarBtnActionPerformed
        // TODO addRegistro your handling code here:
        try {
            jp.pagarOrdenPago(CedulaTF.getText());
            JOptionPane.showMessageDialog(rootPane, "Pago realizado exitosamente.", "Mensaje", JOptionPane.OK_OPTION);
            this.setVisible(false);
        } catch (IllegalArgumentException | CedulaNoValidaException | UsuarioNoExistenteException | PagoYaRealizadoException | ContratoNoEstablecidoException | FueraDelDiaDePagoException | UsuarioNoRegistradoEnUnParqueaderoException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch(ClassNotFoundException | FileNotFoundException | ObjectSizeException ex){
            JOptionPane.showMessageDialog(rootPane, "Se produjo un error al leer o guardar en los datos.", "Mensaje", JOptionPane.OK_OPTION);
        }catch(HeadlessException ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado pasó.", "Mensaje", JOptionPane.OK_OPTION);
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(rootPane, "Algo inesperado pasó.", "Mensaje", JOptionPane.OK_OPTION);
        } 
    }//GEN-LAST:event_PagarBtnActionPerformed

    private void CedulaTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaTFKeyPressed
        // TODO addRegistro your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            try {
                cargarDatos(CedulaTF.getText());
            } catch (IllegalArgumentException | CedulaNoValidaException | UsuarioNoExistenteException | ContratoNoEstablecidoException | FueraDelDiaDePagoException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
            } catch(ClassNotFoundException | FileNotFoundException | ObjectSizeException ex){
            JOptionPane.showMessageDialog(rootPane, "Se produjo un error al leer o guardar en los datos.", "Mensaje", JOptionPane.OK_OPTION);
            } catch(IOException| UsuarioNoRegistradoEnUnParqueaderoException ex){
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Mensaje", JOptionPane.OK_OPTION);
            }
        }
    }//GEN-LAST:event_CedulaTFKeyPressed

    private void ApellidoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApellidoTFActionPerformed
        // TODO addRegistro your handling code here:
    }//GEN-LAST:event_ApellidoTFActionPerformed

    private void fechaContratoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaContratoTFActionPerformed
        // TODO addRegistro your handling code here:
    }//GEN-LAST:event_fechaContratoTFActionPerformed

    private void ImprimirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImprimirBtnActionPerformed
        try {
            // TODO addRegistro your handling code here:
            File archivo = jp.exportarOrdenPago(CedulaTF.getText());
            Desktop.getDesktop().open(archivo);
            
        } catch (IllegalArgumentException | DocumentException | FileNotFoundException | UsuarioNoExistenteException | CedulaNoValidaException | ContratoNoEstablecidoException | FueraDelDiaDePagoException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }catch (IOException ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado pasó.", "Error", JOptionPane.OK_OPTION);
        }catch (UsuarioNoRegistradoEnUnParqueaderoException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }catch(ClassNotFoundException | ObjectSizeException ex){
            JOptionPane.showMessageDialog(rootPane, "Se produjo un error al leer o guardar en los datos.", "Mensaje", JOptionPane.OK_OPTION);
        }finally{}
       
        
        
        
    }//GEN-LAST:event_ImprimirBtnActionPerformed

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
            java.util.logging.Logger.getLogger(OrdenPagoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrdenPagoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrdenPagoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrdenPagoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                OrdenPagoGUI dialog = new OrdenPagoGUI(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField ApellidoTF;
    private javax.swing.JTextField CedulaTF;
    private javax.swing.JButton CerrarBtn;
    private javax.swing.JTextField DireccionTF;
    private javax.swing.JButton ImprimirBtn;
    private javax.swing.JTextField NombreTF;
    private javax.swing.JButton PagarBtn;
    private javax.swing.JTextField TelefonoTF;
    private javax.swing.JTextField TipoUsuarioTF;
    private javax.swing.JTextField ValorTF;
    private javax.swing.JTextField fechaContratoTF;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

    public void cargarDatos(String cedula) throws CedulaNoValidaException, UsuarioNoExistenteException, ContratoNoEstablecidoException, FueraDelDiaDePagoException, UsuarioNoRegistradoEnUnParqueaderoException, IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException {
        OrdenPago  ordenPago = jp.getOrdenPago(cedula);
        Usuario u = jp.getUsuario(cedula);
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        CedulaTF.setEditable(false);
        
        NombreTF.setEnabled(true);
        ApellidoTF.setEnabled(true);
        DireccionTF.setEnabled(true);
        TelefonoTF.setEnabled(true);
        TipoUsuarioTF.setEnabled(true);
        fechaContratoTF.setEnabled(true);
        ValorTF.setEnabled(true);
        PagarBtn.setEnabled(true);
        ImprimirBtn.setEnabled(true);
        
        NombreTF.setText(u.getNombres());
        ApellidoTF.setText(u.getApellidos());
        DireccionTF.setText(u.getDireccion());
        TelefonoTF.setText(u.getTelefono());
        TipoUsuarioTF.setText(u.getTipoUsuarioString());
        fechaContratoTF.setText(u.getFechaContrato() != null?df.format(u.getFechaContrato().getTime()):"../../../");
        ValorTF.setText(String.format("%.2f", ordenPago.getCosto()));
    }
}
