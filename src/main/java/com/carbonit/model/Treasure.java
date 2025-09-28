package com.carbonit.model;

/**
 * Représente un trésor sur la carte.
 * Gère le nombre de trésors restants sur une case.
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
     * Ramasse un trésor si disponible.
     * @return true si un trésor a été ramassé, false sinon
     */
    public boolean collect() {
        if (count > 0) {
            count--;
            return true;
        }
        return false;
    }
}