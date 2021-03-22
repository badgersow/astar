package com.efim.astar.heuristic;

import com.efim.astar.algo.Heuristic;
import com.efim.astar.algo.Point;

public class DistanceWithoutObstacles implements Heuristic {
    @Override
    public Double apply(Point a, Point b) {
        final var di = Math.abs(a.i() - b.i());
        final var dj = Math.abs(a.j() - b.j());

        return Math.min(di, dj) * Math.sqrt(2)
                + Math.max(di, dj) - Math.min(di, dj);
    }
}
