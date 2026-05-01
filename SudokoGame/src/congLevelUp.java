import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class congLevelUp extends JFrame implements ActionListener {

    JButton menu, next;
    String lvl;

    congLevelUp(String lvl) {

        this.lvl=lvl;
        setUndecorated(true);
        int windowWidth = 1000;
        int windowHeight = 500;

        // Logo Image
        ImageIcon o = new ImageIcon(ClassLoader.getSystemResource("a_perfect.png"));
        Image li = o.getImage().getScaledInstance(472, 308, Image.SCALE_SMOOTH);
        ImageIcon c = new ImageIcon(li);
        JLabel lab3 = new JLabel(c);
        lab3.setBounds((windowWidth - 472) / 2, (windowHeight - 308) / 2 - 50, 472, 308);
        add(lab3);

        // Button colors and font
        
        Font buttonFont = new Font("Eracake", Font.PLAIN, 50);

        // Menu Button
        menu = new JButton("Home");
        menu.setFont(buttonFont);
        menu.setBackground(Color.WHITE);
        menu.setForeground(new Color(254, 113, 21));
        menu.setBorderPainted(false);
        menu.setFocusPainted(false);
        menu.addActionListener(this);
        menu.setBounds(150, windowHeight - 100, 200, 80);
        add(menu);

        // Next Button
        next = new JButton("Next");
        next.setFont(buttonFont);
        next.setBackground(Color.WHITE);
        next.setForeground(new Color(254, 113, 21));
        next.setBorderPainted(false);
        next.setFocusPainted(false);
        next.addActionListener(this);
        next.setBounds(windowWidth - 350, windowHeight - 100, 200, 80);
        add(next);

        // Background Image
        ImageIcon lo = new ImageIcon(ClassLoader.getSystemResource("b_backgraound.jpg"));
        Image p = lo.getImage().getScaledInstance(windowWidth, windowHeight, Image.SCALE_SMOOTH);
        ImageIcon b = new ImageIcon(p);
        JLabel lab2 = new JLabel(b);
        lab2.setBounds(0, 0, windowWidth, windowHeight);
       // add(lab2);


        getContentPane().setBackground(Color.white);
        setLayout(null);
        setSize(windowWidth, windowHeight);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==menu)
        {
            new mainMenu();
            setVisible(false);
        }
        else if(e.getSource()==next)
        {
            int n=1;
           char a = lvl.charAt(6);
           String s=String.valueOf(a);
          
           int sum =n+ Integer.parseInt(s);
           String s1 =Integer.toString(sum);

           String lvlUp=lvl.replace(s, s1);
           

           // create play file
           File file = new File("a_play.txt");
           //File file1 = new File("l_l_levelComplete.txt");
           try {
            file.createNewFile();       
           } catch (Exception ee) {
            ee.printStackTrace();
           }
   
           // write play file for complete and go next level
           try {
            Formatter form = new Formatter(file);
            
            form.format("%s",lvlUp);
   
            form.close();  
           } catch (Exception aa) {
            aa.printStackTrace();
           }


           JPanel panel = new JPanel();
           panel.setBackground(Color.WHITE);
   
           panel.setLayout(new GridBagLayout());
           GridBagConstraints gbcd = new GridBagConstraints();
           gbcd.anchor = GridBagConstraints.CENTER;
   
           JLabel label = new JLabel("Unlocked New Level");
           label.setForeground(new Color(254, 113, 21)); 
           label.setFont(new Font("Eracake", Font.PLAIN, 36)); 
   
           panel.add(label, gbcd);
   
           panel.setPreferredSize(new Dimension(350, 150));
   
           JOptionPane.showMessageDialog(null, panel, "Sudoku -> Message", JOptionPane.PLAIN_MESSAGE, new ImageIcon("a_sudoku_logo.png"));



           /// write level complete for next level unlock
           File file1 = new File("l_l_levelComplete.txt");
           try {

               FileWriter fw = new FileWriter(file1, true);
               PrintWriter pw = new PrintWriter(fw);
   
               pw.print(this.lvl);
               
               if(pw!=null)
               {
                pw.println();
               }
               

               pw.close();
   
           } catch (Exception ea) {
               ea.printStackTrace();
           }

           new sudokuBoard(lvlUp);
           setVisible(false);
        }
        
    }
}
