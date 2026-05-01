import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

class loading extends JFrame {
    loading() {

        // Remove the title bar
        setUndecorated(true);

        JLabel lab1 = new JLabel("© 2026 md-rakib-78");
        lab1.setFont(new Font("Arial", Font.BOLD, 11));
        lab1.setForeground(Color.WHITE);
        lab1.setSize(130,15); 
        lab1.setLocation(500, (350-127)/2); 
        add(lab1);


        // Loading image adding
        ImageIcon Lo = new ImageIcon(ClassLoader.getSystemResource("image/loading.gif"));
        Image l = Lo.getImage().getScaledInstance(100, 100,0);
        ImageIcon a = new ImageIcon(l);
        JLabel lab = new JLabel(a);
        lab.setBounds(100, (350-100)/2, 100, 100);
        add(lab);

        // Logo Image adding
        ImageIcon o = new ImageIcon(ClassLoader.getSystemResource("image/l_logo1.png"));
        Image li = o.getImage().getScaledInstance(400, 100, Image.SCALE_SMOOTH);
        ImageIcon c = new ImageIcon(li);
        JLabel lab3 = new JLabel(c);
        lab3.setBounds(210, (350-100)/2, 400, 100);
        add(lab3);

        JLabel lab4 = new JLabel(
                "<html><p>B&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;o&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;o&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;s&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Y&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;o&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;u&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;r&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;r&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;i&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;n</p></html>");
        lab4.setFont(new Font("Arial", Font.ITALIC, 12));
        lab4.setForeground(Color.WHITE);
        lab4.setSize(400,15); 
        lab4.setLocation(222, (350+100)/2); 
        add(lab4);


        // Background Image for background
        ImageIcon lo = new ImageIcon(ClassLoader.getSystemResource("image/board3.png"));
        Image p = lo.getImage().getScaledInstance(732, 365, Image.SCALE_SMOOTH);
        ImageIcon b = new ImageIcon(p);
        JLabel lab2 = new JLabel(b);
        lab2.setBounds(-15, -6, 732, 365);
        add(lab2);

        // Pop Up Layout
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setSize(700, 350); 
        setLocationRelativeTo(null);
        setVisible(true);

        try {
           Thread.sleep(4000);
           dispose();
           new mainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
            
    }

    public static void main(String[] args) throws Exception {
        new loading();
    }
}
