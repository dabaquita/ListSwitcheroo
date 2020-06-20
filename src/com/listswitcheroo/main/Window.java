package com.listswitcheroo.main;

// Denielle Abaquita
// 6/8/20

import javax.swing.*;
import java.awt.*;

public class Window
{
    private static final long serialVersionUID = 1366868780868328907L;

    public Window(int width, int height, String title, Game game)
    {
        JFrame frame = new JFrame(title);

        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setPreferredSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}
