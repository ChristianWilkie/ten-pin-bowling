package com.christianwilkie.bowling.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.christianwilkie.bowling.scoring.ScoreCalculator.CANNOT_BE_SCORED_YET;
import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {
    private GameState gameState;

    @BeforeEach
    public void beforeEachTest() {
        this.gameState = new GameState();
    }

    @Test
    void addRollEmptyState() {
        gameState.addRoll(3);

        assertEquals(1, gameState.getCurrentRoll());
        assertEquals(0, gameState.getCurrentFrame());
        assertEquals(CANNOT_BE_SCORED_YET, gameState.getFrameScores()[0]);
    }

    @Test
    void addInvalidRoll() {
        assertThrows(InvalidRollException.class, () -> gameState.addRoll(-1));
    }

    @Test
    void addStrike() {
        gameState.addRoll(10);

        assertEquals(1, gameState.getCurrentRoll());
        assertEquals(0, gameState.getCurrentFrame());
        assertEquals(CANNOT_BE_SCORED_YET, gameState.getFrameScores()[0]);
    }

    @Test
    void addMultipleStrikes() {
        gameState.addRoll(10);
        gameState.addRoll(10);
        gameState.addRoll(10);

        assertEquals(3, gameState.getCurrentRoll());
        assertEquals(1, gameState.getCurrentFrame());
        assertEquals(30, gameState.getFrameScores()[0]);
        assertEquals(CANNOT_BE_SCORED_YET, gameState.getFrameScores()[1]);
    }
}