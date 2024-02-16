package cn.com.twoke.game.sokoban.state;

import cn.com.twoke.game.sokoban.constant.Global;
import cn.com.twoke.game.sokoban.levels.LevelManage;
import cn.com.twoke.game.sokoban.main.Game;
import cn.com.twoke.game.sokoban.object.*;
import cn.com.twoke.game.sokoban.utils.ResourceLoader;
import lombok.Getter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Getter
public class Playing extends AbstractGameState {


    private final Game game;
    private BufferedImage wall;
    private BufferedImage box;
    private BufferedImage ball;

    private LevelManage levelManage;
    private Player player;
    private List<GameObject> gameObjects;
    public Playing(Game game) {
        this.game = game;
        init();
    }

    private void init() {
        player = new Player(0,0);
        levelManage = new LevelManage(this);
        levelManage.loadCurrentLevel();

        wall = ResourceLoader.loadImage(Global.Resource.Image.WALL);
        box = ResourceLoader.loadImage(Global.Resource.Image.BOX);
        ball = ResourceLoader.loadImage(Global.Resource.Image.BALL);
    }


    public void loadGameObjects() {
        gameObjects = new ArrayList<>();
        gameObjects.addAll(levelManage.getWalls());
        gameObjects.addAll(levelManage.getBoxs());
        gameObjects.addAll(levelManage.getEndPoints());
    }

    @Override
    public void draw(Graphics g) {
        levelManage.drawLevelBackground(g);
        drawLevel(g, gameObjects, bean -> bean.getY() <= player.getY());
        player.drawImg(g);
        drawLevel(g, gameObjects, bean -> bean.getY() > player.getY());
    }

    private void drawLevel(Graphics g,List<? extends GameObject> gameObjects, Predicate<GameObject> predicate) {
        gameObjects.stream().filter(predicate).sorted((a,b) -> a.getY() - b.getY()).forEach(bean -> drawGameObject(g, bean));
    }




    private void drawGameObject(Graphics g,GameObject bean) {
        if (bean instanceof Box) {
            bean.drawImg(g, box);
        } else if (bean instanceof EndPoint && !((EndPoint) bean).isSuccess()) {
            bean.drawImg(g, ball);
        } else if (bean instanceof  Wall) {
            bean.drawImg(g, wall);
        }
    }


    @Override
    public void update() {
        player.update();
        levelManage.update();
        if (levelManage.isOk()) {
            levelManage.loadNextLevel();
        }
    }


    @Override
    public GameStateEnum state() {
        return GameStateEnum.PLAYING;
    }
}
