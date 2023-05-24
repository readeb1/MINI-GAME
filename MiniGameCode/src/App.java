import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Suduko");
        int row = 3;
        int col = 3;
        int sudoku[][] = { { 4, 3, 0, 0 },
                { 1, 2, 3, 0 },
                { 0, 0, 2, 0 },
                { 2, 1, 0, 0 } };

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();

        }

    }
}
