package com.carbonit.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TreasureTest {

    @Test
    public void testCollect() {
        Treasure treasure = new Treasure(new Position(0, 0), 2);
        assertTrue(treasure.collect());
        assertEquals(1, treasure.getCount());
        assertTrue(treasure.collect());
        assertEquals(0, treasure.getCount());
        assertFalse(treasure.collect());
    }
}