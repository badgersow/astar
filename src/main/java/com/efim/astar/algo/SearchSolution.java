package com.efim.astar.algo;

import java.util.List;

public record SearchSolution(
        List<Point> path,
        List<Point> expanded
) {
}
