import java.util.Random;

public class TablerosGenerator {

    //Genera un sudoku resuelto desde 0
    public static int[][] sudokuGenerator() {
        int[][] tablero = new int[9][9];
        fillBoard(tablero, 0, 0);
        return tablero;
    }

    //Función de backtracking para llenar el tablero de Sudoku
    private static boolean fillBoard(int[][] tablero, int fila, int columna) {
        if (fila == 9) return true;
        if (columna == 9) return fillBoard(tablero, fila + 1, 0);

        Random random = new Random();
        int[] nums = random.ints(1, 10).distinct().limit(9).toArray();
        for (int num : nums) {
            if (isValid(tablero, fila, columna, num)) {
                tablero[fila][columna] = num;
                if (fillBoard(tablero, fila, columna + 1)) return true;
                tablero[fila][columna] = 0;
            }
        }

        return false;
    }

    //Verifica si un número puede ser colocado en una celda específica
    private static boolean isValid(int[][] tablero, int fila, int columna, int num) {
        for (int i = 0; i < 9; i++) {
            if (tablero[fila][i] == num || tablero[i][columna] == num) return false;
        }

        int startRow = (fila / 3) * 3;
        int startCol = (columna / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[startRow + i][startCol + j] == num) return false;
            }
        }

        return true;
    }
}
