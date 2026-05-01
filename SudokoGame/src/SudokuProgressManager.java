import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SudokuProgressManager {

    // Represents ONE level progress
    static class LevelProgress {
        int level;
        boolean levelOpen;
        String gameMode;
        String timeMode;
        boolean completed;
        int score;
        int finishedTimeSeconds;
        int usedHints;
        int stars;
        String playedAt;
    }

    private static final String FILE_NAME = "sudoku_progress.json";
    private static final Gson gson =
            new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {

        saveOrUpdateLevel(1,true,"","", false, 0,0,0,0,"");
    }

    //  MAIN METHOD YOU WILL CALL FROM GAME
    public static void saveOrUpdateLevel(int level, boolean levelOpen, String gameMode, String timeMode,  boolean completed, int score, int finishedTimeSeconds, int usedHints, int stars, String playedAt)
     {

        try {
            List<LevelProgress> progressList = loadProgress();

            boolean updated = false;

            for (int i = 0; i < progressList.size(); i++) {
                if (progressList.get(i).level == level) {
                    progressList.set(i, createLevel(
                            level,levelOpen, gameMode, timeMode, completed,
                            score, finishedTimeSeconds,
                            usedHints, stars, playedAt
                    ));
                    updated = true;
                    break;
                }
            }

            if (!updated) {
                progressList.add(createLevel(
                        level,levelOpen, gameMode, timeMode, completed,
                        score, finishedTimeSeconds,
                        usedHints, stars, playedAt
                ));
            }

            writeProgress(progressList);

            System.out.println(updated
                    ? " Level " + level + " updated"
                    : " Level " + level + " added");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------- Helper Methods ----------

    private static LevelProgress createLevel(
            int level,
            boolean levelOpen,
            String gameMode,
            String timeMode,
            boolean completed,
            int score,
            int finishedTimeSeconds,
            int usedHints,
            int stars,
            String playedAt
    ) {
        LevelProgress lp = new LevelProgress();
        lp.level = level;
        lp.levelOpen = levelOpen;
        lp.gameMode = gameMode;
        lp.timeMode = timeMode;
        lp.completed = completed;
        lp.score = score;
        lp.finishedTimeSeconds = finishedTimeSeconds;
        lp.usedHints = usedHints;
        lp.stars = stars;
        lp.playedAt = playedAt;
        return lp;
    }

    private static List<LevelProgress> loadProgress() throws Exception {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        FileReader reader = new FileReader(file);

        Type type = new TypeToken<ProgressWrapper>() {}.getType();
        ProgressWrapper wrapper = gson.fromJson(reader, type);
        reader.close();

        if (wrapper == null || wrapper.progress == null) {
            return new ArrayList<>();
        }

        return wrapper.progress;
    }

    private static void writeProgress(List<LevelProgress> progress)
            throws Exception {

        ProgressWrapper wrapper = new ProgressWrapper();
        wrapper.progress = progress;

        FileWriter writer = new FileWriter(FILE_NAME);
        gson.toJson(wrapper, writer);
        writer.close();
    }

    // Wrapper for JSON root
    static class ProgressWrapper {
        List<LevelProgress> progress;
    }
}
