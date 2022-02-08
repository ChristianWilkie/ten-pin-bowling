package com.christianwilkie.bowling.game;

import com.christianwilkie.bowling.scoring.ScoreCalculator;

import java.util.Arrays;

import static com.christianwilkie.bowling.scoring.ScoreCalculator.CANNOT_BE_SCORED_YET;

/**
 * Represents the current state of a bowling game
 */
public class GameState {
    public static final int MAX_NUM_ROLLS = 21;
    public static final int MAX_NUM_FRAMES = 10;
    private static final int MAX_PIN_VALUE = 10;
    private static final int MIN_PIN_VALUE = 0;

    private final int[] rolls;
    private final ScoreCalculator scoreCalculator;
    private int[] frameScores;
    private int currentRoll;
    private int currentFrame;

    public GameState() {
        this.rolls = new int[MAX_NUM_ROLLS];
        Arrays.fill(rolls, CANNOT_BE_SCORED_YET);
        this.scoreCalculator = new ScoreCalculator();
        this.currentRoll = 0;
        this.currentFrame = 0;
        this.frameScores = new int[MAX_NUM_FRAMES];
        Arrays.fill(frameScores, CANNOT_BE_SCORED_YET);
    }

    /**
     * @param numPinsKnockedDown number of pins the player knocked down on their roll
     * @throws InvalidRollException if the player enters an invalid number of pins knocked down during their roll
     */
    public void addRoll(int numPinsKnockedDown) throws InvalidRollException {
        if (numPinsKnockedDown > MAX_PIN_VALUE || numPinsKnockedDown < MIN_PIN_VALUE) throw new InvalidRollException("Roll must be between 0 and 10");
        rolls[currentRoll++] = numPinsKnockedDown;
        frameScores = scoreCalculator.calculateScoreForFrames(rolls);
        if (frameScores[currentFrame] != CANNOT_BE_SCORED_YET) currentFrame++;
    }


    public int[] getRolls() {
        return rolls;
    }

    public int[] getFrameScores() {
        return frameScores;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public int getCurrentRoll() {
        return currentRoll;
    }
}
