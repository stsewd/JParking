/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.interfaces.ClaveDAOInterface;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Franklin
 */
public class ClaveDAO implements ClaveDAOInterface{
    
    private static ClaveDAO instancia;

    private ClaveDAO() {
        
    }
    
    public static ClaveDAO getInstancia(){
        if (instancia==null)
            instancia = new ClaveDAO();
        return instancia;
    }

    @Override
    public void saveClave(String fileName, SecretKeySpec clave) throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fileName)));
        oos.writeObject(clave);
        oos.close();
    }
    
    @Override
    public SecretKeySpec recuperarClave(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fileName)));
        SecretKeySpec clave = (SecretKeySpec) ois.readObject();
        return clave;
    }
           
    @Override
    public void guardarContrasenia(String fileName, byte[] clave) throws FileNotFoundException, IOException{
        FileOutputStream fos = new FileOutputStream(new File(fileName));
        fos.write(clave, 0, 16);
        fos.close();
    }
    
    @Override
    public byte[] recuperarContrasenia(String filename) throws IOException{
        File file = new File(filename);
        byte[] clave = Files.readAllBytes(file.toPath());
        return clave;
    }
    
    @Override
    public PublicKey loadPublicKey(String fileName) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
      FileInputStream fis = new FileInputStream(fileName);
      int numBtyes = fis.available();
      byte[] bytes = new byte[numBtyes];
      fis.read(bytes);
      fis.close();

      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      KeySpec keySpec = new X509EncodedKeySpec(bytes);
      PublicKey keyFromBytes = keyFactory.generatePublic(keySpec);
      return keyFromBytes;
    }

    @Override
    public PrivateKey loadPrivateKey(String fileName)
            throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException
    {
      FileInputStream fis = new FileInputStream(fileName);
      int numBtyes = fis.available();
      byte[] bytes = new byte[numBtyes];
      fis.read(bytes);
      fis.close();

      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      KeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
      PrivateKey keyFromBytes = keyFactory.generatePrivate(keySpec);
      return keyFromBytes;
    }

    @Override
    public void saveKey(Key key, String fileName) throws FileNotFoundException, IOException {
        byte[] publicKeyBytes = key.getEncoded();
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(publicKeyBytes);
        fos.close();
    }
}
