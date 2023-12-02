package com.misi.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * Base class for running test for solvers.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class PuzzleSolverTest {

    /**
     * Prefix used na solver class name.
     */
    private static final String SOLVER_CLASS_PREFIX = "Day";
    private PuzzleSolver solver;

    /**
     * Get the day of the puzzle, used to load correct files.
     *
     * @return day as 2-digit string ex: 01, 07, 13,20
     */
    protected abstract String getDay();

    /**
     * Get the expected result for example puzzle input from the description.
     *
     * @return expected result
     */
    protected abstract String getExpectedCorrectResult();

    /**
     * Get the expected result for first part of the puzzle.
     *
     * @return expected result
     */
    protected abstract String getExpectedPuzzle1Result();

    /**
     * Get the expected result for second part of the puzzle.
     *
     * @return expected result
     */
    protected abstract String getExpectedPuzzle2Result();

    @SuppressWarnings({"rawtypes", "unchecked"})
    @BeforeAll
    public void loadSolverInstance() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clazz = Class.forName(this.getClass().getPackageName() + "." + SOLVER_CLASS_PREFIX + getDay());
        solver = (PuzzleSolver) clazz.getDeclaredConstructor().newInstance();
    }

    @Test
    public void testCorrectInput() throws IOException, URISyntaxException {
        var result = solver.solvePuzzle1(getLines(String.format("day%s_test_input.txt", getDay())));
        Assertions.assertEquals(getExpectedCorrectResult(), result);
    }

    @Test
    public void testPuzzle1Input() throws IOException, URISyntaxException {
        var result = solver.solvePuzzle1(getLines(String.format("day%s_puzzle_input.txt", getDay())));
        Assertions.assertEquals(getExpectedPuzzle1Result(), result);
    }

    @Test
    public void testPuzzle2Input() throws IOException, URISyntaxException {
        var result = solver.solvePuzzle2(getLines(String.format("day%s_puzzle_input.txt", getDay())));
        Assertions.assertEquals(getExpectedPuzzle2Result(), result);
    }

    private List<String> getLines(String fileName) throws URISyntaxException, IOException {
        return Files.readAllLines(Paths.get(Objects.requireNonNull(PuzzleSolverTest.class.getClassLoader().getResource(fileName)).toURI()));
    }
}
