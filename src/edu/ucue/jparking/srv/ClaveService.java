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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
    
    ClaveDAOInterface oInterface = ClaveDAO.getInstancia();
    private static Cipher rsa;
    
    public void GenerarClave() throws NoSuchAlgorithmException, Exception{
      
      // Generamos una clave de 128 bits adecuada para AES
      KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
      keyGenerator.init(128);
      Key key = keyGenerator.generateKey();
       
      // Alternativamente, una clave que queramos que tenga al menos 16 bytes
      // y nos quedamos con los bytes 0 a 15
      key = new SecretKeySpec("una clave de 16 bytes".getBytes(),  0, 16, "AES");

      //guarda en un archivo .dat
      oInterface.saveClave("data/celebrum.dat", (SecretKeySpec) key);
      
      
      // Texto a encriptar
      
    }
    
    public void cifrar(String clave) throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, ClassNotFoundException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
      
      Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
      SecretKeySpec key = oInterface.recuperarClave("data/celebrum.dat");

      // Se inicializa para encriptacion y se encripta el texto,
      // que debemos pasar como bytes.
      
      aes.init(Cipher.ENCRYPT_MODE, key);
      byte[] encriptado = aes.doFinal(clave.getBytes());
      oInterface.guardarContrasenia("data/password.dat", encriptado);
      }
        
      //flujoSalida.writeChars(encriptado.toString());
      //flujoSalida.close();
      
    
    
    public boolean validarClave(String usuario,String clave) throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, ClassNotFoundException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, ClaveNoValidaException  {

        Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec key = oInterface.recuperarClave("data/celebrum.dat");
        aes.init(Cipher.ENCRYPT_MODE, key);
        byte[] encriptado = aes.doFinal(clave.getBytes());
        byte[] recuperada = oInterface.recuperarContrasenia("data/password.dat");
        if(!Arrays.equals(encriptado, recuperada) || !usuario.equals("Administrador"))
            throw new ClaveNoValidaException();
        return true;
    } 
}
