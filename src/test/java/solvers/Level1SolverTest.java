package solvers;

import model.Cell;
import model.Group;
import model.TestCellFactory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Level1SolverTest
{
    @Test
    public void removesValueOfSolvedCellFromAllOtherCells()
    {
        Group group = new Group();
        TestCellFactory.addTestCellsToGroup(8, group, TestCellFactory.DO_NOTHING);

        Cell solvedCell = new Cell();
        solvedCell.setSolution(1);

        ISolver solver = new Level1Solver();
        solver.solve(group, solvedCell);

        boolean couldAnyUnsolvedCellsBeOne = group.unsolvedCells().anyMatch((cell) -> cell.couldSolutionBe(1));
        assertThat(couldAnyUnsolvedCellsBeOne, is(false));
    }
}
