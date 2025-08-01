package main.tile;

import entity.CollisionTile;
import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class TileManager {

    GamePanel gp;
    HashMap<String, Tile> tileMap = new HashMap<>();
    public List<CollisionTile> collisionTiles = new ArrayList<>();




    public TileManager(GamePanel gp) {
        this.gp = gp;
        loadTilesFromFolder("res/tiles"); // <-- set this path to your exported tiles folder

    }

    private void loadTilesFromFolder(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("Tile folder does not exist: " + folderPath);
            return;
        }

        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
        if (files == null) return;

        for (File file : files) {
            try {
                String fileName = file.getName();
                String tileCode = fileName.substring(0, fileName.length() - 4); // remove ".png"
                Tile t = new Tile();
                t.image = ImageIO.read(file);
                tileMap.put(tileCode, t);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Loaded " + tileMap.size() + " tiles from " + folderPath);
    }

    public void buildCollisionTilesFromMap(String[][] map) {
        collisionTiles.clear(); // Reset if regenerating
        int tileSize = gp.tileSize;

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                String tileCode = map[row][col];
                if (tileCode.equals("c0")) {
                    int worldX = col * tileSize;
                    int worldY = row * tileSize;
                    collisionTiles.add(new CollisionTile(worldX, worldY));
                } else {
                    continue;
                }
            }
        }
    }

    public void draw(Graphics2D g2, String[][] map) {
        int tileSize = gp.tileSize;
        int mapRows = map.length;
        int mapCols = (mapRows > 0) ? map[0].length : 0;

        for (int row = 0; row < mapRows; row++) {
            for (int col = 0; col < mapCols; col++) {
                String tileCode = map[row][col];
                Tile t = tileMap.get(tileCode);
                if (tileCode.equals("e0")) continue;


                if (t != null && t.image != null) {
                    int worldX = col * tileSize;
                    int worldY = row * tileSize;

                    int screenX = worldX - gp.player.worldX + gp.player.screenX;
                    int screenY = worldY - gp.player.worldY + gp.player.screenY;
                    g2.drawImage(t.image, screenX, screenY, tileSize, tileSize, null);

                } else {
                    // Optional: draw a placeholder if tile not found
                    g2.setColor(Color.MAGENTA);
                    int screenX = col * tileSize - gp.player.worldX + gp.player.screenX;
                    int screenY = row * tileSize - gp.player.worldY + gp.player.screenY;
                    g2.fillRect(screenX, screenY, tileSize, tileSize);
                }
            }
        }
    }
}
