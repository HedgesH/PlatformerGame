package com.hshedges.game.states;

import com.hshedges.game.main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class MainMenuState extends GameState {

    private static final String[] options = {"Start","Help","Exit"};
    private int currentSelection;

    //menu graphic
    private static final int SIZE = 250;
    private int counter;

    public static final int FONT_SIZE = 72;
    public static final int OFFSET = 0;
    public static final int SEPARATION = GamePanel.HEIGHT /4;

    public MainMenuState(GameStateManager gsm){
        super(gsm);
        currentSelection = 0;
        this.counter = 0;


    }

    @Override
    public void init() {

    }

    @Override
    public void tick() {


    }

    @Override
    public void draw(Graphics g) {

        int counter = 1;

        g.setColor(new Color(255, 251, 255));
        g.fillRect(0,0,GamePanel.WIDTH,GamePanel.HEIGHT);

        g.setColor(new Color(0,0,0));
        for (int i = 0; i < GamePanel.WIDTH ; i+= GamePanel.WIDTH/SIZE) {
            g.drawLine(i,0,i,GamePanel.HEIGHT);

        }
        for (int i = 0; i < GamePanel.HEIGHT ; i+= GamePanel.HEIGHT/SIZE) {
            g.drawLine(0,i,GamePanel.WIDTH,i);

        }

        drawGrid(g);


        for (String s: options) {

            if(s.equals(options[currentSelection])){
                g.setColor(Color.RED);
            }
            else{
                g.setColor(Color.BLACK);
            }


            g.setFont(new Font("Garamond",Font.BOLD,FONT_SIZE));
            g.drawString(s, (3*GamePanel.WIDTH/4) - (FONT_SIZE), (SEPARATION * counter) + OFFSET);
            counter++;
        }

    }

    public void drawGrid(Graphics g){
        int dx = GamePanel.WIDTH / SIZE;
        int dy = GamePanel.HEIGHT / SIZE;
        for (int i = 0; i < GamePanel.WIDTH ; i+= GamePanel.WIDTH/SIZE) {
            for (int j = 0; j < GamePanel.HEIGHT ; j+= GamePanel.HEIGHT/SIZE) {

                g.setColor(new Color((i + (j + counter) % 100) % 255,255,((i + j ) + (counter)) % 255));

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

        if (k == KeyEvent.VK_DOWN) currentSelection = (currentSelection + 1) % 3;
        else if (k == KeyEvent.VK_UP){
            currentSelection -= 1;
            if (currentSelection == -1) currentSelection = 2;
        }
        else if (k == KeyEvent.VK_ENTER) enterPressed();
    }

    public void enterPressed(){


        switch (currentSelection){

            //play
            case 0: {gsm.states.push(new LevelState(gsm)); break;}

            //help
            case 1: {gsm.states.push(new HelpState(gsm)); break;}

            //exit
            case 2: {System.exit(0); break;}

        }

    }
}
