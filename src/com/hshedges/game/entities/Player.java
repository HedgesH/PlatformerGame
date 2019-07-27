package com.hshedges.game.entities;

import com.hshedges.game.main.GamePanel;
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
    public static final double JUMP_SPEED = -0.0005;
    public static final double MAX_FALL_SPEED = 0.0005;
    public static final double GRAVITY = 0.000000001;
    public boolean jumping;
    public boolean falling;
    public double currentJumpSpeed;


    //movement
    public static final double SPEED = 0.0003;
    public boolean right;
    public boolean left;


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
    }


    public void tick(Block[][] blocks) {

        if(jumping || falling){
            currentJumpSpeed += GRAVITY;
            GameState.yOffset += currentJumpSpeed;
            if(currentJumpSpeed > MAX_FALL_SPEED) currentJumpSpeed = MAX_FALL_SPEED;
            if(currentJumpSpeed >= 0) falling = true;

        }


        if(right) GameState.xOffset += SPEED;

        if(left) GameState.xOffset -= SPEED;

        for (int i = 0; i < blocks.length ; i++) {
            for (int j = 0; j < blocks[0].length ; j++) {

                Block b = blocks[i][j];
                if(b.id == 0) continue;

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
                        || Collisions.playerBlock2(leftPos + 1, downPos,b)){
                    currentJumpSpeed = 0;
                    falling = false;
                    jumping = false;
                }
                else falling = true;

            }

        }





    }




    public void drawPlayer(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(GamePanel.WIDTH/2,GamePanel.HEIGHT/2,width,height);
    }


    public void keyPressed(int k) {



        if(k == KeyEvent.VK_D){
            right = true;
        }
        if(k == KeyEvent.VK_A) left = true;

        if(k == KeyEvent.VK_SPACE && !jumping) {
            jumping = true;
            currentJumpSpeed = JUMP_SPEED;
            GameState.yOffset += currentJumpSpeed;

        }


    }


    public void keyReleased(int k) {
        if(k == KeyEvent.VK_D) right = false;
        if(k == KeyEvent.VK_A) left = false;
    }
}
