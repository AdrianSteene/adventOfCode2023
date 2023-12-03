package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day3 {
    final static ArrayList<String> input = new ArrayList<>();
    final static String path = "day3/input.txt";

    public static void main(String[] args) {
        readInput();
        problem1();
        problem2();
    }

    public static void readInput() {
        File file = new File(path);
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String inputString = myReader.nextLine();
                input.add(inputString);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error in reading input");
        }
    }

    public static void problem1() {
        int sum = 0;
        for (int lineIndex = 0; lineIndex != input.size(); lineIndex++) {
            String line = input.get(lineIndex);
            for (int charIndex = 0; charIndex != line.length(); charIndex++) {

                char currentChar = line.charAt(charIndex);

                if (Character.isDigit(currentChar)) {
                    if (hasSymbol(lineIndex, charIndex)) {
                        StringBuilder local = new StringBuilder();
                        int firstIndex = 0;
                        int lastIndex = 0;
                        for (int index = charIndex - 1; index != -1; index--) {
                            if (Character.isDigit(line.charAt(index))) {
                                firstIndex = index;
                            } else {
                                firstIndex = index + 1;
                                break;
                            }
                        }
                        for (int index = charIndex + 1; index != line.length(); index++) {
                            if (Character.isDigit(line.charAt(index))) {
                                lastIndex = index;
                            } else {
                                lastIndex = index - 1;
                                break;
                            }
                        }
                        for (int index = firstIndex; index <= lastIndex; index++) {
                            local.append(line.charAt(index));
                        }
                        charIndex = lastIndex;
                        sum += Integer.parseInt(local.toString());

                    }
                }
            }
        }
        System.out.println("Sum of all part numbers: " + sum);
    }

    private static boolean hasSymbol(int lineIndex, int charIndex) {
        String symbols = ".1234567890";
        int[][] pos = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

        for (int[] p : pos) {
            int newLineIndex = lineIndex + p[0];
            int newCharIndex = charIndex + p[1];

            if (newLineIndex >= 0 && newLineIndex < input.size() &&
                    newCharIndex >= 0 && newCharIndex < input.get(newLineIndex).length()) {
                if (symbols.indexOf(input.get(newLineIndex).charAt(newCharIndex)) == -1) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void problem2() {
        int product = 0;
        for (int lineIndex = 0; lineIndex != input.size(); lineIndex++) {
            String line = input.get(lineIndex);
            for (int charIndex = 0; charIndex != line.length(); charIndex++) {
                char currentChar = line.charAt(charIndex);
                if (currentChar == '*') {
                    Set<Integer> result = hasNumber(lineIndex, charIndex);
                    if (result.size() > 1) {
                        product += result.stream().reduce(1, (a, b) -> a * b);
                    }
                }
            }
        }
        System.out.println("Product of all: " + product);
    }

    private static Set<Integer> hasNumber(int lineIndex, int charIndex) {
        Set<Integer> adjacentNumbers = new HashSet<>();
        String symbols = "1234567890";
        int[][] pos = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

        for (int[] p : pos) {
            int newRow = lineIndex + p[0];
            int newCol = charIndex + p[1];

            if (newRow >= 0 && newRow < input.size() && newCol >= 0 && newCol < input.get(newRow).length()) {
                char symbol = input.get(newRow).charAt(newCol);
                if (symbols.indexOf(symbol) != -1) {
                    adjacentNumbers.add(getNumber(newCol, input.get(newRow)));
                }
            }
        }

        return adjacentNumbers;

    }

    static int getNumber(int index, String list) {
        int firstIndex = index;
        int lastIndex = index;
        while (firstIndex > 0 && Character.isDigit(list.charAt(firstIndex - 1))) {
            firstIndex--;
        }
        while (lastIndex < list.length() - 1 && Character.isDigit(list.charAt(lastIndex + 1))) {
            lastIndex++;
        }

        StringBuilder number = new StringBuilder();
        for (int k = firstIndex; k <= lastIndex; k++) {
            number.append(list.charAt(k));
        }
        return Integer.valueOf(number.toString());
    }

}
