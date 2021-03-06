/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.gui;

import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.srv.JP;
import edu.ucue.jparking.srv.JPInterface;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.srv.objetos.Puerta;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author lara
 */
public class EditarPuertaGUI extends javax.swing.JDialog {
    JPInterface jp = JP.getInstance();
    /**
     * Creates new form EditarPuerta
     */
    public EditarPuertaGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
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

        jLabel2 = new javax.swing.JLabel();
        CodigoTF = new javax.swing.JTextField();
        UbicacionTF = new javax.swing.JTextField();
        CancelarBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        EstadoCK = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        CampusTF = new javax.swing.JTextField();
        EditarBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Puerta");
        setResizable(false);

        jLabel2.setText("Ubicación:");

        CodigoTF.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                CodigoTFInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        CodigoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodigoTFActionPerformed(evt);
            }
        });
        CodigoTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CodigoTFKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CodigoTFKeyPressed(evt);
            }
        });

        UbicacionTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                UbicacionTFKeyTyped(evt);
            }
        });

        CancelarBtn.setText("Cancelar");
        CancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Codigo:");

        jLabel3.setText("Estado:");

        EstadoCK.setText("Activo");

        jLabel4.setText("Campus:");

        CampusTF.setEditable(false);
        CampusTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CampusTFKeyTyped(evt);
            }
        });

        EditarBtn.setText("Editar");
        EditarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarBtnActionPerformed(evt);
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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(EditarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CancelarBtn))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EstadoCK)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(UbicacionTF)
                                .addComponent(CodigoTF, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                                .addComponent(CampusTF)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(CodigoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(UbicacionTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CampusTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(EstadoCK))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CancelarBtn)
                    .addComponent(EditarBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CodigoTFInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_CodigoTFInputMethodTextChanged
        // TODO addRegistro your handling code here:
    }//GEN-LAST:event_CodigoTFInputMethodTextChanged

    private void CodigoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodigoTFActionPerformed
        // TODO addRegistro your handling code here:
    }//GEN-LAST:event_CodigoTFActionPerformed

    public void HabilitarCampos(){
        CodigoTF.setEditable(false);
        UbicacionTF.setEditable(true);
        EstadoCK.setEnabled(true);
        CampusTF.setEditable(false);
    }
    
    public void CargarDatos(String nombreCampus, String codigo) throws CodigoNoValidoException, PuertaNoExistenteException, CampusNoExistenteException, IOException, ClassNotFoundException, ObjectSizeException{
        Puerta puerta = jp.getPuerta(nombreCampus, codigo);
        CodigoTF.setText(codigo);
        UbicacionTF.setText(puerta.getUbicacion());
        EstadoCK.setSelected(puerta.estaActiva());
        CampusTF.setText(puerta.getCampus());
        
    }
    private void CodigoTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CodigoTFKeyPressed
        // TODO addRegistro your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            try {
                CargarDatos(CampusTF.getText(), CodigoTF.getText());
                HabilitarCampos();
            } catch (CodigoNoValidoException | PuertaNoExistenteException | IllegalArgumentException | CampusNoExistenteException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(rootPane, "Algo inesperado pasó.", "Mensaje", JOptionPane.OK_OPTION);
            }
        }
    }//GEN-LAST:event_CodigoTFKeyPressed

    private void CancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarBtnActionPerformed
        // TODO addRegistro your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_CancelarBtnActionPerformed

    private void EditarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarBtnActionPerformed
        // TODO addRegistro your handling code here:
        String codigo = CodigoTF.getText();
        String ubicacion = UbicacionTF.getText();
        String campus = CampusTF.getText();
        boolean estado = EstadoCK.isSelected();
        try {
            jp.modPuerta(campus, codigo, ubicacion, estado);
            JOptionPane.showMessageDialog(rootPane, "Puerta modificada exitosamente.", "Puerta", JOptionPane.OK_OPTION);
            this.setVisible(false);
        } catch (CodigoNoValidoException | PuertaNoExistenteException | CampusNoExistenteException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch(ClassNotFoundException | FileNotFoundException | ObjectSizeException ex){
            JOptionPane.showMessageDialog(rootPane, "Se produjo un error al leer o guardar en los datos.", "Mensaje", JOptionPane.OK_OPTION);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado pasó.", "Mensaje", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_EditarBtnActionPerformed

    private void CodigoTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CodigoTFKeyTyped

        // TODO addRegistro your handling code here:
        if(CodigoTF.getText().length()==3){
            evt.consume();
        }
    }//GEN-LAST:event_CodigoTFKeyTyped

    private void UbicacionTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UbicacionTFKeyTyped
        // TODO addRegistro your handling code here:
        if(UbicacionTF.getText().length()==70){
            evt.consume();
        }
    }//GEN-LAST:event_UbicacionTFKeyTyped

    private void CampusTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CampusTFKeyTyped
        // TODO addRegistro your handling code here:
        if(CampusTF.getText().length()==70){
            evt.consume();
        }
    }//GEN-LAST:event_CampusTFKeyTyped

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
            java.util.logging.Logger.getLogger(EditarPuertaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarPuertaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarPuertaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarPuertaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditarPuertaGUI dialog = new EditarPuertaGUI(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField CampusTF;
    private javax.swing.JButton CancelarBtn;
    private javax.swing.JTextField CodigoTF;
    private javax.swing.JButton EditarBtn;
    private javax.swing.JCheckBox EstadoCK;
    private javax.swing.JTextField UbicacionTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
