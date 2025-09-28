package com.carbonit.service;

import com.carbonit.model.Orientation;
import com.carbonit.model.Position;

/**
 * Service contenant la logique de déplacement des aventuriers.
 * Cette classe peut être étendue pour ajouter des règles plus complexes.
 */
public class MovementService {

    /**
     * Calcule la prochaine position en fonction de l'orientation de l'aventurier.
     * @param pos Position actuelle de l'aventurier
     * @param orientation Orientation actuelle (N, S, E, W)
     * @return Nouvelle position après déplacement
     */
    public Position getNextPosition(Position pos, Orientation orientation) {
        switch (orientation) {
            case N: return new Position(pos.getX(), pos.getY() - 1); // Nord : y-1
            case S: return new Position(pos.getX(), pos.getY() + 1); // Sud : y+1
            case E: return new Position(pos.getX() + 1, pos.getY()); // Est : x+1
            case W: return new Position(pos.getX() - 1, pos.getY()); // Ouest : x-1
            default: return pos; // Orientation inconnue, ne pas bouger
        }
    }
}