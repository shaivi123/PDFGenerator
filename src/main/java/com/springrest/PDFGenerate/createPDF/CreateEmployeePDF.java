package com.springrest.PDFGenerate.createPDF;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.springrest.PDFGenerate.model.Employee;
import javax.servlet.ServletContext;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CreateEmployeePDF {

    public CreateEmployeePDF(String abc){

    }

    //private static Logger

    public static ByteArrayInputStream employeePDFReport(List<Employee> employees) throws IOException {
//        String filePDF=context.getRealPath("");
//        ArrayList<Employee> studentList = new ArrayList();
//        Employee s1 = new Employee(1,"Sachin","mishra",25,"Sachine@gmail.com",765678634);
//        //Employee s2 = new Employee(2, "Rahul","Rahul@gmail.com");
//        studentList.add(s1);
//      //
        String filePDF="/home/shaivi/Desktop/PDF/";
        ByteArrayOutputStream out;
        try {
           // Document document = new Document();
            //PdfWriter writer=new PdfWriter(filePDF);
            //PdfDocument pdfDoc=new PdfDocument(writer);
            Document document=new Document();
            out = new ByteArrayOutputStream();

            PdfWriter.getInstance(document, out);
            document.open();

            //Add text code to PDF
            Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 14, BaseColor.GRAY);
            Paragraph para = new Paragraph("Employee records", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);
            PdfPTable table = new PdfPTable(6);
            //Add PDF table header
            Stream.of("id", "firstName", "lastName", "age", "mail", "mobile")
                    .forEach(headerTitle -> {
                        PdfPCell header = new PdfPCell();
                        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                        header.setBackgroundColor(BaseColor.MAGENTA);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setBorder(2);
                        header.setPhrase(new Phrase(headerTitle, headFont));
                        table.addCell(header);
                    });
            for (Employee emp : employees) {
                String ids = String.valueOf(emp.getId());
                PdfPCell idCell = new PdfPCell(new Phrase(ids));
                idCell.setPadding(4);
                idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);

                PdfPCell firstNameCell = new PdfPCell(new Phrase(emp.getFirstName()));
                firstNameCell.setPadding(4);
                firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                firstNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(firstNameCell);

                PdfPCell lastNameCell = new PdfPCell(new Phrase(emp.getLastName()));
                lastNameCell.setPadding(4);
                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(lastNameCell);

                String ages = String.valueOf(emp.getAge());
                PdfPCell ageCell = new PdfPCell(new Phrase(ages));
                ageCell.setPadding(4);
                ageCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(ageCell);

                PdfPCell mailCell = new PdfPCell(new Phrase(emp.getMail()));
                mailCell.setPadding(4);
                mailCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                mailCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(mailCell);

                String mobiles = String.valueOf(emp.getMobile());
                PdfPCell mobileCell = new PdfPCell(new Phrase(mobiles));
                mobileCell.setPadding(4);
                mobileCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                mobileCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(mobileCell);
            }
            document.add(table);
            document.close();


        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
