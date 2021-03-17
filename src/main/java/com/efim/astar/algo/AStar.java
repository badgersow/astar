package com.efim.astar.algo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class AStar implements SearchAlgorithm{

    private final double distWeight;
    private final double heuristicWeight;

    public AStar(double distWeight, double heuristicWeight) {
        this.distWeight = distWeight;
        this.heuristicWeight = heuristicWeight;
    }

    private static final int[][] diffs = new int[][]{
            {0, 1}, {0, -1}, {1, 0}, {-1, 0},
            {1,1}, {1,-1}, {-1,1}, {-1,-1}
    };

    @Override
    public SearchSolution solve(SearchProblem problem) {
        var path = new ArrayList<Point>();
        var expanded = new ArrayList<Point>();

        Cell[][] area = problem.area();
        var I = area.length;
        var J = area[0].length;

        var frontier = new PriorityQueue<>(Comparator.comparingDouble(FrontierNode::weight));
        var minDistances = new HashMap<Point, Double>();
        var backlinks = new HashMap<Point, Point>();
        var cost = Double.POSITIVE_INFINITY;

        frontier.add(new FrontierNode(0, problem.start()));
        minDistances.put(problem.start(), 0.0);
        while (!frontier.isEmpty()) {
            var current = frontier.remove().point;

            if (current.equals(problem.goal())) {
                cost = minDistances.get(current);
                break;
            }

            expanded.add(current);

            for (int[] diff : diffs) {
                var adjI = current.i() + diff[0];
                var adjJ = current.j() + diff[1];
                var adjPoint = new Point(adjI, adjJ);

                if (adjI < 0 || adjI >= I || adjJ < 0 || adjJ >= J ||
                        area[adjI][adjJ] == Cell.WALL) {
                    continue;
                }

                var adjDist = minDistances.get(current) + Math.sqrt(diff[0] * diff[0] + diff[1] * diff[1]);
                // Let's see if we have found a better route
                if (minDistances.containsKey(adjPoint) && minDistances.get(adjPoint) <= adjDist) {
                    continue;
                }

                // Okay, now let's add this to the frontier
                minDistances.put(adjPoint, adjDist);
                backlinks.put(adjPoint, current);
                var adjWeight =
                        distWeight * adjDist +
                                heuristicWeight * heuristic(adjPoint, problem.goal());
                frontier.add(new FrontierNode(adjWeight, adjPoint));
            }
        }

        if (Double.isFinite(cost)) {
            // Let's unwind all backlinks
            var current = problem.goal();
            while (!current.equals(problem.start())) {
                path.add(current);
                current = backlinks.get(current);
            }
        }

        return new SearchSolution(cost, path, expanded);
    }

    private double heuristic(Point current, Point goal) {
        return current.euclidianTo(goal);
    }

    private record FrontierNode (
            double weight,
            Point point
    ) {
    }
}
