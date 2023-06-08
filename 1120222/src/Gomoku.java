import java.util.Scanner;

public class Gomoku {
    private static final int ROWS = 15;
    private static final int COLUMNS = 15;
    private static char[][] board = new char[ROWS][COLUMNS];
    private static boolean player1Turn = true;

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println((player1Turn ? "Player 1" : "Player 2") + ", enter your move (row,column):");
            int row = scanner.nextInt();
            int column = scanner.nextInt();
            if (makeMove(row, column)) {
                printBoard();
                if (checkWin(row, column)) {
                    System.out.println((player1Turn ? "Player 1" : "Player 2") + " wins!");
                    break;
                }
                player1Turn = !player1Turn;
            } else {
                System.out.println("Invalid move, try again.");
            }
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void printBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean makeMove(int row, int column) {
        if (row < 0 || row >= ROWS || column < 0 || column >= COLUMNS || board[row][column] != '-') {
            return false;
        }
        board[row][column] = player1Turn ? 'X' : 'O';
        return true;
    }

    private static boolean checkWin(int row, int column) {
        char symbol = player1Turn ? 'X' : 'O';

        // Check horizontal
        int count = 0;
        for (int j = Math.max(column - 4, 0); j <= Math.min(column + 4, COLUMNS - 1); j++) {
            if (board[row][j] == symbol) {
                count++;
            } else {
                count = 0;
            }
            if (count == 5) {
                return true;
            }
        }

        // Check vertical
        count = 0;
        for (int i = Math.max(row - 4, 0); i <= Math.min(row + 4, ROWS - 1); i++) {
            if (board[i][column] == symbol) {
                count++;
            } else {
                count = 0;
            }
            if (count == 5) {
                return true;
            }
        }

        // Check diagonal (top-left to bottom-right)
        count = 0;
        for (int i = Math.max(row - 4, 0), j = Math.max(column - 4, 0); i <= Math.min(row + 4, ROWS - 1) && j <= Math.min(column + 4, COLUMNS - 1); i++, j++) {
            if (board[i][j] == symbol) {
                count++;
            } else {
                count = 0;
            }
        }
    }
}

