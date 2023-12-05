package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day5 {
    final static ArrayList<String> input = new ArrayList<>();
    final static String path = "day5/input.txt";

    public static void main(String[] args) {
        readInput();
        problem1();
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
        int row = 0;
        ArrayList<String> seeds = new ArrayList<String>();
        ArrayList<String> tempSeeds = new ArrayList<String>();
        ArrayList<String> removeSeeds = new ArrayList<String>();


        for (String s : input) {
            if (row == 0) {
                String[] seedNumbers = s.split(" ");
                for (String number : seedNumbers) {
                    seeds.add(number);
                }
            }
            row++;

            String[] seedNumbers = s.split(" ");
            if (seedNumbers.length == 3) {
                Long end = Long.parseLong(seedNumbers[0]);
                Long start = Long.parseLong(seedNumbers[1]);
                Long Longervall = Long.parseLong(seedNumbers[2]);

                for (String seed : seeds) {
                    if (Long.parseLong(seed) >= start && Long.parseLong(seed) < (start + Longervall)) {
                        removeSeeds.add(seed);
                        tempSeeds.add(String.valueOf((Long.parseLong(seed) - start) + end));
                    }
                }
            } else if (seedNumbers.length < 3) {
                for (String seed : seeds) {
                    if (!removeSeeds.contains(seed))
                        tempSeeds.add(seed);
                        removeSeeds.remove(seed);
                }
                seeds = new ArrayList<String>(tempSeeds);
                tempSeeds = new ArrayList<String>();
                removeSeeds = new ArrayList<String>();
            }

        }
        System.out.println("__________________");
        seeds.forEach((e) -> System.out.println(e));
        System.out.println(String.valueOf(findMin(seeds)));

    }

    public static Long findMin(ArrayList<String> seeds) {
        Long min = Long.parseLong(seeds.get(0));
        for (String seed : seeds) {
            if (Long.parseLong(seed) < min) {
                min = Long.parseLong(seed);
            }
        }
        return min;
    }
}
