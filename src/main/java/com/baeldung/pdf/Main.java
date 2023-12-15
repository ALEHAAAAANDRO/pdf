package com.baeldung.pdf;

import com.itextpdf.text.DocumentException;

import java.io.IOException;

public class Main {

    private static final String FILENAME = "input.docx";

    public static void main(String[] args) {
        try {
            DocxToPDFExample.generatePDFFromDOCX(FILENAME);
            System.out.println("Conversion completed successfully.");
        } catch (IOException | DocumentException e) {
            System.err.println("Error during conversion: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
