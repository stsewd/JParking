/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.gui;

import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.srv.excepciones.LugaresDeParqueoOCupadosException;
import edu.ucue.jparking.srv.excepciones.NumeroLugaresDeParqueoInsuficientesException;
import edu.ucue.jparking.srv.ParqueaderoService;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author stsewd
 */
public class EditarParqueaderoGUI extends javax.swing.JDialog {

    private PrincipalGUI padre;
    /**
     * Creates new form EditarParqueaderoGUI
     * @param parent
     * @param modal
     */
    public EditarParqueaderoGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.padre = (PrincipalGUI) parent;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CampusTF = new javax.swing.JTextField();
        UbicacionTF = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        CerrarBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        EditarBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        CodigoTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        NumeroLugaresTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        EstadoCK = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar parqueadero");

        CampusTF.setEditable(false);
        CampusTF.setEnabled(false);

        UbicacionTF.setEnabled(false);

        jLabel2.setText("Ubicación:");

        CerrarBtn.setText("Cerrar");
        CerrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarBtnActionPerformed(evt);
            }
        });

        jLabel3.setText("Campus:");

        EditarBtn.setText("Editar");
        EditarBtn.setEnabled(false);
        EditarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Código:");

        CodigoTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CodigoTFKeyPressed(evt);
            }
        });

        jLabel4.setText("Num lugares:");

        NumeroLugaresTF.setEnabled(false);

        jLabel5.setText("Estado:");

        EstadoCK.setText("Activo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CodigoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CampusTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UbicacionTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(EditarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CerrarBtn))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NumeroLugaresTF, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EstadoCK))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(CodigoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UbicacionTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampusTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NumeroLugaresTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(EstadoCK))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CerrarBtn)
                    .addComponent(EditarBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EditarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarBtnActionPerformed
        String codigo = CodigoTF.getText();
        String ubicacion = UbicacionTF.getText();
        String Campus = CampusTF.getText();
        boolean estado = EstadoCK.isSelected();
        int numLugares = 0;
        try{
            numLugares = Integer.parseInt(NumeroLugaresTF.getText());
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "El número de lugares debe ser un número.", "Error", JOptionPane.OK_OPTION);
            return;
        }
        
        ParqueaderoService parqueaderoService = new ParqueaderoService();
        try {
            parqueaderoService.modParqueadero(codigo, ubicacion, numLugares,estado);
            JOptionPane.showMessageDialog(rootPane, "Parqueadero modificado satisfactoriamente.", "Mensaje", JOptionPane.OK_OPTION);
            this.setVisible(false);
            getPadre().listarParqueaderos();
        }catch (ParqueaderoNoExistenteException | CodigoNoValidoException | CampusNoExistenteException | LugaresDeParqueoOCupadosException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (NumeroLugaresDeParqueoInsuficientesException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }catch(IllegalArgumentException ex){
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_EditarBtnActionPerformed

    private void CerrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarBtnActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_CerrarBtnActionPerformed

    private void CodigoTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CodigoTFKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            try {
                cargarDatos(CodigoTF.getText());
                habilitarCampos();
                JOptionPane.showMessageDialog(rootPane, "Parqueadero modificado exitosamente.", "Aviso", JOptionPane.OK_OPTION);
                this.setVisible(false);
                getPadre().listarParqueaderos();
            } catch (ParqueaderoNoExistenteException | CodigoNoValidoException | CampusNoExistenteException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
            }
        }
    }//GEN-LAST:event_CodigoTFKeyPressed

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
            java.util.logging.Logger.getLogger(EditarParqueaderoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarParqueaderoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarParqueaderoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarParqueaderoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditarParqueaderoGUI dialog = new EditarParqueaderoGUI(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton CerrarBtn;
    private javax.swing.JTextField CodigoTF;
    private javax.swing.JButton EditarBtn;
    private javax.swing.JCheckBox EstadoCK;
    private javax.swing.JTextField NumeroLugaresTF;
    private javax.swing.JTextField UbicacionTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

    public void cargarDatos(String parqueaderoId) throws ParqueaderoNoExistenteException, CodigoNoValidoException {
        ParqueaderoService parqueaderoService = new ParqueaderoService();
        Parqueadero parqueadero = parqueaderoService.getParqueadero(parqueaderoId);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(parqueaderoId);
        
        CodigoTF.setText(parqueaderoId);
        UbicacionTF.setText(parqueadero.getUbicacion());
        CampusTF.setText(parqueadero.getNombreCampus());
        NumeroLugaresTF.setText(Integer.toString(parqueadero.getNumeroLugares()));
        EstadoCK.setSelected(parqueadero.isActivo());
    }

    public void habilitarCampos() {
        CodigoTF.setEditable(false);
        UbicacionTF.setEnabled(true);
        CampusTF.setEnabled(true);
        EditarBtn.setEnabled(true);
        NumeroLugaresTF.setEnabled(true);
        EstadoCK.setEnabled(true);
    }

    /**
     * @return the padre
     */
    public PrincipalGUI getPadre() {
        return padre;
    }
}
