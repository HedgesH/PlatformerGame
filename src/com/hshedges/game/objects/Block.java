package com.hshedges.game.objects;

import com.hshedges.game.main.Images;
import com.hshedges.game.states.GameState;

import java.awt.*;
import java.util.Random;

public class Block extends Rectangle {
    public double x,y;
    public int id;
    public static final int WIDTH = 128;
    public static final int HEIGHT = 128;
    public static final int DAMAGE_BLOCK_ID = 14;

    //rand color
    public Color currentColor;
    public boolean increaseHue;

    public Block(int x, int y, int id){
        setBounds(x,y,WIDTH,HEIGHT);
        this.x = x;
        this.y = y;
        this.id = id;
        this.currentColor = new Color(120, 0,0);
        this.increaseHue = true;
    }

    public void drawBlock(Graphics g){
        int xOff = (int)GameState.xOffset;
        int yOff = (int)GameState.yOffset;
        if(id == 14){
            currentColor = randColor(currentColor);
            g.setColor(currentColor);
            g.fillRect((int)x - xOff ,(int)y - yOff ,WIDTH,HEIGHT);
        }
        else if(id != 0 ){
            g.drawImage(Images.blocks[id-1],(int)x - xOff ,(int)y - yOff ,WIDTH,HEIGHT,null);
        }
    }

    public boolean isDamageBlock(){
        return (id == DAMAGE_BLOCK_ID);
    }

    public Color randColor (Color color){
        Random rand = new Random();
        int f = 2;
        int colorToChange = color.getRed();

        if(colorToChange - f < 100 || colorToChange + f > 200){ increaseHue = !increaseHue; }
        if(increaseHue) colorToChange += f;
        else colorToChange -= f;


        return new Color(colorToChange,color.getGreen(),color.getBlue());

    }
}
