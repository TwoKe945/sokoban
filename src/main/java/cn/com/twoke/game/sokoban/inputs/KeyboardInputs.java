package cn.com.twoke.game.sokoban.inputs;

import cn.com.twoke.game.sokoban.constant.DirEnum;
import cn.com.twoke.game.sokoban.main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> gamePanel.getGame().getPlaying().getPlayer().move(DirEnum.UP);
            case KeyEvent.VK_DOWN -> gamePanel.getGame().getPlaying().getPlayer().move(DirEnum.DOWN);
            case KeyEvent.VK_LEFT -> gamePanel.getGame().getPlaying().getPlayer().move(DirEnum.LEFT);
            case KeyEvent.VK_RIGHT -> gamePanel.getGame().getPlaying().getPlayer().move(DirEnum.RIGHT);
        }
    }
}
