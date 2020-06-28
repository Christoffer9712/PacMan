package realpac;

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
        if (newLevel != null) {
            newLevel.stop();
        }
        if (level1 != null) {
            level1.stop();
        }
        if (level2 != null) {
            level2.stop();
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
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\chjal\\Documents\\NetBeansProjects\\RealPac\\src\\realpac\\Level1.wav"));
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
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\chjal\\Documents\\NetBeansProjects\\RealPac\\src\\realpac\\Level2.wav"));
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
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\chjal\\Documents\\NetBeansProjects\\RealPac\\src\\realpac\\Level3.wav"));
            // NOTICE: I am only initializing and NOT declaring (no capital Clip)
            level1 = AudioSystem.getClip();
            level1.open(audioInputStream);
            level1.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void level4() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\chjal\\Documents\\NetBeansProjects\\RealPac\\src\\realpac\\Level4.wav"));
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
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\chjal\\Documents\\NetBeansProjects\\RealPac\\src\\realpac\\Menu.wav"));
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

        if (System.currentTimeMillis() % 2 == 0) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\chjal\\Documents\\NetBeansProjects\\RealPac\\src\\realpac\\PåRiktigt.wav"));
                // NOTICE: I am only initializing and NOT declaring (no capital Clip)
                påRiktigt = AudioSystem.getClip();
                påRiktigt.open(audioInputStream);
                påRiktigt.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\chjal\\Documents\\NetBeansProjects\\RealPac\\src\\realpac\\Klant.wav"));
                // NOTICE: I am only initializing and NOT declaring (no capital Clip)
                klant = AudioSystem.getClip();
                klant.open(audioInputStream);
                klant.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void gameOver() {
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\chjal\\Documents\\NetBeansProjects\\RealPac\\src\\realpac\\gameOver.wav"));
            // NOTICE: I am only initializing and NOT declaring (no capital Clip)
            gameOver = AudioSystem.getClip();
            gameOver.open(audioInputStream);
            gameOver.loop(Clip.LOOP_CONTINUOUSLY);

            AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(new File("C:\\Users\\chjal\\Documents\\NetBeansProjects\\RealPac\\src\\realpac\\Goodbye.wav"));
            // NOTICE: I am only initializing and NOT declaring (no capital Clip)
            goodbye = AudioSystem.getClip();
            goodbye.open(audioInputStream2);
            goodbye.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
