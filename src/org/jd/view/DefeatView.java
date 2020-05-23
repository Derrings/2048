package org.jd.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DefeatView {
    private ImageFrame imageFrame;
    private JLabel label;
    public DefeatView() {
        label = new JLabel();
        label.setFont(new Font("楷体", Font.BOLD, 50));
        label.setText("你输了！");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        imageFrame = new ImageFrame("失败！");
        imageFrame.setLayout(new BorderLayout());
        imageFrame.getContentPane().add(label, BorderLayout.CENTER);
        imageFrame.setSize(500, 400);
        imageFrame.setVisible(true);
    }
}
