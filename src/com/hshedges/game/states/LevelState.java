package com.hshedges.game.states;

import com.hshedges.game.entities.Player;
import com.hshedges.game.main.GamePanel;
import com.hshedges.game.objects.Block;
import com.hshedges.game.objects.Map;

import java.awt.*;

public class LevelState extends GameState {

    private Player player;
    private Block[][] blocks;
    private Map map;

    public LevelState(GameStateManager gsm){
        super(gsm);


    }

    @Override
    public void init() {
        player = new Player(30,30);
        this.map = new Map("/lvl1.map",4,4);
        GameState.yOffset = -GamePanel.HEIGHT/2 - 60;
        GameState.xOffset = -GamePanel.WIDTH/2 + 60;





    }

    @Override
    public void tick() {
        player.tick(map.blocks);

    }

    @Override
    public void draw(Graphics g) {
        player.drawPlayer(g);
        map.draw(g);

    }

    @Override
    public void keyPressed(int k) {
        player.keyPressed(k);

    }

    @Override
    public void keyReleased(int k) {
        player.keyReleased(k);

    }
}
