package com.listswitcheroo.main;// Denielle Abaquita
// 7/20/20

import com.listswitcheroo.main.Bones.Handler;
import com.listswitcheroo.main.Objects.ID;
import com.listswitcheroo.main.Objects.Node;
import com.listswitcheroo.main.Objects.Pointer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter
{
    private Game game;
    private Handler handler;
    private int changeColorTimer;
    private Color titleColor;

    public Menu (Game game, Handler handler)
    {
        Random r = new Random();
        this.game = game;
        this.handler = handler;
        changeColorTimer = 0;
        titleColor = Color.ORANGE;

        // Create particles
        for (int i = 0; i < 3; i++)
        {
            // Nodes
            handler.add(new Node(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
                    r.nextInt(7), r.nextInt(7), ID.HappyList));

            // Pointers
            handler.add(new Pointer(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
                    r.nextInt(7), r.nextInt(7), ID.HappyList));
        }
    }

    public void mousePressed(MouseEvent e)
    {
        int mx = e.getX(), my = e.getY();

        // Menu page button presses
        if (game.gameState == Game.STATE.Menu)
        {
            // Play Button
            if (mouseOver(mx, my, 210, 150, 200, 64))
            {
                handler.clearAll();
                game.initSpawner();
                game.gameState = Game.STATE.Game;
            }

            // Help Button
            else if (mouseOver(mx, my,210, 250, 200, 64))
            {
                game.gameState = Game.STATE.Help;
            }

            // Quit Button
            else if (mouseOver(mx, my, 210, 350, 200, 64))
            {
                System.exit(1);
            }
        }
        // Help page button presses
        else if (game.gameState == Game.STATE.Help)
        {
            // Back button
            if (mouseOver(mx, my, 210, 350, 200, 64))
            {
                game.gameState = Game.STATE.Menu;
            }
        }
    }

    // Returns true if the mouse coordinate is within
    // the borders of the given x and y params
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
    {
        if (mx > x && mx < x + width)
            if (my > y && my < y + height)
                return true;

        return false;
    }

    public void render(Graphics g)
    {
        changeColorTimer++;
        Random r = new Random();

        if (changeColorTimer == 250)
        {
            titleColor = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255), 255);
            changeColorTimer = 0;
        }

        Font titleFont = new Font("arial", 1, 50);
        Font buttonFont = new Font("arial", 1, 30);
        Font descriptionFont = new Font("arial", 1, 12);

        if (game.gameState == Game.STATE.Menu)
        {
            g.setFont(titleFont);
            g.setColor(titleColor);
            g.drawString("ListSwitcheroo", 140, 100);

            // Play Button
            g.setFont(buttonFont);
            g.setColor(Color.white);
            g.drawString("Show", 270, 190);

            g.setColor(Color.white);
            g.drawRect(210, 150, 200, 64);

            // Help Button
            g.setFont(buttonFont);
            g.setColor(Color.white);
            g.drawString("Help", 275, 290);

            g.setColor(Color.white);
            g.drawRect(210, 250, 200, 64);

            // Quit Button
            g.setFont(buttonFont);
            g.setColor(Color.white);
            g.drawString("Quit", 280, 390);

            g.setColor(Color.white);
            g.drawRect(210, 350, 200, 64);
        }
        else if (game.gameState == Game.STATE.Help)
        {
            // Help Label at Top
            g.setFont(titleFont);
            g.setColor(titleColor);
            g.drawString("What is this?", 150, 100);

            // Description
            g.setFont(descriptionFont);
            g.setColor(Color.white);
            g.drawString("It's a small visualization of reversing a linked list!", 50, 140);
            g.drawString("To reverse a linked list, you have to keep track of previous, current, and next nodes", 50, 160);
            g.drawString("and then change the references to have the current node point to the previous node and so on.", 50, 180);

            g.drawString("Press SPACE any time to reverse the linked list.", 50, 220);
            g.drawString("When the linked list turns red, it means it REALLY wants to be reversed!", 50, 240);
            g.drawString("During reversal, colors will change to indicate it's reversing", 50, 260);

            g.drawString("Check out the Github README.md for more info on coding reversal!", 50, 300);
            g.drawString("Made with love during COVID-19 by Denielle Abaquita", 50, 320);

            // Back Button
            g.setFont(buttonFont);
            g.setColor(Color.white);
            g.drawString("Back", 280, 390);

            g.setColor(Color.white);
            g.drawRect(210, 350, 200, 64);
        }
    }
}
