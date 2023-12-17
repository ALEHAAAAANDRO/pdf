package crypto;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Key;

public class Crypto {

    private static final String ALGORITHM = "AES";

    // Метод для считывания ключа из файла на флешке
    private static Key readKeyFromFile(Path keyFilePath) throws IOException {
        byte[] keyBytes = Files.readAllBytes(keyFilePath);
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    // Метод для шифрования файла
    public static void encryptFile(Path inputFilePath, Path keyFilePath, Path outputDirectory) throws Exception {
        Key key = readKeyFromFile(keyFilePath);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        try (FileInputStream fis = new FileInputStream(String.valueOf(inputFilePath));
             CipherInputStream cis = new CipherInputStream(fis, cipher)) {

            // Получаем имя файла
            Path fileName = inputFilePath.getFileName();

            // Формируем имя зашифрованного файла
            Path encryptedFilePath = Path.of(outputDirectory + File.separator
                    + fileName + ".enc");

            try (FileOutputStream fos = new FileOutputStream(String.valueOf(encryptedFilePath))) {
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = cis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            }
        }
    }

    // Метод для дешифрования файла
    public static void decryptFile(Path inputFilePath, Path keyFilePath, Path outputDirectory) throws Exception {
        Key key = readKeyFromFile(keyFilePath);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);

        try (FileInputStream fis = new FileInputStream(String.valueOf(inputFilePath));
             CipherInputStream cis = new CipherInputStream(fis, cipher)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            String fileName = String.valueOf(inputFilePath.getFileName());

            Path decryptedFilePath = Path.of(outputDirectory + File.separator
                    + "decrypted_of_" + fileName.substring(0, fileName.lastIndexOf('.')));

            try (FileOutputStream fos = new FileOutputStream(String.valueOf(decryptedFilePath))) {


                while ((bytesRead = cis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            }
        }
    }
}
