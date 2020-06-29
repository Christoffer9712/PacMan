package PacManGIT;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Sound {

//    InputStream lostLife;
//    InputStream gameOver;
//    InputStream backGround;
//    InputStream newLevel;
//
//    AudioStream lostLifeA;
//    AudioStream gameOverA;
//    AudioStream backGroundA;
//    AudioStream newLevelA;
    Clip level1;
    Clip level2;
    Clip level3;
    Clip level4;
    Clip Menu;
    Clip newLevel;
    Clip lostLife;
    Clip gameOver;
    Clip goodbye;
    Clip menu;
    Clip påRiktigt;
    Clip klant;

    public Sound() {
//        try {
//            lostLife = new FileInputStream(new File("C:\\Users\\chjal\\Documents\\NetBeansProjects\\RealPac\\src\\realpac\\pacman_death.wav"));
//            lostLifeA = new AudioStream(lostLife);
//
//            gameOver = new FileInputStream(new File("C:\\Users\\chjal\\Documents\\NetBeansProjects\\RealPac\\src\\realpac\\gameOver.wav"));
//            gameOverA = new AudioStream(gameOver);
//
//            backGround = new FileInputStream(new File("C:\\Users\\chjal\\Documents\\NetBeansProjects\\RealPac\\src\\realpac\\Cry.wav"));
//            backGroundA = new AudioStream(backGround);
//
////            newLevel = new FileInputStream(new File("C:\\Users\\chjal\\Documents\\NetBeansProjects\\RealPac\\src\\realpac\\pacman_death.wav"));
////            newLevelA = new AudioStream(newLevel);
//        } catch (Exception e) {
//
//        }

    }

    public void stopAll() {
//        if (newLevel != null) {
//            newLevel.stop();
//        }
        if (level1 != null) {
            level1.stop();
        }
        if (level2 != null) {
            level2.stop();
        }
        if (level3 != null) {
            level3.stop();
        }
        if (level4 != null) {
            level4.stop();
        }

        if (lostLife != null) {
            lostLife.stop();
        }
        if (gameOver != null) {
            gameOver.stop();
        }
        if (goodbye != null) {
            goodbye.stop();
        }
        if (menu != null) {
            menu.stop();
        }

    }

    public void level1() {
        try {
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("\\src\\PacManGIT\\Sounds\\Level1.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            // NOTICE: I am only initializing and NOT declaring (no capital Clip)
            level1 = AudioSystem.getClip();
            level1.open(audioInputStream);
            level1.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void level2() {
        try {
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("\\src\\PacManGIT\\Sounds\\Level2.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            // NOTICE: I am only initializing and NOT declaring (no capital Clip)
            level2 = AudioSystem.getClip();
            level2.open(audioInputStream);
            level2.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void level3() {
        try {
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("\\src\\PacManGIT\\Sounds\\Level3.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            // NOTICE: I am only initializing and NOT declaring (no capital Clip)
            level3 = AudioSystem.getClip();
            level3.open(audioInputStream);
            level3.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void level4() {
        try {
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("\\src\\PacManGIT\\Sounds\\Level4.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            // NOTICE: I am only initializing and NOT declaring (no capital Clip)
            level1 = AudioSystem.getClip();
            level1.open(audioInputStream);
            level1.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menu() {
        try {
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("\\src\\PacManGIT\\Sounds\\Menu.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            // NOTICE: I am only initializing and NOT declaring (no capital Clip)
            menu = AudioSystem.getClip();
            menu.open(audioInputStream);
            menu.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void newLevel() {

    }

    public void lostLife() {
        try{
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("\\src\\PacManGIT\\Sounds\\pacManDeath.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            // NOTICE: I am only initializing and NOT declaring (no capital Clip)
            påRiktigt = AudioSystem.getClip();
            påRiktigt.open(audioInputStream);
            påRiktigt.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        if (System.currentTimeMillis() % 2 == 0) {
//            try {
//                String filePath = new File("").getAbsolutePath();
//                filePath = filePath.concat("\\src\\PacManGIT\\Sounds\\PåRiktigt.wav");
//                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
//                // NOTICE: I am only initializing and NOT declaring (no capital Clip)
//                påRiktigt = AudioSystem.getClip();
//                påRiktigt.open(audioInputStream);
//                påRiktigt.start();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            try {
//                String filePath = new File("").getAbsolutePath();
//                filePath = filePath.concat("\\src\\PacManGIT\\Sounds\\Klant.wav");
//                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
//                // NOTICE: I am only initializing and NOT declaring (no capital Clip)
//                klant = AudioSystem.getClip();
//                klant.open(audioInputStream);
//                klant.start();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void gameOver() {
        try {
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("\\src\\PacManGIT\\Sounds\\gameOver.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            // NOTICE: I am only initializing and NOT declaring (no capital Clip)
            gameOver = AudioSystem.getClip();
            gameOver.open(audioInputStream);
            //gameOver.loop(Clip.LOOP_CONTINUOUSLY);
            gameOver.start();
            
            String filePath2 = new File("").getAbsolutePath();
            filePath2 = filePath2.concat("\\src\\PacManGIT\\Sounds\\Goodbye.wav");
            AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(new File(filePath2));
            // NOTICE: I am only initializing and NOT declaring (no capital Clip)
            goodbye = AudioSystem.getClip();
            goodbye.open(audioInputStream2);
            goodbye.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
