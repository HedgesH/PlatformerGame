package com.hshedges.game.states;

import com.hshedges.game.UI.Lives;
import com.hshedges.game.UI.Timer;
import com.hshedges.game.entities.Player;
import com.hshedges.game.main.GamePanel;
import com.hshedges.game.main.Images;
import com.hshedges.game.objects.Block;
import com.hshedges.game.objects.Map;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LevelState extends GameState {

    private static final String[] levelPath = {"/maps/lvl1.map","/maps/lvl2.map","/maps/lvl3.map"};

    private Player player;
    private Block[][] blocks;
    private Map map;
    private Lives lives;
    private Timer timer;
    private int levelCount;

    //level complete
    public static final int FONT_SIZE = 72;
    public static final int TARGET_TIME = 5000;
    private long counter;


    public LevelState(GameStateManager gsm){
        super(gsm);
        this.levelCount = 0;


    }

    @Override
    public void init() {
        this.map = new Map(levelPath[levelCount],4,4);
        player = new Player(64,64,map);
        GameState.yOffset = -GamePanel.HEIGHT/2 - 128;
        GameState.xOffset = -GamePanel.WIDTH/2 + 128;
        lives = new Lives(GamePanel.WIDTH/32,GamePanel.HEIGHT/16,GamePanel.WIDTH/40,GamePanel.WIDTH/40,player.lives);
        timer = new Timer(GamePanel.WIDTH/64,GamePanel.HEIGHT/32);





    }

    @Override
    public void tick() {

        if(!player.levelComplete){
            player.tick(map.blocks,map.movingBlocks);
            map.tick();
            lives.tick(player.lives);
            timer.tick();
            //TODO: splash screen for death
            if(player.lives == 0) gsm.states.pop();
            counter = System.nanoTime();
        }
        else{
            long time = System.nanoTime();
            if((time - counter) / 1_000_000 > 5000){
                levelCount++;
                if (levelCount >= levelPath.length) gsm.states.pop();
                else init();
            }
        }

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(95, 176, 255));
        //g.fillRect(0,0,GamePanel.WIDTH,GamePanel.HEIGHT);
        Images.drawBackground(g);
        map.draw(g);
        player.drawPlayer(g);
        lives.drawLives(g);
        timer.drawTimer(g);
        if(player.levelComplete){
            g.setFont(new Font("Garamond",Font.BOLD,FONT_SIZE));
            g.drawString("Level Complete", GamePanel.WIDTH/8 - (FONT_SIZE), GamePanel.HEIGHT/2);
        }

    }

    @Override
    public void keyPressed(int k) {
        player.keyPressed(k);
        if(k == KeyEvent.VK_ESCAPE) gsm.states.pop();

    }

    @Override
    public void keyReleased(int k) {
        player.keyReleased(k);

    }

    public void levelComplete(){




    }
}
