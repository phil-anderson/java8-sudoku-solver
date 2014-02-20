package solvers;

import model.Cell;
import model.Group;

import java.util.List;

// Looks for multiple cells in the same group that are equal (i.e. that have the same list of possible values).
// For each grouping of equal cells, it checks to see if there are the same number of cells in the grouping as
// there is number of possible values.
//
// So... If only two cells had the possible values 3 and 4 (and nothing else) then that would be a match as
// there are two cells with the same two possible values. One of them must be the 3, and the other must be the 4,
// so 3 and 4 can be removed as possibilities from all other cells in the group.
public class Level3Solver extends AbstractSolver
{
    public void solve(Group changedGroup, Cell changedCell)
    {
        CellTally cellTally = new CellTally(changedGroup);
        List<Cell> talliedCells = cellTally.getCellsWithSameTallyAsNumberOfPossibleValues();
        if(cellTally.size() > 1)
        {
            for(Cell talliedCell : talliedCells)
            {
                changedGroup.unsolvedCells().filter((cell) -> !cell.equals(talliedCell))
                                            .forEach((cell) -> cell.removeAllPossibleValuesIn(talliedCell));
            }
        }
    }
}
