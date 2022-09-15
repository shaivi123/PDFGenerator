package com.springrest.PDFGenerate.dto;

import lombok.*;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PdfRequest {




        private String title;
        private int price;
        private String language;
        private String author;

    }


