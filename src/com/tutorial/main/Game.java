package com.tutorial.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import com.tutorial.main.ID;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;
    private Handler handler;

    public Game(){
        handler = new Handler();
        handler.addObject(new Player(100, 100, ID.Player));

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
                tick(c);
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

    private void tick(Controller c){

        handler.tick(c);
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
}
