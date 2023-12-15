package day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Day15 {
    final static ArrayList<String> input = new ArrayList<>();
    final static String path = "day15/input.txt";
    final static ArrayList<LinkedList<String>> box = new ArrayList<LinkedList<String>>();

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
                String[] inp = inputString.split(",");
                for (String s : inp)
                    input.add(s);
            }
            myReader.close();

            for (int i = 0; i < 256; i++) {
                box.add(new LinkedList<String>());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error in reading input");
        }
    }

    public static void problem1() {
        System.out.println(input.stream().mapToInt(s -> getHash(s)).sum());
    }

    public static void problem2() {
        for (String s : input) {

            if (s.indexOf('-') != -1) {
                String key = s.substring(0, s.length() - 1);
                int boxIndex = getHash(key);
                LinkedList<String> temp = box.get(boxIndex);
                temp.removeIf((String current) -> current.split("=")[0].equals(key));
            } else {
                String[] hash = s.split("=");
                String key = hash[0];
                int boxIndex = getHash(key);
                LinkedList<String> temp = box.get(boxIndex);
                boolean found = false;
                for (int i = 0; i < temp.size(); i++) {
                    String currentKey = temp.get(i).split("=")[0];
                    if (currentKey.equals(key)) {
                        box.get(boxIndex).remove(i);
                        box.get(boxIndex).add(i, s);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    box.get(boxIndex).addLast(s);

                }

            }

        }
        int ans = 0;
        for (int j = 0; j < box.size(); j++) {
            for (int i = 0; i < box.get(j).size(); i++) {
                ans += (i + 1) * (j + 1)
                        * Character.getNumericValue(box.get(j).get(i).charAt(box.get(j).get(i).length() - 1));
            }

        }
        System.out.println(ans);

    }

    public static int getHash(String s) {
        int local = 0;
        for (char c : s.toCharArray()) {
            local += (int) c;
            local *= 17;
            local = local % 256;
        }
        return local;
    }
}
