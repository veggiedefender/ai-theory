import java.util.HashSet;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

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

    public static void solvePuzzle(SliderPuzzle puzzle) {
        HashSet<SliderPuzzle> visited = new HashSet<SliderPuzzle>();
        PriorityQueue<SliderPuzzle> willVisit = new PriorityQueue<SliderPuzzle>();
        HashMap<SliderPuzzle, SliderPuzzle> cameFrom = new HashMap<SliderPuzzle, SliderPuzzle>();

        willVisit.add(puzzle);
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

                if (!willVisit.contains(neighbor)) {
                    willVisit.add(neighbor);
                }

                int tentativeDistance = current.getDistance() + 1;
                if (tentativeDistance >= neighbor.getDistance()) {
                    continue;
                }

                cameFrom.put(neighbor, current);
                neighbor.setDistance(tentativeDistance);
            }
        }
        System.err.println("The puzzle is unsolvable.");
    }

    public static void reconstructPath(HashMap<SliderPuzzle, SliderPuzzle> cameFrom, SliderPuzzle current) {
        ArrayList<SliderPuzzle> totalPath = new ArrayList<SliderPuzzle>();
        totalPath.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            totalPath.add(current);
        }

        for (SliderPuzzle puzzle : totalPath) {
            System.out.println(puzzle);
        }
    }
}
