package solvers;

import model.Cell;
import model.Group;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class CellTallyTest
{
    private Cell cellA;
    private Cell cellB;
    private CellTally cellTallyUnderTest;

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

        Group group = new Group();
        group.addCell(cellA);
        group.addCell(cellA);
        group.addCell(cellB);

        cellTallyUnderTest = new CellTally(group);
    }

    @Test
    public void keepsATallyOfObjectsAdded() throws Exception
    {
        assertThat(cellTallyUnderTest.getTallyOf(cellA), is(2));
        assertThat(cellTallyUnderTest.getTallyOf(cellB), is(1));
    }

    @Test
    public void tallyForANonexistentObjectIsZero() throws Exception
    {
        Cell nonexistentCell = new Cell();
        assertThat(cellTallyUnderTest.getTallyOf(nonexistentCell), is(0));
    }

    @Test
    public void canGetListOfCellsThatHaveSameNumberOfIdenticalCellsAsPossibleValues()
    {
        List<Cell> returnedCells = cellTallyUnderTest.getCellsWithSameTallyAsNumberOfPossibleValues();
        assertThat(returnedCells, hasSize(1));
        assertThat(returnedCells, contains(cellA));
    }
}
