package com.efim.astar.algo;

import java.util.ArrayList;

public class GothicSearchAlgorithm implements SearchAlgorithm{
    @Override
    public SearchSolution solve(SearchProblem problem) {
        var path = new ArrayList<Point>();
        var expanded = new ArrayList<Point>();

        Cell[][] area = problem.area();
        var diffI = problem.goal().i() - problem.start().i();
        var diffJ = problem.goal().j() - problem.start().j();
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {
                var currentDiffI = i - problem.start().i();
                var currentDiffJ = j - problem.start().j();

                expanded.add(new Point(i, j));
                if (Math.round((double) currentDiffI / diffI) ==
                        Math.round((double) currentDiffJ / diffJ)) {
                    path.add(new Point(i, j));
                }
            }
        }

        return new SearchSolution(path, expanded);
    }
}
