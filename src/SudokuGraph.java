import java.util.LinkedList;
import java.util.Random;

public class SudokuGraph {

    // Clase Node

    public class Node {
        private int fila, columna;
        private int valor;
        private LinkedList<Node> adjList;

        public Node(int fila, int columna, int valor) {
            this.fila = fila;
            this.columna = columna;
            this.valor = valor;
            adjList = new LinkedList<>();
        }

        public void addAdj(Node n) {
            adjList.add(n);
        }

        public boolean esValido(){
            switch (valor){
                case 1,2,3,4,5,6,7,8,9:
                    break;
                default: return false;
            }
            for(Node adj:adjList){
                if(valor == adj.valor){
                    return false;
                }
            }
            return true;
        }

        public boolean numEsValido(int num){
            for(Node adj:adjList){
                if(num == adj.valor){
                    return false;
                }
            }
            return true;
        }
    }

    //Crea el grafo del sudoku

    private Node [][] sudoku;

    public SudokuGraph(int [][] tablero) {
        this.sudoku = new Node[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.sudoku[i][j] = new Node(i, j, tablero[i][j]);
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                addAdj(sudoku[i][j]);
            }
        }
    }

    //Elimina cierta cant de numeros a peticion, para las diferentes dificultades

    public void removeNumbers(int numToRemove) {
        Random random = new Random();
        int count = 0;

        while (count < numToRemove) {
            int fila = random.nextInt(9);
            int columna = random.nextInt(9);

            if (sudoku[fila][columna].valor != 0) {
                this.sudoku[fila][columna].valor = 0;
                count++;
            }
        }
    }

    //Añade los Adj

    private void addAdj(Node n) {
        int fila = n.fila;
        int columna = n.columna;

        //Añade las filas y columnas

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j].fila == fila && sudoku[i][j].columna == columna) {
                    continue;
                } else if (sudoku[i][j].fila == fila) {
                    n.adjList.add(sudoku[i][j]);
                } else if (sudoku[i][j].columna == columna) {
                    n.adjList.add(sudoku[i][j]);
                }
            }
        }

        //Añade los adj del 3x3
        //Al dividir un int por 3 y luego multiplicarlo por 3, el primer resultado de redondea y da como resultado el num de inicio
        int inicioAdjBoxFila = (fila / 3) * 3;
        int inicioADjBoxCol = (columna / 3) * 3;

        for (int i = inicioAdjBoxFila; i < inicioAdjBoxFila + 3; i++){
            for (int j = inicioADjBoxCol; j < inicioADjBoxCol + 3; j++){
                if (i != fila || j != columna){
                    n.addAdj(sudoku[i][j]);
                }
            }
        }
    }

    //Resulve el tablero sudoku      BACK TRACKING

    public boolean resolver(){
        return resolver(0,0);
    }

    private boolean resolver(int fila , int columna){
        if (fila == 9){
            return true;
        }
        if (columna == 9) return resolver(fila+1, 0);
        if (sudoku[fila][columna].valor != 0) return resolver(fila, columna+1);

        for (int num = 1; num <= 9; num++){
            if (sudoku[fila][columna].numEsValido(num)){
                this.sudoku[fila][columna].valor = num;
                if (resolver(fila, columna+1)) return true;
                this.sudoku[fila][columna].valor = 0;
            }
        }
        return false;
    }

    //Verifica si el numero ingresado por fieldText es valido

    public boolean setValor(int fila, int columna, int valor){
        if (sudoku[fila][columna].numEsValido(valor)){
            this.sudoku[fila][columna].valor = valor;
            return true;
        }
        return false;
    }

    // Métodos públicos para obtener los datos del Sudoku

    public int getValor(int fila, int columna) {
        return sudoku[fila][columna].valor;
    }

    public int[][] getTablero() {
        int[][] tablero = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tablero[i][j] = sudoku[i][j].valor;
            }
        }
        return tablero;
    }
}
