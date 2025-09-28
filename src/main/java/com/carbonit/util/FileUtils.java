package com.carbonit.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe utilitaire pour les opérations courantes sur les fichiers.
 * Permet de lire et d'écrire facilement le contenu d'un fichier texte.
 */
public class FileUtils {

    /**
     * Lit tout le contenu d'un fichier et le retourne sous forme de String.
     * @param path Chemin du fichier à lire
     * @return Contenu du fichier sous forme de chaîne de caractères
     * @throws IOException En cas d'erreur de lecture
     */
    public static String readFile(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    /**
     * Écrit le contenu donné dans un fichier.
     * @param path Chemin du fichier à écrire
     * @param content Contenu à écrire dans le fichier
     * @throws IOException En cas d'erreur d'écriture
     */
    public static void writeFile(String path, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(content);
        }
    }
}