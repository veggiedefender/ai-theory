import java.io.*;
import java.util.Scanner;
import java.util.PriorityQueue;

public class Slider {
    public static void main(String[] args) {
        try {
            char[][] puzzle = readPuzzle("puzzle.txt");
            solvePuzzle(puzzle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read a puzzle from a file into a 2D array
     * @param  filename              name of file containing puzzle
     * @return                       2D array representation of puzzle
     * @throws FileNotFoundException
     */
    public static char[][] readPuzzle(String filename) throws FileNotFoundException {
        Scanner scan;
        scan = new Scanner(new BufferedReader(new FileReader(filename)));
        char[][] puzzle = new char[4][4];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                puzzle[row][col] = scan.next().charAt(0);
            }
        }
        return puzzle;
    }

    /**
     * Find the manhattan distance between a letter's position and its target position
     * @param  row    row of the letter's current position
     * @param  col    column of the letter's current position
     * @param  puzzle the entire puzzle
     * @return        the manhattan distance
     */
    public static int manhattanDistance(int row, int col, char[][] puzzle) {
        // Map letter to a value from 0-15 (A=0, B=1, ..., O=14, S=15).
        int target = Math.min((int) puzzle[row][col] - 65, 15);

        int targetRow = target / 4;
        int targetCol = target % 4;

        int distance = Math.abs(targetRow - row) + Math.abs(targetCol - col);
        return distance;
    }

    /**
     * Find the total manhattan distance of each piece from its target position
     * @param  puzzle 2D array of letters representing the puzzle
     * @return        total cost of the given state
     */
    public static int cost(char[][] puzzle) {
        int totalCost = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                totalCost += manhattanDistance(row, col, puzzle);
            }
        }
        return totalCost;
    }

    /**
     * Prints the puzzle as a square, replacing S with a blank space
     * @param puzzle 2D array of letters representing the puzzle
     */
    public static void printPuzzle(char[][] puzzle) {
        for (char[] row : puzzle) {
            for (char piece : row) {
                // print a gap instead of S
                if (piece == 'S') {
                    piece = ' ';
                }
                System.out.print(piece + " ");
            }
            System.out.println();
        }
    }

    public static void solvePuzzle(char[][] puzzle) {
        printPuzzle(puzzle);
    }
}
