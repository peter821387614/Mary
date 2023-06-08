public class EightQueens {
    private static final int SIZE = 4;
    private static int[] queens = new int[SIZE];

    public static void main(String[] args) {
        solve(0);
    }

    private static void solve(int row) {
        if (row == SIZE) {
            printQueens();
            return;
        }
        for (int col = 0; col < SIZE; col++) {
            if (isValidPosition(row, col)) {
                queens[row] = col;
                solve(row + 1);
            }
        }
    }

    private static boolean isValidPosition(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == col || 
                queens[i] - i == col - row || 
                queens[i] + i == col + row) {
                return false;
            }
        }
        return true;
    }

    private static void printQueens() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (queens[row] == col) {
                    System.out.print("Q ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}