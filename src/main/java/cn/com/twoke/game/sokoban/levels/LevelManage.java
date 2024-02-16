package cn.com.twoke.game.sokoban.levels;

import cn.com.twoke.game.sokoban.constant.Global;
import cn.com.twoke.game.sokoban.object.Box;
import cn.com.twoke.game.sokoban.object.EndPoint;
import cn.com.twoke.game.sokoban.object.Wall;
import cn.com.twoke.game.sokoban.state.Playing;
import cn.com.twoke.game.sokoban.utils.ResourceLoader;
import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Data
public class LevelManage {

    private int levelNo;
    private List<Level> levels;
    private BufferedImage block;
    private Playing playing;
    private List<Wall> walls;
    private List<Box> boxs;
    private List<EndPoint> endPoints;

    public LevelManage( Playing playing) {
        this.playing = playing;
        levelNo = 0; // 默认第一关
        loadLevels();
        init();
    }

    private void init() {
        block = ResourceLoader.loadImage(Global.Resource.Image.BLOCK);
        walls = new ArrayList<>();
        boxs = new ArrayList<>();
        endPoints = new ArrayList<>();
    }


    public void loadCurrentLevel() {
        int[][] levelData = getCurrentLevel().getLevelData();
        for (int row = 0; row < levelData.length; row++) {
            for (int col = 0; col < levelData[row].length; col++) {
                // 加载墙体
                if (levelData[row][col] == 1) {
                    walls.add(new Wall(col, row));
                }
                if (levelData[row][col] == 2) {
                    endPoints.add(new EndPoint(col, row));
                }
                if (levelData[row][col] == 3) {
                    boxs.add(new Box(col, row, this));
                }
                // 初始化本关卡玩家位置
                if (levelData[row][col] == 4) {
                    playing.getPlayer().setX(col);
                    playing.getPlayer().setY(row);
                }
            }
        }
        playing.loadGameObjects();
        playing.getPlayer().setBoxes(boxs);
        playing.getPlayer().setLevelData(levelData);
    }

    public void loadLevels() {
        levels = new ArrayList<>();
        List<int[][]> levels = ResourceLoader.loadAllLevels();
        for (int[][] levelData : levels) {
            this.levels.add(new Level(levelData));
        }
    }

    public void loadNextLevel() {
        if (hasNextLevel()) {
            levelNo++;
            boxs.clear();
            endPoints.clear();
            walls.clear();
            loadCurrentLevel();
        }
    }

    /**
     * 获取当前关卡
     * @return
     */
    public Level getCurrentLevel() {
        if (levelNo >= levels.size()) {
            throw new RuntimeException("不存在的关卡异常");
        }
        return levels.get(levelNo);
    }

    /**
     * 是否存在下一关
     * @return
     */
    public boolean hasNextLevel() {
        return levelNo + 1 < levels.size();
    }


    public void drawLevelBackground(Graphics g) {
        Level currentLevel = getCurrentLevel();
        for (int row = 0; row < currentLevel.getLevelData().length; row++) {
            for (int col = 0; col < currentLevel.getLevelData()[row].length; col++) {
                // 绘制背景
                g.drawImage(block, col *  Global.Game.TILE_SIZE,row *  Global.Game.TILE_SIZE, Global.Game.TILE_SIZE, Global.Game.TILE_SIZE, null);
            }
        }
    }

    public void update() {
        for (Box box : boxs) {
            box.update();
        }
    }

    public boolean isOk() {
        int count = 0;
        boolean isSuccess;
        for (int i = 0; i < endPoints.size(); i++) {
            isSuccess = false;
            for (int j = 0; j < boxs.size(); j++) {
                if (boxs.get(j).getX() == endPoints.get(i).getX() &&
                        boxs.get(j).getY() == endPoints.get(i).getY()
                ) {
                    isSuccess = true;
                    count++;
                    break;
                }
            }
            endPoints.get(i).setSuccess(isSuccess);
        }
        return count == endPoints.size();

    }

    public boolean canMoveBox(Box box, int x, int y) {
        for (Box box1 : boxs) {
            if (box1 != box &&  box1.getY() == y && box1.getX() == x) {
                return false;
            }
        }
        return true;
    }
}
