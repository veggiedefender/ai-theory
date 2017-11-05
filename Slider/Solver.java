import java.util.HashSet;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * Solve a 4x4 slider puzzle read in from file puzzle.txt
 * @author Jesse Li
 */
public class Solver {
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new BufferedReader(new FileReader("puzzle.txt")));
            SliderPuzzle puzzle = new SliderPuzzle(scan);
            solvePuzzle(puzzle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Solve the puzzle using A* search
     * @param puzzle puzzle in unsolved configuration
     */
    public static void solvePuzzle(SliderPuzzle puzzle) {
        HashSet<SliderPuzzle> visited = new HashSet<SliderPuzzle>();
        PriorityQueue<SliderPuzzle> willVisit = new PriorityQueue<SliderPuzzle>();
        HashMap<SliderPuzzle, SliderPuzzle> cameFrom = new HashMap<SliderPuzzle, SliderPuzzle>();

        puzzle.setDistance(0);
        willVisit.add(puzzle);

        while (willVisit.size() != 0) {
            SliderPuzzle current = willVisit.poll();
            if (current.heuristic == 0) {
                reconstructPath(cameFrom, current);
                return;
            }
            visited.add(current);

            for (SliderPuzzle neighbor : current.getNeighbors()) {
                if (visited.contains(neighbor)) {
                    continue;
                }

                int tentativeDistance = current.getDistance() + 1;
                if (tentativeDistance >= neighbor.getDistance()) {
                    continue;
                }

                cameFrom.put(neighbor, current);
                neighbor.setDistance(tentativeDistance);

                if (!willVisit.contains(neighbor)) {
                    willVisit.add(neighbor);
                }
            }
        }
        System.err.println("The puzzle is unsolvable.");
    }

    /**
     * Print out the optimal solution given by A*
     * @param cameFrom Map containing shortest solution
     * @param current  Goal/last state in solution
     */
    public static void reconstructPath(HashMap<SliderPuzzle, SliderPuzzle> cameFrom, SliderPuzzle current) {
        ArrayList<SliderPuzzle> totalPath = new ArrayList<SliderPuzzle>();
        totalPath.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            totalPath.add(0, current);
        }

        for (SliderPuzzle puzzle : totalPath) {
            System.out.println(puzzle);
        }
    }
}
