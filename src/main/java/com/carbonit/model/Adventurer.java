package com.carbonit.model;

/**
 * Représente un aventurier sur la carte.
 * Contient son nom, sa position, son orientation, sa séquence de mouvements et le nombre de trésors ramassés.
 */
public class Adventurer {
    private final String name;
    private Position position;
    private Orientation orientation;
    private java.util.Queue<Character> moves; // File de mouvements à effectuer
    private int treasuresCollected = 0;

    /**
     * Constructeur de l'aventurier.
     * @param name Nom de l'aventurier
     * @param position Position initiale
     * @param orientation Orientation initiale
     * @param moves Séquence de mouvements (sous forme de chaîne de caractères)
     */
    public Adventurer(String name, Position position, Orientation orientation, String moves) {
        this.name = name;
        this.position = position;
        this.orientation = orientation;
        this.moves = new java.util.LinkedList<>();
        for (char c : moves.toCharArray()) {
            this.moves.add(c);
        }
    }

    public String getName() { return name; }
    public Position getPosition() { return position; }
    public void setPosition(Position position) { this.position = position; }
    public Orientation getOrientation() { return orientation; }
    public void turnLeft() { orientation = orientation.turnLeft(); }
    public void turnRight() { orientation = orientation.turnRight(); }
    public java.util.Queue<Character> getMoves() { return moves; }
    public void collectTreasure() { treasuresCollected++; }
    public int getTreasuresCollected() { return treasuresCollected; }
}