/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.zip.*;

/**
 *
 * @author Franklin Lara
 */
public class BackupService {
    
    private static ZipOutputStream zos;

    public void generarZip(String fileName) throws IOException, FileNotFoundException{
        makeZip(fileName);
    }

    public void makeZip(String fileName)
        throws IOException, FileNotFoundException
    {
        Calendar fecha = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String fecha2 = (String) df.format(fecha.getTime());
        File file = new File(fileName);
        zos = new ZipOutputStream(new FileOutputStream( file + ".zip"));
        recurseFiles(file);
        // Hemos terminado de agregar entradas al archivo zip ,
        // por lo que cerrar el flujo de salida Zip.
        zos.close();

        File archivo = new File(file + ".zip");
        File nuevoArchivo = new File("backup/" + file+"Jparking" + fecha2 +".zip");
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
            while ((len = in.read(buf)) >= 0) {
                zos.write(buf, 0, len);
            }
            //Close la entrada del stream.        
            in.close();
            //Cerrar esta entrada en la ZIP stream.        
            zos.closeEntry();
        }
   }
   
   public void unZipFiles(File zipfile, String descDir) throws IOException {
		File file = new File(descDir);
                if(file.isDirectory())
                    System.out.println("es directorio");
		if (!file.exists()) {
			try {
				file.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
			ZipFile zf = new ZipFile(zipfile);
			for (Enumeration entries = zf.entries(); entries.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String zipEntryName = entry.getName();
				InputStream in = zf.getInputStream(entry);
                                System.out.println(descDir + zipEntryName);
                                System.out.println("in");
				OutputStream out = new FileOutputStream(descDir + zipEntryName);
				System.out.println("Out");
                                byte[] buf1 = new byte[1024];
				int len;
				while ((len = in.read(buf1)) > 0) {
					out.write(buf1, 0, len);
				}
				in.close();
				out.close();
				System.out.println("Descompresión completa.");
			}

			zf.close();
                        
                
		
	}

}

