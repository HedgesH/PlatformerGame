package com.hshedges.game.objects;

import com.hshedges.game.states.GameState;

import java.awt.*;

public class Block extends Rectangle {
    public int x,y;
    public int id;
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;

    public Block(int x, int y, int id){
        setBounds(x,y,WIDTH,HEIGHT);
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public void drawBlock(Graphics g){
        int xOff = (int)GameState.xOffset;
        int yOff = (int)GameState.yOffset;
        if(id != 0) g.fillRect(x - xOff ,y - yOff ,WIDTH,HEIGHT);
    }
}
