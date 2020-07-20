package com.listswitcheroo.main;

// Denielle Abaquita
// 7/3/20

import com.listswitcheroo.main.Bones.Handler;
import com.listswitcheroo.main.Bones.KeyInput;
import com.listswitcheroo.main.Bones.Spawner;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable
{
    private static final long serialVersionUID = -5847085599864562483L;

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running;
    private static boolean performReverse;     // pauses the game

    private Handler handler;
    private Spawner spawner;
    private Menu menu;

    public enum STATE
    {
        Menu,
        Help,
        Game
    }

    public STATE gameState = STATE.Menu;

    Game()
    {
        // Window
        new Window(WIDTH, HEIGHT, "List Switcheroo", this);

        // Handler
        handler = new Handler();

        // Menu
        menu = new Menu(this, handler);

        // Listeners for input
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);
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
        // but referenced by RealTutsGML on YouTube
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
        if (gameState == STATE.Game)
        {
            if (performReverse)
                return;

            handler.tick();

            spawner.tick();
        }
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

        if (gameState == STATE.Menu || gameState == STATE.Help)
            menu.render(g);

        g.dispose();
        bs.show();
    }

    public boolean isPerformReverse()
    {
        return performReverse;
    }

    public static void togglePerformReverse()
    {
        performReverse = !performReverse;
    }

    public void initSpawner()
    {
        spawner = new Spawner(handler);
    }

    public static void main(String[] args)
    {
        new Game();
    }
}
