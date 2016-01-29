/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;
import edu.ucue.jparking.dao.ClaveDAO;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.zip.*;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;

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
        File archivo = new File(file + ".jpbackup");
        zos = new ZipOutputStream(new FileOutputStream(archivo));
        recurseFiles(file);
        // Hemos terminado de agregar entradas al archivo zip ,
        // por lo que cerrar el flujo de salida Zip.
        zos.close();
        
        // Agregar encriptado a zip
        RandomAccessFile raf = new RandomAccessFile(archivo, "rw");
        Cipher rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsa.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encriptado = rsa.doFinal("admin".getBytes());
        raf.seek(raf.length());
        raf.write(encriptado);
        raf.writeInt(encriptado.length);
        raf.close();
                
        Calendar fecha = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String fechaString = (String) df.format(fecha.getTime());
        File nuevoArchivo = new File("backup/" + file + "-jparking-" + fechaString + ".jpbackup");
        
        Files.move(archivo.toPath(), nuevoArchivo.toPath(), StandardCopyOption.REPLACE_EXISTING);
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
                for (int i=0; i<fileNames.length; i++)
                    recurseFiles(new File(file, fileNames[i]));
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
   
   public void unZipFiles(File zipfile, File descDir, File clavePath) throws IOException, Exception {
       File tempZip = new File(descDir, "temp.zip"); 
       RandomAccessFile raf = null;
       ZipFile zf = null;
       try{
            // Comprobar fichero
            Files.copy(zipfile.toPath(), tempZip.toPath(), StandardCopyOption.REPLACE_EXISTING);
            
            raf = new RandomAccessFile(tempZip, "rw");
            raf.seek(raf.length() - 4);
            int lenEncrip = raf.readInt();
            raf.seek(raf.length() - (lenEncrip + 4));
            byte[] encrypData = new byte[lenEncrip];
            raf.read(encrypData);

            if(!claveService.validarClaveRSA(clavePath, encrypData)){
                throw new InvalidKeySpecException();
            }
            
            raf.setLength(raf.length() - (lenEncrip + 4));
            raf.close();
            
            if (!descDir.exists())
                descDir.mkdirs();

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
        }finally {
           try{
               zf.close();
               raf.close();
           }catch(Exception ex){}
           Files.delete(tempZip.toPath());
        }
    }

}

