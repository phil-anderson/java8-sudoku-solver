package model;

import java.util.function.BiConsumer;

public class TestCellFactory
{
    public static final BiConsumer<Cell, Integer> DO_NOTHING = (cell, value) -> {};
    public static final BiConsumer<Cell, Integer> SOLVE = Cell::setSolution;

    public static void addTestCellsToGroup(int numberOfCellsToAdd, Group group, BiConsumer<Cell, Integer> postProcess)
    {
        for(int i = 1; i <= numberOfCellsToAdd; i++)
        {
            addTestCell(group, postProcess, i);
        }
    }

    private static Cell addTestCell(Group group, BiConsumer<Cell, Integer> postProcessCell, int value)
    {
        Cell cell = new Cell();
        cell.addChangeListener(group);
        postProcessCell.accept(cell, value);
        group.addCell(cell);
        return cell;
    }
}