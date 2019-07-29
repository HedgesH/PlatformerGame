package com.hshedges.game.objects;

import com.hshedges.game.states.GameState;

public class MovingBlock extends Block {

    private double startX,startY,finalX,finalY;
    private static final double SPEED = 1;
    public double currentSpeed;
    private boolean ret;
    public boolean offset;

    public MovingBlock(int x, int y, int fx, int fy, int id){
        super(x * WIDTH,y * HEIGHT,id);
        this.startX = x * WIDTH;
        this.startY = y * HEIGHT;
        this.finalX = fx * WIDTH;
        this.finalY = fy * HEIGHT;
        this.ret = false;
        this.currentSpeed = SPEED;
        this.offset = false;

        System.out.println(startX + " " + finalX);




    }


    public void tick(){
        double dy = finalY - startY;
        double dx = finalX - startX;
        double angle = Math.atan(dy/dx);



        if(ret){
            y -= currentSpeed * Math.sin(angle);
            x -= currentSpeed * Math.cos(angle);
        }
        else{
            y += currentSpeed * Math.sin(angle);
            x += currentSpeed * Math.cos(angle);
        }


        double xx1 = finalX - x;
        double yy1 = finalY - y;
        double xx2 = startX - x;
        double yy2 = startY - y;
        if((xx1*xx1 + yy1*yy1 < 10) || ((xx2*xx2 + yy2*yy2 < 10) && ret) ){
            ret = !ret;
        }

        setBounds((int)x,(int)y,WIDTH,HEIGHT);

        if (offset){
            if(ret) GameState.xOffset -= currentSpeed * Math.cos(angle);
            else  GameState.xOffset += currentSpeed * Math.cos(angle);
            //offset = false;
        }

    }



}
