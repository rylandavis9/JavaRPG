package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Settings
    final int ogTileSize = 16;
    final int scale = 3;
    public final int tileSize = ogTileSize * scale; //Scaling tiles for modern screens
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //16x12 tiles
    final int screenHeight = tileSize * maxScreenRow; //768x576

    double fps = 60;

    InputHandler keyH = new InputHandler();
    Thread gameThread;
    Player player = new Player(this,keyH);

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //set dimensions
        this.setBackground(Color.black); //not really necessary, just for testing pre sprites will remove later if remember
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);


    }

    public void startGameThread() {

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

        player.draw(g2);

        g2.dispose();
    }
}
