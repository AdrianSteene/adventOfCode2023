package day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Day8 {
    final static ArrayList<String> input = new ArrayList<>();
    final static String path = "day8/input.txt";
    final static HashMap<String, String[]> nodes = new HashMap<String, String[]>();
    final static LinkedList<Integer> moves = new LinkedList<>();
    final static LinkedList<String> startingNodes = new LinkedList<>();

    public static void main(String[] args) {
        readInput();
        problem();
    }

    public static void readInput() {
        File file = new File(path);
        int row = 0;
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                if (row == 0) {
                    String inputString = myReader.nextLine();
                    for (char c : inputString.toCharArray()) {
                        if (c == ' ')
                            continue;
                        else if (c == 'L')
                            moves.add(0);
                        else if (c == 'R')
                            moves.add(1);
                    }
                    row++;
                } else {
                    while (myReader.hasNextLine()) {
                        String inputString = myReader.nextLine();
                        if (!inputString.equals("")) {
                            String[] s = inputString.split("=");
                            String[] str = s[1].split(",");
                            String[] arr = new String[2];
                            arr[0] = (str[0].trim().substring(1));
                            arr[1] = (str[1].trim().substring(0, str[1].length() - 2));
                            input.add(inputString);

                            if (s[0].trim().charAt(2) == 'A')
                                startingNodes.add(s[0].trim());
                            nodes.put(s[0].trim(), arr);
                        }
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error in reading input");
        }
    }

    public static void problem() {
        int count = 0;
        LinkedList<String> currentNodes = startingNodes;
        LinkedList<Integer> ans = new LinkedList<>();

        for (String s : currentNodes) {
            String currentNode = s;
            while (currentNode.trim().charAt(2) != ('Z')) {
                for (int nextMove : moves) {
                    String[] nextNode = nodes.get(currentNode);
                    currentNode = nextNode[nextMove];
                    count++;
                }
            }
            ans.add(count);
            count = 0;

        }
        System.out.println("Part 1: " + ans.getFirst());
        BigInteger answer = findLCMOfLinkedList(ans);
        System.out.println("Part 2: " + answer);
    }

    // https://www.geeksforgeeks.org/program-to-find-lcm-of-two-numbers/
    // Formula for calculating LCM
    private static BigInteger findLCMOfLinkedList(LinkedList<Integer> list) {
        BigInteger lcm = BigInteger.valueOf(list.getFirst());
        for (Integer number : list) {
            lcm = lcm.multiply(BigInteger.valueOf(number)).divide(lcm.gcd(BigInteger.valueOf(number)));
        }
        return lcm;
    }
}
