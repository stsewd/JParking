/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.dao.ClaveDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Franklin
 */
public interface ClaveDAOInterface {
    
    /**
     * guardar la clave que se genera
     * @param fileName
     * @param clave
     * @throws IOException 
     */
    public void saveClave(String fileName, SecretKeySpec clave) throws IOException;
    
    /**
     * se recupera la clave pra cifrar y decifrar
     * @param fileName
     * @return
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public SecretKeySpec recuperarClave(String fileName) throws IOException, ClassNotFoundException;

    /**
     * guarda la contrasenia cifrada
     * @param fileName
     * @param clave
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void guardarContrasenia(String fileName,byte[] clave) throws FileNotFoundException, IOException;

    /**
     * recupera la clave cifrada
     * @param filename
     * @return
     * @throws IOException 
     */
    public byte[] recuperarContrasenia(String filename) throws IOException;


}
