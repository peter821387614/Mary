import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[3][3];
        char player = 'X';
        int row, col;

        // Initialize the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }

        // Play the game
        while (true) {
            // Print the board
            System.out.println("Current board:");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }

            // Get the player's move
            System.out.print("Player " + player + ", enter row (0-2): ");
            row = scanner.nextInt();
            System.out.print("Player " + player + ", enter column (0-2): ");
            col = scanner.nextInt();

            // Check if the move is valid
            if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != '-') {
                System.out.println("Invalid move, please try again.");
                continue;
            }

            // Make the move
            board[row][col] = player;

            // Check if the game is over
            if (isGameOver(board, player)) {
                System.out.println("Player " + player + " wins!");
                break;
            } else if (isBoardFull(board)) {
                System.out.println("Game over, it's a tie!");
                break;
            }

            // Switch to the other player
            player = (player == 'X') ? 'O' : 'X';
        }
    }
    

    // Check if the game is over (i.e. there is a winning player)
    public static boolean isGameOver(char[][] board, char player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    // Check if the board is full (i.e. no more moves can be made)
    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
