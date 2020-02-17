package com.tutorial.main;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends GameObject{



    public Player(int x, int y, ID id){
        super(x, y, id);

    }

    public void tick(Controller c){
        if(c.keys.get(KeyEvent.VK_W) == true){
            y +=1;
        }
        if(c.keys.get(KeyEvent.VK_S) == true){
            y -=1;
        }
        if(c.keys.get(KeyEvent.VK_A) == true){
            x -=1;
        }
        if(c.keys.get(KeyEvent.VK_D) == true){
            x +=1;
        }

    }

    public void render(Graphics g){
        g.setColor(Color.white);
        g.fillRect(x,y,32,32);
    }

}