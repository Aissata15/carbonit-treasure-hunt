package com.carbonit.service;

import com.carbonit.model.Map;
import com.carbonit.model.Position;
import com.carbonit.model.Treasure;

/**
 * Service for treasure collection logic.
 */
public class TreasureService {

    /**
     * Attempts to collect a treasure at the given position.
     * @return true if a treasure was collected, false otherwise.
     */
    public boolean collectTreasure(Map map, Position position) {
        Treasure treasure = map.getTreasure(position);
        if (treasure != null && treasure.getCount() > 0) {
            return treasure.collect();
        }
        return false;
    }
}