package com.hshedges.game.main;


import javax.swing.*;
import java.awt.*;

public class Main  {

    public static void main(String[] args) {
        JFrame frame = new JFrame("PlatformerGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //TODO: what does this do?
        frame.setLayout(new BorderLayout());

        frame.add(new GamePanel(), BorderLayout.CENTER);

        //TODO: what does this do?
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

    }

    //TODO: enemy block
    //TODO: losing health
    //TODO: out of bounds
    //TODO: add 0 to front of digit
    //TODO: 3 levels
    //TODO: level complete
    //TODO: Goal block
    //TODO: design UI
    //TODO: more backgrounds
    //TODO: stops running when lands
    //TODO: sound
    //TODO: put on android app store

}
