import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EasyMediumHard extends Pop_Up implements ActionListener {

    JButton close, easy, mid, hard;
    int buttonWidth,buttonHeight;
    int level;

    public EasyMediumHard(int lvl) {

        // initialize parent Pop_Up
        super();

        FrameManager.E_M_H = this;

        // Initialize Level
        level=lvl;

        // Load new board image
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("image/popup.png"));
        Image img1 = icon1.getImage().getScaledInstance(boardWidth, boardHeight, Image.SCALE_SMOOTH);
        boardLabel.setIcon(new ImageIcon(img1));

        // ================================================================= BUTTON Section =================================================

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screen.width;
        int screenHeight = screen.height;

        int Y =(screenHeight - 60) / 2 - 60/2;

        // Copyright Tag Adding to the bottom
        JLabel lab1 = new JLabel("Timer Mode");
        lab1.setFont(new Font("Eracake", Font.BOLD, 35));
        lab1.setForeground(Color.decode("#423128"));
        lab1.setSize(220, 35);
        lab1.setLocation(screenWidth/2-220/2, Y-(screenHeight/7));
    
        layeredPane.add(lab1,JLayeredPane.DRAG_LAYER);


        //=============================== Close Button ================================
        close = new JButton("Close");
        close.setFont(new Font("Eracake", Font.PLAIN,20));
        buttonWidth = 100;
        buttonHeight = 40;
        close.setBounds((screenWidth - buttonWidth) / 2 , (screenHeight - buttonHeight) / 2  - buttonHeight/2 + buttonHeight+ buttonHeight+buttonHeight/2+60, buttonWidth, buttonHeight);
        close.setBackground(Color.darkGray);
        close.setForeground(Color.decode("#ffffff"));
        close.setBorderPainted(false);
        close.setFocusPainted(false);
        close.setContentAreaFilled(true);
        close.addActionListener(this);

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
        // Add button to layeredPane in front
        layeredPane.add(close, JLayeredPane.DRAG_LAYER);


        //=============================== Easy Button ================================
        buttonWidth = 155;
        buttonHeight = 60;
        easy = new JButton("Easy");
        easy.setFont(new Font("Eracake", Font.PLAIN,35));
        easy.setBounds((screenWidth - buttonWidth) / 2 - (buttonWidth+buttonWidth/2)+buttonHeight, (screenHeight - buttonHeight) / 2 - buttonHeight/2+20, buttonWidth, buttonHeight);
        easy.setBackground(Color.decode("#FFFDCE"));
        easy.setForeground(Color.darkGray);
        easy.setBorderPainted(false);
        easy.setFocusPainted(false);
        easy.setContentAreaFilled(false);
        easy.addActionListener(this);

        // Hover effect for text color and border
        easy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                easy.setForeground(Color.decode("#FFFDCE"));
                easy.setBorder(BorderFactory.createEmptyBorder(0, 0, 7, 0)); 
                //easy.setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                easy.setForeground(Color.darkGray);
                easy.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0)); 
                //easy.setBorderPainted(true);
            }
        });
        // Add button to layeredPane in front
        layeredPane.add(easy, JLayeredPane.DRAG_LAYER);



        //=============================== Medium Button ================================
        buttonWidth = 175;
        buttonHeight = 60;
        mid = new JButton("Medium");
        mid.setFont(new Font("Eracake", Font.PLAIN,35));
        mid.setBounds((screenWidth - buttonWidth) / 2 , (screenHeight - buttonHeight) / 2  - buttonHeight/2+20, buttonWidth, buttonHeight);
        mid.setBackground(Color.decode("#FFFDCE"));
        mid.setForeground(Color.decode("#FF6500"));
        mid.setBorderPainted(false);
        mid.setFocusPainted(false);
        mid.setContentAreaFilled(false);
        mid.addActionListener(this);

        // Hover effect for text color and border
        mid.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mid.setForeground(Color.decode("#FFFDCE"));
                mid.setBorder(BorderFactory.createEmptyBorder(0, 0, 7, 0)); 
                //mid.setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mid.setForeground(Color.decode("#FF6500"));
                mid.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0)); 
                //mid.setBorderPainted(true);
            }
        });
        // Add button to layeredPane in front
        layeredPane.add(mid, JLayeredPane.DRAG_LAYER);


        //=============================== Hard Button ================================
        buttonWidth = 155;
        buttonHeight = 60;
        hard = new JButton("Hard");
        hard.setFont(new Font("Eracake", Font.PLAIN,35));
        hard.setBounds((screenWidth - buttonWidth) / 2 + (buttonWidth+buttonWidth/2) - buttonHeight, (screenHeight - buttonHeight) / 2  - buttonHeight/2+20, buttonWidth, buttonHeight);
        hard.setBackground(Color.decode("#FFFDCE"));
        hard.setForeground(Color.decode("#ff3232"));
        hard.setBorderPainted(false);
        hard.setFocusPainted(false);
        hard.setContentAreaFilled(false);
        hard.addActionListener(this);

        // Hover effect for text color and border
        hard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hard.setForeground(Color.decode("#FFFDCE"));
                hard.setBorder(BorderFactory.createEmptyBorder(0, 0, 7, 0)); 
                //hard.setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hard.setForeground(Color.decode("#ff3232"));
                hard.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0)); 
                //hard.setBorderPainted(true);
            }
        });
        // Add button to layeredPane in front
        layeredPane.add(hard, JLayeredPane.DRAG_LAYER);


        // Ensure null layout so bounds work
        setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        sound_effect sound = new sound_effect();
        
        if (e.getSource() == close) {

            new Thread(() -> sound.playSound("button.wav")).start();
            dispose();
        }

        else if(e.getSource() == easy)
        {
            new Thread(() -> sound.playSound("button.wav")).start();
            new Countdown3To0(level,"easy",false);
            dispose();
        }

        else if(e.getSource() == mid)
        {
            new Thread(() -> sound.playSound("button.wav")).start();
            new Countdown3To0(level,"medium",false);
            dispose();
        }

        else if(e.getSource() == hard)
        {
            new Thread(() -> sound.playSound("button.wav")).start();
            new Countdown3To0(level,"hard",false);
            dispose();
        }
    }

    public static void main(String[] args) {
        new EasyMediumHard(2);
    }
}
