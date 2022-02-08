package com.christianwilkie.bowling;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static com.christianwilkie.bowling.BowlingGameApp.EXIT_COMMAND;

class BowlingGameAppTest {
    private final InputStream sysIn = System.in;
    private final PrintStream sysOut = System.out;

    @BeforeEach
    public void beforeEachTest() {
        ByteArrayOutputStream testSysOutData = new ByteArrayOutputStream();
        PrintStream testSysOut = new PrintStream(testSysOutData);
        System.setOut(testSysOut);
    }

    @AfterEach
    public void afterEachTest() {
        System.setIn(sysIn);
        System.setOut(sysOut);
    }

    @Test
    void testGameLoopTerminatesWithExit() {
        String rollInput = simulateRollInput(6,2,7,2,3,4,8,2,9,0,10,10,10,6,3,8,2,7);
        ByteArrayInputStream testSysIn = new ByteArrayInputStream(rollInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(testSysIn);
        BowlingGameApp.main(new String[]{});
    }

    @Test
    void testGameLoopWithInvalidRoll() {
        String rollInput = simulateRollInput(-1);
        ByteArrayInputStream testSysIn = new ByteArrayInputStream(rollInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(testSysIn);
        BowlingGameApp.main(new String[]{});
    }

    private String simulateRollInput(int... rolls) {
        StringBuilder sb = new StringBuilder();
        for (int roll: rolls) {
            sb.append(roll);
            sb.append(System.lineSeparator());
        }
        sb.append(EXIT_COMMAND);
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}