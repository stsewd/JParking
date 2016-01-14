/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking;

import edu.ucue.jparking.dao.excepciones.CampusExistenteExeption;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoYaExistenteException;
import edu.ucue.jparking.dao.excepciones.PersonaYaRegistradaComoUsuarioException;
import edu.ucue.jparking.dao.excepciones.PersonaYaRegistradoComoPorteroException;
import edu.ucue.jparking.dao.excepciones.PorteroYaExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaYaAgregadaException;
import edu.ucue.jparking.dao.excepciones.PuertaYaExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaAgregadoException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaExistenteException;
import edu.ucue.jparking.srv.CampusService;
import edu.ucue.jparking.srv.ParqueaderoService;
import edu.ucue.jparking.srv.PorterosService;
import edu.ucue.jparking.srv.PuertaService;
import edu.ucue.jparking.srv.UsuarioService;
import edu.ucue.jparking.srv.Utilidades;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import edu.ucue.jparking.srv.excepciones.CampusInactivoException;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.srv.excepciones.NumeroParqueaderosNoDisponiblesException;
import edu.ucue.jparking.srv.excepciones.ParquaderoInactivoException;
import edu.ucue.jparking.srv.excepciones.PuertaInactivaException;
import edu.ucue.jparking.srv.excepciones.TelefonoNoValidoException;
import edu.ucue.jparking.srv.excepciones.UsuarioInactivoException;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Puerta;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Santos Gallegos
 * @author Franklin Lara
 */
public class Test {
    
    //Usuarios
    private static final String[][] usuarios = {
        {"0106228414", "ABAD CONTRERAS", "DAVID LEONARDO"},
        {"0104806625", "ABAD GONZALEZ", "JOSE ANTONIO"},
        {"0104496765", "ABAD LEON", "FREDDY LEONARDO"},
        {"0302618319", "ABAD REGALADO", "LUIS MIGUEL"},
        {"1105596991", "ABAD SIGCHO", "CARLOS DANIEL"},
        {"0105871487", "ABRIL GUAMANCELA", "DIEGO VINICIO"},
        {"0106909807", "BACUILIMA MUÑOZ", "ALVARO ROMAN"},
        {"0104162763", "BACUILIMA OLEAS", "MARCO STALIN"},
        {"0105287965", "BALCAZAR MALDONADO", "CRISTINA ALEJANDRA"},
        {"0106743651", "BANEGAS DUTAN", "STALIN FERNANDO"},
        {"0107297145", "BARRERA BERMEO", "XAVIER EDWARD"},
        {"0105634554", "BARRERA CAJAS", "JONNATHAN MAURICIO"},
        {"0105597983", "BARRERA CORNEJO", "CARLOS ALEJANDRO"},
        {"0105244073", "BARRERA SALAMEA", "DAVID VICENTE"},
        {"0104830195", "BARRERA ZUÑIGA", "JORGE LUIS"},
        {"0105277099", "BARRETO PAREDES", "CHRISTIAN FABIAN"},
        {"0106729528", "BARRIGA LEON", "PABLO ADRIAN"},
        {"0107141061", "BARRIGA ZHINDON", "MARIA JOSE"},
        {"0105680755", "BARROS BRAVO", "BELEN CAROLINA"},
        {"0706455136", "GALLEGOS CEVILLA", "SANTOS EDUARDO"},
        {"0103976536", "GARATE ORTIZ", "JUAN DIEGO"},
        {"0104490198", "GARAY GUALPA", "EDGAR ANTOLIN"},
        {"0105970099", "GARCIA BRITO", "PABLO SEBASTIAN"},
        {"0104647532", "GARCIA CALLE", "ANDRES FERNANDO"},
        {"0104638507", "GARCIA CLAVIJO", "EDGAR ANDRES"},
        {"0105979314", "GARCIA MONRROY", "GEOVANNY XAVIER"},
        {"0302639059", "GARCÍA NOVILLO", "MARÍA JOSÉ"},
        {"0105955082", "GARCIA PESANTEZ", "LUIS EMILIO"},
        {"0302712823", "GARCIA ROJAS", "SHIMABEL CRISTINA"},
        {"0105890404", "GARNICA BAUTISTA", "FERNANDO XAVIER"},
        {"0302025242", "GARZON VAZQUEZ", "CHRISTIAN MARCELO"},
        {"1400718977", "GARZON VERA", "JONNY EDUARDO"},
        {"0104040738", "GAVILANES", "SARMIENTO CHRISTIAN RICARDO"},
        {"0105706493", "GENOVEZ", "RAMIREZ KARLA GABRIELA"},
        {"1104339476", "JAYA CABRERA", "FABIAN ENRIQUE"},
        {"0104502273", "JERVES COELLO", "RUBEN ANDRES"},
        {"0105201909", "JIMBO TACURI", "VERONICA GABRIELA"},
        {"0104284492", "JIMENEZ AVILA", "JOHN ISRAEL"},
        {"0105272975", "JIMENEZ BARRETO", "DAMIAN MARCELO"},
        {"1721542734", "JIMENEZ CAIZA", "FRANCISCO JAVIER"},
        {"1104336183", "JIMÉNEZ MENDIETA", "DANIELA DOLORES"},
        {"1103876585", "JIMENEZ SANCHEZ", "JUAN DIEGO"},
        {"0105266217", "JIMENEZ SIGUENZA", "STALIN MANUEL"},
        {"0106822000", "JUCA SAENZ", "RAUL HERNAN"},
        {"0105137665", "JURADO MOGROVEJO", "JULIO CESAR"},
        {"0705520674", "LAM ARMIJOS", "RONNY ALEXANDER"},
        {"0105905913", "LANDIVAR ORDOÑEZ", "TANIA MARICELA"},
        {"0105036024", "LARA SANMARTIN", "FRANKLIN ENRIQUE"},
        {"1400773881", "LARA SERRANO", "JAIRO MIGUEL"},
        {"0104959523", "LARRIVA BECERRA", "JUAN DIEGO"},
        {"0105763080", "LARRIVA CASTILLO", "PABLO EDUARDO"},
        {"0106522030", "LASSO LAZO", "EDGAR PATRICIO"},
        {"0105385538", "LASSO SERPA", "KARINA DOMÉNICA"},
        {"0105688816", "LATA CHUQUI", "MILTON GEOVANNY"}
    };
    
    //Porteros
    private static final String[][] porteros = {
        {"0105456883", "RAIBAN VERA", "ELVIS FABIAN"},
        {"0104800164", "RAMOS CORONEL", "PABLO ESTEBAN"},
        {"0105561989", "REA ASMAL", "SEBASTIAN ALEJANDRO"},
        {"0105742811", "REA ORELLANA", "WILLIAM FERNANDO"},
        {"0105175038", "REIBÁN MOROCHO", "GILSON XAVIER"},
        {"0302076955", "REINO ABAD", "PAOLA GABRIELA"},
        {"0105812465", "REINO CHERREZ", "MATEO JUAN"},
        {"0302991104", "REINO CRIOLLO", "RUTH ALEXANDRA"},
        {"0105389993", "REINO PARRA", "CHRISTIAN ALEJANDRO"},
        {"0105547376", "REINOSO DELGADO", "JOEL FRANCISCO"},
        {"0302712856", "REINOSO QUINTEROS", "JOHNNY GABRIEL"},
        {"1400757116", "REINOZO TRELLES", "EDISSON SANTIAGO"},
        {"0106419534", "REMACHE FARINANGO", "PAOLA MARIBEL"},
        {"0105730667", "REMACHE ORELLANA", "JORGE MAURICIO"},
        {"0106633449", "RENDON LOJA", "RONALDO SEBASTIAN"},
        {"0105925077", "REYES ESPINOZA", "JONNATHAN FABRICIO"},
        {"0106433816", "RIOS CEDILLO", "ESTEBAN JAVIER"},
        {"1400682710", "RIVADENEIRA", "ERAZO THALIA GABRIELA"},
        {"1104660962", "RIVAS", "TORRES CIDER"},
        {"0105822787", "RIVAS VÁSQUEZ", "CARLOS EDUARDO"},
        {"0105180533", "RIVERA GUTIERREZ", "LUIS XAVIER"},
        {"0106728884", "RIVERA ORTIZ", "LENIN MARCELO"}
    };
    
    //Tipos de usuario
    private static final TipoUsuario[] tipoUsuario = {TipoUsuario.DOCENTE, TipoUsuario.EMPLEADO, TipoUsuario.ESTUDIANTE};
    
    
    //Campus
    private static final String[][] campus = {
        {"Paraiso" + Utilidades.fill(30 - "Paraiso".length()), "Direccion y"},
        {"Central" + Utilidades.fill(30 - "Central".length()), "Direccion y"},
        {"Balzay" + Utilidades.fill(30 - "Balzay".length()), "Direccion y"},
        {"Centro Historico" + Utilidades.fill(30 - "Centro Historico".length()), "Direccion y"},
        {"Yanuncay" + Utilidades.fill(30 - "Yanuncay".length()), "Direccion y"},
    };
    
    
    public static void cargarUsuarios() throws UsuarioYaExistenteException, CedulaNoValidaException, TelefonoNoValidoException, PersonaYaRegistradoComoPorteroException, UsuarioNoExistenteException{
        UsuarioService us = new UsuarioService();
        /*
        us.add("0706455136", "Santos", "Gallegos", "Calle", "1234567890", "ESTUDIANTE");
        Usuario u = us.get("0706455136");
        
        try {
            byte[] b;
            b = serialize(u);
            System.out.println(b.length);
        } catch (IOException ex) {
        } 
        /*/
        for(int i = 0; i<usuarios.length; i++)
            us.add(usuarios[i][0], usuarios[i][2], usuarios[i][1], "Direccion z", "1234567890", tipoUsuario[(int) ((Math.random()*100)%3)].toString());
    }
    
    public static void cargarCampus() throws CampusExistenteExeption{
        CampusService cs = new CampusService();
        for(int i = 0; i<campus.length; i++)
            cs.addCampus(campus[i][0], campus[i][1]);
    }
    
    public static void cargarParqueaderos(int numParqueaderos) throws ParqueaderoYaExistenteException, CampusNoExistenteException, CodigoNoValidoException, CampusInactivoException{
        ParqueaderoService ps = new ParqueaderoService();
        for(int i = 0; i < numParqueaderos; i++)
            ps.addParqueadero("Ubicacion z", (int) (Math.random()*10 % 15 + 10), "P" + String.format("%02d", i), campus[(int)(Math.random()*100 % campus.length)][0]);
    }
    
    public static void cargarPorteros() throws CedulaNoValidaException, CampusNoExistenteException, PorteroYaExistenteException, TelefonoNoValidoException, PersonaYaRegistradaComoUsuarioException {
        PorterosService ps = new PorterosService();
        for(int i = 0; i<porteros.length; i++)
            ps.addPortero(campus[(int)(Math.random()*100 % campus.length)][0], porteros[i][0], porteros[i][2], porteros[i][1], "Direccion A", "0123456789");
    }
    
    public static void cargarPuertas(int numPuertas) throws CodigoNoValidoException, PuertaYaExistenteException, CampusNoExistenteException {
        PuertaService ps = new PuertaService();
        for(int i = 0; i < numPuertas; i++)
            ps.addpuerta("Ubicacion B", "A" + String.format("%02d", i), campus[(int)(Math.random()*100 % campus.length)][0]);
    }
    
    public static void cargarUsuariosParqueaderos()
            throws CedulaNoValidaException, CodigoNoValidoException,
            ParqueaderoNoExistenteException, UsuarioNoExistenteException,
            ParquaderoInactivoException, NumeroParqueaderosNoDisponiblesException,
            UsuarioInactivoException, CampusNoExistenteException
    {
        
        ParqueaderoService ps = new ParqueaderoService();
        for(int i = 0; i < usuarios.length; i++){
            String nombreCampus = campus[(int)(Math.random()*100 % campus.length)][0];
            Parqueadero[] parqueaderos = ps.getParqueaderos(nombreCampus).toArray(new Parqueadero[0]);
            for(int j = 0; j < (int) (Math.random()*100 % 3 + 1); j++){
                try{
                    ps.addUsuario(nombreCampus, parqueaderos[(int)(Math.random()*100 % parqueaderos.length)].getId(), usuarios[i][0]);
                }catch(UsuarioYaAgregadoException | CampusInactivoException ex) {
                }
            }
        }
    }
    
    public static void cargarPuertasParqueaderos()
            throws CampusNoExistenteException, ParqueaderoNoExistenteException,
            PuertaNoExistenteException, CodigoNoValidoException,
            ParquaderoInactivoException, PuertaYaExistenteException,
            PuertaInactivaException, CampusInactivoException 
    {
        ParqueaderoService ps = new ParqueaderoService();
        
        for(int i = 0; i < campus.length; i++){
            Parqueadero[] parqueaderos = ps.getParqueaderos(campus[i][0]).toArray(new Parqueadero[0]);
            for(int j = 0; j < parqueaderos.length; j++){
                Puerta[] puertas = new PuertaService().getPuertas(campus[i][0]).toArray(new Puerta[0]);
                for(int k = 0; k < (int) (Math.random()*100 % 3 + 1); k++){
                    try{
                        ps.addPuertaEntrada(campus[i][0], parqueaderos[j].getId(), puertas[(int) (Math.random()*100 % puertas.length)].getId());
                        ps.addPuertaSalida(campus[i][0], parqueaderos[j].getId(), puertas[(int) (Math.random()*100 % puertas.length)].getId());
                    }catch (PuertaYaAgregadaException ex){
                    }
                        
                }
            }
            
        }
    }
    
    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }
}
