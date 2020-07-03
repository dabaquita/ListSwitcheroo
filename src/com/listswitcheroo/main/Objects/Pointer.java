package com.listswitcheroo.main.Objects;

import com.listswitcheroo.main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pointer extends Node
{
    protected Node next;
    private BufferedImage pointerImg;

    public Pointer (float x, float y, float velX, float velY, ID id)
    {
        super(x, y, id);
        initImages();

        this.velX = velX;
        this.velY = velY;
    }

    private void initImages()
    {
        try
        {
            pointerImg = ImageIO.read(new File("src/com/listswitcheroo/main/Pointer.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void render(Graphics g)
    {
        if (velX > 0 && velY > 0)
        {
            g.drawImage(rotateImageByDegrees(pointerImg, 90), (int) x, (int) y,  null);
        }
        else if (velX > 0 && velY < 0)
        {
            g.drawImage(pointerImg, (int) x, (int) y,  null);
        }
        else if (velX < 0 && velY < 0)
        {
            g.drawImage(rotateImageByDegrees(pointerImg, 270), (int) x, (int) y,  null);
        }
        else if (velX < 0 && velY > 0)
        {
            g.drawImage(rotateImageByDegrees(pointerImg, 180), (int) x, (int) y,  null);
        }
    }
}
