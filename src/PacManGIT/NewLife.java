/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacManGIT;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author chjal
 */
public class NewLife {

    BufferedImage heart;
    int xLife, yLife, radius = 30, resolution;
    private boolean taken;
    ArrayList<ArrayList<Integer>> mazeArray;

    public NewLife(ArrayList<ArrayList<Integer>> mazeArray, int resolution, BufferedImage heart) {
        xLife = -1;
        yLife = -1;
        taken = true;
        this.mazeArray = mazeArray;
        this.resolution = resolution;
        this.heart = heart;
    }

    public void init() {
        taken = false;
        this.heart = heart;
        Random r = new Random();
        this.resolution = resolution;
//        int yMax = (int) (mazeArray.get(0).size()/rowLength) - 1; 
//        System.out.println(yMax +"  " + rowLength);
        do {
            xLife = r.nextInt(mazeArray.size() - 4) + 2;
            yLife = r.nextInt(mazeArray.get(0).size() - 4) + 2;
            //System.out.println("y = " +   yLife + "x = " +  xLife);

        } while ((mazeArray.get(xLife).get(yLife) == 0) ||
                (mazeArray.get(xLife + 1).get(yLife) == 0) ||
                mazeArray.get(xLife - 1).get(yLife) == 0 ||
                mazeArray.get(xLife).get(yLife + 1) == 0 ||
                mazeArray.get(xLife).get(yLife - 1) == 0 ||
                mazeArray.get(xLife + 1).get(yLife + 1) == 0 ||
                 mazeArray.get(xLife - 1).get(yLife - 1) == 0
                );
//         System.out.println(mazeArray.get(xLife).get(yLife));
//        System.out.println("y = " + yLife + "x = " + xLife);
        xLife = xLife * resolution;
        yLife = yLife * resolution;
       
    }

    public void taken() { // Kan eventuellt tas bort
        taken = true;
    }

    public boolean isTaken() { // Kan eventuellt tas bort
        return (taken);
    }

    public int getY() {
        return (yLife);
    }

    public int getX() {
        return (xLife);
    }

    public int getRadius() {
        return (radius);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(heart, xLife - radius, yLife - radius, radius, radius, null);

    }

}
