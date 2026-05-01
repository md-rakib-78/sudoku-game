import java.awt.*;
import java.awt.event.MouseAdapter;
import javax.swing.*;

public class Pop_Up extends JFrame {

    JLabel boardLabel;
    int boardWidth;
    int boardHeight;
    int screenWidth;
    int screenHeight;
    JLayeredPane layeredPane;

    public Pop_Up() {

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        // ===== Transparent Frame =====
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 150));
        setSize(screen);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        screenWidth=screen.width;
        screenHeight=screen.height;
        boardWidth = screen.width / 2;
        boardHeight = screen.height / 2;
        int boardX = screen.width / 2 - boardWidth / 2;
        int boardY = screen.height / 2 - boardHeight / 2;

        // ===== Layered Pane =====
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        layeredPane.setOpaque(false);
        setContentPane(layeredPane);

        // ===== BLOCKERS (Outside board) =====
        JPanel topBlocker = new JPanel();
        topBlocker.setBounds(0, 0, screen.width, boardY);
        topBlocker.setOpaque(false);
        topBlocker.addMouseListener(new MouseAdapter() {});
        layeredPane.add(topBlocker, JLayeredPane.DEFAULT_LAYER);

        JPanel bottomBlocker = new JPanel();
        bottomBlocker.setBounds(0, boardY + boardHeight, screen.width, screen.height - (boardY + boardHeight));
        bottomBlocker.setOpaque(false);
        bottomBlocker.addMouseListener(new MouseAdapter() {});
        layeredPane.add(bottomBlocker, JLayeredPane.DEFAULT_LAYER);

        JPanel leftBlocker = new JPanel();
        leftBlocker.setBounds(0, boardY, boardX, boardHeight);
        leftBlocker.setOpaque(false);
        leftBlocker.addMouseListener(new MouseAdapter() {});
        layeredPane.add(leftBlocker, JLayeredPane.DEFAULT_LAYER);

        JPanel rightBlocker = new JPanel();
        rightBlocker.setBounds(boardX + boardWidth, boardY, screen.width - (boardX + boardWidth), boardHeight);
        rightBlocker.setOpaque(false);
        rightBlocker.addMouseListener(new MouseAdapter() {});
        layeredPane.add(rightBlocker, JLayeredPane.DEFAULT_LAYER);

        // ===== BOARD IMAGE =====
        boardLabel = new JLabel();
        boardLabel.setBounds(0, 0, boardWidth, boardHeight);

        // Board receives clicks
        boardLabel.addMouseListener(new MouseAdapter() {});

        // ===== SHADOW PANEL =====
        ShadowPanel shadowPanel = new ShadowPanel(boardLabel);
        shadowPanel.setBounds(boardX - shadowPanel.getShadowPadding(),boardY - shadowPanel.getShadowPadding(),boardWidth + shadowPanel.getShadowPadding() * 2,boardHeight + shadowPanel.getShadowPadding() * 2);

        layeredPane.add(shadowPanel, JLayeredPane.PALETTE_LAYER);

        setVisible(true);
        setLayout(null);
    }

    // ===== SHADOW PANEL =====
    static class ShadowPanel extends JPanel {

        private final int shadowSize = 30;
        private final int cornerRadius = 28;

        public ShadowPanel(JComponent content) {
            setLayout(new BorderLayout());
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(
                    shadowSize, shadowSize, shadowSize, shadowSize));
            add(content, BorderLayout.CENTER);
        }

        public int getShadowPadding() {
            return shadowSize;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            for (int i = shadowSize; i > 0; i--) {
                float alpha = (float) i / shadowSize;
                g2.setColor(new Color(0, 0, 0, (int) (alpha * 18)));
                g2.fillRoundRect(
                        i, i,
                        w - i * 2,
                        h - i * 2,
                        cornerRadius,
                        cornerRadius
                );
            }
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Pop_Up::new);
    }
}
