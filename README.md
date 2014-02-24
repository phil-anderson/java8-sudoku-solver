Java 8 Sudoku Solver
====================

I wanted to explore some of the features in Java8 (mainly lambda expressions and Streams) and thought a Soduko
solver might be an interesting trial project with a few different query style operations to write.

It was a lot of fun!

The approach breaks down to a subscriber/subscribe model where cells notify teh groups they are in when they change.
The groups notify the grid, and it notifies any solving strategies that are registered with it. As these make changes
to the model, further events are fired until ultimately there's nothing left to do.

I wrote three solving strategies and haven't found a sudoku that they can't solve between them so far. I have avoided
adding a "guess" strategy for when no further changes can be made, as I just don;t like the idea of having to guess in
order to solve the puzzle.

If you're interested, there are comments in each strategy class explaining what they do.

