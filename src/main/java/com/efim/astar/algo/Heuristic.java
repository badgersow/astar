package com.efim.astar.algo;

import java.util.function.BiFunction;

public interface Heuristic extends BiFunction<Point, Point, Double> {
}
