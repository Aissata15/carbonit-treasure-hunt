package com.carbonit.repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.carbonit.model.Adventurer;
import com.carbonit.model.Position;
import com.carbonit.model.Treasure;

/**
 * Writes the final state of the map and adventurers to the output file.
 */
public class OutputFileRepository {

    public void write(String filename, com.carbonit.model.Map map, List<Adventurer> adventurers) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(String.format("C - %d - %d\n", map.getWidth(), map.getHeight()));
        // Write mountains
        for (Position pos : map.getCells().keySet()) {
            if (map.isMountain(pos)) {
                writer.write(String.format("M - %d - %d\n", pos.getX(), pos.getY()));
            }
        }
        // Write treasures
        for (Treasure treasure : map.getTreasures().values()) {
            if (treasure.getCount() > 0) {
                Position pos = treasure.getPosition();
                writer.write(String.format("T - %d - %d - %d\n", pos.getX(), pos.getY(), treasure.getCount()));
            }
        }
        // Write adventurers
        for (Adventurer adv : adventurers) {
            Position pos = adv.getPosition();
            writer.write(String.format("A - %s - %d - %d - %s - %d\n",
                adv.getName(), pos.getX(), pos.getY(), adv.getOrientation(), adv.getTreasuresCollected()));
        }
        writer.close();
    }
}