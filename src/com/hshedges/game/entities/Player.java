package com.hshedges.game.entities;

import com.hshedges.game.main.GamePanel;
import com.hshedges.game.main.Images;
import com.hshedges.game.objects.Block;
import com.hshedges.game.physics.Collisions;
import com.hshedges.game.states.GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Rectangle {
    int width;
    int height;
    int posX;
    int posY;

    //jumping
    public static final double JUMP_SPEED = -0.0012;
    public static final double MAX_FALL_SPEED = 0.0005;
    public static final double GRAVITY = 0.000000004;
    public boolean jumping;
    public boolean falling;
    public double currentJumpSpeed;


    //movement
    public static final double SPEED = 0.001;
    public boolean right;
    public boolean left;

    //movement animation
    public int aniStage;
    private static final int FPS = 12;
    private static final int targetTime = 1000/FPS;
    private long start;


    public Player(int w ,int h){
        setBounds(GamePanel.WIDTH/2,GamePanel.HEIGHT/2,width,height);
        this.posX = GamePanel.WIDTH/2;
        this.posY = GamePanel.HEIGHT/2;
        this.width = w;
        this.height = h;
        this.jumping = false;
        this.falling = true;
        this.right = false;
        this.left = false;
        this.currentJumpSpeed = 0;
        this.aniStage = 0;
        this.start = System.nanoTime();
    }


    public void tick(Block[][] blocks) {




        if(right) GameState.xOffset += SPEED;

        if(left) GameState.xOffset -= SPEED;

        if(jumping || falling){
            GameState.yOffset += currentJumpSpeed;
            currentJumpSpeed += GRAVITY;
            if(currentJumpSpeed > MAX_FALL_SPEED) currentJumpSpeed = MAX_FALL_SPEED;


        }

        for (int i = 0; i < blocks.length ; i++) {
            for (int j = 0; j < blocks[0].length ; j++) {

                Block b = blocks[i][j];
                if(b.id == 0 || b.id == 9
                        || b.id == 10
                        || b.id == 11
                        || b.id == 12
                        || b.id == 13) continue;



                int rightPos = posX + width + (int)GameState.xOffset;
                int leftPos = posX + (int)GameState.xOffset;
                int downPos = posY + height + (int)GameState.yOffset ;
                int upPos = posY + (int)GameState.yOffset;

                //right
                if(Collisions.playerBlock2(rightPos , upPos + 1,b)
                        || Collisions.playerBlock2(rightPos,  downPos - 1,b)) right = false;
                //left
                if(Collisions.playerBlock2(leftPos, upPos + 1,b)
                        || Collisions.playerBlock2(leftPos , downPos - 1,b)) left = false;

                //up
                if(Collisions.playerBlock2(leftPos + 1, upPos,b)
                        || Collisions.playerBlock2(rightPos - 1, upPos,b)){
                    currentJumpSpeed = GRAVITY;
                }
                //down
                if(Collisions.playerBlock2(rightPos - 1 , downPos,b)
                        || Collisions.playerBlock2(leftPos + 1, downPos ,b)){
                    currentJumpSpeed = 0;
                    jumping = false;
                    falling = false;

                }
                else falling = true;






            }

        }

        long time = System.nanoTime();
        if((time - start) / 1_000_000 > targetTime){
            animate();
            start = time;
        }



    }

    public void drawPlayer(Graphics g) {
        g.setColor(new Color(0,0,0));
        g.drawRect(GamePanel.WIDTH/2,GamePanel.HEIGHT/2,width,height);

        if(right){
            g.drawImage(Images.playerRunRight[aniStage],GamePanel.WIDTH/2,GamePanel.HEIGHT/2,width,height,null);
        }
        else if(left){
            g.drawImage(Images.playerRunLeft[aniStage],GamePanel.WIDTH/2,GamePanel.HEIGHT/2,width,height,null);
        }
        else g.drawImage(Images.playerIdle,GamePanel.WIDTH/2,GamePanel.HEIGHT/2,width,height,null);

    }


    public void keyPressed(int k) {



        if(k == KeyEvent.VK_D){
            right = true;
        }
        if(k == KeyEvent.VK_A) left = true;

        if(k == KeyEvent.VK_SPACE && !jumping) {
            jumping = true;
            currentJumpSpeed = JUMP_SPEED;
            GameState.yOffset -= 1;

        }


    }


    public void keyReleased(int k) {
        if(k == KeyEvent.VK_D){
            right = false;}
        if(k == KeyEvent.VK_A){
            left = false;
        }
    }

    public void animate(){


        if(right || left){
            aniStage = (aniStage + 1) % 6;
        }
        else aniStage = 0;
    }
}
