package com.carbonit.service;

import com.carbonit.model.Adventurer;
import com.carbonit.model.Position;
import com.carbonit.model.Orientation;

/**
 * Service for movement logic (can be extended for more complex rules).
 */
public class MovementService {

    /**
     * Calculates the next position based on orientation.
     */
    public Position getNextPosition(Position pos, Orientation orientation) {
        switch (orientation) {
            case N: return new Position(pos.getX(), pos.getY() - 1);
            case S: return new Position(pos.getX(), pos.getY() + 1);
            case E: return new Position(pos.getX() + 1, pos.getY());
            case W: return new Position(pos.getX() - 1, pos.getY());
            default: return pos;
        }
    }
}