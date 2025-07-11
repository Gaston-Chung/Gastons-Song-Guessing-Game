import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;



public class AchievementsPage extends JFrame
{
    //JComps
    private JLabel titleLabel;
    private JLabel bronzeSpeedsterTitle, silverSpeedsterTitle, goldSpeedsterTitle, diamondSpeedsterTitle, extremityTitle, superstarTitle, allDayTitle, completionistTitle;
    private JLabel bronzeSpeedsterDesc, silverSpeedsterDesc, goldSpeedsterDesc, diamondSpeedsterDesc, extremityDesc, superstarDesc, allDayDesc, completionistDesc;
    private JButton backButton;

    private ImageIcon bronze, silver, gold, diamond, skull, superstars, hundred, trophy;

    //Non JComp
    public static boolean bronzeSpeedster, silverSpeedster, goldSpeedster, diamondSpeedster, extremity, superstar, allDay, completionist = false;



    public AchievementsPage()
    {
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

        JLabel titleLabel = new JLabel("🏆 Achievements 🏆", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 35));
        titleLabel.setBounds(250, 15, 550, 50);
        titlePanel.add(titleLabel);



        Font titleFont = new Font("Helvetica", Font.BOLD, 30);
        Font descFont = new Font("Helvetica", Font.PLAIN, 24);

        bronzeSpeedsterTitle = new JLabel("Bronze Speedster");
        silverSpeedsterTitle = new JLabel("Silver Speedster");
        goldSpeedsterTitle = new JLabel("Gold Speedster");
        diamondSpeedsterTitle = new JLabel("Diamond Speedster");
        extremityTitle = new JLabel("Extremity");
        superstarTitle = new JLabel("Superstar");
        allDayTitle = new JLabel("I Can Do This All Day");
        completionistTitle = new JLabel("Completionist");

        bronzeSpeedsterDesc = new JLabel("<html> Get a perfect score on Easy <br> Mode in less than 30 seconds. <html>"); 
        silverSpeedsterDesc = new JLabel("<html> Get a perfect score on Normal <br> Mode in less than 30 seconds. <html>");
        goldSpeedsterDesc = new JLabel("<html> Get a perfect score on Hard <br> Mode in less than 30 seconds. <html>");
        diamondSpeedsterDesc = new JLabel("<html> Get a perfect score on Extreme <br> Mode in less than 30 seconds. <html>");
        extremityDesc = new JLabel("<html> Complete Extreme Mode <br> with a perfect score. <html>");
        superstarDesc = new JLabel("<html> Collect all 4 stars on <br> all difficulties. <html>");
        allDayDesc = new JLabel("<html> Play 10 total games, of any difficulty. <html>");
        completionistDesc = new JLabel("<html> Complete all 8 achievements. <html>");

        bronzeSpeedsterTitle.setBounds(150, 115, 350, 30);
        silverSpeedsterTitle.setBounds(150, 315, 350, 30);
        goldSpeedsterTitle.setBounds(150, 515, 350, 30);
        diamondSpeedsterTitle.setBounds(150, 715, 350, 30);
        extremityTitle.setBounds(650, 115, 350, 30);
        superstarTitle.setBounds(650, 315, 350, 30);
        allDayTitle.setBounds(650, 515, 350, 30);
        completionistTitle.setBounds(650, 715, 350, 30);

        bronzeSpeedsterDesc.setBounds(150, 150, 450, 60);
        silverSpeedsterDesc.setBounds(150, 350, 450, 60);
        goldSpeedsterDesc.setBounds(150, 550, 450, 60);
        diamondSpeedsterDesc.setBounds(150, 750, 450, 60);
        extremityDesc.setBounds(650, 150, 350, 60);
        superstarDesc.setBounds(650, 350, 350, 60);
        allDayDesc.setBounds(650, 550, 350, 60);
        completionistDesc.setBounds(650, 750, 350, 60);

        bronzeSpeedsterTitle.setFont(titleFont);
        silverSpeedsterTitle.setFont(titleFont);
        goldSpeedsterTitle.setFont(titleFont);
        diamondSpeedsterTitle.setFont(titleFont);
        extremityTitle.setFont(titleFont);
        superstarTitle.setFont(titleFont);
        allDayTitle.setFont(titleFont);
        completionistTitle.setFont(titleFont);
        bronzeSpeedsterDesc.setFont(descFont);
        silverSpeedsterDesc.setFont(descFont);
        goldSpeedsterDesc.setFont(descFont);
        diamondSpeedsterDesc.setFont(descFont);
        extremityDesc.setFont(descFont);
        superstarDesc.setFont(descFont);
        allDayDesc.setFont(descFont);
        completionistDesc.setFont(descFont);

        this.add(bronzeSpeedsterTitle);
        this.add(silverSpeedsterTitle);
        this.add(goldSpeedsterTitle);
        this.add(diamondSpeedsterTitle);
        this.add(extremityTitle);
        this.add(superstarTitle);
        this.add(allDayTitle);
        this.add(completionistTitle);
        this.add(bronzeSpeedsterDesc);
        this.add(silverSpeedsterDesc);
        this.add(goldSpeedsterDesc);
        this.add(diamondSpeedsterDesc);
        this.add(extremityDesc);
        this.add(superstarDesc);
        this.add(allDayDesc);
        this.add(completionistDesc);



        //Bronze Icon
        try
        {
            BufferedImage ogBronze = ImageIO.read(new File("Images/Bronze.jpg"));
            BufferedImage resized = scaleImage(ogBronze, 80, 125); // scale to desired size

            if (bronzeSpeedster == true)
            {
                JLabel bronzeLabel = new JLabel(new ImageIcon(resized));
                bronzeLabel.setBounds(40, 100, 75, 125);
                this.add(bronzeLabel);
            }

            else
            {
                BufferedImage darkened = darkenImage(resized, 0.1f);   // then darken it
                JLabel bronzeLabel = new JLabel(new ImageIcon(darkened));
                bronzeLabel.setBounds(40, 100, 75, 125);
                this.add(bronzeLabel);
            }

        } catch (IOException e) { e.printStackTrace(); } //I need to do this or else it wont let me compile

        //Silver Icon
        try
        {
            BufferedImage ogSilver = ImageIO.read(new File("Images/Silver.jpg"));
            BufferedImage resized = scaleImage(ogSilver, 80, 125); // scale to desired size

            if (silverSpeedster == true)
            {
                JLabel silverLabel = new JLabel(new ImageIcon(resized));
                silverLabel.setBounds(40, 300, 75, 125);
                this.add(silverLabel);
            }

            else
            {
                BufferedImage darkened = darkenImage(resized, 0.1f);   // then darken it
                JLabel silverLabel = new JLabel(new ImageIcon(darkened));
                silverLabel.setBounds(40, 300, 75, 125);
                this.add(silverLabel);
            }

        } catch (IOException e) { e.printStackTrace(); } //I need to do this or else it wont let me compile

        //Gold Icon
        try
        {
            BufferedImage ogGold = ImageIO.read(new File("Images/Gold.jpg"));
            BufferedImage resized = scaleImage(ogGold, 80, 125); // scale to desired size

            if (goldSpeedster == true)
            {
                JLabel goldLabel = new JLabel(new ImageIcon(resized));
                goldLabel.setBounds(40, 500, 80, 125);
                this.add(goldLabel);
            }

            else
            {
                BufferedImage darkened = darkenImage(resized, 0.1f);   // then darken it
                JLabel goldLabel = new JLabel(new ImageIcon(darkened));
                goldLabel.setBounds(40, 500, 80, 125);
                this.add(goldLabel);
            }

        } catch (IOException e) { e.printStackTrace(); } //I need to do this or else it wont let me compile

        //Diamond Icon
        try
        {
            BufferedImage ogDiamond = ImageIO.read(new File("Images/Diamond.jpg"));
            BufferedImage resized = scaleImage(ogDiamond, 85, 125); // scale to desired size

            if (diamondSpeedster == true)
            {
                JLabel diamondLabel = new JLabel(new ImageIcon(resized));
                diamondLabel.setBounds(40, 700, 85, 125);
                this.add(diamondLabel);
            }

            else
            {
                BufferedImage darkened = darkenImage(resized, 0.1f);   // then darken it
                JLabel diamondLabel = new JLabel(new ImageIcon(darkened));
                diamondLabel.setBounds(40, 700, 85, 125);
                this.add(diamondLabel);
            }

        } catch (IOException e) { e.printStackTrace(); } //I need to do this or else it wont let me compile

        //Skull Icon
        try
        {
            BufferedImage ogSkull = ImageIO.read(new File("Images/Skull.jpg"));
            BufferedImage resized = scaleImage(ogSkull, 135, 100); // scale to desired size

            if (extremity == true)
            {
                JLabel skullLabel = new JLabel(new ImageIcon(resized));
                skullLabel.setBounds(510, 100, 135, 100);
                this.add(skullLabel);
            }

            else
            {
                BufferedImage darkened = darkenImage(resized, 0.1f);   // then darken it
                JLabel skullLabel = new JLabel(new ImageIcon(darkened));
                skullLabel.setBounds(510, 100, 135, 100);
                this.add(skullLabel);
            }

        } catch (IOException e) { e.printStackTrace(); } //I need to do this or else it wont let me compile

        //Superstar Icon
        try
        {
            BufferedImage ogStar = ImageIO.read(new File("Images/Stars.jpg"));
            BufferedImage resized = scaleImage(ogStar, 125, 115); // scale to desired size

            if (diamondSpeedster == true)
            {
                JLabel superstarLabel = new JLabel(new ImageIcon(resized));
                superstarLabel.setBounds(510, 300, 125, 115);
                this.add(superstarLabel);
            }

            else
            {
                BufferedImage darkened = darkenImage(resized, 0.1f);   // then darken it
                JLabel superstarLabel = new JLabel(new ImageIcon(darkened));
                superstarLabel.setBounds(510, 300, 125, 115);
                this.add(superstarLabel);
            }

        } catch (IOException e) { e.printStackTrace(); } //I need to do this or else it wont let me compile

        //All Day Icon
        try
        {
            BufferedImage og100 = ImageIO.read(new File("Images/100.jpg"));
            BufferedImage resized = scaleImage(og100, 125, 115); // scale to desired size

            if (allDay == true)
            {
                JLabel hundredLabel = new JLabel(new ImageIcon(resized));
                hundredLabel.setBounds(510, 500, 125, 115);
                this.add(hundredLabel);
            }

            else
            {
                BufferedImage darkened = darkenImage(resized, 0.1f);   // then darken it
                JLabel hundredLabel = new JLabel(new ImageIcon(darkened));
                hundredLabel.setBounds(510, 500, 125, 115);
                this.add(hundredLabel);
            }

        } catch (IOException e) { e.printStackTrace(); } //I need to do this or else it wont let me compile

        //Trophy Icon
        try
        {
            BufferedImage ogTrophy = ImageIO.read(new File("Images/Trophy.jpg"));
            BufferedImage resized = scaleImage(ogTrophy, 150, 150); // scale to desired size

            if (completionist == true)
            {
                JLabel trophyLabel = new JLabel(new ImageIcon(resized));
                trophyLabel.setBounds(500, 685, 150, 150);
                this.add(trophyLabel);
            }

            else
            {
                BufferedImage darkened = darkenImage(resized, 0.1f);   // then darken it
                JLabel trophyLabel = new JLabel(new ImageIcon(darkened));
                trophyLabel.setBounds(500, 685, 150, 150);
                this.add(trophyLabel);
            }

        } catch (IOException e) { e.printStackTrace(); } //I need to do this or else it wont let me compile



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
        backButton.setBounds(buttonX, buttonY + 400, buttonWidth, buttonHeight);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        backButton.setOpaque(true);
        backButton.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent e) { backButton.setBackground(new Color(50, 150, 255)); }
            public void mouseExited(MouseEvent e) { backButton.setBackground(new Color(50, 100, 255)); }
        });

        backButton.addActionListener(e -> {
            new HomePage();
            dispose();
        });

        background.add(backButton);
        this.setVisible(true);
    }



    public static BufferedImage scaleImage(BufferedImage originalImage, int width, int height)
    {
        Image scaled = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        
        // Create a new BufferedImage with the desired size and draw the scaled image onto it
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); //This will be custom for each image since they all have different scales
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(scaled, 0, 0, null);
        g2d.dispose();
        
        return resized;
    }



    public static BufferedImage darkenImage(BufferedImage img, float brightnessFactor)
    {
        // brightnessFactor < 1.0 will darken; 0.0 = fully black
        RescaleOp op = new RescaleOp(brightnessFactor, 0, null);
        BufferedImage dark = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        op.filter(img, dark);
        return dark;
    }



    // Gradient Background Panel
    static class BackgroundPanel extends JPanel
    {
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(0, 0, new Color(0, 225, 255), 0, getHeight(), new Color(255, 230, 89));
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }



    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(AchievementsPage::new);
    }
}