import java.util.Comparator;
import java.util.PriorityQueue;

public class App {
    public static void main(String[] args) throws Exception {
        Board board = new Board();
        System.out.println(board);
        board.randomize();
        System.out.println(board);
        greetyBestFirst(board);
    }

    public static void greetyBestFirst(Board board) {
        PriorityQueue<Board> queue = new PriorityQueue<>(Comparator.comparingInt(Board::heuristics2));
        queue.add(board);
        while (!queue.isEmpty()) {
            Board current = queue.poll();
            if (current.solved()) {
                System.out.println(current);
                current.printMoves();
                return;
            }
            for (int move : Board.moves[current.empty]) {
                Board next = new Board(current);
                next.move(move);
                queue.add(next);
            }
        }
        System.out.println("No solution found");
    }
}
