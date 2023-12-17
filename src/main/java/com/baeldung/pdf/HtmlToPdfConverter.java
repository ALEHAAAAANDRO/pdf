package com.baeldung.pdf;

import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class HtmlToPdfConverter {

    public static void convertHtmlToPdf(String htmlFilePath, String pdfFilePath) {
        try {
            // Создаем объект ITextRenderer
            ITextRenderer renderer = new ITextRenderer();

            // Устанавливаем путь к HTML файлу
            File inputHtmlFile = new File(htmlFilePath);
            String inputHtmlFilePath = inputHtmlFile.toURI().toURL().toString();
            renderer.setDocument(inputHtmlFilePath);

            // Рендерим PDF в поток вывода
            try (OutputStream os = new FileOutputStream(pdfFilePath)) {
                renderer.layout();
                renderer.createPDF(os);
            }

            System.out.println("Conversion successful. PDF file created at: " + pdfFilePath);
        } catch (Exception e) {
            System.err.println("Error during HTML to PDF conversion: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Пример использования
        String htmlFilePath = "src/main/resources/html.html"; // Путь к HTML файлу
        String pdfFilePath = "src/main/resources/output.pdf"; // Путь, по которому будет сохранен PDF файл
        convertHtmlToPdf(htmlFilePath, pdfFilePath);
    }
}
