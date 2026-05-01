import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class mainMenu extends JFrame implements ActionListener {

    JButton NewGame, saveGame, level, exit, aboutUs;

    public mainMenu() {

        FrameManager.Main_Menu = this;

        // Remove the title bar
        setUndecorated(true);
        setLayout(null);

        // Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        Font fontStyle = new Font("Eracake", Font.PLAIN, 50);

        // Copyright Tag Adding to the bottom
        JLabel lab1 = new JLabel("© 2026 md-rakib-78");
        lab1.setFont(new Font("Arial", Font.BOLD, 17));
        lab1.setForeground(new Color(255, 255, 255, 150));
        lab1.setSize(160, 15);
        lab1.setLocation((screenWidth/2)-(160/2), screenHeight-40);
        add(lab1);

        
        //================================== Menu Button Section ====================================================

        Color hoverColor = new Color(200, 200, 200);
        Color col = new Color(238, 238, 238);


        //================= Button 1: New Game =======================

        NewGame = new JButton("PLAY");
        NewGame.setFont(fontStyle);
        NewGame.setForeground(Color.DARK_GRAY);
        NewGame.setBorderPainted(false);
        NewGame.setFocusPainted(false);
        NewGame.setContentAreaFilled(false);
        NewGame.addActionListener(this);

        // Hover effect for NewGame button
        NewGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                NewGame.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                NewGame.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); 
            }
        });

        NewGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                NewGame.setBackground(hoverColor);
                NewGame.setForeground(Color.decode("#FFFDCE"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                NewGame.setBackground(col);
                NewGame.setForeground(Color.DARK_GRAY);
            }
        });
        add(NewGame);


        //======================== Button 2: Save Game =====================

        saveGame = new JButton("Save Game");
        saveGame.setFont(fontStyle);
        saveGame.setForeground(Color.DARK_GRAY);
        saveGame.setBorderPainted(false);
        saveGame.setFocusPainted(false);
        saveGame.setContentAreaFilled(false);
        saveGame.addActionListener(this);

        // Hover effect for saveGame button
        saveGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                saveGame.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                saveGame.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); 
            }
        });
        saveGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                saveGame.setBackground(hoverColor);
                saveGame.setForeground(Color.decode("#FFFDCE"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                saveGame.setBackground(col);
                saveGame.setForeground(Color.DARK_GRAY);
            }
        });
        add(saveGame);


        //========================== Button 3: Level ======================

        level = new JButton("Level");
        level.setFont(fontStyle);
        level.setBackground(Color.white);
        level.setForeground(Color.DARK_GRAY);
        level.setBorderPainted(false);
        level.setFocusPainted(false);
        level.setContentAreaFilled(false);
        // level.setOpaque(true); // Clean look
        level.addActionListener(this);

        // Hover effect for level button
        level.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                level.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                level.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            }
        });

        level.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                level.setBackground(hoverColor);
                level.setForeground(Color.decode("#FFFDCE"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                level.setBackground(col);
                level.setForeground(Color.DARK_GRAY);
            }
        });
        add(level);


        //============================= Button 4: AboutUs =====================

        aboutUs = new JButton("About");
        aboutUs.setFont(fontStyle);
        aboutUs.setBackground(Color.white);
        aboutUs.setForeground(Color.DARK_GRAY);
        aboutUs.setBorderPainted(false);
        aboutUs.setFocusPainted(false);
        aboutUs.setContentAreaFilled(false);
        aboutUs.addActionListener(this);

        // Hover effect for level button
        aboutUs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                aboutUs.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                aboutUs.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); 
            }
        });

        aboutUs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                aboutUs.setBackground(hoverColor);
                aboutUs.setForeground(Color.decode("#FFFDCE"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                aboutUs.setBackground(col);
                aboutUs.setForeground(Color.DARK_GRAY);
            }
        });
        add(aboutUs);


        //======================= Button 5: Exit Game ==================
        
        exit = new JButton("Exit Game");
        exit.setFont(fontStyle);
        exit.setBackground(Color.RED);
        exit.setForeground(Color.RED);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        exit.setContentAreaFilled(false);
        exit.addActionListener(this);

        // Hover effect for exit button
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exit.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exit.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); 
            }
        });

        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exit.setBackground(hoverColor);
                exit.setForeground(Color.decode("#FFFDCE"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exit.setBackground(col);
                exit.setForeground(Color.RED);
            }
        });
        add(exit);


        //=============================================== Logo Heading Section ===================================================

        // Logo adding as heading
        ImageIcon o = new ImageIcon(ClassLoader.getSystemResource("image/l_logo1.png"));
        Image li = o.getImage().getScaledInstance(screenWidth/3+50, screenHeight/5-25,Image.SCALE_SMOOTH);
        ImageIcon c = new ImageIcon(li);
        JLabel lab3 = new JLabel(c);
        add(lab3);

        // Dynamic positioning based on screen size
        int buttonWidth = 500;
        int buttonHeight = 55;
        int gap = 20;
        int buttonCount=5;
        int totalHeight = (buttonCount * buttonHeight)+((buttonCount - 1) * gap);
        int centerX = screenWidth / 2 - buttonWidth / 2;
        int startY = (screenHeight - totalHeight) / 2;
        int step = buttonHeight + gap;

        // Menu Button Backgraound Image
        ImageIcon ooo = new ImageIcon(ClassLoader.getSystemResource("image/Score and time board.png"));
        Image liii = ooo.getImage().getScaledInstance(screenWidth/2,totalHeight+totalHeight/3+20, Image.SCALE_SMOOTH);
        ImageIcon ccc = new ImageIcon(liii);
        JLabel labbb3 = new JLabel(ccc);
        //add(labbb3);

        // Slogan Text
        String spaced = "B     o     o     s     t            Y     o     u     r            B     r     a     i     n";
        JLabel lab4 = new JLabel(spaced);
        Font fonti = new Font("Arial", Font.ITALIC, 16);
        FontMetrics fm = getFontMetrics(fonti);
        lab4.setFont(fonti);
        lab4.setForeground(Color.WHITE);
        int textWidth = fm.stringWidth(spaced);
        int textHeight = fm.getHeight();
        lab4.setSize(textWidth, textHeight);
        add(lab4);

        // Logo Background Board adding
        ImageIcon oo = new ImageIcon(ClassLoader.getSystemResource("image/button board.png"));
        Image lii = oo.getImage().getScaledInstance(screenWidth/2+100, screenHeight/4,Image.SCALE_SMOOTH);
        ImageIcon cc = new ImageIcon(lii);
        JLabel labb3 = new JLabel(cc);
        add(labb3);

        // Centering window on the screen
        setBounds(0, 0, screenWidth, screenHeight);
        setLocationRelativeTo(null);

        // Add Menu Button
        NewGame.setBounds(centerX, (startY + 0 * step)+70, buttonWidth, buttonHeight);
        saveGame.setBounds(centerX, (startY + 1 * step)+70, buttonWidth, buttonHeight);
        level.setBounds(centerX, (startY + 2 * step)+70, buttonWidth, buttonHeight);
        aboutUs.setBounds(centerX, (startY + 3 * step)+70, buttonWidth, buttonHeight);
        exit.setBounds(centerX, (startY + 4 * step)+70, buttonWidth, buttonHeight);

        // Set positions for logo and copyright label
        lab3.setBounds(screenWidth/2-(screenWidth/3+50)/2, 30, screenWidth/3+50, screenHeight/5-25);
        labb3.setBounds(screenWidth/2-(screenWidth/2+100)/2, 15, screenWidth/2+100, screenHeight/4);
        labbb3.setBounds(screenWidth/2-(screenWidth/2)/2, screenHeight/5+80, screenWidth/2, totalHeight+totalHeight/3+20);
        lab4.setLocation(screenWidth/2-textWidth/2, screenHeight/5+13);

        // Background Image for background
        ImageIcon lo = new ImageIcon(ClassLoader.getSystemResource("image/window.png"));
        Image p = lo.getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
        ImageIcon b = new ImageIcon(p);
        JLabel lab2 = new JLabel(b);
        lab2.setBounds(0, 0, screenWidth, screenHeight);
        add(lab2);

        // Pop Up Layout
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setSize(screenWidth, screenHeight); 
        setLocationRelativeTo(null);
        setVisible(true);
    }


    ////////////////////////////////===================== Button Action Function ================///////////////////////////////////////////////////////////////
       
    @Override
    public void actionPerformed(ActionEvent e) {

        sound_effect sound = new sound_effect();

        if (e.getSource() == NewGame) {
            new Thread(() -> sound.playSound("button.wav")).start();
            StringBuffer s = new StringBuffer();
            //String src = null;

            File file = new File("a_play.txt");
            try {
                Scanner sc = new Scanner(file);
                while (sc.hasNext()) {
                    s.append(sc.next());
                }
                sc.close();
                //src = s.toString();

            } catch (Exception ee) {
                ee.printStackTrace();
            }











            //new sudokuBoard(src);
                        //setVisible(true);
            new EasyMediumHard(1);









            

        }
        if (e.getSource() == saveGame) {
            new Thread(() -> sound.playSound("button.wav")).start();
            GameData loaded = GameData.loadFromFile();
            if (loaded != null) {
                new Countdown3To0(ABORT, "", true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No Save Game!");
            }
        }
        if (e.getSource() == level) {
            new Thread(() -> sound.playSound("button.wav")).start();
            new GameLevel();
            dispose();
        }
        if (e.getSource() == aboutUs) {
            new Thread(() -> sound.playSound("button.wav")).start();
            new About();
            dispose();
        }
        if (e.getSource() == exit) {

            new Thread(() -> sound.playSound("button.wav")).start();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new mainMenu();
    }

}
