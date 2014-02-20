package utils;

import java.util.function.BiConsumer;

public class Iterate
{
    public static void forEachRowColumn(BiConsumer<Integer, Integer> consumer)
    {
        for(int row = 0; row < 9; row++)
        {
            for(int column = 0; column < 9; column++)
            {
                consumer.accept(row, column);
            }
        }
    }
}
