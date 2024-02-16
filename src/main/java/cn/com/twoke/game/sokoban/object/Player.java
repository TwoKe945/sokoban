package cn.com.twoke.game.sokoban.object;

import cn.com.twoke.game.sokoban.constant.DirEnum;
import cn.com.twoke.game.sokoban.constant.Global;
import cn.com.twoke.game.sokoban.features.Movable;
import cn.com.twoke.game.sokoban.utils.ResourceLoader;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

public class Player extends GameObject implements Movable {

    private boolean moving = false;
    private DirEnum dir;
    BufferedImage[] animations;
    @Setter
    private int[][] levelData;
    @Setter
    private List<Box> boxes;

    public Player(int x, int y) {
        super(x, y ,35,60);
        init();
    }

    private void init() {
        dir = DirEnum.DOWN;
        animations = new BufferedImage[4];
        animations[DirEnum.LEFT.getCode()] = ResourceLoader.loadImage(Global.Resource.Image.PLAYER_LEFT);
        animations[DirEnum.UP.getCode()] = ResourceLoader.loadImage(Global.Resource.Image.PLAYER_UP);
        animations[DirEnum.RIGHT.getCode()] = ResourceLoader.loadImage(Global.Resource.Image.PLAYER_RIGHT);
        animations[DirEnum.DOWN.getCode()] = ResourceLoader.loadImage(Global.Resource.Image.PLAYER_DOWN);
    }

    public void drawImg(Graphics g) {
        drawImg(g, animations[dir.getCode()], 50 , 62, - (((int)(50 * Global.Game.SCALE - Global.Game.TILE_SIZE )) / 2), - ((int)(62 * Global.Game.SCALE - Global.Game.TILE_SIZE)));
    }

    @Override
    public void move(DirEnum dir) {
        moving = true;
        this.dir = dir;
    }

    public void update() {
        if (moving) {
            int xSpeed = 0;
            int ySpeed = 0;
            switch (dir) {
                case DOWN -> ySpeed += 1;
                case UP -> ySpeed -= 1;
                case RIGHT -> xSpeed += 1;
                case LEFT -> xSpeed -= 1;
            }
            if (hasBox(xSpeed, ySpeed)) {
                if (canPushBox(xSpeed, ySpeed)) {
                    pushBox(xSpeed, ySpeed);
                    this.setY(this.y + ySpeed);
                    this.setX(this.x + xSpeed);
                }
            } else if (canMove(xSpeed, ySpeed)) { // 玩家是否可移动
                if (hasBox(xSpeed, ySpeed)) {
                    if (canPushBox(xSpeed, ySpeed)) {
                        pushBox(xSpeed, ySpeed);
                        this.setY(this.y + ySpeed);
                        this.setX(this.x + xSpeed);
                    }
                } else {
                    this.setY(this.y + ySpeed);
                    this.setX(this.x + xSpeed);
                }
            }
            moving = false;
        }
    }

    private boolean canPushBox(int xSpeed, int ySpeed) {
        return boxes.stream().anyMatch(bean ->
                bean.canMove(xSpeed, ySpeed) &&
                bean.getY() == this.y + ySpeed &&
                bean.getX() == this.x + xSpeed
        );
    }

    private boolean hasBox(int xSpeed, int ySpeed) {
        return boxes.stream().anyMatch(bean ->
                bean.getY() == this.y + ySpeed &&
                bean.getX() == this.x + xSpeed
        );
    }

    private void pushBox(int xSpeed, int ySpeed) {
        boxes.stream().filter(bean ->
                bean.canMove(xSpeed, ySpeed) &&
                bean.getY() == this.y + ySpeed &&
                bean.getX() == this.x + xSpeed
        ).forEach(box ->  box.move(dir));
    }

    private boolean canMove(int xSpeed, int ySpeed) {
        return levelData[this.y + ySpeed][this.x + xSpeed] != 1;
    }


}
