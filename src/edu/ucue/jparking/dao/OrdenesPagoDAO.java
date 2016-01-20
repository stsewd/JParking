/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.bptree.BPTreeMap;
import edu.ucue.jparking.dao.bptree.ComparatorInt;
import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.interfaces.OrdenPagoNoExistenteException;
import edu.ucue.jparking.dao.interfaces.OrdenesPagoDAOInterface;
import edu.ucue.jparking.srv.objetos.OrdenPago;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    // private static Map<Integer, OrdenPago> ordenesPago;
    private static BPTreeMap<Integer, OrdenPago> ordenesPago;
    private static final String dataPath = "data/ordenes_pago.dat";
    private static final String indiceNum = "data/ordenes_pago_num_index.dat";
    private static final int objSize = 9999;
    
    private static OrdenesPagoDAO instance;
    private OrdenesPagoDAO() throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        // ordenesPago = new LinkedHashMap<>();
        ordenesPago = BPTreeMap.getTree(3, new ComparatorInt(), dataPath, indiceNum, objSize, 1500);
    }

    public static OrdenesPagoDAO getInstance() throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        if(instance == null)
            instance = new OrdenesPagoDAO();
        return instance;
    }    

    @Override
    public void addOrdenPago(OrdenPago ordenPago)
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        ordenPago.setNumeroOrdenPago(ordenesPago.size());
        ordenesPago.put(ordenPago.getNumeroOrdenPago(), ordenPago);
    }

    @Override
    public OrdenPago getOrdenPago(int numeroOrdenPago)
            throws OrdenPagoNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException
    {
        OrdenPago ordenPago = ordenesPago.get(numeroOrdenPago);
        if(ordenPago == null)
            throw new OrdenPagoNoExistenteException(numeroOrdenPago);
        return ordenPago;
    }

    @Override
    public Set<OrdenPago> getOrdenesPago()
            throws IOException, FileNotFoundException, ClassNotFoundException
    {
        return new LinkedHashSet<>(ordenesPago.values());
    }

    @Override
    public Set<OrdenPago> getOrdenesPago(Calendar fechaInicial, Calendar fechaFinal)
            throws IOException, FileNotFoundException, ClassNotFoundException
    {
        Set<OrdenPago> ordenesPago = new LinkedHashSet<>();
        for(OrdenPago o : getOrdenesPago()){
            if(o.getFechaEmision().after(fechaInicial) && o.getFechaEmision().before(fechaFinal))
                ordenesPago.add(o);
            if(o.getFechaEmision().after(fechaFinal))
                break;
        }
        return ordenesPago;
    }

    @Override
    public double getFondos() throws IOException, FileNotFoundException, ClassNotFoundException {
        double fondos = 0;
        for(OrdenPago o : ordenesPago.values())
            fondos += o.getCosto();
        return fondos;
    }

    @Override
    public double getFondos(Calendar fechaInicial, Calendar fechaFinal)
            throws IOException, FileNotFoundException, ClassNotFoundException
    {
        double fondos = 0;
        for(OrdenPago o : getOrdenesPago(fechaInicial, fechaFinal))
            fondos += o.getCosto();
        return fondos;
    }
}
