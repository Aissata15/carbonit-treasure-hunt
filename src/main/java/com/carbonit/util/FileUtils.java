package com.carbonit.util;

import java.io.*;

/**
 * Utility class for common file operations.
 */
public class FileUtils {

    /**
     * Reads the entire content of a file as a String.
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
     * Writes the given content to a file.
     */
    public static void writeFile(String path, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(content);
        }
    }
}