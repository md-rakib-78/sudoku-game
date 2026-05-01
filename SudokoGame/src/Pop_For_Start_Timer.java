import java.awt.*;
import java.awt.event.MouseAdapter;
import javax.swing.*;

public class Pop_For_Start_Timer extends JFrame{

    JLabel boardLabel;
    int screenWidth;
    int screenHeight;
    JLayeredPane layeredPane;

    public Pop_For_Start_Timer()
    {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        // ===== Transparent Frame =====
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 160));
        setSize(screen);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        screenWidth=screen.width;
        screenHeight=screen.height;


        // ===== Layered Pane =====
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        layeredPane.setOpaque(false);
        setContentPane(layeredPane);


        // ===== BOARD label text =====
        boardLabel = new JLabel();

        // Board receives clicks
        boardLabel.addMouseListener(new MouseAdapter() {});

        layeredPane.add(boardLabel, JLayeredPane.PALETTE_LAYER);

        setVisible(true);
        setLayout(null);
    }

    
}
