/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.CampusDAO;
import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.CampusExistenteExeption;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoAgregadoException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.interfaces.CampusDAOInterface;
import edu.ucue.jparking.srv.objetos.Campus;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;


/**
 * 
 * @author Franklin
 */
class CampusService {
    Validaciones validar = new Validaciones();
    CampusDAOInterface campusDAO;

    public CampusService() throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        campusDAO = CampusDAO.getInstancia();
    }    
    
    public Campus addCampus(String nombreCampus, String direccion) 
            throws CampusExistenteExeption, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException 
    {
        validar.ValidarCampus(nombreCampus, direccion);
        Campus campus = new Campus(nombreCampus, direccion);
        campusDAO.addCampus(campus);
        return campus;
    }

    public Campus getCampus(String nombreCampus)
            throws CampusNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException
    {
        if(nombreCampus==null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El nombre del campus no puede estar vacio");
        return campusDAO.getCampus(nombreCampus);
    }
    
    public void modCampus(String nombreCampus, String direccion, boolean estado)
            throws CampusNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException
    {
        validar.ValidarCampus(nombreCampus, direccion);
        campusDAO.modCampus(nombreCampus, direccion, estado);
    }
   
    public void delCampus(String nombreCampus)
            throws CampusNoExistenteException, ParqueaderoNoExistenteException,
            UsuarioNoExistenteException, UsuarioNoAgregadoException, IOException,
            ClassNotFoundException, ObjectSizeException
    {
        if(nombreCampus==null || nombreCampus.trim().length()==0)
            throw new IllegalArgumentException("El nombre del campus no puede estar vacio");
        campusDAO.delCampus(nombreCampus);
    }
    
    
    public Set<Campus> getCampus() throws IOException, FileNotFoundException, ClassNotFoundException{
        return campusDAO.getCampus();
    }
}
