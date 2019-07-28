package com.hshedges.game.objects;

import com.hshedges.game.main.Images;
import com.hshedges.game.states.GameState;

import java.awt.*;

public class Block extends Rectangle {
    public double x,y;
    public int id;
    public static final int WIDTH = 128;
    public static final int HEIGHT = 128;

    public Block(int x, int y, int id){
        setBounds(x,y,WIDTH,HEIGHT);
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public void drawBlock(Graphics g){
        int xOff = (int)GameState.xOffset;
        int yOff = (int)GameState.yOffset;
        if(id != 0){
            g.drawImage(Images.blocks[id-1],(int)x - xOff ,(int)y - yOff ,WIDTH,HEIGHT,null);
        }
    }
}
