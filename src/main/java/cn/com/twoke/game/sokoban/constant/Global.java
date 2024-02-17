package cn.com.twoke.game.sokoban.constant;

public interface Global {


    interface Game {
        int DEFAULT_TILE_SIZE = 35;
        float SCALE = 1.0f;
        int TILE_SIZE = (int)(DEFAULT_TILE_SIZE * SCALE);
        int COLS = 16;
        int ROWS = 16;
    }

    interface Window {
        int WIDTH = Game.TILE_SIZE * Game.COLS;
        int HEIGHT = Game.TILE_SIZE * Game.ROWS;
    }

    interface Resource {

        interface Image {

            String BLOCK = "block.png";
            String WALL = "wall.png";
            String BOX = "box.png";
            String BALL = "ball.png";
            String PLAYER_DOWN = "down.png";
            String PLAYER_UP = "up.png";
            String PLAYER_LEFT = "left.png";
            String PLAYER_RIGHT = "right.png";
            String PLAYER_DEFAULT = PLAYER_DOWN;


        }



    }


}
