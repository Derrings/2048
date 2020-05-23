package org.jd.model;
import java.io.*;
import java.util.concurrent.*;

public class Matrix {
    private int[][] numMatrix; // 二维数组表示的数字方阵
    private int size; // 方阵大小
    private int nowBlocks; // 现存方块数

    /**
     * @param size：用户指定的方阵大小
     */
    public Matrix(int size) {
        this.size = size;
        numMatrix = new int[size][size];
        nowBlocks = 0;
    }

    /**
     * 方阵内数组向左对齐
     */
    private void moveLeft() {
        for(int row = 0; row < size; row++) {
            for (int col = 0, index = 0; col < size; col++) {
                if (numMatrix[row][col] != 0) {
                    int mid = numMatrix[row][col];
                    numMatrix[row][col] = 0;
                    numMatrix[row][index] = mid;
                    index++;
                }
            }
        }
    }

    /**
     * 方阵内数组向右对齐
     */

    private void moveRight() {
        for (int row = 0; row < size; row++) {
            for (int col = size - 1, index = size - 1; col >= 0; col--) {
                if (numMatrix[row][col] != 0) {
                    int mid = numMatrix[row][col];
                    numMatrix[row][col] = 0;
                    numMatrix[row][index] = mid;
                    index--;
                }
            }
        }
    }

    /**
     * 方阵内数组向上对齐
     */
    private void moveUp() {
        for (int col = 0; col < size; col++) {
            for (int row = 0, index = 0; row < size; row++) {
                if (numMatrix[row][col] != 0) {
                    int mid = numMatrix[row][col];
                    numMatrix[row][col] = 0;
                    numMatrix[index][col] = mid;
                    index++;
                }
            }
        }
    }

    /**
     * 方阵内数组向下对齐
     */
    private void moveDown() {
        for (int col = 0; col < size; col++) {
            for (int row = size - 1, index = size - 1; row >= 0; row--) {
                if (numMatrix[row][col] != 0) {
                    int mid = numMatrix[row][col];
                    numMatrix[row][col] = 0;
                    numMatrix[index][col] = mid;
                    index--;
                }
            }
        }
    }

    /**
     * 随机生成方阵内数字
     */
    public int makeNums() {
        if (nowBlocks >= size * size) {
            return 0;
        }
        int row = (int) (Math.random() * size);
        int col = (int) (Math.random() * size);
        int num = ((int) (Math.random() * 2) + 1) * 2;
        if (numMatrix[row][col] != 0) {
            makeNums();
        } else {
            numMatrix[row][col] = num;
            nowBlocks++;
        }
        return num;
    }

    /**
     * 向左合并
     */
    public void leftMerge() {
        moveLeft();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size - 1; col++) {
                if (numMatrix[row][col] != 0 && numMatrix[row][col] == numMatrix[row][col + 1]) {
                    numMatrix[row][col] <<= 1;
                    numMatrix[row][col + 1] = 0;
                    nowBlocks--;
                }
            }
        }
        moveLeft();
    }

    /**
     * 向右合并
     */
    public void rightMerge() {
        moveRight();
        for (int row = 0; row < size; row++) {
            for (int col = size - 1; col >= 1; col--) {
                if (numMatrix[row][col] != 0 && numMatrix[row][col] == numMatrix[row][col - 1]) {
                    numMatrix[row][col] <<= 1;
                    numMatrix[row][col - 1] = 0;
                    nowBlocks--;
                }
            }
        }
        moveRight();
    }

    /**
     * 向上合并
     */
    public void upMerge() {
        moveUp();
        for (int col = 0; col < size; col++) {
            for (int row = 0; row < size - 1; row++) {
                if(numMatrix[row][col] != 0 && numMatrix[row][col] == numMatrix[row + 1][col]) {
                    numMatrix[row][col] <<= 1;
                    numMatrix[row + 1][col] = 0;
                    nowBlocks--;
                }
            }
        }
        moveUp();
    }

    /**
     * 向下合并
     */
    public void downMerge() {
        moveDown();
        for (int col = 0; col < size; col++) {
            for (int row = size - 1; row >= 1; row--) {
                if(numMatrix[row][col] != 0 && numMatrix[row][col] == numMatrix[row - 1][col]) {
                    numMatrix[row][col] <<= 1;
                    numMatrix[row - 1][col] = 0;
                    nowBlocks--;
                }
            }
        }
        moveDown();
    }

    public boolean ctn() {
        if (nowBlocks < size * size) {
            return true;
        }
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (row + 1 < size) {
                    if (numMatrix[row][col] == numMatrix[row + 1][col]) {
                        return true;
                    }
                }
                if (col + 1 < size) {
                    if (numMatrix[row][col] == numMatrix[row][col + 1]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void printMatrix() {
        System.out.println("-----------------");
        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                System.out.print(numMatrix[i][j] + "\t");
            }
            System.out.println("\n");
        }
        System.out.println("-----------------");
        System.out.println("\n");
    }

    public int at(int i, int j) {
        return numMatrix[i][j];
    }
}
