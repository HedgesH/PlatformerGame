package com.hshedges.game.UI;

import com.hshedges.game.main.Images;

import java.awt.*;

public class Lives {

    int lives,x,y,width,height;
    final static double SEPERATIONCONST = 1.5;
    double seperation;


    public Lives(int x, int y, int width, int height, int lives){
        this.lives = lives;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.seperation = SEPERATIONCONST * width;

    }

    public void tick(int lives){ this.lives = lives; }

    public void drawLives(Graphics g){

        for (int i = 0; i < lives; i++) {
            g.drawImage(Images.life,x + (int)(i*seperation), y , width, height,null);
        }


    }

    public void addLives(){lives++;}

    public void subLives(){lives--;}


}
