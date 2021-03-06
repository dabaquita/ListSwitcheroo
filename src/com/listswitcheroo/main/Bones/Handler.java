package com.listswitcheroo.main.Bones;

// Denielle Abaquita
// 7/3/20

import com.listswitcheroo.main.Objects.GameObject;
import com.listswitcheroo.main.Objects.ID;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;

public class Handler
{
    public LinkedList<GameObject> objects = new LinkedList<>();

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
        objects.add(gameObject);
    }

    public void remove(GameObject gameObject)
    {
        objects.remove(gameObject);
    }

    public GameObject get(String className)
    {
        for (GameObject go : objects)
            if (go.getClass().toString().contains(className))
                return go;

        return null;
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
