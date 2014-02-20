package solvers;

import model.Cell;
import model.Group;

// If a cell is solved, remove the solution value from the list of possible values for all other cells in the group.
public class Level1Solver extends AbstractSolver
{

    @Override
    public void solve(Group group, Cell cell)
    {
        if(cell.isSolved())
        {
            int value = cell.getSolution();
            group.unsolvedCells().forEach((otherCell) -> otherCell.removePossibleValue(value));
        }
    }
}
