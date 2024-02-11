package src;

import Entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    CollisionChecker(GamePanel gp) {
        this.gp = gp;

    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = (entityLeftWorldX) / gp.tileSize;
        int entityRightCol = (entityRightWorldX) / gp.tileSize;
        int entityTopRow = (entityTopWorldY) / gp.tileSize;
        int entityBottomRow = (entityBottomWorldY) / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {

            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision)
                    entity.collisionOn = true;
                break;
            case "down":

                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision)
                    entity.collisionOn = true;
                break;
            case "left":

                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision)
                    entity.collisionOn = true;

                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision)
                    entity.collisionOn = true;
                break;

        }

    }

    public int checkObject(Entity e, boolean player) {

        int index = 999;

        for (int i = 0; i < gp.target.length; i++) {
            if (gp.target[i] != null) {

                e.solidArea.x = e.worldX + e.solidArea.x;
                e.solidArea.y = e.worldY + e.solidArea.y;

                gp.target[i].solidArea.x = gp.target[i].worldX + gp.target[i].solidArea.x;
                gp.target[i].solidArea.y = gp.target[i].worldY + gp.target[i].solidArea.y;

                switch (e.direction) {

                    case "up":
                        e.solidArea.y -= e.speed;
                        if (e.solidArea.intersects(gp.target[i].solidArea)) {

                            e.collisionOn = gp.target[i].collision == true;
                            if (player) {
                                index = i;
                            }

                        }
                        break;
                    case "down":
                        e.solidArea.y += e.speed;
                        if (e.solidArea.intersects(gp.target[i].solidArea)) {
                            // System.out.println("down collision");
                            e.collisionOn = gp.target[i].collision == true;
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        e.solidArea.x -= e.speed;
                        if (e.solidArea.intersects(gp.target[i].solidArea)) {
                            // System.out.println("left collision");
                            e.collisionOn = gp.target[i].collision == true;
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        e.solidArea.x += e.speed;
                        if (e.solidArea.intersects(gp.target[i].solidArea)) {
                            // System.out.println("right collision");
                            e.collisionOn = gp.target[i].collision == true;
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                }

                e.solidArea.x = e.solidAreaDefaultX;
                e.solidArea.y = e.solidAreaDefaultY;
                gp.target[i].solidArea.x = gp.target[i].solidAreaDefaultX;
                gp.target[i].solidArea.y = gp.target[i].solidAreaDefaultY;

            }

        }
        return index;

    }

    // npc or monster
    public int checkEntity(Entity e, Entity[] target) {

        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {

                e.solidArea.x = e.worldX + e.solidArea.x;
                e.solidArea.y = e.worldY + e.solidArea.y;

                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (e.direction) {

                    case "up":
                        e.solidArea.y -= e.speed;
                        if (e.solidArea.intersects(target[i].solidArea)) {

                           // e.collisionOn = target[i].collisionOn == true;
                            e.collisionOn=true;;
                                index = i;
                            

                        }
                        break;
                    case "down":
                        e.solidArea.y += e.speed;
                        if (e.solidArea.intersects(target[i].solidArea)) {
                            // System.out.println("down collision");
                                index = i;
                                e.collisionOn=true;;

                        }
                        break;
                    case "left":
                        e.solidArea.x -= e.speed;
                        if (e.solidArea.intersects(target[i].solidArea)) {
                            // System.out.println("left collision");
                            e.collisionOn=true;;

                                index = i;
                        }
                        break;
                    case "right":
                        e.solidArea.x += e.speed;
                        if (e.solidArea.intersects(target[i].solidArea)) {
                            // System.out.println("right collision");
                            e.collisionOn=true;;

                                index = i;
                        }
                        break;
                }

                e.solidArea.x = e.solidAreaDefaultX;
                e.solidArea.y = e.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;

            }

        }
        return index;

    }

    public void checkPlayer(Entity e){

        e.solidArea.x = e.worldX + e.solidArea.x;
        e.solidArea.y = e.worldY + e.solidArea.y;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (e.direction) {

            case "up":
                e.solidArea.y -= e.speed;
                if (e.solidArea.intersects(gp.player.solidArea)) {

                   // e.collisionOn = target[i].collisionOn == true;
                   e.collisionOn=true;;

                    

                }
                break;
            case "down":
                e.solidArea.y += e.speed;
                if (e.solidArea.intersects(gp.player.solidArea)) {
                    // System.out.println("down collision");
                        e.collisionOn=true;;

                }
                break;
            case "left":
                e.solidArea.x -= e.speed;
                if (e.solidArea.intersects(gp.player.solidArea)) {
                    // System.out.println("left collision");
                        e.collisionOn=true;;

                }
                break;
            case "right":
                e.solidArea.x += e.speed;
                if (e.solidArea.intersects(gp.player.solidArea)) {
                    // System.out.println("right collision");
                        e.collisionOn=true;;

                }
                break;
        }

        e.solidArea.x = e.solidAreaDefaultX;
        e.solidArea.y = e.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

     
    }

}
