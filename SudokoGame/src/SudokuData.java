import com.google.gson.*;
import java.io.*;
import java.util.*;

public class SudokuData {

    public int level;
    public String difficulty;
    public int[][] puzzle;

    // Reads the FIRST level from JSON (or loop if needed)
    public static List<SudokuData> readAllLevels() {

        List<SudokuData> list = new ArrayList<>();

        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("sudoku_300_levels.json");

            JsonObject root = gson.fromJson(reader, JsonObject.class);
            JsonArray levels = root.getAsJsonArray("levels");

            for (JsonElement e : levels) {
                JsonObject obj = e.getAsJsonObject();

                SudokuData data = new SudokuData();

                data.level = obj.get("level").getAsInt();
                data.difficulty = obj.get("difficulty").getAsString();

                JsonArray p = obj.getAsJsonArray("puzzle");
                data.puzzle = new int[9][9];

                for (int i = 0; i < 9; i++) {
                    JsonArray row = p.get(i).getAsJsonArray();
                    for (int j = 0; j < 9; j++) {
                        data.puzzle[i][j] = row.get(j).getAsInt();
                    }
                }

                list.add(data);
            }

            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }


    public static void main(String[] args) {
        
        List<SudokuData> levels = SudokuData.readAllLevels();

SudokuData l = levels.get(1); // any level you want 

int level = l.level;
String difficulty = l.difficulty;
int[][] board = l.puzzle;

System.out.println("Level: " + level);
System.out.println("Difficulty: " + difficulty);

for (int i = 0; i < board.length; i++) {
    for (int j = 0; j < board.length; j++) {
        System.out.print(" "+board[i][j]);
    }
    System.err.println();
}


    }

}
