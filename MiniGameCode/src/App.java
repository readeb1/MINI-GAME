import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Suduko!\n");

        Scanner in = new Scanner(System.in);

        int sudoku[][] = 
        // {
        //     {11, 11, 1, 2, 3, 4, 11},
        //     {11, 10, 9, 9, 9, 9, 10},
        //     {5, 10, 4, 3, 1, 2, 10},
        //     {6, 10, 1, 2, 3, 4, 10},
        //     {7, 10, 3, 4, 2, 1, 10},
        //     {8, 10, 2, 1, 4, 0, 10},
        //     {11, 10, 9, 9, 9, 9, 10}};
        {
                { 11, 11, 1, 2, 3, 4, 11 },
                { 11, 10, 9, 9, 9, 9, 10 },
                { 5, 10, 4, 3, 0, 0, 10 },
                { 6, 10, 1, 2, 3, 0, 10 },
                { 7, 10, 0, 0, 2, 0, 10 },
                { 8, 10, 2, 1, 0, 0, 10 },
                { 11, 10, 9, 9, 9, 9, 10 } };

        // ArraysUtils.printArray(sudoku);
        printArray(sudoku);

        boolean failed = false;

        while (!isSudokuComplete(sudoku)) {
            System.out.println(blue("What number would you like to place? Enter the box (ex. A 4 3)"));
            
            String change = in.nextLine();
            String[] stringChange = change.split(" ");
            stringChange[0] = stringChange[0].toUpperCase();

            int row = stringChange[0].charAt(0) - 'A' + 2;
            int col = Integer.parseInt(stringChange[1]) + 1;

            if (row < 0 || row >= sudoku.length || col < 0 || col >= sudoku[0].length) {
                System.out.println("Invalid input! Please enter a valid box.");
                continue;
            }

            // if (sudoku[row][col] != 0) {
            //     System.out.println("The selected box is already filled! Please choose another box.");
            //     continue;
            // }

            int number = Integer.parseInt(stringChange[2]);
            sudoku[row][col] = number;

            printArray(sudoku);

        }

        if (failed) {
            System.out.println("You failed! The Sudoku puzzle is not completed correctly.");
        } else {
            System.out.println("Congratulations! You have completed the Sudoku puzzle!");
            String[] notes = {"C4", "C4", "G4", "G4", "A4", "A4", "G4","C4","C4", "G4", "G4", "A4", "A4", "G4","C4","C4", "G4", "G4", "A4", "A4", "G4","C4","C4", "G4", "G4", "A4", "A4", "G4"};
        for (int i = 0; i < notes.length; i++){
        MusicNote.playNote(notes[i], .25);
        }

    }
    }

    public static void printArray(int [][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] >= 0 && array[i][j] <= 4) {
                    System.out.print(" " + array[i][j] + " ");
                } else if (array[i][j] == 9) {
                    System.out.print(blue("---"));
                } else if (array[i][j] == 10) {
                    System.out.print(blue(" | "));
                } else if (array[i][j] == 5) {
                    System.out.print(" A ");
                } else if (array[i][j] == 6) {
                    System.out.print(" B ");
                } else if (array[i][j] == 7) {
                    System.out.print(" C ");
                } else if (array[i][j] == 8) {
                    System.out.print(" D ");
                } else if (array[i][j] == 11) {
                    System.out.print("   ");
                }

            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean isSudokuComplete(int[][] sudoku) {
        int sudokuCorrect[][] = {
                { 11, 11, 1, 2, 3, 4, 11 },
                { 11, 10, 9, 9, 9, 9, 10 },
                { 5, 10, 4, 3, 1, 2, 10 },
                { 6, 10, 1, 2, 3, 4, 10 },
                { 7, 10, 3, 4, 2, 1, 10 },
                { 8, 10, 2, 1, 4, 3, 10 },
                { 11, 10, 9, 9, 9, 9, 10 } };

        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[0].length; j++) {
                if (sudoku[i][j] != sudokuCorrect[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static String blue(String text){
        return "\033[0;34m" + text +"\033[0m";
    }

}
