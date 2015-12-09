/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import java.util.Calendar;

/**
 *
 * @author stsewd
 */
public class OrdenPago {
    
    private String cadulaUsuario;
    private float costo;
    private Calendar fechaEmision;

    public OrdenPago(String cadulaUsuario, float costo) {
        this.cadulaUsuario = cadulaUsuario;
        this.costo = costo;
        this.fechaEmision = Calendar.getInstance();
    }

    
    public void setCadulaUsuario(String cadulaUsuario) {
        this.cadulaUsuario = cadulaUsuario;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public void setFechaEmision(Calendar fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
    
    
    
    
}
