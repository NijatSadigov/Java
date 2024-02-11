package tiles;

import java.io.BufferedReader;
import java.io.IOException;

import src.GamePanel;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import java.io.InputStream;
import java.io.InputStreamReader;

public class tileManager {
    GamePanel gP;
    public tile[] tiles;
   public int mapTileNum[][];
   public int Level=1;
    public tileManager(GamePanel gamePanel) {
        this.gP = gamePanel;
        mapTileNum = new int[gP.maxWorldCol][gP.maxWorldRow];
        tiles = new tile[10];
        getTileImage();
        
        String LevelPath = "/levels/level" + Level + ".txt";

        loadMap(LevelPath);
    }

    public void getTileImage() {

        try {
            tiles[0] = new tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/player/grass.png"));

            tiles[1] = new tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/player/wall.png"));
            tiles[1].collision = true;
            tiles[2] = new tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/player/water.png"));
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String levelPath) {

        try {
            System.out.println("Image loading started");
            InputStream is = getClass().getResourceAsStream(levelPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0, row = 0;
            for (row = 0; row < gP.maxWorldRow; row++) {
                String line = br.readLine();
                for (col = 0; col < gP.maxWorldCol; col++) {

                    String numbers[] = line.split(" ");
                    // System.out.println(numbers[col]);
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;

                }
                System.out.println(row);

            }
            System.out.println("Image loading finished");

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2) {

        int WorldCol = 0, WorldRow = 0;
        // x=0,y=0;
        while (WorldCol < gP.maxWorldCol && WorldRow < gP.maxWorldRow) {
            int tileNum = mapTileNum[WorldCol][WorldRow];

            int worldX = WorldCol * gP.tileSize;
            int worldY = WorldRow * gP.tileSize;
            int screenX = worldX - gP.player.worldX + gP.player.screenX;
            int screenY = worldY - gP.player.worldY + gP.player.screenY;
            /// performance booster
            if (worldX +gP.tileSize > gP.player.worldX - gP.player.screenX && worldX-gP.tileSize < gP.player.worldX + gP.player.screenX
                    && worldY+gP.tileSize > gP.player.worldY - gP.player.screenY && worldY-gP.tileSize < gP.player.worldY + gP.player.screenY)
                g2.drawImage(tiles[tileNum].image, screenX, screenY, gP.tileSize, gP.tileSize, null);
                //
            WorldCol++;
            // x+=gP.tileSize;
            if (WorldCol == gP.maxWorldCol) {
                WorldCol = 0;
                // x=0;
                WorldRow++;
                // y+=gP.tileSize;
            }

        }

    }

}
