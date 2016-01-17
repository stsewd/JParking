/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.ContratoNoEstablecidoException;
import edu.ucue.jparking.srv.excepciones.FueraDelDiaDePagoException;
import edu.ucue.jparking.srv.objetos.OrdenPago;
import edu.ucue.jparking.srv.objetos.Usuario;
import edu.ucue.jparking.srv.excepciones.UsuarioNoRegistradoEnUnParqueaderoException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Franklin Lara
 */
public class ExportOrdenPagoService {
    
    
    private static Font catFont = new Font(Font.FontFamily.HELVETICA, 14,
        Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.HELVETICA, 12,
        Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 11,
        Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 12,
        Font.BOLD);
    
    private static Font smallBody = new Font(Font.FontFamily.HELVETICA, 11,
        Font.NORMAL);
    
    private static Font footPage = new Font(Font.FontFamily.HELVETICA, 8,
        Font.NORMAL, BaseColor.GRAY);

    /**
     *
     * @param cedula
     * @return 
     * @throws edu.ucue.jparking.srv.excepciones.UsuarioNoRegistradoEnUnParqueaderoException
     * @throws DocumentException
     * @throws FileNotFoundException
     * @throws UsuarioNoExistenteException
     * @throws edu.ucue.jparking.srv.excepciones.CedulaNoValidaException
     * @throws edu.ucue.jparking.srv.excepciones.ContratoNoEstablecidoException
     * @throws edu.ucue.jparking.srv.excepciones.FueraDelDiaDePagoException
     * @throws com.itextpdf.text.BadElementException
     */
    public File impresion(String cedula) 
            throws UsuarioNoRegistradoEnUnParqueaderoException, 
            DocumentException, FileNotFoundException, 
            UsuarioNoExistenteException, CedulaNoValidaException,
            ContratoNoEstablecidoException, FueraDelDiaDePagoException, 
            BadElementException, IOException, ClassNotFoundException, ObjectSizeException{
        
        Document document = new Document();
        String directorioStr = "";
        
        directorioStr = (new File(".").getCanonicalPath()) + "/archivos";
        
        File directorio = new File(directorioStr);
        
        if(!directorio.exists())
            directorio.mkdir();
        File FILE = new File(directorio, "orden_pago_" + cedula + ".pdf");
        PdfWriter.getInstance(document, new FileOutputStream(FILE));
        document.open();
        addMetaData(document);
        addContent(document, cedula);
        document.close();
        return FILE;
    }
            
    
    private void addMetaData(Document document) {
        document.addTitle("Orden de pago");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lara-Santos");
        document.addCreator("Lara-Santos");
    }
    
    private void addContent(Document document, String cedula)
            
            throws DocumentException, UsuarioNoExistenteException, 
            CedulaNoValidaException, ContratoNoEstablecidoException,
            FueraDelDiaDePagoException, BadElementException, IOException, 
            UsuarioNoRegistradoEnUnParqueaderoException, ClassNotFoundException, FileNotFoundException, ObjectSizeException {
    
        document.addTitle("Orden de pago del Parqueadero");
        Paragraph preface = new Paragraph();
        // Lets write a big header
        Paragraph tituloUniversidad = new Paragraph("UNIVERSIDAD DE CUENCA", catFont);
        Paragraph subtituloOrdenPago = new Paragraph("ORDEN DE PAGO", subFont);
        tituloUniversidad.setAlignment(Paragraph.ALIGN_CENTER);
        subtituloOrdenPago.setAlignment(Paragraph.ALIGN_CENTER);
        Image logoU = null;

        logoU = Image.getInstance(getClass().getResource("/edu/ucue/jparking/img/logo_u.png"));
        logoU.scalePercent(10);
        logoU.setAlignment(Image.ALIGN_CENTER);

        OrdenPagoService ops = new OrdenPagoService();
        UsuarioService service = new UsuarioService();
        Usuario u = service.get(cedula);
        OrdenPago orden = ops.getOrdenPago(cedula);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        preface.add(tituloUniversidad);
        addEmptyLine(preface, 1);
        preface.add(logoU);
        addEmptyLine(preface, 1);
        preface.add(subtituloOrdenPago);
        addEmptyLine(preface, 2);
        preface.add(new Paragraph(String.format("%s %s", "Fecha:", df.format(Calendar.getInstance().getTime())), smallBody));
        preface.add(new Paragraph(String.format("%s %s", "Cédula:", u.getCedula()), smallBody));
        preface.add(new Paragraph(String.format("%s %s %s", "Nombre:", u.getNombres(), u.getApellidos()), smallBody));
        preface.add(new Paragraph(String.format("%s %s", "Dirección:", u.getDireccion()), smallBody));
        preface.add(new Paragraph(String.format("%s %s", "Teléfono:", u.getTelefono()), smallBody));
        preface.add(new Paragraph(String.format("%s %s", "Tipo de Usuario:", u.getTipoUsuarioString()), smallBody));
        //preface.add(new Paragraph(String.format("%s %s", "Fecha de contrato:", df.format(orden.getFechaEmision().getTime())), smallBody));
        preface.add(new Paragraph(String.format("%s $%.2f", "Valor a pagar:", orden.getCosto()), smallBody));
        /*if(u.estaDebiendo()){
            preface.add(new Paragraph("Estado:           Debe"));
        }else{
            preface.add(new Paragraph("Estado:           Cancelado"));
        }*/
        addEmptyLine(preface, 2);
        preface.add(new Paragraph(String.format("%s ...............................", "Firma:"), smallBody));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(String.format("%s ..........................", "Autorizado:"), smallBody));
        addEmptyLine(preface, 2);
        preface.add(new Paragraph("Documento generado automáticamente por la aplicación JParking.", footPage));
        
        document.add(preface);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
      for (int i = 0; i < number; i++) {
        paragraph.add(new Paragraph(" "));
      }
    }
  
} 
