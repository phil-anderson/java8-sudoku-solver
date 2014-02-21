package utils;

import model.Grid;

// Will look at first nine columns of first nine lines. Numerics in those positions are entered as solutions anything
// else is considered an unsolved cell
public class GridTextParser
{
    private Grid grid;

    public GridTextParser(Grid grid)
    {
        this.grid = grid;
    }

    public Grid parseString(String gridString)
    {
        String[] lines = gridString.split("\n");
        return parseLines(lines);
    }

    private Grid parseLines(String... lines)
    {
        for(int row = 0; row < 9; row++)
        {
            String text = lines.length > row ? lines[row] : "";
            parseRow(row, text);
        }
        return grid;
    }

    private void parseRow(int row, String line)
    {
        for(int column = 0; column < 9; column++)
        {
            char character = column < line.length() ? line.charAt(column) : ' ';
            if(Character.isDigit(character)) grid.setSolutionAt(row, column, character - 48);
        }
    }
}
