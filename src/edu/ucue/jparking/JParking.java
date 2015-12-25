/**
 * Administrador de los parqueaderos ubicados en la Universidad de Cuenca
 * 
 * @author Santos Gallegos <santos_g@outlook.com>
 * @author  Franklin Lara <larafranklin@outlook.com> 
 *  
 * @version 18/12/2015
 *
 * Descripcion del programa:
 * Permite al adminstrador crear campus, parqueaderos dentro de estos.
 * Dentro de un campus se dentrá puertas de ingreso y/o salida hacia los
 * paqrqueaderos.
 * Un paqueadero puede tener varias puertas de acceso y de salida.
 * Dentro de cada parqueadero se contará con puestos de parqueo.
 * El número de usuarios de un parqueadero puede ser como máximo el 
 * 150% de los puestos de parqueo disponibles en el parqueadero.
 * El administrador podrá administrar los campus, parqueaderos, usuarios,
 * puertas, puestos de parqueo, porteros.
 * Los tipos de usuario existentes en el sistema son:
 *      Portero: quien tiene acceso a todas las puertas pertenecientes al
 *      campus donde pertenece.
 *      Docente, empleado, estudiante: Sólo tienen acceso a las puertas
 *      registradas de acceso/salida correspondientes al parqueadero donde
 *      pertece cada usuario.
 * 
 */
package edu.ucue.jparking;

import edu.ucue.jparking.dao.excepciones.UsuarioYaExistenteException;
import edu.ucue.jparking.gui.PrincipalGUI;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.TelefonoNoValidoException;
import javax.swing.UIManager;

/**
 *
 * @author Santos Gallegos
 * @author Franklin Lara
 */
public class JParking {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //PrincipalGUI.main(args);
        //PrincipalGUI pgui = new PrincipalGUI();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel"); 
        } 
        catch(Exception e){
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (Exception ex) {
                //Carga interfaz por defecto
            }
        }
        PrincipalGUI pgui = new PrincipalGUI();
        try {
            //Tests
            Test.cargarUsuarios();
            pgui.listarUsuarios();
        } catch (UsuarioYaExistenteException | CedulaNoValidaException | TelefonoNoValidoException ex) {
            System.out.println(ex.getMessage());
        }
        pgui.setVisible(true);
    }
}
