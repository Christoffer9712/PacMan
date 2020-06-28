package realpac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menue extends JPanel {

    public int width = PanelLogic.xFrameSize, height = PanelLogic.yFrameSize;
    private Rectangle playButton = new Rectangle(width / 2 - 100, 150, 200, 80);
    private Rectangle highScoreButton = new Rectangle(width / 2 - 100, 250, 200, 80);
    private Rectangle endButton = new Rectangle(width / 2 - 100, 350, 200, 80);
    private Rectangle restart = new Rectangle(width / 2 - 100, 450, 200, 80);
    private Rectangle menu = new Rectangle(width - 103, 520, 90, 50);

    private boolean initHighScore = false;
    String[] name;
    int[] score;

    public Menue() {

    }

    public void highScore() throws IOException {
        BufferedReader br = null;

        File file = new File("C:\\Users\\chjal\\Documents\\NetBeansProjects\\RealPac\\src\\realpac\\HighScore.txt");
        br = new BufferedReader(new FileReader(file));
        String st;

        String[] info = new String[2];
        String nextLine;
        name = new String[10];
        score = new int[10];
        int j = 0;
        while ((nextLine = br.readLine()) != null && j < 10) {
            if (!nextLine.equals("")) {
                info = nextLine.split(";");//; would be the delimiter
                name[j] = info[0];
                score[j] = Integer.parseInt(info[1].trim());

                j++;
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if (PanelLogic.state == PanelLogic.STATE.Menu) {
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, width, height);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("arial", Font.BOLD, 50));
            g2d.drawString("PacMan 2.0", width / 2 - 133, 100);

            g2d.setFont(new Font("arial", Font.BOLD, 30));

            g2d.draw(playButton);
            g2d.drawString("Play", width / 2 - 30, 200);

            g2d.draw(highScoreButton);
            g2d.drawString("Highscore", width / 2 - 70, 300);

            g2d.draw(endButton);
            g2d.drawString("End game", width / 2 - 75, 400);
        } else if (PanelLogic.state == PanelLogic.STATE.GameOver) {
            initHighScore = false;
            g2d.setFont(new Font("arial", Font.BOLD, 30));

            g2d.draw(restart);
            g2d.drawString("Main Menu", width / 2 - 75, 500);

        } else if (PanelLogic.state == PanelLogic.STATE.HighScore) {
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, width, height);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("arial", Font.BOLD, 50));
            g2d.drawString("Highscore", width / 2 - 125, 75);
            if (!initHighScore) {
                try {
                    highScore();
                    initHighScore = true;
                } catch (IOException ex) {
                    Logger.getLogger(Menue.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            g2d.setFont(new Font("arial", Font.BOLD, 25));
            for (int i = 0; i < 9; i++) {
                g2d.drawString(Integer.toString(i + 1) + ".   " + name[i], width / 2 - 200, 150 + 45 * i);
                g2d.drawString(Integer.toString(score[i]), width / 2 + 50, 150 + 45 * i);
            }

            g2d.draw(menu);
            g2d.drawString("Menu", width - 90, 550);

        }
    }
}
