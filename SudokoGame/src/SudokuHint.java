import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SudokuHint {

    public static HintResult getHint(int currentRow, int currentCol, int[][] playerBoard, int[][] solutionBoard) {

        List<HintResult> possibleHints = new ArrayList<>();

        if (currentRow != -1 && currentCol != -1 && playerBoard[currentRow][currentCol] == 0) {

                int correctValue = solutionBoard[currentRow][currentCol];

                // Store valid hint option
                possibleHints.add(new HintResult(currentRow, currentCol, correctValue));
        

        } else {

            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {

                    // Only EMPTY cells
                    if (playerBoard[r][c] == 0) {

                        int correctValue = solutionBoard[r][c];

                        // Store valid hint option
                        possibleHints.add(new HintResult(r, c, correctValue));
                    }
                }
            }


        }

        // No hints available
        if (possibleHints.isEmpty()) {
            return null;
        }

        // Random hint (better UX)
        Random rand = new Random();
        return possibleHints.get(rand.nextInt(possibleHints.size()));
    }
}
