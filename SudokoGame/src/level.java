import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class level extends JFrame implements ActionListener {

    String lvl;

    JButton level1, level2, level3, level4, level5, exit, menu;
    boolean level1Complete = false; 
    boolean level2Complete = false; 
    boolean level3Complete = false; 
    boolean level4Complete = false; 

    public level() {

        setUndecorated(true);



        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        setTitle("Game Level");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(null);

        // Adding Heading Background Image
        ImageIcon lo = new ImageIcon(ClassLoader.getSystemResource("image/b_backgraound.jpg"));
        Image p = lo.getImage().getScaledInstance(screenWidth, 90, Image.SCALE_SMOOTH);
        ImageIcon b = new ImageIcon(p);
        JLabel lab2 = new JLabel(b);
        lab2.setLayout(new FlowLayout());
        lab2.setPreferredSize(new Dimension(screenWidth, screenHeight));
        add(lab2);

        // Adding Level Title
        JLabel lab1 = new JLabel("GAME LEVEL",JLabel.CENTER);
        lab1.setFont(new Font("Eracake", Font.PLAIN, 60));
        lab1.setForeground(Color.WHITE);
        //lab1.setLocation(screenWidth/2-lab1.getWidth(), screenHeight);
        add(lab1);

        // Create a panel for level buttons
        JPanel levelPanel = new JPanel();
        levelPanel.setLayout(new GridLayout(2, 4, 20, 20)); 
        levelPanel.setBackground(Color.WHITE);

        // Level Buttons with custom icon size
        level1 = createLevelButton("Level 01", null, 120, 145); 
        

        String[] s = new String[5];  

        File f = new File("l_l_levelComplete.txt");
        try {
            Scanner n = new Scanner(f);
        
            int i = 0;
            while (n.hasNextLine() && i < s.length) {
                String line = n.nextLine();
                s[i] = line;
                i++;
            }
            n.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Level unlock logic
        try {
            // Check if level 1 is complete
            if (s[0] != null && s[0].equals("level01.txt")) {
                level2 = createLevelButton("Level 02", null, 120, 145);  
                this.level1Complete = true;
            } else {
                level2 = createLevelButton("Level 02", "image/l_l_lock.png", 120, 145);  
            }
        
            // Check if level 2 is complete
            if (s[1] != null && s[1].equals("level02.txt")) {
                level3 = createLevelButton("Level 03", null, 120, 145);  
                this.level2Complete = true;
            } else {
                level3 = createLevelButton("Level 03", "image/l_l_lock.png", 120, 145);  
            }
        
            // Check if level 3 is complete
            if (s[2] != null && s[2].equals("level03.txt")) {
                level4 = createLevelButton("Level 04", null, 120, 145); 
                this.level3Complete = true;
            } else {
                level4 = createLevelButton("Level 04", "image/l_l_lock.png", 120, 145); 
            }
        
            // Check if level 4 is complete
            if (s[3] != null && s[3].equals("level04.txt")) {
                level5 = createLevelButton("Level 05", null, 120, 145); 
                this.level4Complete = true;
            } else {
                level5 = createLevelButton("Level 05", "image/l_l_lock.png", 120, 145); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Adding buttons to the panel
        levelPanel.setPreferredSize(new Dimension(90, 80));
        levelPanel.add(level1);
        levelPanel.add(level2);
        levelPanel.add(level3);
        levelPanel.add(level4);
        levelPanel.add(level5);
        

        add(levelPanel, BorderLayout.CENTER); 

// Create a panel for menu and exit buttons
JPanel bottomPanel = new JPanel();
bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
bottomPanel.setBackground(Color.WHITE);

// Menu Button
Color c = new Color(52, 52, 52);
menu = new JButton("Menu");
menu.setFont(new Font("Eracake", Font.PLAIN, 40));
menu.setBackground(Color.WHITE);
menu.setForeground(c);
menu.setBorderPainted(false);
menu.setFocusPainted(false);
menu.setContentAreaFilled(true);
menu.setPreferredSize(new Dimension(200, 100));
menu.addActionListener(this);


menu.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseEntered(MouseEvent e) {
        
        menu.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); 
    }

    @Override
    public void mouseExited(MouseEvent e) {
      
        menu.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    }
});


bottomPanel.add(menu);

// Exit Button
exit = new JButton("Exit");
exit.setFont(new Font("Eracake", Font.PLAIN, 40));
exit.setBackground(Color.WHITE);
exit.setForeground(Color.RED);
exit.setBorderPainted(false);
exit.setFocusPainted(false);
exit.setContentAreaFilled(true);
exit.setPreferredSize(new Dimension(200, 100));
exit.addActionListener(this);

// Add hover effect for the exit button
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


bottomPanel.add(exit);

add(bottomPanel, BorderLayout.SOUTH);



        pack(); 
        setLocationRelativeTo(null);
        setBounds(0, 0, screenWidth, screenHeight); 
        setVisible(true);
    }///////////////////////////////////////////////////////////////////////////////////////////////////////////////


private JButton createLevelButton(String text, String iconPath, int iconWidth, int iconHeight) {
    Color c = new Color(254, 113, 21);
    JButton button = new JButton(text);

    if (iconPath != null) {

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(iconPath));

        Image scaledImage = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));
    }

    // Button customization
    button.setFont(new Font("Eracake", Font.PLAIN, 45));
    button.setBackground(Color.WHITE);
    button.setForeground(c);
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setHorizontalTextPosition(SwingConstants.CENTER); 
    button.setVerticalTextPosition(SwingConstants.BOTTOM);    

    button.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {

            button.setLocation(button.getX(), button.getY() - 20);
        }

        @Override
        public void mouseExited(MouseEvent e) {
 
            button.setLocation(button.getX(), button.getY() + 20);
        }
    });

    button.addActionListener(this);  

    return button;
}


    @Override
    public void actionPerformed(ActionEvent e) {

        sound_effect sound =new sound_effect();

        if (e.getSource() == level1) {
            new Thread(() -> sound.playSound("button.wav")).start();
            new sudokuBoard("level01.txt");
            setVisible(false);
        }
        if (e.getSource() == level2) {
            if (level1Complete) { 
                new Thread(() -> sound.playSound("button.wav")).start();
                new sudokuBoard("level02.txt");
                setVisible(false);
            } else {
                new Thread(() -> sound.playSound("lock.wav")).start();
                JPanel panel = new JPanel();
                panel.setBackground(Color.WHITE);
        
                panel.setLayout(new GridBagLayout());
                GridBagConstraints gbcd = new GridBagConstraints();
                gbcd.anchor = GridBagConstraints.CENTER;
        
                JLabel label = new JLabel("Locked!");
                label.setForeground(new Color(254, 113, 21)); 
                label.setFont(new Font("Eracake", Font.PLAIN, 36)); 
        
                panel.add(label, gbcd);
        
                panel.setPreferredSize(new Dimension(300, 150));
                setUndecorated(true);
        
                JOptionPane.showMessageDialog(null, panel, "Sudoku -> Message", JOptionPane.PLAIN_MESSAGE, new ImageIcon("image/a_sudoku_logo.png"));
            }
        }
        if (e.getSource() == level3) {
            if (level2Complete) { 
                new Thread(() -> sound.playSound("button.wav")).start();
                new sudokuBoard("level03.txt");
                setVisible(false);
            } else {
                new Thread(() -> sound.playSound("lock.wav")).start();
                JPanel panel = new JPanel();
                panel.setBackground(Color.WHITE);
        
                panel.setLayout(new GridBagLayout());
                GridBagConstraints gbcd = new GridBagConstraints();
                gbcd.anchor = GridBagConstraints.CENTER;
        
                JLabel label = new JLabel("Locked!");
                label.setForeground(new Color(254, 113, 21)); 
                label.setFont(new Font("Eracake", Font.PLAIN, 36)); 
        
                panel.add(label, gbcd);
        
                panel.setPreferredSize(new Dimension(300, 150));
        
                JOptionPane.showMessageDialog(null, panel, "Sudoku -> Message", JOptionPane.PLAIN_MESSAGE, new ImageIcon("image/a_sudoku_logo.png"));
            }
        }
        if (e.getSource() == level4) {
            if (level3Complete) { 
                new Thread(() -> sound.playSound("button.wav")).start();
                new sudokuBoard("level04.txt");
                setVisible(false);
            } else {
                new Thread(() -> sound.playSound("lock.wav")).start();
                JPanel panel = new JPanel();
                panel.setBackground(Color.WHITE);
        
                panel.setLayout(new GridBagLayout());
                GridBagConstraints gbcd = new GridBagConstraints();
                gbcd.anchor = GridBagConstraints.CENTER;
        
                JLabel label = new JLabel("Locked!");
                label.setForeground(new Color(254, 113, 21)); 
                label.setFont(new Font("Eracake", Font.PLAIN, 36)); 
        
                panel.add(label, gbcd);
        
                panel.setPreferredSize(new Dimension(300, 150));
        
                JOptionPane.showMessageDialog(null, panel, "Sudoku -> Message", JOptionPane.PLAIN_MESSAGE, new ImageIcon("image/a_sudoku_logo.png"));
            }
        }
        if (e.getSource() == level5) {

            if (level4Complete) { 
                new Thread(() -> sound.playSound("button.wav")).start();
                new sudokuBoard("level05.txt");
                setVisible(false);
            } else {
                new Thread(() -> sound.playSound("button.wav")).start();
                JPanel panel = new JPanel();
                panel.setBackground(Color.WHITE);
        
                panel.setLayout(new GridBagLayout());
                GridBagConstraints gbcd = new GridBagConstraints();
                gbcd.anchor = GridBagConstraints.CENTER;
        
                JLabel label = new JLabel("Locked!");
                label.setForeground(new Color(254, 113, 21)); 
                label.setFont(new Font("Eracake", Font.PLAIN, 36)); 
        
                panel.add(label, gbcd);
        
                panel.setPreferredSize(new Dimension(300, 150));
        
                JOptionPane.showMessageDialog(null, panel, "Sudoku -> Message", JOptionPane.PLAIN_MESSAGE, new ImageIcon("image/a_sudoku_logo.png"));
            }
        }

        if (e.getSource() == menu) {
            new Thread(() -> sound.playSound("button.wav")).start();
            setVisible(false);
            new mainMenu();
        }

        if (e.getSource() == exit) {
            new Thread(() -> sound.playSound("button.wav")).start();
            System.exit(0);
        }
    }

    public static void main(String args[])
    {
        new level();
    }

}
