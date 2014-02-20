package solvers;

import model.Cell;
import model.Group;
import model.IGroupChangeListener;

public interface ISolver extends IGroupChangeListener
{
    void solve(Group group, Cell cell);
}
