package solvers;

import model.Cell;
import model.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CellTally
{
    private Map<Cell, Integer> backingMap = new HashMap<>();

    public CellTally(Group group)
    {
        group.unsolvedCells().forEach(this::add);
    }

    public int getTallyOf(Cell cell)
    {
        Integer count = backingMap.get(cell);
        return count != null ? count : 0;
    }

    public int size()
    {
        return backingMap.size();
    }

    // Returns cells where the number of identical cells is the same as the number of possible values in the cell.
    // E.g. Two cells both have possible values [1, 2] or three cells that all have possible values [4, 5, 6]
    // If only two cells had [4, 5, 6] that wouldn't be a match.
    public List<Cell> getCellsWithSameTallyAsNumberOfPossibleValues()
    {
        List<Cell> result = new ArrayList<>();

        for(Map.Entry<Cell, Integer> entry : backingMap.entrySet())
        {

            Cell cell = entry.getKey();
            int numberOfIdenticalCells = entry.getValue();
            if(cell.numberOfPossibleValues() == numberOfIdenticalCells)
            {
                result.add(cell);
            }
        }
        return result;
    }

    private void add(Cell cell)
    {
        int newValue = getTallyOf(cell) + 1;
        backingMap.put(cell, newValue);
    }
}
