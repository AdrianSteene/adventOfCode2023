package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day2 {
    final static ArrayList<String> input = new ArrayList<>();
    final static String path = "day2/input.txt";
    static HashMap<String, Integer> maxPerColor = new HashMap<String, Integer>();

    final static int RED = 12;
    final static int GREEN = 13;
    final static int BLUE = 14;

    public static void main(String[] args) {
        maxPerColor.put("red", 12);
        maxPerColor.put("green", 13);
        maxPerColor.put("blue", 14);

        readInput();
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

    public static void problem2() {
        int answer = 0;
        boolean isOK = true;
        for (String line : input) {
            int red = 0;
            int blue = 0;
            int green = 0;

            String[] getid = line.split(":");
            String idString = getid[0].split("\\s+")[1];

            int id = Integer.parseInt(idString.trim());
            System.out.println(id);
            String[] iterations = getid[1].split(";");

            for (String times : iterations) {

                String[] cubes = times.split(",");

                for (String s : cubes) {
                    String[] str = s.trim().split("\\s+");

                    if (str[1].contains("red")) {
                        int temp = Integer.parseInt(str[0]);
                        if (temp > red) {
                            red = temp;
                        }
                    }
                    if (str[1].contains("green")) {
                        int temp = Integer.parseInt(str[0]);
                        if (temp > green) {
                            green = temp;
                        }
                    }
                    if (str[1].contains("blue")) {
                        int temp = Integer.parseInt(str[0]);
                        if (temp > blue) {
                            blue = temp;
                        }
                    }
                }
            }

            answer += (red * blue * green);
            red = 0;
            green = 0;
            blue = 0;
        }
        System.out.println(answer);

    }

    public static void problem1() {
        int answer = 0;
        boolean isOK = true;
        for (String line : input) {
            int red = 0;
            int blue = 0;
            int green = 0;

            String[] getid = line.split(":");
            String idString = getid[0].split("\\s+")[1];

            int id = Integer.parseInt(idString.trim());
            System.out.println(id);
            String[] iterations = getid[1].split(";");

            for (String times : iterations) {

                String[] cubes = times.split(",");

                for (String s : cubes) {
                    String[] str = s.trim().split("\\s+");

                    if (str[1].contains("red")) {
                        red += Integer.parseInt(str[0]);
                    }
                    if (str[1].contains("green")) {
                        green += Integer.parseInt(str[0]);
                    }
                    if (str[1].contains("blue")) {
                        blue += Integer.parseInt(str[0]);
                    }
                    if (RED >= red && GREEN >= green && BLUE >= blue && isOK) {
                        isOK = true;
                    } else {
                        isOK = false;
                    }
                }
                red = 0;
                green = 0;
                blue = 0;
            }

            if (isOK) {
                answer += id;
            }
            isOK = true;
        }
        System.out.println(answer);

    }

}
