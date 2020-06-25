package com.listswitcheroo.main.Objects;

// Denielle Abaquita
// 6/8/20
// The actual linked list that will
// move around the screen and such

import java.awt.*;

public class ListyList extends GameObject
{
    private Node head, tail;
    private int size;

    public ListyList(float x, float y, ID id)
    {
        super(x, y, id);

        // Initialize the head/tail node
        head = tail = new Node(x, y, ID.HappyList);
        size = 1;
    }

    public void increaseSize()
    {
        float x = tail.getX(), y = tail.getY(), velX = tail.getVelX(), velY = tail.getVelY();

        // Direction: downward-right
        if (velX > 0 && velY > 0)
        {
            x -= 20;
            y -= 20;
        }
        // Direction: upward-left
        else if (velX < 0 && velY < 0)
        {
            x += 20;
            y += 20;
        }
        // Direction: upward-right
        else if (velX > 0 && velY < 0)
        {
            x -= 20;
            y += 20;
        }
        // Direction: downward-left
        else if (velX < 0 && velY > 0)
        {
            x += 20;
            y -= 20;
        }
        // MAYBE - handle edge cases here?
        else
        {
            System.out.println("We have reached an edge case! Please fix it up in ListyList.java");
        }

        tail = tail.tailInsert(new Node(x, y, velX, velY, ID.HappyList));
        size++;
    }

    // Changes the individual ids of the linked list
    // thus changing the colors they render
    private void changeID(ID id)
    {
        System.out.println("Changing the id to: " + id.toString());
        Node temp = head;

        while (temp != null)
        {
            temp.setId(id);
            temp = temp.next;
        }
    }

    @Override
    public void tick()
    {
        Node temp = head;

        if (size == 3)
            changeID(ID.MildList);
        else if (size >= 4)
            changeID(ID.LonelyList);

        while (temp != null)
        {
            temp.tick();
            temp = temp.next;
        }
    }

    @Override
    public void render(Graphics g)
    {
        Node temp = head;
        // TODO: Render the linked list...
        // If less than 3 nodes, nodes are green
        // If it reaches 3 nodes, nodes turn yellow
        // If it reaches 4 ndoes, nodes turn red

        while (temp != null)
        {
            temp.render(g);
            temp = temp.next;
        }
    }
}
