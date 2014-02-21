package utils;

import model.Cell;
import model.Grid;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GridTextParserTest
{
    private Grid grid;
    private GridTextParser gridTextParserUnderTest;
    private String inputString;

    @Before
    public void setUp()
    {
        grid = new Grid();
        gridTextParserUnderTest = new GridTextParser(grid);
        inputString = "....1....\n" +
                      ".2.....3.\n" +
                      "..4...5..\n" +
                      "6.......7\n" +
                      "...8.....\n" +
                      ".....9...\n" +
                      "1.......2\n" +
                      ".3.......\n" +
                      ".....4...\n";
    }

    @Test
    public void canParseBoardFromStrings()
    {
        gridTextParserUnderTest.parseString(inputString);

        assertNotSolved(3, 5);
        assertNotSolved(7, 8);

        assertSolved(0, 4, 1);
        assertSolved(8, 5, 4);
    }

    private void assertSolved(int row, int column, int expectedValue)
    {
        Cell cell = grid.getCellAt(row, column);
        assertThat(cell.isSolved(), is(true));
        assertThat(cell.getSolution(), is(expectedValue));
    }

    private void assertNotSolved(int row, int column)
    {
        Cell cell = grid.getCellAt(row, column);
        assertThat(cell.isSolved(), is(false));
    }
}
