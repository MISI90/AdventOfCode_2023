package com.misi.utils;

import java.util.List;

/**
 * Base class for puzzle solving classes for easier testing.
 */
public interface PuzzleSolver {

    /**
     * Solve the first puzzle.
     *
     * @param inputs lines for puzzle input
     * @return result
     */
    String solvePuzzle1(List<String> inputs);

    /**
     * Solve the second puzzle.
     *
     * @param inputs lines for puzzle input
     * @return result
     */
    String solvePuzzle2(List<String> inputs);
}
