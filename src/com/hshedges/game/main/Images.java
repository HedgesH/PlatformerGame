package com.hshedges.game.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {

    public static BufferedImage[] blocks;
    public static BufferedImage[] backGround;
    public static BufferedImage[] playerRunRight;
    public static BufferedImage[] playerRunLeft;
    public static BufferedImage playerIdle;

    public Images(){
        blocks = new BufferedImage[13];
        backGround = new BufferedImage[7];
        playerRunRight = new BufferedImage[6];
        playerRunLeft= new BufferedImage[6];

        loadBlocks();
        loadBackground();
        loadPlayerRun();
        loadPlayer();





    }

    public void loadBlocks(){
        try{

            blocks[0] = ImageIO.read(getClass().getResourceAsStream("/assets/blocks/interior.png"));
            blocks[1] = ImageIO.read(getClass().getResourceAsStream("/assets/blocks/interiorLeft.png"));
            blocks[2] = ImageIO.read(getClass().getResourceAsStream("/assets/blocks/interiorRight.png"));
            blocks[3] = ImageIO.read(getClass().getResourceAsStream("/assets/blocks/interiorSingle.png"));
            blocks[4] = ImageIO.read(getClass().getResourceAsStream("/assets/blocks/grassMiddle.png"));
            blocks[5] = ImageIO.read(getClass().getResourceAsStream("/assets/blocks/grassLeft.png"));
            blocks[6] = ImageIO.read(getClass().getResourceAsStream("/assets/blocks/grassRight.png"));
            blocks[7] = ImageIO.read(getClass().getResourceAsStream("/assets/blocks/grassSingle.png"));
            blocks[8] = ImageIO.read(getClass().getResourceAsStream("/assets/blocks/cave.png"));
            blocks[9] = ImageIO.read(getClass().getResourceAsStream("/assets/blocks/caveTop.png"));
            blocks[10] = ImageIO.read(getClass().getResourceAsStream("/assets/blocks/caveBottom.png"));
            blocks[11] = ImageIO.read(getClass().getResourceAsStream("/assets/blocks/caveLeft.png"));
            blocks[12] = ImageIO.read(getClass().getResourceAsStream("/assets/blocks/caveRight.png"));
        }
        catch (IOException e){
            e.printStackTrace();

        }
    }

    public void loadBackground(){
        try{

            backGround[0] = ImageIO.read(getClass().getResourceAsStream("/assets/background/Clouds.png"));
            backGround[1] = ImageIO.read(getClass().getResourceAsStream("/assets/background/Grass 1.png"));
            backGround[2] = ImageIO.read(getClass().getResourceAsStream("/assets/background/Grass 2.png"));
            backGround[3] = ImageIO.read(getClass().getResourceAsStream("/assets/background/Sky BG 1.png"));
            backGround[4] = ImageIO.read(getClass().getResourceAsStream("/assets/background/Sky BG 2.png"));
            backGround[5] = ImageIO.read(getClass().getResourceAsStream("/assets/background/Water.png"));
            backGround[6] = ImageIO.read(getClass().getResourceAsStream("/assets/background/Water Reflexion.png"));

        }
        catch (IOException e){
            e.printStackTrace();

        }
    }

    public void loadPlayerRun(){
        try{

            playerRunRight[0] = ImageIO.read(getClass().getResourceAsStream("/assets/player/PlayerR1.tiff"));
            playerRunRight[1] = ImageIO.read(getClass().getResourceAsStream("/assets/player/PlayerR2.tiff"));
            playerRunRight[2] = ImageIO.read(getClass().getResourceAsStream("/assets/player/PlayerR3.tiff"));
            playerRunRight[3] = ImageIO.read(getClass().getResourceAsStream("/assets/player/PlayerR4.tiff"));
            playerRunRight[4] = ImageIO.read(getClass().getResourceAsStream("/assets/player/PlayerR5.tiff"));
            playerRunRight[5] = ImageIO.read(getClass().getResourceAsStream("/assets/player/PlayerR6.tiff"));

            playerRunLeft[0] = ImageIO.read(getClass().getResourceAsStream("/assets/player/PlayerL1.tiff"));
            playerRunLeft[1] = ImageIO.read(getClass().getResourceAsStream("/assets/player/PlayerL2.tiff"));
            playerRunLeft[2] = ImageIO.read(getClass().getResourceAsStream("/assets/player/PlayerL3.tiff"));
            playerRunLeft[3] = ImageIO.read(getClass().getResourceAsStream("/assets/player/PlayerL4.tiff"));
            playerRunLeft[4] = ImageIO.read(getClass().getResourceAsStream("/assets/player/PlayerL5.tiff"));
            playerRunLeft[5] = ImageIO.read(getClass().getResourceAsStream("/assets/player/PlayerL6.tiff"));

        }
        catch (IOException e){
            e.printStackTrace();

        }
    }

    public void loadPlayer(){
        try{

            playerIdle = ImageIO.read(getClass().getResourceAsStream("/assets/player/PlayerIdle.tiff"));

        }
        catch (IOException e){
            e.printStackTrace();

        }

    }


    public static void drawBackground(Graphics g){

        g.drawImage(backGround[0],0,0,GamePanel.WIDTH,GamePanel.HEIGHT,null);
        g.drawImage(backGround[3],0,0,GamePanel.WIDTH,GamePanel.HEIGHT,null);
        g.drawImage(backGround[4],0,0,GamePanel.WIDTH,GamePanel.HEIGHT,null);
        g.drawImage(backGround[5],0,0,GamePanel.WIDTH,GamePanel.HEIGHT,null);
        g.drawImage(backGround[6],0,0,GamePanel.WIDTH,GamePanel.HEIGHT,null);
        g.drawImage(backGround[2],0,0,GamePanel.WIDTH,GamePanel.HEIGHT,null);
        g.drawImage(backGround[1],0,0,GamePanel.WIDTH,GamePanel.HEIGHT,null);
    }
}
