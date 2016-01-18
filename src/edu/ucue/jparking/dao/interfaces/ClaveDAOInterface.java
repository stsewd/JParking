/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.dao.ClaveDAO;
import java.io.IOException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author Franklin
 */
public interface ClaveDAOInterface {
    
    /**
     * cargar clave publica desde el archivo
     * @param fileName
     * @return
     * @throws Exception 
     */
    public PublicKey loadPublicKey(String fileName) throws Exception;
    
    /**
     * cargar clave privada desde el archivo
     * @param fileName
     * @return
     * @throws Exception 
     */
    public PrivateKey loadPrivateKey(String fileName) throws Exception;
    
    /**
     * guardar las claves en un archivo
     * @param key
     * @param fileName
     * @throws Exception 
     */
    public  void saveKey(Key key, String fileName) throws Exception;
    
    /**
     * recupera la clave del archivo 
     * @param fileName
     * @return
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public String recuperarClave(String fileName) throws IOException, ClassNotFoundException;
    
    /**
     * guarda la clave en un archivo
     * @param fileName
     * @throws IOException 
     */
    public void saveClave(String fileName,String clave) throws IOException;
    //public ClaveDAO getInstancia();
}
