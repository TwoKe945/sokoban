package cn.com.twoke.game.sokoban.object;

import cn.com.twoke.game.sokoban.constant.Global;
import cn.com.twoke.game.sokoban.main.Game;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;


@Setter
@Getter
public class EndPoint extends GameObject {
    boolean success;

    public EndPoint(int x, int y) {
        super(x, y, 35, 35);
    }

    @Override
    public void drawImg(Graphics g, BufferedImage img) {
        super.drawImg(g, img, 30, 30,  (((int)(Global.Game.TILE_SIZE - 30 * Global.Game.SCALE)) / 2), (((int)(Global.Game.TILE_SIZE - 30 * Global.Game.SCALE)) / 2));
    }
}
