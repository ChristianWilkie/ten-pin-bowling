package com.christianwilkie.bowling.game;

/**
 * Thrown if the user tries to add an invalid roll
 */
public class InvalidRollException extends RuntimeException{
    public InvalidRollException(String message) {
        super(message);
    }
}
