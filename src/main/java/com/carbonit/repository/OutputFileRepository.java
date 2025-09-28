package com.carbonit.repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.carbonit.model.Adventurer;
import com.carbonit.model.Position;
import com.carbonit.model.Treasure;

/**
 * Cette classe permet d'écrire l'état final de la carte et des aventuriers dans le fichier de sortie.
 * Elle écrit les dimensions de la carte, les montagnes, les trésors restants et la position finale des aventuriers.
 */
public class OutputFileRepository {

    /**
     * Écrit l'état final du jeu dans le fichier de sortie.
     * @param filename Chemin du fichier de sortie
     * @param map La carte du jeu
     * @param adventurers Liste des aventuriers
     * @throws IOException En cas d'erreur d'écriture
     */
    public void write(String filename, com.carbonit.model.Map map, List<Adventurer> adventurers) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        // Écrit les dimensions de la carte
        writer.write(String.format("C - %d - %d\n", map.getWidth(), map.getHeight()));
        // Écrit les montagnes
        for (Position pos : map.getCells().keySet()) {
            if (map.isMountain(pos)) {
                writer.write(String.format("M - %d - %d\n", pos.getX(), pos.getY()));
            }
        }
        // Écrit les trésors restants
        for (Treasure treasure : map.getTreasures().values()) {
            if (treasure.getCount() > 0) {
                Position pos = treasure.getPosition();
                writer.write(String.format("T - %d - %d - %d\n", pos.getX(), pos.getY(), treasure.getCount()));
            }
        }
        // Écrit la position et le nombre de trésors ramassés par chaque aventurier
        for (Adventurer adv : adventurers) {
            Position pos = adv.getPosition();
            writer.write(String.format("A - %s - %d - %d - %s - %d\n",
                adv.getName(), pos.getX(), pos.getY(), adv.getOrientation(), adv.getTreasuresCollected()));
        }
        writer.close();
    }
}