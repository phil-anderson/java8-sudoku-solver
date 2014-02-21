package main;

import model.Grid;
import solvers.ISolver;
import solvers.Level1Solver;
import solvers.Level2Solver;
import solvers.Level3Solver;
import utils.GridTextParser;

import java.util.Arrays;

public class SudokuSolver
{
    private static final ISolver[] solvers = new ISolver[] {
        new Level1Solver(),
        new Level2Solver(),
        new Level3Solver()
    };

    private Grid grid;

    public SudokuSolver(int solveLevel)
    {
        if (solveLevel < 1 || solveLevel > solvers.length) throw new IllegalArgumentException("Solve level must be between 1 and " + solvers.length);

        grid = new Grid();
        grid.addChangeListeners(Arrays.copyOf(solvers, solveLevel));
    }

    public boolean solveGridText(String gridText)
    {
        GridTextParser parser = new GridTextParser(grid);
        parser.parseString(gridText);
        return grid.isSolved();
    }

    public Grid getGrid()
    {
        return grid;
    }
}

