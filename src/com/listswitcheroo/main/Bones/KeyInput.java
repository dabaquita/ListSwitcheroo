package com.listswitcheroo.main.Bones;

// Denielle Abaquita
// 7/3/20

import com.listswitcheroo.main.Game;
import com.listswitcheroo.main.Objects.ListyList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private Game game;

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyChar();
        ListyList listyList = (ListyList) handler.get("ListyList");

        // Pauses the animation
        // and then it reverses the linked list
        // one by one.
        if (key == KeyEvent.VK_SPACE)
        {
            new ReverseThread(listyList).start();
        }

        // Exit out
        if (key == KeyEvent.VK_ESCAPE)
            System.exit(1);
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyChar();

        if (key == KeyEvent.VK_SPACE)
        {
            System.out.println("Space key was released");
        }
    }
}

class ReverseThread extends Thread
{
    private ListyList listyList;

    ReverseThread(ListyList listyList)
    {
        this.listyList = listyList;
    }

    @Override
    public void run()
    {
        Game.togglePerformReverse();
        listyList.reverse();

        try
        {
            // Arbitrary sleep value found through trial and error
            // and optimized for the timing of the main thread in charge
            // of rendering
            this.sleep((int) (1000 * (listyList.getSize() / 1.15)));
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        Game.togglePerformReverse();
    }
}

