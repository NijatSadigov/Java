package Entity;


import java.util.Random;

import src.GamePanel;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp){
        super (gp);
direction ="up";
speed=1;
solidArea.x=8;
solidArea.y=16;
solidArea.width=32;
solidArea.height=26;
solidAreaDefaultX = solidArea.x;
solidAreaDefaultY = solidArea.y;

getImage();

    }

    public void getImage() {
     
        up1 =setup( "/player/boy_up_1.png");
        up2 = setup("/player/boy_up_2.png");
        down1 = setup("/player/boy_down_1.png");
        down2 = setup("/player/boy_down_2.png");
        left1 = setup("/player/boy_left_1.png");
        left2 = setup("/player/boy_left_2.png");
        right1 = setup("/player/boy_right_1.png");
        right2 = setup("/player/boy_right_2.png");

  
}




public void setAction(){
    actionLookCounter++;

       // if (actionLookCounter > 100) {
        if(collisionOn==true){
Random random = new Random();
int randomNum = random.nextInt(100)+1;

if(randomNum<25){
    direction="up";
}
else if(randomNum<50){
    direction="down";
}
else if(randomNum<75){
    direction="left";
}
else if(randomNum<100){
    direction="right";

}

actionLookCounter=0;
        }

        
}
}