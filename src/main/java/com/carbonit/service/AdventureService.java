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
 * Main service to run the treasure hunt simulation.
 */
public class AdventureService {

    public void run(String inputFile, String outputFile) {
        try {
            InputFileRepository inputRepo = new InputFileRepository();
            InputFileRepository.MapData data = inputRepo.read(inputFile);
            Map map = data.map;
            List<Adventurer> adventurers = data.adventurers;

            // Simulate movements turn by turn
            boolean movesLeft;
            do {
                movesLeft = false;
                for (Adventurer adv : adventurers) {
                    if (!adv.getMoves().isEmpty()) {
                        char move = adv.getMoves().poll();
                        switch (move) {
                            case 'A':
                                Position next = getNextPosition(adv.getPosition(), adv.getOrientation());
                                if (isValidMove(map, next, adventurers)) {
                                    adv.setPosition(next);
                                    if (map.hasTreasure(next)) {
                                        Treasure t = map.getTreasure(next);
                                        if (t.collect()) adv.collectTreasure();
                                    }
                                }
                                break;
                            case 'G':
                                adv.turnLeft();
                                break;
                            case 'D':
                                adv.turnRight();
                                break;
                        }
                        movesLeft = true;
                    }
                }
            } while (movesLeft);

            OutputFileRepository outputRepo = new OutputFileRepository();
            outputRepo.write(outputFile, map, adventurers);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private Position getNextPosition(Position pos, Orientation orientation) {
        switch (orientation) {
            case N: return new Position(pos.getX(), pos.getY() - 1);
            case S: return new Position(pos.getX(), pos.getY() + 1);
            case E: return new Position(pos.getX() + 1, pos.getY());
            case W: return new Position(pos.getX() - 1, pos.getY());
            default: return pos;
        }
    }

    public boolean isValidMove(Map map, Position pos, java.util.List<Adventurer> adventurers) {
        if (pos.getX() < 0 || pos.getY() < 0 || pos.getX() >= map.getWidth() || pos.getY() >= map.getHeight())
            return false;
        if (map.isMountain(pos)) return false;
        for (Adventurer adv : adventurers) {
            if (adv.getPosition().equals(pos)) return false;
        }
        return true;
    }
}