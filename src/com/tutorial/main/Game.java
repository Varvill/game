package com.tutorial.main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.HashMap;

import com.tutorial.main.ID;

public class Game extends Canvas implements Runnable, KeyListener{

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;
    private Handler handler;
    HashMap<Integer, Boolean> keys;

    public Game(){
        handler = new Handler();
        handler.addObject(new Player(100, 100, ID.Player));


        keys.put(KeyEvent.VK_W, false);
        keys.put(KeyEvent.VK_A, false);
        keys.put(KeyEvent.VK_S, false);
        keys.put(KeyEvent.VK_D, false);
        keys.put(KeyEvent.VK_SPACE, false);

        new Window(WIDTH, HEIGHT, "GameTime", this);


    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
        run();
    }

    public synchronized void stop(){
        try{
            thread.join();
            running= false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        Controller c = new Controller();
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1)
            {
                tick(keys);
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: "+ frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(HashMap keys){

        handler.tick(keys);
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.green);
        g.fillRect(0,0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();

    }

    public static void main(String args[]){
        new Game();
    };

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
