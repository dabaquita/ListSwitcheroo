package com.listswitcheroo.main;// Denielle Abaquita
// 7/20/20

import com.listswitcheroo.main.Bones.Handler;
import com.listswitcheroo.main.Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter
{
    private Game game;
    private Handler handler;

    public Menu (Game game, Handler handler)
    {
        this.game = game;
        this.handler = handler;
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
                // Add in spawner!
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
        Font titleFont = new Font("arial", 1, 50);
        Font buttonFont = new Font("arial", 1, 30);
        Font descriptionFont = new Font("arial", 1, 12);

        if (game.gameState == Game.STATE.Menu)
        {
            g.setFont(titleFont);
            g.setColor(Color.white);
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
            g.setColor(Color.white);
            g.drawString("What is this?", 150, 100);

            // Description
            g.setFont(descriptionFont);
            g.setColor(Color.white);
            g.drawString("It's a small visualization of reversing a linked list!", 50, 180);
            g.drawString("To reverse a linked list, you have to keep track of previous, current, and next nodes", 50, 200);
            g.drawString("and then change the references to have the current node point to the previous node and so on.", 50, 220);

            g.drawString("Made with love during COVID-19, by Denielle Abaquita", 50, 250);

            // Back Button
            g.setFont(buttonFont);
            g.setColor(Color.white);
            g.drawString("Back", 280, 390);

            g.setColor(Color.white);
            g.drawRect(210, 350, 200, 64);
        }
    }
}
