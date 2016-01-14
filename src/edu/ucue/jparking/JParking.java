/*****************************************************************************
 * 
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Santos Gallegos, Franklin Lara
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 *****************************************************************************/


/******************************************************************************
 *  * Administrador de los parqueaderos ubicados en la Universidad de Cuenca
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
 * Cada campus cuenta con porteros.
 * Un paqueadero puede tener varias puertas de acceso y de salida.
 * Dentro de cada parqueadero se contará con puestos de parqueo.
 * El administrador podrá administrar los campus, parqueaderos, usuarios,
 * puertas, puestos de parqueo, porteros.
 * Los tipos de usuario existentes en el sistema son:
 *      Portero: quien tiene acceso a todas las puertas pertenecientes al
 *      campus donde pertenece.
 *      Docente, empleado, estudiante: Sólo tienen acceso a las puertas
 *      registradas de acceso/salida correspondientes al parqueadero donde
 *      pertece cada usuario.
 * 
******************************************************************************/

package edu.ucue.jparking;

import javax.swing.UIManager;
import edu.ucue.jparking.gui.LoginGUI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;

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
            //Look and feel para sistemas linux con gtk
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel"); 
        } 
        catch(Exception e){
            try {
                //Look and feel para sistemas windows
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (Exception ex) {
                //Carga interfaz por defecto de java swing
            }
        }
        File direc =  new  File("data"); 
        File backup = new File("backup");
        String path = "data\\usuario.dat";
        String path1 = "data\\clave,dat";
        File archivoUsuario = new File(path);
        File archivoClave = new File(path1);
        if(archivoUsuario.isDirectory() && archivoClave.isDirectory()){
            if(!(archivoUsuario.isFile() && archivoClave.isFile())){
                try {
                    ObjectOutputStream EntradaObjeto = new ObjectOutputStream(new FileOutputStream(direc));
                    String clave = "administrador";
                    //celebrum = clave;
                    EntradaObjeto.writeObject(clave);
                    
                    //escritura de clave encriptada en caso de no existir el archivo creado
                } catch (IOException ex) {
                    System.out.println("Error a abrir el archivo");
                }
            }
                
            
        }else{
            ObjectOutputStream salidaObjetosUsuarios = null;
            ObjectOutputStream salidaObjetostClave = null;
            try {
                direc.mkdirs();
                backup.mkdirs();
                salidaObjetosUsuarios = new ObjectOutputStream(new FileOutputStream(new File(direc,"usuarios.dat")));
                salidaObjetostClave  = new ObjectOutputStream(new FileOutputStream(new File(direc,"celebrum.dat")));
                //guardar clave encriptada
            
            } catch (FileNotFoundException ex) {
                Logger.getLogger(JParking.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(JParking.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    salidaObjetosUsuarios.close();
                    salidaObjetostClave.close();
                } catch (IOException ex) {
                    Logger.getLogger(JParking.class.getName()).log(Level.SEVERE, null, ex);
                }
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
