package solvers;

import model.Grid;
import model.Group;

public class SolutionValidator
{
    public static boolean validate(Grid grid)
    {
        return grid.groupStream().allMatch(SolutionValidator::isGroupValid);
    }

    private static boolean isGroupValid(Group group)
    {
        for(int i = 1; i <= 9; i++)
        {
            if(!group.hasSolved(i)) return false;
        }
        return true;
    }
}
