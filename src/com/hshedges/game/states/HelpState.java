package com.hshedges.game.states;

import com.hshedges.game.main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class HelpState extends GameState {

    public HelpState(GameStateManager gsm){
        super(gsm);
        System.out.println("here");
    }

    @Override
    public void init() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void draw(Graphics g) {

        g.setColor(new Color(221, 166, 187));
        g.fillRect(0,0,GamePanel.WIDTH,GamePanel.HEIGHT);
        g.setColor(Color.BLACK);

        g.setFont(new Font("Garamond",Font.BOLD,MainMenuState.FONT_SIZE));
        g.drawString("Use WASD to move", (MainMenuState.FONT_SIZE*2),GamePanel.HEIGHT/4 );
        g.drawString("press ESC to go back", (MainMenuState.FONT_SIZE*2),3* GamePanel.HEIGHT/4);

    }

    @Override
    public void keyPressed(int k) {


    }

    @Override
    public void keyReleased(int k) {
        if(k == KeyEvent.VK_ESCAPE) gsm.states.pop();

    }
}
