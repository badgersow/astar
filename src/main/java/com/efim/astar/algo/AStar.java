package com.efim.astar.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class AStar implements SearchAlgorithm{

    private int heuristicWeight;

    public AStar(int heuristicWeight) {
        this.heuristicWeight = heuristicWeight;
    }

    private static int[][] diffs = new int[][]{
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    @Override
    public SearchSolution solve(SearchProblem problem) {
        var path = new ArrayList<Point>();
        var expanded = new ArrayList<Point>();

        Cell[][] area = problem.area();
        var I = area.length;
        var J = area[0].length;

        var frontier = new PriorityQueue<FrontierNode>();
        var minDistances = new HashMap<Point, Integer>();
        var backlinks = new HashMap<Point, Point>();
        var visited = new boolean[I][J];
        var found = false;

        frontier.add(new FrontierNode(0, problem.start()));
        minDistances.put(problem.start(), 0);
        while (!frontier.isEmpty()) {
            var current = frontier.poll().point;

            if (current.equals(problem.goal())) {
                found = true;
                break;
            }

            expanded.add(current);
            visited[current.i()][current.j()] = true;

            for (int[] diff : diffs) {
                var adjI = current.i() + diff[0];
                var adjJ = current.j() + diff[1];
                var adjPoint = new Point(adjI, adjJ);

                if (adjI < 0 || adjI >= I || adjJ < 0 || adjJ >= J || area[adjI][adjJ] == Cell.WALL || visited[adjI][adjJ]) {
                    continue;
                }

                // Okay, now let's add this to the frontier
                var adjDist = minDistances.get(current) + 1;
                minDistances.put(adjPoint, adjDist);
                backlinks.put(adjPoint, current);
                var adjWeight = adjDist + heuristicWeight * heuristic(adjPoint, problem.goal());
                frontier.add(new FrontierNode(adjWeight, adjPoint));
            }
        }

        if (found) {
            // Let's unwind all backlinks
            var current = problem.goal();
            while (!current.equals(problem.start())) {
                path.add(current);
                current = backlinks.get(current);
            }
        }

        return new SearchSolution(path, expanded);
    }

    private int heuristic(Point current, Point goal) {
        return current.manhattanTo(goal);
    }

    private record FrontierNode (
            int weight,
            Point point
    ) implements Comparable<FrontierNode>{
        @Override
        public int compareTo(FrontierNode other) {
            return weight - other.weight;
        }
    }
}
