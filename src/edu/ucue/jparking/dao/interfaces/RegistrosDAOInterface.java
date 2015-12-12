/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.srv.registros.Registro;
import java.util.Calendar;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public interface RegistrosDAOInterface {
    public void addRegistro(Registro registro);
    public Set<Registro> getRegistros(Calendar fechaInicial, Calendar fechaFinal);
    public Set<Registro> getRegistros();
}
