package com.springrest.PDFGenerate.service;

import com.aspose.cells.HtmlSaveOptions;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import com.itextpdf.text.Document;
import com.springrest.PDFGenerate.model.Book;
import com.springrest.PDFGenerate.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired(required = false)
    private static EmployeeRepository employeeRepository;
    static List<Book> list;

       public EmployeeService() {
           list = new ArrayList<>();
       }

        public static Book addBook(Book book) {
        list.add(book);
        return book;
    }

    public Book getJson(Book book) throws Exception {
        // this code is converting json to html
        Workbook workbook=new Workbook();
        System.out.println("workbook call");
        HtmlSaveOptions saveOptions = new HtmlSaveOptions(SaveFormat.HTML);
        saveOptions.setExportGridLines(true);
       // saveOptions.setExportHeadings(true);
        saveOptions.setSaveAsSingleFile(true);
        System.out.println("workbook call 1111111111111");
        saveOptions.setPageTitle("Convert JSON to HTML");
        System.out.println("workbook call !!!!!!!!!!!!!!!!!!!!!!!!");
        workbook.save(book + "books-template.html", saveOptions);
        System.out.println("workbook call  $$$$$$$$$$$$$$$$$$$$$$"+workbook);
        return book;
    }

//    Workbook workbook = new Workbook("C:\\SampleJson.json");
    public Book createDataForPdf(String title, int price, String language, String author, String content) {
        Document document = new Document();
        document.open();
        Book books = new Book();
        books.setTitle(title);
        books.setPrice(price);
        books.setLanguage(language);
        books.setAuthor(author);
        document.close();

        return books;
    }

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
