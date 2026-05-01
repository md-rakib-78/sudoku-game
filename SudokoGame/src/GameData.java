import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameData {

    private int level;
    private int score;
    private int remainingTime;  
    private String timerMode;    
    private int useHint;         
    private int[][] playboard;

    // Constructor
    public GameData(int level, int score, int remainingTime,String timerMode, int useHint, int[][] playboard) {

        this.level = level;
        this.score = score;
        this.remainingTime = remainingTime;
        this.timerMode = timerMode;
        this.useHint = useHint;
        this.playboard = playboard;
    }

    // ================= SAVE (overwrite) =================
    public void saveToFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("savegame.json", false)) {
            gson.toJson(this, writer);
            System.out.println("Game Saved Successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ================= LOAD =================
    public static GameData loadFromFile() {

        File file = new File("savegame.json");

        if (!file.exists()) {
            System.out.println("No saved game found!");
            return null;
        }

        Gson gson = new Gson();

        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, GameData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= GETTERS =================
    public int getLevel() { return level; }
    public int getScore() { return score; }
    public int getRemainingTime() { return remainingTime; }
    public String getTimerMode() { return timerMode; }
    public int getUseHint() { return useHint; }
    public int[][] getPlayboard() { return playboard; }
}
