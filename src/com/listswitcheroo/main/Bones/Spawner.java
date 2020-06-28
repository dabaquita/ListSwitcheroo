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
    private int timeKeep;

    public Spawner(Handler handler)
    {
        this.handler = handler;
        rand = new Random();
        timeKeep = 0;

        // Initial spawn of the first linked list
        handler.add(new ListyList(150, 150, ID.HappyList));
    }

    public void tick()
    {
        timeKeep++;
        System.out.println("Timekeep: " + timeKeep);

        if (timeKeep >= 100)
        {
            ListyList temp = (ListyList) handler.get("ListyList");
            temp.increaseSize();
            timeKeep = 0;
        }
    }
}
