public class MazeSolver {
    private int[][] maze;
    private int size;

    public MazeSolver(int[][] maze, int size) {
        this.maze = maze;
        this.size = size;
    }

     public boolean solve(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            return false;
        }
        if (maze[x][y] == 0) {
            maze[x][y] = 2;
            if (x == size - 1 && y == size - 1) {
                return true;
            } else if (solve(x + 1, y) || solve(x, y + 1) || solve(x - 1, y) || solve(x, y - 1)) {
                return true;
            } else {
                maze[x][y] = 3;
            }
        }
        return false;
    }

    public void printSolution() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }
     public static void main(String[] args) {
        int[][] maze = {
            {0, 1, 0, 1, 1, 1},
            {0, 0, 0, 0, 1, 0},
            {1, 1, 1, 0, 0, 0},
            {1, 0, 1, 1, 0, 0},
            {1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 0}
        };
        MazeSolver solver = new MazeSolver(maze, 6);
        if (solver.solve(0, 0)) {
            solver.printSolution();
        } else {
            System.out.println("No solution found.");
        }
    }
}