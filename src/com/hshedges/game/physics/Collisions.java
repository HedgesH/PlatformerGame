package com.hshedges.game.physics;

import com.hshedges.game.objects.Block;

import java.awt.*;

public class Collisions {

    public static boolean playerBlock(Point p, Block b){
        return b.contains(p);
    }

    public static boolean playerBlock2(int x,int y, Block b){
        Point p = new Point(x,y);
        return b.contains(p);
    }
}
