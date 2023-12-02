package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Day1 {
    final static ArrayList<String> input = new ArrayList<>();
    final static String path = "day1/input.txt";
    static HashMap<String, String> stringToInt = new HashMap<String, String>();
    static int problem = 1;

    public static void main(String[] args) {
        // "twoone" != tw1 == 21
        stringToInt.put("one", "o1e");
        stringToInt.put("two", "t2o");
        stringToInt.put("three", "t3");
        stringToInt.put("four", "4");
        stringToInt.put("five", "5e");
        stringToInt.put("six", "6");
        stringToInt.put("seven", "7");
        stringToInt.put("eight", "8t");
        stringToInt.put("nine", "9");

        readInput();
        problem1(input);
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

    public static void problem1(ArrayList<String> input1) {
        int totalSum = 0;

        for (String line : input1) {
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
        System.out.println("Problem " + problem);
        System.out.println("Total sum: " + totalSum);
        System.out.println("______________________");
    }

    public static void problem2() {
        ArrayList<String> temp = new ArrayList<>();
        for (String line : input) {

            for (Map.Entry<String, String> entry : stringToInt.entrySet()) {
                String current = entry.getKey();
                String newCurrent = entry.getValue();
                line = line.replaceAll(current, newCurrent);
            }
            temp.add(line);
        }
        problem++;
        problem1(temp);
    }
}
