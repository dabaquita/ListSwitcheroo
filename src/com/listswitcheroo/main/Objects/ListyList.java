package com.listswitcheroo.main.Objects;

// Denielle Abaquita
// 6/8/20
// The actual linked list that will
// move around the screen and such

import java.awt.*;

public class ListyList extends GameObject
{
    private Node head;

    public ListyList(float x, float y, ID id)
    {
        super(x, y, id);

        // Initialize the head node
        head = new Node(x, y, ID.HeadNode);
    }

    @Override
    public void tick()
    {

    }

    @Override
    public void render(Graphics g)
    {

    }
}
