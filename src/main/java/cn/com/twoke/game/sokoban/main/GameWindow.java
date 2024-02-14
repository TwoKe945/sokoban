package cn.com.twoke.game.sokoban.main;

import lombok.Getter;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

@Getter
public class GameWindow {

    private final JFrame jframe;

    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame();
//        jframe.setSize(1280, 800);
        jframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setResizable(false);
        jframe.pack();
        // 设置显示位置到屏幕中间,必须在pack后面设置
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowLostFocus();
            }
        });
    }

}
