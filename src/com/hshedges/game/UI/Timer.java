package com.hshedges.game.UI;

import com.hshedges.game.main.GamePanel;

import java.awt.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Timer {

    final static int FONT_SIZE = 30;
    double tickTime;
    double time;
    int x,y;


    Duration timer;

    public Timer(int x, int y){
        this.tickTime = GamePanel.targetTime;
        this.tickTime = this.tickTime/1000;
        this.time = 0;
        this.x = x;
        this.y = y;




    }

    public int[] getTime()
    {
        double milliTime = time;
        int[] out = new int[3];
        out[0] = (int)(milliTime / 3600   );
        out[1] = (int)(milliTime / 60    ) % 60;
        out[2] = (int)(milliTime       ) % 60;


        return out;
    }

    public void tick(){
        time += tickTime;
    }

    public void drawTimer(Graphics g){
        int[] time = getTime();

        String timeString = time[0]  + ":"  + time[1] + ":" + time[2] ;
        g.setColor(Color.WHITE);
        g.setFont(new Font("Garamond",Font.BOLD,FONT_SIZE));
        g.drawString(timeString, x + (FONT_SIZE/3),y + (FONT_SIZE/3)   );
    }
}
