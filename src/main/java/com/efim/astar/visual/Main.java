package com.efim.astar.visual;

import com.efim.astar.algo.GothicSearchAlgorithm;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        var filename = "astar-1";

        var input = new File("src/main/resources/%s.png".formatted(filename));
        var output = new File("src/main/resources/%s-solution.png".formatted(filename));

        var start = System.nanoTime();
        var problem = new ProblemGeneratorFromPng().generate(input);
        var solution = new GothicSearchAlgorithm().solve(problem);
        new SolutionVisualiserToPng().visualise(output, problem, solution);

        System.out.println("Finished in %d ms. Result: %s".formatted(
                TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start),
                output.toURI()
        ));
    }
}
