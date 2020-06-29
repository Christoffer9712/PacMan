package PacManGIT;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Pac extends JPanel {

    public int xCoord = 0;
    public int yCoord = 0;
    public int radius = 15;
    int xFrameSize = 100;
    int yFrameSize = 100;
    int coin = 0;
    BufferedImage pac;

    public Pac(int xFrameSize, int yFrameSize, BufferedImage pac) {
        this.xFrameSize = xFrameSize;
        this.yFrameSize = yFrameSize;
        this.pac = pac;

    }

    public void setImage(BufferedImage pac) {
        this.pac = pac;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(pac, xCoord - radius, yCoord - radius, 30, 30, this);
//        g2.setColor(Color.RED);
//        g2.fillOval(xCoord, yCoord, 2, 2);

    }

    public void gotCoin() {
        coin++;
    }

    public int getCoin() {
        return (coin);
    }

    public void setX(int newX) {

        if (newX < 0) {
            xCoord = xFrameSize + newX;
        } else {
            xCoord = newX % xFrameSize;
        }

    }

    public void setY(int newY) {
        if (newY < 0) {
            yCoord = yFrameSize + newY;
        } else {
            yCoord = newY % yFrameSize;
        }
    }

    public int getXCoord() {
        return (xCoord);
    }

    public int getYCoord() {
        return (yCoord);
    }

    public int getRadius() {
        return (radius);
    }
}
