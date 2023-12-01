package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Scanner;

public class Day1 {
    final static ArrayList<String> input = new ArrayList<>();
    final static String path = "day1/input.txt";
    static HashMap<String, Integer> stringToInt = new HashMap<String, Integer>();

    public static void main(String[] args) {
        stringToInt.put("one", 1);
        stringToInt.put("two", 2);
        stringToInt.put("three", 3);
        stringToInt.put("four", 4);
        stringToInt.put("five", 5);
        stringToInt.put("six", 6);
        stringToInt.put("seven", 7);
        stringToInt.put("eight", 8);
        stringToInt.put("nine", 9);

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
        int totalSum = 0;

        for (String line : input) {
            int firstDigit = 0;
            int lastDigit = 0;
            boolean hasChanged = false;

            for (int i = 0; i != line.length(); i++) {
                char currentChar = line.charAt(i);
                if (Character.isDigit(currentChar)) {
                    if (!hasChanged) {
                        firstDigit = Character.getNumericValue(currentChar);
                        hasChanged = true;
                    }
                    lastDigit = Character.getNumericValue(currentChar);
                }
            }
            totalSum += 10 * firstDigit + lastDigit;
        }
        System.out.println("Problem 1");
        System.out.println("Total sum: " + totalSum);
        System.out.println("______________________");
    }

    public static void problem2() {
        int totalSum = 0;

        for (String line : input) {
            int firstDigit = 0;
            int lastDigit = 0;
            boolean hasChanged = false;

            for (int i = line.length() - 1; i > -1; i--) {
                char currentChar = line.charAt(i);
                if (!hasChanged) {
                    if (Character.isDigit(currentChar)) {
                        lastDigit = Character.getNumericValue(currentChar);
                        hasChanged = true;
                    } else {
                        for (int j = line.length(); j >= i; j--) {
                            String isNumber = line.substring(i, j);
                            if (stringToInt.containsKey(isNumber)) {
                                lastDigit = stringToInt.get(isNumber);
                                hasChanged = true;
                                break;
                            }

                        }
                    }
                }
            }
            hasChanged = false;
            for (int i = 0; i != line.length(); i++) {
                char currentChar = line.charAt(i);
                if (!hasChanged) {
                    if (Character.isDigit(currentChar)) {
                        firstDigit = Character.getNumericValue(currentChar);
                        hasChanged = true;
                    } else {
                        for (int j = 0; j <= i; j++) {
                            String isNumber = line.substring(j, i + 1);
                            if (stringToInt.containsKey(isNumber)) {
                                firstDigit = stringToInt.get(isNumber);
                                hasChanged = true;
                                break;
                            }
                        }
                    }
                }
            }

            firstDigit *= 10;
            int result = firstDigit + lastDigit;
            totalSum += result;

        }
        System.out.println("Problem 2");
        System.out.println("Total sum: " + totalSum);
    }

}