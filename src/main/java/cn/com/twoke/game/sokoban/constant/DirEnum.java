package cn.com.twoke.game.sokoban.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DirEnum {
    LEFT(0),
    UP(1),
    RIGHT(2),
    DOWN(3),
    ;

    private int code;
}
