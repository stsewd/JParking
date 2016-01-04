/*****************************************************************************
 * Administrador de los parqueaderos ubicados en la Universidad de Cuenca
 * 
 * @author Santos Gallegos <santos_g@outlook.com>
 * @author  Franklin Lara <larafranklin@outlook.com> 
 *  
 * @version 0.1.3
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
 *****************************************************************************/
package edu.ucue.jparking;

import javax.swing.UIManager;


import edu.ucue.jparking.gui.LoginGUI;


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
        LoginGUI loginGUI = new LoginGUI();
        loginGUI.setVisible(true);
        /*PrincipalGUI pgui = new PrincipalGUI();
        /*
        //Inicio de tests
        try {
            Test.cargarUsuarios();
            pgui.listarUsuarios();
        }catch (UsuarioYaExistenteException | CedulaNoValidaException | TelefonoNoValidoException ex) {
            System.out.println(ex.getMessage());
        }catch (PersonaYaRegistradoComoPorteroException ex) {
            System.out.println(ex.getMessage());
        } catch (UsuarioNoExistenteException ex) {
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
        }catch (CedulaNoValidaException | CampusNoExistenteException | PorteroYaExistenteException | TelefonoNoValidoException ex) {
            System.out.println(ex.getMessage());
        }catch (PersonaYaRegistradaComoUsuarioException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            Test.cargarPuertas(35);
        }catch (CodigoNoValidoException | PuertaYaExistenteException | CampusNoExistenteException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            Test.cargarUsuariosParqueaderos();
            pgui.listarParqueaderos();
        } catch (CedulaNoValidaException | CodigoNoValidoException | ParqueaderoNoExistenteException | UsuarioNoExistenteException | ParquaderoInactivoException | NumeroParqueaderosNoDisponiblesException | UsuarioInactivoException | CampusNoExistenteException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            Test.cargarPuertasParqueaderos();
        } catch (CampusInactivoException | CampusNoExistenteException | ParqueaderoNoExistenteException | PuertaNoExistenteException | CodigoNoValidoException | ParquaderoInactivoException | PuertaYaExistenteException | PuertaInactivaException ex) {
            System.out.println(ex.getMessage());
        }
        //Fin de tests
        /*
        pgui.setVisible(true);*/
    }
}
