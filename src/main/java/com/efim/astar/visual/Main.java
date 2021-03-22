package com.efim.astar.visual;

import com.efim.astar.algo.AStar;
import com.efim.astar.algo.Point;
import com.efim.astar.heuristic.EuclideanDistance;

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
                new SearchParams("astar5", 1, 5),
                new SearchParams("greedy", 0, 1)
        );

        var heuristics = List.of(
                new HeuristicContainer("eucledian", new EuclideanDistance())
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
