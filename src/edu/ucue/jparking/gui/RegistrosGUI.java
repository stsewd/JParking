/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.gui;

import edu.ucue.jparking.dao.excepciones.RegistroNoExistenteException;
import edu.ucue.jparking.srv.JP;
import edu.ucue.jparking.srv.JPInterface;
import edu.ucue.jparking.srv.excepciones.FechaFinalMenorAFechaInicialException;
import edu.ucue.jparking.srv.excepciones.FechaInicialIgualAFechaFinalException;
import edu.ucue.jparking.srv.excepciones.FechaInicialMayorAFechaFinalException;
import edu.ucue.jparking.srv.enums.TipoRegistro;
import edu.ucue.jparking.srv.objetos.registros.Registro;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Santos Gallegos
 */
public class RegistrosGUI extends javax.swing.JDialog {

    JPInterface jp = JP.getInstance();

    /**
     * Creates new form RegistrosGUI
     */
    public RegistrosGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        listarRegistros();
    }
    
    public void listarRegistros(){        
        
        String tipoRegistro = (String) TipoRegistroCB.getSelectedItem();
        Set<Registro> registros = null;
        switch(tipoRegistro){
            case "Todos": {
                registros = jp.getRegistros();
                break;
            }
            case "Acceso a parqueadero":{
                registros = jp.getRegistros(TipoRegistro.ACCESO_PARQUEADERO);
                break;
            }
            case "Pagos": {
                registros = jp.getRegistros(TipoRegistro.PAGOS);
                break;
            }
            case "Persona": {
                registros = jp.getRegistros(TipoRegistro.PERSONA);
                break;
            }
        }
        
        DefaultTableModel model = (DefaultTableModel) RegistrosTabla.getModel();
        
        //Borrar elementos anteriores
        for(int i = model.getRowCount() - 1; i >= 0 ; i--)
            model.removeRow(i);
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");     
        int n = 0;
        for(Registro r : registros)
            model.addRow(new Object[]{n++, df.format(r.getFecha().getTime()),
                r.getCedulaPersona(), r.getTipoRegistroString(), r.getTipoAccionString()});
    }
    
    
    public void listarRegistros(Calendar fechaInicial, Calendar fechaFinal) throws FechaInicialMayorAFechaFinalException, FechaFinalMenorAFechaInicialException, FechaInicialIgualAFechaFinalException{        
        
        String tipoRegistro = (String) TipoRegistroCB.getSelectedItem();
        Set<Registro> registros = null;
        switch(tipoRegistro){
            case "Todos": {
                registros = jp.getRegistros(fechaInicial, fechaFinal);
                break;
            }
            case "Acceso a parqueadero":{
                registros = jp.getRegistros(TipoRegistro.ACCESO_PARQUEADERO, fechaInicial, fechaFinal);
                break;
            }
            case "Pagos": {
                registros = jp.getRegistros(TipoRegistro.PAGOS, fechaInicial, fechaFinal);
                break;
            }
            case "Usuario": {
                registros = jp.getRegistros(TipoRegistro.PERSONA, fechaInicial, fechaFinal);
                break;
            }
        }
        
        DefaultTableModel model = (DefaultTableModel) RegistrosTabla.getModel();
        
        //Borrar elementos anteriores
        for(int i = model.getRowCount() - 1; i >= 0 ; i--)
            model.removeRow(i);
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");        
        int n = 0;
        for(Registro r : registros)
            model.addRow(new Object[]{n++, df.format(r.getFecha().getTime()),
                r.getCedulaPersona(), r.getTipoRegistroString(), r.getTipoAccionString()});
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        RegistrosTabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        TipoRegistroCB = new javax.swing.JComboBox();
        FechaCheckB = new javax.swing.JCheckBox();
        VerRegistroBtn = new javax.swing.JButton();
        CerrarBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        FechaInicialDate = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        FechaFinalDate = new com.toedter.calendar.JDateChooser();
        FiltrarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registros");

        RegistrosTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Fecha", "Cedula", "Tipo de registro", "Accion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        RegistrosTabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        RegistrosTabla.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(RegistrosTabla);
        if (RegistrosTabla.getColumnModel().getColumnCount() > 0) {
            RegistrosTabla.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        jLabel1.setText("Tipo registro:");

        TipoRegistroCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Acceso a parqueadero", "Pagos", "Persona" }));
        TipoRegistroCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoRegistroCBActionPerformed(evt);
            }
        });

        FechaCheckB.setText("Fecha");
        FechaCheckB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FechaCheckBActionPerformed(evt);
            }
        });

        VerRegistroBtn.setText("Ver registro");
        VerRegistroBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerRegistroBtnActionPerformed(evt);
            }
        });

        CerrarBtn.setText("Cerrar");
        CerrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("Fecha inicial:");

        FechaInicialDate.setEnabled(false);

        jLabel3.setText("Fecha final:");

        FechaFinalDate.setEnabled(false);

        FiltrarBtn.setText("Filtrar");
        FiltrarBtn.setEnabled(false);
        FiltrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltrarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(VerRegistroBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CerrarBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TipoRegistroCB, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(FechaCheckB)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(8, 8, 8)
                        .addComponent(FechaInicialDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel3)
                        .addGap(11, 11, 11)
                        .addComponent(FechaFinalDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(FiltrarBtn)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TipoRegistroCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FechaCheckB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(FechaInicialDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FechaFinalDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(FiltrarBtn)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(4, 4, 4)))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VerRegistroBtn)
                    .addComponent(CerrarBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FechaCheckBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FechaCheckBActionPerformed
        boolean estado = FechaCheckB.isSelected();
        FechaFinalDate.setEnabled(estado);
        FechaInicialDate.setEnabled(estado);
        FiltrarBtn.setEnabled(estado);
    }//GEN-LAST:event_FechaCheckBActionPerformed

    private void CerrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarBtnActionPerformed
        // TODO addRegistro your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_CerrarBtnActionPerformed

    private void TipoRegistroCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoRegistroCBActionPerformed
        // TODO addRegistro your handling code here:
        listarRegistros();
    }//GEN-LAST:event_TipoRegistroCBActionPerformed

    private void FiltrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltrarBtnActionPerformed
        // TODO addRegistro your handling code here:
        try{
            listarRegistros(FechaInicialDate.getCalendar(), FechaFinalDate.getCalendar());
        }catch(IllegalArgumentException | FechaInicialMayorAFechaFinalException | FechaFinalMenorAFechaInicialException | FechaInicialIgualAFechaFinalException ex){
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado paso...", "Mensaje", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_FiltrarBtnActionPerformed

    private void VerRegistroBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerRegistroBtnActionPerformed
        // TODO addRegistro your handling code here:
        int row = RegistrosTabla.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un registro.", "Error", JOptionPane.OK_OPTION);
            return;
        }
        String idRegistro = Integer.toString((Integer) RegistrosTabla.getValueAt(row, 0));
        
        RegistroGUI registroGUI = new RegistroGUI(null, true);
        
        try {
            registroGUI.cargarDatos(idRegistro);
            registroGUI.setLocationRelativeTo(this);
            registroGUI.setVisible(true);
        } catch (RegistroNoExistenteException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Algo inesperado paso...", "Mensaje", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_VerRegistroBtnActionPerformed

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
            java.util.logging.Logger.getLogger(RegistrosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RegistrosGUI dialog = new RegistrosGUI(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton CerrarBtn;
    private javax.swing.JCheckBox FechaCheckB;
    private com.toedter.calendar.JDateChooser FechaFinalDate;
    private com.toedter.calendar.JDateChooser FechaInicialDate;
    private javax.swing.JButton FiltrarBtn;
    private javax.swing.JTable RegistrosTabla;
    private javax.swing.JComboBox TipoRegistroCB;
    private javax.swing.JButton VerRegistroBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
