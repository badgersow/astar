package com.efim.astar.visual;

import com.efim.astar.algo.Cell;
import com.efim.astar.algo.Point;
import com.efim.astar.algo.SearchProblem;
import com.efim.astar.algo.SearchSolution;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class SolutionVisualiserToPng implements SolutionVisualiser{

    @Override
    public void visualise(File output, SearchProblem problem, SearchSolution solution) {
        var area = problem.area();
        var image = new BufferedImage(area[0].length, area.length, BufferedImage.TYPE_INT_RGB);
        var pathSet = new HashSet<Point>(solution.path());
        var expandedSet = new HashSet<Point>(solution.expanded());

        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {
                if (area[i][j] == Cell.WALL) {
                    image.setRGB(j, i, Color.BLACK.getRGB());
                } else {
                    var currentPoint = new Point(i, j);
                    if (problem.start().inVicinity(currentPoint, 10)) {
                        image.setRGB(j, i, Color.BLUE.getRGB());
                    } else if (problem.goal().inVicinity(currentPoint, 10)) {
                        image.setRGB(j, i, Color.RED.getRGB());
                    } else if (pathSet.contains(currentPoint)) {
                        image.setRGB(j, i, Color.MAGENTA.getRGB());
                    } else if (expandedSet.contains(currentPoint)) {
                        image.setRGB(j, i, Color.LIGHT_GRAY.getRGB());
                    } else if (area[i][j] == Cell.EMPTY) {
                        image.setRGB(j, i, Color.WHITE.getRGB());
                    }
                }
            }
        }

        try {
            ImageIO.write(image, "png", output);
        } catch (IOException e) {
            throw new RuntimeException("Can't write the image " + output, e);
        }
    }

}
