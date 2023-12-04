package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day4 {
    final static List<String> input = new ArrayList<>();
    final static String path = "day4/input.txt";

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

    private static void problem1() {
        int ans = 0;
        for (String s : input) {
            String[] parts = s.split(":");
            String[] cardNumbers = parts[1].split("\\|")[1].trim().split(" ");
            String[] winningNumbers = parts[1].split("\\|")[0].trim().split(" ");

            Set<String> winningSet = new HashSet<>(Arrays.asList(winningNumbers));
            int localAns = 0;

            for (String number : cardNumbers) {
                if (winningSet.contains(number.trim())) {
                    localAns++;
                }
            }

            if (localAns == 1) {
                ans += 1;
            } else if (localAns > 1) {
                int local = 1;
                for (int i = 1; i < localAns; i++) {
                    local *= 2;
                }
                ans += local;
            }
        }
        System.out.println(ans);
    }

    public static void problem2() {
        int[] ans = new int[input.size()];
        Arrays.fill(ans, 1);

        for (int i = 0; i < input.size(); i++) {
            String[] parts = input.get(i).split(":");
            String[] cardNumbers = parts[1].split("\\|")[1].trim().split(" ");
            String[] winningNumbers = parts[1].split("\\|")[0].trim().split(" ");

            Set<String> winningSet = new HashSet<>(Arrays.asList(winningNumbers));

            int localAns = 0;
            for (String number : cardNumbers) {
                if (winningSet.contains(number.trim())) {
                    localAns++;
                }
            }
            for (int newIndex = i + 1; newIndex < input.size(); newIndex++) {
                if (localAns <= 0) {
                    break;
                }
                ans[newIndex] += ans[i];
                localAns--;
            }
        }
        int sum = 0;
        for (int i : ans) {
            sum += i;
        }

        System.out.println(sum);
    }
}
