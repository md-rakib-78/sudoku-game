import java.awt.*;
import java.io.File;
import javax.swing.border.Border;
import javax.swing.*;
import java.util.*;
import javax.swing.border.MatteBorder;
import java.awt.event.*;

public class sudokuBoard extends JFrame implements ActionListener {
   
    JButton exitG, home, reset, save,next;
    String lvl;
    String lvl1;
    JTextField[][] textFields = new JTextField[9][9];
    String location[][]=new String[9][9];
    String saveLevelGame;

   

    String matching[][] = new String[9][9];

    String array1[][] = new String[3][3];
    String array2[][] = new String[3][3];
    String array3[][] = new String[3][3];

    String array4[][] = new String[3][3];
    String array5[][] = new String[3][3];
    String array6[][] = new String[3][3];

    String array7[][] = new String[3][3];
    String array8[][] = new String[3][3];
    String array9[][] = new String[3][3];

    sudokuBoard(String lvl) {

        this.lvl = lvl;
        this.lvl1=lvl;

        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.location[i][j]="0";
            }
        }



        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Overlay Panel
        JPanel overlayPanel = new JPanel(new BorderLayout());
        overlayPanel.setOpaque(false);
       // backgroundPanel.add(overlayPanel, BorderLayout.CENTER);

        // Top Panel for Level Tag
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        Font fontStylelvl = new Font("Eracake", Font.PLAIN, 70);
        JLabel lab1;


        // Center Panel for Sudoku Grid and Board Template
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;


        switch (lvl) {
            case "level01.txt":
                valueAdd(lvl);
                lab1 = new JLabel("Level 01");
                lab1.setFont(fontStylelvl);
                break;
            case "level02.txt":
                 valueAdd(lvl);
                lab1 = new JLabel("Level 02");
                lab1.setFont(fontStylelvl);
                break;
            case "level03.txt":
                valueAdd(lvl);
                lab1 = new JLabel("Level 03");
                lab1.setFont(fontStylelvl);
                break;
            case "level04.txt":
                valueAdd(lvl);
                lab1 = new JLabel("Level 04");
                lab1.setFont(fontStylelvl);
                break;
            case "level05.txt":
                valueAdd(lvl);
                lab1 = new JLabel("Level 05");
                lab1.setFont(fontStylelvl);
                break;
            case "saveLevelgame.txt":
    
                File filein = new File("saveLevelgame.txt");
                String s = null;
                try {
                    Scanner input = new Scanner(filein);
                    while(input.hasNext())
                    {
                        s= input.next();
                    }
                    input.close();
                    
                } catch (Exception i) {
                    i.printStackTrace();
                }

                this.saveLevelGame=s;
                this.lvl1=this.saveLevelGame;

                if(saveLevelGame== null)
                {
                
                    JPanel panel = new JPanel();
                    panel.setBackground(Color.WHITE); 
            
                    panel.setLayout(new GridBagLayout());
                    GridBagConstraints gbcd = new GridBagConstraints();
                    gbcd.anchor = GridBagConstraints.CENTER;
            
                    JLabel label = new JLabel("No Save Game!");
                    label.setForeground(new Color(254, 113, 21));
                    label.setFont(new Font("Eracake", Font.PLAIN, 30));
            
                    panel.add(label, gbc);
        
                    panel.setPreferredSize(new Dimension(300, 150)); 
        
                    JOptionPane.showMessageDialog(null, panel, "Sudoku -> Message", JOptionPane.PLAIN_MESSAGE, new ImageIcon("a_sudoku_logo.png"));         
                    
                }

                switch (this.saveLevelGame) {
                    case "level01.txt":
                       
                        saveValueOnly();
                        valueAdd(this.saveLevelGame);

                        lab1 = new JLabel("Level 01");
                        lab1.setFont(fontStylelvl);
                        break;
                    case "level02.txt":
                        saveValueOnly();
                        valueAdd(this.saveLevelGame);

                        lab1 = new JLabel("Level 02");
                        lab1.setFont(fontStylelvl);
                        break;
                    case "level03.txt":
                        valueAdd(this.saveLevelGame);
                        saveValueOnly();

                        lab1 = new JLabel("Level 03");
                        lab1.setFont(fontStylelvl);
                        break;
                    case "level04.txt":
                        valueAdd(this.saveLevelGame);
                        saveValueOnly();

                        lab1 = new JLabel("Level 04");
                        lab1.setFont(fontStylelvl);
                        break;
                    case "level05.txt":
                        valueAdd(this.saveLevelGame);
                        saveValueOnly();

                        lab1 = new JLabel("Level 05");
                        lab1.setFont(fontStylelvl);
                        break;

                        default:
                        
                        
                        
                        lab1 = new JLabel("Sudoku");
                        
                        lab1.setFont(fontStylelvl);
                        break;
                }

                break;
            default:
                lab1 = new JLabel("Sudoku");
                lab1.setFont(fontStylelvl);
                break;
        }
      

        Color customColor = new Color(85, 56, 39);
        lab1.setForeground(customColor);
        topPanel.add(lab1);
        overlayPanel.add(topPanel, BorderLayout.NORTH);



///// Sudoku Grid Panel
        JPanel gridPanel = new JPanel(new GridLayout(9, 9, 3, 3));
        gridPanel.setOpaque(false);
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                textFields[row][col] = new JTextField();
                textFields[row][col].setHorizontalAlignment(JTextField.CENTER);
                textFields[row][col].setFont(new Font("Eracake", Font.PLAIN, 35));
                textFields[row][col].setPreferredSize(new Dimension((screenWidth/2)/11, (screenWidth/2)/12-4));
                gridPanel.add(textFields[row][col]);
            }
        }
    
        centerPanel.add(gridPanel, gbc);
        overlayPanel.add(centerPanel, BorderLayout.CENTER);

////// Game Board adding
        try {          
            ImageIcon lo = new ImageIcon(ClassLoader.getSystemResource("board.png"));
            Image plo = lo.getImage().getScaledInstance(screenWidth/2+20, screenHeight-210, Image.SCALE_SMOOTH);
            ImageIcon b = new ImageIcon(plo);
            JLabel lab2 = new JLabel(b);
            centerPanel.add(lab2, gbc);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Right Panel for Buttons////////////////////////////////////////////////////////////////////////////////////
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 50));
        
        Color c = new Color(238, 238, 238);  
        Font fontStyle = new Font("Eracake", Font.PLAIN, 49);
       
        Color hoverColor = new Color(200, 200, 200); // Light gray for hover
        
        // Next Button
        next = new JButton("NEXT");
        next.setFont(new Font("Eracake", Font.PLAIN, 55));
        next.setMaximumSize(new Dimension(310, 60));
        next.setBackground(c);
        next.setForeground(Color.DARK_GRAY);
        next.setBorderPainted(false);
        next.setFocusPainted(false);
        next.setContentAreaFilled(false);
        next.addActionListener(this);
        
        // Hover effect for Next button
        next.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                next.setBackground(hoverColor);  // Change background on hover
                next.setForeground(Color.WHITE); // Change text color on hover
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                next.setBackground(c);  // Restore original background
                next.setForeground(Color.DARK_GRAY);  // Restore original text color
            }
        });
        
        rightPanel.add(next);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Save Button
        save = new JButton("Save");
        save.setFont(fontStyle);
        save.setMaximumSize(new Dimension(300, 50));
        save.setBackground(c);
        save.setForeground(Color.DARK_GRAY);
        save.setBorderPainted(false);
        save.setFocusPainted(false);
        save.setContentAreaFilled(false);
        save.addActionListener(this);
        
        // Hover effect for Save button
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                save.setBackground(hoverColor);  
                save.setForeground(Color.WHITE); 
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                save.setBackground(c);  
                save.setForeground(Color.DARK_GRAY); 
            }
        });
        
        rightPanel.add(save);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Reset Button
        reset = new JButton("Reset");
        reset.setFont(fontStyle);
        reset.setMaximumSize(new Dimension(300, 50));
        reset.setBackground(c);
        reset.setForeground(Color.DARK_GRAY);
        reset.setBorderPainted(false);
        reset.setFocusPainted(false);
        reset.setContentAreaFilled(false);
        reset.addActionListener(this);
        
        // Hover effect for Reset button
        reset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                reset.setBackground(hoverColor);  
                reset.setForeground(Color.WHITE); 
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                reset.setBackground(c);  
                reset.setForeground(Color.DARK_GRAY); 
            }
        });
        
        rightPanel.add(reset);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Home Button
        home = new JButton("Home");
        home.setFont(fontStyle);
        home.setMaximumSize(new Dimension(300, 50));
        home.setBackground(c);
        home.setForeground(Color.DARK_GRAY);
        home.setBorderPainted(false);
        home.setFocusPainted(false);
        home.setContentAreaFilled(false);
        home.addActionListener(this);
        
        // Hover effect for Home button
        home.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                home.setBackground(hoverColor);  
                home.setForeground(Color.WHITE); 
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                home.setBackground(c);  
                home.setForeground(Color.DARK_GRAY); 
            }
        });
        
        rightPanel.add(home);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Exit Game Button
        exitG = new JButton("Exit Game");
        Font fontStyle2 = new Font("Eracake", Font.PLAIN, 50);
        exitG.setFont(fontStyle2);
        exitG.setMaximumSize(new Dimension(300, 50));
        exitG.setBackground(c);
        exitG.setForeground(Color.white);
        exitG.setBorderPainted(false);
        exitG.setFocusPainted(false);
        exitG.setContentAreaFilled(false);
        exitG.addActionListener(this);
        
        // Hover effect for Exit Game button
        exitG.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitG.setBackground(hoverColor);  
                exitG.setForeground(Color.red); 
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                exitG.setBackground(c);  
                exitG.setForeground(Color.white); 
            }
        });
        
        rightPanel.add(exitG);
        
        overlayPanel.add(rightPanel, BorderLayout.EAST);
        

        // Bottom Panel for Copyright/////////////////////////////////////////////////////////////////////////////////////////
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        JLabel labCopyright = new JLabel("© 2024 Rakib The Power");
        labCopyright.setFont(new Font("Poppins", Font.PLAIN, 18));
        labCopyright.setForeground(new Color(64, 64, 64, 64));
        bottomPanel.add(labCopyright);
        overlayPanel.add(bottomPanel, BorderLayout.SOUTH);



        // Add Borders and Populate Grid
        Border rightBorder = BorderFactory.createMatteBorder(0, 0, 0, 4, Color.BLACK);
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 4, 0, Color.BLACK);

        // Array 1
        for (int i = 0; i <=2; i++) {
            for (int j = 0; j <=2; j++) {
                if (array1[i][j].equals("0")) {
                    this.textFields[i][j].setText("");
                    this.textFields[i][j].setEditable(true);
                }
                else {
                    this.textFields[i][j].setText(array1[i][j]);
                    this.textFields[i][j].setBackground(Color.lightGray);
                    if(location[i][j].equals("0"))
                    {
                    this.textFields[i][j].setEditable(false);
                    }
                }

                if(!location[i][j].equals("0"))
                {
                   this.textFields[i][j].setText(location[i][j]);
                   this.textFields[i][j].setEditable(true);
                }
            }
            this.textFields[i][2].setBorder(rightBorder);
            this.textFields[2][i].setBorder(bottomBorder);
        }
        this.textFields[2][2].setBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK));

        // Array 2
        int k = 0;
        int l = 3;
        for (int i = 0; i <= 2; i++) {
            for (int j = 3; j <= 5; j++) {
                if (array2[i][k].equals("0")) {
                    this.textFields[i][j].setText("");
                    this.textFields[i][j].setEditable(true);
                    k++;
                } else {
                    this.textFields[i][j].setText(array2[i][k]);
                    this.textFields[i][j].setBackground(Color.lightGray);
                    if(location[i][j].equals("0"))
                    {
                    this.textFields[i][j].setEditable(false);
                    }
                    k++;
                }

                if(!location[i][j].equals("0"))
                {
                   this.textFields[i][j].setText(location[i][j]);
                   this.textFields[i][j].setEditable(true);
                }
            }
            k = 0;
            this.textFields[i][5].setBorder(rightBorder);
            this.textFields[2][l].setBorder(bottomBorder);
            l++;
        }
        this.textFields[2][5].setBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK));

        // Array 3
        k = 0;
        l = 6;
        for (int i = 0; i <= 2; i++) {
            for (int j = 6; j <= 8; j++) {
                if (array3[i][k].equals("0")) {
                    this.textFields[i][j].setText("");
                    this.textFields[i][j].setEditable(true);
                    k++;
                } else {
                    this.textFields[i][j].setText(array3[i][k]);
                    this.textFields[i][j].setBackground(Color.lightGray);
                    if(location[i][j].equals("0"))
                    {
                    this.textFields[i][j].setEditable(false);
                    }
                    k++;
                }
                if(!location[i][j].equals("0"))
                {
                   this.textFields[i][j].setText(location[i][j]);
                   this.textFields[i][j].setEditable(true);
                }
            }
            k = 0;
            this.textFields[2][l].setBorder(bottomBorder);
            l++;
        }

        // Array 4
        int p = 0;
        k = 0;
        l = 0;
        for (int i = 3; i <= 5; i++) {
            for (int j = 0; j <= 2; j++) {
                if (array4[p][k].equals("0")) {
                    this.textFields[i][j].setText("");
                    this.textFields[i][j].setEditable(true);
                    k++;
                } else {
                    this.textFields[i][j].setText(array4[p][k]);
                    this.textFields[i][j].setBackground(Color.lightGray);
                    if(location[i][j].equals("0"))
                    {
                    this.textFields[i][j].setEditable(false);
                    }
                    k++;
                }
                if(!location[i][j].equals("0"))
                {
                   this.textFields[i][j].setText(location[i][j]);
                   this.textFields[i][j].setEditable(true);
                }
            }
            k = 0;
            p++;

            this.textFields[i][2].setBorder(rightBorder);
            this.textFields[5][l].setBorder(bottomBorder);
            l++;
        }
        this.textFields[5][2].setBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK));

        // Array 5
        p = 0;
        k = 0;

        for (int i = 3; i <= 5; i++) {
            for (int j = 3; j <= 5; j++) {
                if (array5[p][k].equals("0")) {
                    this.textFields[i][j].setText("");
                    this.textFields[i][j].setEditable(true);
                    k++;
                } else {
                    this.textFields[i][j].setText(array5[p][k]);
                    this.textFields[i][j].setBackground(Color.lightGray);
                    if(location[i][j].equals("0"))
                    {
                    this.textFields[i][j].setEditable(false);
                    }
                    k++;
                }
                if(!location[i][j].equals("0"))
                {
                   this.textFields[i][j].setText(location[i][j]);
                   this.textFields[i][j].setEditable(true);
                }
            }
            k = 0;
            p++;

            this.textFields[i][5].setBorder(rightBorder);
            this.textFields[5][i].setBorder(bottomBorder);       
        }
        this.textFields[5][5].setBorder(new MatteBorder(0, 0, 3, 3, Color.BLACK));

        // Array 6
        p = 0;
        k = 0;
        l = 6;
        for (int i = 3; i <= 5; i++) {
            for (int j = 6; j <= 8; j++) {
                if (array6[p][k].equals("0")) {
                    this.textFields[i][j].setText("");
                    this.textFields[i][j].setEditable(true);
                    k++;
                } else {
                    this.textFields[i][j].setText(array6[p][k]);
                    this.textFields[i][j].setBackground(Color.lightGray);
                    if(location[i][j].equals("0"))
                    {
                    this.textFields[i][j].setEditable(false);
                    }
                    k++;
                }
                if(!location[i][j].equals("0"))
                {
                   this.textFields[i][j].setText(location[i][j]);
                   this.textFields[i][j].setEditable(true);
                }
            }
            k = 0;
            p++;

            this.textFields[5][l].setBorder(bottomBorder);
            l++;
        }

        // Array 7
        p = 0;
        k = 0;
        for (int i = 6; i <= 8; i++) {
            for (int j = 0; j <= 2; j++) {
                if (array7[p][k].equals("0")) {
                    this.textFields[i][j].setText("");
                    this.textFields[i][j].setEditable(true);
                    k++;
                } else {
                    this.textFields[i][j].setText(array7[p][k]);
                    this.textFields[i][j].setBackground(Color.lightGray);
                    if(location[i][j].equals("0"))
                    {
                        this.textFields[i][j].setEditable(false);
                    }
                    
                    k++;
                }
                if(!location[i][j].equals("0"))
                {
                   this.textFields[i][j].setText(location[i][j]);
                   this.textFields[i][j].setEditable(true);
                }
            }
            k = 0;
            p++;

            this.textFields[i][2].setBorder(rightBorder);    
        }

        // Array 8
        p = 0;
        k = 0;
        for (int i = 6; i <= 8; i++) {
            for (int j = 3; j <= 5; j++) {
                if (array8[p][k].equals("0")) {
                    this.textFields[i][j].setText("");
                    this.textFields[i][j].setEditable(true);
                    k++;
                } else {
                    this.textFields[i][j].setText(array8[p][k]);
                    this.textFields[i][j].setBackground(Color.lightGray);
                    if(location[i][j].equals("0"))
                    {
                    this.textFields[i][j].setEditable(false);
                    }

                    k++;
                }

                if(!location[i][j].equals("0"))
                {
                   this.textFields[i][j].setText(location[i][j]);
                   this.textFields[i][j].setEditable(true);
                }
            }
            k = 0;
            p++;

            this.textFields[i][5].setBorder(rightBorder);   

        }

        // Array 9
        p = 0;
        k = 0;
        for (int i = 6; i <= 8; i++) {
            for (int j = 6; j <= 8; j++) {
                if (array9[p][k].equals("0")) {
                    this.textFields[i][j].setText("");
                    this.textFields[i][j].setEditable(true);
                    k++;
                } else{
                    this.textFields[i][j].setText(array9[p][k]);

                    this.textFields[i][j].setBackground(Color.lightGray);
                    if(location[i][j].equals("0"))
                    {
                    this.textFields[i][j].setEditable(false);
                    }

                    k++;
                }
                if(!location[i][j].equals("0"))
                {
                   this.textFields[i][j].setText(location[i][j]);
                   this.textFields[i][j].setEditable(true);
                }
            }
            k = 0;
            p++;
        }


/// Manualy cheak
        for (int i = 0; i < this.textFields.length; i++) {
            for (int j = 0; j < this.textFields[i].length; j++) {

                final int rowIndex = i;
                final int colIndex = j;

                int len = location[rowIndex][colIndex].length();
            
                String userInput = location[rowIndex][colIndex];
                
                if(!userInput.equals("0"))
                {
                boolean stringMatch = userInput.matches("[1-9]+");
                boolean indicate = cheakBox(userInput, rowIndex, colIndex);

                System.out.println("Indicator : " + indicate);

                if (indicate == false && !textFields[rowIndex][colIndex].getBackground().equals(Color.lightGray) || len>1 || stringMatch==false ) {

                    textFields[rowIndex][colIndex].setBackground(Color.red);

                }
                else
                {
                    textFields[rowIndex][colIndex].setBackground(Color.lightGray);
                }

            }          
            }
        }



////// Add FocusListeners

for (int i = 0; i < this.textFields.length; i++) {
    for (int j = 0; j < this.textFields[i].length; j++) {
        final int rowIndex = i;
        final int colIndex = j;

        this.textFields[i][j].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
 
            }

            @Override
            public void focusLost(FocusEvent e) {
                String userInput = textFields[rowIndex][colIndex].getText();
                int l = userInput.length();
                

         
                boolean stringMatch = userInput.matches("[1-9]");

                boolean indicate = cheakBox(userInput, rowIndex, colIndex);

                System.out.println("Indicator : " + indicate);

                if (userInput.equals("")) {
                    textFields[rowIndex][colIndex].setBackground(Color.white); 
                    location[rowIndex][colIndex] = "0"; 
                   // System.out.println("Field at Location[" + rowIndex + "][" + colIndex + "] was left blank.");
                } else {

                    if ((!indicate && !userInput.equals(""))  && !textFields[rowIndex][colIndex].getBackground().equals(Color.lightGray) || l > 1) {
                        textFields[rowIndex][colIndex].setBackground(Color.red);
                    } else if ((!stringMatch && !userInput.equals("")) && !textFields[rowIndex][colIndex].getBackground().equals(Color.lightGray) || l > 1) {
                        textFields[rowIndex][colIndex].setBackground(Color.red);
                    } else if (indicate && !userInput.equals("")) {
                        textFields[rowIndex][colIndex].setBackground(Color.green);
                    }

                    location[rowIndex][colIndex] = userInput;

                    //System.out.println("Location[" + rowIndex + "][" + colIndex + "] = " + location[rowIndex][colIndex]);
                }
            }
        });
    }
}

        add(overlayPanel, BorderLayout.CENTER);



        // Make Frame Visible

        pack(); // Automatically sizes the frame to fit its components
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255,126,0));
        setBounds(0,0,screenWidth,screenHeight); // Center the frame on the screen
        setVisible(true);

 }//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////






 /// Read From text filed location file
public void saveValueOnly()
{

    File f = new File("textFiledsaveLoc.txt");
    try {
       Scanner n = new Scanner(f);

       while (n.hasNext()) {
           for (int i = 0; i < location.length; i++) {
               for (int j = 0; j < location[i].length; j++) {
                   this.location[i][j] = n.next();
               }
           }
       }
n.close();
       
    } catch (Exception e) {
       e.printStackTrace();
    }
}




///// Add value Function
        public void valueAdd(String lvl) {
            File file = new File(lvl);
            try {
                Scanner in = new Scanner(file);
                while (in.hasNext()) {
                    for (int i = 1; i <= 3; i++) {
                        for (int m = 1; m <= 3; m++) {
                            for (int j = 0; j < 3; j++) {
                                for (int k = 0; k < 3; k++) {
                                    if (i == 1 && m == 1) {
                                        this.array1[j][k] = in.next();
                                    }
                                    if (i == 1 && m == 2) {
                                        this.array2[j][k] = in.next();
                                    }
                                    if (i == 1 && m == 3) {
                                        this.array3[j][k] = in.next();
                                    }
                                    if (i == 2 && m == 1) {
                                        this.array4[j][k] = in.next();
                                    }
                                    if (i == 2 && m == 2) {
                                        this.array5[j][k] = in.next();
                                    }
                                    if (i == 2 && m == 3) {
                                        this.array6[j][k] = in.next();
                                    }
                                    if (i == 3 && m == 1) {
                                        this.array7[j][k] = in.next();
                                    }
                                    if (i == 3 && m == 2) {
                                        this.array8[j][k] = in.next();
                                    }
                                    if (i == 3 && m == 3) {
                                        this.array9[j][k] = in.next();
                                    }
                                }
                            }
                        }
                    }
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }






    /////////////////////////////////////////////////////////////////////////// Main Logic Function \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public boolean cheakBox(String userin, int row, int col) {
        boolean indicate = true;

        /// For first box
        if (0 <= row && row <= 2 && 0 <= col && col <= 2 && !userin.equals("")) {
            int count = 0;
            for (int i = 0; i <= 2; i++) {
                for (int j = 0; j <= 2; j++) {
                    if (userin.equals(this.textFields[i][j].getText())) {
                        System.out.println("1st  " + textFields[row][i].getText() + "  row: " + i + " col:  " + j);
                        count++;
                    }
                }
            }

            if (count > 1) {
                indicate = false;
            }
        }

        /// For second box
        else if (0 <= row && row <= 2 && 3 <= col && col <= 5 && !userin.equals("")) {
            int count = 0;
            for (int i = 0; i <= 2; i++) {
                for (int j = 3; j <= 5; j++) {
                    if (userin.equals(this.textFields[i][j].getText())) {
                        count++;
                    } 

                }
            }

            if (count > 1) {
                count = 0;
                indicate = false;
            }
        }

        // For third box
        else if (0 <= row && row <= 2 && 6 <= col && col <= 8 && !userin.equals("")) {
            int count = 0;
            for (int i = 0; i <= 2; i++) {
                for (int j = 6; j <= 8; j++) {
                    if (userin.equals(this.textFields[i][j].getText())) {
                        System.out.println("1st  " + textFields[row][i].getText() + "  row: " + i + " col:  " + j);
                        count++;
                    }

                }
            }

            if (count > 1) {
                 count =0;
                indicate = false;
            }
        }

        // For fourth box
        else if (3 <= row && row <= 5 && 0 <= col && col <= 2 && !userin.equals("")) {
            int count = 0;
            for (int i = 3; i <= 5; i++) {
                for (int j = 0; j <= 2; j++) {
                    if (userin.equals(this.textFields[i][j].getText())) {
                        count++;
                    } 

                }
            }

            if (count > 1) {
                 count =0;
                indicate = false;
            }
        }

        // For Five box
        else if (3 <= row && row <= 5 && 3 <= col && col <= 5 && !userin.equals("")) {
            int count = 0;
            for (int i = 3; i <= 5; i++) {
                for (int j = 3; j <= 5; j++) {
                    if (userin.equals(this.textFields[i][j].getText())) {
                        count++;
                    }
                }
            }

            if (count > 1) {
                count=0;
                indicate = false;
            }
        }

        // For Six box
        else if (3 <= row && row <= 5 && 6 <= col && col <= 8 && !userin.equals("")) {
            int count = 0;
            for (int i = 3; i <= 5; i++) {
                for (int j = 6; j <= 8; j++) {
                    if (userin.equals(this.textFields[i][j].getText())) {
                        count++;
                    } 

                }
            }

            if (count > 1) {
                count=0;
                indicate = false;
            }
        }

        // For seven box
        else if (6 <= row && row <= 8 && 0 <= col && col <= 2 && !userin.equals("")) {
            int count = 0;
            for (int i = 6; i <= 8; i++) {
                for (int j = 0; j <= 2; j++) {
                    if (userin.equals(this.textFields[i][j].getText())) {
                        count++;
                    } 
                }
            }

            if (count > 1) {
                count=0;
                indicate = false;
            }
        }

        // For Eight box
        else if (6 <= row && row <= 8 && 3 <= col && col <= 5 && !userin.equals("")) {
            int count = 0;
            for (int i = 6; i <= 8; i++) {
                for (int j = 3; j <= 5; j++) {
                    if (userin.equals(this.textFields[i][j].getText())) {
                        count++;
                    } 
                }
            }

            if (count > 1) {
                count=0;
                indicate = false;
            }
        }

        // For Nine box
        else if (6 <= row && row <= 8 && 6 <= col && col <= 8 && !userin.equals("")) {
            int count = 0;
            for (int i = 6; i <= 8; i++) {
                for (int j = 6; j <= 8; j++) {
                    if (userin.equals(this.textFields[i][j].getText())) {
                        count++;
                    } 
                }
            }

            if (count > 1) {
               count=0;
                indicate = false;
            }
        }

        if (indicate == true) {
            indicate = cheakRowCol(userin, row, col);
        }

        return indicate;
    }

    /////// FUNCTION FOR ROW AND COLUM WISE CHEACK
    public boolean cheakRowCol(String userin, int row, int col) {
        boolean indicate = true;

        for (int i = 0; i < 9; i++) {
            if (userin.equals(this.textFields[row][i].getText()) && col != i) {
                System.out.println("1st  " + textFields[row][i].getText() + "  row: " + row + " col:  " + i);
                indicate = false;
                break;
            }
            if (userin.equals(this.textFields[i][col].getText()) && row != i) {
                System.out.println("2ND  " + textFields[row][i].getText() + "  row: " + i + " col:  " + col);
                indicate = false;
                break;
            }
        }

        return indicate;
    }



/////  Button Action/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == save) {

        File loc = new File("textFiledsaveLoc.txt");
        try {
            loc.createNewFile();
        } catch (Exception aa) {
            aa.printStackTrace();
        }


        try {
            Formatter loca= new Formatter(loc);

            for (int i = 0; i <9; i++) {
                for (int j = 0; j <9; j++) {
                    loca.format("%s ", location[i][j]);
                }
                loca.format("\n");
            }
            loca.close();
    

            
        } catch (Exception lool) {
           lool.printStackTrace();
        }


System.out.println(""+this.lvl1);


        /// Level Save
        File file1 = new File("saveLevelgame.txt");


        try {
            Formatter form1 = new Formatter(file1);
                 form1.format("%s", this.lvl1); 
                 form1.close();       
            
        } catch (Exception a) {
            a.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbcd = new GridBagConstraints();
        gbcd.anchor = GridBagConstraints.CENTER;

        JLabel label = new JLabel("Save Game");
        label.setForeground(new Color(254, 113, 21)); 
        label.setFont(new Font("Eracake", Font.PLAIN, 36)); 

        panel.add(label, gbcd);

        panel.setPreferredSize(new Dimension(300, 150));

        JOptionPane.showMessageDialog(null, panel, "Sudoku -> Message", JOptionPane.PLAIN_MESSAGE, new ImageIcon("a_sudoku_logo.png"));


    }
    else if (e.getSource() == reset) {

        new sudokuBoard(lvl);
    }
    else if (e.getSource() == home) {
        new mainMenu();
        setVisible(false);
    }
    else if (e.getSource() == exitG) {
        System.exit(0);
    }
    
    
    /// Important part
    else if(e.getSource()== next)
    {
        boolean p=false;

        int array[] = new int[81];
        String l ="";
        int k=0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(l.equals(textFields[i][j].getText()))
                {
                    p=true;      
                }
                else
                {
                int n =Integer.parseInt(this.textFields[i][j].getText());
                array[k]= n;
                k++;
                }
            }
        }

        if(p==false)
        {

        
        MergeSort(array,0,array.length-1);

        boolean cheack = cheakSudoku(array);

        if(cheack==true)
        {
           new congLevelUp(lvl1);
        }
        else
        {
            JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbcd = new GridBagConstraints();
        gbcd.anchor = GridBagConstraints.CENTER;

        JLabel label = new JLabel("Red Box!");
        label.setForeground(new Color(254, 113, 21)); 
        label.setFont(new Font("Eracake", Font.PLAIN, 36)); 

        panel.add(label, gbcd);

        panel.setPreferredSize(new Dimension(300, 150));

        JOptionPane.showMessageDialog(null, panel, "Sudoku -> Message", JOptionPane.PLAIN_MESSAGE, new ImageIcon("a_sudoku_logo.png"));
        }
    }
    else
    {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbcd = new GridBagConstraints();
        gbcd.anchor = GridBagConstraints.CENTER;

        JLabel label = new JLabel("Empty Box!");
        label.setForeground(new Color(254, 113, 21)); 
        label.setFont(new Font("Eracake", Font.PLAIN, 36)); 

        panel.add(label, gbcd);

        panel.setPreferredSize(new Dimension(300, 150));

        JOptionPane.showMessageDialog(null, panel, "Sudoku -> Message", JOptionPane.PLAIN_MESSAGE, new ImageIcon("a_sudoku_logo.png"));
    }


    }
}/////////////////////////////////////////////// 


public boolean cheakSudoku(int array[])
{
    boolean b[]=new boolean[9];
    boolean bo = false;

    /// cheak value 1
    int count=0;
    for (int i = 0; i <=8; i++) {
        if(array[i]==1)
        {
          count++;
        }
    }
    if(count==9)
    {
        b[0]=true;
    }

    /// cheack value 2
    count=0;
    for (int i = 9; i <=17; i++) {
        if(array[i]==2)
        {
          count++;
        }
    }
    if(count==9)
    {
        b[1]=true;
    }

        /// cheack value 3
        count=0;
        for (int i = 18; i <=26; i++) {
            if(array[i]==3)
            {
              count++;
            }
        }
        if(count==9)
        {
            b[2]=true;
        }

                /// cheack value 4
                count=0;
                for (int i = 27; i <=35; i++) {
                    if(array[i]==4)
                    {
                      count++;
                    }
                }
                if(count==9)
                {
                    b[3] = true;
                }

                /// cheack value 5
                count = 0;
                for (int i = 36; i <= 44; i++) {
                    if (array[i] == 5) {
                        count++;
                    }
                }
                if (count == 9) {
                    b[4] = true;
                }

                /// cheack value 6
                count = 0;
                for (int i = 45; i <= 53; i++) {
                    if (array[i] == 6) {
                        count++;
                    }
                }
                if (count == 9) {
                    b[5] = true;
                }

                /// cheack value 7
                count = 0;
                for (int i = 54; i <= 62; i++) {
                    if (array[i] == 7) {
                        count++;
                    }
                }
                if (count == 9) {
                    b[6] = true;
                }

                /// cheack value 8
                count = 0;
                for (int i = 63; i <= 71; i++) {
                    if (array[i] == 8) {
                        count++;
                    }
                }
                if (count == 9) {
                    b[7] = true;
                }

                /// cheack value 9
                count = 0;
                for (int i = 72; i <= 80; i++) {
                    if (array[i] == 9) {
                        count++;
                    }
                }
                if (count == 9) {
                    b[8] = true;
                }



                count=0;
                for (int i = 0; i < b.length; i++) {
                    if(b[i]==true)
                    {
                        count++;
                    }
                }
                if(count==9)
                {
                    bo=true;
                }else
                {
                    bo=false;
                }



    return bo;
}



/// Mearge Sort applying
public void MergeSort(int array[],int s,int e)
{
    if(s<e)
    {
        int mid=(s+e)/2;

        MergeSort(array, s, mid);
        MergeSort(array, mid+1, e);

        merge(array,s,mid,e);
    }

}

public  void merge(int array[],int s,int mid,int e)
{
    int l = mid-s+1;
    int r = e-mid;

    int leftArr[] = new int[l];
    int righArr[] = new int[r];

    int k = s;

    for (int i = 0; i < leftArr.length; i++) {
        leftArr[i] = array[k];
        k++;
    }

    k = mid+1;
    for (int i = 0; i < righArr.length; i++) {
        righArr[i] = array[k];
        k++;
    }

    int i=0;
    int j=0;
    k=s;

    while(i<l && j<r)
    {
        if(leftArr[i]<=righArr[j])
        {
            array[k] = leftArr[i];           
             i++;
        }
        else{
            array[k] = righArr[j];
            j++;
        }
        k++;
    }


    while(i<l)
    {
        array[k] = leftArr[i];
        i++;
        k++;
    }

    while(j<r)
    {
        array[k] = righArr[j];
        j++;
        k++;
    }
}


public static void main(String[] args) {
    new sudokuBoard("level01.txt");
}

}
