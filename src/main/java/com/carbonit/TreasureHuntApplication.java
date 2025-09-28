package com.carbonit;

import java.util.List;

import com.carbonit.model.Adventurer;
import com.carbonit.model.Map;
import com.carbonit.model.Position;
import com.carbonit.model.Treasure;
import com.carbonit.repository.InputFileRepository;
import com.carbonit.repository.OutputFileRepository;
import com.carbonit.service.MovementService;

/**
 * Point d'entrée de l'application Treasure Hunt.
 * Cette classe lit le fichier d'entrée, exécute la simulation tour par tour,
 * puis écrit le résultat dans le fichier de sortie.
 */
public class TreasureHuntApplication {
    public static void main(String[] args) throws Exception {
        // Vérifie que les chemins des fichiers d'entrée et de sortie sont fournis
        if (args.length < 2) {
            System.out.println("Usage: java -jar treasure-hunt.jar <input.txt> <output.txt>");
            return;
        }
        String inputPath = args[0];
        String outputPath = args[1];

        // Lecture du fichier d'entrée et initialisation de la carte et des aventuriers
        InputFileRepository inputRepo = new InputFileRepository();
        InputFileRepository.MapData data = inputRepo.read(inputPath);
        Map map = data.map;
        List<Adventurer> adventurers = data.adventurers;

        MovementService movementService = new MovementService();

        // Détermine le nombre maximum de mouvements à effectuer (le plus long des aventuriers)
        int maxMoves = adventurers.stream()
                .mapToInt(a -> a.getMoves().length())
                .max()
                .orElse(0);

        // Boucle principale de la simulation : chaque tour, chaque aventurier joue son mouvement
        for (int turn = 0; turn < maxMoves; turn++) {
            for (Adventurer adv : adventurers) {
                if (turn < adv.getMoves().length()) {
                    char move = adv.getMoves().charAt(turn);
                    switch (move) {
                        case 'A':
                            // Calcul de la prochaine position selon l'orientation
                            Position nextPos = movementService.getNextPosition(adv.getPosition(), adv.getOrientation());
                            // Vérifie que le déplacement est valide (dans la carte, pas de montagne, pas d'autre aventurier)
                            boolean valid = nextPos.getX() >= 0 && nextPos.getX() < map.getWidth()
                                    && nextPos.getY() >= 0 && nextPos.getY() < map.getHeight()
                                    && !map.isMountain(nextPos)
                                    && adventurers.stream().noneMatch(a -> a != adv && a.getPosition().equals(nextPos));
                            if (valid) {
                                adv.setPosition(nextPos);
                                // Ramasse un trésor si présent sur la case
                                if (map.hasTreasure(nextPos)) {
                                    Treasure t = map.getTreasure(nextPos);
                                    if (t.collect()) {
                                        adv.collectTreasure();
                                    }
                                }
                            }
                            break;
                        case 'G':
                            adv.turnLeft(); // Tourne à gauche
                            break;
                        case 'D':
                            adv.turnRight(); // Tourne à droite
                            break;
                        default:
                            // Ignore les mouvements inconnus
                    }
                }
            }
        }

        // Écriture du résultat final dans le fichier de sortie
        OutputFileRepository outputRepo = new OutputFileRepository();
        outputRepo.write(outputPath, map, adventurers);

        System.out.println("Simulation complete. Output written to " + outputPath);
    }
}