package com.listswitcheroo.main.Bones;

// Denielle Abaquita
// 6/8/20

import com.listswitcheroo.main.Objects.ListyList;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
    private Handler handler;

    public KeyInput(Handler handler)
    {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyChar();

        // Reverse the linked list
        if (key == KeyEvent.VK_SPACE)
        {
            ListyList list = (ListyList) handler.get("ListyList");
            list.reverse();
        }

        // Exit out
        if (key == KeyEvent.VK_ESCAPE)
            System.exit(1);
    }
}
