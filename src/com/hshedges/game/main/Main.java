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

    //TODO: design UI
    //TODO: stops running when lands
    //TODO: sound
    //TODO: put on android app store

}
