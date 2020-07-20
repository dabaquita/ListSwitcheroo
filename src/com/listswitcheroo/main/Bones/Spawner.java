package com.listswitcheroo.main.Bones;

// Denielle Abaquita
// 7/3/20

import com.listswitcheroo.main.Objects.ID;
import com.listswitcheroo.main.Objects.ListyList;

import java.util.Random;

public class Spawner
{
    private Handler handler;
    private int timeKeep;

    public Spawner(Handler handler)
    {
        this.handler = handler;
        timeKeep = 0;

        // Initial spawn of the first linked list
        handler.add(new ListyList(150, 150, ID.HappyList));
    }

    public void tick()
    {
        ListyList list = (ListyList) handler.get("ListyList");
        timeKeep++;

        if (timeKeep >= 100 && list.getSize() < list.getMaxSize())
        {
            list.increaseSize();
            timeKeep = 0;
        }
    }
}
