package main;

import org.junit.Test;
import solvers.SolutionValidator;
import utils.PrettyPrinter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class SudokuSolverTest
{
    @Test
    public void canSolveEasySudoku()
    {
        assertCanSolve(1, "6..7...5.",
                          "8.4......",
                          "..9..13.6",
                          ".682.57.4",
                          ".17.8.56.",
                          "5.24.783.",
                          "9.65..1..",
                          "......4.5",
                          ".2...8...");
    }

    @Test
    public void canSolveMediumSudoku()
    {
        assertCanSolve(2, "...9...82",
                          "...81.59.",
                          "..9.3.16.",
                          ".2.1.....",
                          "85..2..19",
                          ".....8.4.",
                          ".72.5.9..",
                          ".18.42...",
                          "53...9...");
    }

    @Test
    public void canSolveHardSudoku()
    {
        assertCanSolve(3, "4..6..9..",
                          "......4..",
                          ".2.35..61",
                          "...1..58.",
                          ".3.5.7.2.",
                          ".81..6...",
                          "75..61.9.",
                          "..6......",
                          "..8..2..4");
    }

    @Test
    public void canSolveEvilSudoku()
    {
        assertCanSolve(3, "5.2.6.7..",
                          "63...7.8.",
                          ".9.......",
                          "...6...9.",
                          "2.5...3.8",
                          ".8...3...",
                          ".......7.",
                          ".7.4...15",
                          "..3.1.4.2");
    }

    public void assertCanSolve(int solveLevel, String... rows)
    {
        SudokuSolver sudokuSolver = new SudokuSolver(solveLevel);
        assertThat(sudokuSolver.solveGridText(rows), is(true));

        System.out.println(PrettyPrinter.prettyPrint(sudokuSolver.getGrid()));

        boolean validSolution = SolutionValidator.validate(sudokuSolver.getGrid());
        assertThat(validSolution, is(true));
    }
}
