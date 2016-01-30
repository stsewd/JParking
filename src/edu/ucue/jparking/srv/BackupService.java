/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.ClaveDAO;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.zip.*;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Franklin Lara
 */
public class BackupService {
    
    private static ZipOutputStream zos;
    private ClaveService claveService = new ClaveService();

    public void generarZip(File clavePath) throws IOException, FileNotFoundException, Exception{
        makeZip(clavePath, "data");
    }

    public void makeZip(File clavePath, String fileName)
        throws IOException, FileNotFoundException, Exception
    {
        if(!clavePath.exists())
            throw new FileNotFoundException(clavePath.toString());
        PublicKey publicKey = ClaveDAO.getInstancia().loadPublicKey(clavePath.toString());
        
        File file = new File(fileName);
        File tempZip = new File("temp.zip");
        zos = new ZipOutputStream(new FileOutputStream(tempZip));
        recurseFiles(file);
        // Hemos terminado de agregar entradas al archivo zip ,
        // por lo que cerrar el flujo de salida Zip.
        zos.close();
        
        
        Calendar fecha = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String fechaString = (String) df.format(fecha.getTime());
        File nuevoArchivo = new File("backup/" + file + "-jparking-" + fechaString + ".jpbackup");
        
        // Encriptar backup
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        Key key = keyGen.generateKey();
        
        Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        aes.init(Cipher.ENCRYPT_MODE, key);
        FileInputStream is = new FileInputStream(tempZip);
        CipherOutputStream os = new CipherOutputStream(new FileOutputStream(nuevoArchivo), aes);
        copiarBytes(is, os);
        os.close();
        
        // Escribir clave de aes ecriptada con rsa
        RandomAccessFile raf = new RandomAccessFile(nuevoArchivo, "rw");
        Cipher rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsa.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encriptado = rsa.doFinal(key.getEncoded());
        raf.seek(raf.length());
        raf.write(encriptado);
        raf.writeInt(encriptado.length);
        raf.close();
        
        // Eliminar archivo temporal
        Files.delete(tempZip.toPath());
   }
    
    private void copiarBytes(InputStream is, OutputStream os) throws IOException {
        int i;
        byte[] b = new byte[1024];
        while((i = is.read(b)) != -1)
            os.write(b, 0, i);
    }

   /*
    * Recursivamente por un directorio y sus subdirectorios para buscar
    * Los archivos para agregar a la postal . Si el archivo actual está examinando
    * No es un directorio , el método añade al archivo Zip.
    */
   private static void recurseFiles(File file)
           throws IOException, FileNotFoundException
   {
        if (file.isDirectory()) {
            // Crear una matriz con todos los archivos y subdirectorios
            // del directorio actual .
            String[] fileNames = file.list();
            if (fileNames != null) {
                // Recursiva agregar cada entrada de la matriz para asegurarse de que lleguemos
                // subdirectorios , así como archivos normales en el directorio.
                for (int i=0; i<fileNames.length; i++){
                    if(fileNames[i].toString().compareToIgnoreCase("password.dat") == 0 ||fileNames[i].toString().compareToIgnoreCase("celebrum.dat") == 0)
                        continue;
                    recurseFiles(new File(file, fileNames[i]));
                }
            }
        }else { // De lo contrario , un archivo para agregarlo como una entrada en el archivo Zip
            byte[] buf = new byte[1024];
            int len;
            // Crear una nueva entrada de postal con el nombre del archivo.        
            ZipEntry zipEntry = new ZipEntry(file.toString());
            // Crear un flujo de entrada tamponada fuera del archivo
            // que estamos tratando de agregar en el archivo Zip.

            FileInputStream fin = new FileInputStream(file);
            BufferedInputStream in = new BufferedInputStream(fin);
            zos.putNextEntry(zipEntry);
            
            // Lee bytes del archivo y escribir en el archivo Zip.      
            while ((len = in.read(buf)) >= 0)
                zos.write(buf, 0, len);
            
            //Close la entrada del stream.        
            in.close();
            //Cerrar esta entrada en la ZIP stream.        
            zos.closeEntry();
        }
   }
   
   public void unZipFiles(File backupFile, File descDir, File clavePath)
           throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
           IllegalBlockSizeException, BadPaddingException, Exception
   {
       File tempFile = new File("temp.jpbackup"); 
       File tempZip = new File(descDir, "temp.zip");
       RandomAccessFile raf;
       ZipFile zf;
       try{
            // Copiar fichero al espacio de trabajo
            Files.copy(backupFile.toPath(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            
            // Recuperar la clave aes encriptada.
            raf = new RandomAccessFile(tempFile, "rw");
            raf.seek(raf.length() - 4);
            int lenEncrip = raf.readInt();
            raf.seek(raf.length() - (lenEncrip + 4));
            byte[] encrypKey = new byte[lenEncrip];
            raf.read(encrypKey);
            
            // Desencriptar clave aes con clave privada rsa
            PrivateKey privateKey = ClaveDAO.getInstancia().loadPrivateKey(clavePath.toString());
            Cipher rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            rsa.init(Cipher.DECRYPT_MODE, privateKey);
            
            byte[] decodedKey = rsa.doFinal(encrypKey);
            Key key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
            
            // Eliminar clave del fichero
            raf.setLength(raf.length() - (lenEncrip + 4));
            raf.close();
            
            // Desencriptar backup
            if (!descDir.exists())
                descDir.mkdirs();
            
            Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
            aes.init(Cipher.DECRYPT_MODE, key);
            
            CipherInputStream is = new CipherInputStream(new FileInputStream(tempFile), aes);
            FileOutputStream os = new FileOutputStream(tempZip);
            copiarBytes(is, os);
            os.close();

            // Descomprimir archivos
            zf = new ZipFile(tempZip);
            for (Enumeration entries = zf.entries(); entries.hasMoreElements();) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                File zipEntryName = new File(entry.getName());
                InputStream in = zf.getInputStream(entry);
                OutputStream out = new FileOutputStream(zipEntryName);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
            }
            zf.close();
        }finally {
           Files.delete(tempFile.toPath());
           Files.delete(tempZip.toPath());
        }
    }

}

