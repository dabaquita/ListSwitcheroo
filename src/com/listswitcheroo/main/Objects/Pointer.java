package com.listswitcheroo.main.Objects;

// Denielle Abaquita
// 7/3/20

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    // Reverses the movement of the pointer
    @Override
    public void reverse(int animationDelay)
    {
        // Check if the node we are at already hits
        // a wall and change back velocities if true
        clamp();

        // We need to delay the reversal of the
        // pointer to show a steady reversal overall
        Timer timer = new Timer((int) ((750 * animationDelay) / 1.5), new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                velX *= -1;
                velY *= -1;
            }
        });

        timer.setRepeats(false);
        timer.start();
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
