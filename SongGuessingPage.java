import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.util.List;



public class SongGuessingPage extends JFrame
{
    //JComps
    private JLabel titleLabel;
    private JPanel titlePanel;

    private JLabel roundsLabel, scoreLabel;
    private JPanel roundsPanel, scorePanel;

    private static JButton playButton;
    private PlayButtonListener pb1;

    private JLabel song1NameLabel, song2NameLabel, song3NameLabel, song4NameLabel;
    private ImageIcon song1Picture, song2Picture, song3Picture, song4Picture;
    private JButton song1Button, song2Button, song3Button, song4Button;

    private ButtonListener b1;
    
    private JPanel timePanel;
    private JLabel timeLabel;

    private Timer timer;
    private TimeListener t1;

    private JPanel lateMessagePanel;
    private JLabel lateMessageLabel;

    private JButton restartButton;
    private RestartButtonListener rb1;
    
    
    
    //Non-JComp Variables
    private static final int BYTES_PER_SECOND = 24000; // Rough estimate for 192 kbps
    private static Long fixedRandomStart = null; // Store fixed random start byte position
    private static byte [] cachedSnippet = null;  // Store the snippet bytes

    public static boolean easyMode;
    public static boolean normalMode;
    public static boolean hardMode;
    public static boolean extremeMode;

    public static int clipDuration = 0;
    public static int playerPoints, computerPoints;
    public static int currentRound = 1;
    private static final int totalRounds = 10;
    private static boolean correctAnswerFlag = false;
    private static int numberOfClicks;
    private int timeLeft = 10; //Just set it to some reasonable amount of time first because if its initialized to 0 then a new round will keep getting generated
    private boolean firstClick = true;
    public static int roundsWon;



    public SongGuessingPage()
    {
        super("Music Snippet Player");
        cachedSnippet = null; //Doing this loads a new song every round

        //Background Panel Setup
        BackgroundPanel background = new BackgroundPanel();
        background.setLayout(null); // Use null layout for absolute positioning
        this.setContentPane(background);

        // JFrame Setup
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);



        // Title Panel
        titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0, 200, 255));
        titlePanel.setBounds(0, 0, 1000, 60);
        titlePanel.setLayout(null);

        titleLabel = new JLabel("🎵 Which Song Is This? 🎶");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 35));
        titleLabel.setBounds(280, 5, 475, 50);
        titlePanel.add(titleLabel);
        this.add(titlePanel);


        Color teal = new Color(0, 255, 193); //Teal

        // Score Tracker Label & Panel
        scorePanel = new JPanel();
        scorePanel.setBounds(100, 75, 160, 40);
        scorePanel.setBackground(teal); //Deep purple
        scorePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        scoreLabel = new JLabel("Score: " + playerPoints + " - " + computerPoints);
        scoreLabel.setFont(new Font("Helvetica", Font.BOLD, 25));
        scoreLabel.setForeground(Color.BLACK);
        scorePanel.add(scoreLabel);
        this.add(scorePanel);



        // Rounds Tracker Label & Panel
        roundsPanel = new JPanel();
        roundsPanel.setBounds(745, 75, 160, 40);
        roundsPanel.setBackground(teal); //Hot Pink
        roundsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        
        roundsLabel = new JLabel("Round " + currentRound + "/" + totalRounds);
        roundsLabel.setFont(new Font("Helvetica", Font.BOLD, 25));
        roundsPanel.add(roundsLabel);
        this.add(roundsPanel);



        //Play Button
        playButton = new JButton("🔊 Play Song"); //🔊
        playButton.setFont(new Font("Helvetica", Font.BOLD, 35));
        playButton.setBounds(345, 865, 300, 85);
        playButton.setBackground(new Color(0, 250, 210));
        playButton.setForeground(Color.BLACK);
        playButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        playButton.setFocusPainted(false);
        playButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        playButton.setOpaque(true);
        pb1 = new PlayButtonListener();
        playButton.addActionListener(pb1);

        playButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) {
                playButton.setBackground(new Color(0, 200, 165));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                playButton.setBackground(new Color(0, 255, 212));
            }
        });
        
        this.add(playButton);



        //Restart
        restartButton = new JButton("🔄 Restart"); //🔄
        restartButton.setFont(new Font("Helvetica", Font.BOLD, 35));
        restartButton.setBounds(715, 865, 250, 85);
        restartButton.setBackground(new Color(255, 90, 0));
        restartButton.setForeground(Color.BLACK);
        restartButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        restartButton.setFocusPainted(false);
        restartButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        restartButton.setOpaque(true);
        rb1 = new RestartButtonListener();
        restartButton.addActionListener(rb1);

        restartButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) {
                restartButton.setBackground(new Color(200, 100, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                restartButton.setBackground(new Color(250, 100, 0));
            }
        });

        this.add(restartButton);



        //Late Message
        lateMessagePanel = new JPanel();
        lateMessagePanel.setBounds(40, 865, 200, 85);
        lateMessagePanel.setBackground(new Color(255, 0, 176, 25)); //By default make it light, and whenever the user gets it wrong, turn it vibrant
        lateMessagePanel.setLayout(null);
        lateMessagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        lateMessageLabel = new JLabel("Too Late!");
        lateMessageLabel.setFont(new Font("Helvetica", Font.BOLD, 35));
        lateMessageLabel.setBounds(20, 0, 275, 85);
        lateMessageLabel.setForeground(new Color(0, 0, 0, 25));
        
        lateMessagePanel.add(lateMessageLabel);
        this.add(lateMessagePanel);

        

        display4Songs(generate4Options());
        updateRoundsAndScore();



        //Timer for each round
        checkTime();

        timePanel = new JPanel();
        timePanel.setBounds(395, 75, 215, 40);
        timePanel.setBackground(new Color(0, 255, 193)); //Nice aqua
        timePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        timeLabel = new JLabel("⏰ Time Left: " + timeLeft); //Won't let me put a int variable alone, I need to put concatentation
        timeLabel.setFont(new Font("Helvetica", Font.BOLD, 25));
        timePanel.add(timeLabel);
        this.add(timePanel);

        t1 = new TimeListener();
        timer = new Timer(1000, t1); //1000 ms = 1 second for each second passing

        

    } //End of Constructor



    private static final Random RNG = new Random();


    //Method for Generating 4 Answers - 1 Real and 3 Fake
    public static List<Song> generate4Options()
    {
        List<Song> copyList = new ArrayList<>(Song.masterList); // Make a modifiable copy of the master list
        Song newCurrentSong = Song.generateRandomSong(); // Generate the real song
        copyList.remove(newCurrentSong); // Remove the correct song so it can’t appear as a fake answer

        Collections.shuffle(copyList, RNG); // Shuffle the rest of the copyList to get 3 fake answers
        List<Song> displayedSongs = new ArrayList<>(); // Create an arraylist to store all 4 answers, including real and fake
        displayedSongs.add(newCurrentSong); // Add the right song
        displayedSongs.addAll(copyList.subList(0, 3)); // The 3 fakes we're adding are just the 3 songs at the top of the SHUFFLED deck, so its still random
        Collections.shuffle(displayedSongs, RNG); // Shuffle again so the correct answer isn’t always first, since in the previous steps, we added the correct song first and all the fakes afterwards

        //Now the system needs to know WHICH INDEX is the correct song, so that I can program the right button to work
        Song.currentSongNumber = (displayedSongs.indexOf(newCurrentSong) + 1);

        return displayedSongs;
    }



    public void display4Songs(List<Song> fourSongs)
    {
        //Getting Song Names
        Song song1 = fourSongs.get(0); Song song2 = fourSongs.get(1); Song song3 = fourSongs.get(2); Song song4 = fourSongs.get(3);
        song1NameLabel = new JLabel(song1.getName()); song2NameLabel = new JLabel(song2.getName()); song3NameLabel = new JLabel(song3.getName()); song4NameLabel = new JLabel(song4.getName());
        song1NameLabel.setBounds(40, 380, 450, 30); song2NameLabel.setBounds(540, 380, 450, 30); song3NameLabel.setBounds(40, 750, 450, 30); song4NameLabel.setBounds(540, 750, 450, 30);
        Font songFont = new Font("Helvetica", Font.BOLD, 25);
        song1NameLabel.setFont(songFont); song2NameLabel.setFont(songFont); song3NameLabel.setFont(songFont); song4NameLabel.setFont(songFont);
        this.add(song1NameLabel); this.add(song2NameLabel); this.add(song3NameLabel); this.add(song4NameLabel);
        
        //Getting Song Pictures
        song1Picture = new ImageIcon(song1.getImagePath()); song2Picture = new ImageIcon(song2.getImagePath()); song3Picture = new ImageIcon(song3.getImagePath()); song4Picture = new ImageIcon(song4.getImagePath());
        Image song1PictureScaled = song1Picture.getImage().getScaledInstance(425, 240, Image.SCALE_SMOOTH); Image song2PictureScaled = song2Picture.getImage().getScaledInstance(425, 240, Image.SCALE_SMOOTH); Image song3PictureScaled = song3Picture.getImage().getScaledInstance(425, 240, Image.SCALE_SMOOTH); Image song4PictureScaled = song4Picture.getImage().getScaledInstance(425, 240, Image.SCALE_SMOOTH);
        song1Picture = new ImageIcon(song1PictureScaled); song2Picture = new ImageIcon(song2PictureScaled); song3Picture = new ImageIcon(song3PictureScaled); song4Picture = new ImageIcon(song4PictureScaled);
        JLabel song1Label = new JLabel(song1Picture); JLabel song2Label = new JLabel(song2Picture); JLabel song3Label = new JLabel(song3Picture); JLabel song4Label = new JLabel(song4Picture);
        song1Label.setBounds(40, 130, 425, 240); song2Label.setBounds(540, 130, 425, 240); song3Label.setBounds(40, 500, 425, 240); song4Label.setBounds(540, 500, 425, 240);
        this.add(song1Label); this.add(song2Label); this.add(song3Label); this.add(song4Label);

        //Making Song Buttons
        song1Button = new JButton("Choose Song 1"); song2Button = new JButton("Choose Song 2"); song3Button = new JButton("Choose Song 3"); song4Button = new JButton("Choose Song 4");
        Font buttonFont = new Font("Helvetica", Font.BOLD, 20);
        song1Button.setFont(buttonFont); song2Button.setFont(buttonFont); song3Button.setFont(buttonFont); song4Button.setFont(buttonFont);

        Color red1 = new Color(255, 90, 90); Color red2 = new Color(200, 90, 90);
        Color blue1 = new Color(70, 175, 255); Color blue2 = new Color(70, 125, 200);
        Color green1 = new Color(55, 235, 90); Color green2 = new Color(55, 175, 90);
        Color yellow1 = new Color(245, 255, 70); Color yellow2 = new Color(200, 200, 70);

        song1Button.setBackground(red1); song2Button.setBackground(blue1); song3Button.setBackground(green1); song4Button.setBackground(yellow1);
        song1Button.setForeground(Color.BLACK); song2Button.setForeground(Color.BLACK); song3Button.setForeground(Color.BLACK); song4Button.setForeground(Color.BLACK);
        song1Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); song2Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); song3Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); song4Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        song1Button.setFocusPainted(false); song2Button.setFocusPainted(false); song3Button.setFocusPainted(false); song4Button.setFocusPainted(false);
        song1Button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3)); song2Button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3)); song3Button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3)); song4Button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        song1Button.setOpaque(true); song2Button.setOpaque(true); song3Button.setOpaque(true); song4Button.setOpaque(true);

        song1Button.setBounds(40, 415, 425, 60); song2Button.setBounds(540, 415, 425, 60); song3Button.setBounds(40, 785, 425, 60); song4Button.setBounds(540, 785, 425, 60);
        b1 = new ButtonListener(); song1Button.addActionListener(b1); song2Button.addActionListener(b1); song3Button.addActionListener(b1); song4Button.addActionListener(b1);
        this.add(song1Button); this.add(song2Button); this.add(song3Button); this.add(song4Button);

        song1Button.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) {
                song1Button.setBackground(red2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                song1Button.setBackground(red1);
            }
        });

        song2Button.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) {
                song2Button.setBackground(blue2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                song2Button.setBackground(blue1);
            }
        });

        song3Button.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) {
                song3Button.setBackground(green2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                song3Button.setBackground(green1);
            }
        });

        song4Button.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) {
                song4Button.setBackground(yellow2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                song4Button.setBackground(yellow1);
            }
        });
    }



    public class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == song1Button)
            {
                if (Song.getCurrentSongNumber() == 1)
                {
                    correctAnswerFlag = true;
                    playerPoints++;
                }

                else
                {
                    correctAnswerFlag = false;
                    computerPoints++;
                    song1NameLabel.setForeground(Color.RED); //Display Incorrect Answer
                }
            }

            else if (e.getSource() == song2Button)
            {
                if (Song.getCurrentSongNumber() == 2)
                {
                    correctAnswerFlag = true;
                    playerPoints++;
                }

                else
                {
                    correctAnswerFlag = false;
                    computerPoints++;
                    song2NameLabel.setForeground(Color.RED); //Display Incorrect Answer
                }
            }

            else if (e.getSource() == song3Button)
            {
                if (Song.getCurrentSongNumber() == 3)
                {
                    correctAnswerFlag = true;
                    playerPoints++;
                }

                else
                {
                    correctAnswerFlag = false;
                    computerPoints++;
                    song3NameLabel.setForeground(Color.RED); //Display Incorrect Answer
                }
            }

            else if (e.getSource() == song4Button)
            {
                if (Song.getCurrentSongNumber() == 4)
                {
                    correctAnswerFlag = true;
                    playerPoints++;
                }

                else
                {
                    correctAnswerFlag = false;
                    computerPoints++;
                    song4NameLabel.setForeground(Color.RED); //Display Incorrect Answer
                }
            }

            //Immediately lock the buttons the moment they click one of them, regardless if the user was right or wrong
            song1Button.setEnabled(false); song2Button.setEnabled(false); song3Button.setEnabled(false); song4Button.setEnabled(false); playButton.setEnabled(false); restartButton.setEnabled(false);
            //player.stop(); //Stop the audio
            displayCorrectAnswer(); //Even when you get the answer wrong, I still need to turn the RIGHT answer green no matter what was selected
            moveOntoNextRound();
        }
    }


    public void moveOntoNextRound()
    {
        //No matter what, we must increment the rounds counter
        timer.stop();
        currentRound++;
        numberOfClicks = 0; //Reset number of clicks for the play button on normal difficulty

        //Wait 1 second before creating a new page
        new javax.swing.Timer(1000, evt ->
        {
            ((Timer) evt.getSource()).stop();

            //If user has finished the game and played 10 rounds, send them to the GameResults page
            if (currentRound > 10)
            {
                new GameResultsPage();

                DifficultyPage.achievementsTimer.stop();

                //Rewarding Checkmarks & Stars
                if ((easyMode == true) && (playerPoints > computerPoints) && (DifficultyPage.easyLock == false))
                {
                    DifficultyPage.completedEasyMode = true;

                    if (playerPoints == 10)
                    {
                        DifficultyPage.perfectedEasyMode = true;
                        DifficultyPage.completedEasyMode = false;

                        if (DifficultyPage.perfectedEasyMode == true) //If Star unlocked, do not allow Checkmark to be displayed again
                        {
                            DifficultyPage.easyLock = true;
                        }
                    }
                }

                if ((normalMode == true) && (playerPoints > computerPoints) && (DifficultyPage.normalLock == false))
                {
                    DifficultyPage.completedNormalMode = true;

                    if (playerPoints == 10)
                    {
                        DifficultyPage.completedNormalMode = false;
                        DifficultyPage.perfectedNormalMode = true;

                        if (DifficultyPage.perfectedNormalMode == true)
                        {
                            DifficultyPage.normalLock = true;
                        }
                    }
                }

                if ((hardMode == true) && (playerPoints > computerPoints) && (DifficultyPage.hardLock == false))
                {
                    DifficultyPage.completedHardMode = true;

                    if (playerPoints == 10)
                    {
                        DifficultyPage.perfectedHardMode = true; 
                        DifficultyPage.completedHardMode = false;

                        if (DifficultyPage.perfectedHardMode == true)
                        {
                            DifficultyPage.hardLock = true;
                        }
                    }
                }  

                if ((extremeMode == true) && (playerPoints > computerPoints) && (DifficultyPage.extremeLock == false))
                {
                    DifficultyPage.completedExtremeMode = true;

                    if (playerPoints == 10)
                    {
                        DifficultyPage.perfectedExtremeMode = true; 
                        DifficultyPage.completedExtremeMode = false;

                        if (DifficultyPage.perfectedExtremeMode == true)
                        {
                            DifficultyPage.extremeLock = true;
                        }
                    }
                }



                //Checking if user unlocked achievements
                if ((easyMode == true) && (DifficultyPage.achievementTimeLeft > 0))
                {
                    AchievementsPage.bronzeSpeedster = true;
                    System.out.println("Achieved Bronze Speedster");
                }

                if ((normalMode == true) && (DifficultyPage.achievementTimeLeft > 0))
                {
                    AchievementsPage.silverSpeedster = true;
                    System.out.println("Achieved Silver Speedster");
                }

                if ((hardMode == true) && (playerPoints == 10) && (DifficultyPage.achievementTimeLeft > 0)) //Perfected Hard Mode sub 30 seconds
                {
                    AchievementsPage.goldSpeedster = true;
                    System.out.println("Achieved Gold Speedster");
                }

                if ((extremeMode == true) && (DifficultyPage.achievementTimeLeft > 0))
                {
                    AchievementsPage.diamondSpeedster = true;
                    System.out.println("Achieved Diamond Speedster");
                }

                if (DifficultyPage.perfectedExtremeMode == true)
                {
                    AchievementsPage.extremity = true;
                    System.out.println("Achieved Extremity");
                }

                if ((DifficultyPage.perfectedEasyMode == true) && (DifficultyPage.perfectedNormalMode == true) &&  (DifficultyPage.perfectedHardMode == true) && (DifficultyPage.perfectedExtremeMode == true))
                {
                    AchievementsPage.superstar = true;
                    System.out.println("Achieved Superstar");
                }

                if (roundsWon == 10)
                {
                    AchievementsPage.allDay = true;
                    System.out.println("Achieved All Day");
                }

                if ((AchievementsPage.bronzeSpeedster == true) && (AchievementsPage.silverSpeedster == true) && (AchievementsPage.goldSpeedster == true) && (AchievementsPage.diamondSpeedster == true) && (AchievementsPage.extremity == true) && (AchievementsPage.superstar == true) && (AchievementsPage.allDay == true))
                {
                    AchievementsPage.completionist = true;
                    System.out.println("Achieved Completionist");
                }



                //Resetting difficulties for the next game
                easyMode = false;
                normalMode = false;
                hardMode = false;
                extremeMode = false;

            }//End of if (currentRound > 10)

            else
            {
                new SongGuessingPage();
            }

            dispose();
        }).start();
    }



    public class TimeListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            timeLeft--;
            timeLabel.setText("⏰ Time Left: " + timeLeft);

            //If user runs out of time, skip the current round, create a new round, increment roundsCounter
            if (timeLeft == 0)
            {
                song1Button.setEnabled(false); song2Button.setEnabled(false); song3Button.setEnabled(false); song4Button.setEnabled(false); playButton.setEnabled(false); restartButton.setEnabled(false);
                correctAnswerFlag = false;
                computerPoints++; //Must do this outside of the moveOntoNextRound method since the user didn't answer so thats automatically 1 point for the computer
                displayCorrectAnswer();
                moveOntoNextRound();
                lateMessagePanel.setBackground(new Color(255, 0, 176, 255)); //Making it vibrant so the user can see
                lateMessageLabel.setForeground(new Color(0, 0, 0, 255));
            }
        }
    }



    public class RestartButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == restartButton)
            {
                //Reset All Values
                currentRound = 1;
                playerPoints = 0;
                computerPoints = 0;

                //Reset Timer
                easyMode = false;
                normalMode = false;
                hardMode = false;
                extremeMode = false;

                timer.stop();
                DifficultyPage.achievementsTimer.stop();
                DifficultyPage.achievementTimeLeft = 30;

                //Create New Page
                new DifficultyPage();
                dispose();
            }
        }
    }



    public class PlayButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == playButton)
            {
                //If this is the first time the user has clicked the button for the ROUND (not the GAME), then start timer
                if (firstClick == true)
                {
                    timer.start();
                    firstClick = false;
                }

                try
                {
                    if (extremeMode == true || easyMode == true)
                    {
                        playRandom1SecondClip(Song.getCurrentSong().getAudioPath());
                    }

                    if (hardMode == true)
                    {
                        playRandom1SecondClip(Song.getCurrentSong().getAudioPath());
                        playButton.setEnabled(false); //Only let user listen 1 time if set to Hard
                    }

                    if (normalMode == true)
                    {
                        playRandom1SecondClip(Song.getCurrentSong().getAudioPath());
                        numberOfClicks++;
                        if (numberOfClicks >= 3)
                        {
                            playButton.setEnabled(false); //Only let user listen 1 time if set to Hard
                        }
                    }
                }
                
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }



    public static void playRandom1SecondClip(String filepath) throws Exception
    {
        if (cachedSnippet == null)
        {
            File mp3 = new File(filepath);
            long totalSize = mp3.length();
            long maxStart = totalSize - BYTES_PER_SECOND;
            fixedRandomStart = (long) (Math.random() * maxStart);

            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(mp3)))
            {
                bis.skip(fixedRandomStart);

                if (easyMode == true)
                {
                    clipDuration = BYTES_PER_SECOND * 3;
                    cachedSnippet = bis.readNBytes(clipDuration);
                }

                if (normalMode == true)
                {
                    clipDuration = BYTES_PER_SECOND * 2;
                    cachedSnippet = bis.readNBytes(clipDuration);
                }

                if (hardMode == true)
                {
                    clipDuration = BYTES_PER_SECOND;
                    cachedSnippet = bis.readNBytes(clipDuration);
                }

                if (extremeMode == true)
                {
                    cachedSnippet = bis.readNBytes(BYTES_PER_SECOND);
                }
            }
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(cachedSnippet);
        BufferedInputStream snippetStream = new BufferedInputStream(bais);
        AdvancedPlayer player = new AdvancedPlayer(snippetStream);

        new Thread(() -> {
            try
            {
                if (extremeMode == true)
                {
                    player.play(0, 5); //Only let them hear 0.1 seconds of the song
                }

                else
                {
                    player.play(0, 38 * clipDuration); // 38 frames is ~1 second worth of frames
                }
            } 

            catch (Exception e) { e.printStackTrace(); }
        }).start();
    }



    public void updateRoundsAndScore()
    {
        roundsLabel.setText("Round " + currentRound + "/" + totalRounds);
        scoreLabel.setText("Score: " + playerPoints + " - " + computerPoints);
    }



    public void displayCorrectAnswer()
    {
        Color correctColor = new Color(0, 255, 0);

        if (Song.getCurrentSongNumber() == 1)
        {
            song1NameLabel.setForeground(correctColor);
        }

        if (Song.getCurrentSongNumber() == 2)
        {
            song2NameLabel.setForeground(correctColor);
        }

        if (Song.getCurrentSongNumber() == 3)
        {
            song3NameLabel.setForeground(correctColor);
        }

        if (Song.getCurrentSongNumber() == 4)
        {
            song4NameLabel.setForeground(correctColor);
        }
    }



    // Gradient Background Panel
    public static class BackgroundPanel extends JPanel
    {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(0, 0, new Color(176, 134, 255), 0, getHeight(), new Color(176, 134, 255));
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }



    public void checkTime()
    {
        if (extremeMode == true)
        {
            timeLeft = 5;
        }

        if (hardMode == true)
        {
            timeLeft = 3;
        }

        if (normalMode == true)
        {
            timeLeft = 10;
        }

        if (easyMode == true)
        {
            timeLeft = 9999999;
        }
    }



    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new SongGuessingPage());
    }
}