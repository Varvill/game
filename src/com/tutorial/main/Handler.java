package com.tutorial.main;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Handler {
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick(HashMap keys){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.tick(keys);
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }
}
