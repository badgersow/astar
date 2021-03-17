package com.efim.astar.visual;

import com.efim.astar.algo.SearchProblem;

import java.io.File;

public interface ProblemGenerator {
    SearchProblem generate(File input);
}
