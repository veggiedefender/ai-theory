import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class holding slider puzzle data and internal methods
 * @author Jesse Li
 */
public class SliderPuzzle implements Comparable<SliderPuzzle> {
    private final String puzzle;
    private final int freeSpace;

    private int distance = Integer.MAX_VALUE;
    private int cost = Integer.MAX_VALUE;
    public final int heuristic;    

    /**
     * Constructor for creating a SliderPuzzle from a string
     * @param  newPuzzle string to base a SliderPuzzle off of
     * @return           new SliderPuzzle containing specified data
     */
    public SliderPuzzle(String newPuzzle) {
        this.puzzle = newPuzzle;
        heuristic = getManhattanDistance();
        freeSpace = getFreeSpace();
    }

    /**
     * Constructor for creating a SliderPuzzle from scanner input
     * @param  scan any scanner object (could be from network, stdin, disk, etc.)
     * @return      new SliderPuzzle containing data retrieved from scanner
     */
    public SliderPuzzle(Scanner scan) {
        StringBuilder puzzle = new StringBuilder(16);
        for (int row = 0; row < 16; row++) {
            puzzle.append(scan.next());
        }
        this.puzzle = puzzle.toString();
        heuristic = getManhattanDistance();
        freeSpace = getFreeSpace();
    }

    public void setDistance(int distance) {
        this.distance = distance;
        this.cost = this.distance + this.heuristic;
    }

    public int getDistance() {
        return distance;
    }

    public int getCost() {
        return cost;
    }

    public ArrayList<SliderPuzzle> getNeighbors() {
        ArrayList<SliderPuzzle> neighbors = new ArrayList<SliderPuzzle>();
        // left
        if (this.freeSpace % 4 != 0) {
            neighbors.add(new SliderPuzzle(swap(freeSpace - 1)));
        }

        // right
        if ((this.freeSpace + 1) % 4 != 0) {
            neighbors.add(new SliderPuzzle(swap(freeSpace + 1)));
        }

        // up
        if (this.freeSpace >= 4) {
            neighbors.add(new SliderPuzzle(swap(freeSpace - 4)));
        }

        // down
        if (this.freeSpace <= 11) {
            neighbors.add(new SliderPuzzle(swap(freeSpace + 4)));
        }

        return neighbors;
    }

    private String swap(int target) {
        char[] chars = this.puzzle.toCharArray();
        char temp = chars[target];
        chars[target] = chars[this.freeSpace];
        chars[this.freeSpace] = temp;
        return new String(chars);
    }

    /**
     * Calculate total manhattan distance from the solution of each piece
     * @return total manhattan distance
     */
    private int getManhattanDistance() {
        int total = 0;
        for (int i = 0; i < this.puzzle.length(); i++) {
            char piece = puzzle.charAt(i);
            if (piece == 'S') {
                // apparently it's important not to count the blank
                // in the manhattan distance calculation
                continue;
            }
            // calculate manhattan distance
            int target = Math.min((int) piece - 65, 15);
            int distanceY = Math.abs(i / 4 - target / 4);
            int distanceX = Math.abs(i % 4 - target % 4);

            total += distanceX + distanceY;
        }
        return total;
    }

    /**
     * Find the empty piece in the puzzle (the "S")
     * @return index of free space
     */
    private int getFreeSpace() {
        return this.puzzle.indexOf("S");
    }

    @Override
    public int compareTo(SliderPuzzle other) {
        return this.cost - other.cost;
    }

    @Override
    public String toString() {
        StringBuilder puzzle = new StringBuilder(32);
        for (int i = 0; i < 16; i++) {
            char piece = this.puzzle.charAt(i);
            puzzle.append(piece != 'S' ? piece : ' ');
            puzzle.append((i + 1) % 4 == 0 ? "\n" : " ");
        }
        return puzzle.toString();
    }

    @Override
    public boolean equals(Object other) {
        try {
            SliderPuzzle otherPuzzle = (SliderPuzzle) other;
            return this.puzzle.equals(otherPuzzle.puzzle);
        } catch (ClassCastException | NullPointerException e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        // String.hashCode() is deterministic so this is safe to use
        return this.puzzle.hashCode();
    }
}