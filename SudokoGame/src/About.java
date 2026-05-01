import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class About extends JFrame implements ActionListener {

    JButton menu, exit;

    About() {
        setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        setTitle("Level Section");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(new Color(255, 126, 0));

        JLabel lab1 = new JLabel("About");
        lab1.setFont(new Font("Eracake", Font.PLAIN, 60));
        lab1.setForeground(Color.WHITE);
        add(lab1);
        topPanel.add(lab1);

        add(topPanel, BorderLayout.NORTH);

        ////// Ading Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(255, 126, 0));

        JLabel s1 = new JLabel("<html><!DOCTYPE html>\r\n" + //
                "\r\n" + //
                "    <h1>Basic Rules of Sudoku</h1>\r\n" + //
                "\r\n" + //
                "    <h2>Grid Layout:</h2>\r\n" + //
                "    <p>\r\n" + //
                "        The Sudoku grid consists of <strong>9 rows</strong> and <strong>9 columns</strong>.\r\n" + //
                "        The grid is further divided into <strong>9 smaller 3x3 subgrids or boxes</strong>.\r\n" + //
                "    </p>\r\n" + //
                "\r\n" + //
                "    <h2>Starting Setup:</h2>\r\n" + //
                "    <p>\r\n" + //
                "        Some cells in the grid are pre-filled with numbers (called <em>\"clues\" or \"givens\"</em>).<br>\r\n"
                + //
                "        These starting numbers cannot be changed during the game.\r\n" + //
                "    </p>\r\n" + //
                "\r\n" + //
                "    <h2>Objective:</h2>\r\n" + //
                "    <p>\r\n" + //
                "        The goal is to fill the remaining empty cells so that:\r\n" + //
                "    </p>\r\n" + //
                "    <ul>\r\n" + //
                "        <li>Each <strong>row</strong> contains the digits from 1 to 9, without repetition.</li>\r\n" + //
                "        <li>Each <strong>column</strong> contains the digits from 1 to 9, without repetition.</li>\r\n"
                + //
                "        <li>Each <strong>3x3 subgrid</strong> contains the digits from 1 to 9, without repetition.</li> </ul><h2>No Guessing Needed:</h2>\r\n"
                + //
                "        <p> Sudoku puzzles are designed so that they can be solved with <strong>logic</strong>, without the need for guessing.</p>\r\n"
                + //
                "        <br>\r\n" + //
                "        <br><br><br>\r\n" + //
                "        <b>Md. Rakibul Islam</b> (Game Creator)<br>\r\n" + //
                "        Bsc in CSE<br>\r\n" + //
                "        <br>\r\n" + //
                "        <b>Khandoker Foysal Ahamed</b>\r\n" + //
                "        (Special Thanks)<br>\r\n" + //
                "        Bsc in CSE\r\n" + //
                "        </html>\r\n" + //
                "");
        s1.setFont(new Font("Arial", Font.PLAIN, 20));
        s1.setBounds(50, 10, 200, 500);
        s1.setForeground(Color.darkGray);
        add(s1);
        leftPanel.add(s1);

        add(leftPanel, BorderLayout.WEST);

        // Create a panel for menu and exit buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(255, 126, 0));

        // Menu Button
        Color c = new Color(52, 52, 52);
        menu = new JButton("Menu");
        menu.setFont(new Font("Eracake", Font.PLAIN, 40));
        menu.setBackground(Color.WHITE);
        menu.setForeground(c);
        menu.setBorderPainted(false);
        menu.setFocusPainted(false);
        menu.setContentAreaFilled(false);
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
        exit.setContentAreaFilled(false);
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

        setBounds(0, 0, screenWidth, screenHeight);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(255, 126, 0));
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sound_effect sound = new sound_effect();

        if (e.getSource() == menu) {
            new Thread(() -> sound.playSound("button.wav")).start();
            new mainMenu();
            setVisible(false);
        } else if (e.getSource() == exit) {
            new Thread(() -> sound.playSound("button.wav")).start();
            System.exit(1);
        }

    }
}
