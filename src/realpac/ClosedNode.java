package realpac;


public class ClosedNode {

    private int x, y, parent;

    public ClosedNode(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setParent(int  parent){
        this.parent = parent;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getParent() {
        return parent;
    }

    public boolean equals(Object t) {
        if (t != null && t.getClass() == this.getClass()) {
            ClosedNode tmp = (ClosedNode) t;
            if (tmp.getX() == this.x && tmp.getY() == this.y) {
                return (true);
            } else {
                return (false);
            }
        }

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
