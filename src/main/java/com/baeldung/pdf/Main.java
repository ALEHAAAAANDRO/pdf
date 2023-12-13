package com.baeldung.pdf;

import com.itextpdf.text.DocumentException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            DocxToPDFExample.main(args);
            System.out.println("Conversion completed successfully.");
        } catch (IOException | DocumentException e) {
            System.err.println("Error during conversion: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
