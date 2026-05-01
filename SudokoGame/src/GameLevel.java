import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameLevel extends JFrame implements ActionListener {

    private static final int TOTAL_LEVELS = 300;

    JButton[] levelButtons = new JButton[TOTAL_LEVELS];
    boolean[] levelComplete = new boolean[TOTAL_LEVELS];
    boolean[] levelOpen = new boolean[TOTAL_LEVELS];
    JButton close;

    int screenWidth;
    int screenHeight;
    int buttonWidth;
    int buttonHeight;
    int fontSize;

    public GameLevel() {

        setUndecorated(true);


        // Frame Dispose Control
        FrameManager.Game_Level = this;


        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = d.width;
        screenHeight = d.height;

        // ================= BACKGROUND =================
        BackgroundPanel background = new BackgroundPanel();
        background.setLayout(new BorderLayout());
        setContentPane(background);

        // ================= HEADER =================
        background.add(createHeader(), BorderLayout.NORTH);

        // ================= LEVEL GRID =================
        JScrollPane scrollPane = createScrollableLevels();
        background.add(scrollPane, BorderLayout.CENTER);

        //================== LOWER PANEL AND CLOSE BUTTON =======
        background.add(createFotter(),BorderLayout.SOUTH);


        // ================= FRAME =================
        setSize(screenWidth, screenHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    // =====================================================
    // FOTTER
    // =====================================================
    private JPanel createFotter() {

        JPanel fotter = new JPanel(new BorderLayout());
        fotter.setOpaque(false);
        fotter.setPreferredSize(new Dimension(screenWidth, screenHeight / 7));

        int buttonWidth = 100;
        int buttonHeight = 40;

        JLayeredPane lp = new JLayeredPane();
        lp.setPreferredSize(new Dimension(screenWidth, screenHeight / 7 -15));
        lp.setLayout(null);

        //====== Close Button =====
        close = new JButton("Close");
        close.setFont(new Font("Eracake", Font.PLAIN,20));
        close.setBounds(screenWidth/2 - buttonWidth/2, (screenHeight/7-15)/2 - buttonHeight/2, buttonWidth, buttonHeight);
        close.setBackground(Color.darkGray);
        close.setForeground(Color.decode("#ffffff"));
        close.setBorderPainted(false);
        close.setFocusPainted(false);
        close.setContentAreaFilled(true);


        // Hover effect for text color and border
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                close.setForeground(Color.decode("#ff0707"));
                close.setBackground(Color.decode("#FFFDCE"));
                //close.setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                close.setForeground(Color.decode("#ffffff"));
                close.setBackground(Color.darkGray);
                //close.setBorderPainted(true);
            }
        });

        lp.add(close,JLayeredPane.DEFAULT_LAYER);

        fotter.add(lp, BorderLayout.CENTER);

        sound_effect sound = new sound_effect();

        close.addActionListener(e -> {

            new Thread(() -> sound.playSound("button.wav")).start();
            new mainMenu();
            dispose(); 
        });
        
        return fotter;
    }




    // =====================================================
    // HEADER
    // =====================================================
    private JPanel createHeader() {

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setPreferredSize(new Dimension(screenWidth, screenHeight / 6));

        int headerW = screenWidth / 4;
        int headerH = screenHeight / 6 - 15;

        JLayeredPane lp = new JLayeredPane();
        lp.setPreferredSize(new Dimension(screenWidth, headerH));
        lp.setLayout(null);

        ImageIcon boardIcon = new ImageIcon(
                ClassLoader.getSystemResource("image/level show board.png")
        );
        Image boardImg = boardIcon.getImage()
                .getScaledInstance(headerW - (headerH/4), headerH, Image.SCALE_SMOOTH);

        JLabel board = new JLabel(new ImageIcon(boardImg));
        board.setBounds(screenWidth / 2 - (headerW - (headerH/4))/ 2, 0, headerW - (headerH/4), headerH);

        JLabel title = new JLabel("GAME LEVEL", JLabel.CENTER);
        title.setFont(new Font("Eracake", Font.PLAIN, screenWidth / 37));
        title.setForeground(Color.decode("#FFFDCE"));
        title.setBounds(screenWidth / 2 - headerW / 2, 0, headerW, headerH);

        lp.add(board, JLayeredPane.DEFAULT_LAYER);
        lp.add(title, JLayeredPane.PALETTE_LAYER);

        header.add(lp, BorderLayout.CENTER);
        return header;
    }

    // =====================================================
    // SCROLLABLE LEVEL GRID
    // =====================================================
    private JScrollPane createScrollableLevels() {

        // Responsive sizes
        int columns = screenWidth > 1400 ? 4 : 3;
        buttonWidth = screenWidth / (columns + 2);
        buttonHeight = screenHeight / 6;
        fontSize = screenWidth / 45;

        int rows = (int) Math.ceil(TOTAL_LEVELS / (double) columns);

        JPanel levelPanel = new JPanel(new GridLayout(rows, columns,screenWidth / 60,screenHeight / 40));
        levelPanel.setOpaque(false);
        levelPanel.setBorder(BorderFactory.createEmptyBorder(10, 80, 10, 80));

        loadCompletedLevels();

        for (int i = 0; i < TOTAL_LEVELS; i++) {
            boolean unlocked = levelOpen[i];
            levelButtons[i] = createLevelButton(i+1, unlocked);
            levelPanel.add(levelButtons[i]);
        }

        levelPanel.setPreferredSize(new Dimension(screenWidth,rows * (buttonHeight + screenHeight / 30)));

        JScrollPane scrollPane = new JScrollPane(levelPanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // ===== HIDE SCROLL UI =====
        JScrollBar vBar = scrollPane.getVerticalScrollBar();
        vBar.setPreferredSize(new Dimension(0, 0));
        vBar.setUnitIncrement(40);      // NORMAL scroll speed
        vBar.setBlockIncrement(150);   // FAST scroll (PageDown)

        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        return scrollPane;
    }

    // =====================================================
    // BUTTON CREATION
    // =====================================================
    private JButton createLevelButton(int level, boolean unlocked) {

        JButton b = new JButton("Level " + String.format("%02d", level));
        b.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        b.setFont(new Font("Eracake", Font.PLAIN, fontSize));
        b.setFocusPainted(false);
        b.setHorizontalTextPosition(SwingConstants.CENTER);
        b.setVerticalTextPosition(SwingConstants.BOTTOM);
        b.setContentAreaFilled(false);
        Color accent = new Color(254, 113, 21);
        b.setForeground(Color.darkGray);
        b.setBorder(BorderFactory.createLineBorder(accent, 0));

        if (!unlocked) {
            ImageIcon lockIcon = new ImageIcon(ClassLoader.getSystemResource("image/l_l_lock.png"));
            Image img = lockIcon.getImage().getScaledInstance(buttonWidth / 4,buttonHeight / 2,Image.SCALE_SMOOTH);
            b.setIcon(new ImageIcon(img));
        }
        else
        {
            
        // fire Gif adding
        ImageIcon Loo = new ImageIcon(ClassLoader.getSystemResource("image/fire.gif"));
        Image ll = Loo.getImage().getScaledInstance(buttonWidth / 3, buttonHeight/2 ,0);
        ImageIcon aa = new ImageIcon(ll);
        b.setIcon(aa);
        
        }

        if(levelComplete[level-1] == true)
        {

        // fire Gif adding
        ImageIcon Loop = new ImageIcon(ClassLoader.getSystemResource("image/fire.gif"));
        Image llp = Loop.getImage().getScaledInstance(buttonWidth / 3, buttonHeight/2 ,0);
        ImageIcon aap = new ImageIcon(llp);
        b.setIcon(aap);

        }

        // Hover effect (GridLayout-safe)
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                 b.setBorder(BorderFactory.createLineBorder(accent, 6));
            }

            public void mouseExited(MouseEvent e) {
                b.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
            }
        });



        b.addActionListener(this);
        return b;
    }

    // =====================================================
    // LOAD LEVEL PROGRESS
    // =====================================================
    private void loadCompletedLevels() {

        ReadSudokuProgress reader = new ReadSudokuProgress();

        if (reader.getProgressList() == null)
            return;

        for (ReadSudokuProgress.LevelProgress lp : reader.getProgressList()) {

            System.out.println("Level " + lp.level + " completed = " + lp.completed);

            // Convert level number → array index
            int index = lp.level - 1;

            if (lp.completed == true) {

                levelComplete[index]=lp.completed;
                levelOpen[index+1] = true;
                
            }
            else if(lp.levelOpen == true)
            {
                levelOpen[index]=lp.levelOpen;
            }
        }
    }


    // =====================================================
    // CLICK HANDLING
    // =====================================================
    @Override
    public void actionPerformed(ActionEvent e) {

        sound_effect sound = new sound_effect();

        for (int i = 0; i < TOTAL_LEVELS; i++) {

            if (e.getSource() == levelButtons[i]) {

                new Thread(() -> sound.playSound("lock.wav")).start();

                if (levelComplete[i] || levelOpen[i]) {

                    // TODO: open actual level

                    new Thread(() -> sound.playSound("lock.wav")).start();

                    int lvl = i + 1;
                    new EasyMediumHard(lvl);

                }
                else
                {
                    System.out.println("Level Open: "+levelOpen[i]);

                    JOptionPane.showMessageDialog(this, "Level Locked!");
                    return;

                }

            }
        }

        
    }

    // =====================================================
    // BACKGROUND PANEL
    // =====================================================
    class BackgroundPanel extends JPanel {
        private final Image bgImage;

        BackgroundPanel() {
            bgImage = new ImageIcon(
                    ClassLoader.getSystemResource("image/window.png")
            ).getImage();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameLevel::new);
    }
}
