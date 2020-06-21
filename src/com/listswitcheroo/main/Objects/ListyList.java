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
        tail = tail.tailInsert(new Node(tail.getX() - 20, tail.getY() - 20, tail.getVelX(), tail.getVelY(), ID.HappyList));
        size++;
    }

    @Override
    public void tick()
    {
        Node temp = head;
        // TODO: Tick the linked list...
        // Need to have it so that if it reaches 5 nodes,
        // the linked list splits into 3 different linked lists
        // The head node, middle node, and tail node each becoming their own

        // Now let's set the ID's so that we can render
        // a new graphic for when the list gets lonelier
        if (size >= 3)
            this.setId(ID.MildList);
        else if (size >= 4)
            this.setId(ID.LonelyList);

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
