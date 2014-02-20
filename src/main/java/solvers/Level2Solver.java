package solvers;

import model.Cell;
import model.Group;

import java.util.List;

// Scans the group for any values that only appear in one cell. If it finds any, it sets them to
// solved with that value.
public class Level2Solver extends AbstractSolver
{
    public void solve(Group changedGroup, Cell changedCell)
    {
        for(int value = 1; value < 10; value++)
        {
            if(!changedGroup.hasSolved(value))
            {
                List<Cell> cellsWithValue = changedGroup.cellsThatCouldBe(value);
                if(cellsWithValue.size() == 1) cellsWithValue.get(0).setSolution(value);
            }
        }
    }
}
