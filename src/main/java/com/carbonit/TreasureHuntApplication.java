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
 * Entry point for the Treasure Hunt application.
 */
public class TreasureHuntApplication {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java -jar treasure-hunt.jar <input.txt> <output.txt>");
            return;
        }
        String inputPath = args[0];
        String outputPath = args[1];

        // Read input
        InputFileRepository inputRepo = new InputFileRepository();
        InputFileRepository.MapData data = inputRepo.read(inputPath);
        Map map = data.map;
        List<Adventurer> adventurers = data.adventurers;

        MovementService movementService = new MovementService();

        int maxMoves = adventurers.stream()
                .mapToInt(a -> a.getMoves().length())
                .max()
                .orElse(0);

        for (int turn = 0; turn < maxMoves; turn++) {
            for (Adventurer adv : adventurers) {
                if (turn < adv.getMoves().length()) {
                    char move = adv.getMoves().charAt(turn);
                    switch (move) {
                        case 'A':
                            Position nextPos = movementService.getNextPosition(adv.getPosition(), adv.getOrientation());
                            boolean valid = nextPos.getX() >= 0 && nextPos.getX() < map.getWidth()
                                    && nextPos.getY() >= 0 && nextPos.getY() < map.getHeight()
                                    && !map.isMountain(nextPos)
                                    && adventurers.stream().noneMatch(a -> a != adv && a.getPosition().equals(nextPos));
                            if (valid) {
                                adv.setPosition(nextPos);
                                if (map.hasTreasure(nextPos)) {
                                    Treasure t = map.getTreasure(nextPos);
                                    if (t.collect()) {
                                        adv.collectTreasure();
                                    }
                                }
                            }
                            break;
                        case 'G':
                            adv.turnLeft();
                            break;
                        case 'D':
                            adv.turnRight();
                            break;
                        default:
                            // Ignore unknown moves
                    }
                }
            }
        }

        OutputFileRepository outputRepo = new OutputFileRepository();
        outputRepo.write(outputPath, map, adventurers);

        System.out.println("Simulation complete. Output written to " + outputPath);
    }
}