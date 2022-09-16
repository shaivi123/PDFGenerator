package com.springrest.PDFGenerate.controller;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.Document;
import com.springrest.PDFGenerate.dto.PdfRequest;
import com.springrest.PDFGenerate.model.Book;
import com.springrest.PDFGenerate.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
//import com.aspose.cells.Workbook;
import javax.servlet.ServletContext;
import java.io.*;
import java.util.List;

@RestController
public class MyController {

    @Autowired(required = false)
    private EmployeeService employeeService;
    @Autowired
    ServletContext servletContext;
    @Autowired
    private  TemplateEngine templateEngine;

    //File upload
    @PostMapping("/downloadingPDFOnLocal")
    public String addPDF(@RequestBody String content,PdfRequest dto){
        Document document=new Document();
        try{
            document.open();
            HtmlConverter.convertToPdf(content, new FileOutputStream("src/main/resources/PDF/books-template.pdf"));
           // document.save();
            document.close();
            return "books-template";
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/downloadingPDFOnFolder")
    public void downloadPDF(@RequestBody String content){
        Document document=new Document();
        try{
            document.open();
            HtmlConverter.convertToPdf(content, new FileOutputStream("/home/shaivi/Downloads/Test.pdf/"));
            document.close();
        }catch (Exception ep) {
            throw new RuntimeException(ep);
        }
    }

    @PostMapping("/downloadingDataWithPDFOnFolder")
    public void downloadDataWithPDF(@RequestBody List<Book> books){
        Document document=new Document();
        try{
            StringBuilder dataPdf=new StringBuilder();
            for(Book book:books){
                dataPdf.append("<tr><td>").append(book.getTitle()).append("</td>").append("<td>").append(book.getPrice()).append("</td>").append("<td>").append(book.getLanguage()).append("</td>").append("<td>").append(book.getAuthor()).append("</td></tr>");
            }
            String k="<!DOCTYPE html>\n" +
                    "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:th=\"http://www.thymeleaf.org\"\n" +
                    "xmlns:float=\"http://www.w3.org/1999/xhtml\">\n" +
                    "<head>\n" +
                    "<meta charset=\"UTF-8\">\n" +
                    "<title>Title</title>\n" +
                    "<link rel=\"stylesheet\"\n" +
                    "href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"container-fluid\">\n" +
                    "<div class=\"row\">\n" +
                    "<div class=\"col-md-12\">\n" +
                    "<h2 style=\"float:center;\"> All Books </h2>\n" +
                    "<div style=\"margin-top:20px\">\n" +
                    "<table id=\"books\" class=\"table table-striped table-bordered\" style=\"width:100%\">\n" +
                    "<thead>\n" +
                    "<tr>\n" +
                    "<th>Title</th>\n" +
                    "<th>Price</th>\n" +
                    "<th>Language</th>\n" +
                    "<th>Author</th>\n" +
                    "</tr>\n"
                    +dataPdf+
                    "</thead>\n" +
                    "</table>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";
            document.open();
            HtmlConverter.convertToPdf(k, new FileOutputStream("src/main/resources/PDF/books-template.pdf"));
            document.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/dataSending")
    public void dataSending(){
        Document document=new Document();
        try {
            document.open();
            HtmlConverter.convertToPdf(new FileInputStream("src/main/resources/templates/about.html"),
                    new FileOutputStream("src/main/resources/PDF/books-template.pdf"));
        }catch(Exception e){
            System.out.println(e);
        }
    }













































    //        @PostMapping("/addBook")
//        public Book addBook(@RequestBody Book book) {
//            Document document=new Document();
//            try{
//                document.open();
//              //  employeeService.addBook(book);
//                System.out.println("method call");
//               return  employeeService.getJson(book);
//               //  System.out.println("json call");
//             //  HtmlConverter.convertToPdf(String.valueOf(book), new FileOutputStream("src/main/resources/PDF/books-template.pdf"));
//             //   document.close();
//           // return EmployeeService.addBook(book);
//            }catch (Exception ep) {
//                throw new RuntimeException(ep);
//            }
//      }
// Workbook workbook = new Workbook(dataDir + "output.xls");

//     this method is used to send data from postman in json format but it's not worked
//    @RequestMapping(path = "/pdf")
//    public ResponseEntity<?> getPDF( HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        Book book = OrderHelper.getOrder();
//
//        WebContext context = new WebContext(request, response, servletContext);
//        context.setVariable("bookEntry", book);
//        String orderHtml = templateEngine.process("book", context);
//
//        ByteArrayOutputStream target = new ByteArrayOutputStream();
//        ConverterProperties converterProperties = new ConverterProperties();
//        converterProperties.setBaseUri("http://localhost:9096");
//
//        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);
//
//        byte[] bytes = target.toByteArray();
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=books-template.pdf")
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(bytes);
//    }
//    @PostMapping("/getData")
//    public JSONArray getData(@RequestBody String title, long price, String language, String author){
//        Document document =new Document();
//        JSONObject jo = new JSONObject();
//        jo.put("title", title);
//        jo.put("price", price);
//        jo.put("language", language);
//        jo.put("author", author);
//
//        JSONArray results = CDL.toJSONArray(jo.names(), title);
//      return results;
//    }



//    @PostMapping("/sendData")
//    public void sendData(@RequestBody String content){
//        Document document=new Document();
//        JSONObject request = new JSONObject();
//        request.put("title", title);
//        request.put("price", price);
//        request.put("language", language);
//        request.put("author", author);
//
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
//
//
//        ResponseEntity<String> loginResponse = restTemplate
//                .exchange(urlString, HttpMethod.POST, entity, String.class);
//        if (loginResponse.getStatusCode() == HttpStatus.OK) {
//            JSONObject bookJson = new JSONObject(loginResponse.getBody());
//        } else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
//
//        }
//    }

//    @PostMapping("/sendData")
//    public Book sendData(@RequestBody PdfRequest dto,String content){
//        return employeeService.createDataForPdf(dto.getTitle(),dto.getPrice(),dto.getLanguage(),dto.getAuthor(),content);
//    }

    //This is also not worked
//    @RequestMapping(value="/saveBook",method = RequestMethod.POST)
//    public ModelAndView save(@ModelAttribute Book book){
//        System.out.println("Book UI "+book);
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.setViewName("books-template");
//        modelAndView.addObject("book",book);
//        return modelAndView;
//    }

}



























