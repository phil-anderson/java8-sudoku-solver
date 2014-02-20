package solvers;

import model.Cell;
import model.Group;

public abstract class AbstractSolver implements ISolver
{
    @Override
    public void handleGroupChange(Group changedGroup, Cell changedCell)
    {
        if(!changedGroup.isSolved())
        {
            solve(changedGroup, changedCell);
        }
    }
}
