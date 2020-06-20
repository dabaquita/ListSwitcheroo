package com.listswitcheroo.main.Bones;

// Denielle Abaquita
// 6/8/20

import com.listswitcheroo.main.Objects.ID;
import com.listswitcheroo.main.Objects.ListyList;

import java.util.Random;

public class Spawner
{
    private Handler handler;
    private Random rand;

    public Spawner(Handler handler)
    {
        this.handler = handler;
        rand = new Random();

        // Initial spawn of the first linked list
        handler.add(new ListyList(150, 150, ID.HappyList));
    }

    public void tick()
    {

    }
}
