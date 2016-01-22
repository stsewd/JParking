/**
 * Clase que permite el acceso a los campus registrados en el sistema
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.bptree.BPTreeMap;
import edu.ucue.jparking.dao.bptree.ComparatorString;
import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.CampusExistenteExeption;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoYaExistenteException;
import edu.ucue.jparking.dao.excepciones.PersonaYaRegistradaComoUsuarioException;
import edu.ucue.jparking.dao.excepciones.PorteroNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PorteroYaExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoAgregadaException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaYaAgregadaException;
import edu.ucue.jparking.dao.excepciones.PuertaYaExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoAgregadoException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaAgregadoException;
import edu.ucue.jparking.dao.interfaces.CampusDAOInterface;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Portero;
import edu.ucue.jparking.srv.objetos.Puerta;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Franklin Lara
 * @author Santos Gallegos
 */
public class CampusDAO implements CampusDAOInterface {
    private static CampusDAO instancia;
    
    // Mapa <Nombre de campus, campus>
    // private static Map<String, Campus> mapCampus;
    private static BPTreeMap<String, Campus> mapCampus;
    private static final String dataPath = "data/campus.dat";
    private static final String indiceNombreCampusPath = "data/campus_nombre_index.dat";
    private static final int objSize = 9999999;
    
    private CampusDAO()
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        mapCampus = BPTreeMap.getTree(3, new ComparatorString(), dataPath, indiceNombreCampusPath, objSize, 2500);
    }

    public static CampusDAO getInstancia()
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        if (instancia == null) {
            instancia = new CampusDAO();
        }
        return instancia;
    }

    @Override
    public void addCampus(Campus campus)
            throws CampusExistenteExeption, IOException, FileNotFoundException, ClassNotFoundException,
            ObjectSizeException
    {
        if (mapCampus.get(campus.getNombre()) != null) {
            throw new CampusExistenteExeption(campus.getNombre());
        }

        mapCampus.put(campus.getNombre(), campus);
    }

    @Override
    public void delCampus(String nombreCampus)
            throws CampusNoExistenteException, ParqueaderoNoExistenteException,
            UsuarioNoExistenteException, UsuarioNoAgregadoException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        
        if (mapCampus.get(nombreCampus) == null) {
            throw new CampusNoExistenteException(nombreCampus);
        }
        
        /*
         * Eliminar depencias:
         * Parqueaderos del campus a eliminar donde esta registrado un usuario.
         */
        for(Parqueadero p : ParqueaderosDAO.getInstance().getParqueaderos(nombreCampus)){
            ParqueaderosDAO.getInstance().delParqueadero(nombreCampus, p.getId());
        }
        
        mapCampus.remove(nombreCampus);
    }

    @Override
    public Campus getCampus(String nombre)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException
    {
        if (mapCampus.get(nombre) == null) {
            throw new CampusNoExistenteException(nombre);
        }
        return mapCampus.get(nombre);
    }

    @Override
    public void modCampus(String nombre, String ubicacion, boolean estado)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        if (mapCampus.get(nombre) == null) {
            throw new CampusNoExistenteException(nombre);
        }
        Campus cam = mapCampus.get(nombre);
        cam.setDireccion(ubicacion);
        cam.setActivo(estado);
        
        mapCampus.update(nombre, cam);
    }

    @Override
    public Set<Campus> getCampus()
            throws IOException, FileNotFoundException, ClassNotFoundException
    {
        return new LinkedHashSet<>(mapCampus.values());
    }

    @Override
    public void update(String nomrbreCampus, Campus campus)
            throws IOException, FileNotFoundException, ClassNotFoundException,
            ObjectSizeException
    {
        mapCampus.update(nomrbreCampus, campus);
    }
    
    // Parqueaderos
    
    public Parqueadero getParqueadero(String nombreCampus, String idParqueadero)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException
    {
        Campus campus = getCampus(nombreCampus);
        if(campus == null)
            throw new CampusNoExistenteException(nombreCampus);
        return campus.getParqueadero(idParqueadero);
    }
    
    public Set<Usuario> getUsuariosParqueadero(String nombreCampus, String idParqueadero)
            throws ParqueaderoNoExistenteException, CampusNoExistenteException,
            IOException, FileNotFoundException, ClassNotFoundException
    {
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        return new TreeSet<>(parqueadero.getUsuarios());
    }
    
    public void delUsuarioParqueadero(String nombreCampus, String idParqueadero, String cedula)
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException,
            UsuarioNoExistenteException, CampusNoExistenteException, UsuarioNoAgregadoException
    {
        Campus campus = getCampus(nombreCampus);
        UsuariosDAO.getInstance().getUsuario(cedula);
        Parqueadero parqueadero = campus.getParqueadero(idParqueadero);
        if(parqueadero.getUsuario(cedula) == null)
            throw new UsuarioNoAgregadoException(cedula);

        parqueadero.delUsuario(cedula);
        update(nombreCampus, campus);

        // campus.addParqueadero(idParqueadero, parqueadero);
        UsuariosDAO.getInstance().delParqueadero(cedula, nombreCampus, idParqueadero);
    }
    
    public void addParqueadero(String nombreCampus, Parqueadero parqueadero)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ParqueaderoYaExistenteException, ObjectSizeException
    {
        Campus campus = getCampus(nombreCampus);
        if(campus.getParqueadero(parqueadero.getId()) != null)
            throw new ParqueaderoYaExistenteException(parqueadero.getId());
        campus.addParqueadero(parqueadero.getId(), parqueadero);
        update(nombreCampus, campus);
    }
    
    public void delParqueadero(String nombreCampus, String idParqueadero)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ParqueaderoNoExistenteException, ObjectSizeException,
            UsuarioNoExistenteException, UsuarioNoAgregadoException
    {
        Campus campus = getCampus(nombreCampus);
        Parqueadero parqueadero = campus.getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        /*
         *  Eliminar dependencias
         * Eliminar parqueadero de todos los usuarios de este parqueadero.
         */
        for(Usuario usuario : parqueadero.getUsuarios()){
            parqueadero.delUsuario(usuario.getCedula());
            UsuariosDAO.getInstance().delParqueadero(usuario.getCedula(), nombreCampus, idParqueadero);
        }
        
        campus.delParqueadero(idParqueadero);
        update(nombreCampus, campus);
    }
    
    public Set<Parqueadero> getParqueaderos()
            throws IOException, FileNotFoundException, ClassNotFoundException
    {
        Set<Parqueadero> parqueaderos = new TreeSet<>();
        for(Campus c : getCampus())
            parqueaderos.addAll(c.getParqueaderos());
        return parqueaderos;
    }
    
    public Set<Parqueadero> getParqueaderos(String nombreCampus)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException
    {
        return new TreeSet<>(getCampus(nombreCampus).getParqueaderos());
    }
    
    public void modParqueadero(String nombreCampus, String idParqueadero, String ubicacion, int numLugares, boolean estado)
            throws ParqueaderoNoExistenteException, CampusNoExistenteException,
            IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        Campus campus = getCampus(nombreCampus);
        
        Parqueadero parqueadero = campus.getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        parqueadero.setUbicacion(ubicacion);
        parqueadero.setNumeroLugares(numLugares);
        parqueadero.setActivo(estado);
        
        update(nombreCampus, campus);
    }

    public void addPuertaEntradaParqueadero(String nombreCampus, String idParqueadero, String idPuerta)
            throws PuertaYaAgregadaException, PuertaNoExistenteException, CampusNoExistenteException,
            IOException, FileNotFoundException, ClassNotFoundException, ParqueaderoNoExistenteException, ObjectSizeException
    {
        Campus campus = getCampus(nombreCampus);
        Parqueadero parqueadero = campus.getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        Puerta puerta = campus.getPuerta(idPuerta);
        if(puerta == null)
            throw new PuertaNoExistenteException(idPuerta);
        if(parqueadero.getPuertaEntrada(idPuerta) != null)
            throw new PuertaYaAgregadaException(idPuerta);
        parqueadero.addPuertaEntrada(idPuerta, puerta);
        
        update(nombreCampus, campus);
    }
    
    public void addPuertaSalidaParqueadero(String nombreCampus, String idParqueadero, String idPuerta)
            throws CampusNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException,
            ParqueaderoNoExistenteException, PuertaNoExistenteException, PuertaYaAgregadaException, ObjectSizeException
    {
        Campus campus = getCampus(nombreCampus);
        Parqueadero parqueadero = campus.getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        Puerta puerta = campus.getPuerta(idPuerta);
        if(puerta == null)
            throw new PuertaNoExistenteException(idPuerta);
        if(parqueadero.getPuertaSalida(idPuerta) != null)
            throw new PuertaYaAgregadaException(idPuerta);
        parqueadero.addPuertaSalida(idPuerta, puerta);
        
        update(nombreCampus, campus);
    }
    
    public void delPuertaEntradaParqueadero(String nombreCampus, String idParqueadero, String idPuerta)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ParqueaderoNoExistenteException, PuertaNoExistenteException,
            PuertaNoAgregadaException, ObjectSizeException
    {
        Campus campus = getCampus(nombreCampus);
        Parqueadero parqueadero = campus.getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        if(campus.getPuerta(idPuerta) == null)
            throw new PuertaNoExistenteException(idPuerta);
        if(parqueadero.getPuertaEntrada(idPuerta) == null)
            throw new PuertaNoAgregadaException(idPuerta);
        parqueadero.delPuertaEntrada(idPuerta);
        
        update(nombreCampus, campus);
    }
    
    public void delPuertaSalidaParqueadero(String nombreCampus, String idParqueadero, String idPuerta)
            throws ParqueaderoNoExistenteException, CampusNoExistenteException, IOException,
            FileNotFoundException, ClassNotFoundException, PuertaNoExistenteException,
            PuertaNoAgregadaException, ObjectSizeException
    {
        Campus campus = getCampus(nombreCampus);
        Parqueadero parqueadero = campus.getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        if(campus.getPuerta(idPuerta) == null)
            throw new PuertaNoExistenteException(idPuerta);
        if(parqueadero.getPuertaSalida(idPuerta) == null)
            throw new PuertaNoAgregadaException(idPuerta);
        parqueadero.delPuertaSalida(idPuerta);
        
        update(nombreCampus, campus);
    }
    
    public void addUsuarioParqueadero(String nombreCampus, String idParqueadero, String cedula)
            throws ParqueaderoNoExistenteException, CampusNoExistenteException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException,
            UsuarioNoExistenteException, UsuarioYaAgregadoException
    {
        Campus campus = getCampus(nombreCampus);
        Parqueadero parqueadero = campus.getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        
        Usuario usuario = UsuariosDAO.getInstance().getUsuario(cedula);
        
        if(parqueadero.getUsuario(cedula) != null)
            throw new UsuarioYaAgregadoException(cedula);
        
        parqueadero.addUsuario(cedula, usuario);
        UsuariosDAO.getInstance().addPaqueadero(cedula, nombreCampus, idParqueadero);
        
        update(nombreCampus, campus);
    }
    
    public Set<Puerta> getPuertasEntradaParqueadero(String nombreCampus, String idParqueadero)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ParqueaderoNoExistenteException
    {
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        return new TreeSet<>(parqueadero.getPuertasEntrada());
    }

    public Set<Puerta> getPuertasSalida(String nombreCampus, String idParqueadero)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ParqueaderoNoExistenteException
    {
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        return new TreeSet<>(parqueadero.getPuertasSalida());
    }
    

    // Puertas
    
    public Puerta getPuerta(String nombreCampus, String idPuerta)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException
    {
        Campus campus = getCampus(nombreCampus);
        Puerta puerta = campus.getPuerta(idPuerta);
        return puerta;
    }
    
    public Set<Puerta> getPuertas(String nombreCampus)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        return new TreeSet<>(getCampus(nombreCampus).getPuertas());
    }

    public Set<Puerta> getPuertas()
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        Set<Puerta> puertas = new TreeSet<>();
        for(Campus c : getCampus()){
            puertas.addAll(c.getPuertas());
        }
        return puertas;
    }
    
    public void modPuerta(String nombreCampus, String id, String ubicacion, boolean activa)
            throws PuertaNoExistenteException, CampusNoExistenteException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        Campus campus = getCampus(nombreCampus);
        Puerta puerta = campus.getPuerta(id);
        if(puerta == null)
            throw new PuertaNoExistenteException(id);
        puerta.setUbicacion(ubicacion);
        puerta.setActiva(activa);
        
        update(nombreCampus, campus);
    }
    
    public void addPuerta(String nombreCampus, Puerta puerta)
            throws PuertaYaExistenteException, CampusNoExistenteException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        Campus campus = getCampus(nombreCampus);
        if(campus.getPuerta(puerta.getId()) != null)
            throw new PuertaYaExistenteException(puerta.getId());
        campus.addPuerta(puerta.getId(), puerta);
        
        update(nombreCampus, campus);
    }
    
    public void delPuerta(String nombreCampus, String idPuerta)
            throws PuertaNoExistenteException, CampusNoExistenteException,
            ParqueaderoNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        Campus campus = getCampus(nombreCampus);
        Puerta puerta = campus.getPuerta(idPuerta);
        if(puerta == null)
            throw new PuertaNoExistenteException(idPuerta);
        /*******************************
         * Eliminar dependencias
         * Eliminar puertas de entrada/salida de todos los parqueaderos del campus
        ***********************************/
        for(Parqueadero p : campus.getParqueaderos()){
            p.delPuertaEntrada(idPuerta);
            // delPuertaEntradaParqueadero(nombreCampus, p.getId(), idPuerta);
            p.delPuertaSalida(idPuerta);
            // delPuertaSalidaParqueadero(nombreCampus, p.getId(), idPuerta);
        }
        campus.delPuerta(idPuerta);
        
        update(nombreCampus, campus);
    }
    
    // Porteros
    public void addPortero(String nombreCampus, Portero portero)
            throws CampusNoExistenteException, PorteroYaExistenteException, PersonaYaRegistradaComoUsuarioException,
            IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException 
    {
        Campus campus = getCampus(nombreCampus);
        if(campus.getPortero(portero.getCedula()) != null)
            throw new PorteroYaExistenteException(portero.getCedula());
        try{
            UsuariosDAO.getInstance().getUsuario(portero.getCedula());
            throw new PersonaYaRegistradaComoUsuarioException(portero.getCedula());
        }catch (UsuarioNoExistenteException ex){
            campus.addPortero(portero.getCedula(), portero);
            update(nombreCampus, campus);
        }
    }

    public void delPortero(String cedula)
            throws PorteroNoExistenteException, CampusNoExistenteException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        Portero portero = getPortero(cedula);
        if(portero == null)
            throw new PorteroNoExistenteException(cedula);
        Campus campus = getCampus(portero.getCampus());
        campus.delPortero(cedula);
        
        update(campus.getNombre(), campus);
    }

    public void modPortero(String cedula, String nombres, String apellidos, String direccion, String telefono, boolean activo)
            throws PorteroNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException, CampusNoExistenteException
    {
        Portero portero = getPortero(cedula);
        if(portero == null)
            throw new PorteroNoExistenteException(cedula);
        Campus campus = getCampus(portero.getCampus());
        portero.setActivo(activo);
        portero.setApellidos(apellidos);
        portero.setNombres(nombres);
        portero.setDireccion(direccion);
        portero.setTelefono(telefono);
        
        update(campus.getNombre(), campus);
    }

    public Portero getPortero(String cedula)
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        Portero portero;
        for(Campus c : getCampus()){
            portero = c.getPortero(cedula);
            if(portero != null)
                return portero;
        }
        return null;
    }

    public Set<Portero> getPorteros()
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        Set<Portero> porteros = new TreeSet<>(); 
        for(Campus c : CampusDAO.getInstancia().getCampus())
            porteros.addAll(c.getPorteros());
        return porteros;
    }

    public Set<Portero> getPorteros(String nombreCampus)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        return new TreeSet<>(getCampus(nombreCampus).getPorteros());
    }
}
