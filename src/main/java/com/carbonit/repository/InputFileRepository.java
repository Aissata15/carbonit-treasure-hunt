package com.carbonit.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.carbonit.model.Adventurer;
import com.carbonit.model.Orientation;
import com.carbonit.model.Position;

/**
 * Reads the input file and builds the map and adventurers.
 */
public class InputFileRepository {

    public static class MapData {
        public com.carbonit.model.Map map;
        public List<Adventurer> adventurers;
    }

    public MapData read(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        com.carbonit.model.Map map = null;
        List<Adventurer> adventurers = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#")) continue; // <-- Ignore comments and empty lines
            String[] parts = line.split(" - ");
            switch (parts[0]) {
                case "C":
                    map = new com.carbonit.model.Map(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    break;
                case "M":
                    map.addMountain(new Position(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
                    break;
                case "T":
                    map.addTreasure(new Position(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])), Integer.parseInt(parts[3]));
                    break;
                case "A":
                    Adventurer adv = new Adventurer(
                        parts[1],
                        new Position(Integer.parseInt(parts[2]), Integer.parseInt(parts[3])),
                        Orientation.valueOf(parts[4]),
                        parts[5]
                    );
                    adventurers.add(adv);
                    break;
            }
        }
        reader.close();
        MapData data = new MapData();
        data.map = map;
        data.adventurers = adventurers;
        return data;
    }
}