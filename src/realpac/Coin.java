package realpac;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Coin {

    int xCoin, yCoin, radius = 5, resolution;

    public Coin(ArrayList<ArrayList<Integer>> mazeArray, int resolution) {
        Random r = new Random();
        this.resolution = resolution;
//        int yMax = (int) (mazeArray.get(0).size()/rowLength) - 1; 
//        System.out.println(yMax +"  " + rowLength);
        do {
            xCoin = r.nextInt(mazeArray.size());
            yCoin = r.nextInt(mazeArray.get(0).size());

        }while(mazeArray.get(xCoin).get(yCoin) == 0 
//                && mazeArray.get(yCoin * rowLength + xCoin + 1) == 0 
//                && mazeArray.get(yCoin * rowLength + xCoin - 1) == 0
//                && mazeArray.get((yCoin + 1) * rowLength + xCoin) == 0 
//                && mazeArray.get((yCoin - 1) * rowLength + xCoin) == 0
//                
//                && mazeArray.get(yCoin * rowLength + xCoin + 2) == 0 
//                && mazeArray.get(yCoin * rowLength + xCoin - 2) == 0 
//                && mazeArray.get((yCoin + 2) * rowLength + xCoin) == 0
//                && mazeArray.get((yCoin - 2) * rowLength + xCoin) == 0 
                );
        
        xCoin = xCoin * resolution;
        yCoin = yCoin * resolution;
        
    }


    public int getY() {
        return (yCoin);
    }

    public int getX() {
        return (xCoin);
    }

    public int getRadius() {
        return (radius);
    }

    public void paint(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(xCoin - radius, yCoin - radius, 2 * radius, 2 * radius);

    }

}
