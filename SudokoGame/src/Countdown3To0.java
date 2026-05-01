import java.awt.*;
import javax.swing.*;

public class Countdown3To0 extends Only_Board {

    private int seconds = 3;
    private String time = "3";
    private Timer timer;
    private boolean finished = false;

    private static int levelpop;
    private static String timerMode;

    public Countdown3To0(int lvl,String md,boolean savee) {

        levelpop=lvl;
        timerMode=md;

        // ================= Popup UI =================
        Pop_For_Start_Timer obj = new Pop_For_Start_Timer();

        // ================= Countdown Timer =================
        timer = new Timer(1000, e -> {
            time = String.valueOf(seconds);
            seconds--;

            if (seconds < 0) {
                timer.stop();
                time = "Start !";
                finished = true;
            }
        });

        // ✅ NOW timer exists, safe to start
        start();

        new javax.swing.Timer(100, e -> {

            Font fontle = new Font("Eracake", Font.PLAIN, obj.screenWidth / 11);

            obj.boardLabel.setText(getTime());
            obj.boardLabel.setForeground(Color.WHITE);
            obj.boardLabel.setFont(fontle);

            obj.boardLabel.setBounds(0, 0, obj.screenWidth, obj.screenHeight);
            obj.boardLabel.setHorizontalAlignment(SwingConstants.CENTER);
            obj.boardLabel.setVerticalAlignment(SwingConstants.CENTER);

            System.out.println("time: " + getTime());

            if (isFinished()) {
                ((javax.swing.Timer) e.getSource()).stop();

                new javax.swing.Timer(1000, ev -> {
                    obj.dispose();
                    new Play(levelpop,timerMode,savee);
                    dispose();
                    
                    ((javax.swing.Timer) ev.getSource()).stop();
                }).start();
               
            }

        }).start();
    }

    public void start() {
        seconds = 3;
        time = "3";
        finished = false;
        timer.start(); 
    }

    public String getTime() {
        return time;
    }

    public boolean isFinished() {
        return finished;
    }
}
