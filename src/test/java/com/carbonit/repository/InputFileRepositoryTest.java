package com.carbonit.repository;

import java.io.FileWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.carbonit.model.Adventurer;
import com.carbonit.model.Position;

public class InputFileRepositoryTest {

    @Test
    public void testRead() throws Exception {
        String path = "test_input.txt";
        try (FileWriter writer = new FileWriter(path)) {
            writer.write("C - 2 - 2\n");
            writer.write("M - 1 - 1\n");
            writer.write("T - 0 - 1 - 1\n");
            writer.write("A - Indy - 0 - 0 - N - AAD\n");
        }
        InputFileRepository repo = new InputFileRepository();
        InputFileRepository.MapData data = repo.read(path);

        assertNotNull(data.map);
        assertEquals(2, data.map.getWidth());
        assertEquals(2, data.map.getHeight());
        assertTrue(data.map.isMountain(new Position(1, 1)));
        assertTrue(data.map.hasTreasure(new Position(0, 1)));
        List<Adventurer> advs = data.adventurers;
        assertEquals(1, advs.size());
        assertEquals("Indy", advs.get(0).getName());

        // nettoyage
        java.nio.file.Files.deleteIfExists(java.nio.file.Paths.get(path));
    }
}