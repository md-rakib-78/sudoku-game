import javax.swing.Timer;

public class CountdownTimer {

    private long remainingMillis;
    private long endTime;
    private Timer timer;
    private String timeString = "00:00:00";
    private boolean running = false;

    // 🔹 New Game Constructor (minutes input)
    public CountdownTimer(int minutes) {
        this.remainingMillis = minutes * 60L * 1000L;
        initTimer();
        updateTimeString();
    }

    // 🔹 Resume Constructor (milliseconds input)
    public CountdownTimer(long remainingMillis, boolean resumeMode) {
        this.remainingMillis = remainingMillis;
        initTimer();
        updateTimeString();
    }

    // 🔹 Initialize Swing Timer
    private void initTimer() {
        timer = new Timer(10, e -> {

            if (running) {
                remainingMillis = endTime - System.currentTimeMillis();

                if (remainingMillis <= 0) {
                    remainingMillis = 0;
                    running = false;
                    timer.stop();
                }

                updateTimeString();
            }
        });
    }

    // 🔹 Start / Resume
    public void start() {
        if (!running && remainingMillis > 0) {
            endTime = System.currentTimeMillis() + remainingMillis;
            running = true;
            timer.start();
        }
    }

    // 🔹 Pause
    public void pause() {
        if (running) {
            remainingMillis = endTime - System.currentTimeMillis();
            running = false;
            timer.stop();
        }
    }

    // 🔹 Reset with new minutes
    public void reset(int minutes) {
        pause();
        remainingMillis = minutes * 60L * 1000L;
        updateTimeString();
    }

    // 🔹 Add or subtract seconds
    public void addSeconds(int seconds) {
        remainingMillis += seconds * 1000L;

        if (remainingMillis < 0) {
            remainingMillis = 0;
        }

        if (running) {
            endTime = System.currentTimeMillis() + remainingMillis;
        }

        updateTimeString();
    }

    // 🔹 Format time (MM:SS:MS)
    private void updateTimeString() {
        long minutes = remainingMillis / 60000;
        long seconds = (remainingMillis % 60000) / 1000;
        long millis  = (remainingMillis % 1000) / 10;

        timeString = String.format("%02d:%02d:%02d", minutes, seconds, millis);
    }

    public String getTimeString() {
        return timeString;
    }

    public boolean isFinished() {
        return remainingMillis <= 0;
    }

    // 🔥 Important for saving game state
    public long getRemainingMillis() {
        return remainingMillis;
    }

    public boolean isRunning() {
        return running;
    }
}