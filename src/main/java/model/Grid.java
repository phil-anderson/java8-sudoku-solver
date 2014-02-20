package model;

import solvers.ISolver;
import utils.Iterate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Grid
{
    private static int GRID_SIZE = 9;
    private static int CELL_COUNT = GRID_SIZE * GRID_SIZE;

    private Cell[] cells = new Cell[CELL_COUNT];
    private Group[] rows = new Group[GRID_SIZE];
    private Group[] columns = new Group[GRID_SIZE];
    private Group[] squares = new Group[GRID_SIZE];
    private List<Group> allGroups = new ArrayList<>();

    public Grid()
    {
        buildGroups(rows);
        buildGroups(columns);
        buildGroups(squares);
        buildCells();
    }

    public void setSolutionAt(int row, int column, int answer)
    {
        getCellAt(row, column).setSolution(answer);
    }

    public Cell getCellAt(int row, int column)
    {
        return cells[cellsIndex(row, column)];
    }

    public boolean isSolved()
    {
        return Stream.of(cells).allMatch(Cell::isSolved);
    }

    public void addChangeListeners(ISolver[] solvers)
    {
        Consumer<Group> addListeners = (group) -> group.addChangeListeners(solvers);
        groupStream().forEach(addListeners);
    }

    public Stream<Group> groupStream()
    {
        return allGroups.stream();
    }

    private void buildGroups(Group[] groups)
    {
        for(int i = 0; i < GRID_SIZE; i++)
        {
            groups[i] = new Group();
            allGroups.add(groups[i]);
        }
    }

    private void buildCells()
    {
        Iterate.forEachRowColumn(this::buildCell);
    }

    private void buildCell(int row, int column)
    {
        Cell cell = new Cell();
        rows[row].addCell(cell);
        columns[column].addCell(cell);
        cells[cellsIndex(row, column)] = cell;
        squares[squaresIndex(row, column)].addCell(cell);
    }

    private int cellsIndex(int row, int column)
    {
        return row * GRID_SIZE + column;
    }

    private int squaresIndex(int row, int column)
    {
        return (column / 3) + (row / 3) * 3;
    }
}
