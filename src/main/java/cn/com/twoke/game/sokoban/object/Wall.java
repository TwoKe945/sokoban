package cn.com.twoke.game.sokoban.object;

import cn.com.twoke.game.sokoban.constant.Global;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Wall extends GameObject{
    public Wall(int x, int y) {
        super(x, y,35,46);
    }

    @Override
    public void drawImg(Graphics g, BufferedImage img) {
        super.drawImg(g, img, 35, 46, 0, - ((int)(46 * Global.Game.SCALE) - Global.Game.TILE_SIZE));
    }
}
