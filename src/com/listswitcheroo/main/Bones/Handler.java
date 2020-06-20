package com.listswitcheroo.main.Bones;

// Denielle Abaquita
// 6/8/20

import com.listswitcheroo.main.Objects.GameObject;
import com.listswitcheroo.main.Objects.ID;

import java.awt.*;
import java.util.HashSet;

public class Handler
{
    public HashSet<GameObject> objects = new HashSet<>();

    public void tick()
    {
        for (GameObject go : objects)
            go.tick();
    }

    public void render(Graphics g)
    {
        for (GameObject go : objects)
            go.render(g);
    }

    public void add(GameObject gameObject)
    {
        this.objects.add(gameObject);
    }

    public void remove(GameObject gameObject)
    {
        this.objects.remove(gameObject);
    }

    public void clear(ID id)
    {
        objects.removeIf(go -> go.getId().equals(id));
    }

    public void clearAll()
    {
        objects.clear();
    }
}
