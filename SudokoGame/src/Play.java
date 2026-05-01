import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



public class Play extends Only_Board {

    private int currentRow;
    private int currentCol;
    private int cRow = -1;
    private int cCol = -1;
    private int useHint=0;
    private String timerMode;
    private static boolean hitButton = false;
    static boolean checkLis = false;

    Timer hintBlinkTimer;
    static String start="";

    private int orginalBoard [][];
    private int playerBoard [][];
    private int solutionBoard [][];

    private int level;
    private String difficultyLevel;
    private CountdownTimer timerEasy;
    private CountdownTimer timerMid;
    private CountdownTimer timerHard;


    public Play(int lvl,String md,boolean savee) {
        super();

        FrameManager.PLAY = this;

        // ==================================== Row Data Read From JSON =====================================

        playerBoard = new int[9][9];

        if(savee == true)
        {
            GameData loaded = GameData.loadFromFile();

            useHint = loaded.getUseHint();
            timerMode = loaded.getTimerMode();
            level = loaded.getLevel();

            // Sudoku Level JSON file read class access
            List<SudokuData> levels = SudokuData.readAllLevels();

            // any level we want
            SudokuData l = levels.get(level-1);
            difficultyLevel = l.difficulty;
            orginalBoard = l.puzzle;
            playerBoard = loaded.getPlayboard();

            //Timer Set base on timer mode 
            if(timerMode.equals("easy")){

                timerEasy = new CountdownTimer(loaded.getRemainingTime()*1000,true);
            }
            else if (timerMode.equals("medium")) {
                timerMid = new CountdownTimer(loaded.getRemainingTime()*1000,true);
            }
            else if (timerMode.equals("hard")) {
                timerHard = new CountdownTimer(loaded.getRemainingTime()*1000,true); 
            }

            // =================================== Insert Value into text field ====================================
            boolean check = false;

            for (int r = 0; r < orginalBoard.length; r++) {
                for (int c = 0; c < orginalBoard.length; c++) {
                    if (orginalBoard[r][c] != 0) {
                        textFields[r][c].setText(String.valueOf(orginalBoard[r][c]));
                        textFields[r][c].setEditable(false);
                    } else {

                        if(playerBoard[r][c] != 0)
                        {
                            textFields[r][c].setText(String.valueOf(playerBoard[r][c]));

                            String text = textFields[r][c].getText();

                            int value = Integer.parseInt(text);

                            // Invalid Sudoku move
                            if (!isSafe(textFields, playerBoard, r, c, value)) {
                                textFields[r][c].setForeground(Color.RED);
                            }

                        }
                        else
                        {
                            textFields[r][c].setText("");

                            if (check == false) {
                                currentRow = r;
                                currentCol = c;
                                check = true;
                        }
                        }

                        textFields[r][c].setEditable(true);

                    }

                }
            }// Loop Close


        }

        else
        {
            // Timer Mode Initialize (Easy, medium, hard)
            timerMode = md;

            // Sudoku Level JSON file read class access
            List<SudokuData> levels = SudokuData.readAllLevels();

            // any level you want
            SudokuData l = levels.get(lvl - 1);

            // Initialize JSON value
            level = l.level;
            difficultyLevel = l.difficulty;
            orginalBoard = l.puzzle;

            // ================= Init Boards =================
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    playerBoard[r][c] = orginalBoard[r][c];
                }
            }

            //Timer Set base on timer mode 
            timerEasy = new CountdownTimer(1);
            timerMid = new CountdownTimer(2); 
            timerHard = new CountdownTimer(3); 

            // =================================== Insert Value into text field ====================================
            boolean check = false;

            for (int r = 0; r < orginalBoard.length; r++) {
                for (int c = 0; c < orginalBoard.length; c++) {
                    if (orginalBoard[r][c] != 0) {
                        textFields[r][c].setText(String.valueOf(orginalBoard[r][c]));
                        textFields[r][c].setEditable(false);
                    } else {
                        textFields[r][c].setText("");
                        textFields[r][c].setEditable(true);

                        if (check == false) {
                            currentRow = r;
                            currentCol = c;
                            check = true;
                        }

                    }

                }
            }
        }



        // Get Solution 
        solutionBoard = SudokuSolver.solve(orginalBoard);

        // Adding Exact Level Heading
        lab1.setText("Level "+level);
    
        
        // Adding Level Difficulty
        if(difficultyLevel.equalsIgnoreCase("easy"))
        {
            mode.setText(difficultyLevel);
            mode.setForeground(Color.white);
        }
        else if (difficultyLevel.equalsIgnoreCase("medium")) {
            mode.setText(difficultyLevel);
            mode.setForeground(Color.orange);
        }
        else if (difficultyLevel.equalsIgnoreCase("hard")) {
            mode.setText(difficultyLevel);
            mode.setForeground(Color.RED);
        }
 

        // Activate Timer Mode And Start Time

        if(timerMode.equals("easy"))
        {
            
            timerHading.setText("<html>Timer: <font color='white'>" + timerMode + "</font></html>");

            Timer uiTimer = new Timer(50, e -> {
                timer.setText(timerEasy.getTimeString());
            });

            uiTimer.start();
            timerEasy.start();

        }
        else if(timerMode.equals("medium") )
        {
            timerHading.setText("<html>Timer: <font color='orange'>" + timerMode + "</font></html>");

            timerMid.start();

            new javax.swing.Timer(50, e -> {
                timer.setText(timerMid.getTimeString());
            }).start();

        }
        else if(timerMode.equals("hard") )
        {
            timerHading.setText("<html>Timer: <font color='red'>" + timerMode + "</font></html>");

            timerHard.start();

            new javax.swing.Timer(50, e -> {
                timer.setText(timerHard.getTimeString());
            }).start();

        }



        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                int row = i;
                int col = j;

                    
                //=============================== Highlight Cell ============================================

                    textFields[row][col].addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {

                            currentRow = row;
                            currentCol = col;
                            cRow=row;
                            cCol=col;

                            if (!trackerEnabled) return;  // tracker OFF → do nothing

                            Color c1 = Color.decode("#BBC863");
                            Color c2 = Color.decode("#EBF4DD");

                            // Restore all colors when focus leaves
                            for (int i = 0; i < 9; i++) {
                                for (int j = 0; j < 9; j++) {
                                    textFields[i][j].setOpaque(true);
                                    if ((i / 3 + j / 3) % 2 == 0)
                                        textFields[i][j].setBackground(colorArr[0]);
                                    else
                                        textFields[i][j].setBackground(colorArr[1]);
                                }
                            }


                            // Highlight column
                            for (int k = 0; k < 9; k++) {
                                textFields[k][col].setBackground(c1);
                            }

                            // Highlight row
                            for (int p = 0; p < 9; p++) {
                                textFields[row][p].setBackground(c1);
                            }

                            // Selected cell
                            textFields[row][col].setBackground(c2);
                        }


                        @Override
                        public void focusLost(FocusEvent e) {

                            if (!trackerEnabled) return; // tracker OFF → no action

                            // Restore all colors when focus leaves
                            for (int i = 0; i < 9; i++) {
                                for (int j = 0; j < 9; j++) {
                                    textFields[i][j].setOpaque(true);
                                    if ((i / 3 + j / 3) % 2 == 0)
                                        textFields[i][j].setBackground(colorArr[0]);
                                    else
                                        textFields[i][j].setBackground(colorArr[1]);
                                }
                            }

                        }
                    });


            //========================================= sudoku validation when user input and check solution ========================

                textFields[i][j].addKeyListener(new KeyAdapter() {

                    @Override
                    public void keyReleased(KeyEvent e) {

                        if (!textFields[row][col].isEditable()) return;

                        String text = textFields[row][col].getText();

                        // Allow empty (erase)
                        if (text.isEmpty()) {
                            playerBoard[row][col] = 0;
                            textFields[row][col].setForeground(Color.BLACK);
                            return;
                        }

                        // More than one digit
                        if (text.length() > 1) {
                            textFields[row][col].setText("");
                            return;
                        }

                        // Not a number
                        if (!text.matches("[1-9]")) {
                            textFields[row][col].setText("");
                            return;
                        }

                        int value = Integer.parseInt(text);

                        // Invalid Sudoku move
                        if (!isSafe(textFields,playerBoard, row, col, value)) {
                            textFields[row][col].setForeground(Color.RED);
                            playerBoard[row][col] = value;
                            return;
                        } 

                        playerBoard[row][col] = value;
                        textFields[row][col].setForeground(Color.BLACK);
                        
                        //checkWin();
                    }
                });
            }
        }


        //================================= Hint button action and generate hint =====================================

        hint.addActionListener(e -> {

            useHint = useHint + 1;
            hitButton = true;

            if (hitButton = true) {

                if (timerMode.equals("easy") && useHint <= 5) {
                    HintResult h = SudokuHint.getHint(cRow, cCol, playerBoard, solutionBoard);

                    if (h == null) {
                        // Need work here
                        JOptionPane.showMessageDialog(this, "No hints available!");
                        return;
                    }

                    int r = h.row;
                    int c = h.col;
                    int value = h.value;

                    blinkUntilInteraction(value, textFields, r, c);

                    playerBoard[r][c] = value;

                }

                else if (timerMode.equals("medium") && useHint <= 3) {

                    HintResult h = SudokuHint.getHint(cRow, cCol, playerBoard, solutionBoard);

                    if (h == null) {
                        JOptionPane.showMessageDialog(this, "No hints available!");
                        return;
                    }

                    int r = h.row;
                    int c = h.col;
                    int value = h.value;

                    blinkUntilInteraction(value, textFields, r, c);

                    playerBoard[r][c] = value;
                }

                else if (timerMode.equals("hard") && useHint <= 2) {

                    HintResult h = SudokuHint.getHint(cRow, cCol, playerBoard, solutionBoard);

                    if (h == null) {
                        JOptionPane.showMessageDialog(this, "No hints available!");
                        return;
                    }

                    int r = h.row;
                    int c = h.col;
                    int value = h.value;

                    blinkUntilInteraction(value, textFields, r, c);

                    playerBoard[r][c] = value;
                } else {
                    // Need to work

                    JOptionPane.showMessageDialog(this, "No hints available!");
                }

            }

        });



        //================================== Higlight cell focus button control ======================================

        track.addActionListener(e -> {

            if(hitButton == false)
            {

                trackerEnabled = !trackerEnabled; // toggle ON/OFF

                if (trackerEnabled) {
                    track.setForeground(Color.decode("#FFFDCE"));
                    track.setBackground(Color.decode("#423128"));

                    // Force refresh highlight on currently selected cell
                    textFields[currentRow][currentCol].requestFocusInWindow();


                    



                } else {
                    track.setForeground(Color.decode("#423128"));
                    track.setBackground(Color.decode("#FFFDCE"));

                    // remove all highlights
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            if ((i / 3 + j / 3) % 2 == 0)
                                textFields[i][j].setBackground(colorArr[0]);
                            else
                                textFields[i][j].setBackground(colorArr[1]);
                        }
                    }

                }

            }

        });


        //================================== Higlight cell focus button control ======================================

        save.addActionListener(e -> {

            // Convert milliseconds to seconds
            int remainingSeconds = (int) (timerEasy.getRemainingMillis() / 1000);

            GameData data = new GameData(
                    level,
                    450,
                    remainingSeconds,
                    timerMode,
                    useHint,
                    playerBoard);

            data.saveToFile();
        });

    }



///////////////////////////////////////////// ==================== Text Field Blinking For hint ================== ///////////////////////////////////
    
public void blinkUntilInteraction(int value,JTextField[][] textFields, int r, int c) {

    Color blinkColor = Color.decode("#A3E635");
    Color originalColor = textFields[r][c].getBackground();

    Timer blinkTimer = new Timer(350, null);

    blinkTimer.addActionListener(e -> {
        if (textFields[r][c].getBackground().equals(blinkColor)) {
            textFields[r][c].setBackground(originalColor);
        } else {
            textFields[r][c].setBackground(blinkColor);
            textFields[r][c].setText(String.valueOf(value));
            textFields[r][c].setForeground(Color.GRAY);
            textFields[r][c].setEditable(false);

        }
    });
    blinkTimer.start();

    // STOP when ANY cell gains focus

    textFields[r][c].addFocusListener(new FocusAdapter() {

        @Override
        public void focusGained(FocusEvent e) {

            hitButton = false;
            blinkTimer.stop();

            textFields[r][c].setOpaque(true);
            textFields[r][c].setBackground(originalColor);
            textFields[r][c].setText(String.valueOf(value));
            textFields[r][c].setForeground(Color.black);
            textFields[r][c].setEditable(false);
        }
    });

    //checkLis = false;

}


///////////////////////////////////////////// ==================== Text Field Blinking For Mistake  ================== ///////////////////////////////////
    
public static void blinkMistake(int value,JTextField[][] textFields, int mr, int mc,int rro, int rco) {

    Color blinkColor = Color.decode("#F5824A");
    Color originalColor = textFields[mr][mc].getBackground();

    Timer blinkTimer = new Timer(350, null);

    blinkTimer.addActionListener(e -> {
        if (textFields[mr][mc].getBackground().equals(blinkColor)) {
            textFields[mr][mc].setBackground(originalColor);
        } else {
            textFields[mr][mc].setBackground(blinkColor);
        }
    });
    blinkTimer.start();


    // STOP when correct value or clear the cell
    textFields[rro][rco].addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            blinkTimer.stop();
            textFields[mr][mc].setBackground(originalColor);
        }
    });

}



    ////////////////////////////////////////////// =================== Instant Textfield value check ================///////////////////////////////////////

    public static boolean isSafe(JTextField[][] textFields,int[][] board, int row, int col, int num) {

        // Check box
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int r = startRow + i;
                int c = startCol + j;

                if ((r != row || c != col) && board[r][c] == num)
                {
                    blinkMistake(num,textFields, r, c,row,col);
                    return false;
                }
            }
        }

        // Check row
        for (int x = 0; x < 9; x++) {
            if (x != col && board[row][x] == num)
            {
                blinkMistake(num,textFields, row, x,row,col);
                return false;
            }
        }

        // Check column
        for (int x = 0; x < 9; x++) {
            if (x != row && board[x][col] == num)
            {
                blinkMistake(num,textFields, x, col,row,col);
                return false;
            }
        }

        return true;
    }



////////////////////////////////////////////// =================== Instant win check ================///////////////////////////////////////
    private void checkWin() {

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {

                if (playerBoard[r][c] != solutionBoard[r][c]) {
                    return;
                }
            }
        }

        onWin();
    }


      ////////////////////////////////////////////// =================== After win stop time and level up ================///////////////////////////////////////
    private void onWin() {

        //timer.stop();

        //JOptionPane.showMessageDialog(this,"Level Complete!\nStars: " + scoreManager.getStars());
    }



      ////////////////////////////////////////////// =================== Button Action ================///////////////////////////////////////

    @Override
    public void actionPerformed(ActionEvent e)
    {

        sound_effect sound = new sound_effect();

        if(e.getSource()==save)
        {

        }

        else if(e.getSource() == reset)
        {
            new Thread(() -> sound.playSound("button.wav")).start();
            new Countdown3To0(level,timerMode,false);
            dispose();
        }

        else if(e.getSource()==home)
        {
            new Thread(() -> sound.playSound("button.wav")).start();
            new mainMenu();
            dispose();
        }
        else if(e.getSource() == exitG)
        {
            new Thread(() -> sound.playSound("button.wav")).start();
            System.exit(0);
        }

    }



    public static void main(String args[]) {
        new Play(1,"easy",false);
    }
}
