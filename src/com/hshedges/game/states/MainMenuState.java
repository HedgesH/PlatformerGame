package com.hshedges.game.states;

import com.hshedges.game.main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MainMenuState extends GameState {

    private static final String[] options = {"Start","Help","Exit"};
    private int currentSelection;

    public static final int FONT_SIZE = 72;
    public static final int OFFSET = 0;
    public static final int SEPARATION = GamePanel.HEIGHT /4;

    public MainMenuState(GameStateManager gsm){
        super(gsm);
        currentSelection = 0;

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

        g.setColor(new Color(221, 166, 187));
        g.fillRect(0,0,GamePanel.WIDTH,GamePanel.HEIGHT);


        for (String s: options) {

            if(s.equals(options[currentSelection])){
                g.setColor(Color.RED);
            }
            else{
                g.setColor(Color.BLACK);
            }


            g.setFont(new Font("Garamond",Font.BOLD,FONT_SIZE));
            g.drawString(s, GamePanel.WIDTH/2 - (FONT_SIZE), (SEPARATION * counter) + OFFSET);
            counter++;
        }

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
