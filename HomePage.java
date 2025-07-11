import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class HomePage
{
    private JFrame homePage;
    private JButton startGameButton, instructionsButton, achievementsButton;
    private JLabel creditsLabel;



    public HomePage()
    {
        homePage = new JFrame("Home Page");
        homePage.setPreferredSize(new Dimension(1000, 1000));
        homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homePage.pack();
        homePage.setLocationRelativeTo(null);
        homePage.setResizable(false);

        BackgroundPanel background = new BackgroundPanel("Images/MusicBackground.jpg");
        background.setLayout(null);

        // Title with attractive styling
        JLabel title = new JLabel("Gaston's Song Guessing Game");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 50)); // More regal font
        title.setForeground(new Color(255, 215, 0)); // Gold
        title.setBounds(100, 50, 850, 80);
        title.setOpaque(true);
        title.setVisible(true);
        title.setBackground(new Color(0, 0, 0, 150)); // Slightly more transparent black
        title.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 180, 140), 5), // Light brown border
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        background.add(title);

        // Start Button with improved style
        startGameButton = createStyledButton("Start Game");
        startGameButton.setBounds(400, 300, 200, 60);
        background.add(startGameButton);

        // instructionsButtons Button with improved style
        instructionsButton = createStyledButton("Instructions");
        instructionsButton.setBounds(400, 450, 200, 60);
        background.add(instructionsButton);

        // instructionsButtons Button with improved style
        achievementsButton = createStyledButton("Achievements");
        achievementsButton.setBounds(400, 600, 200, 60);
        background.add(achievementsButton);



        // Attractive Credits Label
        creditsLabel = new JLabel("Created By: Gaston C. | Last Updated: July 10, 2025", SwingConstants.CENTER);
        creditsLabel.setFont(new Font("Goudy Old Style", Font.ITALIC, 24)); // Elegant, old-style font
        creditsLabel.setForeground(new Color(255, 223, 0)); // Light gold
        creditsLabel.setBounds(100, 900, 800, 60);
        creditsLabel.setOpaque(true);
        creditsLabel.setBackground(new Color(0, 0, 0, 180)); // Semi-transparent black
        creditsLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(160, 82, 45), 3), // Sienna border
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        background.add(creditsLabel);

        // Add background
        homePage.setContentPane(background);
        homePage.setVisible(true);

        // Button actions
        startGameButton.addActionListener(e -> {
            homePage.setVisible(false);
            new DifficultyPage();
        });

        instructionsButton.addActionListener(e -> {
            homePage.setVisible(false);
            new InstructionsPage();
        });

        achievementsButton.addActionListener(e -> {
            homePage.setVisible(false);
            new AchievementsPage();
        });
    }



    private JButton createStyledButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Georgia", Font.BOLD, 20)); // Slightly larger font
        button.setForeground(Color.WHITE); // Off-white
        button.setBackground(new Color(50,30,10)); // Saddle brown
        button.setBorder(BorderFactory.createLineBorder(new Color(205, 133, 63), 4)); // Peru border
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);
        button.setOpaque(true);

        button.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent e)
            {
                button.setBackground(new Color(160, 82, 45)); // Sienna on hover
            }

            public void mouseExited(MouseEvent e)
            {
                button.setBackground(new Color(50,30,10)); // Saddle brown on exit
            }
        }
        
        );

        return button;
    }

    // Custom panel that loads and draws background image
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                System.err.println("Failed to load background image.");
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}