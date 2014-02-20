package utils;

import model.Cell;
import model.Grid;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GridTextParserTest
{
    private String[] inputStrings;
    private GridTextParser gridTextParserUnderTest;

    @Before
    public void setUp()
    {
        inputStrings = new String[] { "....1....",
                                      ".2.....3.",
                                      "..4...5..",
                                      "6.......7",
                                      "...8.....",
                                      ".....9...",
                                      "1.......2",
                                      ".3.......",
                                      ".....4..." };
        gridTextParserUnderTest = new GridTextParser(new Grid());
    }

    @Test
    public void canParseBoardFromStrings()
    {
        Grid grid = gridTextParserUnderTest.parseStrings(inputStrings);

        assertSolved(grid, 0, 4, 1);
        assertNotSolved(grid, 3, 5);
        assertSolved(grid, 8, 5, 4);
        assertNotSolved(grid, 7, 8);
    }

    private void assertSolved(Grid grid, int row, int column, int expectedValue)
    {
        Cell cell = grid.getCellAt(row, column);
        assertThat(cell.isSolved(), is(true));
        assertThat(cell.getSolution(), is(expectedValue));
    }

    private void assertNotSolved(Grid grid, int row, int column)
    {
        Cell cell = grid.getCellAt(row, column);
        assertThat(cell.isSolved(), is(false));
    }
}
