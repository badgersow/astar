package com.efim.astar.visual;

import com.efim.astar.algo.GothicSearchAlgorithm;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        var filename = "astar-1";

        var input = new File("src/main/resources/%s.png".formatted(filename));
        var output = new File("src/main/resources/%s-solution.png".formatted(filename));
        var problem = new ProblemGeneratorFromPng().generate(input);
        var solution = new GothicSearchAlgorithm().solve(problem);

        System.out.println("Done");
    }
}
