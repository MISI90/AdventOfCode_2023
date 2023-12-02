package com.misi;

import com.misi.utils.PuzzleSolver;

import java.util.List;
import java.util.regex.Pattern;

public class Day02 implements PuzzleSolver {

    @Override
    public String solvePuzzle1(List<String> inputs) {
        int gameNumbersSum = 0;
        CubesCollection bag = new CubesCollection(12, 13, 14);
        for (String input : inputs) {
            var segments = input.split("[:;]");
            boolean add = true;
            for (int i = 1; i < segments.length; i++) {
                String segment = segments[i];
                CubesCollection hand = new CubesCollection(segment);
                if (!canHandBeInBag(hand, bag)) {
                    add = false;
                    break;
                }
            }
            if (add) {
                gameNumbersSum += Integer.parseInt(segments[0].split(" ")[1]);
            }
        }
        return String.valueOf(gameNumbersSum);
    }

    @Override
    public String solvePuzzle2(List<String> inputs) {
        int sum = 0;
        for (String input : inputs) {
            int red = 0, green = 0, blue = 0;
            var redPattern = Pattern.compile("([0-9]+) (red)").matcher(input);
            var greenPattern = Pattern.compile("([0-9]+) (green)").matcher(input);
            var bluePattern = Pattern.compile("([0-9]+) (blue)").matcher(input);
            while (redPattern.find()) {
                var group = Integer.parseInt(redPattern.group(1));
                if (group > red) {
                    red = group;
                }
            }
            while (greenPattern.find()) {
                var group = Integer.parseInt(greenPattern.group(1));
                if (group > green) {
                    green = group;
                }
            }
            while (bluePattern.find()) {
                var group = Integer.parseInt(bluePattern.group(1));
                if (group > blue) {
                    blue = group;
                }
            }
            sum += red * green * blue;
        }
        return String.valueOf(sum);
    }

    private boolean canHandBeInBag(CubesCollection hand, CubesCollection bag) {
        return hand.red <= bag.red && hand.green <= bag.green && hand.blue <= bag.blue;
    }

    private static class CubesCollection {
        int red = 0;
        int green = 0;
        int blue = 0;

        public CubesCollection(int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }

        public CubesCollection(String line) {
            parseLine(line);
        }

        private void parseLine(String line) {
            for (String cube : line.split(",")) {
                var singleColor = cube.trim().split(" ");
                switch (singleColor[1]) {
                    case "red":
                        red = Integer.parseInt(singleColor[0]);
                        break;
                    case "green":
                        green = Integer.parseInt(singleColor[0]);
                        break;
                    case "blue":
                        blue = Integer.parseInt(singleColor[0]);
                        break;
                }
            }
        }

        @Override
        public String toString() {
            return "CubesCollection{" +
                    "red=" + red +
                    ", green=" + green +
                    ", blue=" + blue +
                    '}';
        }
    }
}
