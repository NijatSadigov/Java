package src;

import java.awt.Dimension;

import javax.swing.JPanel;

import Entity.Entity;
import Entity.Player;
import object.SuperObject;
import tiles.tileManager;

import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTitleSize = 16;// 16*16 size default size of objects
    final int scale = 3;
    public final int tileSize = originalTitleSize * scale;
    public final int maxScreenColumn = 6;
    public final int maxScreenRow = 6;
    public final int screenWidth = maxScreenColumn * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;
    // WOrld Setings
    public final int maxWorldCol = 16;
    public final int maxWorldRow = 12;
    public final int WorldWidth = maxWorldCol * tileSize;
    public final int WorldHeight = maxWorldCol * tileSize;

    // fps
    int fps = 60;
    Thread gameThread;
    KeyHandler keyH = new KeyHandler(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);
    public SuperObject target[] = new SuperObject[10];
    public Entity nps[] = new Entity[10];

    public tileManager tileM = new tileManager(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public SaveLoad sl=new SaveLoad(this);
    // player default place
    // gamestates
    public int Score;
    public int GameState;
    public int PlayState = 1;
    public int PauseState = 2;
    public int titleState = 0;
    public int WinState = 3;
    public int ScoreState = 4;
public int LoseState=5;
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(java.awt.Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        Score=0;


    }

    public void setupGame() {
        
        aSetter.setObject();
        aSetter.setNpc();
        GameState = titleState;
    }

    public void StartGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        while (gameThread != null) {
            // System.out.println("Game Thread is running");

            uptade();

            repaint();

            try {
                Thread.sleep(1000 / fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void uptade() {

        if (GameState == titleState || GameState == ScoreState || GameState == WinState || GameState == LoseState) {
        } else {

        player.update();

        for (int i = 0; i < nps.length; i++) {
            if (nps[i] != null) {
                nps[i].update();
            }
        }
    
    }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // title

        if (GameState == titleState || GameState == ScoreState) {
            ui.draw(g2);
        } else {

            tileM.draw(g2);

            // object
            for (int i = 0; i < target.length; i++) {
                if (target[i] != null) {
                    target[i].draw(g2, this);

                }

            }

            for (int i = 0; i < nps.length; i++) {
                if (nps[i] != null) {
                    nps[i].draw(g2);

                }

            }

            // players
            player.draw(g2);

            ui.draw(g2);

        }
        // tiles

        g2.dispose();

    }


public void SaveGame(){
//saveScore
sl.Save();

}

public void nextLevel() {

    tileM.Level++;
    String LevelPath = "/levels/level" + tileM.Level + ".txt";

        tileM.loadMap(LevelPath);


    player.reset();
    aSetter.ResetNpc();
    player.hasKey+=1;
}

public void Reset(){
player.reset();

player.hasKey=1;

aSetter.ResetNpc();

String LevelPath = "/levels/level" + tileM.Level + ".txt";

tileM.loadMap(LevelPath);
tileM.Level=1;

ui.playTime=0;
}

public void loseGame() {
SaveGame();

    GameState=LoseState;

}
}
