package cn.com.twoke.game.sokoban.main;

import cn.com.twoke.game.sokoban.constant.Global;
import cn.com.twoke.game.sokoban.inputs.KeyboardInputs;
import cn.com.twoke.game.sokoban.inputs.MouseInputs;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class GamePanel extends JPanel {

    private final Game game;

    public GamePanel(Game game) {
        this.game = game;
        MouseInputs mouseInputs = new MouseInputs(this);
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(Global.Window.WIDTH, Global.Window.HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.draw(g);
    }


}
