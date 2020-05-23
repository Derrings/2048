package org.jd.view;
import org.jd.model.Matrix;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SingleClassicView {
    /**
     *  整体框架：合成自 ImageFrame，使得其可以添加背景图片
     */
    private ImageFrame frame;

    /**
     *  矩阵面板：用于展示游戏数字方块矩阵
     */
    private MatrixPanel matrixPanel;

    /**
     *  积分版面：用于显示分数
     */
    private ScorePanel scorePanel;


    private void initScorePanel() {
        scorePanel = new ScorePanel();
        scorePanel.setBounds(0, 0,1000,200);
    }

    private void initMatrixPanel() {
        matrixPanel = new MatrixPanel(300, 250, 405);
    }

    public SingleClassicView() {
        frame = new ImageFrame("单人传统模式");
        frame.setLayout(null);
        frame.setSize(1000, 900);
        frame.setMinimumSize(new Dimension(1000, 900));
        initMatrixPanel();
        initScorePanel();
        Container container = frame.getContentPane();
        container.add(matrixPanel);
        container.add(scorePanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.requestFocus();
        frame.addKeyListener(new GameCtl());
    }

    public JPanel getMatrixPanel() {
        return matrixPanel;
    }

    public JLabel getScorePanel() {
        return scorePanel;
    }

    private class GameCtl extends KeyAdapter {

        public GameCtl() {
            super();
        }

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    matrixPanel.getMatrix().upMerge();
                    break;
                case KeyEvent.VK_DOWN:
                    matrixPanel.getMatrix().downMerge();
                    break;
                case KeyEvent.VK_RIGHT:
                    matrixPanel.getMatrix().rightMerge();
                    break;
                case KeyEvent.VK_LEFT:
                    matrixPanel.getMatrix().leftMerge();
                    break;
                default:
                    return;
            }
            int score;
            if ((score = matrixPanel.getMatrix().makeNums()) == 0 && !matrixPanel.getMatrix().ctn()) {
                new DefeatView();
            } else {
                scorePanel.addScore(score);
                matrixPanel.updateMatrix();
            }
        }
    }
}
