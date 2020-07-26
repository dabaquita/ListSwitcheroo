package com.listswitcheroo.main.Bones;

// Denielle Abaquita
// 7/3/20

import com.listswitcheroo.main.Game;
import com.listswitcheroo.main.Objects.ListyList;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
    private Handler handler;
    private boolean isSpacePressed;

    public KeyInput(Handler handler)
    {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyChar();
        ListyList listyList = (ListyList) handler.get("ListyList");

        // Let's start reversing!
        if (key == KeyEvent.VK_SPACE && !listyList.isReversing())
        {
            new ReverseThread(listyList).start();
        }

        // Exit out
        if (key == KeyEvent.VK_ESCAPE)
            System.exit(1);
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
        listyList.setIsReversing(true);
        listyList.reverse();

        try
        {
            // Arbitrary sleep value found through trial and error
            // and optimized for the timing of the main thread in charge
            // of rendering
            this.sleep((1300 * (listyList.getSize())));
        } catch (Exception e)
        {
            e.printStackTrace();
        }


        listyList.setIsReversing(false);
        Game.togglePerformReverse();
    }
}

