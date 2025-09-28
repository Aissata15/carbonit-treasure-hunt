package com.carbonit.model;

import java.util.HashMap;

/**
 * Représente la carte du jeu, avec sa largeur, sa hauteur,
 * les montagnes et les trésors placés sur les cases.
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
    /**
     * Ajoute une montagne à la position donnée.
     */
    public void addMountain(Position pos) {
        cells.put(pos, MapCell.MOUNTAIN);
    }

    /**
     * Ajoute un trésor à la position donnée.
     */
    public void addTreasure(Position pos, int count) {
        cells.put(pos, MapCell.TREASURE);
        treasures.put(pos, new Treasure(pos, count));
    }
    /**
     * Vérifie si une position est une montagne.
     */
    public boolean isMountain(Position pos) {
        return cells.get(pos) == MapCell.MOUNTAIN;
    }
    /**
     * Vérifie s'il y a un trésor sur la position donnée.
     */
    public boolean hasTreasure(Position pos) {
        return treasures.containsKey(pos) && treasures.get(pos).getCount() > 0;
    }
    /**
     * Retourne le trésor à la position donnée.
     */
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