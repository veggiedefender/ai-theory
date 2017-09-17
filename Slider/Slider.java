import java.io.*;
import java.util.Scanner;
import java.util.PriorityQueue;

public class Slider {
    public static void main(String[] args) {
        try {
            String[][] puzzle = readPuzzle("puzzle.txt");
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
    public static String[][] readPuzzle(String filename) throws FileNotFoundException {
        Scanner scan;
        scan = new Scanner(new BufferedReader(new FileReader(filename)));
        String[][] puzzle = new String[4][4];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                puzzle[row][col] = scan.next();
            }
        }
        return puzzle;
    }

    /**
     * Find the manhattan distance between a letter's position and its target position
     * @param  row    row of the letter's current position
     * @param  col    column of the letter's current position
     * @param  letter the letter at the current position
     * @return        the manhattan distance
     */
    public static int manhattanDistance(int row, int col, String letter) {
        // Map letter to a value from 0-15 (A=0, B=1, ..., O=14, S=15).
        int target = Math.min((int) letter.charAt(0) - 65, 15);

        int targetRow = target / 4;
        int targetCol = target % 4;
        
        int distance = Math.abs(targetRow - row) + Math.abs(targetCol - col);
        return distance;
    }

    /**
     * Prints the puzzle as a square, replacing S with a blank space
     * @param puzzle 2D array of letters
     */
    public static void printPuzzle(String[][] puzzle) {
        for (String[] row : puzzle) {
            for (String piece : row) {
                // print a gap instead of S
                if (piece.equals("S")) {
                    piece = " ";
                }
                System.out.print(piece + " ");
            }
            System.out.println();
        }
    }

    public static void solvePuzzle(String[][] puzzle) {
        printPuzzle(puzzle);
    }
}
