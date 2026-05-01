import java.awt.*;
import javax.swing.border.Border;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.event.*;

public class Only_Board extends JFrame implements ActionListener{

    JButton exitG, home, reset, save,track,hint;
    String lvl;
    String lvl1;
    public JTextField[][] textFields = new JTextField[9][9];
    JLabel score, lab1, timerHading, mode, timer;
    Color colorArr[]=new Color[2];
    boolean trackerEnabled = false;

    public Only_Board()
    {
        // Dispose Main Menu previous frames
        if (FrameManager.Main_Menu != null) FrameManager.Main_Menu.dispose();
        if (FrameManager.Game_Level != null) FrameManager.Game_Level.dispose();

        // Set Default Layout
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // Get Screen Size From System
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        System.out.println("Width: "+screenWidth);
        System.out.println("Height: "+screenHeight);

        // Overlay Panel as IMAGE BACKGROUND
        ImageIcon lob = new ImageIcon(ClassLoader.getSystemResource("image/cut_board.png"));
        Image p = lob.getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
        JLabel overlayPanel = new JLabel(new ImageIcon(p));
        overlayPanel.setLayout(new BorderLayout()); // IMPORTANT

        //===================== Heading Game Level ================================================================== 

        // Top Panel container
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        // Layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(screenWidth, screenHeight-((screenWidth/2))));

        // =====Level Background Image =====
        ImageIcon bgIcon = new ImageIcon(
                ClassLoader.getSystemResource("image/level show board.png"));
        Image bgImg = bgIcon.getImage().getScaledInstance(screenWidth/4, screenHeight-((screenWidth/2))+100, Image.SCALE_SMOOTH);
        JLabel bgLabel = new JLabel(new ImageIcon(bgImg));
        bgLabel.setBounds(screenWidth/2-(screenWidth/4)/2, -100, screenWidth/4, screenHeight-((screenWidth/2))+100);

        // ===== Level Text =====
        Font fontStylelvl = new Font("Eracake", Font.PLAIN,  screenWidth / 30);
        lab1 = new JLabel("Level", SwingConstants.CENTER);
        lab1.setFont(fontStylelvl);
        lab1.setForeground(Color.decode("#FFFDCE"));
        lab1.setBounds(screenWidth/2-450/2, -17, 450, 106);

        // ===== Add to layers =====
        layeredPane.add(bgLabel, Integer.valueOf(0)); // BACK
        layeredPane.add(lab1, Integer.valueOf(1)); // FRONT

        topPanel.add(layeredPane, BorderLayout.CENTER);
        overlayPanel.add(topPanel, BorderLayout.NORTH);


        //============================== Sudoku Text Board  Grid Panel ================================================

        // Center Panel for Sudoku Grid and Board Template
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        JPanel gridPanel = new JPanel(new GridLayout(9, 9, 3, 3));
        gridPanel.setOpaque(false);

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                textFields[row][col] = new JTextField();
                textFields[row][col].setHorizontalAlignment(JTextField.CENTER);
                textFields[row][col].setFont(new Font("Eracake", Font.PLAIN, 35));    
                textFields[row][col].setPreferredSize(new Dimension((screenWidth / 2) / 11 , (screenWidth / 2) / 11));
                gridPanel.add(textFields[row][col]);
            }
        }

        //Textfield Coloring
        
        colorArr[0] = Color.decode("#FFFDCE");
        colorArr[1] = Color.decode("#F7DB91");

        Border rightBorder = BorderFactory.createMatteBorder(0, 0, 0, 4, Color.BLACK);
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 4, 0, Color.BLACK);
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ((i / 3 + j / 3) % 2 == 0)
                    textFields[i][j].setBackground(colorArr[0]);
                else
                    textFields[i][j].setBackground(colorArr[1]);

                if (j == 2 || j == 5)
                    textFields[i][j].setBorder(rightBorder);
                if (i == 2 || i == 5)
                    textFields[i][j].setBorder(bottomBorder);
            }
        }
        textFields[2][2].setBorder(new MatteBorder(0, 0, 4, 4, Color.BLACK));
        textFields[2][5].setBorder(new MatteBorder(0, 0, 4, 4, Color.BLACK));
        textFields[5][2].setBorder(new MatteBorder(0, 0, 4, 4, Color.BLACK));
        textFields[5][5].setBorder(new MatteBorder(0, 0, 4, 4, Color.BLACK));

        centerPanel.add(gridPanel, gbc);
        overlayPanel.add(centerPanel, BorderLayout.CENTER);

        // Game Background Board adding
        try {
            ImageIcon lo = new ImageIcon(ClassLoader.getSystemResource("image/board3.png"));
            Image plo = lo.getImage().getScaledInstance(screenWidth/2-20 , screenWidth/2-20, Image.SCALE_SMOOTH);
            ImageIcon b = new ImageIcon(plo);
            JLabel lab2 = new JLabel(b);
            centerPanel.add(lab2, gbc);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //============================================ Button Section ===============================================

        // Right Panel For Button
        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // Color For Grid Text field
        Font fontStyle = new Font("Eracake", Font.PLAIN, 45);
        

        //================= Tracker Button =====================

        track = new JButton("Focus");
        track.setFont(new Font("Eracake", Font.PLAIN, 45));
        track.setMaximumSize(new Dimension(300, 60));
        track.setForeground(Color.decode("#423128"));
        track.setBackground(Color.decode("#FFFDCE"));
        track.setBorderPainted(false);
        track.setFocusPainted(false);
        track.setContentAreaFilled(true);
        track.addActionListener(this);
        
        // Hover effect for Next button
        track.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                if (trackerEnabled)
                {
                    track.setForeground(Color.decode("#423128"));
                    track.setBackground(Color.decode("#FFFDCE"));
                    return;
                }
                  
                track.setForeground(Color.decode("#FFFDCE"));
                track.setBackground(Color.decode("#423128"));

            }

            @Override
            public void mouseExited(MouseEvent e) {

                if (trackerEnabled)
                {
                    track.setForeground(Color.decode("#FFFDCE"));
                    track.setBackground(Color.decode("#423128"));
                    return; 
                }
                    

                track.setForeground(Color.decode("#423128"));
                track.setBackground(Color.decode("#FFFDCE"));

            }
        });

        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(track);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));


        //================== Save Button ======================

        save = new JButton("Save");
        save.setFont(fontStyle);
        save.setMaximumSize(new Dimension(300, 40));
        save.setForeground(Color.decode("#423128"));
        save.setBackground(Color.decode("#FFFDCE"));
        save.setBorderPainted(false);
        save.setFocusPainted(false);
        save.setContentAreaFilled(true);
        save.addActionListener(this);
        
        // Hover effect for Save button
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                save.setForeground(Color.decode("#FFFDCE"));  
                save.setBackground(Color.decode("#423128"));
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                save.setForeground(Color.decode("#423128")); 
                save.setBackground(Color.decode("#FFFDCE"));
            }
        });
        
        rightPanel.add(save);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        

        //================ Reset Button ========================

        reset = new JButton("Restart");
        reset.setFont(fontStyle);
        reset.setMaximumSize(new Dimension(300, 40));
        reset.setForeground(Color.decode("#423128"));
        reset.setBackground(Color.decode("#FFFDCE"));
        reset.setBorderPainted(false);
        reset.setFocusPainted(false);
        reset.setContentAreaFilled(true);
        reset.addActionListener(this);
        
        // Hover effect for Reset button
        reset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                reset.setForeground(Color.decode("#FFFDCE"));  
                reset.setBackground(Color.decode("#423128"));
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                reset.setForeground(Color.decode("#423128")); 
                reset.setBackground(Color.decode("#FFFDCE"));
            }
        });
        
        rightPanel.add(reset);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        

        //=================== Home Button =======================

        home = new JButton("Menu");
        home.setFont(fontStyle);
        home.setMaximumSize(new Dimension(300, 40));
        home.setForeground(Color.decode("#423128"));
        home.setBackground(Color.decode("#FFFDCE"));
        home.setBorderPainted(false);
        home.setFocusPainted(false);
        home.setContentAreaFilled(true);
        home.addActionListener(this);
        
        // Hover effect for Home button
        home.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                home.setForeground(Color.decode("#FFFDCE"));  
                home.setBackground(Color.decode("#423128"));
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                home.setForeground(Color.decode("#423128"));
                home.setBackground(Color.decode("#FFFDCE"));
            }
        });
        
        rightPanel.add(home);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        

        //================= Exit Game Button =======================

        exitG = new JButton("Quit Game");
        Font fontStyle2 = new Font("Eracake", Font.PLAIN, 45);
        exitG.setFont(fontStyle2);
        exitG.setMaximumSize(new Dimension(300, 45));
        exitG.setBackground(Color.decode("#FFFDCE")); 
        exitG.setForeground(Color.red);
        exitG.setBorderPainted(false);
        exitG.setFocusPainted(false);
        exitG.setContentAreaFilled(true);
        exitG.addActionListener(this);
        
        // Hover effect for Exit Game button
        exitG.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitG.setForeground(Color.decode("#FFFDCE"));  
                exitG.setBackground(Color.decode("#423128"));
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                exitG.setBackground(Color.decode("#FFFDCE")); 
                exitG.setForeground(Color.red); 
            }
        });
        
        rightPanel.add(exitG);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        rightPanel.add(Box.createVerticalGlue());


        //=================== Hint Button =======================

        hint = new JButton("HINT");
        hint.setFont(fontStyle);
        hint.setMaximumSize(new Dimension(350, 40));
        hint.setForeground(Color.decode("#423128"));
        hint.setBackground(Color.decode("#FFFDCE"));
        hint.setBorderPainted(false);
        hint.setFocusPainted(false);
        hint.setContentAreaFilled(true);
        hint.addActionListener(this);
        
        // Hover effect for Home button
        hint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hint.setForeground(Color.decode("#FFFDCE"));  
                hint.setBackground(Color.decode("#423128"));
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                hint.setForeground(Color.decode("#423128"));
                hint.setBackground(Color.decode("#FFFDCE"));
            }
        });


        //==================================== Message Section =======================================================

        // Layered pane
        JLayeredPane layeredPane1 = new JLayeredPane();
        layeredPane1.setLayout(null);

        // =====messeage Background Image =====
        ImageIcon bgIcon1 = new ImageIcon(ClassLoader.getSystemResource("image/message board.png"));
        Image bgImg1 = bgIcon1.getImage().getScaledInstance(250, 170, Image.SCALE_SMOOTH);
        JLabel bgLabel1 = new JLabel(new ImageIcon(bgImg1));
        bgLabel1.setBounds(0, 0, 250,170);

        // ===== Message Text =====
        Font fontStylelvl1 = new Font("Eracake", Font.PLAIN, 35);
        JLabel lab11 = new JLabel("Good job !", SwingConstants.CENTER);
        lab11.setFont(fontStylelvl1);
        lab11.setForeground(Color.decode("#5F361A"));
        lab11.setBounds(25, 0, 200, 170);

        // ===== Add to layers =====
        layeredPane1.add(bgLabel1, Integer.valueOf(0)); // BACK
        layeredPane1.add(lab11, Integer.valueOf(1)); // FRONT
        rightPanel.add(layeredPane1);

        
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
   

        overlayPanel.add(rightPanel, BorderLayout.EAST);


        //================================== Timer Hints Section & Left Panel ===========================================

        // Creating Left Panel
        JPanel LeftPanel = new JPanel();
        LeftPanel.setOpaque(false);
        LeftPanel.setLayout(new BoxLayout(LeftPanel, BoxLayout.Y_AXIS));
        LeftPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 40, 0));

        //  Fix the size
        Dimension fixedSize = new Dimension(screenWidth / 6+15, screenHeight);
        LeftPanel.setPreferredSize(fixedSize);
        LeftPanel.setMinimumSize(fixedSize);
        LeftPanel.setMaximumSize(fixedSize);

        LeftPanel.add(Box.createRigidArea(new Dimension(0, 20)));


        // Play Mode (Easy / Medium / Hard)
        Font fontStylell = new Font("Eracake", Font.PLAIN, 45);
        Font fontStylel = new Font("Eracake", Font.PLAIN, 35);
        JLabel modeLevel = new JLabel("Level Mode");
        modeLevel.setFont(fontStylel);
        modeLevel.setForeground(Color.decode("#F7DB91"));
        LeftPanel.add(modeLevel);

        //Text Area For Scores
        mode=new JLabel("--------");
        mode.setFont(fontStylell);
        mode.setForeground(Color.white);
        LeftPanel.add(mode);
        LeftPanel.add(Box.createRigidArea(new Dimension(0, 35)));

        // Game Score heading
        JLabel lob1 = new JLabel("Scores");
        lob1.setFont(fontStylel);
        lob1.setForeground(Color.decode("#F7DB91"));
        LeftPanel.add(lob1);

        //Text Area For Scores
        score=new JLabel("000");
        score.setFont(fontStylell);
        score.setForeground(Color.white);
        LeftPanel.add(score);
        LeftPanel.add(Box.createRigidArea(new Dimension(0, 35)));

        // Timer heading
        timerHading = new JLabel("Timer: ");
        timerHading.setFont(fontStylel);
        timerHading.setForeground(Color.decode("#F7DB91"));
        LeftPanel.add(timerHading);

        // timer adding
        timer = new JLabel("00:00:00");
        timer.setFont(fontStylell);
        timer.setForeground(Color.white);
        LeftPanel.add(timer);

        // Hint Button Adding in the left panel
        LeftPanel.add(Box.createVerticalGlue());

        // Thinking Gif adding
        ImageIcon Loo = new ImageIcon(ClassLoader.getSystemResource("image/thinking.gif"));
        Image ll = Loo.getImage().getScaledInstance(130, 130,0);
        ImageIcon aa = new ImageIcon(ll);
        JLabel labb = new JLabel(aa);
        LeftPanel.add(labb);

        LeftPanel.add(hint);



        overlayPanel.add(LeftPanel, BorderLayout.WEST);

        add(overlayPanel, BorderLayout.CENTER);
        pack(); // Automatically sizes the frame to fit its components
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255,126,0));
        setBounds(0,0,screenWidth,screenHeight); 
        setVisible(true);
    }


    ////////////////////////////////===================== Button Action Function ================///////////////////////////////////////////////////////////////
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new Only_Board();
    }

    
}
