package com.efim.astar.algo;

public record Point(
        int i,
        int j) {

    public double euclidianTo(Point other) {
        return Math.sqrt((i - other.i) * (i - other.i) + (j - other.j) * (j - other.j));
    }

    public boolean inEuclideanVicinity(Point other, int epsilon) {
        return euclidianTo(other) <= epsilon;
    }

}
