package com.tutorial.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;


public class Controller implements KeyListener {
    HashMap<Integer, Boolean> keys;
    public Controller(){
        keys = new HashMap<>();
        keys.put(KeyEvent.VK_W, false);
        keys.put(KeyEvent.VK_A, false);
        keys.put(KeyEvent.VK_S, false);
        keys.put(KeyEvent.VK_D, false);
        keys.put(KeyEvent.VK_SPACE, false);



    }


    public void keyTyped(KeyEvent e){

    }

    public void keyPressed(KeyEvent e){
        keys.replace(e.getKeyCode(), true);
    }

    public void keyReleased(KeyEvent e){
        keys.replace(e.getKeyCode(), false);
    }
    public void releaseAll(){
        for(Integer key : keys.keySet()){
            keys.replace(key, false);
        }
    }


}
