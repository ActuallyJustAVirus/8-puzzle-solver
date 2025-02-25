import java.util.Comparator;
import java.util.PriorityQueue;

public class App {
    public static void main(String[] args) throws Exception {
        Board board = new Board(4, 4);
        System.out.println(board);
        board.randomize();
        System.out.println(board);
        greedyBestFirst(board);
    }

    public static void greedyBestFirst(Board board) {
        PriorityQueue<Board> queue = new PriorityQueue<>(Comparator.comparingInt(Board::value));
        queue.add(board);
        while (!queue.isEmpty()) {
            Board current = queue.poll();
            if (current.solved()) {
                System.out.println(current);
                System.out.println("Solved with " + current.move + " moves");
                System.out.println("Frontier: " + queue.size());
                return;
            }
            for (int move : current.moves[current.empty]) {
                Board next = current.createBoardByMove(move);
                if (next == null) {
                    continue;
                }
                queue.add(next);
                if (queue.size() > 10000000) {
                    System.out.println("No solution found");
                    System.exit(0);
                }
            }
        }
        System.out.println("No solution found");
    }
}
