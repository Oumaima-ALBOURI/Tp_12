
package com.example.filemanagement;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class Main {

    // 1. Lecture avec BufferedReader
    public static void readFileWithBufferedReader(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture : " + e.getMessage());
        }
    }

    // 2. Ecriture avec BufferedWriter
    public static void writeFileWithBufferedWriter(String filePath, String content) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

            bw.write(content);
            bw.newLine();
            bw.write("Ligne ajoutée avec succès.");

            System.out.println("Ecriture réussie.");

        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture : " + e.getMessage());
        }
    }

    // 3. Ecriture avec PrintWriter
    public static void writeWithPrintWriter(String filePath) {

        try (PrintWriter pw = new PrintWriter(new FileOutputStream(filePath))) {

            pw.println("=== Rapport des employés ===");

            pw.printf("Nom: %s, Age: %d, Poste: %s%n",
                    "Alice", 25, "Engineer");

            pw.printf("Nom: %s, Age: %d, Poste: %s%n",
                    "Bob", 30, "Manager");

            pw.println("Fin du rapport.");

            if (pw.checkError()) {
                System.err.println("Erreur pendant l'écriture.");
            } else {
                System.out.println("Rapport créé avec succès.");
            }

        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    // 4. Lecture avec l'API Files
    public static void readWithFilesApi(String filePath) {

        try {

            Path path = Paths.get(filePath);

            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.println("Erreur de lecture : " + e.getMessage());
        }
    }

    // 5. Ecriture avec l'API Files
    public static void writeWithFilesApi(String filePath, String content) {

        try {

            Path path = Paths.get(filePath);

            Files.write(path, content.getBytes());

            System.out.println("Fichier écrit avec succès : " + filePath);

        } catch (IOException e) {
            System.err.println("Erreur d'écriture : " + e.getMessage());
        }
    }

    // 6. Copie de fichier
    public static void copyFile(String source, String destination) {

        try {

            Path sourcePath = Paths.get(source);
            Path destPath = Paths.get(destination);

            Files.copy(sourcePath, destPath,
                    StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Fichier copié avec succès.");

        } catch (IOException e) {
            System.err.println("Erreur lors de la copie : " + e.getMessage());
        }
    }

    // 7. Déplacement de fichier
    public static void moveFile(String source, String destination) {

        try {

            Path sourcePath = Paths.get(source);
            Path destPath = Paths.get(destination);

            Files.move(sourcePath, destPath,
                    StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Fichier déplacé avec succès.");

        } catch (IOException e) {
            System.err.println("Erreur lors du déplacement : " + e.getMessage());
        }
    }

    // 8. Suppression de fichier
    public static void deleteFile(String filePath) {

        try {

            Path path = Paths.get(filePath);

            if (Files.exists(path)) {

                Files.delete(path);

                System.out.println("Fichier supprimé : " + filePath);

            } else {

                System.out.println("Le fichier n'existe pas : " + filePath);
            }

        } catch (NoSuchFileException e) {
            System.err.println("Fichier introuvable.");

        } catch (IOException e) {
            System.err.println("Erreur de suppression : " + e.getMessage());
        }
    }

    // 9. Lecture d'un fichier binaire
    public static void readBinaryFile(String filePath) {

        try (BufferedInputStream bis =
                     new BufferedInputStream(new FileInputStream(filePath))) {

            byte[] buffer = new byte[1024];

            int bytesRead;
            long totalBytes = 0;

            while ((bytesRead = bis.read(buffer)) != -1) {

                totalBytes += bytesRead;
            }

            System.out.println("Fichier binaire lu : " + filePath);
            System.out.println("Taille totale : " + totalBytes + " octets");

        } catch (IOException e) {
            System.err.println("Erreur de lecture binaire : " + e.getMessage());
        }
    }

    // 10. Ecriture d'un fichier binaire
    public static void writeBinaryFile(String filePath, byte[] data) {

        try (BufferedOutputStream bos =
                     new BufferedOutputStream(new FileOutputStream(filePath))) {

            bos.write(data);
            bos.flush();

            System.out.println("Fichier binaire écrit : " + filePath);
            System.out.println("Taille écrite : " + data.length + " octets");

        } catch (IOException e) {
            System.err.println("Erreur d'écriture binaire : " + e.getMessage());
        }
    }

    // 11. Métadonnées du fichier
    public static void getFileMetadata(String filePath) {

        try {

            Path path = Paths.get(filePath);

            BasicFileAttributes attrs =
                    Files.readAttributes(path, BasicFileAttributes.class);

            System.out.println("=== Métadonnées du fichier ===");

            System.out.println("Taille : " + attrs.size() + " octets");

            System.out.println("Dernière modification : "
                    + attrs.lastModifiedTime().toInstant());

            System.out.println("Est un répertoire : " + attrs.isDirectory());

            System.out.println("Est un fichier : " + attrs.isRegularFile());

        } catch (IOException e) {
            System.err.println("Erreur métadonnées : " + e.getMessage());
        }
    }

    // MAIN
    public static void main(String[] args) {

        System.out.println("TP 12 : Gestion des fichiers en Java");

        try {

            // Création du dossier data
            Files.createDirectories(Paths.get("data"));

            // Création du fichier employees.txt
            Files.write(Paths.get("data/employees.txt"),
                    List.of(
                            "Alice,25,Engineer",
                            "Bob,30,Manager",
                            "Charlie,28,Designer"
                    ));

        } catch (IOException e) {
            System.err.println("Erreur d'initialisation : " + e.getMessage());
            return;
        }

        System.out.println("\n1. LECTURE AVEC BUFFEREDREADER");
        readFileWithBufferedReader("data/employees.txt");

        System.out.println("\n2. ECRITURE AVEC BUFFEREDWRITER");
        writeFileWithBufferedWriter(
                "data/output.txt",
                "Bonjour, fichier Java !"
        );

        System.out.println("\n3. ECRITURE AVEC PRINTWRITER");
        writeWithPrintWriter("data/report.txt");

        System.out.println("\n4. LECTURE AVEC FILES");
        readWithFilesApi("data/employees.txt");

        System.out.println("\n5. ECRITURE AVEC FILES");
        writeWithFilesApi(
                "data/nio_output.txt",
                "Test de l'API Files en Java."
        );

        System.out.println("\n6. COPIE DE FICHIER");
        copyFile(
                "data/employees.txt",
                "data/employees_backup.txt"
        );

        System.out.println("\n7. DEPLACEMENT DE FICHIER");
        moveFile(
                "data/nio_output.txt",
                "data/moved_output.txt"
        );

        System.out.println("\n8. SUPPRESSION DE FICHIER");
        deleteFile("data/moved_output.txt");

        System.out.println("\n9. LECTURE BINAIRE");

        readBinaryFile("data/test.jpg");

        System.out.println("\n10. ECRITURE BINAIRE");

        try {
            byte[] imageData = Files.readAllBytes(
                    Paths.get("data/test.jpg")
            );

            writeBinaryFile("data/copy_binary.bin", imageData);

        } catch (IOException e) {
            System.err.println("Image introuvable : " + e.getMessage());
        }

        System.out.println("\n11. METADONNEES");

        getFileMetadata("data/employees.txt");

        System.out.println("\n===== FIN DU TP 12 =====");
    }
}

