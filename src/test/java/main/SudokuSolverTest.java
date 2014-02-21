package main;

import org.junit.Before;
import org.junit.Test;
import solvers.SolutionValidator;
import utils.PrettyPrinter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class SudokuSolverTest
{

    private PrettyPrinter prettyPrinter;
    private SolutionValidator solutionValidator;

    @Before
    public void setUp() throws Exception
    {
        prettyPrinter = new PrettyPrinter();
        solutionValidator = new SolutionValidator();
    }

    @Test
    public void canSolveEasySudoku()
    {
        assertCanSolve(1, "6..7...5.\n" +
                          "8.4......\n" +
                          "..9..13.6\n" +
                          ".682.57.4\n" +
                          ".17.8.56.\n" +
                          "5.24.783.\n" +
                          "9.65..1..\n" +
                          "......4.5\n" +
                          ".2...8...\n");
    }

    @Test
    public void canSolveMediumSudoku()
    {
        assertCanSolve(2, "...9...82\n" +
                          "...81.59.\n" +
                          "..9.3.16.\n" +
                          ".2.1.....\n" +
                          "85..2..19\n" +
                          ".....8.4.\n" +
                          ".72.5.9..\n" +
                          ".18.42...\n" +
                          "53...9...\n");
    }

    @Test
    public void canSolveHardSudoku()
    {
        assertCanSolve(3, "4..6..9..\n" +
                          "......4..\n" +
                          ".2.35..61\n" +
                          "...1..58.\n" +
                          ".3.5.7.2.\n" +
                          ".81..6...\n" +
                          "75..61.9.\n" +
                          "..6......\n" +
                          "..8..2..4\n");
    }

    @Test
    public void canSolveEvilSudoku()
    {
        assertCanSolve(3, "5.2.6.7..\n" +
                          "63...7.8.\n" +
                          ".9.......\n" +
                          "...6...9.\n" +
                          "2.5...3.8\n" +
                          ".8...3...\n" +
                          ".......7.\n" +
                          ".7.4...15\n" +
                          "..3.1.4.2\n");
    }

    public void assertCanSolve(int solveLevel, String rows)
    {
        SudokuSolver sudokuSolver = new SudokuSolver(solveLevel);

        boolean wasSolved = sudokuSolver.solveGridText(rows);
        prettyPrinter.print(sudokuSolver.getGrid(), System.out);

        assertThat(wasSolved, is(true));
        boolean validSolution = solutionValidator.validate(sudokuSolver.getGrid());
        assertThat(validSolution, is(true));
    }
}
