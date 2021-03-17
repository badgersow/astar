package com.efim.astar.algo;

import java.util.List;

public record SearchSolution(
        double cost,
        List<Point> path,
        List<Point> expanded
) {
}
