package com.hshedges.game.objects;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Map {

    private String path;
    private int height,width;

    public Block[][] blocks;

    public Map(String filePath, int height, int width){

        path = filePath;
        loadMap();


    }

    public void loadMap(){

        InputStream is = this.getClass().getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        try{
            width = Integer.parseInt(br.readLine());
            height = Integer.parseInt(br.readLine());

            blocks = new Block[height][width];



            for (int i = 0; i < height ; i++) {

                String line = br.readLine();
                String[] split = line.split(" ");
                for (int j = 0; j < width; j++) {
                    blocks[i][j] = new Block(j * Block.WIDTH, i * Block.HEIGHT,Integer.parseInt(split[j]));

                }

                
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }



    }

    public void draw(Graphics g){
        for (int i = 0; i < blocks.length ; i++) {
            for (int j = 0; j < blocks[0].length; j++) {

                blocks[i][j].drawBlock(g);

            }
            
        }
    }
}
