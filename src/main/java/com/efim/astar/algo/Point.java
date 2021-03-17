package com.efim.astar.algo;

public record Point(
        int i,
        int j) {

    public boolean inVicinity(Point other, int epsilon) {
        return Math.sqrt(
                (i - other.i) * (i - other.i) +
                        (j - other.j) * (j - other.j)) <= epsilon;
    }

}
