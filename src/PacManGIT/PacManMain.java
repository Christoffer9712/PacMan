package PacManGIT;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.*;

public class PacManMain extends JFrame {

    public PacManMain() throws IOException {
        PanelLogic p = new PanelLogic(800, 600);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.add(p);
        this.pack();
        this.setVisible(true);
        p.initImages();
        p.start();

    }

    public static void main(String[] args) throws IOException {
        PacManMain p = new PacManMain();

    }

}
