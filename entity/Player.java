package entity;

import main.GamePanel;
import main.InputHandler;

import java.awt.*;

public class Player extends Entity{

    GamePanel gp;
    InputHandler keyH;

    public Player(GamePanel gp, InputHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        setDefaults();

    }
    public void setDefaults() {
        x = 100;
        y = 100;
        speed = 4;
    }
    public void update() {
        if(keyH.upPressed) {
            y -= speed;
        }
        if(keyH.downPressed) {
            y += speed;
        }
        if(keyH.leftPressed) {
            x -= speed;
        }
        if(keyH.rightPressed) {
            x += speed;
        }
    }
    public void draw(Graphics g2) {
        g2.setColor(Color.white);

        // x, y, width, height
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
}
