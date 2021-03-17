package com.efim.astar.visual;

import com.efim.astar.algo.AStar;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        var filename = "astar-1";
        var weights = List.of(0, 1, 2, 10);

        var input = new File("src/main/resources/%s.png".formatted(filename));
        var problem = new ProblemGeneratorFromPng().generate(input);

        for (Integer weight : weights) {
            var start = System.nanoTime();
            var output = new File("src/main/resources/%s-solution-w%d.png".formatted(filename, weight));
            var solution = new AStar(weight).solve(problem);
            new SolutionVisualiserToPng().visualise(output, problem, solution);
            System.out.println("Weight: %d. Finished in %d ms. Result: %s".formatted(
                    weight,
                    TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start),
                    output.toURI()
            ));
        }
    }
}
