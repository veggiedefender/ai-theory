# A* Implementation for 4x4 Slide Puzzle

The 4x4 slide puzzle was described in the page about heuristics.

Write a program which implements A* with the Manhattan Distance heuristic to solve a 4x4 slide puzzle.

## File Format
The puzzle will be read in from a file called puzzle.txt using the following format:

The file should only contain the capital letters A through O (the first 15 letters of the alphabet) and a capital S to represent the space in the puzzle. Each letter should only appear once in the file, and they should be seperated by spaces or newlines (use a default Scanner in java). The sequence of letters in the file represent each row of the puzzle from left to right, top to bottom. Therefore, the first letter in the file represents the top left square of the puzzle. For example, a file which represents the example puzzle from the heuristics page would look like (Assuming A=1, B=2, C=3, etc):

```
A S C D
F B K J
E H G I
N L O M
```

That is probably the most logical way to organize the file, but if using the default scanner in java, it could also look like:

`A S C D F B K J E H G I N L O M`

THE SOLUTION SHOULD HAVE THE SPACE IN THE LOWER RIGHT CORNER, like so:

```
A B C D
E F G H
I J K L
M N O S
```

## Valid Moves
A valid move swaps a single adjacent tile with the space. On a physical 4x4 slide puzzle you can slide more than one piece at a time, but accounting for that will complicate things.

## Documentation
Standard documentation applies (javadoc). Please use variable names that make sense throughout your project. Keep formatting simple (try not to have really long lines of code).

## Final Notes/Hints
If you have taken data structures, this implementation is very similar to Dijkstra's Algorithm. Feel free to refer to the wikipedia pseudocode to try and implement the algorithm. You may implement a priority queue however you like, including using the PriorityQueue class built in to java (`java.util.PriorityQueue`). How you represent the puzzle internally and how you design your program is completely up to you. Email with questions or to arrange a meeting.
