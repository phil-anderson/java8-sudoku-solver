package solvers;

import model.Cell;
import model.Group;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Level2SolverTest
{
    @Test
    public void infersValueOfOnlyUnsolvedCell()
    {
        Group group = new Group();
        for(int i = 1; i <= 8; i++)
        {
            Cell cell = new Cell();
            cell.setSolution(i);
            group.addCell(cell);
        }
        Cell unsolvedCell = new Cell();
        group.addCell(unsolvedCell);

        ISolver solver = new Level2Solver();
        solver.solve(group, null);
        assertThat(unsolvedCell.isSolved(), is(true));
        assertThat(unsolvedCell.getSolution(), is(9));
    }
}
