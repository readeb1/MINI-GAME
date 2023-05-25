public class ArraysUtils {
    /**
     * Overloaded versions of printArray
     * Prints specified array, one element at a time.
     * @param sudoku
     */
    public static void printArray(int[][] sudoku){
        for (int i = 0; i < sudoku.length;i++){
            Utilities.prn(sudoku[i]);
        }
    }
    public static void printArray(int[] array){
        for (int i = 0; i < array.length;i++){
            Utilities.prn(array[i]);
        }
    }
    public static void printArray(String[] array){
        for (int i = 0; i < array.length;i++){
            Utilities.prn(array[i]);
        }
    }
    public static void printArray(float[] array){
        for (int i = 0; i < array.length;i++){
            Utilities.prn(array[i]);
        }
    }
    public static void printArray(long[] array){
        for (int i = 0; i < array.length;i++){
            Utilities.prn(array[i]);
        }
    }
    public static void printArray(char[] array){
        for (int i = 0; i < array.length;i++){
            Utilities.prn(array[i]);
        }
    }
    public static void printArray(boolean[] array){
        for (int i = 0; i < array.length;i++){
            Utilities.prn(array[i]);
        }
    }
    
    public static double[] createFilledArray(int size, double value){
        double[] array = new double[size];

        for (int i = 0; i < size; i++){
            array[i] = value;
        }
        return array;
    }
    public static float[] createFilledArray(int size, float value){
        float[] array = new float[size];

        for (int i = 0; i < size; i++){
            array[i] = value;
        }
        return array;
    }
    public static long[] createFilledArray(int size, long value){
     long[] array = new long[size];

        for (int i = 0; i < size; i++){
            array[i] = value;
        }
        return array;
    }
    public static int[] createFilledArray(int size, int value){
        int[] array = new int[size];

        for (int i = 0; i < size; i++){
            array[i] = value;
        }
        return array;
    }
    public static char[] createFilledArray(int size, char value){
        char[] array = new char[size];

        for (int i = 0; i < size; i++){
            array[i] = value;
        }
        return array;
    }
    public static boolean[] createFilledArray(int size, boolean value){
        boolean[] array = new boolean[size];

        for (int i = 0; i < size; i++){
            array[i] = value;
        }
        return array;
    }
    
}
