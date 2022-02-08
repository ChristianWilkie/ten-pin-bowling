package com.christianwilkie.bowling.game;

import static com.christianwilkie.bowling.scoring.ScoreCalculator.CANNOT_BE_SCORED_YET;

/**
 * Prints the current game state to the console (System.out)
 */
public class GameStatusPrinter {

    public void printGameStatus(GameState gameState) {
        System.out.println("Current Rolls: " + framesOrRollsToString(gameState.getRolls()));
        System.out.println("Current (Rolling) Frame Scores: " + framesOrRollsToString(gameState.getFrameScores()));
    }

    private String framesOrRollsToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == CANNOT_BE_SCORED_YET) break;
            sb.append(arr[i]);
            if (i != arr.length-1 && arr[i+1] != CANNOT_BE_SCORED_YET) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
