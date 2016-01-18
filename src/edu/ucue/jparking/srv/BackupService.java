/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.zip.*;

/**
 *
 * @author Franklin
 */
public class BackupService {
    
    

private static ZipOutputStream zos;

public void generarZip(String fileName) throws IOException, FileNotFoundException{
    makeZip(fileName);
}

public void makeZip(String fileName)  throws IOException, FileNotFoundException
{
      Calendar fecha = Calendar.getInstance();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      String fecha2 = (String) df.format(fecha.getTime());
      File file = new File(fileName);
      zos = new ZipOutputStream(new FileOutputStream( file + ".zip"));
      System.out.println("asd");
        recurseFiles(file);
        // Hemos terminado de agregar entradas al archivo zip ,
        // por lo que cerrar el flujo de salida Zip.
        zos.close();
        File f = new File(file+".zip");
        File file2 = new File("backup\\"+file + fecha2 +".zip");
        f.renameTo(file2);
   }
   /*
    * Recursivamente por un directorio y sus subdirectorios para buscar
    * Los archivos para agregar a la postal . Si el archivo actual está examinando
    * No es un directorio , el método añade al archivo Zip.
    */
   private static void recurseFiles(File file) throws IOException, FileNotFoundException
   {
    if (file.isDirectory()) {
         // Crear una matriz con todos los archivos y subdirectorios
        // del directorio actual .
        String[] fileNames = file.list();
         if (fileNames != null) {
            // Recursiva agregar cada entrada de la matriz para asegurarse de que lleguemos
           // subdirectorios , así como archivos normales en el directorio.
            for (int i=0; i<fileNames.length; i++){
                recurseFiles(new File(file, fileNames[i]));
            }
         }
    }
      
    // De lo contrario , un archivo para agregarlo como una entrada en el archivo Zip ..     
    else {
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
        while ((len = in.read(buf)) >= 0) {
                zos.write(buf, 0, len);
             }
             //Close la entrada del stream.        
             in.close();
             //Cerrar esta entrada en la ZIP stream.        
            zos.closeEntry();
      }
   }
}

