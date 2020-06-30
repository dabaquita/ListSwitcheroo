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
    private BufferedImage greenPoint, orangePoint, redPoint;

    public Pointer(float x, float y, ID id)
    {
        super(x, y, id);
        initImages();

        velX = 5;
        velY = 5;
    }

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
            greenPoint = ImageIO.read(new File("src/com/listswitcheroo/main/GreenPoint.png"));
            orangePoint = ImageIO.read(new File("src/com/listswitcheroo/main/OrangePoint.png"));
            redPoint = ImageIO.read(new File("src/com/listswitcheroo/main/RedPoint.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void tick()
    {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 32)
            velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 16)
            velX *= -1;
    }

    @Override
    public void render(Graphics g)
    {
        // If we have head Pointer, then it
        // must have the smiley face texture
        if (getId().equals(ID.PointLeft))
        {
            g.drawImage(greenPoint, (int) x, (int) y,  null);
            //g.setColor(Color.GREEN);
            //g.fillOval((int) x, (int) y, 32, 32);
        }
        else if (getId().equals(ID.MildList))
        {
            g.setColor(Color.ORANGE);
            g.fillOval((int) x, (int) y, 32, 32);
        }
        else if (getId().equals(ID.LonelyList))
        {
            g.setColor(Color.RED);
            g.fillOval((int) x, (int) y, 32, 32);
        }
    }
}
