package org.jd.view;

import org.jd.model.Matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Method;

public class MatrixPanel extends JPanel {
    private int size;
    private Matrix matrix;
    private NumBlock[][] blocks = new NumBlock[4][4];

    public MatrixPanel(int x, int y, int size) {
        this(x, y, size, Color.orange);
    }

    public MatrixPanel(int x, int y, int size, Color color) {
        matrixInit();
        this.size = size;
        FlowLayout layoutManager = new FlowLayout(FlowLayout.LEFT);
        setLayout(layoutManager);
        layoutManager.setHgap(1);
        layoutManager.setVgap(1);
        setBounds(x, y,size, size);
        setBorder(BorderFactory.createLineBorder(Color.black, 2));
        setOpaque(true);
        setBackground(color);
        showBlocks();
        setEnabled(true);
        setFocusable(true);
        requestFocus();
//        addKeyListener(new MatrixCtl());

    }

    public void updateMatrix() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                blocks[i][j].setBackground(getColor(i, j));
                if (matrix.at(i, j) != 0) {
                    blocks[i][j].setText("" + matrix.at(i, j));
                } else {
                    blocks[i][j].setText("");
                }
            }
        }
    }

    private void matrixInit() {
        matrix = new Matrix(4);
        matrix.makeNums();
    }

    private Color getColor(int i, int j) {
        switch (matrix.at(i, j)) {
            case 2:
                return new Color(0xFF, 0xF6, 0x8F);
            case 4:
                return new Color(0xFF, 0xEB,0xCD);
            case 8:
                return new Color(0xFF, 0xD7,0x00);
            case 16:
                return new Color(0xFF, 0xB5,0xC5);
            case 32:
                return new Color(0xFF, 0x7F,0x00);
            case 64:
                return new Color(0xFF, 0x00,0xFF);
            case 128:
                return new Color(0xFF, 0x00,0x00);
            case 256:
                return new Color(0xC0, 0xFF,0x3E);
            case 512:
                return new Color(0xA0, 0x20,0xF0);
            case 1024:
                return new Color(0xB2, 0x22,0x22);
            case 2048:
                return new Color(0x00, 0x00,0x8B);
            default:
                return new Color(0xFF, 0xFF, 0xE0);
        }
    }

    private void showBlocks() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                blocks[i][j] = new NumBlock();
                add(blocks[i][j]);
                blocks[i][j].setBackground(getColor(i, j));
                if (matrix.at(i, j) != 0) {
                    blocks[i][j].setText("" + matrix.at(i, j));
                } else {
                    blocks[i][j].setText("");
                }
            }
        }
    }

    public Matrix getMatrix() {
        return matrix;
    }

//    private class MatrixCtl extends KeyAdapter {
//
//        public MatrixCtl() {
//            super();
//        }
//
//        @Override
//        public void keyPressed(KeyEvent e) {
//
//            switch (e.getKeyCode()) {
//                case KeyEvent.VK_UP:
//                    matrix.upMerge();
//                    break;
//                case KeyEvent.VK_DOWN:
//                    matrix.downMerge();
//                    break;
//                case KeyEvent.VK_RIGHT:
//                    matrix.rightMerge();
//                    break;
//                case KeyEvent.VK_LEFT:
//                    matrix.leftMerge();
//                    break;
//                default:
//                    return;
//            }
//            if (matrix.makeNums() == 0 && !matrix.ctn()) {
//                System.out.println("你输了");
//            } else {
//                updateMatrix();
//            }
//            System.out.println("?");
//        }
//    }

    private class NumBlock extends JLabel{
        NumBlock() {
            setBorder(BorderFactory.createLineBorder(Color.black, 1));
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
            setOpaque(true);
            setPreferredSize(new Dimension((size - 3) / 4 - 1, (size - 3) / 4 - 1));
        }
    }
}
