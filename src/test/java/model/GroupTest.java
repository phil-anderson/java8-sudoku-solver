package model;

import org.junit.Before;
import org.junit.Test;

import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class GroupTest
{
    private Group groupUnderTest;

    @Before
    public void setUp() throws Exception
    {
        groupUnderTest = new Group();
    }

    @Test
    public void canAddNineCellsWithoutThrowing()
    {
        addTestCells(9, TestCellFactory.DO_NOTHING);
    }

    @Test(expected = IllegalStateException.class)
    public void addingMoreThanNineCellsThrows()
    {
        addTestCells(10, TestCellFactory.DO_NOTHING);
    }

    @Test
    public void isSolvedReturnsTrueOnlyIfAllCellsAreSolved()
    {
        addTestCells(8, TestCellFactory.SOLVE);
        assertThat(groupUnderTest.isSolved(), is(true));

        groupUnderTest.addCell(new Cell());
        assertThat(groupUnderTest.isSolved(), is(false));
    }

    @Test
    public void canGetAllCellsContainingAValue()
    {
        addTestCells(5, TestCellFactory.DO_NOTHING);
        Cell solvedCell = new Cell();
        solvedCell.setSolution(1);
        assertThat(groupUnderTest.cellsThatCouldBe(9), hasSize(5));
    }

    @Test
    public void canDetermineIfAGivenValueHAsBeenSolved()
    {
        addTestCells(8, TestCellFactory.DO_NOTHING);
        addTestCells(1, TestCellFactory.SOLVE);
        assertThat(groupUnderTest.hasSolved(1), is(true));
        assertThat(groupUnderTest.hasSolved(2), is(false));
    }

    @Test
    public void canGetAStreamOfUnsolvedCells()
    {
        addTestCells(6, TestCellFactory.DO_NOTHING);
        addTestCells(3, TestCellFactory.SOLVE);
        Stream<Cell> unsolvedCells = groupUnderTest.unsolvedCells();
        assertThat((int) unsolvedCells.count(), is(6));
    }

    @Test
    public void canAddChangeListeners()
    {
        addTestCells(8, TestCellFactory.DO_NOTHING);
        FlaggingGroupChangeListener listener = new FlaggingGroupChangeListener();
        FlaggingGroupChangeListener[] listeners = new FlaggingGroupChangeListener[] { listener };
        groupUnderTest.addChangeListeners(listeners);
        addTestCells(1, TestCellFactory.SOLVE);
        assertThat(listener.wasCalled, is(true));
    }

    private void addTestCells(int numberOfCellsToAdd, BiConsumer<Cell, Integer> postProcessCell)
    {
        TestCellFactory.addTestCellsToGroup(numberOfCellsToAdd, groupUnderTest, postProcessCell);
    }

    private static class FlaggingGroupChangeListener implements IGroupChangeListener
    {
        boolean wasCalled;

        @Override
        public void handleGroupChange(Group changedGroup, Cell changedCell)
        {
            wasCalled = true;
        }
    }

}
