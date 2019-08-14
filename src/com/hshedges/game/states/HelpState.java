package com.hshedges.game.states;

import com.hshedges.game.main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class HelpState extends GameState {

    private int counter;
    private static final int SIZE = 250;

    public HelpState(GameStateManager gsm){
        super(gsm);
        counter = 0;
    }

    @Override
    public void init() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void draw(Graphics g) {

        drawGrid(g);
        g.setColor(Color.BLACK);

        g.setFont(new Font("Garamond",Font.BOLD,MainMenuState.FONT_SIZE));
        g.drawString("Use WASD to move", (MainMenuState.FONT_SIZE*2),GamePanel.HEIGHT/4 );
        g.drawString("press ESC to go back", (MainMenuState.FONT_SIZE*2),3* GamePanel.HEIGHT/4);

    }

    public void drawGrid(Graphics g){
        int dx = GamePanel.WIDTH / SIZE;
        int dy = GamePanel.HEIGHT / SIZE;
        for (int i = 0; i < GamePanel.WIDTH ; i+= GamePanel.WIDTH/SIZE) {
            for (int j = 0; j < GamePanel.HEIGHT ; j+= GamePanel.HEIGHT/SIZE) {

                g.setColor(new Color((j + (i + counter) % 100) % 255,((i + j ) + (counter)) % 255,255));

                g.fillRect(i,j,dx,dy);



            }
        }
        if(counter == 1000) counter = 0;
        else counter+= 10;

    }

    @Override
    public void keyPressed(int k) {


    }

    @Override
    public void keyReleased(int k) {
        if(k == KeyEvent.VK_ESCAPE) gsm.states.pop();

    }
}
