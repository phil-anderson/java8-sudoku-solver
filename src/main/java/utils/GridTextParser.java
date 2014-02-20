package utils;

import model.Grid;

// Will look at first nine columns of first nine lines. Numerics in those positions are entered as solutions anything
// else is an considered an unsolved cell
public class GridTextParser
{
    private Grid grid;

    public GridTextParser(Grid grid)
    {
        this.grid = grid;
    }

    // TODO: anderp - Refactor out cyclomatic complexity
    public Grid parseStrings(String... lines)
    {
        for(int row = 0; row < 9; row++)
        {
            String line = lines.length > row ? lines[row] : "";
            for(int column = 0; column < 9; column++)
            {
                char character = line.length() > column ? line.charAt(column) : ' ';
                if(Character.isDigit(character))
                {
                    grid.setSolutionAt(row, column, character - 48);
                }
            }
        }
        return grid;
    }
}
