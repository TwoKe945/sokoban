package cn.com.twoke.game.sokoban.object;

import cn.com.twoke.game.sokoban.constant.DirEnum;
import cn.com.twoke.game.sokoban.constant.Global;
import cn.com.twoke.game.sokoban.features.Movable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
@Setter
public class Box  extends GameObject implements Movable {

    boolean movable = true;
    boolean moving = false;
    DirEnum dir;
    private int[][] levelData;
    public Box(int x, int y, int[][] levelData) {
        super(x, y, 35, 45);
        this.levelData = levelData;
    }

    @Override
    public void drawImg(Graphics g, BufferedImage img) {
        super.drawImg(g, img, 35, 45, 0,  - ((int)(45 * Global.Game.SCALE) - Global.Game.TILE_SIZE));
    }

    @Override
    public void move(DirEnum dir) {
        moving = true;
        this.dir = dir;
    }

    public void update() {
        if (movable && moving) {
            int xSpeed = 0;
            int ySpeed = 0;
            switch (dir) {
                case DOWN -> ySpeed += 1;
                case UP -> ySpeed -= 1;
                case RIGHT -> xSpeed += 1;
                case LEFT -> xSpeed -= 1;
            }
            if (canMove(xSpeed, ySpeed)) { // 箱子是否可移动
                this.setY(this.y + ySpeed);
                this.setX(this.x + xSpeed);
            }
            moving = false;
        }
    }



    public boolean canMove(int xSpeed, int ySpeed) {
        return levelData[this.y + ySpeed][this.x + xSpeed] != 1;
    }
}
