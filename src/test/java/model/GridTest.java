package model;

import org.junit.Before;
import org.junit.Test;
import solvers.ISolver;
import utils.GridTextParser;
import utils.Iterate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GridTest
{
    private Grid grid;

    @Before
    public void setUp() throws Exception
    {
        grid = new Grid();
    }

    @Test
    public void newBoardIsFullyPopulatedWithUnsolvedCells()
    {
        Iterate.forEachRowColumn(this::assertNotSolved);
    }

    @Test
    public void canSetCellAsSolved() throws Exception
    {
        assertNotSolved(5, 5);

        grid.setSolutionAt(5, 5, 5);
        assertSolved(5, 5);
    }

    @Test
    public void isSolvedReturnsTrueIfAllCellsSolved()
    {
        assertThat(grid.isSolved(), is(false));
        makeSolvedGrid();
        assertThat(grid.isSolved(), is(true));
    }

    @Test
    public void canAddSolversToAllItsGroups()
    {
        FlaggingSolver solver = new FlaggingSolver();
        FlaggingSolver[] solvers = new FlaggingSolver[] { solver };
        grid.addChangeListeners(solvers);
        Iterate.forEachRowColumn((row, column) -> {
            solver.wasCalled = false;
            grid.setSolutionAt(row, column, 1);
            assertThat(solver.wasCalled, is(true));
        });
    }

    private void assertSolved(Integer row, Integer column)
    {
        assertThat(grid.getCellAt(row, column).isSolved(), is(true));
    }

    private void assertNotSolved(Integer row, Integer column)
    {
        assertThat(grid.getCellAt(row, column).isSolved(), is(false));
    }

    private void makeSolvedGrid()
    {
        GridTextParser parser = new GridTextParser(grid);
        parser.parseString("123456789\n" +
                           "456789123\n" +
                           "789123456\n" +
                           "234567891\n" +
                           "567891234\n" +
                           "891234567\n" +
                           "345678912\n" +
                           "678912345\n" +
                           "912345678\n");
    }

    private static class FlaggingSolver implements ISolver
    {
        boolean wasCalled;

        @Override
        public void solve(Group group, Cell cell)
        {
            // Do nothing
        }

        @Override
        public void handleGroupChange(Group changedGroup, Cell changedCell)
        {
            wasCalled = true;
        }
    }

}
