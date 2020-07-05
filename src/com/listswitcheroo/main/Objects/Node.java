package com.listswitcheroo.main.Objects;

// Denielle Abaquita
// 7/3/20
// Each "body part" of the linked list

import com.listswitcheroo.main.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class Node extends GameObject
{
    protected Node next;
    protected float velYPrev, velXPrev;

    public Node(float x, float y, ID id)
    {
        super(x, y, id);

        velX = 5;
        velY = 5;
    }

    public Node (float x, float y, float velX, float velY, ID id)
    {
        super(x, y, id);

        this.velX = velX;
        this.velY = velY;
    }

    // Returns the new tail of the linked list
    public Node tailInsert(Node next)
    {
        Node temp = this;

        if (next == null)
            return this;

        while (temp.next != null)
        {
            temp = temp.next;
        }

        temp.next = next;
        return temp.next;
    }

    // Reverses the movement of the node
    public void reverse(int pointerDelay)
    {
        // We need to delay the reversal of the
        // pointer to show a steady reversal overall
        if (id.toString().contains("Pointer"))
        {
            Timer timer = new Timer((750 * pointerDelay) / 2, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    velX *= -1;
                    velY *= -1;
                }
            });
            timer.setRepeats(false);
            timer.start();
            return;
        }

        velX *= -1;
        velY *= -1;
    }

    @Override
    public void tick()
    {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 64)
            velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 40)
            velX *= -1;
    }

    @Override
    public void render(Graphics g)
    {
        // If we have head node, then it
        // must have the smiley face texture
        if (getId().equals(ID.HappyList))
        {
            g.setColor(Color.GREEN);
            g.fillOval((int) x, (int) y, 48, 48);
        }
        else if (getId().equals(ID.MildList))
        {
            g.setColor(Color.ORANGE);
            g.fillOval((int) x, (int) y, 48, 48);
        }
        else if (getId().equals(ID.LonelyList))
        {
            g.setColor(Color.RED);
            g.fillOval((int) x, (int) y, 48, 48);
        }
    }
}
