package org.jd.view;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JLabel {
    private int score;
    public ScorePanel() {
        score = 0;
        setHorizontalAlignment(SwingConstants.CENTER);
        setText("分数：" + score);
        setFont(new Font("楷体", Font.BOLD, 30));
    }

    public void addScore(int add) {
        score += add;
        setText("分数：" + score);
    }
}
