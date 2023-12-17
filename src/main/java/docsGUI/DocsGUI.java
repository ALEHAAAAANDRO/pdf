package docsGUI;

import com.baeldung.pdf.PDF2HTMLExample;
import com.baeldung.pdf.PDF2TextExample;
import com.baeldung.pdf.PDF2WordExample;
import crypto.Crypto;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;

public class DocsGUI {

    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JTextField file1FieldEncrypt;
    private JTextField file2FieldEncrypt;
    private JTextField file1FieldDecrypt;
    private JTextField file2FieldDecrypt;
    private JTextField file1FieldConvert;

    public DocsGUI() {
        frame = new JFrame("Docs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 200);

        tabbedPane = new JTabbedPane();

        // Создаем вкладки
        JPanel encryptTab = createEncryptTab();
        tabbedPane.addTab("Шифрование", encryptTab);

        JPanel decryptTab = createDecryptTab();
        tabbedPane.addTab("Дешифрование", decryptTab);

        JPanel convertTab = createConvertTab();
        tabbedPane.addTab("Конвертер", convertTab);

        // Добавляем вкладки к панели вкладок
        frame.add(tabbedPane);

        frame.setVisible(true);
    }

    private JPanel createEncryptTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel file1Label = new JLabel("Файл:");
        file1FieldEncrypt = new JTextField();
        JButton file1Button = new JButton("Выбрать файл");

        JLabel file2Label = new JLabel("Ключ:");
        file2FieldEncrypt = new JTextField();
        JButton file2Button = new JButton("Выбрать ключ");

        JButton processButton = new JButton("Обработать");

        file1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Добавьте код для выбора файла
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    file1FieldEncrypt.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        file2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Добавьте код для выбора файла 2
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    file2FieldEncrypt.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Добавьте код для обработки выбранных файлов
                Path filePath1 = Path.of(file1FieldEncrypt.getText());
                Path filePath2 = Path.of(file2FieldEncrypt.getText());

                // Ваши действия с выбранными файлами
                try {
                    Crypto.encryptFile(filePath1, filePath2, filePath1.getParent());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        panel.add(file1Label);
        panel.add(file1FieldEncrypt);
        panel.add(file1Button);
        panel.add(file2Label);
        panel.add(file2FieldEncrypt);
        panel.add(file2Button);
        panel.add(processButton);
        return panel;
    }

    private JPanel createDecryptTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel file1Label = new JLabel("Зашифрованный файл:");
        file1FieldDecrypt = new JTextField();
        JButton file1Button = new JButton("Выбрать файл");

        JLabel file2Label = new JLabel("Ключ:");
        file2FieldDecrypt = new JTextField();
        JButton file2Button = new JButton("Выбрать ключ");

        JButton processButton = new JButton("Обработать");

        file1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Добавьте код для выбора файла
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    file1FieldDecrypt.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        file2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Добавьте код для выбора файла 2
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    file2FieldDecrypt.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Добавьте код для обработки выбранных файлов
                Path filePath1 = Path.of(file1FieldDecrypt.getText());
                Path filePath2 = Path.of(file2FieldDecrypt.getText());

                // Ваши действия с выбранными файлами
                try {
                    Crypto.decryptFile(filePath1, filePath2, filePath1.getParent());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        panel.add(file1Label);
        panel.add(file1FieldDecrypt);
        panel.add(file1Button);
        panel.add(file2Label);
        panel.add(file2FieldDecrypt);
        panel.add(file2Button);
        panel.add(processButton);
        return panel;
    }

    private JPanel createConvertTab() {
        JTextField file1FieldConvert;
        JComboBox<String> fromFormatComboBox;
        JComboBox<String> toFormatComboBox;

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel file1Label = new JLabel("Файл:");
        file1FieldConvert = new JTextField();
        JButton file1Button = new JButton("Выбрать файл");

        JLabel fromFormatLabel = new JLabel("Из формата:");
        fromFormatComboBox = new JComboBox<>(new String[]{"PDF"});

        JLabel toFormatLabel = new JLabel("В формат:");
        toFormatComboBox = new JComboBox<>(new String[]{"HTML", "Image", "Text", "Docx"});

        JButton processButton = new JButton("Обработать");

        file1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    file1FieldConvert.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Path choosenFile = Path.of(file1FieldConvert.getText());
                String fromFormat = (String) fromFormatComboBox.getSelectedItem();
                String toFormat = (String) toFormatComboBox.getSelectedItem();

                if(fromFormat == "PDF"){
                    switch (toFormat){
                        case "HTML":
                            try {
                                PDF2HTMLExample.generateHTMLFromPDF(String.valueOf(choosenFile));
                            } catch (ParserConfigurationException | IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            break;

                        case "Text":
                            try {
                                PDF2TextExample.generateTxtFromPDF(String.valueOf(choosenFile));
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                        case "Docx":
                            try {
                                PDF2WordExample.generateDocFromPDF(String.valueOf(choosenFile));
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }


                    }
                }
            }
        });

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy++;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(file1Label, gbc);
        gbc.gridx++;
        panel.add(file1FieldConvert, gbc);
        gbc.gridx++;
        panel.add(file1Button, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(fromFormatLabel, gbc);
        gbc.gridx++;
        panel.add(fromFormatComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(toFormatLabel, gbc);
        gbc.gridx++;
        panel.add(toFormatComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(processButton, gbc);

        return panel;
    }
}
