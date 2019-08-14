package com.hshedges.game.objects;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Map {

    private String path;
    public int height,width,movingbs;

    public Block[][] blocks;
    public MovingBlock[] movingBlocks;

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
            movingbs = Integer.parseInt(br.readLine());

            blocks = new Block[height][width];
            movingBlocks = new MovingBlock[movingbs];



            for (int i = 0; i < height ; i++) {

                String line = br.readLine();
                String[] split = line.split(" ");
                for (int j = 0; j < width; j++) {
                    blocks[i][j] = new Block(j * Block.WIDTH, i * Block.HEIGHT,Integer.parseInt(split[j]));

                }

                
            }

            for (int i = 0; i < movingbs; i++) {
                String line = br.readLine();
                String[] split = line.split(" ");
                MovingBlock mb = new MovingBlock(Integer.parseInt(split[0]),Integer.parseInt(split[1]),
                        Integer.parseInt(split[2]),Integer.parseInt(split[3]),Integer.parseInt(split[4]));
                movingBlocks[i] = mb;


            }

        }
        catch (Exception e){
            e.printStackTrace();
        }



    }

    public void tick(){
        for (MovingBlock mb:movingBlocks) {
            mb.tick();

        }

    }

    public void draw(Graphics g){
        for (int i = 0; i < blocks.length ; i++) {
            for (int j = 0; j < blocks[0].length; j++) {

                blocks[i][j].drawBlock(g);

            }
            
        }
        for (MovingBlock mb:movingBlocks) {
            mb.drawBlock(g);
        }

    }
}
