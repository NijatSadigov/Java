package Entity;

import src.GamePanel;
import src.KeyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    KeyHandler keyh;
    public int hasKey = 1;
    public String username="Kairat";

    public Player(GamePanel gamePanel, KeyHandler keyH2) {
        super(gamePanel);
        this.keyh = keyH2;
        name = "Player";
        screenX = gP.screenWidth / 2 - (gP.tileSize / 2);
        screenY = gP.screenHeight / 2 - (gP.tileSize / 2);
        // solidArea;// = new Rectangle(8, 16, 32, 26);
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 26;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gP.tileSize * 1;
        worldY = gP.tileSize * 10;
        speed = 4;
        direction = "down";
    }

    public void reset() {
        worldX = gP.tileSize * 1;
        worldY = gP.tileSize * 10;
        speed = 4;
        direction = "down";

    }

    public void getPlayerImage() {

        up1 = setup("/player/boy_up_1.png");
        up2 = setup("/player/boy_up_2.png");
        down1 = setup("/player/boy_down_1.png");
        down2 = setup("/player/boy_down_2.png");
        left1 = setup("/player/boy_left_1.png");
        left2 = setup("/player/boy_left_2.png");
        right1 = setup("/player/boy_right_1.png");
        right2 = setup("/player/boy_right_2.png");

    }

    public void update() {
        if (keyh.upPressed || keyh.downPressed || keyh.leftPressed || keyh.rightPressed) {
            if (keyh.upPressed) {
                direction = "up";
            }
            if (keyh.downPressed) {
                direction = "down";
            }
            if (keyh.leftPressed) {
                direction = "left";
            }
            if (keyh.rightPressed) {
                direction = "right";
            }

            collisionOn = false;
            gP.cChecker.checkTile(this);
            int objIndex = gP.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // check npc
            int npcIndex = gP.cChecker.checkEntity(this, gP.nps);
            interactNPC(npcIndex);
            // System.out.println(objIndex);
            // collision is false player pass else speed=0
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
            }
        }

    }

    private void interactNPC(int npcIndex) {

        if (npcIndex != 999) {
            gP.loseGame();
        }

    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objName = gP.target[i].name;

            switch (objName) {
                case "key":
                    hasKey++;
                    gP.target[i] = null;
                    break;
                case "Door":
                    if (hasKey > 0) {
                        hasKey--;

                        gP.target[i] = null;
                    }
                    break;
                case "Chest":

                    gP.GameState = gP.WinState;

                    // gP.obj[i] = null;

                    break;
            }

        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
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

        g2.drawImage(image, screenX, screenY, gP.tileSize, gP.tileSize, null);

    }

}
