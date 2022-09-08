package com.springrest.PDFGenerate.controller;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.springrest.PDFGenerate.createPDF.CreateEmployeePDF;
import com.springrest.PDFGenerate.dto.Request;
import com.springrest.PDFGenerate.model.Employee;
import com.springrest.PDFGenerate.repository.EmployeeRepository;
import com.springrest.PDFGenerate.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MyController {
    @Autowired(required = false)
    private  EmployeeRepository employeeRepository;
   @Autowired(required = false)
    private EmployeeService employeeService;



    //File upload
    @PostMapping("/downloadingPDFOnLocal")
    public void addPDF(){
        Document document=new Document();
        try{
            String k="<table>\n" +
                    "  <tr>\n" +
                    "    <th>Month</th>\n" +
                    "    <th>Savings</th>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>August</td>\n" +
                    "    <td>$1000</td>\n" +
                    "  </tr>\n" +
                    "</table>";
            //Download PDF from Project directory
            //PdfWriter writer= PdfWriter.getInstance(document,new FileOutputStream("file.pdf"));

            //Unused code
           // document.addLanguage("org.springframework.beans.TypeConverter");

            document.open();
            HtmlConverter.convertToPdf(k, new FileOutputStream("file.pdf"));
          //  HTMLWorker htmlWorker = new HTMLWorker(document);
          //  htmlWorker.parse(new StringReader(k));
            document.close();
           // writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/downloadingPDFOnFolder")
    public void downloadPDF(){
        Document document=new Document();
        try{
            String k="<table>\n" +
                    "  <tr>\n" +
                    "    <th>Month</th>\n" +
                    "    <th>Savings</th>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>August</td>\n" +
                    "    <td>$1000</td>\n" +
                    "  </tr>\n" +
                    "</table>";

            //Download PDF in folder file
            //OutputStream writer = new FileOutputStream(new File("/home/shaivi/Downloads/Test.pdf/"));
            //Write the code of getting the data in folder PDF
           // PdfWriter.getInstance(document, writer);
            document.open();
            HtmlConverter.convertToPdf(k, new FileOutputStream("/home/shaivi/Downloads/Test.pdf/"));
           // HTMLWorker htmlWorker = new HTMLWorker(document);
           // htmlWorker.parse(new StringReader(k));
            document.close();
          //  writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



}
