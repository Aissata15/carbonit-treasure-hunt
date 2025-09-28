package com.carbonit.model;

/**
 * Represents a treasure on the map.
 */
public class Treasure {
    private final Position position;
    private int count;

    public Treasure(Position position, int count) {
        this.position = position;
        this.count = count;
    }

    public Position getPosition() {
        return position;
    }

    public int getCount() {
        return count;
    }

    /**
     * Collects a treasure if available.
     * @return true if a treasure was collected, false otherwise.
     */
    public boolean collect() {
        if (count > 0) {
            count--;
            return true;
        }
        return false;
    }
}