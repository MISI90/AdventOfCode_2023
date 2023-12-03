package com.misi;

import com.misi.utils.PuzzleSolver;

import java.util.List;

public class Day03 implements PuzzleSolver {


    @Override
    public String solvePuzzle1(List<String> inputs) {
        Character[][] map = parse(inputs);
        int sum = 0;
        Temps temps = new Temps();
        for (int i = 0; i < map.length; i++) {
            Character[] row = map[i];
            for (int j = 0; j < row.length; j++) {
                Character col = row[j];
                if (Character.isDigit(col)) {
                    temps.addTemp(col, isValid(map, i, j));
                } else {
                    if (temps.isValidPosition) {
                        sum += Integer.parseInt(temps.tempNumber);
                    }
                    temps.reset();
                }
            }
        }
        return String.valueOf(sum);
    }

    @Override
    public String solvePuzzle2(List<String> inputs) {
        return "";
    }

    private boolean isValid(Character[][] map, int i, int j) {
        return get(map, i - 1, j - 1) || get(map, i - 1, j) || get(map, i - 1, j + 1) || get(map, i, j - 1) || get(map, i, j + 1) || get(map, i + 1, j - 1) || get(map, i + 1, j) || get(map, i + 1, j + 1);
    }

    private boolean get(Character[][] map, int i, int j) {
        try {
            var ch = map[i][j];
            return !Character.isDigit(ch) && !ch.equals('.');
        } catch (Exception e) {
            return false;
        }
    }

    private Character[][] parse(List<String> inputs) {
        Character[][] map = new Character[inputs.size()][inputs.size()];
        for (int i = 0; i < inputs.size(); i++) {
            String line = inputs.get(i);
            char[] charArray = line.toCharArray();
            for (int j = 0; j < charArray.length; j++) {
                char c = charArray[j];
                map[i][j] = c;
            }
        }
        return map;
    }

    private static class Temps {
        String tempNumber = "";
        Boolean isValidPosition = false;

        public void reset() {
            tempNumber = "";
            isValidPosition = false;
        }

        public void addTemp(char digit, boolean isValid) {
            tempNumber = tempNumber + digit;
            isValidPosition = isValidPosition || isValid;
        }
    }


}
