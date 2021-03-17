package com.efim.astar.visual;

import com.efim.astar.algo.SearchProblem;
import com.efim.astar.algo.SearchSolution;

import java.io.File;

public interface SolutionVisualiser {
    File visualise(SearchProblem problem, SearchSolution solution);
}
