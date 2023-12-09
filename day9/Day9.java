package day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Day9 {
    final static String path = "day9/input.txt";

    public static void main(String[] args) {
        readInput();

    }

    public static void readInput() {
        File file = new File(path);
        int answer1 = 0;
        int answer2 = 0;
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String inputString = myReader.nextLine();
                String[] inputStringArray = inputString.split(" ");
                LinkedList<Integer> input1 = new LinkedList<Integer>();
                LinkedList<Integer> input2 = new LinkedList<Integer>();
                for (String number : inputStringArray) {
                    input1.addLast(Integer.parseInt(number));
                    input2.addLast(Integer.parseInt(number));
                }
                answer1 += problem1(input1);
                answer2 += problem2(input2);
            }
            myReader.close();
            System.out.println("Problem 1: " + answer1);
            System.out.println("Problem 2: " + answer2);
        } catch (FileNotFoundException e) {
            System.out.println("Error in reading input");
        }
    }

    private static int problem2(LinkedList<Integer> input2) {
        LinkedList<LinkedList<Integer>> listList = new LinkedList<LinkedList<Integer>>();
        listList.addFirst(input2);
        boolean isNoneZero = true;
        while (isNoneZero) {
            LinkedList<Integer> tempList = new LinkedList<Integer>();
            LinkedList<Integer> list = listList.getLast();
            for (int index = 0; index < list.size() - 1; index++) {
                int firstNumber = list.get(index);
                int secondNumber = list.get(index + 1);
                tempList.addLast((secondNumber - firstNumber));
            }
            listList.addLast(tempList);
            if (tempList.stream().allMatch(number -> number == 0)) {
                isNoneZero = false;
            }
        }
        for (int index = listList.size() - 1; index > 0; index--) {
            int localSum = listList.get(index - 1).getFirst() - listList.get(index).getFirst();
            listList.get(index - 1).addFirst(localSum);
        }
        return listList.getFirst().getFirst();
    }

    public static int problem1(LinkedList<Integer> inputList) {
        LinkedList<LinkedList<Integer>> listList = new LinkedList<LinkedList<Integer>>();
        listList.addFirst(inputList);
        boolean isNoneZero = true;
        while (isNoneZero) {
            LinkedList<Integer> tempList = new LinkedList<Integer>();
            LinkedList<Integer> list = listList.getLast();
            for (int index = 0; index < list.size() - 1; index++) {
                int firstNumber = list.get(index);
                int secondNumber = list.get(index + 1);
                tempList.addLast((secondNumber - firstNumber));
            }
            listList.addLast(tempList);
            if (tempList.stream().allMatch(number -> number == 0)) {
                isNoneZero = false;
            }
        }
        for (int index = listList.size() - 1; index > 0; index--) {
            int localSum = listList.get(index).getLast() + listList.get(index - 1).getLast();
            listList.get(index - 1).addLast(localSum);
        }
        return listList.getFirst().getLast();

    }
}
