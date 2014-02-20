package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Group implements ICellChangeListener
{
    private List<IGroupChangeListener> changeListeners = new ArrayList<>();

    private List<Cell> cells = new ArrayList<>();

    public void addCell(Cell cell)
    {
        if(cells.size() >= 9) throw new IllegalStateException("Group can only have 9 cells");

        cells.add(cell);
        cell.addChangeListener(this);
    }

    public boolean isSolved()
    {
        return cells.stream().allMatch(Cell::isSolved);
    }

    public List<Cell> cellsThatCouldBe(int value)
    {
        return cells.stream().filter((cell) -> cell.couldSolutionBe(value))
                             .collect(Collectors.toList());
    }

    public void addChangeListeners(IGroupChangeListener[] listeners)
    {
        Stream.of(listeners).forEach(changeListeners::add);
    }

    public boolean hasSolved(int value)
    {
        return cells.stream().filter(Cell::isSolved)
                             .anyMatch((cell) -> cell.getSolution() == value);
    }

    public Stream<Cell> solvedCells()
    {
        return cells.stream().filter(Cell::isSolved);
    }

    public Stream<Cell> unsolvedCells()
    {
        return cells.stream().filter((cell) -> !cell.isSolved());
    }

    @Override
    public void handleCellChange(Cell changedCell)
    {
        changeListeners.stream().forEach((listener) -> listener.handleGroupChange(this, changedCell));
    }
}
