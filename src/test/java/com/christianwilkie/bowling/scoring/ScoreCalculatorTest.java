package com.christianwilkie.bowling.scoring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.christianwilkie.bowling.game.GameState.MAX_NUM_FRAMES;
import static com.christianwilkie.bowling.game.GameState.MAX_NUM_ROLLS;
import static com.christianwilkie.bowling.scoring.ScoreCalculator.CANNOT_BE_SCORED_YET;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ScoreCalculatorTest {
    private ScoreCalculator scoreCalculator;

    @BeforeEach
    public void beforeEachTest() {
        scoreCalculator = new ScoreCalculator();
    }

    @Test
    void scoreBeginningGame() {
        int[] rolls = createRolls();
        int[] expected = createExpectedFrameValues();
        int[] res = scoreCalculator.calculateScoreForFrames(rolls);
        assertArrayEquals(expected, res);
    }

    @Test
    void scoreGutterBall() {
        int[] rolls = createRolls(0);
        int[] expected = createExpectedFrameValues();
        int[] res = scoreCalculator.calculateScoreForFrames(rolls);
        assertArrayEquals(expected, res);
    }

    @Test
    void scoreDoubleGutterBall() {
        int[] rolls = createRolls(0, 0);
        int[] expected = createExpectedFrameValues(0);
        int[] res = scoreCalculator.calculateScoreForFrames(rolls);
        assertArrayEquals(expected, res);
    }

    @Test
    void scoreMidGameFrames() {
        int[] rolls = createRolls(6,2,7,2,3,4,8,2,9,0,10,10,10);
        int[] expected = createExpectedFrameValues(8, 17, 24, 43, 52, 82);
        int[] res = scoreCalculator.calculateScoreForFrames(rolls);
        assertArrayEquals(expected, res);
    }

    @Test
    void scoreFinishedTenRoundGameEndingWithSpareAndRoll() {
        int[] rolls = createRolls(6,2,7,2,3,4,8,2,9,0,10,10,10,6,3,8,2,7);
        int[] expected = createExpectedFrameValues(8, 17, 24, 43, 52, 82, 108, 127, 136, 153);
        int[] actual = scoreCalculator.calculateScoreForFrames(rolls);
        assertArrayEquals(expected, actual);
    }

    @Test
    void scoreFinishedTenRoundGameEndingWithSpareAndStrike() {
        int[] rolls = createRolls(6,2,7,2,3,4,8,2,9,0,10,10,10,6,3,8,2,10);
        int[] expected = createExpectedFrameValues(8, 17, 24, 43, 52, 82, 108, 127, 136, 156);
        int[] actual = scoreCalculator.calculateScoreForFrames(rolls);
        assertArrayEquals(expected, actual);
    }

    @Test
    void scoreEndingWithAStrikeAndTwoRolls() {
        int[] rolls = createRolls(6,2,7,2,3,4,8,2,9,0,10,10,10,6,3,10,2,2);
        int[] expected = createExpectedFrameValues(8, 17, 24, 43, 52, 82, 108, 127, 136, 150);
        int[] actual = scoreCalculator.calculateScoreForFrames(rolls);
        assertArrayEquals(expected, actual);
    }

    @Test
    void scorePerfectGame() {
        int[] rolls = createRolls(10,10,10,10,10,10,10,10,10,10,10,10);
        int[] expected = createExpectedFrameValues(30,60,90,120,150,180,210,240,270,300);
        int[] actual = scoreCalculator.calculateScoreForFrames(rolls);
        assertArrayEquals(expected, actual);
    }

    private int[] createRolls(int... rolls) {
        int[] res = new int[MAX_NUM_ROLLS];
        Arrays.fill(res, CANNOT_BE_SCORED_YET);
        System.arraycopy(rolls, 0, res, 0, rolls.length);
        return res;
    }

    private int[] createExpectedFrameValues(int... frameValues) {
        int[] res = new int[MAX_NUM_FRAMES];
        Arrays.fill(res, CANNOT_BE_SCORED_YET);
        System.arraycopy(frameValues, 0, res, 0, frameValues.length);
        return res;
    }
}