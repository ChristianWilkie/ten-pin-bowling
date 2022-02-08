package com.christianwilkie.bowling.game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GameStatusPrinterTest {
    private final PrintStream sysOut = System.out;
    private ByteArrayOutputStream testSysOutData;
    private PrintStream testSysOut;
    private GameStatusPrinter gameStatusPrinter;

    @BeforeEach
    public void beforeEachTest() {
        this.testSysOutData = new ByteArrayOutputStream();
        this.testSysOut = new PrintStream(testSysOutData);
        this.gameStatusPrinter = new GameStatusPrinter();
        System.setOut(testSysOut);
    }

    @AfterEach
    public void afterEachTest() {
        System.setOut(sysOut);
        testSysOut.close();
    }

    @Test
    void printEmptyGameState() {
        gameStatusPrinter.printGameStatus(new GameState());
        assertTrue(testSysOutData.toString().contains("Current Rolls"));
    }

    @Test
    void printMidGameState() {
        GameState gameState = new GameState();
        gameState.addRoll(10);
        gameState.addRoll(3);
        gameState.addRoll(7);
        gameState.addRoll(5);
        gameState.addRoll(5);
        gameStatusPrinter.printGameStatus(gameState);
        assertTrue(testSysOutData.toString().contains("20, 35"));
    }
}