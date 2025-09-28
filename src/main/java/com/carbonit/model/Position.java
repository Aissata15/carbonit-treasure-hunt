package com.carbonit.model;

/**
 * Représente une position (x, y) sur la carte.
 * Utilisée pour localiser les aventuriers, montagnes et trésors.
 */
public class Position {
    private int x; // Coordonnée horizontale
    private int y; // Coordonnée verticale

    /**
     * Constructeur pour initialiser une position avec les coordonnées x et y.
     * @param x Coordonnée horizontale
     * @param y Coordonnée verticale
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retourne la coordonnée x.
     */
    public int getX() {
        return x;
    }

    /**
     * Retourne la coordonnée y.
     */
    public int getY() {
        return y;
    }

    /**
     * Modifie la coordonnée x.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Modifie la coordonnée y.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Vérifie si deux positions sont identiques (mêmes coordonnées).
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) return false;
        Position other = (Position) obj;
        return this.x == other.x && this.y == other.y;
    }

    /**
     * Calcule le hashCode pour permettre l'utilisation dans des collections.
     */
    @Override
    public int hashCode() {
        return x * 31 + y;
    }
}