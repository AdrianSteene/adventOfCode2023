package day6;

import java.util.ArrayList;

public class Day6 {
    static ArrayList<long[]> inputTime = new ArrayList<>();
    final static String path = "day6/input.txt";

    public static void main(String[] args) {
        readInput();
    }

    public static void readInput() {
        String line1 = "Time: 52 94 75 94";
        String line2 = "Distance: 426 1374 1279 1216";
        String[] line1Split = line1.split(" ");
        String[] line2Split = line2.split(" ");
        for (int i = 1; i < line1Split.length; i++) {
            long[] temp = new long[2];
            temp[0] = Long.parseLong(line1Split[i]);
            temp[1] = Long.parseLong(line2Split[i]);
            inputTime.add(temp);
        }
        problem("1");
        inputTime = new ArrayList<>();

        long[] temp = new long[2];
        temp[0] = 52947594;
        temp[1] = 426137412791216L;
        inputTime.add(temp);
        problem("2");

    }

    private static void problem(String problem) {
        int ans = 1;
        for (long[] input : inputTime) {
            int ways = 0;
            for (int time = 0; time <= input[0]; time++) 
                if ((time * (input[0] - time)) > input[1]) ways++;
            ans *= ways;
        }
        System.out.println("Answer for problem " + problem + ": " + ans);
    }

}
