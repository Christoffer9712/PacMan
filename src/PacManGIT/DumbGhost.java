package PacManGIT;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class DumbGhost extends JPanel {

    public int xCoord = 20; // xCoord är mitt i spöket, radien är 15
    public int yCoord = 20;
    public int xFrameSize = 800, yFrameSize = 600;
    int speed = 1, resolution;
    BufferedImage mon;
    ArrayList<ArrayList<Integer>> mazeArray;
    int xArrayPos, yArrayPos; //Monster position in matrix
    int randomGo = 1;

    public DumbGhost(ArrayList<ArrayList<Integer>> mazeArray, int speed, int resolution, BufferedImage mon) {

        this.mazeArray = mazeArray;
        this.speed = speed;
        this.mon = mon;
        this.resolution = resolution;
        xArrayPos = xCoord / resolution;
        yArrayPos = yCoord / resolution;

    }

    public void updateArray(ArrayList<ArrayList<Integer>> mazeArray) {
        this.mazeArray = mazeArray;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(mon, xCoord - 15, yCoord - 15, 30, 30, this);

    }

    public boolean canGo(int dir) { // 1: right, 2: down,....
        int xArrayPos = xCoord / resolution;
        int yArrayPos = yCoord / resolution;
//System.out.println(dir);
        switch (dir) {
            case 1:
                return (mazeArray.get((xArrayPos + 1) % mazeArray.size()).get(yArrayPos) == 1); // 1 betyder fritt
            case 2:
                return (mazeArray.get(xArrayPos).get((yArrayPos + 1) % mazeArray.get(0).size()) == 1);
            case 3:
                if (xArrayPos < 2) {
                    return mazeArray.get(mazeArray.size() - 1).get(yArrayPos) == 1;
                } else {
                    return (mazeArray.get((xArrayPos - 1) % mazeArray.size()).get(yArrayPos) == 1);
                }
            case 4:
                if (yArrayPos < 2) {
                    return mazeArray.get(xArrayPos).get(mazeArray.get(0).size() - 1) == 1;
                } else {
                    return (mazeArray.get(xArrayPos).get((yArrayPos - 1) % mazeArray.get(0).size()) == 1);
                }
            default:
                return (false);

        }

    }

    public void go(int dir) {
        switch (dir) {
            case 1:
                setX(xCoord + speed);
                break;
            case 2:
                setY(yCoord + speed);
                break;
            case 3:
                setX(xCoord - speed);
                break;
            default:
                setY(yCoord - speed);
                break;
        }

    }

    public int goMonsters(int xPac, int yPac) {
        int xPacArrayPos = (int) xPac / resolution;
        int yPacArrayPos = (int) yPac / resolution;
        
        if(canGo(randomGo)){
            go(randomGo);
        }else{
            Random r = new Random();
            randomGo = r.nextInt(4) +1;
        }
       
        if (Math.abs(xPac - xCoord) < 30 && Math.abs(yPac - yCoord) < 30) {
            return 0; // Killed Pac


        } else {
//            for (int i = 0; i < path.size(); i++) {
//                go(path.get(i));
            return 1;

        }
//       }
//

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

}
