package com.example.caritaf.ankkapeli;


import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder holder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;


    public MainThread(SurfaceHolder holder, GamePanel panel) {
        super();
        this.holder = holder;
        this.gamePanel = panel;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        long frameCount = 0;
        long targetTime = 1000/FPS;

        while(running) {
            startTime = System.nanoTime();
            canvas = null;

            // Yritä lukita canvas pixelien piirtoa varten
            try {
                canvas = this.holder.lockCanvas();
                synchronized (holder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {
            }
            finally {
                if(canvas!=null) {
                    try {
                        holder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                timeMillis = (System.nanoTime() - startTime / 1000000);
                waitTime = targetTime - timeMillis;

                try {
                    this.sleep(waitTime);
                } catch (Exception e) {
                }

                totalTime += System.nanoTime() - startTime;
                frameCount++;
                if(frameCount == FPS) {
                    averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                    frameCount = 0;
                    totalTime = 0;
                    System.out.println(averageFPS);
                }
            }
        }
    }

    public void setRunning(boolean b) {
        running = b;
    }
}
