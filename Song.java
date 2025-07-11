import java.util.List;

public class Song
{
    //Instance Variables
    private String name;
    private String audioPath;
    private String imagePath;
    

    //Constructor
    public Song(String someName, String someAudioPath, String someImagePath)
    {
        this.name = someName;
        this.audioPath = someAudioPath;
        this.imagePath = someImagePath;
    }


    //Getters
    public String getName() {return name;}
    public String getAudioPath() {return audioPath;}
    public String getImagePath() {return imagePath;}


    //All 44 Songs
    public static Song jealous = new Song("Jealous - Nick Jonas", "Songs/Jealous.mp3", "Images/Jealous.jpg");
    public static Song deathOfABachelor = new Song("Death Of A Bachelor - PATD", "Songs/Death Of A Bachelor.mp3", "Images/DeathOfABachelor.jpg");
    public static Song everybody = new Song("Everybody - SHINee", "Songs/Everybody.mp3", "Images/Everybody.jpg");
    public static Song missRight = new Song("Miss Right - Teen Top", "Songs/Miss Right.mp3", "Images/MissRight.jpg");
    public static Song driveBy = new Song("Drive By - Train", "Songs/Drive By.mp3", "Images/DriveBy.jpg");
    public static Song fantasticBaby = new Song("Fantastic Baby - BIGBANG", "Songs/Fantastic Baby.mp3", "Images/FantasticBaby.jpg");
    public static Song sucker = new Song("Sucker - Jonas Brothers", "Songs/Sucker.mp3", "Images/Sucker.jpg");
    public static Song lastFridayNight = new Song("Last Friday Night - Katy Perry", "Songs/LastFridayNight.mp3", "Images/LastFridayNight.jpg");
    public static Song californiaGurls = new Song("California Gurls - Katy Perry", "Songs/CaliforniaGurls.mp3", "Images/CaliforniaGurls.jpg");
    public static Song callMeMaybe = new Song("Call Me Maybe - Carly Rae Jepsen", "Songs/CallMeMaybe.mp3", "Images/CallMeMaybe.jpg");
    public static Song twoHeads = new Song("2 Heads - Coleman Hell", "Songs/2Heads.mp3", "Images/2Heads.jpg");
    public static Song bangarang = new Song("Bangarang - Skrillex", "Songs/Bangarang.mp3", "Images/Bangarang.jpg");
    public static Song countingStars = new Song("Counting Stars - One Republic", "Songs/CountingStars.mp3", "Images/CountingStars.jpg");
    public static Song september = new Song("September - Earth, Wind and Fire", "Songs/September.mp3", "Images/September.jpg");
    public static Song crazyForYou = new Song("Crazy For You - Hedley", "Songs/CrazyForYou.mp3", "Images/CrazyForYou.jpg");
    public static Song timber = new Song("Timber - Pitbull & Ke$ha", "Songs/Timber.mp3", "Images/Timber.jpg");
    public static Song soundOfYourHeart = new Song("Sound Of Your Heart - Shawn Hook", "Songs/SoundOfYourHeart.mp3", "Images/SoundOfYourHeart.jpg");
    public static Song handClap = new Song("Hand Clap - Fitz & The Tantrums", "Songs/HandClap.mp3", "Images/HandClap.jpg");
    public static Song carelessWhisper = new Song("Careless Whisper - George Michael", "Songs/CarelessWhisper.mp3", "Images/CarelessWhisper.jpg");
    public static Song thatsWhatILike = new Song("That's What I Like - Bruno Mars", "Songs/ThatsWhatILike.mp3", "Images/ThatsWhatILike.jpg");
    public static Song treasure = new Song("Treasure - Bruno Mars", "Songs/Treasure.mp3", "Images/Treasure.jpg");
    public static Song talkingToTheMoon = new Song("Talking To The Moon - Bruno Mars", "Songs/TalkingToTheMoon.mp3", "Images/TalkingToTheMoon.jpg");
    public static Song backInBlack = new Song("Back In Black - AC/DC", "Songs/BackInBlack.mp3", "Images/BackInBlack.jpg");
    public static Song breakFree = new Song("Break Free - Ariana Grande", "Songs/BreakFree.mp3", "Images/BreakFree.jpg");
    public static Song allIWant = new Song("All I Want - Mariah Carey", "Songs/AllIWant.mp3", "Images/AllIWant.jpg");
    public static Song holdingMeBack = new Song("Nothing Holding Me - Shawn Mendes", "Songs/HoldingMeBack.mp3", "Images/HoldingMeBack.jpg");
    public static Song seeYouAgain = new Song("See You Again - Charlie Puth", "Songs/SeeYouAgain.mp3", "Images/SeeYouAgain.jpg");
    public static Song movesLikeJagger = new Song("Moves Like Jagger - Maroon 5", "Songs/MovesLikeJagger.mp3", "Images/MovesLikeJagger.jpg");
    public static Song troublemaker = new Song("Troublemaker - Olly Murs", "Songs/Troublemaker.mp3", "Images/Troublemaker.jpg");
    public static Song levitating = new Song("Levitating - Dua Lipa", "Songs/Levitating.mp3", "Images/Levitating.jpg");
    public static Song leaveTheDoor = new Song("Leave The Door Open - Silk Sonic", "Songs/LeaveTheDoorOpen.mp3", "Images/LeaveTheDoorOpen.jpg");
    public static Song sugar = new Song("Sugar - Maroon 5", "Songs/Sugar.mp3", "Images/Sugar.jpg");
    public static Song beatIt = new Song("Beat It - Michael Jackson", "Songs/BeatIt.mp3", "Images/BeatIt.jpg");
    public static Song imNotAlright = new Song("I'm Not Alright - Bryce Vine", "Songs/ImNotAlright.mp3", "Images/ImNotAlright.jpg");
    public static Song justTheWay = new Song("The Way You Are - Bruno Mars", "Songs/JustTheWay.mp3", "Images/JustTheWay.jpg");
    public static Song onTheFloor = new Song("On The Floor - IceJJFish", "Songs/OnTheFloor.mp3", "Images/OnTheFloor.jpg");
    public static Song highHopes = new Song("High Hopes - PATD", "Songs/HighHopes.mp3", "Images/HighHopes.jpg");
    public static Song everybody2 = new Song("Everybody - Backstreet Boys", "Songs/Everybody2.mp3", "Images/Everybody2.jpg");
    public static Song classic = new Song("Classic - MKTO", "Songs/Classic.mp3", "Images/Classic.jpg");
    public static Song fireflies = new Song("Fireflies - Owl City", "Songs/Fireflies.mp3", "Images/Fireflies.jpg");
    public static Song sunroof = new Song("Sunroof - Nicky Youre", "Songs/Sunroof.mp3", "Images/Sunroof.jpg");
    public static Song allIWannaBe = new Song("She's All I Wanna Be - Tate McRae", "Songs/AllIWannaBe.mp3", "Images/AllIWannaBe.jpg");
    public static Song honeyImGood = new Song("Honey, I'm Good - Andy Grammer", "Songs/HoneyImGood.mp3", "Images/HoneyImGood.jpg");

    //Songs To Disable If Someone Else Is Playing
    public static Song nobodyLikeU = new Song("Nobody Like U - DCapella", "Songs/Nobody Like U.mp3", "Images/NobodyLikeU.jpg");
    public static Song levelTwo = new Song("Level Two - DJ Striden", "Songs/Level Two.mp3", "Images/LevelTwo.jpg");
    public static Song rhythm = new Song("Rhythm - Manic Drive", "Songs/Rhythm.mp3", "Images/Rhythm.jpg");
    public static Song burnOut = new Song("Burn Out - Martin Garrix", "Songs/Burn Out.mp3", "Images/BurnOut.jpg");
    public static Song readySetGo = new Song("Ready Set Go - Royal Tailor", "Songs/Ready Set Go.mp3", "Images/ReadySetGo.jpg");
    public static Song energyDrink = new Song("Energy Drink - Virtual Riot", "Songs/EnergyDrink.mp3", "Images/EnergyDrink.jpg");

    public static Song currentSong;
    public static int currentSongNumber;

    public static Song getCurrentSong() { return Song.currentSong; }
    public static int getCurrentSongNumber() { return Song.currentSongNumber; }


    
    //Adding All Songs To An ArrayList
    public static final List <Song> masterList = List.of(jealous, deathOfABachelor, everybody, missRight, driveBy, fantasticBaby, sucker, lastFridayNight, californiaGurls, callMeMaybe, twoHeads, bangarang,
    countingStars, september, crazyForYou, timber, soundOfYourHeart, handClap, carelessWhisper, thatsWhatILike, treasure, talkingToTheMoon, backInBlack, nobodyLikeU, levelTwo, rhythm, burnOut, readySetGo,
    energyDrink, breakFree, allIWant, seeYouAgain, holdingMeBack, troublemaker, levitating, leaveTheDoor, sugar, beatIt, imNotAlright, justTheWay, onTheFloor, highHopes, everybody2, classic, fireflies, sunroof, allIWannaBe, honeyImGood);



    public static Song generateRandomSong()
    {
        Song currentSong = null;
        int randomNumber = (int) ((Math.random() * (51 - 1)) + 1); //return (int) ((Math.random() * (upperBound - lowerBound)) + lowerBound);
        switch (randomNumber)
        {
            case 1: currentSong = Song.jealous; break;
            case 2: currentSong = Song.energyDrink; break;          
            case 3: currentSong = Song.deathOfABachelor; break;
            case 4: currentSong = Song.handClap; break;
            case 5: currentSong = Song.readySetGo; break;
            case 6: currentSong = Song.everybody; break;            
            case 7: currentSong = Song.missRight; break;      
            case 8: currentSong = Song.driveBy; break;           
            case 9: currentSong = Song.fantasticBaby; break;   
            case 10: currentSong = Song.nobodyLikeU; break;
            case 11: currentSong = Song.levelTwo; break;
            case 12: currentSong = Song.sucker; break;
            case 13: currentSong = Song.rhythm; break;
            case 14: currentSong = Song.burnOut; break;
            case 15: currentSong = Song.carelessWhisper; break;
            case 16: currentSong = Song.thatsWhatILike; break;
            case 17: currentSong = Song.treasure; break;
            case 18: currentSong = Song.talkingToTheMoon; break;
            case 19: currentSong = Song.backInBlack; break;
            case 20: currentSong = Song.breakFree; break;
            case 21: currentSong = Song.lastFridayNight; break;
            case 22: currentSong = Song.californiaGurls; break;          
            case 23: currentSong = Song.callMeMaybe; break;       
            case 24: currentSong = Song.twoHeads; break;          
            case 25: currentSong = Song.bangarang; break;            
            case 26: currentSong = Song.countingStars; break;            
            case 27: currentSong = Song.september; break;      
            case 28: currentSong = Song.crazyForYou; break;           
            case 29: currentSong = Song.timber; break;   
            case 30: currentSong = Song.soundOfYourHeart; break;
            case 31: currentSong = Song.allIWant; break;
            case 32: currentSong = Song.holdingMeBack; break;
            case 33: currentSong = Song.seeYouAgain; break;
            case 34: currentSong = Song.movesLikeJagger; break;
            case 35: currentSong = Song.troublemaker; break;
            case 36: currentSong = Song.troublemaker; break; //Need to double chances for it to even play
            case 37: currentSong = Song.levitating; break;
            case 38: currentSong = Song.leaveTheDoor; break;
            case 39: currentSong = Song.sugar; break;
            case 40: currentSong = Song.beatIt; break;
            case 41: currentSong = Song.imNotAlright; break;
            case 42: currentSong = Song.justTheWay; break;
            case 43: currentSong = Song.onTheFloor; break;
            case 44: currentSong = Song.highHopes; break;
            case 45: currentSong = Song.everybody2; break;
            case 46: currentSong = Song.classic; break;
            case 47: currentSong = Song.fireflies; break;
            case 48: currentSong = Song.sunroof; break;
            case 49: currentSong = Song.allIWannaBe; break;
            case 50: currentSong = Song.honeyImGood; break;
            case 51: currentSong = Song.honeyImGood; break;
        }

        Song.currentSong = currentSong;
        return currentSong;
    }

    //Procedure for adding new songs:
    // 1 - Download the MP3 and Song Thumbnail into this folder
    // 2 - Add the Song Object
    // 3 - Include the new song in generateSong method
    // 4 - Increase the upperbound of generating random numbers
}