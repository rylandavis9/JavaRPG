package main;

import entity.CollisionTile;
import entity.Entity;
import entity.Player;
import java.util.List;
import java.awt.*;

public class CollisionCheck {

    GamePanel gp;

    public CollisionCheck(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        Rectangle futureBounds = new Rectangle(
                entity.worldX + entity.solidArea.x,
                entity.worldY + entity.solidArea.y,
                entity.solidArea.width,
                entity.solidArea.height
        );

        // Predict movement based on direction
        switch (entity.direction) {
            case "up" -> futureBounds.y -= entity.speed;
            case "down" -> futureBounds.y += entity.speed;
            case "left" -> futureBounds.x -= entity.speed;
            case "right" -> futureBounds.x += entity.speed;
        }

        for (CollisionTile tile : gp.tileM.collisionTiles) {
            if (futureBounds.intersects(tile.getBounds())) {
                entity.collisionOn = true;
                return; // Collision found, stop checking
            }
        }

        entity.collisionOn = false; // No collision
    }



}
