package com.listswitcheroo.main;

// Denielle Abaquita
// 6/8/20

import com.listswitcheroo.main.Bones.Handler;
import com.listswitcheroo.main.Bones.Spawner;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable
{
    private static final long serialVersionUID = -5847085599864562483L;

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running;

    private Handler handler;
    private Spawner spawner;

    public enum STATE
    {
        Menu,
        Game,
        End
    }

    public STATE gameState = STATE.Game;

    Game()
    {
        handler = new Handler();

        // TODO: Maybe add a menu?

        // TODO: Add listeners (make Handler and KeyInput classes)

        // Window
        new Window(WIDTH, HEIGHT, "List Switcheroo", this);

        // Spawner
        spawner = new Spawner(handler);
    }

    public synchronized void start()
    {
        thread = new Thread(this);
        running = true;
        thread.start();
    }

    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run()
    {
        // Automatically focuses on window
        this.requestFocus();

        // Famous Game Loop - credited to someone unknown
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1)
            {
                tick();
                delta--;
            }

            if (running)
                render();

            frames++;

            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }

        stop();
    }

    private void tick()
    {
        handler.tick();
    }

    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();

        // Stick with no more than 3 buffers
        if (bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args)
    {
        new Game();
    }
}
