package com.efim.astar.heuristic;

import com.efim.astar.algo.Heuristic;
import com.efim.astar.algo.Point;

import java.util.HashMap;
import java.util.Map;

public class RandomlyReducedEuclideanDistance implements Heuristic {

    public final Map<PointPair, Double> coefficient = new HashMap<>();

    @Override
    public Double apply(Point a, Point b) {
        return a.euclidianTo(b) * coefficient.computeIfAbsent(new PointPair(a, b), p -> Math.random());
    }

    private static record PointPair(
            Point a, Point b
    ) {
    }

}
