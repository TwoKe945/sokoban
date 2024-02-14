package cn.com.twoke.game.sokoban.components;

import java.awt.*;

public interface GameComponent {

    /**
     * 页面状态更新
     * @param g
     */
    void draw(Graphics g);

    /**
     * 逻辑状态更新
     */
    void update();


}
