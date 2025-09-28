package com.carbonit.service;

import com.carbonit.model.Map;
import com.carbonit.model.Position;
import com.carbonit.model.Treasure;

/**
 * Service contenant la logique de collecte des trésors.
 * Cette classe permet de gérer le ramassage d'un trésor sur une case donnée.
 */
public class TreasureService {

    /**
     * Tente de ramasser un trésor à la position donnée sur la carte.
     * @param map La carte du jeu
     * @param position La position où l'on tente de ramasser un trésor
     * @return true si un trésor a été ramassé, false sinon
     */
    public boolean collectTreasure(Map map, Position position) {
        Treasure treasure = map.getTreasure(position);
        // Vérifie qu'il y a un trésor disponible sur la case
        if (treasure != null && treasure.getCount() > 0) {
            return treasure.collect(); // Ramasse un trésor
        }
        return false; // Aucun trésor ramassé
    }
}