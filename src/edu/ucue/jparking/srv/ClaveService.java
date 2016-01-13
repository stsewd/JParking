/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.ClaveDAO;
import edu.ucue.jparking.dao.interfaces.ClaveDAOInterface;

/**
 *
 * @author Franklin
 */
public class ClaveService {
    
    ClaveDAOInterface oInterface = ClaveDAO.getInstancia();
    public boolean validarClave(String usuario,String clave){
        return oInterface.validarClave(usuario, clave);
    }
    
}
