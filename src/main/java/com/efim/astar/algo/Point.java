package com.efim.astar.algo;

public record Point(
        int i,
        int j) {

    public int manhattanTo(Point other) {
        return Math.abs(i - other.i) + Math.abs(j - other.j);
    }

    public boolean inEuclideanVicinity(Point other, int epsilon) {
        return Math.sqrt(
                (i - other.i) * (i - other.i) +
                        (j - other.j) * (j - other.j)) <= epsilon;
    }

}
