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
        System.out.println("Answer 1: " + String.valueOf(findMin(seeds)));

    }

    public static void problem2() {
        String[] line1 = input.get(0).split(" ");
        ArrayList<long[]> seedRanges = new ArrayList<>();
        Long minSeeds = Long.MAX_VALUE;

        for (int size = 0; size < line1.length; size += 2) {
            long[] temp = new long[2];
            temp[0] = Long.parseLong(line1[size]);
            temp[1] = Long.parseLong(line1[size + 1]);
            seedRanges.add(temp);
        }

        for (long[] seedRange : seedRanges) {
            long startRange = seedRange[0];
            long rangeLength = seedRange[1];

            for (long seed = startRange; seed < startRange + rangeLength; seed++) {
                Long currentSeed = findEnd(seed);
                minSeeds = Math.min(minSeeds, currentSeed);
            }
            System.out.println("Progress: " + ((seedRanges.indexOf(seedRange) + 1) / seedRanges.size()) * 100);
        }

        System.out.println("__________________");
        System.out.println("Answer 2: " + minSeeds);
    }

    public static long findEnd(long seed) {
        boolean hasChanged = true;
        for (int i = 1; i < input.size(); i++) {
            String[] line = input.get(i).split(" ");
            if (line.length == 3) {
                long start = Long.parseLong(line[1]);
                long end = Long.parseLong(line[0]);
                long range = Long.parseLong(line[2]);

                if (seed >= start && seed < start + range) {
                    if (hasChanged) {
                        seed = (seed - start) + end;
                        hasChanged = false;
                    }
                }
            }

            else {
                hasChanged = true;
            }

        }
        return seed;
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
