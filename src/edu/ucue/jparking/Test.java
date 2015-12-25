/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking;

import edu.ucue.jparking.dao.excepciones.CampusExistenteExeption;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoYaExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaExistenteException;
import edu.ucue.jparking.srv.CampusService;
import edu.ucue.jparking.srv.ParqueaderoService;
import edu.ucue.jparking.srv.UsuarioService;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.srv.excepciones.TelefonoNoValidoException;

/**
 *
 * @author Santos 
 */
public class Test {
    /**
     * Clase con campus y usuarios de prueba.
     */
    
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
    
    private static final TipoUsuario[] tipoUsuario = {TipoUsuario.DOCENTE, TipoUsuario.EMPLEADO, TipoUsuario.ESTUDIANTE};
    
    
    //Campus
    private static final String[][] campus = {
        {"Paraiso", "Direccion y"},
        {"Central", "Direccion y"},
        {"Balzay", "Direccion y"},
        {"Centro Historico", "Direccion y"},
        {"Yanuncay", "Direccion y"},
    };
    
    
    public static void cargarUsuarios() throws UsuarioYaExistenteException, CedulaNoValidaException, TelefonoNoValidoException{
        UsuarioService us = new UsuarioService();
        for(int i = 0; i<usuarios.length; i++)
            us.add(usuarios[i][0], usuarios[i][1], usuarios[i][2], "Direccion z", "1234567890", tipoUsuario[(int) ((Math.random()*100)%3)].toString());
    }
    
    public static void cargarCampus() throws CampusExistenteExeption{
        CampusService cs = new CampusService();
        for(int i = 0; i<campus.length; i++)
            cs.addCampus(campus[i][0], campus[i][1]);
    }
    
    public static void cargarParqueaderos() throws ParqueaderoYaExistenteException, CampusNoExistenteException, CodigoNoValidoException{
        ParqueaderoService ps = new ParqueaderoService();
        for(int i = 0; i<60; i++)
            ps.addParqueadero("Ubicacion z", (int) (Math.random()*10 % 30 +1), "P" + String.format("%02d", i), campus[(int)(Math.random()*100 % campus.length)][0]);
    }
}
