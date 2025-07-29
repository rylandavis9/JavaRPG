package main.tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int x = 0;
    int y = 0;




    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[10];

        getTileImage();

    }

    public void getTileImage() {

        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2, String[][] Map) {
        x=0;
        y=0;
        for(int i = 0; i < 20; i++) {

            for (int j = 0; j < 20; j++) {
                switch (Map[i][j]) {
                    case "g" -> g2.drawImage(tile[0].image, x, y, gp.tileSize, gp.tileSize, null);
                    case "b" -> g2.drawImage(tile[1].image, x, y, gp.tileSize, gp.tileSize, null);
                    case "w" -> g2.drawImage(tile[2].image, x, y, gp.tileSize, gp.tileSize, null);
                }
                x += gp.tileSize;
            }
            y += gp.tileSize;
            x=0;
        }


    }

}
