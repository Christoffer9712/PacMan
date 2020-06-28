package realpac;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AStar {

    ArrayList<ClosedNode> closedNode; //Här lagras info om vilka noder som redan undersökts 
    List<Node> open; //Vilka noder ska undersökas

    ArrayList<ArrayList<Integer>> mazeArray;

    int xStart;
    int yStart;
    int xGoal;
    int yGoal;

    public AStar(ArrayList<ArrayList<Integer>> mazeArray, int xStart, int yStart, int xGoal, int yGoal) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xGoal = xGoal;
        this.yGoal = yGoal;
        this.mazeArray = mazeArray;
        if (mazeArray != null) {
            if (mazeArray.get(xStart).get(yStart) == 0) {
                throw new UnsupportedOperationException("Invalid startpos");
            }
            if (mazeArray.get(xGoal).get(yGoal) == 0) {
                throw new UnsupportedOperationException("Invalid goalpos");
            }
        }
        closedNode = new ArrayList();
        open = new ArrayList();
    }

    public ArrayList<Integer> beginAStar() {
//        mazeArray.get(xStart).set(yStart, 5);
//        mazeArray.get(xGoal).set(yGoal, 6);
        //  printArr(mazeArray);
        Node nStart = new Node(xStart, yStart, xStart, yStart, xGoal, yGoal, 0);

        open.add(nStart);
        Node currentNode;
        while (true) {

            open = open.stream().sorted().distinct().collect(Collectors.toList()); // sortera de öppna så att lägst f-värde ligger överst

            // System.out.println("x = " + open.get(0).getX() + "    y = " + open.get(0).getY());
            currentNode = open.get(0); // hämta nod med lägst f-värde

            open.remove(0); // ta bort current från de öppna
            ClosedNode cnTmp = new ClosedNode(currentNode.getX(), currentNode.getY());  // Skapa en stängd nod 
            cnTmp.setParent(currentNode.getParent()); //lägg till föräldrar i den stängda noden
            closedNode.add(cnTmp);
            // mazeArray.get(cnTmp.getX()).set(cnTmp.getY(), 3);
            //  System.out.println(closedNode.contains(new ClosedNode(currentNode.getX(), currentNode.getY())));
            if (currentNode.getX() == xGoal && currentNode.getY() == yGoal) {
                break; //found path
            }

            if (currentNode.getX() < mazeArray.size() - 1 && mazeArray.get(currentNode.getX() + 1).get(currentNode.getY()) != 0) { //Lägg till åt höger
                if (!closedNode.contains(new ClosedNode(currentNode.getX() + 1, currentNode.getY()))) {
                    open.add(new Node(currentNode.getX() + 1, currentNode.getY(), xStart, yStart, xGoal, yGoal, 1));

                    //      mazeArray.get(currentNode.getX() + 1).set(currentNode.getY(), 2);
                }
            }
            if (currentNode.getY() < mazeArray.get(0).size() - 1 && mazeArray.get(currentNode.getX()).get(currentNode.getY() + 1) != 0) { //Lägg till nedåt
                if (!closedNode.contains(new ClosedNode(currentNode.getX(), currentNode.getY() + 1))) {
                    open.add(new Node(currentNode.getX(), currentNode.getY() + 1, xStart, yStart, xGoal, yGoal, 2));
                    //           mazeArray.get(currentNode.getX()).set(currentNode.getY() + 1, 2);
                }
            }
            if (currentNode.getX() > 0 && mazeArray.get(currentNode.getX() - 1).get(currentNode.getY()) != 0) { //Lägg till åt vänster
                if (!closedNode.contains(new ClosedNode(currentNode.getX() - 1, currentNode.getY()))) {
                    open.add(new Node(currentNode.getX() - 1, currentNode.getY(), xStart, yStart, xGoal, yGoal, 3));

                    //         mazeArray.get(currentNode.getX() - 1).set(currentNode.getY(), 2);
                }
            }
            if (currentNode.getY() > 0 && mazeArray.get(currentNode.getX()).get(currentNode.getY() - 1) != 0) { //Lägg till uppåt
                if (!closedNode.contains(new ClosedNode(currentNode.getX(), currentNode.getY() - 1))) {
                    open.add(new Node(currentNode.getX(), currentNode.getY() - 1, xStart, yStart, xGoal, yGoal, 4));
                    //         mazeArray.get(currentNode.getX()).set(currentNode.getY() - 1, 2);
                }
            }
        }
        // printArr(mazeArray);
        int parent = currentNode.getParent();
        ArrayList<Integer> path = new ArrayList(); //hur ska man gå? 
        ClosedNode cnTmp = new ClosedNode(currentNode.getX(), currentNode.getY());
        cnTmp.setParent(parent);
        OUTER:
        while (true) {
            path.add(parent);
            switch (parent) {
                case 1: //om förälder = 1 betyder att den kom från vänster.
                {
                    cnTmp = new ClosedNode(cnTmp.getX() - 1, cnTmp.getY()); //hämta den föregående noden
                    ClosedNode cnTmp2 = new ClosedNode(cnTmp.getX(), cnTmp.getY()); //var tvungen att göra såhär...
                    List<ClosedNode> list = closedNode.stream().filter(s -> s.equals(cnTmp2)).collect(Collectors.toList()); // Finns någon stängd = cnTmp (måste finnas)
                    parent = list.get(0).getParent(); //Hmta nya föräldern
                    cnTmp.setParent(parent);
                    break;
                }
                case 2: {
                    cnTmp = new ClosedNode(cnTmp.getX(), cnTmp.getY() - 1);
                    ClosedNode cnTmp2 = new ClosedNode(cnTmp.getX(), cnTmp.getY());
                    List<ClosedNode> list = closedNode.stream().filter(s -> s.equals(cnTmp2)).collect(Collectors.toList());
                    parent = list.get(0).getParent();
                    cnTmp.setParent(parent);
                    break;
                }
                case 3: {
                    cnTmp = new ClosedNode(cnTmp.getX() + 1, cnTmp.getY());
                    ClosedNode cnTmp2 = new ClosedNode(cnTmp.getX(), cnTmp.getY());
                    List<ClosedNode> list = closedNode.stream().filter(s -> s.equals(cnTmp2)).collect(Collectors.toList());
                    parent = list.get(0).getParent();
                    cnTmp.setParent(parent);
                    break;
                }
                case 4: {
                    cnTmp = new ClosedNode(cnTmp.getX(), cnTmp.getY() + 1);
                    ClosedNode cnTmp2 = new ClosedNode(cnTmp.getX(), cnTmp.getY());
                    List<ClosedNode> list = closedNode.stream().filter(s -> s.equals(cnTmp2)).collect(Collectors.toList());
                    parent = list.get(0).getParent();
                    cnTmp.setParent(parent);
                    break;
                }
                case 0:
                    break OUTER; //om förälder = 0 så är det startpositionen
            }
        }

        ArrayList<Integer> pathTmp = new ArrayList();
        for (int i = path.size() - 2; i >= 0; i--) {
            pathTmp.add(path.get(i)); //invertera path
        }
        path = pathTmp;
        return (path);

    }

    public void printArr(ArrayList<ArrayList<Integer>> mazeArray) {
        for (int i = 0; i < mazeArray.get(0).size(); i++) {
            for (int j = 0; j < mazeArray.size(); j++) {
                System.out.print(mazeArray.get(j).get(i) + "   ");

            }
            System.out.println();
        }
    }

}
