package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day3 {
    final static ArrayList<String> input = new ArrayList<>();
    final static String path = "day3/input.txt";

    public static void main(String[] args) {
        readInput();
        // problem1();
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
        int sum = 0;
        for (int lineIndex = 0; lineIndex != input.size(); lineIndex++) {
            String line = input.get(lineIndex);
            for (int charIndex = 0; charIndex != line.length(); charIndex++) {
                char currentChar = line.charAt(charIndex);

                if (Character.isDigit(currentChar)) {
                    if (isAdjacentToSymbol(lineIndex, charIndex)) {
                        StringBuilder local = new StringBuilder();
                        int firstIndex = 0;
                        int lastIndex = 0;
                        for(int index = charIndex-1; index != -1; index--){
                            if(Character.isDigit(line.charAt(index))){
                                firstIndex = index;
                            }
                            else{
                                firstIndex= index+1;
                                break;
                            }
                        }
                        for(int index = charIndex+1; index != line.length(); index++){
                            if(Character.isDigit(line.charAt(index))){
                                lastIndex = index;
                            }
                            else{
                                lastIndex = index-1;
                                break;
                            }
                        }
                        for(int index = firstIndex; index <= lastIndex; index++){
                            local.append(line.charAt(index));
                        }
                        charIndex = lastIndex;
                        System.out.println(local.toString());
                        sum += Integer.parseInt(local.toString());

                    }
                }
            }
        }
        System.out.println("Sum of all part numbers: " + sum);
    }



    private static boolean isAdjacentToSymbol(int lineIndex, int charIndex) {
        String symbols = ".1234567890";
        if(lineIndex == 0){
            if(charIndex == 0){
                return symbols.indexOf(input.get(lineIndex+1).charAt(charIndex+1)) == -1 || symbols.indexOf(input.get(lineIndex).charAt(charIndex+1)) == -1 || symbols.indexOf(input.get(lineIndex+1).charAt(charIndex)) == -1;
            }
            else if(charIndex == input.get(lineIndex).length() - 1){
                return symbols.indexOf(input.get(lineIndex+1).charAt(charIndex-1)) == -1 || symbols.indexOf(input.get(lineIndex).charAt(charIndex-1)) == -1 || symbols.indexOf(input.get(lineIndex+1).charAt(charIndex)) == -1;
            }
            else{
                return symbols.indexOf(input.get(lineIndex+1).charAt(charIndex-1)) == -1 || symbols.indexOf(input.get(lineIndex+1).charAt(charIndex+1)) == -1 || symbols.indexOf(input.get(lineIndex).charAt(charIndex-1)) == -1 || symbols.indexOf(input.get(lineIndex).charAt(charIndex+1)) == -1 || symbols.indexOf(input.get(lineIndex+1).charAt(charIndex)) == -1;
            }
   
        }
        else if(lineIndex == input.size()-1){
            if(charIndex == 0){
                return symbols.indexOf(input.get(lineIndex-1).charAt(charIndex+1)) ==-1 || symbols.indexOf(input.get(lineIndex).charAt(charIndex+1)) == -1 || symbols.indexOf(input.get(lineIndex-1).charAt(charIndex)) == -1;
            }
            else if(charIndex == input.get(lineIndex).length() - 1){
                return symbols.indexOf(input.get(lineIndex-1).charAt(charIndex-1)) == -1 || symbols.indexOf(input.get(lineIndex).charAt(charIndex-1)) == -1 || symbols.indexOf(input.get(lineIndex-1).charAt(charIndex)) == -1;
            }
            else{
                return symbols.indexOf(input.get(lineIndex-1).charAt(charIndex-1)) == -1 || symbols.indexOf(input.get(lineIndex-1).charAt(charIndex+1)) ==-1 || symbols.indexOf(input.get(lineIndex).charAt(charIndex-1)) == -1 || symbols.indexOf(input.get(lineIndex).charAt(charIndex+1)) == -1 || symbols.indexOf(input.get(lineIndex-1).charAt(charIndex)) == -1;
            }
        }
        else{
            if(charIndex ==0){
                return symbols.indexOf(input.get(lineIndex-1).charAt(charIndex+1)) == -1 || symbols.indexOf(input.get(lineIndex+1).charAt(charIndex+1)) == -1 || symbols.indexOf(input.get(lineIndex).charAt(charIndex+1)) == -1 || symbols.indexOf(input.get(lineIndex-1).charAt(charIndex)) == -1 || symbols.indexOf(input.get(lineIndex+1).charAt(charIndex)) == -1;
            }
            else if(charIndex == input.get(lineIndex).length() - 1){
                return symbols.indexOf(input.get(lineIndex-1).charAt(charIndex-1)) == -1 || symbols.indexOf(input.get(lineIndex+1).charAt(charIndex-1)) == -1 || symbols.indexOf(input.get(lineIndex).charAt(charIndex-1)) == -1 || symbols.indexOf(input.get(lineIndex-1).charAt(charIndex)) == -1 || symbols.indexOf(input.get(lineIndex+1).charAt(charIndex)) == -1;
            }
            else{
                return symbols.indexOf(input.get(lineIndex-1).charAt(charIndex-1)) == -1 || symbols.indexOf(input.get(lineIndex-1).charAt(charIndex+1)) ==-1 || symbols.indexOf(input.get(lineIndex+1).charAt(charIndex-1)) == -1 || symbols.indexOf(input.get(lineIndex+1).charAt(charIndex+1)) == -1 || symbols.indexOf(input.get(lineIndex).charAt(charIndex-1)) == -1 || symbols.indexOf(input.get(lineIndex).charAt(charIndex+1)) == -1 || symbols.indexOf(input.get(lineIndex-1).charAt(charIndex)) == -1 || symbols.indexOf(input.get(lineIndex+1).charAt(charIndex)) == -1;

            }
            
            
        }
    }

    public static void problem2() {
        int product = 0;
        for (int lineIndex = 0; lineIndex != input.size(); lineIndex++) {
            String line = input.get(lineIndex);
            for (int charIndex = 0; charIndex != line.length(); charIndex++) {
                char currentChar = line.charAt(charIndex);
                if (currentChar == '*') {
                    Set<Integer> result = isAdjacentToNumber(lineIndex, charIndex);
                    if(result.size() > 1){
                        System.out.println(result);
                        product += result.stream().reduce(1, (a,b) -> a*b);
                    }
                }
            }
        }
        System.out.println(product);
    }
    private static Set<Integer> isAdjacentToNumber(int lineIndex, int charIndex) {
        Set<Integer> adjacentNumbers = new HashSet<>();
        String symbols = "1234567890";
        if(lineIndex ==0){
            
            if(charIndex == 0){
                if(symbols.indexOf(input.get(lineIndex+1).charAt(charIndex+1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex+1, input.get(lineIndex+1)));
                }
                if(symbols.indexOf(input.get(lineIndex).charAt(charIndex+1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex+1, input.get(lineIndex)));
                }
                if(symbols.indexOf(input.get(lineIndex+1).charAt(charIndex)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex, input.get(lineIndex+1)));
                }
                
            }
            else if(charIndex == input.get(lineIndex).length() - 1){
                if(symbols.indexOf(input.get(lineIndex+1).charAt(charIndex-1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex-1, input.get(lineIndex+1)));
                }
                if(symbols.indexOf(input.get(lineIndex).charAt(charIndex-1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex-1, input.get(lineIndex)));
                }
                if(symbols.indexOf(input.get(lineIndex+1).charAt(charIndex)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex, input.get(lineIndex+1)));
                }
                

            }
            else{
                if(symbols.indexOf(input.get(lineIndex+1).charAt(charIndex+1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex+1, input.get(lineIndex+1)));
                }
                if(symbols.indexOf(input.get(lineIndex).charAt(charIndex+1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex+1, input.get(lineIndex)));
                }
                if(symbols.indexOf(input.get(lineIndex+1).charAt(charIndex-1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex-1, input.get(lineIndex+1)));
                }
                if(symbols.indexOf(input.get(lineIndex).charAt(charIndex-1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex-1, input.get(lineIndex)));
                }
                if(symbols.indexOf(input.get(lineIndex+1).charAt(charIndex)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex, input.get(lineIndex+1)));
                }
                

            }
        }
        else if(lineIndex == input.size()-1){
            

            if(charIndex == 0){
                if(symbols.indexOf(input.get(lineIndex-1).charAt(charIndex+1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex+1, input.get(lineIndex-1)));
                }
                if(symbols.indexOf(input.get(lineIndex).charAt(charIndex+1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex+1, input.get(lineIndex)));
                }
                if(symbols.indexOf(input.get(lineIndex-1).charAt(charIndex)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex, input.get(lineIndex-1)));
                }
                //fel
                if(symbols.indexOf(input.get(lineIndex-1).charAt(charIndex-1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex-1, input.get(lineIndex-1)));
                }
                if(symbols.indexOf(input.get(lineIndex).charAt(charIndex-1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex-1, input.get(lineIndex)));
                }
                

            }
            else if(charIndex == input.get(lineIndex).length() - 1){
                if(symbols.indexOf(input.get(lineIndex-1).charAt(charIndex-1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex-1, input.get(lineIndex-1)));
                }
                if(symbols.indexOf(input.get(lineIndex).charAt(charIndex-1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex-1, input.get(lineIndex)));
                }
                if(symbols.indexOf(input.get(lineIndex-1).charAt(charIndex)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex, input.get(lineIndex-1)));
                }
                if(symbols.indexOf(input.get(lineIndex-1).charAt(charIndex+1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex+1, input.get(lineIndex-1)));
                }
                if(symbols.indexOf(input.get(lineIndex).charAt(charIndex+1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex+1, input.get(lineIndex)));
                }

            }
            else{
                if(symbols.indexOf(input.get(lineIndex-1).charAt(charIndex+1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex+1, input.get(lineIndex-1)));
                }
                if(symbols.indexOf(input.get(lineIndex).charAt(charIndex+1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex+1, input.get(lineIndex)));
                }
                if(symbols.indexOf(input.get(lineIndex-1).charAt(charIndex-1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex-1, input.get(lineIndex-1)));
                }
                if(symbols.indexOf(input.get(lineIndex).charAt(charIndex-1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex-1, input.get(lineIndex)));
                }
                if(symbols.indexOf(input.get(lineIndex-1).charAt(charIndex)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex, input.get(lineIndex-1)));
                }

            }
        }
        else{
            if(charIndex == 0){
                if(symbols.indexOf(input.get(lineIndex-1).charAt(charIndex+1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex+1, input.get(lineIndex-1)));
                }
                if(symbols.indexOf(input.get(lineIndex).charAt(charIndex+1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex+1, input.get(lineIndex)));
                }
                if(symbols.indexOf(input.get(lineIndex+1).charAt(charIndex+1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex+1, input.get(lineIndex+1)));
                }
                if(symbols.indexOf(input.get(lineIndex-1).charAt(charIndex)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex, input.get(lineIndex-1)));
                }
                if(symbols.indexOf(input.get(lineIndex+1).charAt(charIndex)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex, input.get(lineIndex+1)));
                }
                if(symbols.indexOf(input.get(lineIndex-1).charAt(charIndex+1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex+1, input.get(lineIndex-1)));
                }
                if(symbols.indexOf(input.get(lineIndex).charAt(charIndex+1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex+1, input.get(lineIndex)));
                }
                

            }
            else if(charIndex == input.get(lineIndex).length() - 1){
                if(symbols.indexOf(input.get(lineIndex-1).charAt(charIndex-1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex-1, input.get(lineIndex-1)));
                }
                if(symbols.indexOf(input.get(lineIndex).charAt(charIndex-1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex-1, input.get(lineIndex)));
                }
                if(symbols.indexOf(input.get(lineIndex+1).charAt(charIndex-1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex-1, input.get(lineIndex+1)));
                }
                if(symbols.indexOf(input.get(lineIndex-1).charAt(charIndex)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex, input.get(lineIndex-1)));
                }
                if(symbols.indexOf(input.get(lineIndex+1).charAt(charIndex)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex, input.get(lineIndex+1)));
                }
                if(symbols.indexOf(input.get(lineIndex-1).charAt(charIndex-1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex-1, input.get(lineIndex-1)));
                }

            }
            else{
                if(symbols.indexOf(input.get(lineIndex-1).charAt(charIndex-1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex-1, input.get(lineIndex-1)));
                }
                if(symbols.indexOf(input.get(lineIndex).charAt(charIndex-1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex-1, input.get(lineIndex)));
                }
                if(symbols.indexOf(input.get(lineIndex+1).charAt(charIndex-1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex-1, input.get(lineIndex+1)));
                }
                if(symbols.indexOf(input.get(lineIndex-1).charAt(charIndex+1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex+1, input.get(lineIndex-1)));
                }
                if(symbols.indexOf(input.get(lineIndex).charAt(charIndex+1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex+1, input.get(lineIndex)));
                }
                if(symbols.indexOf(input.get(lineIndex+1).charAt(charIndex+1)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex+1, input.get(lineIndex+1)));
                }
                if(symbols.indexOf(input.get(lineIndex+1).charAt(charIndex)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex, input.get(lineIndex+1)));
                }
                if(symbols.indexOf(input.get(lineIndex-1).charAt(charIndex)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex, input.get(lineIndex-1)));
                }
                if(symbols.indexOf(input.get(lineIndex).charAt(charIndex)) != -1){
                    adjacentNumbers.add(numberFromIndex(charIndex, input.get(lineIndex)));
                }

            }

        }
        return adjacentNumbers;

    }
    static int numberFromIndex(int j, String list) {
		int startIndex = j;
		int endIndex = j;
		while (startIndex > 0 && Character.isDigit(list.charAt(startIndex - 1))) {
			startIndex--;
		}
		while (endIndex < list.length() - 1 && Character.isDigit(list.charAt(endIndex + 1))) {
			endIndex++;
		}

		String wholeNUmber = "";
		for (int k = startIndex; k <= endIndex; k++) {
			wholeNUmber += list.charAt(k);
		}
		return Integer.valueOf(wholeNUmber);
	}
    
    
        
    }


