package com.efim.astar.heuristic;

import com.efim.astar.algo.Heuristic;
import com.efim.astar.algo.Point;

public class InconsistentGarbageDistance implements Heuristic {

    public double magnitude;

    public InconsistentGarbageDistance(double magnitude) {
        this.magnitude = magnitude;
    }

    @Override
    public Double apply(Point point, Point point2) {
        return Math.random() * magnitude;
    }
}
