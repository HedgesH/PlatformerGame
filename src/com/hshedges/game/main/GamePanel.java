package com.hshedges.game.main;

import com.hshedges.game.states.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    private Thread thread;
    private boolean isRunning = false;

    private static final int FPS = 30;
    public static final int targetTime = 1000/FPS;

    private GameStateManager gsm;

    public GamePanel(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));

        start();

    }

    private void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
        new Images();
        addKeyListener(this);

        //TODO; what does this do?
        setFocusable(true);

    }

    @Override
    public void run() {
        long start,elapsed,wait;
        gsm = new GameStateManager();

        while (isRunning){

            start = System.currentTimeMillis();

            tick();
            repaint();

            elapsed = System.currentTimeMillis() - start;
            wait = (targetTime - elapsed) ;

            if(wait < 0) wait = targetTime;

            try{ Thread.sleep(wait); }
            catch(Exception e){ e.printStackTrace(); }

        }

    }

    public void tick(){gsm.tick();}

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        gsm.draw(g);
       // g.drawRect(10,10,10,10);
    }



    @Override
    public void keyPressed(KeyEvent e) { gsm.keyPressed(e.getKeyCode()); }

    @Override
    public void keyReleased(KeyEvent e) { gsm.keyReleased(e.getKeyCode()); }

    @Override
    public void keyTyped(KeyEvent e) {  }


}
