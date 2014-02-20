package model;

import java.util.ArrayList;
import java.util.List;

public class Cell
{
    private List<Integer> possibleValues = new ArrayList<>();
    private List<ICellChangeListener> listeners = new ArrayList<>();

    public Cell()
    {
        for(int value = 1; value <= 9; value++) possibleValues.add(value);
    }

    public boolean isSolved()
    {
        return possibleValues.size() == 1;
    }

    public void setSolution(int value)
    {
        if(isSolved()) return;

        possibleValues.clear();
        possibleValues.add(value);
        notifyChangeListeners();
    }

    public void removePossibleValue(Integer valueToRemove)
    {
        if(!isSolved() && possibleValues.remove(valueToRemove))
        {
            notifyChangeListeners();
        }
    }

    public void removeAllPossibleValuesIn(Cell referenceCell)
    {
        if(!isSolved() && possibleValues.removeAll(referenceCell.possibleValues))
        {
            notifyChangeListeners();
        }
    }

    public int getSolution()
    {
        if(!isSolved()) throw new IllegalStateException("Can't get solution of unsolved cell");

        return possibleValues.get(0);
    }

    public boolean couldSolutionBe(int value)
    {
        return possibleValues.stream().anyMatch((i) -> i == value);
    }

    public int numberOfPossibleValues()
    {
        return this.possibleValues.size();
    }

    public void addChangeListener(ICellChangeListener listener)
    {
        listeners.add(listener);
    }

    private void notifyChangeListeners()
    {
        for(ICellChangeListener listener : listeners) listener.handleCellChange(this);
    }

    @Override
    public boolean equals(Object other)
    {
        if(other == this) return true;
        if(!(other instanceof Cell)) return false;

        Cell otherCell = (Cell) other;
        return this.possibleValues.equals(otherCell.possibleValues);
    }

    @Override
    public int hashCode()
    {
        return possibleValues.hashCode();
    }

    @Override
    public String toString()
    {
        return isSolved() ? "" + getSolution() : "";
    }
}
