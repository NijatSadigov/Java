package Entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import src.GamePanel;

import java.awt.Rectangle;

public class Entity {
    GamePanel gP;
    public int worldX, worldY, speed;
    public int screenX, screenY;
    public String name;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int actionLookCounter = 0;
    public Rectangle solidArea = new Rectangle(4, 16, 32, 26);
    public int solidAreaDefaultX, solidAreaDefaultY;

    public boolean collisionOn = false;

    public int maxLife;
    public int life;

    public Entity(GamePanel gp) {
        this.gP = gp;
    }

    public BufferedImage setup(String ImageName) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(ImageName));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        int screenX = worldX - gP.player.worldX + gP.player.screenX;
        int screenY = worldY - gP.player.worldY + gP.player.screenY;
        /// performance booster
        if (worldX + gP.tileSize > gP.player.worldX - gP.player.screenX
                && worldX - gP.tileSize < gP.player.worldX + gP.player.screenX
                && worldY + gP.tileSize > gP.player.worldY - gP.player.screenY
                && worldY - gP.tileSize < gP.player.worldY + gP.player.screenY) {
            switch (direction) {
                case "up":
                    if (spriteNum == 1)
                        image = up1;
                    if (spriteNum == 2)
                        image = up2;
                    break;
                case "down":
                    if (spriteNum == 1)
                        image = down1;
                    else
                        image = down2;
                    break;
                case "left":
                    if (spriteNum == 1)
                        image = left1;
                    else
                        image = left2;
                    break;
                case "right":
                    if (spriteNum == 1)
                        image = right1;
                    else
                        image = right2;
                    break;

            }
        }

        g2.drawImage(image, screenX, screenY, gP.tileSize, gP.tileSize, null);

    }

    public void setAction() {
    }

    public void update() {
    

            setAction();
            collisionOn = false;

            gP.cChecker.checkTile(this);
            gP.cChecker.checkObject(this,false);
            gP.cChecker.checkPlayer(this);

            
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;

                        break;
                    case "down":
                        worldY += speed;

                        break;
                    case "left":
                        worldX -= speed;

                        break;
                    case "right":
                        worldX += speed;

                        break;

                }
            }

            spriteCounter++;
            if (spriteCounter > 15) {
                spriteCounter = 0;
                if (spriteNum == 1)
                    spriteNum = 2;
                else
                    spriteNum = 1;
                spriteCounter = 0;
            }
        
    }

    public void SetAtRandomPlace(){
        int MaxCol=gP.maxWorldCol;
        int MaxRow=gP.maxWorldRow;
      
        //mapTileNum
        Random random = new Random();
int colN = random.nextInt(MaxCol-1);
Random random2=new Random();
int rowN = random2.nextInt(MaxRow-1);

        //System.out.println("colN:"+colN+" rowN:"+rowN);


        while(gP.tileM.mapTileNum[colN][rowN]!=0 && (colN !=1 || rowN !=9)){
            colN=(int)(Math.random()*MaxCol);
            rowN=(int)(Math.random()*MaxRow);
        }

        worldX=colN*gP.tileSize;
        worldY=rowN*gP.tileSize;

    }

}
