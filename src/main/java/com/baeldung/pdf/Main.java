package com.baeldung.pdf;

import com.itextpdf.text.DocumentException;
import docsGUI.DocsGUI;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DocsGUI()   ;
            }
        });
    }
}
