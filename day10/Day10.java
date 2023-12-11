package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Day10 {

    final static ArrayList<String> input = new ArrayList<>();
    final static String path = "day10/input.txt";
    final static int[][] distance = new int[140][140];
    final static int[] start = new int[2];

    public static void main(String[] args) {
        readInput();
        problem1(start);
        for (int[] row : distance) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public static void readInput() {
        File file = new File(path);
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String inputString = myReader.nextLine();
                input.add(inputString);
                if (inputString.contains("S")) {
                    start[0] = input.size() - 1;
                    start[1] = inputString.indexOf('S');
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error in reading input");
        }
    }

    static void problem1(int[] move) {
        final Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { move[0], move[1] });
        distance[move[0]][move[1]] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; 

            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (isValidMove(row, col, newRow, newCol)) {
                    int[] newCurrent = { newRow, newCol };
                    distance[newRow][newCol] = distance[row][col] + 1;
                    queue.add(newCurrent);
                }
            }
        }

        int max = findMaxValue(distance);
        System.out.println("Problem 1: " + max);
    }

    static boolean isValidMove(int row, int col, int newRow, int newCol) {
        
        final boolean goingUp = newRow >= 0 && row > newRow && "|JLS".indexOf(input.get(row).charAt(col)) != -1
                && "|F7".indexOf(input.get(newRow).charAt(newCol)) != -1;
        
        final boolean goingDown = newRow < input.size() && row < newRow
                && "|7FS".indexOf(input.get(row).charAt(col)) != -1
                && "|JL".indexOf(input.get(newRow).charAt(newCol)) != -1;
        
        final boolean goingRight = newCol < input.get(row).length() && col < newCol
                && "F-LS".indexOf(input.get(row).charAt(col)) != -1
                && "J7-".indexOf(input.get(newRow).charAt(newCol)) != -1;
        
        final boolean goingLeft = newCol >= 0 && col > newCol && "7J-S".indexOf(input.get(row).charAt(col)) != -1
                && "F-L".indexOf(input.get(newRow).charAt(newCol)) != -1;

        if ((goingUp || goingLeft || goingDown || goingRight) && distance[newRow][newCol] == 0) {
            return true;
        }

        return false;
    }

    static int findMaxValue(int[][] array) {
        int max = Integer.MIN_VALUE;

        for (int[] row : array) {
            for (int value : row) {
                if (value > max) {
                    max = value;
                }
            }
        }

        return max;
    }
}
