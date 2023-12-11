package day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Day11 {
    final static ArrayList<String> input = new ArrayList<>();
    final static String path = "day11/input.txt";
    final static ArrayList<int[]> positions = new ArrayList<int[]>();

    public static void main(String[] args) {
        readInput();
        problem1();
    }

    public static void readInput() {
        HashSet<Integer> containsRow = new HashSet<Integer>();

        File file = new File(path);
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String inputString = myReader.nextLine();
                if (inputString.indexOf('#') == -1) {
                    input.add(inputString);
                    containsRow.add(null);
                } else {
                    input.add(inputString);
                }
            }

            HashSet<Integer> containsCol = new HashSet<Integer>();

            for (int indexRow = 0; indexRow < input.size(); indexRow++) {
                if (input.get(indexRow).indexOf('#') == -1) {
                    containsRow.add(indexRow);
                }
            }

            for (String line : input) {
                System.out.println(line.length());
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '#') {
                        containsCol.add(i);
                    }
                }
            }
            HashSet<Integer> colContains = new HashSet<>();

            for (int j = 0; j < 141; j++) {
                if (!containsCol.contains(j)) {
                    colContains.add(j);
                }
            }

            for (int indexRow = 0; indexRow < input.size(); indexRow++) {
                for (int indexCol = 0; indexCol < input.get(indexRow).length(); indexCol++) {
                    if (input.get(indexRow).charAt(indexCol) == '#') {
                        int addRow = 0;
                        Iterator<Integer> it = containsRow.iterator();
                        it.next();
                        while (it.hasNext()) {
                            if (indexRow >= it.next()) {
                                addRow += 999999;
                            }
                        }
                        int addCol = 0;
                        Iterator<Integer> i = colContains.iterator();

                        while (i.hasNext()) {
                            if (indexCol >= i.next()) {
                                addCol += 999999;
                            }
                        }

                        int[] position = { indexRow + addRow, indexCol + addCol };
                        positions.add(position);
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error in reading input");
        }
    }

    public static String addCharToString(String str, char c, int pos) {

        StringBuffer stringBuffer = new StringBuffer(str);

        stringBuffer.insert(pos, c);

        return stringBuffer.toString();
    }

    public static void problem1() {
        Long answer = (long) 0;
        System.out.println("Positions: " + positions.size());
        for (int[] position : positions) {
            for (int[] positionLook : positions) {

                answer += (Math.abs(position[0] - positionLook[0]) + Math.abs(position[1] - positionLook[1]));
            }
        }
        System.out.println(answer / 2);
    }
}
