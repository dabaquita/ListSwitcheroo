package com.listswitcheroo.main.Objects;

// Denielle Abaquita
// 6/8/20
// Each "body part" of the linked list

import com.listswitcheroo.main.Game;

import java.awt.*;

public class Node extends GameObject
{
    private Node next;

    public Node(float x, float y, ID id)
    {
        super(x, y, id);

        velX = 5;
        velY = 5;
    }

    public void insert(Node next)
    {
        Node temp = this;

        if (next == null)
            return;

        while (temp.next != null)
        {
            temp = temp.next;
        }

        temp.next = next;
    }

    @Override
    public void tick()
    {
        Node temp = this;

        // TODO: Implement a way to tick the linked list

        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 32)
            velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 16)
            velX *= -1;
    }

    @Override
    public void render(Graphics g)
    {
        // If we have head node, then it
        // must have the smiley face texture
        if (getId().equals(ID.HeadNode))
        {
            g.setColor(Color.RED);
            g.fillOval((int) x, (int) y, 32, 32);
            return;
        }

        g.setColor(Color.GREEN);
        g.fillOval((int) x, (int) y, 16, 16);
    }
}
