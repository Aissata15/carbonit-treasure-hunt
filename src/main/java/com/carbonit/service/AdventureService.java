package com.carbonit.service;

import java.util.List;

import com.carbonit.model.Adventurer;
import com.carbonit.model.Map;
import com.carbonit.model.Orientation;
import com.carbonit.model.Position;
import com.carbonit.model.Treasure;
import com.carbonit.repository.InputFileRepository;
import com.carbonit.repository.OutputFileRepository;

/**
 * Service pour gérer les actions des aventuriers sur la carte.
 * Cette classe contient la logique pour valider les déplacements,
 * ramasser les trésors et appliquer les règles du jeu.
 */
public class AdventureService {

    /**
     * Lance la simulation à partir d'un fichier d'entrée et écrit le résultat dans un fichier de sortie.
     * @param inputFile Chemin du fichier d'entrée
     * @param outputFile Chemin du fichier de sortie
     */
    public void run(String inputFile, String outputFile) {
        try {
            // Lecture des données d'entrée (carte, aventuriers, trésors, montagnes)
            InputFileRepository inputRepo = new InputFileRepository();
            InputFileRepository.MapData data = inputRepo.read(inputFile);
            Map map = data.map;
            List<Adventurer> adventurers = data.adventurers;

            // Simulation des mouvements tour par tour
            boolean movesLeft;
            do {
                movesLeft = false;
                for (Adventurer adv : adventurers) {
                    // Si l'aventurier a encore des mouvements à effectuer
                    if (!adv.getMoves().isEmpty()) {
                        char move = adv.getMoves().poll(); // Récupère le prochain mouvement
                        switch (move) {
                            case 'A': // Avancer
                                Position next = getNextPosition(adv.getPosition(), adv.getOrientation());
                                // Vérifie si le déplacement est valide (pas de montagne, pas d'autre aventurier)
                                if (isValidMove(map, next, adventurers)) {
                                    adv.setPosition(next);
                                    // Ramasse un trésor si présent sur la case
                                    if (map.hasTreasure(next)) {
                                        Treasure t = map.getTreasure(next);
                                        if (t.collect()) adv.collectTreasure();
                                    }
                                }
                                break;
                            case 'G': // Tourner à gauche
                                adv.turnLeft();
                                break;
                            case 'D': // Tourner à droite
                                adv.turnRight();
                                break;
                        }
                        movesLeft = true; // Il reste des mouvements à effectuer
                    }
                }
            } while (movesLeft); // Continue tant qu'il reste des mouvements

            // Écriture du résultat final dans le fichier de sortie
            OutputFileRepository outputRepo = new OutputFileRepository();
            outputRepo.write(outputFile, map, adventurers);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Calcule la prochaine position en fonction de l'orientation de l'aventurier.
     * @param pos Position actuelle
     * @param orientation Orientation actuelle
     * @return Nouvelle position après déplacement
     */
    private Position getNextPosition(Position pos, Orientation orientation) {
        switch (orientation) {
            case N: return new Position(pos.getX(), pos.getY() - 1); // Nord
            case S: return new Position(pos.getX(), pos.getY() + 1); // Sud
            case E: return new Position(pos.getX() + 1, pos.getY()); // Est
            case W: return new Position(pos.getX() - 1, pos.getY()); // Ouest
            default: return pos;
        }
    }

    /**
     * Vérifie si le déplacement vers une position est valide.
     * @param map La carte du jeu
     * @param pos La position cible
     * @param adventurers Liste des aventuriers
     * @return true si le déplacement est possible, false sinon
     */
    public boolean isValidMove(Map map, Position pos, java.util.List<Adventurer> adventurers) {
        // Vérifie les limites de la carte
        if (pos.getX() < 0 || pos.getY() < 0 || pos.getX() >= map.getWidth() || pos.getY() >= map.getHeight())
            return false;
        // Vérifie si la case est une montagne
        if (map.isMountain(pos)) return false;
        // Vérifie si un autre aventurier occupe la case
        for (Adventurer adv : adventurers) {
            if (adv.getPosition().equals(pos)) return false;
        }
        return true;
    }
}