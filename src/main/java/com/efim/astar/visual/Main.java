package com.efim.astar.visual;

import com.efim.astar.algo.AStar;
import com.efim.astar.algo.Point;
import com.efim.astar.heuristic.ChebyshevDistance;
import com.efim.astar.heuristic.DistanceWithoutObstacles;
import com.efim.astar.heuristic.EuclideanDistance;
import com.efim.astar.heuristic.GarbageDistance;
import com.efim.astar.heuristic.InconsistentGarbageDistance;
import com.efim.astar.heuristic.ManhattanDistance;
import com.efim.astar.heuristic.RandomlyReducedEuclideanDistance;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
        var filename = "astar-5";
        var allParams = List.of(
                new SearchParams("astar", 1, 1),
                new SearchParams("astar2", 1, 2),
                new SearchParams("greedy", 0, 1)
        );

        var heuristics = List.of(
                new HeuristicContainer("euclidean", new EuclideanDistance()),
                new HeuristicContainer("manhattan", new ManhattanDistance()),
                new HeuristicContainer("chebyshev", new ChebyshevDistance()),
                new HeuristicContainer("without-obstacles", new DistanceWithoutObstacles()),
                new HeuristicContainer("eucl-reduced", new RandomlyReducedEuclideanDistance()),
                new HeuristicContainer("garbage", new GarbageDistance(540.345)),
                new HeuristicContainer("inconsistentGarbage", new InconsistentGarbageDistance(540.345))
        );

        var input = new File("src/main/resources/%s.png".formatted(filename));
        var problem = new ProblemGeneratorFromPng().generate(input);

        for (var params : allParams) {
            for (HeuristicContainer heuristic : heuristics) {
                var start = System.nanoTime();
                var solution = new AStar(params.distWeight, params.heuristicWeight, heuristic.heuristic).solve(problem);
                var timeNanos = System.nanoTime() - start;
                var output = new File("src/main/resources/%s-solution-%s-%s.png".formatted(filename, params.name, heuristic.name));
                new SolutionVisualiserToPng().visualise(output, problem, solution);
                System.out.printf(
                        "Search: %s. Cost: %.3f. Heuristic: %s. Expanded: %d. Time %d ms%n",
                        params.name,
                        solution.cost(),
                        heuristic.name,
                        solution.expanded().size(),
                        TimeUnit.NANOSECONDS.toMillis(timeNanos)
                );
            }
        }
    }

    private record HeuristicContainer(
            String name,
            BiFunction<Point, Point, Double> heuristic
    ) {
    }

    private record SearchParams(
            String name,
            double distWeight,
            double heuristicWeight
    ) {
    }

}
