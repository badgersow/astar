package com.efim.astar.visual;

import com.efim.astar.algo.AStar;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        var filename = "astar-5";
        var weights = List.of(
                new SearchParams("dijkstra", 1, 0),
                new SearchParams("astar", 1, 1),
                new SearchParams("astar2", 1, 2),
                new SearchParams("astar5", 1, 5),
                new SearchParams("greedy", 0, 1)
        );

        var input = new File("src/main/resources/%s.png".formatted(filename));
        var problem = new ProblemGeneratorFromPng().generate(input);

        for (var params : weights) {
            var start = System.nanoTime();
            var solution = new AStar(params.distWeight, params.heuristicWeight).solve(problem);
            var timeNanos = System.nanoTime() - start;
            var output = new File("src/main/resources/%s-solution-%s.png".formatted(filename, params.name));
            new SolutionVisualiserToPng().visualise(output, problem, solution);
            System.out.printf(
                    "Search: %s. Cost: %.3f. Expanded: %d. Time %d ms. Result: %s%n",
                    params.name,
                    solution.cost(),
                    solution.expanded().size(),
                    TimeUnit.NANOSECONDS.toMillis(timeNanos),
                    output.toURI()
            );
        }
    }

    private record SearchParams(
            String name,
            double distWeight,
            double heuristicWeight
    ) {
    }

}
