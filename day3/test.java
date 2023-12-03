package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class test {
    final static ArrayList<String> input = new ArrayList<>();
    final static String path = "day3/input.txt";

    public static void main(String[] args) {
        readInput();
        problem2();
    }

    private static void problem2() {
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
        
    }
    private static boolean isAdjacentToSymbol(int lineIndex, int charIndex) {
        String symbols = "1234567890";
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
}
