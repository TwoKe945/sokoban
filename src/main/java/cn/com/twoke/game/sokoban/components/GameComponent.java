package cn.com.twoke.game.sokoban.components;

import java.awt.*;

public interface GameComponent {

    /**
     * 页面状态更新
     * @param g
     */
    void draw(Graphics g);
    default void draw(Graphics g, int x, int y) {};

    /**
     * 逻辑状态更新
     */
    void update();


}
