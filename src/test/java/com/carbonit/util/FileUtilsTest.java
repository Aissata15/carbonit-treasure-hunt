package com.carbonit.util;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FileUtilsTest {

    @Test
    public void testWriteAndReadFile() throws IOException {
        String path = "testfile.txt";
        String content = "Hello, CarbonIT!";
        FileUtils.writeFile(path, content);
        String read = FileUtils.readFile(path).trim();
        assertEquals(content, read);
        // Clean up
        java.nio.file.Files.deleteIfExists(java.nio.file.Paths.get(path));
    }
}