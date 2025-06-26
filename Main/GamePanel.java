package Main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    //Settings
    final int ogTileSize = 16;
    final int scale = 3;
    final int tileSize = ogTileSize * scale; //Scaling tiles for modern screens
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //16x12 tiles
    final int screenHeight = tileSize * maxScreenRow; //768x576

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //set dimensions
        this.setBackground(Color.black); //not really necessary, just for testing pre sprites will remove later if remember
        this.setDoubleBuffered(true);


    }
}
