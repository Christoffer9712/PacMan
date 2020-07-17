package PacManGIT;

import java.awt.BasicStroke;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.event.*;
import java.util.Random;
import java.util.Date;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class PanelLogic extends JPanel implements Runnable {

    public static int xFrameSize, yFrameSize;
    public int nbrLivesStart = 10;
    public int life = nbrLivesStart, tick = 0;
    public static boolean run = true, left, right, up, down;
    public Pac pac;
    private BufferedImage mazeImage1, pacRight, pacLeft, pacDown, pacUp, monsterImage1, gameOver, mazeImage2, currentMaze, mazeImage3, mazeImage4, monsterImage2, heart;
    private boolean init = false;
    public MazeMatrix mazeMatrix;
    public ArrayList<ArrayList<Integer>> mazeArray = new ArrayList<>();
    public ArrayList<DumbGhost> dumbGhostArray = new ArrayList<>();
    public Monster smartGhost;
    public ArrayList<Coin> coinArray = new ArrayList<>();
    private int nbrCoins = 20, level = 1, nbrDumbGhosts = 2;
    private int resolution = 10;
    private int xMonsterStart = 400, yMonsterStart = 290, xPacStart = 750, yPacStart = 50, xDumbStart = 50, yDumbStart = 50;
    private int monsterWait = 8;
    private MouseInput mi;
    private HighScoreClass h;
    private Sound s;
    private NewLife newLife;

    public static enum STATE {
        Menu,
        Game,
        GameOver,
        Restart,
        HighScore
    };
    public static STATE state = STATE.Menu;

    public Menue m;

    public PanelLogic(int xFrameSize, int yFrameSize) throws IOException {
        this.xFrameSize = xFrameSize;
        this.yFrameSize = yFrameSize;
        this.setPreferredSize(new Dimension(xFrameSize, yFrameSize));
        this.setFocusable(true);
        this.requestFocusInWindow();
        mi = new MouseInput();
        m = new Menue();
        this.addMouseListener(mi);
        this.addKeyListener(new Key());
    }

    public void start() {
        s = new Sound();
        s.stopAll();
        s.menu();
        mi.startListen();
//        initImages();
        state = STATE.Menu;

        pac = new Pac(xFrameSize, yFrameSize, pacRight);
        pac.setX(xPacStart);
        pac.setY(yPacStart);
        currentMaze = mazeImage1;
        mazeMatrix = new MazeMatrix(resolution, currentMaze);
        mazeMatrix.initMatrix();
        //mazeMatrix.write();
        mazeArray = mazeMatrix.getArray();
        initCoins();
        initMonsters();
        newLife = new NewLife(mazeArray, resolution, heart);
        h = new HighScoreClass();
        init = true;
        Thread t = new Thread(this, "pacLoop");
        Thread u = new Thread(this, "monsterLoop");
        u.start();
        t.start();

        run = true;
    }

    public void initCoins() {
        for (int i = 0; i < nbrCoins; i++) {
            coinArray.add(new Coin(mazeArray, resolution));
        }
    }

    public void initMonsters() {
        //for (int i = 0; i < 1; i++) {
        smartGhost = new Monster(mazeArray, xFrameSize, yFrameSize, 1, resolution, monsterImage1);;
        for (int i = 0; i < nbrDumbGhosts; i++) {
            dumbGhostArray.add(new DumbGhost(mazeArray, 1, resolution, monsterImage2));
            dumbGhostArray.get(i).setX(xDumbStart);
            dumbGhostArray.get(i).setY(yDumbStart);
        }
//        monsterArray.add(new Monster(mazeArray, xFrameSize, yFrameSize, 1, resolution, monsterImage2));
//        monsterArray.get(1).setX(xMonsterStart + 10);
//        monsterArray.get(1).setY(yMonsterStart + 10);
        //}
    }

    public void takeCoinLife() {
        for (int i = 0; i < coinArray.size(); i++) {
            if (Math.abs(pac.getXCoord() - coinArray.get(i).getX()) < pac.getRadius() + coinArray.get(i).getRadius()) {
                if (Math.abs(pac.getYCoord() - coinArray.get(i).getY()) < pac.getRadius() + coinArray.get(i).getRadius()) {
                    coinArray.remove(i);
                    pac.gotCoin();
                    if (coinArray.size() == 0) {
                        newLevel();
                    } else if (coinArray.size() == (int) (nbrCoins / 2)) {
                        newLife();
                    }
                }
            }
        }
        if (Math.abs(pac.getXCoord() - newLife.getX()) < pac.getRadius() + newLife.getRadius() - 10 && Math.abs(pac.getYCoord() - newLife.getY()) < pac.getRadius() + newLife.getRadius() - 10 && !newLife.isTaken()) {
            life = life + 1;
            newLife.taken();
        }

    }

    public void highScore() {

    }

    public void newLevel() {
        level++;
        s.newLevel();
        newLife.taken();
        if (level == 2) {
            s.stopAll();
            s.level2();
            currentMaze = mazeImage2;
            monsterWait = monsterWait - 1;

        } else if (level == 3) {
            s.stopAll();
            s.level3();
            currentMaze = mazeImage3;
            monsterWait = monsterWait - 1;

        } else if (level == 4) {
            s.stopAll();
            s.level4();
            currentMaze = mazeImage4;
            monsterWait = monsterWait - 1;

        }

        mazeMatrix = new MazeMatrix(resolution, currentMaze);
        mazeMatrix.initMatrix();
        mazeArray = mazeMatrix.getArray();
        smartGhost.setX(xMonsterStart);
        smartGhost.setY(yMonsterStart);
        smartGhost.updateArray(mazeArray);
        smartGhost.goMonsters(xPacStart, yPacStart);
        pac.setX(xPacStart);
        pac.setY(yPacStart);

        for (int i = 0; i < dumbGhostArray.size(); i++) {
            dumbGhostArray.get(i).setX(xDumbStart);
            dumbGhostArray.get(i).setY(yDumbStart);
            dumbGhostArray.get(i).updateArray(mazeArray);
            dumbGhostArray.get(i).goMonsters(xPacStart, yPacStart);

        }

        initCoins();
    }

    public void initImages() {
        String filePath = new File("").getAbsolutePath();
           // filePath = filePath.concat("\\src\\PacManGIT\\Sounds\\gameOver.wav");

        String imageMazePath = filePath.concat("\\src\\PacManGIT\\Images\\Originalpacmaze.PNG");
        String imagePacRightPath = filePath.concat("\\src\\PacManGIT\\Images\\pacRight.jpg");
        String imagePacLeftPath = filePath.concat("\\src\\PacManGIT\\Images\\pacLeft.jpg");
        String imagePacUpPath = filePath.concat("\\src\\PacManGIT\\Images\\pacUp.jpg");
        String imagePacDownPath = filePath.concat("\\src\\PacManGIT\\Images\\pacDown.jpg");
        String imageMonster1 = filePath.concat("\\src\\PacManGIT\\Images\\pacMonster1.PNG"); 
        String imageMonster2 = filePath.concat("\\src\\PacManGIT\\Images\\pacMonster2.jpg"); 
        String imageGameOver = filePath.concat("\\src\\PacManGIT\\Images\\gameOver.jpg"); 
        String imageMazePath2 = filePath.concat("\\src\\PacManGIT\\Images\\PacMaze2.png"); 
        String imageMazePath3 = filePath.concat("\\src\\PacManGIT\\Images\\PacMaze3.png");
        String imageMazePath4 = filePath.concat("\\src\\PacManGIT\\Images\\PacMaze4.png"); 
        String heartPath = filePath.concat("\\src\\PacManGIT\\Images\\heart.jpg"); 
        
        try {
            heart = ImageIO.read(new File(heartPath));
            pacRight = ImageIO.read(new File(imagePacRightPath));
            pacLeft = ImageIO.read(new File(imagePacLeftPath));
            pacUp = ImageIO.read(new File(imagePacUpPath));
            pacDown = ImageIO.read(new File(imagePacDownPath));
            mazeImage1 = ImageIO.read(new File(imageMazePath));
            monsterImage1 = ImageIO.read(new File(imageMonster1));
            monsterImage2 = ImageIO.read(new File(imageMonster2));
            gameOver = ImageIO.read(new File(imageGameOver));
            mazeImage2 = ImageIO.read(new File(imageMazePath2));
            mazeImage3 = ImageIO.read(new File(imageMazePath3));
            mazeImage4 = ImageIO.read(new File(imageMazePath4));
        } catch (IOException ex) {
            Logger.getLogger(PanelLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    public void whiteToBlack(BufferedImage pac) {
//        int w = pac.getWidth();
//        int h = pac.getHeight();
//
//        for (int i = 0; i < h; i++) {
//            for (int j = 0; j < w; j++) {
//                Color myColor = new Color(pac.getRGB(j, i));
//
//                if (myColor.getRed() > 200 && myColor.getBlue() > 200 && myColor.getGreen() > 200) {
//                    pac.setRGB(j, i, 0);
//                }
//
//            }
//        }
//    }
    public void newLife() {
        newLife.init();
    }

    public boolean canGo(int dir) { // 1: right, 2: down,....
        if (run && mazeArray.size() > 0) {
            int xArrayPos = (int) (pac.getXCoord() / resolution);
            int yArrayPos = (int) (pac.getYCoord() / resolution);
            int radius = (int) pac.getRadius() / resolution;
            switch (dir) {
                case 1:
                    return (mazeArray.get((xArrayPos + radius + 1) % mazeArray.size()).get(yArrayPos) == 1); // 1 betyder fritt
                case 2:
                    return (mazeArray.get(xArrayPos).get((yArrayPos + radius + 1) % mazeArray.get(0).size()) == 1);
                case 3:
                    if (xArrayPos < radius) {
                        return mazeArray.get(mazeArray.size() - 2).get(yArrayPos) == 1;
                    } else {
                        return (mazeArray.get((xArrayPos - radius) % mazeArray.size()).get(yArrayPos) == 1);
                    }
                case 4:
                    if (yArrayPos < radius) {
                        return mazeArray.get(xArrayPos).get(mazeArray.get(0).size() - 2) == 1;
                    } else {
                        return (mazeArray.get(xArrayPos).get((yArrayPos - radius) % mazeArray.get(0).size()) == 1);
                    }
                default:
                    return (false);

            }
        }
        return (false);
    }

    public void monsterTick() {

        try {
            Thread.sleep(monsterWait);
        } catch (InterruptedException ex) {
            Logger.getLogger(PanelLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < dumbGhostArray.size(); i++) {

            if (dumbGhostArray.get(i).goMonsters(pac.getXCoord(), pac.getYCoord()) == 0) {
                lifeLost();
            }
        }
        if (smartGhost.goMonsters(pac.getXCoord(), pac.getYCoord()) == 0) {
            lifeLost();
        }

        //   }
    }

    public void gameOver() {
        s.stopAll();
        s.gameOver();
        state = STATE.GameOver;
        try {
            h.init();
        } catch (IOException ex) {
            Logger.getLogger(PanelLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        int lowestScore = h.getLowestScore();
        int score = pac.getCoin();
        if (score > lowestScore) {
            JFrame f = new JFrame("Highscore");
            f.setPreferredSize(new Dimension(300, 200));
            f.setAlwaysOnTop(true);
            String name = JOptionPane.showInputDialog(f, "Congratulations you got into the top 9 highscore! Please write your name below.");

            try {
                h.sortAndAdd(name, score);
            } catch (IOException ex) {
                Logger.getLogger(PanelLogic.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        // run = false;
    }

    public void lifeLost() {

        life--;
        if (life == 0) {
            gameOver();
        } else {
            s.lostLife();
            smartGhost.setX(xMonsterStart);
            smartGhost.setY(yMonsterStart);
            for (int i = 0; i < dumbGhostArray.size(); i++) {
                dumbGhostArray.get(i).setX(xDumbStart);
                dumbGhostArray.get(i).setY(yDumbStart);
                pac.setX(xPacStart);
                pac.setY(yPacStart);

            }

            repaint();
        }
    }

    public void pacTick() {

        try {
            Thread.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(PanelLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (right && canGo(1)) {
            pac.setImage(pacRight);
            pac.setX(pac.getXCoord() + 1);

        } else if (left && canGo(3)) {

            pac.setImage(pacLeft);
            pac.setX(pac.getXCoord() - 1);

        } else if (up && canGo(4)) {
            pac.setImage(pacUp);
            pac.setY(pac.getYCoord() - 1);

        } else if (down && canGo(2)) {
            pac.setImage(pacDown);
            pac.setY(pac.getYCoord() + 1);

        }

//                for (int i = 0; i < monsterArray.size(); i++) {
//                    monsterArray.get(i).goMonsters(pac.getXCoord(), pac.getYCoord());
//                }
        takeCoinLife();
        tick = 0;
        //   }

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        if (state == STATE.Game) {
            if (life > 0) {

                g2.drawImage(currentMaze, 0, 0, xFrameSize, yFrameSize, null);
                if (init) {
                    pac.paint(g);
                    g.setColor(Color.yellow);
                    // mazeMatrix.paint(g);
                    for (int i = 0; i < coinArray.size(); i++) {
                        coinArray.get(i).paint(g);
                    }
                    for (int i = 0; i < dumbGhostArray.size(); i++) {
                        dumbGhostArray.get(i).paint(g);
                    }
                    smartGhost.paint(g);
                    g2.setFont(new Font("TimesRoman", Font.BOLD, 20));
                    g2.setColor(Color.WHITE);
                    g2.drawString("Coins = " + pac.getCoin(), 20, 20);
                    g2.drawString("Level = " + level, 20, 40);
                    g2.drawString("Life = " + life, 700, 20);

                    if (!newLife.isTaken()) {
//                         g2.drawString("Life = " + life, 700, 266);
                        newLife.paint(g);
                    }
                    //newLife.paint(g);
                }
            }
        } else if (state == STATE.GameOver) {
            g2.drawImage(gameOver, 0, 0, xFrameSize, yFrameSize, null);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("TimesRoman", Font.BOLD, 100));
            g2.drawString("Coins = " + pac.getCoin(), 150, 150);
            m.paint(g);

        } else if (state == STATE.Menu) {
            m.paint(g);

        } else if (state == STATE.Restart) {
            state = STATE.Menu;
            restart();

        } else if (state == STATE.HighScore) {

            //m.highScore();
            m.paint(g);
        }
        g2.dispose();

    }

    public void restart() {
        run = false;
        s.stopAll();
        mazeMatrix.removeAll();
        mazeArray.removeAll(mazeArray);
        dumbGhostArray.removeAll(dumbGhostArray);
        smartGhost.removeAll();
        coinArray.removeAll(coinArray);
        state = STATE.Menu;
        level = 1;
        life = nbrLivesStart;
        newLife.taken();
        this.start();

    }

    @Override
    public void run() {

        String threadName = Thread.currentThread().getName();//get the current thread's name
        if (threadName.equals("pacLoop")) {//if current thread is thread1, increment
            while (run) {
                //System.out.println(state);
                repaint();
                if (state == STATE.Game) {
                    pacTick();

                }
            }

        } else if (threadName.equals("monsterLoop")) {
            while (run) {
                repaint();
                if (state == STATE.Game) {
                    monsterTick();

                }

            }
        }
    }

    private class Key implements KeyListener {

        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            if (key == KeyEvent.VK_UP) {
                //System.out.println("Up");
                allFalse();
                up = true;
                //pac.setY(Pac.getYCoord() - 10);
            } else if (key == KeyEvent.VK_DOWN) {
                // System.out.println("Down");
                allFalse();
                down = true;
                // Pac.setY(Pac.getYCoord() + 10);
            }
            if (key == KeyEvent.VK_RIGHT) {
                //System.out.println("Right");
                allFalse();
                right = true;
                // Pac.setX(Pac.getXCoord() + 10);
            } else if (key == KeyEvent.VK_LEFT) {
                // System.out.println("Left");
                allFalse();
                left = true;
                // Pac.setX(Pac.getXCoord() - 10);
            } else if (key == KeyEvent.VK_SPACE) {
                // System.out.println("Left");
                run = !run;
                // Pac.setX(Pac.getXCoord() - 10);
            } else if (key == KeyEvent.VK_ENTER) {
                // System.out.println("Left");
                restart();
                // Pac.setX(Pac.getXCoord() - 10);
            }

        }

        public void keyReleased(KeyEvent e) {

        }

        public void keyTyped(KeyEvent e) {

        }

        public void allFalse() {
            down = false;
            right = false;
            left = false;
            up = false;
        }

    }

}

//skit
//    public boolean isArrayPosFree(int x, int y) {
//        int xArrayPos = (int) (Pac.getXCoord() / resolution);
//        int yArrayPos = (int) (Pac.getYCoord() / resolution);
//        for (int i = -1; i < 5; i++) { // test x-led
//            if (mazeArray.get(yArrayPos * mazeMatrix.getloopRow() + xArrayPos + i) == 0) {
//                return (false);
//            }
//        }
//        for (int i = -1; i < 5; i++) { // test x-led
//            if (mazeArray.get((yArrayPos + i) * mazeMatrix.getloopRow() + xArrayPos) == 0) {
//                return (false);
//            }
//        }
//        return (true);
//    }
//
//    public boolean canGo(int dir) { // 1: right, 2: down,....
//        int xArrayPos = (int) (Pac.getXCoord() / resolution);
//        int yArrayPos = (int) (Pac.getYCoord() / resolution);
//        switch (dir) {
//            case 1:
//                return (isArrayPosFree(yArrayPos, xArrayPos + 1));
//            case 2:
//                return (isArrayPosFree(yArrayPos + 1, xArrayPos));
//            case 3:
//               return (isArrayPosFree(yArrayPos, xArrayPos - 1));
//            case 4:
//                return (isArrayPosFree(yArrayPos + 1, xArrayPos));
//            default:
//                return (false);
//
//        }
