import java.util.Scanner;

public class FiveInRow {
    private static final int ROWS = 15;
    private static final int COLS = 15;
    private static final char PLAYER_ONE = 'X';
    private static final char PLAYER_TWO = 'O';
    private static final char EMPTY_SPACE = '-';
    private static char[][] board = new char[ROWS][COLS];
    private static boolean playerOneTurn = true;
    
    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println((playerOneTurn ? "Player One" : "Player Two") + "'s turn:");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            if (makeMove(row, col)) {
                printBoard();
                if (checkWin(row, col)) {
                    System.out.println((playerOneTurn ? "Player One" : "Player Two") + " wins!");
                    break;
                }
                if (checkDraw()) {
                    System.out.println("Draw!");
                    break;
                }
                playerOneTurn = !playerOneTurn;
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }
        scanner.close();
    }

    private static void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = EMPTY_SPACE;
            }
        }
    }

    private static void printBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean makeMove(int row, int col) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS || board[row][col] != EMPTY_SPACE) {
            return false;
        }
        board[row][col] = playerOneTurn ? PLAYER_ONE : PLAYER_TWO;
        return true;
    }

    private static boolean checkWin(char[][] board, int row, int col, char player) {
        // check horizontal line
        int count = 0;
        for (int j = 0; j < board[0].length; j++) {
            if (board[row][j] == player) {
                count++;
                if (count == 5) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        // check vertical line
        count = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == player) {
                count++;
                if (count == 5) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        // check diagonal lines
        int startRow = row;
        int startCol = col;
        while (startRow > 0 && startCol > 0) {
            startRow--;
            startCol--;
        }
        count = 0;
        while (startRow < board.length && startCol < board[0].length) {
            if (board[startRow][startCol] == player) {
                count++;
                if (count == 5) {
                    return true;
                }
            } else {
                count = 0;
            }
            startRow++;
            startCol++;
        }

        startRow = row;
        startCol = col;
        while (startRow < board.length - 1 && startCol > 0) {
            startRow++;
            startCol--;
        }
        count = 0;
        while (startRow >= 0 && startCol < board[0].length) {
            if (board[startRow][startCol] == player) {
                count++;
                if (count == 5) {
                    return true;
                }
            } else {
                count = 0;
            }
            startRow--;
            startCol++;
        }

        // no win condition met
        return false;
    }
}
