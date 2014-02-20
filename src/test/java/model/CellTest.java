package model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class CellTest
{
    private Cell cellUnderTest;

    @Before
    public void setUp() throws Exception
    {
        cellUnderTest = new Cell();
    }

    @Test
    public void canHaveASolution() throws Exception
    {
        cellUnderTest.setSolution(3);
        assertThat(cellUnderTest.isSolved(), is(true));
        assertThat(cellUnderTest.getSolution(), is(3));
    }

    @Test
    public void removePossibleValueDoesNothingIfAlreadySolved()
    {
        cellUnderTest.setSolution(1);
        cellUnderTest.removePossibleValue(1);
        assertThat(cellUnderTest.getSolution(), is(1));
    }

    @Test
    public void removingAllButOnePossibleValueSolvesTheCell() throws Exception
    {
        assertThat(cellUnderTest.isSolved(), is(false));
        for(int i = 1; i < 9; i++)
        {
            cellUnderTest.removePossibleValue(i);
        }
        assertThat(cellUnderTest.isSolved(), is(true));
    }

    @Test
    public void canRemoveAllPossibleValuesInOneCellFromAnother()
    {
        Cell otherCell = new Cell();
        otherCell.removePossibleValue(5);
        cellUnderTest.removeAllPossibleValuesIn(otherCell);
        assertThat(cellUnderTest.getSolution(), is(5));
    }

    @Test
    public void doesNothingIfYouSolveACellThatsAlreadySolved()
    {
        cellUnderTest.setSolution(3);
        cellUnderTest.setSolution(1);
        assertThat(cellUnderTest.getSolution(), is(3));
    }

    @Test(expected=IllegalStateException.class)
    public void throwsIfYouGetSolvedValueOfUnsolvedCell()
    {
        cellUnderTest.getSolution();
    }

    @Test
    public void notifiesListenersWhenSolved()
    {
        FlaggingCellChangeListener listener = new FlaggingCellChangeListener();
        Cell cell = new Cell();
        cell.addChangeListener(listener);

        cell.setSolution(3);
        assertThat(listener.wasCalled, is(true));
    }

    @Test
    public void cellsWithDifferentPossibleValuesAreNotEqual()
    {
        Cell otherCell = new Cell();
        otherCell.setSolution(5);
        assertThat(cellUnderTest, is(not(otherCell)));
    }

    @Test
    public void cellsWithSamePossibleValuesAreEqual()
    {
        Cell otherCell = new Cell();
        assertThat(cellUnderTest, is(otherCell));
    }

    @Test
    public void canQueryPossibleValues()
    {
        cellUnderTest.removePossibleValue(3);
        assertThat(cellUnderTest.numberOfPossibleValues(), is(8));
        assertThat(cellUnderTest.couldSolutionBe(2), is(true));
        assertThat(cellUnderTest.couldSolutionBe(3), is(false));
    }

    private static class FlaggingCellChangeListener implements ICellChangeListener
    {
        boolean wasCalled;

        @Override
        public void handleCellChange(Cell changedCell)
        {
            wasCalled = true;
        }
    }
}
