package com.efim.astar.algo;

public record SearchProblem(
        Cell[][] area,
        Point start,
        Point goal
) {
    public SearchProblem {
        for (int i = 1; i < area.length; i++) {
            if (area[i].length != area[0].length) {
                throw new IllegalArgumentException("The area should be rectangular");
            }
        }
    }
}
