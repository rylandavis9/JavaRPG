package main;

import entity.Player;
import main.tile.Map;
import main.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Settings
    final int ogTileSize = 16;
    final int scale = 3;
    public final int tileSize = ogTileSize * scale; //Scaling tiles for modern screens
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //16x12 tiles
    public final int screenHeight = tileSize * maxScreenRow; //768x576

    //world params
    //public final int maxWorldCol = 100;
    //public final int maxWorldRow = 100;
    public final int worldWidth = tileSize * maxScreenCol;
    public final int worldHeight = tileSize * maxScreenRow;

    double fps = 60;


    TileManager tileM = new TileManager(this);
    InputHandler keyH = new InputHandler();
    Thread gameThread;
    public CollisionCheck collisionCheck = new CollisionCheck(this);
    public Player player = new Player(this,keyH);


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //set dimensions
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);


    }

    public void startGameThread() {

        tileM.buildCollisionTilesFromMap(Map.gameMap("res/maps/COLLISION.txt"));
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if(delta >- 1) {
                update();
                repaint();
                delta--;
                drawCount += 1;
                Toolkit.getDefaultToolkit().sync();
            }

            if(timer >= 1000000000) {
                System.out.println("fps: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update() {
        player.update();
    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;


        tileM.draw(g2, Map.gameMap("res/maps/map_layer_0.txt"));
        tileM.draw(g2, Map.gameMap("res/maps/map_layer_1.txt"));
        player.draw(g2);
        tileM.draw(g2, Map.gameMap("res/maps/map_layer_3.txt"));


        g2.dispose();
    }
}
