package cn.com.twoke.game.sokoban.object;

import cn.com.twoke.game.sokoban.constant.Global;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
@Setter
public class GameObject {
    /**
     * x坐标
     */
    protected int x;
    /**
     * y坐标
     */
    protected int y;
    /**
     * 碰撞盒子宽度
     */
    protected final int width;
    /**
     * 碰撞盒子高度
     */
    protected final int height;

    public GameObject(int x, int y, int width, int height) {
        this.width = (int)(width * Global.Game.SCALE);
        this.height = (int)(height * Global.Game.SCALE);
        this.x = x;
        this.y = y;
    }

    public void drawImg(Graphics g, BufferedImage img, int width, int height, int offsetX, int offsetY) {
        g.drawImage(img,
                this.x  *  Global.Game.TILE_SIZE + offsetX,
                this.y  *  Global.Game.TILE_SIZE + offsetY,
                (int)(width * Global.Game.SCALE),
                (int)(height * Global.Game.SCALE), null);
    }

    public void drawImg(Graphics g, BufferedImage img) {
        drawImg(g, img, this.width, this.height,0, 0);
    }


}
