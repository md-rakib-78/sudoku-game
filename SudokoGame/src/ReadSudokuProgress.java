import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class ReadSudokuProgress {

    static class LevelProgress {
        int level;
        boolean levelOpen;
        boolean completed;
        String gameMode;
        String timeMode;
        int score;
        int finishedTimeSeconds;
        int usedHints;
        int stars;
        String playedAt;
    }

    static class ProgressWrapper {
        List<LevelProgress> progress;
    }

    private static final String FILE_NAME = "sudoku_progress.json";

    // 🔹 STORE progress here
    private List<LevelProgress> progressList;

    public ReadSudokuProgress() {

        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return;

            Gson gson = new Gson();
            FileReader reader = new FileReader(file);

            Type type = new TypeToken<ProgressWrapper>() {}.getType();
            ProgressWrapper data = gson.fromJson(reader, type);
            reader.close();

            if (data == null || data.progress == null) return;

            // 🔹 save instead of only printing
            progressList = data.progress;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 Getter (THIS is what other classes use)
    public List<LevelProgress> getProgressList() {
        return progressList;
    }
}
