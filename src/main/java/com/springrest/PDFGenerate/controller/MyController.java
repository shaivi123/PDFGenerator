package com.springrest.PDFGenerate.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.springrest.PDFGenerate.createPDF.CreateEmployeePDF;
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

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MyController {
    @Autowired
    private EmployeeRepository employeeRepository;
//    @Autowired
//    private EmployeeService employeeService;

    //File Download
    @GetMapping("/downloadPdf")
    public ResponseEntity<?> EmployeeReport() throws IOException{
        String filePDF="C:/Desktop/sample.pdf";
        List<Employee> list= (List<Employee>) employeeRepository.findAll();
        ByteArrayInputStream bis= CreateEmployeePDF.employeePDFReport(list);
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Disposition","inline; file ");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));

    }

    //File upload
    @PostMapping("/uploadingPDF")
    public void addPDF(){
        Document document=new Document();
        try{
            PdfWriter writer= PdfWriter.getInstance(document,new FileOutputStream("welcome.pdf"));
            document.open();
            document.add(new Paragraph("Welcome"));
           // document.save("success.pdf");
            document.close();
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    //Add PDF using HTML Inputs
//    @PostMapping
//    public void GeneratePDF(){
//        employeeService.GeneratePDF();
//
//    }


    @GetMapping("/users")
    public String getUser(){
        return "abc";
    }



}
