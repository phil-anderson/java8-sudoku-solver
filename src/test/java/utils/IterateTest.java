package utils;

import org.junit.Test;

import java.util.stream.Stream;

public class IterateTest
{
    @Test
    public void allRowsAndColumnsShouldBeProcessed()
    {
        final Boolean[][] rowsColumns = new Boolean[9][9];
        Iterate.forEachRowColumn((row,  column) -> rowsColumns[row][column] = true);

        for(Boolean[] row : rowsColumns)
        {
            Stream.of(row).allMatch((flag) -> flag);
        }
    }

}
