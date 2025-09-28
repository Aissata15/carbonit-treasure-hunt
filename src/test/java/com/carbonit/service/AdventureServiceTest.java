package com.carbonit.service;

import com.carbonit.model.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class AdventureServiceTest {

    @Test
    public void testIsValidMove() {
        AdventureService service = new AdventureService();
        com.carbonit.model.Map map = new com.carbonit.model.Map(3, 3);
        map.addMountain(new Position(1, 1));
        java.util.List<Adventurer> adventurers = new ArrayList<>();
        adventurers.add(new Adventurer("A", new Position(0, 0), Orientation.N, ""));

        // Valid move
        Position valid = new Position(0, 1);
        assertTrue(service.isValidMove(map, valid, adventurers));

        // Out of bounds
        Position outOfBounds = new Position(-1, 0);
        assertFalse(service.isValidMove(map, outOfBounds, adventurers));

        // Mountain
        Position mountain = new Position(1, 1);
        assertFalse(service.isValidMove(map, mountain, adventurers));

        // Occupied by adventurer
        Position occupied = new Position(0, 0);
        assertFalse(service.isValidMove(map, occupied, adventurers));
    }
}