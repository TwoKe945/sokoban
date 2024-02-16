package cn.com.twoke.game.sokoban.utils;


import cn.com.twoke.game.sokoban.constant.Global;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ResourceLoader {

    public static BufferedImage loadImage(String spriteFileName) {
        BufferedImage image = null;
        InputStream is = ResourceLoader.class.getResourceAsStream("/"+spriteFileName);
        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }


    public static List<int[][]> loadAllLevels() {
        URL url = ResourceLoader.class.getResource("/levels");
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        File[] files = Optional.ofNullable(file).orElseThrow(() -> new RuntimeException("关卡文件夹不能为空！")).listFiles();
        return Arrays.stream(Optional.ofNullable(files).orElseThrow(() -> new RuntimeException("关卡文件不能为空！"))).sorted(ResourceLoader::compareLevelFiles).map(ResourceLoader::fileToArray)
                .collect(Collectors.toList());
    }

    private static int parseInt(File lvlFile) {
        return Integer.parseInt(lvlFile.getName().replace(".level", "") );
    }


    private static int[][] fileToArray(File file) {
        return loadLevel(file.getName());
    }

    private static int compareLevelFiles(File lvlA, File lvlB) {
        return parseInt(lvlA) - parseInt(lvlB);
    }

    public static int[][] loadLevel(String levelName) {
        int[][] levelData = new int[Global.Game.ROWS][Global.Game.COLS];
        URL resource = ResourceLoader.class.getResource("/levels/" + levelName);
        try (FileReader reader = new FileReader( new File(resource.toURI()));
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            int rows = 0;
            while ((line = br.readLine()) != null) {
                for (int col = 0; col < line.length() && col < Global.Game.COLS; col++) {
                    levelData[rows][col] = Integer.parseInt(String.valueOf( line.charAt(col)));
                }
                rows++;
                if (rows >= Global.Game.ROWS) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return levelData;
    }




}
