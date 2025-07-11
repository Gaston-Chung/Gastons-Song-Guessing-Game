import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class DifficultyPage extends JFrame
{
    //JComps
    private JLabel titleLabel;
    private JButton easyButton, normalButton, hardButton, extremeButton;
    private JLabel easyDescription, normalDescription, hardDescription, extremeDescription;
    private JButton backButton;
    private JLabel unlockedTagLabel;

    private ImageIcon easyCheckmark, normalCheckmark, hardCheckmark, extremeCheckmark;
    private ImageIcon easyStar, normalStar, hardStar, extremeStar;
    private JLabel easyCheckmarkLabel, normalCheckmarkLabel, hardCheckmarkLabel, extremeCheckmarkLabel;
    private JLabel easyStarLabel, normalStarLabel, hardStarLabel, extremeStarLabel;

    //Non JComp
    public static boolean completedEasyMode, completedNormalMode, completedHardMode, completedExtremeMode = false;
    public static boolean perfectedEasyMode, perfectedNormalMode, perfectedHardMode, perfectedExtremeMode = false;
    public static boolean easyLock, normalLock, hardLock, extremeLock = false;

    //Achievent Timer Stuff
    public static AchievementListener t2;
    public static Timer achievementsTimer;
    public static int achievementTimeLeft = 30;



    public DifficultyPage()
    {
        perfectedEasyMode = true; 
        perfectedNormalMode = true; 
        perfectedHardMode = true; 
        perfectedExtremeMode = true; //Toggle this on and off to test Extreme Mode appearance on this screen

        // JFrame Setup
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // Background Panel Setup
        BackgroundPanel background = new BackgroundPanel();
        background.setLayout(null); // Use null layout for absolute positioning
        this.setContentPane(background);

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(150, 50, 250));
        titlePanel.setBounds(0, 0, 1000, 80);
        titlePanel.setLayout(null);
        this.add(titlePanel);

        JLabel titleLabel = new JLabel("😃 😐 Select Your Difficulty 😡 🤬", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 36));
        titleLabel.setBounds(250, 15, 550, 50);
        titlePanel.add(titleLabel);


        
        easyButton = new JButton("Easy");
        normalButton = new JButton("Normal");
        hardButton = new JButton("Hard");
        extremeButton = new JButton("EXTREME");

        easyButton.setFont(new Font("Helvetica", Font.BOLD, 30));
        normalButton.setFont(new Font("Helvetica", Font.BOLD, 30));
        hardButton.setFont(new Font("Helvetica", Font.BOLD, 30));
        extremeButton.setFont(new Font("Impact", Font.BOLD, 40));
        
        easyButton.setBackground(new Color(0, 255, 30));
        easyButton.setForeground(Color.BLACK);
        easyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        easyButton.setFocusPainted(false);
        easyButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        easyButton.setOpaque(true);

        easyButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) {
                easyButton.setBackground(new Color(0, 200, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                easyButton.setBackground(new Color(0, 255, 30));
            }
        });

        normalButton.setBackground(new Color(255, 242, 64));
        normalButton.setForeground(Color.BLACK);
        normalButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        normalButton.setFocusPainted(false);
        normalButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        normalButton.setOpaque(true);

        normalButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) {
                normalButton.setBackground(new Color(200, 200, 30));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                normalButton.setBackground(new Color(255, 242, 64));
            }
        });

        hardButton.setBackground(new Color(250, 0, 0));
        hardButton.setForeground(Color.BLACK);
        hardButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        hardButton.setFocusPainted(false);
        hardButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        hardButton.setOpaque(true);

        hardButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) {
                hardButton.setBackground(new Color(150, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hardButton.setBackground(new Color(250, 0, 0));
            }
        });

        extremeButton.setBackground(new Color(0,0,0));
        extremeButton.setForeground(Color.WHITE);
        extremeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        extremeButton.setFocusPainted(false);
        extremeButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        extremeButton.setOpaque(true);

        extremeButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) {
                extremeButton.setBackground(new Color(100, 100, 100));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                extremeButton.setBackground(new Color(0,0,0));
            }
        });



        easyButton.addActionListener(e -> {
            SongGuessingPage.easyMode = true; //Set the difficulty
            new SongGuessingPage(); //Create the actual game page
            dispose();

            achievementTimeLeft = 30;
            t2 = new AchievementListener();
            achievementsTimer = new Timer(1000, t2);
            achievementsTimer.start();
        });

        normalButton.addActionListener(e -> {
            SongGuessingPage.normalMode = true;
            new SongGuessingPage();
            dispose();

            achievementTimeLeft = 30;
            t2 = new AchievementListener();
            achievementsTimer = new Timer(1000, t2);
            achievementsTimer.start();
        });

        hardButton.addActionListener(e -> {
            SongGuessingPage.hardMode = true;
            new SongGuessingPage();
            dispose();

            achievementTimeLeft = 30;
            t2 = new AchievementListener();
            achievementsTimer = new Timer(1000, t2);
            achievementsTimer.start();
        });

        extremeButton.addActionListener(e -> {
            SongGuessingPage.extremeMode = true;
            new SongGuessingPage();
            dispose();

            achievementTimeLeft = 30;
            t2 = new AchievementListener();
            achievementsTimer = new Timer(1000, t2);
            achievementsTimer.start();
        });


        
        easyDescription = new JLabel("<html> 🎵 ‎ ‎ 3 Seconds of Song Playtime <br><br> 🔄 ‎ ‎ ∞ Song Replays <br><br> 🕗 ‎ ‎ ∞ Time </div> <html>");
        easyDescription.setFont(new Font("SansSerif", Font.PLAIN, 25));
        easyDescription.setForeground(Color.BLACK);
        easyDescription.setBackground(new Color(0, 0, 0, 200));
        easyDescription.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        normalDescription = new JLabel("<html> 🎵 ‎ ‎ 2 Seconds of Song Playtime <br><br> 🔄 ‎ ‎ 3 Song Replays <br><br> 🕗 ‎ ‎ 10 Second Time Limit <html>");
        normalDescription.setFont(new Font("SansSerif", Font.PLAIN, 25));
        normalDescription.setForeground(Color.BLACK);
        normalDescription.setBackground(new Color(0, 0, 0, 200));
        normalDescription.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        hardDescription = new JLabel("<html> 🎵 ‎ ‎ 1 Second of Song Playtime <br><br> 🔄 ‎ ‎ 1 Song Replay <br><br> 🕗 ‎ ‎ 3 Second Time Limit <html>");
        hardDescription.setFont(new Font("SansSerif", Font.PLAIN, 25));
        hardDescription.setForeground(Color.BLACK);
        hardDescription.setBackground(new Color(0, 0, 0, 200));
        hardDescription.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        extremeDescription = new JLabel("<html> 🎵 ‎ ‎ 0.1 Seconds of Song Playtime <br><br> 🔄 ‎ ‎ ∞ Song Replays <br><br> 🕗 ‎ ‎ 5 Second Time Limit <html>");
        extremeDescription.setFont(new Font("SansSerif", Font.PLAIN, 25));
        extremeDescription.setForeground(Color.BLACK);
        extremeDescription.setBackground(new Color(0, 0, 0, 200));
        extremeDescription.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        /////////////////////////////////////////////////////////////////////////
        
        easyButton.setBounds(150, 170, 250, 75);
        easyDescription.setBounds(55, 190, 430, 300);

        normalButton.setBounds(590, 170, 250, 75);
        normalDescription.setBounds(505, 190, 430, 300);

        hardButton.setBounds(150, 520, 250, 75);
        hardDescription.setBounds(55, 540, 430, 300);

        extremeButton.setBounds(590, 520, 250, 75);
        extremeDescription.setBounds(505, 540, 460, 300);


        background.add(easyButton);
        background.add(normalButton);
        background.add(hardButton);
        background.add(extremeButton);
        
        background.add(easyDescription);
        background.add(normalDescription);
        background.add(hardDescription);
        background.add(extremeDescription);



        //Checkmarks & Stars
        ImageIcon checkmark = loadScaledIcon("Images/Checkmark.jpg", 75, 75);
        JLabel easyCheckmarkLabel = new JLabel(checkmark); JLabel normalCheckmarkLabel = new JLabel(checkmark); JLabel hardCheckmarkLabel = new JLabel(checkmark); JLabel extremeCheckmarkLabel = new JLabel(checkmark);
        easyCheckmarkLabel.setBounds(60, 170, 75, 75); normalCheckmarkLabel.setBounds(500, 170, 75, 75); hardCheckmarkLabel.setBounds(60, 520, 75, 75); extremeCheckmarkLabel.setBounds(500, 520, 75, 75);
        this.add(easyCheckmarkLabel); this.add(normalCheckmarkLabel); this.add(hardCheckmarkLabel); this.add(extremeCheckmarkLabel);
        easyCheckmarkLabel.setVisible(false); normalCheckmarkLabel.setVisible(false); hardCheckmarkLabel.setVisible(false); extremeCheckmarkLabel.setVisible(false);

        ImageIcon star = loadScaledIcon("Images/Star.jpg", 75, 75);
        JLabel easyStarLabel = new JLabel(easyStar); JLabel normalStarLabel = new JLabel(easyStar); JLabel hardStarLabel = new JLabel(easyStar); JLabel extremeStarLabel = new JLabel(easyStar);
        easyStarLabel.setBounds(60, 170, 75, 75); normalStarLabel.setBounds(500, 170, 75, 75); hardStarLabel.setBounds(60, 520, 75, 75); extremeStarLabel.setBounds(500, 520, 75, 75);
        this.add(easyStarLabel); this.add(normalStarLabel); this.add(hardStarLabel); this.add(extremeStarLabel);
        easyStarLabel.setVisible(false); normalStarLabel.setVisible(false); hardStarLabel.setVisible(false); extremeStarLabel.setVisible(false);
        


        if (perfectedEasyMode == true)
        {
            easyStarLabel.setVisible(true);
            easyCheckmarkLabel.setVisible(false);
        }

        if (completedEasyMode == true)
        {
            easyStarLabel.setVisible(false);
            easyCheckmarkLabel.setVisible(true);
        }

        if (perfectedNormalMode == true)
        {
            normalStarLabel.setVisible(true);
            normalCheckmarkLabel.setVisible(false);
        }

        if (completedNormalMode == true)
        {
            normalStarLabel.setVisible(false);
            normalCheckmarkLabel.setVisible(true);
        }

        if (perfectedHardMode == true)
        {
            hardStarLabel.setVisible(true);
            hardCheckmarkLabel.setVisible(false);
        }

        if (completedHardMode == true)
        {
            hardStarLabel.setVisible(false);
            hardCheckmarkLabel.setVisible(true);
        }

        if (perfectedExtremeMode == true)
        {
            extremeStarLabel.setVisible(true);
            extremeCheckmarkLabel.setVisible(false);
        }

        if (completedExtremeMode == true)
        {
            extremeStarLabel.setVisible(false);
            extremeCheckmarkLabel.setVisible(true);
        }



        //
        unlockedTagLabel = new JLabel("<html> Unlocked after beating" + "<br>" + "the game in Hard Mode" + "<br>" + "with a perfect score. <html>");
        unlockedTagLabel.setFont(new Font("SansSerif", Font.PLAIN, 23));
        unlockedTagLabel.setHorizontalAlignment(SwingConstants.CENTER);
        unlockedTagLabel.setBackground(new Color(0, 0, 0));
        unlockedTagLabel.setForeground(new Color(250, 250, 250));
        unlockedTagLabel.setOpaque(true);
        unlockedTagLabel.setBounds(572, 620, 285, 130);
        background.add(unlockedTagLabel);

        //
        extremeDescription.setVisible(false);
        extremeButton.setEnabled(false);
        if (perfectedHardMode == true)
        {
            extremeDescription.setVisible(true);
            extremeButton.setEnabled(true);
            unlockedTagLabel.setVisible(false);
        }





        // Back Button
        backButton = new JButton("Back To Menu");
        backButton.setFont(new Font("Helvetica", Font.BOLD, 25));
        backButton.setBackground(new Color(50, 100, 255));
        backButton.setForeground(Color.WHITE);
        // Position the button below the scroll pane
        int buttonWidth = 300;
        int buttonHeight = 60;
        int buttonX = (getWidth() - buttonWidth) / 2;
        int scrollY = 10 + 10 + 30; // Increased margin below title
        int scrollHeight = 400;
        int buttonY = scrollY + scrollHeight + 40; // Increased margin below scroll pane
        backButton.setBounds(buttonX, buttonY + 350, buttonWidth, buttonHeight);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        backButton.setOpaque(true);

        backButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(new Color(50, 150, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setBackground(new Color(50, 100, 255));
            }
        });

        backButton.addActionListener(e -> {
            new HomePage();
            dispose();
        });

        background.add(backButton);

        this.setVisible(true);
    }



    public static class AchievementListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            achievementTimeLeft--;
            System.out.println("" + achievementTimeLeft);
        }
    }



    // Gradient Background Panel
    static class BackgroundPanel extends JPanel
    {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(0, 0, new Color(0, 225, 255), 0, getHeight(), new Color(255, 230, 89));
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }



    public static ImageIcon loadScaledIcon(String path, int width, int height)
    {
        ImageIcon icon = new ImageIcon(path);
        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }



    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(DifficultyPage::new);
    }
}