package com.carbonit.model;

/**
 * Represents an adventurer on the map.
 */
public class Adventurer {
    private final String name;
    private Position position;
    private Orientation orientation;
    private final String moves;
    private int treasuresCollected = 0;

    public Adventurer(String name, Position position, Orientation orientation, String moves) {
        this.name = name;
        this.position = position;
        this.orientation = orientation;
        this.moves = moves;
    }

    public String getName() { return name; }
    public Position getPosition() { return position; }
    public void setPosition(Position position) { this.position = position; }
    public Orientation getOrientation() { return orientation; }
    public void turnLeft() { orientation = orientation.turnLeft(); }
    public void turnRight() { orientation = orientation.turnRight(); }
    public String getMoves() { return moves; }
    public void collectTreasure() { treasuresCollected++; }
    public int getTreasuresCollected() { return treasuresCollected; }
}