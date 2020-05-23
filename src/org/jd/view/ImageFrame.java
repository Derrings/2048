package org.jd.view;

import javax.swing.*;
import java.awt.*;

public class ImageFrame extends JFrame {
    private Image background = null;
    public ImageFrame(String title) {
        super(title);
    }
    public ImageFrame(String title, String url) {
        super(title);
        ImageIcon backgroundIcon = new ImageIcon(url);
        background = backgroundIcon.getImage();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }
}
