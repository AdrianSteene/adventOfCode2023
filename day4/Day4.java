package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

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
            int winners = winners(s);
            ans += (int) (winners == 0 ? 0 : Math.pow(2, winners - 1));
        }
        System.out.println("Part one: " + ans);
    }

    public static void problem2() {
        int[] ans = new int[input.size()];
        Arrays.fill(ans, 1);

        for (int i = 0; i < input.size(); i++) {

            int localAns = winners(input.get(i));

            for (int newIndex = i + 1; newIndex < input.size(); newIndex++) {
                if (localAns <= 0) {
                    break;
                }
                ans[newIndex] += ans[i];
                localAns--;
            }
        }
        int sum = IntStream.of(ans).sum();
        System.out.println("Part 2. " + sum);
    }

    public static int winners(String input) {
        String[] parts = input.split(":");
        String[] winningNumbers = parts[1].split("\\|")[0].trim().split(" ");
        String[] cardNumbers = parts[1].split("\\|")[1].trim().split(" ");
        Set<String> winningSet = new HashSet<>(Arrays.asList(winningNumbers));
        Set<String> ticketSet = new HashSet<>(Arrays.asList(cardNumbers));

        winningSet.retainAll(ticketSet);
        return winningSet.size();
    }
}
