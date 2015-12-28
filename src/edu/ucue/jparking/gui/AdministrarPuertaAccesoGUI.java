/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.gui;

import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.srv.ParqueaderoService;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Puerta;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lara
 */
public class AdministrarPuertaAccesoGUI extends javax.swing.JDialog {

    /**
     * Creates new form AdministrarPuertaAcceso
     */
    public AdministrarPuertaAccesoGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        jButton3 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaPuertasEntrada = new javax.swing.JTable();
        AgregarEntradaBtn = new javax.swing.JButton();
        EliminarEntradaBtn = new javax.swing.JButton();
        CerrarBtn = new javax.swing.JButton();
        idParqueaderolbl = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaPuertasSalida = new javax.swing.JTable();
        EliminarSalidaBtn1 = new javax.swing.JButton();
        CerrarBtn1 = new javax.swing.JButton();
        AgregarSalidaBtn1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        CampusTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ParqueaderoTF = new javax.swing.JTextField();
        idCampuslbl = new javax.swing.JLabel();

        jButton3.setText("jButton3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrador de Puertas de Acceso");
        setMinimumSize(new java.awt.Dimension(452, 470));

        TablaPuertasEntrada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Id", "Ubicación"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaPuertasEntrada.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TablaPuertasEntrada.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TablaPuertasEntrada);
        if (TablaPuertasEntrada.getColumnModel().getColumnCount() > 0) {
            TablaPuertasEntrada.getColumnModel().getColumn(0).setMinWidth(30);
            TablaPuertasEntrada.getColumnModel().getColumn(0).setPreferredWidth(30);
            TablaPuertasEntrada.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        AgregarEntradaBtn.setText("Agregar");
        AgregarEntradaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarEntradaBtnActionPerformed(evt);
            }
        });

        EliminarEntradaBtn.setText("Eliminar");
        EliminarEntradaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarEntradaBtnActionPerformed(evt);
            }
        });

        CerrarBtn.setText("Cerrar");
        CerrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(AgregarEntradaBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EliminarEntradaBtn)
                        .addGap(100, 100, 100)
                        .addComponent(idParqueaderolbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CerrarBtn)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AgregarEntradaBtn)
                    .addComponent(EliminarEntradaBtn)
                    .addComponent(CerrarBtn)
                    .addComponent(idParqueaderolbl))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Puertas de Entrada", jPanel1);

        TablaPuertasSalida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Id", "Ubicacion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TablaPuertasSalida);
        if (TablaPuertasSalida.getColumnModel().getColumnCount() > 0) {
            TablaPuertasSalida.getColumnModel().getColumn(0).setMinWidth(30);
            TablaPuertasSalida.getColumnModel().getColumn(0).setPreferredWidth(30);
            TablaPuertasSalida.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        EliminarSalidaBtn1.setText("Eliminar");
        EliminarSalidaBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarSalidaBtn1ActionPerformed(evt);
            }
        });

        CerrarBtn1.setText("Cerrar");
        CerrarBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarBtn1ActionPerformed(evt);
            }
        });

        AgregarSalidaBtn1.setText("Agregar");
        AgregarSalidaBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarSalidaBtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(AgregarSalidaBtn1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EliminarSalidaBtn1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CerrarBtn1)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AgregarSalidaBtn1)
                    .addComponent(EliminarSalidaBtn1)
                    .addComponent(CerrarBtn1))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Puertas de Salida", jPanel3);

        jLabel1.setText("Campus:");

        CampusTF.setEditable(false);

        jLabel2.setText("Parqueadero:");

        ParqueaderoTF.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CampusTF, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(ParqueaderoTF))
                        .addGap(15, 15, 15)
                        .addComponent(idCampuslbl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(CampusTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ParqueaderoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idCampuslbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listarPuertasEntradas() throws CodigoNoValidoException, ParqueaderoNoExistenteException, CampusNoExistenteException{
        ParqueaderoService service = new ParqueaderoService();
        Set<Puerta> puertaEntrada = service.getPuertasEntrada(idParqueaderolbl.getText());
        
        DefaultTableModel model = (DefaultTableModel) TablaPuertasEntrada.getModel();
        
        //Borrar elementos anteriores
        for(int i = model.getRowCount() - 1; i >= 0 ; i--)
            model.removeRow(i);
        
        int n = 1;
        for(Puerta p : puertaEntrada)
            model.addRow(new Object[]{n++, p.getId(), p.getUbicacion()});
    }
    
    
    private void listarPuertasSalida() throws CodigoNoValidoException, ParqueaderoNoExistenteException, CampusNoExistenteException{
        ParqueaderoService service = new ParqueaderoService();
        Set<Puerta> puertaSalida = service.getPuertasSalida(idParqueaderolbl.getText());
        
        DefaultTableModel model = (DefaultTableModel) TablaPuertasSalida.getModel();
        
        //Borrar elementos anteriores
        for(int i = model.getRowCount() - 1; i >= 0 ; i--)
            model.removeRow(i);
        
        int n = 1;
        for(Puerta p : puertaSalida)
            model.addRow(new Object[]{n++, p.getId(), p.getUbicacion()});
    }
    private void AgregarEntradaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarEntradaBtnActionPerformed
        // TODO add your handling code here:
        AgregarPuertaAccesoGUI agregarPuertaAccesoGUI = new AgregarPuertaAccesoGUI(null, true);
        agregarPuertaAccesoGUI.cargarDatos(idParqueaderolbl.getText(), idCampuslbl.getText(), "entrada");
        agregarPuertaAccesoGUI.setLocationRelativeTo(this);
        agregarPuertaAccesoGUI.setVisible(true);
        try {
            listarPuertasEntradas();
        } catch (CodigoNoValidoException |IllegalArgumentException | ParqueaderoNoExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (CampusNoExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
        
        try {
            listarPuertasSalida();
        } catch (CodigoNoValidoException | IllegalArgumentException | ParqueaderoNoExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (CampusNoExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_AgregarEntradaBtnActionPerformed

    private void AgregarSalidaBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarSalidaBtn1ActionPerformed
        // TODO add your handling code here:
        AgregarPuertaAccesoGUI agregarPuertaAccesoGUI = new AgregarPuertaAccesoGUI(null, rootPaneCheckingEnabled);
        agregarPuertaAccesoGUI.setLocationRelativeTo(this);
        agregarPuertaAccesoGUI.cargarDatos(idParqueaderolbl.getText(), idCampuslbl.getText(), "salida");
        agregarPuertaAccesoGUI.setVisible(true);
        try {
            listarPuertasEntradas();
        } catch (CodigoNoValidoException |IllegalArgumentException | ParqueaderoNoExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (CampusNoExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
        
        try {
            listarPuertasSalida();
        } catch (CodigoNoValidoException | IllegalArgumentException | ParqueaderoNoExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (CampusNoExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_AgregarSalidaBtn1ActionPerformed

    private void CerrarBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarBtn1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        ///
    }//GEN-LAST:event_CerrarBtn1ActionPerformed

    private void CerrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarBtnActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_CerrarBtnActionPerformed

    private void EliminarSalidaBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarSalidaBtn1ActionPerformed
        // TODO add your handling code here:
        int row = TablaPuertasSalida.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un usuario.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        String idPuerta = (String) TablaPuertasSalida.getValueAt(row, 1);
        ParqueaderoService service = new ParqueaderoService();
        try {
            service.delPuertaSalida(idParqueaderolbl.getText(), idPuerta);
            JOptionPane.showMessageDialog(rootPane, "Puerta Eliminada existosamente", "Mensaje", JOptionPane.OK_OPTION);
        } catch (PuertaNoExistenteException | IllegalArgumentException | ParqueaderoNoExistenteException | CodigoNoValidoException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (CampusNoExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
        try {
            listarPuertasSalida();
        } catch (CodigoNoValidoException | IllegalArgumentException | ParqueaderoNoExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (CampusNoExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_EliminarSalidaBtn1ActionPerformed

    private void EliminarEntradaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarEntradaBtnActionPerformed

        // TODO add your handling code here:
        int row = TablaPuertasEntrada.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado un usuario.", "Mensaje", JOptionPane.OK_OPTION);
            return;
        }
        String idPuerta = (String) TablaPuertasEntrada.getValueAt(row, 1);
        ParqueaderoService service = new ParqueaderoService();
        try {
            service.delPuertaEntrada(idParqueaderolbl.getText(), idPuerta);
            JOptionPane.showMessageDialog(rootPane, "Puerta Eliminada existosamente", "Mensaje", JOptionPane.OK_OPTION);
        } catch (PuertaNoExistenteException | IllegalArgumentException | ParqueaderoNoExistenteException | CodigoNoValidoException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (CampusNoExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
        
        try {
            listarPuertasEntradas();
        } catch (CodigoNoValidoException | IllegalArgumentException | ParqueaderoNoExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        } catch (CampusNoExistenteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_EliminarEntradaBtnActionPerformed

    public void CargarDatos(String campus,String idParqueadero) throws ParqueaderoNoExistenteException, CodigoNoValidoException, CampusNoExistenteException{
        ParqueaderoService service = new ParqueaderoService();
        Parqueadero parqueadero =  service.getParqueadero(idParqueadero);
        ParqueaderoTF.setText("(" + idParqueadero + ") " + parqueadero.getUbicacion());
        CampusTF.setText(campus);
        idParqueaderolbl.setText(idParqueadero);
        idCampuslbl.setText(campus);
        idParqueaderolbl.setVisible(false);
        idCampuslbl.setVisible(false);
        listarPuertasEntradas();
        listarPuertasSalida();
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
            java.util.logging.Logger.getLogger(AdministrarPuertaAccesoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministrarPuertaAccesoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministrarPuertaAccesoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministrarPuertaAccesoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AdministrarPuertaAccesoGUI dialog = new AdministrarPuertaAccesoGUI(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton AgregarEntradaBtn;
    private javax.swing.JButton AgregarSalidaBtn1;
    private javax.swing.JTextField CampusTF;
    private javax.swing.JButton CerrarBtn;
    private javax.swing.JButton CerrarBtn1;
    private javax.swing.JButton EliminarEntradaBtn;
    private javax.swing.JButton EliminarSalidaBtn1;
    private javax.swing.JTextField ParqueaderoTF;
    private javax.swing.JTable TablaPuertasEntrada;
    private javax.swing.JTable TablaPuertasSalida;
    private javax.swing.JLabel idCampuslbl;
    private javax.swing.JLabel idParqueaderolbl;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
