package org.jd;

import org.jd.view.ImageFrame;

import javax.swing.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

class R implements Runnable {
    private ReentrantReadWriteLock rrw;

    public R(ReentrantReadWriteLock rrw) {
        this.rrw = rrw;
    }

    @Override
    public void run() {
//        rrw.readLock().lock();
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getId() + "Read...");
        }
//        rrw.readLock().unlock();
    }
}
class W implements Runnable {
    private ReentrantReadWriteLock rrw;

    public W(ReentrantReadWriteLock rrw) {
        this.rrw = rrw;
    }

    @Override
    public void run() {
        rrw.writeLock().lock();
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getId() + "write...");
        }
        rrw.writeLock().unlock();
    }
}
public class GUITest {
    private ImageFrame imageFrame = null; // 包含

    public GUITest() {


        imageFrame = new ImageFrame("E:\\Image\\goodPictures\\Ass.jpg");
        imageFrame.setSize(1280, 960);
        imageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imageFrame.setVisible(true);
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantReadWriteLock rrw = new ReentrantReadWriteLock();
        new Thread(new W(rrw)).start();
        new Thread(new R(rrw)).start();
        new Thread(new R(rrw)).start();
        Thread.sleep(10);
    }
}
