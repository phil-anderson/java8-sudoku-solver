package utils;

import model.Grid;

public class PrettyPrinter
{
    // TODO: anderp - Refactor out cyclomatic complexity
    public static String prettyPrint(Grid grid)
    {
        StringBuilder result = new StringBuilder();
        for(int row = 0; row < 9; row++)
        {
            for(int column = 0; column < 9; column++)
            {
                if(column != 0 && column % 3 == 0)
                {
                    result.append(" ");
                }
                result.append(grid.getCellAt(row, column));
            }
            result.append("\n");
            if(row != 0 && row % 3 == 2)
            {
                result.append("\n");
            }
        }
        return result.toString();
    }
}
