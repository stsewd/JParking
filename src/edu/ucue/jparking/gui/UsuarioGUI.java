/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.gui;

import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.srv.JP;
import edu.ucue.jparking.srv.JPInterface;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Santos Gallegos
 */
public class UsuarioGUI extends javax.swing.JDialog {

    JPInterface jp = JP.getInstance();
    /**
     * Creates new form UsuarioGUI
     */
    public UsuarioGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.getRootPane().setDefaultButton(CerrarBtn);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        TelefonoTF = new javax.swing.JTextField();
        CedulaTF = new javax.swing.JTextField();
        NombresTF = new javax.swing.JTextField();
        ApellidosTF = new javax.swing.JTextField();
        CerrarBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        DireccionTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TipoUsuarioTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        fechaContratoTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        activoCheckB = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ParqueaderosTA = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Informacion del Usuario");
        setResizable(false);

        jLabel3.setText("Apellidos:");

        TelefonoTF.setEditable(false);

        CedulaTF.setEditable(false);

        NombresTF.setEditable(false);

        ApellidosTF.setEditable(false);

        CerrarBtn.setText("Cerrar");
        CerrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarBtnActionPerformed(evt);
            }
        });

        jLabel5.setText("Dirección:");

        jLabel2.setText("Nombres:");

        DireccionTF.setEditable(false);
        DireccionTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DireccionTFActionPerformed(evt);
            }
        });

        jLabel1.setText("Cédula:");

        jLabel6.setText("Telefono:");

        jLabel4.setText("Tipo Usuario:");

        TipoUsuarioTF.setEditable(false);

        jLabel7.setText("Fecha contrato:");

        fechaContratoTF.setEditable(false);

        jLabel8.setText("Estado:");

        activoCheckB.setText("Activo");
        activoCheckB.setEnabled(false);

        jLabel9.setText("Parqueaderos registrados:");

        ParqueaderosTA.setEditable(false);
        ParqueaderosTA.setColumns(20);
        ParqueaderosTA.setRows(5);
        ParqueaderosTA.setTabSize(4);
        ParqueaderosTA.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        ParqueaderosTA.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPane1.setViewportView(ParqueaderosTA);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(349, 349, 349)
                        .addComponent(CerrarBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CedulaTF, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(NombresTF, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ApellidosTF, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(DireccionTF)
                            .addComponent(TelefonoTF)
                            .addComponent(TipoUsuarioTF)
                            .addComponent(fechaContratoTF)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(activoCheckB)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jLabel9))
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
                    .addComponent(TipoUsuarioTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fechaContratoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(activoCheckB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CerrarBtn)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CerrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarBtnActionPerformed
        // TODO addRegistro your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_CerrarBtnActionPerformed

    private void DireccionTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DireccionTFActionPerformed
        // TODO addRegistro your handling code here:
    }//GEN-LAST:event_DireccionTFActionPerformed

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
            java.util.logging.Logger.getLogger(UsuarioGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsuarioGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsuarioGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsuarioGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UsuarioGUI dialog = new UsuarioGUI(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField CedulaTF;
    private javax.swing.JButton CerrarBtn;
    private javax.swing.JTextField DireccionTF;
    private javax.swing.JTextField NombresTF;
    private javax.swing.JTextArea ParqueaderosTA;
    private javax.swing.JTextField TelefonoTF;
    private javax.swing.JTextField TipoUsuarioTF;
    private javax.swing.JCheckBox activoCheckB;
    private javax.swing.JTextField fechaContratoTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

    public void cargarDatos(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException, IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException {
        Usuario u =  jp.getUsuario(cedula);
        
        CedulaTF.setText(cedula);
        NombresTF.setText(u.getNombres());
        ApellidosTF.setText(u.getApellidos());
        DireccionTF.setText(u.getDireccion());
        TelefonoTF.setText(u.getTelefono());
        TipoUsuarioTF.setText(u.getTipoUsuarioString());
        if(u.getFechaContrato()==null){
            fechaContratoTF.setText("No Existe un contrato de parqueo");
        }else{
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            fechaContratoTF.setText(df.format(u.getFechaContrato().getTime()));
        }
        
        activoCheckB.setSelected(u.isActivo());
        String parqueaderosList = "";
        for(Parqueadero p :  jp.getParqueaderosUsuario(cedula)){
            parqueaderosList += "Campus: " + p.getCampus() + "\n" +
                    "Parqueadero: (" + p.getId() + ") " + p.getUbicacion() + "\n\n";
        }
        ParqueaderosTA.setText(parqueaderosList);
        
    }
}
