package solvers;

import model.Cell;
import model.Group;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Level3SolverTest
{
    private Group groupUnderTest;
    private Cell cellA;
    private Cell cellB;

    @Before
    public void setUp() throws Exception
    {
        Cell toBeRemoved = new Cell();
        toBeRemoved.removePossibleValue(1);
        toBeRemoved.removePossibleValue(2);

        cellA = new Cell();
        cellA.removeAllPossibleValuesIn(toBeRemoved); // cellA has [1, 2]

        toBeRemoved.removePossibleValue(3);
        cellB = new Cell();
        cellB.removeAllPossibleValuesIn(toBeRemoved); // cellB has [1, 2, 3]

        groupUnderTest = new Group();
        groupUnderTest.addCell(cellA);
        groupUnderTest.addCell(cellA);
        groupUnderTest.addCell(cellB);
    }

    @Test
    public void removesValesOfCellsWhichMustBelongToOtherCells()
    {
        ISolver solver = new Level3Solver();
        solver.solve(groupUnderTest, null);

        assertThat(cellA.numberOfPossibleValues(), is(2)); // Should be unchanged
        assertThat(cellB.isSolved(), is(true));
        assertThat(cellB.getSolution(), is(3));
    }
}
