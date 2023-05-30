import java.util.Scanner;

public class TryingTwoLevels {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Sudoku!\n");

        Scanner in = new Scanner(System.in);

        int level1[][] = {
            { 11, 11, 1, 2, 3, 4, 11 },
            { 11, 10, 9, 9, 9, 9, 10 },
            { 5, 10, 4, 3, 0, 0, 10 },
            { 6, 10, 1, 2, 3, 0, 10 },
            { 7, 10, 0, 0, 2, 0, 10 },
            { 8, 10, 2, 1, 0, 0, 10 },
            { 11, 10, 9, 9, 9, 9, 10 }
        };

        int level2[][] = {
            { 11, 11, 1, 2, 3, 4, 11 },
            { 11, 10, 9, 9, 9, 9, 10 },
            { 5, 10, 0, 0, 3, 1, 10 },
            { 6, 10, 0, 3, 0, 0, 10 },
            { 7, 10, 3, 0, 0, 2, 10 },
            { 8, 10, 2, 0, 4, 0, 10 },
            { 11, 10, 9, 9, 9, 9, 10 }
        };

        while (true) {
            System.out.println("Please select a level:");
            System.out.println("1. Level 1");
            System.out.println("2. Level 2");
            System.out.println("0. Quit");

            int choice = in.nextInt();
            in.nextLine();

            if (choice == 0) {
                System.out.println("Thank you for playing! Goodbye!");
                break;
            } else if (choice == 1) {
                playLevel(level1, choice);
            } else if (choice == 2) {
                playLevel(level2, choice);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void playLevel(int[][] sudoku, int level) {
        Scanner in = new Scanner(System.in);
        boolean completed = false;

        while (!completed) {
            printArray(sudoku);

            System.out.println("What number would you like to place? Enter the box (ex. A 4 3)");

            String change = in.nextLine();
            String[] stringChange = change.split(" ");
            stringChange[0] = stringChange[0].toUpperCase();

            int row = stringChange[0].charAt(0) - 'A' + 2;
            int col = Integer.parseInt(stringChange[1]) + 1;

            if (row < 0 || row >= sudoku.length || col < 0 || col >= sudoku[0].length) {
                System.out.println("Invalid input! Please enter a valid box.");
                continue;
            }

            int number = Integer.parseInt(stringChange[2]);
            sudoku[row][col] = number;

            completed = isSudokuComplete(sudoku, level);
        }

        System.out.println("Congratulations! You have completed the Sudoku puzzle!");
        
    }

    public static void printArray(int[][] array) {
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

    public static boolean isSudokuComplete(int[][] sudoku, int level) {
        int[][] sudokuCorrect;

        if (level == 1) {
            sudokuCorrect = new int[][] {
                { 11, 11, 1, 2, 3, 4, 11 },
                { 11, 10, 9, 9, 9, 9, 10 },
                { 5, 10, 4, 3, 1, 2, 10 },
                { 6, 10, 1, 2, 3, 4, 10 },
                { 7, 10, 3, 4, 2, 1, 10 },
                { 8, 10, 2, 1, 4, 3, 10 },
                { 11, 10, 9, 9, 9, 9, 10 }
            };
        } else if (level == 2) {
            sudokuCorrect = new int[][] {
                { 11, 11, 1, 2, 3, 4, 11 },
                { 11, 10, 9, 9, 9, 9, 10 },
                { 5, 10, 4, 2, 3, 1, 10 },
                { 6, 10, 1, 3, 2, 4, 10 },
                { 7, 10, 3, 4, 1, 2, 10 },
                { 8, 10, 2, 1, 4, 3, 10 },
                { 11, 10, 9, 9, 9, 9, 10 }
            };
        } else {
            throw new IllegalArgumentException("Invalid Level!");
        }

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