package com.hshedges.game.entities;

import com.hshedges.game.main.GamePanel;
import com.hshedges.game.main.Images;
import com.hshedges.game.objects.Block;
import com.hshedges.game.objects.MovingBlock;
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
    public static final double JUMP_SPEED = -15;
    public static final double MAX_FALL_SPEED = 15;
    public static final double GRAVITY = 0.5;
    public boolean jumping;
    public boolean falling;
    public double currentJumpSpeed;


    //movement
    public static final double SPEED = 10;
    public boolean right;
    public boolean left;
    public boolean noleft;
    public boolean noright;

    //movement animation
    public int aniStage;
    private static final int FPS = 12;
    private static final int targetTime = 1000/FPS;
    private long start;

    //damage
    public boolean damaged;
    public static final int START_LIVES = 3;
    public int lives;


    public Player(int w ,int h){
        setBounds(GamePanel.WIDTH/2 + 15,GamePanel.HEIGHT/2 + 8,width - 28,height - 10);
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
        this.noleft = false;
        this.noright = false;
        this.damaged = false;
        this.lives = START_LIVES;
    }



    public void tick(Block[][] blocks,MovingBlock[] mblocks){
        if(damaged){ damageTick(); }
        else normalTick(blocks,mblocks);
    }

    public void damageTick(){
        posY += GRAVITY * 30;
        if(posY > GamePanel.HEIGHT){
            posY = GamePanel.HEIGHT/2;
            GameState.yOffset = -GamePanel.HEIGHT/2 - 128;
            GameState.xOffset = -GamePanel.WIDTH/2 + 128;
            damage();

        }
    }

    public void normalTick(Block[][] blocks,MovingBlock[] mblocks) {

        if(jumping || falling){
            GameState.yOffset += currentJumpSpeed;
            currentJumpSpeed += GRAVITY;
            if(currentJumpSpeed > MAX_FALL_SPEED) currentJumpSpeed = MAX_FALL_SPEED;


        }

        if(right) GameState.xOffset += SPEED;

        if(left) GameState.xOffset -= SPEED;

        int collideCounter = 0;

        for (int i = 0; i < blocks.length ; i++) {
            for (int j = 0; j < blocks[0].length ; j++) {

                Block b = blocks[i][j];
                collideCounter += playerCollision(b,false);
                if(damaged) break;

            }
            if(damaged) break;

        }
        boolean collisions = false;
        if(collideCounter >= 1) collisions = true;

        for (MovingBlock b: mblocks) {
            int temp =playerCollision(b,collisions);
           // System.out.println(temp + " " + collideCounter);
            if( temp >= 1 && collideCounter >=1){
                b.currentSpeed = 0;
            }
            else b.currentSpeed = SPEED;

            if(damaged) break;


        }



        long time = System.nanoTime();
        if((time - start) / 1_000_000 > targetTime){
            animate();
            start = time;
        }







    }

    public int playerCollision(Block b, boolean collision){

        int collideCounter = 0;
        int ec = 1;

        if(b.id == 0 || b.id == 9
                || b.id == 10
                || b.id == 11
                || b.id == 12
                || b.id == 13) return 0;

        setBounds(GamePanel.WIDTH/2 + 15,GamePanel.HEIGHT/2 + 8,width - 28,height - 10);
        int rightPos = posX + 15 + width - 28 + (int)GameState.xOffset;
        int leftPos = posX + 15 + (int)GameState.xOffset;
        int downPos = posY + 8 + height - 10 + (int)GameState.yOffset ;
        int upPos = posY + 8 + (int)GameState.yOffset;

        //right
        if(Collisions.playerBlock2(rightPos + ec , upPos + ec,b)
                || Collisions.playerBlock2(rightPos + ec,  downPos - ec,b)){
            right = false;
            collideCounter++;
            GameState.xOffset -= SPEED;
            rightPos = posX + 15 + width - 28 + (int)GameState.xOffset;


            if(b instanceof MovingBlock){
                if(!collision) GameState.xOffset -= 1;
            }

            if(b.isDamageBlock()) damaged = true;
        }
        //left
        if(Collisions.playerBlock2(leftPos -ec, upPos + ec,b)
                || Collisions.playerBlock2(leftPos - ec , downPos - ec,b)){
            left = false;

            collideCounter++;
            GameState.xOffset += SPEED;
            leftPos = posX + 15 + (int)GameState.xOffset;
            if(b instanceof MovingBlock){
                if(!collision) GameState.xOffset += 1;
            }

            if(b.isDamageBlock()) damaged = true;
        }

        //up
        if(Collisions.playerBlock2(leftPos + ec , upPos - ec,b)
                || Collisions.playerBlock2(rightPos - ec, upPos - ec,b)){

            currentJumpSpeed = GRAVITY;
            int offset =(int)b.y + b.height - upPos + ec;
            upPos = posY + 8 + (int)GameState.yOffset;
            if(jumping)GameState.yOffset += offset;


            if(b instanceof MovingBlock){
                if(!collision) GameState.yOffset += 1;
            }

            if(b.isDamageBlock()) damaged = true;

        }
        //down
        if(Collisions.playerBlock2(rightPos - ec  , downPos + ec,b)
                || Collisions.playerBlock2(leftPos + ec , downPos + ec ,b)){
            if(falling) GameState.yOffset -= downPos + ec -  ((int)b.y );
            downPos = posY + 8 + height - 10 + (int)GameState.yOffset ;
            currentJumpSpeed = 0;
            jumping = false;
            falling = false;
            collideCounter++;


            if(b instanceof MovingBlock){
                //if(!collision) GameState.yOffset -= 1;
                ((MovingBlock) b).offset = true;
            }

            if(b.isDamageBlock()) damaged = true;

        }
        else{
            falling = true;
            if(b instanceof MovingBlock){
                if(!Collisions.playerBlock2(rightPos - ec , downPos + ec,b)
                        || !Collisions.playerBlock2(leftPos + ec, downPos + ec ,b)){
                    ((MovingBlock) b).offset = false;

                }

            }



        }

        return collideCounter;


    }

    public void drawPlayer(Graphics g) {
//        g.setColor(new Color(0,0,0));
//        g.drawRect(GamePanel.WIDTH/2 + 15,GamePanel.HEIGHT/2 + 8,width - 28,height - 10);

        //TODO: injured animation
        if(damaged) g.drawImage(Images.playerIdle,posX,posY,width,height,null);
        else if(right){ g.drawImage(Images.playerRunRight[aniStage],posX,posY,width,height,null); }
        else if(left){ g.drawImage(Images.playerRunLeft[aniStage],posX,posY,width,height,null); }
        else g.drawImage(Images.playerIdle,posX,posY,width,height,null);

    }


    public void keyPressed(int k) {



        if(k == KeyEvent.VK_D ){
            right = true;
            noleft = false;
        }
        if(k == KeyEvent.VK_A ){ left = true; }

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

    public void damage(){
        if(lives != 0) lives--;
        damaged = false;
    }
}
