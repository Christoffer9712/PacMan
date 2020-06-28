package realpac;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class MazeMatrix extends JPanel {

    private int resolution;
    public ArrayList<ArrayList<Integer>> mazeArray = new ArrayList<>();
    private BufferedImage mazeImage;
    public int rowLength, colLength;
    private boolean init = false;

    public MazeMatrix(int resolution, BufferedImage mazeImage) {
        this.mazeImage = mazeImage;
        this.resolution = resolution;
    }

    public void initMatrix() {
        int w = mazeImage.getWidth();
        int h = mazeImage.getHeight();
        colLength = (int) h / resolution;
        rowLength = (int) w / resolution;
        for (int i = 0; i < rowLength; i++) {
            mazeArray.add(new ArrayList<>());
            for (int j = 0; j < colLength; j++) {

                Color myColor = new Color(mazeImage.getRGB(i * resolution, j * resolution));

                if (myColor.getRed() < 20 && myColor.getBlue() < 20 && myColor.getGreen() < 20) {
                    mazeArray.get(i).add(1); //1 betyder fritt
                } else {
                    mazeArray.get(i).add(0); //0 betyder hinder 
                }

            }
        }
        init = true;
    }

//    public void testMid2() {
//        /**
//         * Börja längst upp vänster, gå höger. Om från 0 -> 1 och därefter 0 ->
//         * 1. Kolla koordinaten mittemellan sätt den till 2 Kolla inte hela
//         * vägen utan bara 10 steg
//         *
//         * Efter första loop, nästa loop kolla nedåt
//         *
//         */
//        for (int i = 0 + 1; i < rowLength - 30; i++) {
//            for (int j = 0; j < colLength; j++) {
//                if (mazeArray.get(i).get(j) == 1 && mazeArray.get(i - 1).get(j) == 0) {
//                    for (int k = 1; k < 30; k++) {
//                        if (mazeArray.get(i + k).get(j) == 0) {
//                            mazeArray.get(i + (int) k / 2).set(j, (Integer) 2);
//                            break;
//                        }
//                    }
//
//                }
//            }
//        }
//
//        for (int i = 0; i < rowLength; i++) {
//            for (int j = 0 + 1; j < colLength - 20; j++) {
//                if (mazeArray.get(i).get(j) == 1 && mazeArray.get(i).get(j - 1) == 0) {
//                    for (int k = 1; k < 30; k++) {
//                        if (mazeArray.get(i).get(j + k) == 0) {
//                            mazeArray.get(i).set(j + (int) k / 2, (Integer) 2);
//                            break;
//                        }
//                    }
//
//                }
//            }
//        }
//    }

//    public void initMatrix() {
//        int w = mazeImage.getWidth();
//        int h = mazeImage.getHeight();
//        colLength = (int) h / resolution;
//        rowLength = (int) w / resolution;
//        for (int i = 0; i < colLength; i++) {
//            for (int j = 0; j < rowLength; j++) {
//                Color myColor = new Color(mazeImage.getRGB(j * resolution, i * resolution));
//
//                if (myColor.getRed() < 20 && myColor.getBlue() < 20 && myColor.getGreen() < 20) {
//                    mazeArray.add(1); //1 betyder fritt
//                } else {
//                    mazeArray.add(0); //0 betyder hinder 
//                }
//
//            }
//        }
//        init = true;
//    }
    public ArrayList<ArrayList<Integer>> getArray() {
        return (mazeArray);
    }

    public int getColLength() {
        return (colLength);
    }

    public int getRowLength() {
        return (rowLength);
    }

    public void write() {
        if (init) {
            for (int i = 0; i < colLength; i++) {
                for (int j = 0; j < rowLength; j++) {
                    System.out.print("   " + mazeArray.get(j).get(i));
                }
                System.out.println();
            }
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GREEN);
        if (init) {
            for (int i = 0; i < mazeArray.size(); i++) {
                for (int j = 0; j < mazeArray.get(i).size(); j++) {
                    if (mazeArray.get(i).get(j) == 1) {
                        g2.drawOval((i) * resolution, (j) * resolution, 2, 2);
                    }
                }
            }
        }
    }

}
