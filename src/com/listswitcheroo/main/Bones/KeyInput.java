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

public class KeyInput extends KeyAdapter
{
    private Handler handler;
    private Game game;
    private boolean spacePressed;

    public KeyInput(Handler handler, Game game)
    {
        this.handler = handler;
        this.game = game;
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyChar();

        // Pauses the animation
        // and then it reverses the linked list
        // one by one.
        if (key == KeyEvent.VK_SPACE)
        {
            spacePressed = true;
            game.setPerformReverse(true);

            ListyList list = (ListyList) handler.get("ListyList");
            list.reverse();
        }

        // Exit out
        if (key == KeyEvent.VK_ESCAPE)
            System.exit(1);
    }
}
