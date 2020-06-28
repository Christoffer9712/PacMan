package realpac;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class Monster extends JPanel {

    public int xCoord = 400; // xCoord är mitt i spöket, radien är 15
    public int yCoord = 290;
    int xFrameSize = 100;
    int yFrameSize = 100;
    int speed = 1, resolution;
    BufferedImage mon;
    ArrayList<ArrayList<Integer>> mazeArray;
    int xArrayPos, yArrayPos; //Monster position in matrix
    int xPacArrayPos, yPacArrayPos;
    private AStar a;
    ArrayList<Integer> path;

    public Monster(ArrayList<ArrayList<Integer>> mazeArray, int xFrameSize, int yFrameSize, int speed, int resolution, BufferedImage mon) {
        this.xFrameSize = xFrameSize;
        this.yFrameSize = yFrameSize;
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

//    public boolean canGo(int dir) { // 1: right, 2: down,....
//        int xArrayPos = xCoord / resolution;
//        int yArrayPos = yCoord / resolution;
//        int stepSize = (int) (15 / resolution);
////System.out.println(dir);
//        switch (dir) {
//            case 1:
//                return (mazeArray.get((xArrayPos + stepSize) % mazeArray.size()).get(yArrayPos) == 1); // 1 betyder fritt
//            case 2:
//                return (mazeArray.get(xArrayPos).get((yArrayPos + stepSize) % mazeArray.get(0).size()) == 1);
//            case 3:
//                if (xArrayPos < stepSize) {
//                    return mazeArray.get(mazeArray.size() - 2).get(yArrayPos) == 1;
//                } else {
//                    return (mazeArray.get((xArrayPos - stepSize) % mazeArray.size()).get(yArrayPos) == 1);
//                }
//            case 4:
//                if (yArrayPos < stepSize) {
//                    return mazeArray.get(xArrayPos).get(mazeArray.get(0).size() - 2) == 1;
//                } else {
//                    return (mazeArray.get(xArrayPos).get((yArrayPos - stepSize) % mazeArray.get(0).size()) == 1);
//                }
//            default:
//                return (false);
//
//        }
//
//    }
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
        if (PanelLogic.run && mazeArray.size() > 0) {
            this.xPacArrayPos = (int) xPac / resolution;
            this.yPacArrayPos = (int) yPac / resolution;
            a = new AStar(mazeArray, (int) (this.getXCoord() / resolution), (int) (this.getYCoord()) / resolution, xPacArrayPos, yPacArrayPos);
            path = a.beginAStar();

            if (path.size() > 0) {
//            for (int i = 0; i < 10; i++) {
                go(path.get(0));
//              //  System.out.println(path.get(0));
                return (1);

            } else {
//            for (int i = 0; i < path.size(); i++) {
//                go(path.get(i));
                return (0);

            }
        } else {
            return (0);
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
