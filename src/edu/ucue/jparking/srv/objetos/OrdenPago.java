/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv.objetos;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Santos Gallegos
 */
public class OrdenPago implements Serializable {
    
    private int numeroOrdenPago;
    private String cedulaUsuario;
    private double costo;
    private Calendar fechaEmision;

    public OrdenPago(String cedulaUsuario, double costo) {
        this.cedulaUsuario = cedulaUsuario;
        this.costo = costo;
        this.fechaEmision = Calendar.getInstance();
    }
    
    public void setCadulaUsuario(String cadulaUsuario) {
        this.cedulaUsuario = cadulaUsuario;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public void setFechaEmision(Calendar fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * @return the cedulaUsuario
     */
    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    /**
     * @return the costo
     */
    public double getCosto() {
        return costo;
    }

    /**
     * @return the fechaEmision
     */
    public Calendar getFechaEmision() {
        return fechaEmision;
    }

    /**
     * @return the numeroOrdenPago
     */
    public int getNumeroOrdenPago() {
        return numeroOrdenPago;
    }

    /**
     * @param numeroOrdenPago the numeroOrdenPago to set
     */
    public void setNumeroOrdenPago(int numeroOrdenPago) {
        this.numeroOrdenPago = numeroOrdenPago;
    }
}
