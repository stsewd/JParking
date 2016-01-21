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
import edu.ucue.jparking.srv.JP;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        LoginGUI loginGUI = new LoginGUI();
        loginGUI.setVisible(true);
    }
}
