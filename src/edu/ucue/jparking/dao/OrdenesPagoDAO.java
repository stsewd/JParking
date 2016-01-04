/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.interfaces.OrdenPagoNoExistenteException;
import edu.ucue.jparking.dao.interfaces.OrdenesPagoDAOInterface;
import edu.ucue.jparking.srv.objetos.OrdenPago;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public class OrdenesPagoDAO implements OrdenesPagoDAOInterface {
    private static Set<OrdenPago> ordenesPago;
    
    private static OrdenesPagoDAO instance;
    private OrdenesPagoDAO() {
        ordenesPago = new LinkedHashSet<>();
    }

    public static OrdenesPagoDAO getInstance() {
        if(instance == null)
            instance = new OrdenesPagoDAO();
        return instance;
    }    

    @Override
    public void addOrdenPago(OrdenPago ordenPago) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrdenPago getOrdenPago(int numeroOrdenPago) throws OrdenPagoNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<OrdenPago> getOrdenesPago() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<OrdenPago> getOrdenesPago(Calendar fechaInicial, Calendar fechaFinal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getFondos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getFondos(Calendar fechaInicial, Calendar fechaFinal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
