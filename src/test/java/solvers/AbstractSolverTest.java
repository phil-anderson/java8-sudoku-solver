package solvers;

import model.Cell;
import model.Group;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AbstractSolverTest
{
    @Test
    public void solveMethodShouldOnlyBeCalledOnGroupChangeIfGroupIsntAlreadySolved()
    {
        Cell cell = new Cell();
        cell.setSolution(5);

        Group group = new Group();
        group.addCell(cell);

        Solver solver = new Solver();
        solver.handleGroupChange(group, cell);
        assertThat(solver.solveWasCalled, is(false));

        group.addCell(new Cell());
        solver.handleGroupChange(group, cell);
        assertThat(solver.solveWasCalled, is(true));
    }

    private static class Solver extends AbstractSolver
    {
        private boolean solveWasCalled;

        @Override
        public void solve(Group group, Cell cell)
        {
            solveWasCalled = true;
        }
    }
}
