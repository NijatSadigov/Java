package object;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import src.GamePanel;
import java.awt.Rectangle;
public class SuperObject {
   
    public String name;
    public BufferedImage image;
    public  boolean collision = false;
    public  int worldX,worldY;
    public Rectangle solidArea= new Rectangle(0,0,48,48);
    public int solidAreaDefaultX=solidArea.x;
    public int solidAreaDefaultY=solidArea.y; 
    public void draw(Graphics2D g2, GamePanel gP){



   int screenX = worldX - gP.player.worldX + gP.player.screenX;
            int screenY = worldY - gP.player.worldY + gP.player.screenY;
            /// performance booster
            if (worldX +gP.tileSize > gP.player.worldX - gP.player.screenX && worldX-gP.tileSize < gP.player.worldX + gP.player.screenX
                    && worldY+gP.tileSize > gP.player.worldY - gP.player.screenY && worldY-gP.tileSize < gP.player.worldY + gP.player.screenY)
                g2.drawImage(image, screenX, screenY, gP.tileSize, gP.tileSize, null);

        





    }

}
