package com.listswitcheroo.main.Objects;

// Denielle Abaquita
// 7/3/20

// The actual linked list that will
// move around the screen and such

import com.listswitcheroo.main.Game;
import java.awt.*;

public class ListyList extends GameObject
{
    private Node head, tail;
    private boolean isReversing;

    // Note: size and maxSize refer to the number of nodes
    // NOT the total number of nodes and pointers
    private int size, maxSize;

    public ListyList(float x, float y, ID id)
    {
        super(x, y, id);

        // Initialize the head/tail node
        head = tail = new Node(x, y, ID.HappyList);
        size = 1;
        maxSize = 5;
    }

    public int getSize()
    {
        return size;
    }

    public int getMaxSize()
    {
        return maxSize;
    }

    public void increaseSize()
    {
        if (size == maxSize)
        {
            System.err.println("ListyList is already at max size. We cannot increase anymore");
            return;
        }

        float x = tail.getX(), y = tail.getY(), velX = tail.getVelX(), velY = tail.getVelY();
        float offset = 40;

        // Direction: downward-right
        if (velX > 0 && velY > 0)
        {
            x -= offset;
            y -= offset;
        }
        // Direction: upward-left
        else if (velX < 0 && velY < 0)
        {
            x += offset;
            y += offset;
        }
        // Direction: upward-right
        else if (velX > 0 && velY < 0)
        {
            x -= offset;
            y += offset;
        }
        // Direction: downward-left
        else if (velX < 0 && velY > 0)
        {
            x += offset;
            y -= offset;
        }

        // Handling edge cases when the node is spawned
        // outside of the game window
        if ((x > Game.WIDTH - 40) || x < 0 || (y > Game.HEIGHT - 64) || y < 0)
        {
            System.err.println("We have reached an edge case! Please fix it up in ListyList.java");
            return;
        }

        if (tail.getId().toString().contains("List"))
        {
            tail = tail.tailInsert(new Pointer(x, y, velX, velY, ID.Pointer));
        }
        else
        {
            tail = tail.tailInsert(new Node(x, y, velX, velY, ID.HappyList));
            size++;
        }
    }

    // Reverse the linked list!!
    public void reverse()
    {
        Node temp = head, prev = null, next;
        int animationDelay = 1;

        // Reverses the linked list
        while (temp != null)
        {
            if (prev != null)
            {
                prev.idChange(ID.HappyList, (int) (animationDelay * 1.1));
            }

            // Reverses the animation
            // with a delay on the animation
            temp.reverse(animationDelay);
            temp.idChange(ID.MildList, (int) (animationDelay * 0.9));

            next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
            animationDelay++;
        }

        prev.idChange(ID.HappyList, (int) (animationDelay));

        // Finally switch the pointers
        temp = tail;
        tail = head;
        head = temp;
    }

    public void isReversing(boolean isReversing)
    {
        this.isReversing = isReversing;
    }

    @Override
    public void tick()
    {
        Node temp = head;

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

        while (temp != null)
        {
            // If the node is a pointer, we can
            // just render regularly and skip to next
            if (temp.getId().equals(ID.Pointer))
            {
                temp.render(g);
                temp = temp.next;
                continue;
            }

            // Set up the colors for the node
            // depending on the size
            if (!isReversing)
            {
                if (size == 3)
                    temp.setId(ID.MildList);
                else if (size >= 4)
                    temp.setId(ID.LonelyList);
            }

            temp.render(g);
            temp = temp.next;
        }
    }
}
