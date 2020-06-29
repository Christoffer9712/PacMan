package PacManGIT;


public class Node implements Comparable {

    private double f, h, g;
    private int xCurrent, yCurrent;
    //private int xStart, yStart, xGoal, yGoal;
    private int parent;

    public Node() {

    }

    public Node(int xCurrent, int yCurrent, int xStart, int yStart, int xGoal, int yGoal, int parent) {
        this.xCurrent = xCurrent;
        this.yCurrent = yCurrent;
//        this.xStart = xStart;
//        this.yStart = yStart;
//        this.xGoal = xGoal;
//        this.yGoal = yGoal;
        this.parent = parent;
        this.h = Math.sqrt(Math.pow(xStart - xCurrent, 2) + Math.pow(yStart - yCurrent, 2));
        this.g = Math.sqrt(Math.pow(xGoal - xCurrent, 2) + Math.pow(yGoal - yCurrent, 2));
        this.f = g; //ev + h
    }

    public double getF() {
        return (this.f);
    }

    public String getId() {
        return ("tmp"); //DEBUG
    }

    public int getParent() {
        return (parent);
    }

    public int getX() {
        return xCurrent;
    }

    public int getY() {
        return yCurrent;
    }

    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            Node tmp = (Node) obj;
            if (this.f == tmp.getF() && this.getX() == tmp.getX() && this.getY() == tmp.getY()) {
                return (true);
            } else {
                return (false);
            }

        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Object t) {
        if (t.getClass() == this.getClass()) {
            Node tmp = (Node) t;
            if (this.f < tmp.getF()) { //Kanske ett spann?
                return (-1);  //Är denna mindre?
            } else if (this.f > tmp.getF()) {
                return (1); //1 för att sortera med låga längst ned
            } else if (this.f == tmp.getF()) {
                return 0;
            }
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
