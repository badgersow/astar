package com.efim.astar.heuristic;

import com.efim.astar.algo.Heuristic;
import com.efim.astar.algo.Point;

public class ChebyshevDistance implements Heuristic {
    @Override
    public Double apply(Point a, Point b) {
        final var di = Math.abs(a.i() - b.i());
        final var dj = Math.abs(a.j() - b.j());

        return (double) Math.max(di, dj);
    }
}
