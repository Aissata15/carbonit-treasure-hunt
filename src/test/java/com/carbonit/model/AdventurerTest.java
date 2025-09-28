package com.carbonit.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AdventurerTest {

    @Test
    public void testCollectTreasure() {
        Adventurer adv = new Adventurer("Test", new Position(0, 0), Orientation.N, "A");
        assertEquals(0, adv.getTreasuresCollected());
        adv.collectTreasure();
        assertEquals(1, adv.getTreasuresCollected());
    }

    @Test
    public void testTurnLeftRight() {
        Adventurer adv = new Adventurer("Test", new Position(0, 0), Orientation.N, "A");
        adv.turnLeft();
        assertEquals(Orientation.W, adv.getOrientation());
        adv.turnRight();
        assertEquals(Orientation.N, adv.getOrientation());
    }
}