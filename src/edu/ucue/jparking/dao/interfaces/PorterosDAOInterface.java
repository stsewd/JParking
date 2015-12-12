/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.srv.objetos.Portero;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public interface PorterosDAOInterface {
    public void addPortero(Portero portero);
    public void delPortero(String cedula);
    public Portero getPortero(String cedula);
    public Set<Portero> getPorteros();
    public void modPortero(String cedula, String nombres, String apellidos, boolean activo);
}
