/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Header;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.ContratoNoEstablecidoException;
import edu.ucue.jparking.srv.excepciones.FueraDelDiaDePagoException;
import edu.ucue.jparking.srv.objetos.OrdenPago;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Franklin Lara
 */
public class ImpresionOrdenPagosrv {
    private static Font catFont = new Font(Font.FontFamily.HELVETICA, 14,
        Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.HELVETICA, 12,
        Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 14,
        Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 12,
        Font.BOLD);
    
    private static Font smallBody = new Font(Font.FontFamily.HELVETICA, 11,
        Font.NORMAL);

    public void addMetaData(Document document) {
        document.addTitle("Orden de pago");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lara-Santos");
        document.addCreator("Lara-Santos");
    }
    
    public void addContent(Document document, String cedula) throws DocumentException, UsuarioNoExistenteException, CedulaNoValidaException, ContratoNoEstablecidoException, FueraDelDiaDePagoException {
    
    document.addTitle("Orden de pago del Parqueadero");
    Paragraph preface = new Paragraph();
    // Lets write a big header
    preface.add(new Paragraph("UNIVERSIDAD DE CUENCA", catFont));
    preface.add(new Paragraph("Orden de pago", subFont));
    //Anchor anchor = new Anchor("Orden  de Pago");
    //anchor.setName("First Chapter");
    OrdenPagoService ops = new OrdenPagoService();
    UsuarioService service = new UsuarioService();
    Usuario u = service.get(cedula);
    OrdenPago orden = ops.getOrdenPago(cedula);
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
        
    // Second parameter is the number of the chapter
    //Chapter catPart = new Chapter(new Paragraph(anchor), 1);

    //Paragraph subPara = new Paragraph("Datos: ");
    
    //Section subCatPart = catPart.addSection(subPara);
    addEmptyLine(preface, 1);
    preface.add(new Paragraph(String.format("%-20s%s", "Fecha:", df.format(Calendar.getInstance().getTime())), smallBody));
    preface.add(new Paragraph(String.format("%-20s%s", "Cédula:", u.getCedula()), smallBody));
    preface.add(new Paragraph(String.format("%-20s%s %s", "Nombre:", u.getNombres(), u.getApellidos()), smallBody));
    preface.add(new Paragraph(String.format("%-20s%s", "Dirección:", u.getDireccion()), smallBody));
    preface.add(new Paragraph(String.format("%-20s%s", "Teléfono:", u.getTelefono()), smallBody));
    preface.add(new Paragraph(String.format("%-20s%s", "Tipo de Usuario:", u.getTipoUsuarioString()), smallBody));
    preface.add(new Paragraph(String.format("%-20s%s", "Fecha de contrato:", df.format(orden.getFechaEmision().getTime())), smallBody));
    preface.add(new Paragraph(String.format("%-20s$%.2f", "Valor a pagar:", orden.getCosto()), smallBody));
    /*if(u.estaDebiendo()){
        preface.add(new Paragraph("Estado:           Debe"));
    }else{
        preface.add(new Paragraph("Estado:           Cancelado"));
    }*/
    addEmptyLine(preface, 27);
    preface.add(new Paragraph(String.format("%s _______________________________", "Firma:"), smallBody));
    addEmptyLine(preface, 1);
    preface.add(new Paragraph(String.format("%s __________________________", "Autorizado:"), smallBody)); 
    document.add(preface);
  }

   private static void addEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
      paragraph.add(new Paragraph(" "));
    }
  }
  
} 




