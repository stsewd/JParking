/**
 * 
 */
package edu.ucue.jparking.srv;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Santos Gallegos
 */
public class Campus {

    /**
     * @param aHoraApertura the horaApertura to set
     */
    public static void setHoraApertura(HoraDia aHoraApertura) {
        horaApertura = aHoraApertura;
    }

    /**
     * @param aHoraCierre the horaCierre to set
     */
    public static void setHoraCierre(HoraDia aHoraCierre) {
        horaCierre = aHoraCierre;
    }
    private String nombre;
    private String direccion;
    private static HoraDia horaApertura = new HoraDia(6, 30);
    private static HoraDia horaCierre = new HoraDia(21, 30);
    
    /*
    private List<Puerta> puertas;
    private List<Parqueadero> parqueaderos;
    private List<Portero> porteros;
    */
    
    public Campus(String nombre, String direccion) {
        this.nombre=nombre;
        this.direccion=direccion;
        /*
        this.puertas = new ArrayList<>();
        this.parqueaderos = new ArrayList<>();
        this.porteros = new ArrayList<>();
        */
    }


    /**
     * @return the horaApertura
     */
    public static HoraDia getHoraApertura() {
        return horaApertura;
    }

    /**
     * @return the horaCierre
     */
    public static HoraDia getHoraCierre() {
        return horaCierre;
    }

    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }    
}
