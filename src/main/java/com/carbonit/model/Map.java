package com.carbonit.model;

import java.util.HashMap;

/**
 * Represents the game map, including terrain and treasures.
 */
public class Map {
    private final int width;
    private final int height;
    private final java.util.Map<Position, MapCell> cells = new HashMap<>();
    private final java.util.Map<Position, Treasure> treasures = new HashMap<>();

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addMountain(Position pos) {
        cells.put(pos, MapCell.MOUNTAIN);
    }

    public void addTreasure(Position pos, int count) {
        cells.put(pos, MapCell.TREASURE);
        treasures.put(pos, new Treasure(pos, count));
    }

    public boolean isMountain(Position pos) {
        return cells.get(pos) == MapCell.MOUNTAIN;
    }

    public boolean hasTreasure(Position pos) {
        return treasures.containsKey(pos) && treasures.get(pos).getCount() > 0;
    }

    public Treasure getTreasure(Position pos) {
        return treasures.get(pos);
    }

    public java.util.Map<Position, MapCell> getCells() {
        return cells;
    }

    public java.util.Map<Position, Treasure> getTreasures() {
        return treasures;
    }
}