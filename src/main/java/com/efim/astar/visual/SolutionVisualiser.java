package com.efim.astar.visual;

import com.efim.astar.algo.SearchProblem;
import com.efim.astar.algo.SearchSolution;

import java.io.File;

public interface SolutionVisualiser {
    void visualise(File output, SearchProblem problem, SearchSolution solution);
}
