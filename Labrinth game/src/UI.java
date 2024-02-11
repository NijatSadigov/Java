package src;

import java.awt.*;
import object.OBJ_Key;
import java.awt.image.*;
import java.sql.Struct;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font CurrentFont, FinishFont;
    BufferedImage keyImage;
    public boolean gameFinished = false;
    double playTime;
    ScoreType Scores[] = new ScoreType[10];
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public int CommandNumber = 0;

    public UI(GamePanel gp) {

        this.gp = gp;
        CurrentFont = new Font("Arial", Font.PLAIN, 10);
        FinishFont = new Font("Arial", Font.BOLD, 40);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;

    }

    public void draw(Graphics2D g2) {

        if (gp.GameState == gp.LoseState) {

            String text;
            int textLength, x, y;
            text = "YOU lose Game";
            g2.setFont(FinishFont);
            g2.setColor(Color.white);

            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - gp.tileSize;
            g2.drawString(text, x, y);
            g2.drawString("Time: " + dFormat.format(playTime), x, y + gp.tileSize * 3);
            y += gp.tileSize;
            text = gp.tileM.Level + "";
            g2.drawString("Level: " + text, x, y + gp.tileSize * 3);
            // gp.gameThread = null;

            // playTime=0;

        }

        if (gp.GameState == gp.titleState) {

            drawTitleScreen(g2);

        }

        if (gp.GameState == gp.ScoreState) {
            DrawScoreScreen(g2);
        }

        if (gp.GameState == gp.WinState) {

            if (gp.tileM.Level < 10) {
                gp.nextLevel();
                gp.GameState = gp.PlayState;
            } else {
                String text;
                int textLength, x, y;
                text = "finished Game";
                g2.setFont(FinishFont);
                g2.setColor(Color.white);

                textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gp.screenWidth / 2 - textLength / 2;
                y = gp.screenHeight / 2 - gp.tileSize;
                g2.drawString(text, x, y);
                g2.drawString("Time: " + dFormat.format(playTime), x, y + gp.tileSize * 3);
                gp.SaveGame();
                gp.gameThread = null;
            }
        }

        if (gp.GameState == gp.PlayState) {
            g2.setFont(CurrentFont);
            g2.setColor(Color.white);
            playTime += (double) 1 / 60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 5 - 20, gp.tileSize / 3);

            g2.drawImage(keyImage, gp.tileSize / 4 - 10, gp.tileSize / 4 - 10, null);
            g2.drawString("x " + gp.player.hasKey, gp.tileSize / 4 + 10, gp.tileSize / 4);

        }
    }

    private void drawTitleScreen(Graphics2D g2) {
        String text;
        int textLength, x, y;
        text = "Labrhint Game";
        g2.setFont(FinishFont);
        g2.setColor(Color.white);

        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - textLength / 2;
        y = gp.screenHeight / 2 - gp.tileSize;
        g2.drawString(text, x, y);

        // image
        x = gp.screenWidth / 2 - gp.tileSize / 2;
        y += gp.tileSize * 1;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize, gp.tileSize, null);

        g2.setFont(CurrentFont);
        y += gp.tileSize + gp.tileSize / 4;

        text = "Play Game";
        g2.drawString(text, x, y);

        if (CommandNumber == 0) {
            g2.drawString(">", x - gp.tileSize / 4, y);

        }
        y += gp.tileSize / 4;
        text = "Load Game";
        g2.drawString(text, x, y);
        if (CommandNumber == 1) {
            g2.drawString(">", x - gp.tileSize / 4, y);

        }
        y += gp.tileSize / 4;

        text = "exit";
        g2.drawString(text, x, y);
        if (CommandNumber == 2) {
            g2.drawString(">", x - gp.tileSize / 4, y);

        }
    }

    public void DrawScoreScreen(Graphics2D g2) {

        gp.sl.Load();
        // g2.drawImage(keyImage, gp.tileSize / 4 - 10, gp.tileSize / 4 - 10, null);
        String text;
        int textLength, x, y;
        text = "High Scores";
        g2.setFont(CurrentFont);
        g2.setColor(Color.white);

        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - textLength / 2;
        y = gp.screenHeight / 2 - gp.tileSize;
        g2.drawString(text, x, y);

        // image
        x = gp.screenWidth / 2 - gp.tileSize / 2;
        y += gp.tileSize * 1;

        // System.out.println(Scores[3]);
        for (int e=10;e>=1;e--){
          //  if(Scores[e]!=null){
          //      if(Scores[e].level==e)
        for (int i = 0; i < Scores.length; i++) {
            if (Scores[i] != null)
            if(Scores[i].level==e)
            {
                textLength = (int) g2.getFontMetrics().getStringBounds(Scores[i].name, g2).getWidth();
                x = gp.screenWidth / 2 - gp.tileSize / 2 - textLength;
                g2.drawString(Scores[i].name, x, y);
                x += textLength;
                g2.drawString(" Time: " + dFormat.format(Scores[i].time), x, y);
                x += gp.tileSize / 2;
                g2.drawString("           Level: " + Scores[i].level, x, y);
                y += gp.tileSize / 4;
            }
        }
 //   }
   }
    }

}
