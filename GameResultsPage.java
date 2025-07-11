import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class GameResultsPage extends JFrame
{
    //JComps
    private JButton playAgainButton;



    public GameResultsPage()
    {
        this.setTitle("Game Results");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // Center on screen

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0, 200, 255));
        titlePanel.setBounds(0, 0, 1000, 80);
        titlePanel.setLayout(null);
        this.add(titlePanel);

        JLabel titleLabel = new JLabel("📊 Game Results 📊", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        titleLabel.setBounds(300, 15, 400, 50);
        titlePanel.add(titleLabel);
        this.add(titlePanel);

        playAgainButton = new JButton("🔄 Play Again");
        playAgainButton.setFont(new Font("SansSerif", Font.BOLD, 28));
        playAgainButton.setBounds(375, 600, 250, 70);
        playAgainButton.addActionListener(new ButtonListener());
        playAgainButton.setBackground(Color.WHITE);
        playAgainButton.setForeground(Color.BLACK);
        playAgainButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        playAgainButton.setFocusPainted(false);
        playAgainButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        playAgainButton.setOpaque(true);

        this.add(playAgainButton);

        playAgainButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) {
                playAgainButton.setBackground(new Color(200, 200, 200));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                playAgainButton.setBackground(new Color(255,255,255));
            }
        });
        


        
        if (SongGuessingPage.playerPoints > SongGuessingPage.computerPoints)
        {
            showWinScreen();
        }
        
        else if (SongGuessingPage.playerPoints < SongGuessingPage.computerPoints)
        {
            showLossScreen();
        }

        else //If they tied
        {
            showTieScreen();
        }

        setVisible(true);
    }



    private void showWinScreen()
    {
        getContentPane().setBackground(Color.YELLOW);

        JPanel winPanel = new JPanel();
        winPanel.setLayout(null);
        winPanel.setBounds(0, 80, 1000, 720);
        winPanel.setBackground(Color.YELLOW);

        JLabel winLabel = new JLabel("🎉 You Won! 🥳", SwingConstants.CENTER);
        winLabel.setFont(new Font("SansSerif", Font.BOLD, 60));
        winLabel.setOpaque(true);
        winLabel.setBackground(Color.GREEN);
        winLabel.setBounds(250, 60, 500, 80);
        winPanel.add(winLabel);

        JLabel scoreLabel = new JLabel("Final Score: " + SongGuessingPage.playerPoints + " - " + SongGuessingPage.computerPoints, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 40));
        scoreLabel.setOpaque(true);
        scoreLabel.setBackground(Color.GREEN);
        scoreLabel.setBounds(300, 180, 400, 70);
        winPanel.add(scoreLabel);

        this.add(winPanel);

        SongGuessingPage.roundsWon++; //Incrementing number of rounds played for All Day achievement
    }



    private void showLossScreen()
    {
        getContentPane().setBackground(Color.BLACK);

        JPanel lossPanel = new JPanel();
        lossPanel.setLayout(null);
        lossPanel.setBounds(0, 80, 1000, 720);
        lossPanel.setBackground(Color.BLACK);

        JLabel lossLabel = new JLabel("💀 You Lost! 😔", SwingConstants.CENTER);
        lossLabel.setFont(new Font("SansSerif", Font.BOLD, 60));
        lossLabel.setOpaque(true);
        lossLabel.setBackground(Color.RED);
        lossLabel.setForeground(Color.WHITE);
        lossLabel.setBounds(250, 60, 500, 80);
        lossPanel.add(lossLabel);

        JLabel scoreLabel = new JLabel("Final Score: " + SongGuessingPage.playerPoints + " - " + SongGuessingPage.computerPoints, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 40));
        scoreLabel.setOpaque(true);
        scoreLabel.setBackground(Color.RED);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(300, 180, 400, 70);
        lossPanel.add(scoreLabel);

        this.add(lossPanel);
    }



    private void showTieScreen()
    {
        getContentPane().setBackground(Color.GRAY);

        JPanel tiePanel = new JPanel();
        tiePanel.setLayout(null);
        tiePanel.setBounds(0, 80, 1000, 720);
        tiePanel.setBackground(Color.GRAY);

        JLabel winLabel = new JLabel("😐 You Tied! 😐", SwingConstants.CENTER);
        winLabel.setFont(new Font("Helvetica", Font.BOLD, 45));
        winLabel.setOpaque(true);
        winLabel.setBackground(Color.WHITE);
        winLabel.setBounds(250, 60, 500, 80);
        tiePanel.add(winLabel);

        JLabel scoreLabel = new JLabel("Final Score: " + SongGuessingPage.playerPoints + " - " + SongGuessingPage.computerPoints, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 40));
        scoreLabel.setOpaque(true);
        scoreLabel.setBackground(Color.WHITE);
        scoreLabel.setBounds(300, 180, 400, 70);
        tiePanel.add(scoreLabel);

        JLabel tag = new JLabel("Better luck next time!", SwingConstants.CENTER);
        tag.setFont(new Font("SansSerif", Font.BOLD, 36));
        tag.setOpaque(true);
        tag.setBackground(Color.WHITE);
        tag.setBounds(300, 280, 400, 70);
        tiePanel.add(tag);

        this.add(tiePanel);
    }



    public class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == playAgainButton)
            {
                dispose();
                new HomePage();
                SongGuessingPage.playerPoints = 0;
                SongGuessingPage.computerPoints = 0;
                SongGuessingPage.currentRound = 1;
            }
        }
    }

    public static void main(String[] args)
    {
        new GameResultsPage();
    }
}