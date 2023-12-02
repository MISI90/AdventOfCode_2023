package com.misi;

import com.misi.utils.PuzzleSolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day01 implements PuzzleSolver {

    Map<String, Integer> words;

    {
        words = new HashMap<>();
        words.put("one", 1);
        words.put("two", 2);
        words.put("three", 3);
        words.put("four", 4);
        words.put("five", 5);
        words.put("six", 6);
        words.put("seven", 7);
        words.put("eight", 8);
        words.put("nine", 9);
    }

    Map<String, Integer> digits;

    {
        digits = new HashMap<>();
        digits.put("1", 1);
        digits.put("2", 2);
        digits.put("3", 3);
        digits.put("4", 4);
        digits.put("5", 5);
        digits.put("6", 6);
        digits.put("7", 7);
        digits.put("8", 8);
        digits.put("9", 9);
    }

    @Override
    public String solvePuzzle1(List<String> inputs) {
        return solve(inputs, digits);
    }

    @Override
    public String solvePuzzle2(List<String> inputs) {
        return solve(inputs, combine(words, digits));
    }

    private Map<String, Integer> combine(Map<String, Integer> words, Map<String, Integer> digits) {
        Map<String, Integer> map = new HashMap<>();
        map.putAll(words);
        map.putAll(digits);
        return map;
    }

    private static String solve(List<String> inputs, Map<String, Integer> words) {
        return String.valueOf(inputs.stream().mapToInt(value -> {
            ValueHolder valueHolder = new ValueHolder();
            words.forEach((k, v) -> {
                var i = value.indexOf(k);
                if (i >= 0 && (valueHolder.firstIndex == null || i < valueHolder.firstIndex)) {
                    valueHolder.firstIndex = i;
                    valueHolder.firstValue = v;
                }

                var j = value.lastIndexOf(k);
                if (j >= 0 && (valueHolder.secondIndex == null || j > valueHolder.secondIndex)) {
                    valueHolder.secondIndex = j;
                    valueHolder.secondValue = v;
                }
            });

            return valueHolder.firstValue * 10 + valueHolder.secondValue;
        }).sum());
    }

    private static class ValueHolder {
        Integer firstIndex = null;
        Integer firstValue = 0;
        Integer secondIndex = null;
        Integer secondValue = 0;
    }
}
