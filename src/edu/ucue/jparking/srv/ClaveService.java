/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.srv.excepciones.ClaveNoValidaException;
import edu.ucue.jparking.dao.ClaveDAO;
import edu.ucue.jparking.dao.interfaces.ClaveDAOInterface;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Franklin Lara
 */
class ClaveService {
    
    ClaveDAOInterface claveDAO = ClaveDAO.getInstancia();
    
    
    public void GenerarClave()
            throws NoSuchAlgorithmException, IOException
    {
      // Generamos una clave de 128 bits adecuada para AES
      KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
      keyGenerator.init(128);
      Key key = keyGenerator.generateKey();
       
      //guarda en un archivo .dat
      claveDAO.saveClave("data/celebrum.dat", (SecretKeySpec) key);
    }
    
    public void generarClavesRSA(Path path) throws Exception {
        // Generar el par de claves
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        File privatePath = new File(path.toFile(), "clavePrivadaJP.dat");
        File publicPath = new File(path.toFile(), "clavePublicaJP.dat");
        
        // Se salva y recupera de fichero la clave publica
        claveDAO.saveKey(publicKey, publicPath.toString());
        claveDAO.saveKey(privateKey, privatePath.toString());
    }
      
    public void cifrar(String clave)
            throws NoSuchAlgorithmException, NoSuchPaddingException, IOException,
            ClassNotFoundException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException
    {
        Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec key = claveDAO.recuperarClave("data/celebrum.dat");
        
        // Se inicializa para encriptacion y se encripta el texto,
        // que debemos pasar como bytes.
        aes.init(Cipher.ENCRYPT_MODE, key);
        byte[] encriptado = aes.doFinal(clave.getBytes());
        claveDAO.guardarContrasenia("data/password.dat", encriptado);
    }
        
    
    public boolean validarClave(String usuario, String clave)
            throws NoSuchAlgorithmException, NoSuchPaddingException, IOException,
            ClassNotFoundException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, ClaveNoValidaException 
    {
        String keyPath = "data/celebrum.dat";
        File file = new File(keyPath);
        // Validar si existe clave.
        if(!file.exists())
            Utilidades.iniciarDefaultDirectorios();
        
        Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec key = claveDAO.recuperarClave(keyPath);
        aes.init(Cipher.ENCRYPT_MODE, key);
        byte[] encriptado = aes.doFinal(clave.getBytes());
        byte[] recuperada = claveDAO.recuperarContrasenia("data/password.dat");
        if(!Arrays.equals(encriptado, recuperada) || !usuario.equals("Administrador"))
            throw new ClaveNoValidaException();
        return true;
    }
    
    public void cambiarClave(String usuario, String claveActual, String nuevaClave)
            throws NoSuchAlgorithmException, NoSuchPaddingException, IOException,
            ClassNotFoundException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, ClaveNoValidaException
    {
        if(nuevaClave == null || nuevaClave.trim().length() == 0)
            throw new IllegalArgumentException("La contraseña no puede estar vacia.");
        
        validarClave(usuario, claveActual);
        
        GenerarClave();
        
        cifrar(nuevaClave);
    }
}
