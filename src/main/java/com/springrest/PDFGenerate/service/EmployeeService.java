package com.springrest.PDFGenerate.service;

import com.springrest.PDFGenerate.model.Employee;
import com.springrest.PDFGenerate.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.thymeleaf.context.Context;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class EmployeeService {

    @Autowired(required = false)
    private EmployeeRepository employeeRepository;
//
//    public void GeneratePDF() {
//        employeeRepository.findAll();
//    }
//     private static final String PDF_RESOURCES="/pdf-resources/";
//
//     @Autowired(required = false)
//     private SpringTemplateEngine templateEngine;
//
//     public File generatePdf() throws Exception{
//         Context context=new Context();
//         String html=loadAndFillTemplate(context);
//         return renderPdt(html);
//     }
//    private File renderPdt(String html)throws Exception{
//        File file=File.createTempFile("employees",".pdf");
//        OutputStream outputStream=new FileOutputStream(file);
//        ITextRenderer renderer= new ITextRenderer(20f*4f/3f,20);
//        renderer.setDocumentFromString(html,new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
//        renderer.layout();
//        renderer.createPDF(outputStream);
//        outputStream.close();
//        file.deleteOnExit();
//        return file;
//    }
//
//    private Context getContext(){
//        Context context=new Context();
//         context.setVariable("employees",this.getAll());
//         return context;
//    }
//
//    private String loadAndFillTemplate(Context context){
//        return templateEngine.process("pdfEmployees",context);
//    }
//    public Employee getAll(){
//         List list =new ArrayList();
//         list.add(new Employee(14,"dghd","hduy",14,"duytfre",8654578));
////         Employee employee=new Employee();
////
////         employee.setFirstName("Shaivi");
////        employee.getFirstName();
//        return (Employee) list;
//    }
}
