package entity;

import main.GamePanel;

import java.awt.*;


public class CollisionTile extends Entity{


    public Rectangle bounds;

    public CollisionTile(int x, int y) {
        bounds = new Rectangle(x, y, 48, 48);
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
