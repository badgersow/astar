package com.efim.astar.visual;

import com.efim.astar.algo.Cell;
import com.efim.astar.algo.Point;
import com.efim.astar.algo.SearchProblem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.IntStream;

public class ProblemGeneratorFromPng implements ProblemGenerator {

    @Override
    public SearchProblem generate(File input) {
        try {
            var image = ImageIO.read(input);
            var area = new Cell[image.getHeight()][image.getWidth()];
            Point start = null, end = null;
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    var rgb = image.getRGB(j, i);
                    var color = new Color(rgb);

                    if (isStartPixel(color)) {
                        area[i][j] = Cell.EMPTY;
                        start = new Point(i, j);
                    } else if (isGoalPixel(color)) {
                        area[i][j] = Cell.EMPTY;
                        end = new Point(i, j);
                    } else if (isWallPixel(color)) {
                        area[i][j] = Cell.WALL;
                    } else {
                        area[i][j] = Cell.EMPTY;
                    }
                }
            }
            Objects.requireNonNull(start);
            Objects.requireNonNull(end);

            return new SearchProblem(area, start, end);
        } catch (IOException e) {
            throw new RuntimeException("Problems while reading the image " + input, e);
        }
    }

    private boolean isStartPixel(Color color) {
        return color.getRed() < color.getBlue() &&
                color.getGreen() < color.getBlue();
    }

    private boolean isGoalPixel(Color color) {
        return color.getGreen() < color.getRed() &&
                color.getBlue() < color.getRed();

    }

    private boolean isWallPixel(Color color) {
        return color.getRed() == color.getGreen() &&
                color.getRed() == color.getBlue() &&
                color.getRed() < 255; // now white
    }

}
