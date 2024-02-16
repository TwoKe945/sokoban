package cn.com.twoke.game.sokoban.levels;

import lombok.Data;

@Data
public class Level {

    private int[][] levelData;

    public Level(int[][] levelData) {
        this.levelData = levelData;
    }
}
