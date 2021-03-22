package com.efim.astar.heuristic;

import com.efim.astar.algo.Heuristic;
import com.efim.astar.algo.Point;

import java.util.HashMap;
import java.util.Map;

public class GarbageDistance implements Heuristic {

    public final double magnitude;

    public final Map<PointPair, Double> coefficient = new HashMap<>();

    public GarbageDistance(double magnitude) {
        this.magnitude = magnitude;
    }

    @Override
    public Double apply(Point a, Point b) {
        return magnitude * coefficient.computeIfAbsent(new PointPair(a, b), p -> Math.random());
    }

    private static record PointPair(
            Point a, Point b
    ) {
    }
}
