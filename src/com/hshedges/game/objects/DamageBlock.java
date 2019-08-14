package com.hshedges.game.objects;

public class DamageBlock extends Block {

    public DamageBlock(int x, int y, int id){
        super(x * WIDTH, y * HEIGHT, id);
    }

    @Override
    public boolean isDamageBlock(){
        return true;
    }

}
