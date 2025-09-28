package com.carbonit.repository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.carbonit.model.Adventurer;
import com.carbonit.model.Orientation;
import com.carbonit.model.Position;

public class OutputFileRepositoryTest {

    @Test
    public void testWrite() throws Exception {
        String path = "test_output.txt";
        com.carbonit.model.Map map = new com.carbonit.model.Map(2, 2);
        map.addMountain(new Position(1, 1));
        map.addTreasure(new Position(0, 1), 1);
        Adventurer adv = new Adventurer("Indy", new Position(0, 0), Orientation.N, "");
        adv.collectTreasure();
        java.util.List<Adventurer> adventurers = Collections.singletonList(adv);

        OutputFileRepository repo = new OutputFileRepository();
        repo.write(path, map, adventurers);

        String content = new String(Files.readAllBytes(Paths.get(path)));
        assertTrue(content.contains("C - 2 - 2"));
        assertTrue(content.contains("M - 1 - 1"));
        assertTrue(content.contains("T - 0 - 1 - 1"));
        assertTrue(content.contains("A - Indy - 0 - 0 - N - 1"));

        // nettoyage
        Files.deleteIfExists(Paths.get(path));
    }
}