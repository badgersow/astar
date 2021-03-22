package com.efim.astar.heuristic;

import com.efim.astar.algo.Heuristic;
import com.efim.astar.algo.Point;

public class EuclideanDistance implements Heuristic {
    @Override
    public Double apply(Point point, Point point2) {
        return point.euclidianTo(point2);
    }
}
