package com.christianwilkie.bowling.scoring;

import java.util.Arrays;

/**
 * Scores frames / rounds in a ten-pin bowling game.
 *
 * Uses traditional scoring
 * @see <a href="https://en.wikipedia.org/wiki/Ten-pin_bowling#Traditional_scoring">Wikipedia - Ten-pin Bowling Scoring</a>
 */
public class ScoreCalculator {
    /**
     * Frame is scored as "cannot be scored yet" if it cannot be calculated yet - this affects spares and strikes as they may rely on future rolls which haven't happened yet.
     */
    public static final int CANNOT_BE_SCORED_YET = -1;

    private static final int STRIKE_VALUE = 10;
    private static final int SPARE_VALUE = 10;
    private static final int NUM_FRAMES = 10;

    public int[] calculateScoreForFrames(int[] rolls) {
        int[] frameScores = new int[NUM_FRAMES];
        Arrays.fill(frameScores, CANNOT_BE_SCORED_YET);
        int frameIndex = 0;
        int prevFrameValue = 0;
        for (int rollIndex = 0; rollIndex < rolls.length; rollIndex++) {
            if (rolls[rollIndex] == CANNOT_BE_SCORED_YET) {
                break;
            }
            if (frameIndex == NUM_FRAMES) {
                break;
            }
            if (isStrike(rolls, rollIndex)) {
                if (rolls[rollIndex+2] == CANNOT_BE_SCORED_YET) {
                    frameScores[frameIndex] = CANNOT_BE_SCORED_YET;
                    break;
                }
                frameScores[frameIndex] = prevFrameValue + STRIKE_VALUE + rolls[rollIndex+1]+ rolls[rollIndex+2];
            } else if (isSpare(rolls, rollIndex)) {
                if (rolls[rollIndex+2] == CANNOT_BE_SCORED_YET) {
                    frameScores[frameIndex] = CANNOT_BE_SCORED_YET;
                    break;
                }
                frameScores[frameIndex] = prevFrameValue + SPARE_VALUE + rolls[rollIndex+2];
                rollIndex++;
            } else {
                if (rolls[rollIndex+1] == CANNOT_BE_SCORED_YET) {
                    frameScores[frameIndex] = CANNOT_BE_SCORED_YET;
                    break;
                }
                frameScores[frameIndex] = prevFrameValue + rolls[rollIndex] + rolls[rollIndex+1];
                rollIndex++;
            }
            prevFrameValue = frameScores[frameIndex];
            frameIndex++;
        }
        return frameScores;
    }

    private boolean isStrike(int[] rolls, int currentRoll) {
        return rolls[currentRoll] == STRIKE_VALUE;
    }

    private boolean isSpare(int[] rolls, int currentRoll) {
        if (currentRoll == rolls.length - 1) return false;
        if (rolls[currentRoll + 1] == -1) return false;
        if (rolls[currentRoll] == -1) return false;
        return (rolls[currentRoll] + rolls[currentRoll+1]) == SPARE_VALUE;
    }
}
