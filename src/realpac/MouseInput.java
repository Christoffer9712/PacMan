package realpac;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    public boolean listen = true;

    public enum MENUSTATE {
        startmenu,
        endmenu
    };

    public MENUSTATE menustate = MENUSTATE.startmenu;
    public int width = PanelLogic.xFrameSize, height = PanelLogic.yFrameSize;

//    public void stopListen() {
//        listen = false;
//    }
    public void startListen() {
        listen = true;
    }

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (listen && PanelLogic.state == PanelLogic.STATE.Menu) {
            int mx = me.getX();
            int my = me.getY();
            /**
             * public int width = PanelLogic.xFrameSize, height =
             * PanelLogic.yFrameSize; private Rectangle playButton = new
             * Rectangle(width/2 - 100, 150, 200, 80); private Rectangle
             * highScoreButton = new Rectangle(width/2 - 100, 250, 200, 80);
             * private Rectangle endButton = new Rectangle(width/2 - 100, 350,
             * 200, 80);
             *
             */
            if (mx > width / 2 - 100 && mx < width / 2 + 100) {
                if (my > 150 && my < 220) {
                    PanelLogic.state = PanelLogic.STATE.Game;
                    PanelLogic.run = true;

                }
            }

            if (mx > width / 2 - 100 && mx < width / 2 + 100) {
                if (my > 250 && my < 320) {
                    PanelLogic.state = PanelLogic.STATE.HighScore;
                }
            }

            if (mx > width / 2 - 100 && mx < width / 2 + 100) {
                if (my > 350 && my < 420) {
                    System.exit(0);
                }
            }

        } else if (listen && PanelLogic.state == PanelLogic.STATE.GameOver) {
            int mx = me.getX();
            int my = me.getY();

            if (mx > width / 2 - 100 && mx < width / 2 + 100) {
                if (my > 450 && my < 530) {
                    PanelLogic.state = PanelLogic.STATE.Restart;
                }
            }

        } else if (listen && PanelLogic.state == PanelLogic.STATE.HighScore) {
            int mx = me.getX();
            int my = me.getY();

            if (mx > width - 103 && mx < width - 13) {
                if (my > 520 && my < 570) {
                    PanelLogic.state = PanelLogic.STATE.Menu;
                }
            }

        }
    }

    @Override
    public void mouseReleased(MouseEvent me
    ) {

    }

    @Override
    public void mouseEntered(MouseEvent me
    ) {

    }

    @Override
    public void mouseExited(MouseEvent me
    ) {

    }

}
