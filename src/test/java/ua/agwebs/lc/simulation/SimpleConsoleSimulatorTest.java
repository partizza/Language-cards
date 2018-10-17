package ua.agwebs.lc.simulation;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleConsoleSimulatorTest {

    @Test(expected = IllegalArgumentException.class)
    public void test_NullDelimiter() {
        new SimpleConsoleSimulator(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_EmptyDelimiter() {
        new SimpleConsoleSimulator("");
    }

}