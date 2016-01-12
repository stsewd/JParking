/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

import java.io.UnsupportedEncodingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;

/**
 * 
 * @author Franklin
 */
public class DesEncrypter {
    Cipher ecipher;
    Cipher dcipher;

    DesEncrypter(SecretKey key) {
        try {
            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);

        } catch (javax.crypto.NoSuchPaddingException e) {
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (java.security.InvalidKeyException e) {
        }
    }

    public String encrypt(String str) throws IllegalBlockSizeException {
        try {
            // encodificar el string en UTG(
            byte[] utf8 = str.getBytes("UTF8");

            // Encriptar
            byte[] enc = ecipher.doFinal(utf8);

            // codificar base a bytes base64 para sacar el criptonamiento
            return new sun.misc.BASE64Encoder().encode(enc);
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }
        return null;
    }

    public String decrypt(String str) {
        try {
            // Codificar base64 al extraer los bytes
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

            // Descriptonar
            byte[] utf8 = dcipher.doFinal(dec);

            // codificar usando utf-8
            return new String(utf8, "UTF8");
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }
        return null;
    }    
}