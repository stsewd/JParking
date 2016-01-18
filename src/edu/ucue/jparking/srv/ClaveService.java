/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.srv.excepciones.ClaveNoValidaException;
import edu.ucue.jparking.dao.ClaveDAO;
import edu.ucue.jparking.dao.interfaces.ClaveDAOInterface;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

/**
 *
 * @author Franklin Lara
 */
class ClaveService {
    
    ClaveDAOInterface oInterface = ClaveDAO.getInstancia();
    private static Cipher rsa;
    
    public void GenerarClaves() throws NoSuchAlgorithmException, Exception{
      
        // Generar el par de claves
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      KeyPair keyPair = keyPairGenerator.generateKeyPair();
      PublicKey publicKey = keyPair.getPublic();
      PrivateKey privateKey = keyPair.getPrivate();
      
      // Se guarda en fichero la clave publica
      oInterface.saveKey(publicKey, "data/publickey.dat");
 
      // se guarda en el fichero la clave privada
      oInterface.saveKey(privateKey, "data/privatekey.dat");
      
    }
    
    public void cifrar(String clave) throws Exception{
      //ObjectOutputStream flujoSalida = new ObjectOutputStream(new FileOutputStream(new File("celebrum.dat")));
        // Obtener la clase para encriptar/desencriptar
      rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
 
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      KeyPair keyPair = keyPairGenerator.generateKeyPair();
      PublicKey publicKey = keyPair.getPublic();
      PrivateKey privateKey = keyPair.getPrivate();
      
      
      
      //cargar la calve publica
      privateKey = oInterface.loadPrivateKey("data/privatekey.dat");
      
      // Se encripta
      rsa.init(Cipher.ENCRYPT_MODE, privateKey);
      byte[] encriptado = rsa.doFinal(clave.getBytes());
      String pass = "";
      for (byte b : encriptado) {
         pass=pass.concat(Integer.toHexString(0xFF & b));
      }
      oInterface.saveClave("data/password.dat",pass);
        
      //flujoSalida.writeChars(encriptado.toString());
      //flujoSalida.close();
      
    }
    
    public boolean validarClave(String usuario,String clave) throws Exception,ClaveNoValidaException{
      
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      KeyPair keyPair = keyPairGenerator.generateKeyPair();
      PublicKey publicKey = keyPair.getPublic();
      PrivateKey privateKey = keyPair.getPrivate();
      
      //se salva la clave privada
      privateKey = oInterface.loadPrivateKey("data/privatekey.dat");
      //se salva la clave del administrador
      String recuperada = oInterface.recuperarClave("data/password.dat");
      //proceso de validacion de la clave
      
      // Se encripta
      rsa.init(Cipher.ENCRYPT_MODE, privateKey);
      byte[] encriptado = rsa.doFinal(clave.getBytes());
        String pass = "";
        for (byte b : encriptado) {
           pass=pass.concat(Integer.toHexString(0xFF & b));
        }
      if (!recuperada.equals(pass) || !usuario.equals("Administrador"))
          throw new ClaveNoValidaException();
        
      return true;
    
    } 
}
