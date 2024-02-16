package cn.com.twoke.game.sokoban.main;

import cn.com.twoke.game.sokoban.state.AbstractGameState;
import cn.com.twoke.game.sokoban.state.GameSate;
import cn.com.twoke.game.sokoban.state.GameStateEnum;
import cn.com.twoke.game.sokoban.state.Playing;
import lombok.Getter;

import java.awt.*;


@Getter
public class Game extends AbstractGameState implements Runnable {
    /**
     * FPS
     */
    public static final int FPS_SET = 120;
    /**
     * UPS
     */
    public static final int UPS_SET = 200;

    private final GamePanel gamePanel;
    private final GameWindow gameWindow;
    private Thread drawThread;


    @Getter
    private static GameSate currentState;

    private Playing playing;


    public Game() {
        init();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startDrawGameLoop();
    }

    private void init() {
        playing = new Playing(this);
        currentState = playing;
    }

    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / FPS_SET;
        double timePerUpdate = 1_000_000_000.0 / UPS_SET;
        long lastCheck = System.currentTimeMillis();;
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;

        double deltaU = 0;
        double deltaF = 0;
        while (true) { // loop
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                gamePanel.requestFocus();
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000 ) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void startDrawGameLoop() {
        drawThread = new Thread(this);
        drawThread.start();
    }


    @Override
    public void draw(Graphics g) {
        switch (state()) {
            case MENU -> {}
            case PLAYING -> playing.draw(g);
        }
    }

    @Override
    public void update() {
        switch (state()) {
            case MENU -> {}
            case PLAYING -> playing.update();
        }
    }

    public void windowLostFocus() {
        // TODO 窗口失去焦点时暂停游戏状态
    }

    @Override
    public GameStateEnum state() {
        return currentState.state();
    }
}
