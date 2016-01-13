/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.dao.ClaveDAO;

/**
 *
 * @author Franklin
 */
public interface ClaveDAOInterface {
    
    /**
     * Valida el login de ingreso de un usuario
     * @param usuario
     * @param clave
     * @return 
     */
    public boolean validarClave(String usuario,String clave);
    
    
    //public ClaveDAO getInstancia();
}
