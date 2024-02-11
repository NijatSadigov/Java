package src;


import Entity.NPC_OldMan;
import object.OBJ_Chest;


public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
        setObject();
    };

    public void setObject() {
      /*  gp.target[0] = new OBJ_Key();
        gp.target[0].worldX = 3 * gp.tileSize;
        gp.target[0].worldY = 3 * gp.tileSize;

        gp.target[1] = new OBJ_Key();
        gp.target[1].worldX = 7 * gp.tileSize;
        gp.target[1].worldY = 7 * gp.tileSize;
*/
        gp.target[2] = new OBJ_Chest();
        gp.target[2].worldX = 14 * gp.tileSize;
        gp.target[2].worldY = 1 * gp.tileSize;

       /*  gp.target[3] = new OBJ_Door();
        gp.target[3].worldX = 10 * gp.tileSize;
        gp.target[3].worldY = 10 * gp.tileSize;
*/
    }


    public void setNpc() {
        gp.nps[0] = new NPC_OldMan(gp);

        gp.nps[0].SetAtRandomPlace();

    }
    public void ResetNpc(){
            if(gp.nps[0]!=null){

                gp.nps[0].SetAtRandomPlace();
            }
        


    }


 
}
