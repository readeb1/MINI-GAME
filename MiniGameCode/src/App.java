import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Suduko!\n");

        Scanner in = new Scanner(System.in);

        int sudoku[][] = {
                {11, 11, 1, 2, 3, 4, 11},
                {11, 10, 9, 9, 9, 9, 10},
                {5, 10, 4, 3, 0, 0, 10},
                {6, 10, 1, 2, 3, 0, 10},
                {7, 10, 0, 0, 2, 0, 10},
                {8, 10, 2, 1, 0, 0, 10},
                {11, 10, 9, 9, 9, 9, 10}};

        // ArraysUtils.printArray(sudoku);
printArray(sudoku, sudoku.length, sudoku[0].length);

System.out.println("What number would you like to place? Enter the box (ex. A 4 is 3)");
            String change = in.nextLine();
            String [] stringChange = change.split(" ");
            ArraysUtils.printArray(stringChange);

            if (change.contains("A")|| change.contains("B")|| change.contains("C")|| change.contains("D")){
                
            }
        
    
        }
        public static void swaps(int[] array, int one, int two) {

            int temp = array[one];
            array[one] = array[two];
            array[two] = temp;
            ArraysUtils.printArray(array);
        }

        // boolean finished;

        
            
    
    public static void printArray(int array[][], int row, int col){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (array [i][j] >= 0 && array [i][j] <= 4){
                  System.out.print(" " + array[i][j] + " ");
                } else if (array [i][j] == 9){
                    System.out.print("---");
                } else if (array [i][j] == 10){
                    System.out.print(" | ");
                }else if (array [i][j] == 5){
                    System.out.print(" A ");
                }else if (array [i][j] == 6){
                    System.out.print(" B ");
                } else if (array [i][j] == 7){
                    System.out.print(" C ");
                } else if (array [i][j] == 8){
                    System.out.print(" D ");
                } else if (array [i][j] == 11) {
                    System.out.print("   ");
                }
                
            }
            System.out.println();
    }
}
}
