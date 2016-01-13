/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

/**
 *
 * @author Santos Gallegos
 */
public class Utilidades {
    
    public static String fill(int num){
        return fill(num, " ");
    }
    
    public static String fill(int num, String fill){
        String s = "";
        for(int i = 0; i < num; i++)
            s += fill;
        return s;
    }
}
