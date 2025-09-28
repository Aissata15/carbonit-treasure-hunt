package com.carbonit.exception;

/**
 * Custom exception for errors in the Treasure Hunt adventure.
 */
public class TreasureHuntException extends RuntimeException {
    public TreasureHuntException(String message) {
        super(message);
    }

    public TreasureHuntException(String message, Throwable cause) {
        super(message, cause);
    }
}