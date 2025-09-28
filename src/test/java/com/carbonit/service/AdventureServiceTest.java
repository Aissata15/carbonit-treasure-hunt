package com.carbonit.service;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.carbonit.model.Adventurer;
import com.carbonit.model.Orientation;
import com.carbonit.model.Position;

public class AdventureServiceTest {

    @Test
    public void testIsValidMove() {
        AdventureService service = new AdventureService();
        com.carbonit.model.Map map = new com.carbonit.model.Map(3, 3);
        map.addMountain(new Position(1, 1));
        java.util.List<Adventurer> adventurers = new ArrayList<>();
        adventurers.add(new Adventurer("A", new Position(0, 0), Orientation.N, ""));

        // movement valide
        Position valid = new Position(0, 1);
        assertTrue(service.isValidMove(map, valid, adventurers));

        // hors carte
        Position outOfBounds = new Position(-1, 0);
        assertFalse(service.isValidMove(map, outOfBounds, adventurers));

        // Montagne
        Position mountain = new Position(1, 1);
        assertFalse(service.isValidMove(map, mountain, adventurers));

        // Occupé par un aventurier
        Position occupied = new Position(0, 0);
        assertFalse(service.isValidMove(map, occupied, adventurers));
    }
}