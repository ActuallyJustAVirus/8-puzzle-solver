import java.util.Comparator;
import java.util.PriorityQueue;

public class App {
    public static void main(String[] args) throws Exception {
        Board board = new Board(3, 4);
        System.out.println(board);
        board.randomize();
        System.out.println(board);
        greedyBestFirst(board);
    }

    public static void greedyBestFirst(Board board) {
        PriorityQueue<Board> queue = new PriorityQueue<>(Comparator.comparingInt(Board::heuristics2));
        queue.add(board);
        while (!queue.isEmpty()) {
            Board current = queue.poll();
            if (current.solved()) {
                System.out.println(current);
                System.out.println("Solved with " + current.move + " moves");
                System.out.println("Frontier: " + queue.size());
                return;
            }
            for (int move : current.possibleMoves()) {
                Board next = new Board(current);
                next.move(move);
                queue.add(next);
                if (queue.size() > 1000000) {
                    System.out.println("No solution found");
                    System.exit(0);
                }
            }
        }
        System.out.println("No solution found");
    }
}
