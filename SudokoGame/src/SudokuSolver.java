public class SudokuSolver {

    private static final int SIZE = 9;
    private static int solutionCount;

    // Public API: Solve and return solved board
    public static int[][] solve(int[][] board) {
        int[][] copy = deepCopy(board);
        solveBoard(copy);
        return copy;
    }

    // Public API: Check unique solution
    public static boolean hasUniqueSolution(int[][] board) {
        solutionCount = 0;
        int[][] copy = deepCopy(board);
        countSolutions(copy);
        return solutionCount == 1;
    }

    // ================= CORE SOLVER =================

    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveBoard(board)) return true;
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // ================= UNIQUE COUNT =================

    private static void countSolutions(int[][] board) {
        if (solutionCount > 1) return;

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            countSolutions(board);
                            board[row][col] = 0;
                        }
                    }
                    return;
                }
            }
        }
        solutionCount++;
    }

    // ================= VALIDATION =================

    private static boolean isValid(int[][] board, int row, int col, int num) {

        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num) return false;
            if (board[i][col] == num) return false;
        }

        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;

        for (int r = boxRow; r < boxRow + 3; r++) {
            for (int c = boxCol; c < boxCol + 3; c++) {
                if (board[r][c] == num) return false;
            }
        }
        return true;
    }

    private static int[][] deepCopy(int[][] board) {
        int[][] copy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            System.arraycopy(board[i], 0, copy[i], 0, SIZE);
        return copy;
    }
}
