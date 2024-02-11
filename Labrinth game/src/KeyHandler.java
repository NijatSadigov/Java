package src;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (gp.GameState == gp.LoseState) {
          

            if(code == KeyEvent.VK_ESCAPE){
                gp.GameState = gp.titleState;
                gp.Reset();
            }

        }
        if (gp.GameState == gp.ScoreState) {
          

            if(code == KeyEvent.VK_ESCAPE){
                gp.GameState = gp.titleState;

            }
        }





        if (gp.GameState == gp.titleState) {

            if (code == KeyEvent.VK_W) {
                if (gp.ui.CommandNumber > 0)
                    gp.ui.CommandNumber--;
            }
            if (code == KeyEvent.VK_S) {
                if (gp.ui.CommandNumber < 2)

                    gp.ui.CommandNumber++;
            }

            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.CommandNumber == 0) {
                    gp.GameState = gp.PlayState;

                }
                if (gp.ui.CommandNumber == 1) {
                    // later
                    gp.GameState = gp.ScoreState;

                }
                if (gp.ui.CommandNumber == 2) {
                    System.exit(0);
                }
            }

        }

        if (gp.GameState == gp.PlayState) {

            if (code == KeyEvent.VK_W) {
                System.out.println("UP");
                upPressed = true;
            }

            if (code == KeyEvent.VK_S) {
                System.out.println("DOWN");
                downPressed = true;

            }
            if (code == KeyEvent.VK_A) {
                System.out.println("LEFT");
                leftPressed = true;

            }
            if (code == KeyEvent.VK_D) {
                System.out.println("RIGHT");
                rightPressed = true;

            }

            if(code == KeyEvent.VK_ESCAPE){
                gp.GameState = gp.titleState;

            }
            if(code == KeyEvent.VK_R){
                gp.GameState = gp.titleState;
                gp.Reset();

            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

      

            if (code == KeyEvent.VK_W) {
                System.out.println("UP");
                upPressed = false;
            }

            if (code == KeyEvent.VK_S) {
                System.out.println("DOWN");
                downPressed = false;

            }
            if (code == KeyEvent.VK_A) {
                System.out.println("LEFT");
                leftPressed = false;

            }
            if (code == KeyEvent.VK_D) {
                System.out.println("RIGHT");
                rightPressed = false;

            }

    

    }

}
