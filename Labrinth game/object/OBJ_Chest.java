package object;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject {

    public OBJ_Chest() {
        //
        collision = true;
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/player/chest.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
