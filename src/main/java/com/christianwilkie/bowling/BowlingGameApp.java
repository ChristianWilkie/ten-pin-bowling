package com.christianwilkie.bowling;

import com.christianwilkie.bowling.game.GameState;
import com.christianwilkie.bowling.game.GameStatusPrinter;
import com.christianwilkie.bowling.game.InvalidRollException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Scanner;

public class BowlingGameApp {
    public static final String EXIT_COMMAND = "exit";
    private static final Logger log = LoggerFactory.getLogger(BowlingGameApp.class);
    private final GameStatusPrinter gameStatusPrinter;
    private GameState gameState;

    public BowlingGameApp() {
        this.gameStatusPrinter = new GameStatusPrinter();
        this.gameState = new GameState();
    }

    public static void main(String[] args) {
        log.debug("Starting Bowling Game application with args {}", Arrays.toString(args));
        BowlingGameApp bowlingGameApp = new BowlingGameApp();
        System.out.println("Welcome to Ten-Pin Bowling! Type \"exit\" at any point to exit the game.");
        bowlingGameApp.gameLoop();
        System.out.println("Thank you for playing Ten-Pin Bowling.");
    }

    public void gameLoop() {
        try (Scanner scanner = new Scanner(System.in)) {
            while(true) {
                System.out.print("Enter your roll: ");
                try {
                    String line = scanner.nextLine();
                    if (line.equals(EXIT_COMMAND)) break;
                    int roll = Integer.parseInt(line);
                    gameState.addRoll(roll);
                    gameStatusPrinter.printGameStatus(gameState);
                    if (gameState.getCurrentFrame() == 10) {
                        System.out.println("Game complete! Starting new match..");
                        gameState = new GameState();
                    }
                } catch (NumberFormatException|InvalidRollException ex) {
                    System.out.println("Invalid roll - please enter a number between 0 and 10 for your roll");
                }
            }
        }
    }
}
