package utils;

import model.Cell;
import model.Grid;

import java.io.PrintStream;

public class PrettyPrinter
{
    public void print(Grid grid, PrintStream outputStream)
    {
        outputStream.print(formatGrid(grid));
    }

    public String formatGrid(Grid grid)
    {
        StringBuilder result = new StringBuilder();
        for(int row = 0; row < 9; row++) appendRow(result, grid, row);
        return result.toString();
    }

    private void appendRow(StringBuilder builder, Grid grid, int row)
    {
        for(int column = 0; column < 9; column++)
        {
            appendCell(builder, grid.getCellAt(row, column), column);
        }
        builder.append("\n");
        if(row % 3 == 2) builder.append("\n");
    }

    private void appendCell(StringBuilder builder, Cell cell, int column)
    {
        if(column != 0 && column % 3 == 0) builder.append(" ");
        builder.append(cell);
    }
}
