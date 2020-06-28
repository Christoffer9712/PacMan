package realpac;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.*;

public class RealPac extends JFrame {

    public RealPac() throws IOException {
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
        RealPac p = new RealPac();

    }

}
