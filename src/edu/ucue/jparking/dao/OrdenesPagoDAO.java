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
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public class OrdenesPagoDAO implements OrdenesPagoDAOInterface {
    private static Map<Integer, OrdenPago> ordenesPago;
    
    private static OrdenesPagoDAO instance;
    private OrdenesPagoDAO() {
        ordenesPago = new LinkedHashMap<>();
    }

    public static OrdenesPagoDAO getInstance() {
        if(instance == null)
            instance = new OrdenesPagoDAO();
        return instance;
    }    

    @Override
    public void addOrdenPago(OrdenPago ordenPago) {
        ordenPago.setNumeroOrdenPago(ordenesPago.size());
        ordenesPago.put(ordenPago.getNumeroOrdenPago(), ordenPago);
    }

    @Override
    public OrdenPago getOrdenPago(int numeroOrdenPago) throws OrdenPagoNoExistenteException {
        OrdenPago ordenPago = ordenesPago.get(numeroOrdenPago);
        if(ordenPago == null)
            throw new OrdenPagoNoExistenteException(numeroOrdenPago);
        return ordenPago;
    }

    @Override
    public Set<OrdenPago> getOrdenesPago() {
        return new LinkedHashSet<>(ordenesPago.values());
    }

    @Override
    public Set<OrdenPago> getOrdenesPago(Calendar fechaInicial, Calendar fechaFinal) {
        Set<OrdenPago> ordenesPago = new LinkedHashSet<>();
        for(OrdenPago o : getOrdenesPago()){
            if(o.getFechaEmision().before(fechaInicial) && o.getFechaEmision().after(fechaFinal))
                ordenesPago.add(o);
            if(o.getFechaEmision().after(fechaInicial))
                break;
        }
        return ordenesPago;
    }

    @Override
    public double getFondos() {
        double fondos = 0;
        for(OrdenPago o : ordenesPago.values())
            fondos += o.getCosto();
        return fondos;
    }

    @Override
    public double getFondos(Calendar fechaInicial, Calendar fechaFinal) {
        double fondos = 0;
        for(OrdenPago o : getOrdenesPago(fechaInicial, fechaFinal))
            fondos += o.getCosto();
        return fondos;
    }
}
