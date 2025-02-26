import java.util.Comparator;
import java.util.PriorityQueue;

public class App {
    public static void main(String[] args) throws Exception {
        BoardManager manager = new BoardManager(4, 4);
        Board board = new Board(4, 4);
        System.out.println(manager.toString(board));
        manager.randomize(board);
        System.out.println(manager.toString(board));
        greedyBestFirst(manager, board);
    }

    public static void greedyBestFirst(BoardManager manager, Board board) {
        LinkedListArray queue = new LinkedListArray(1000);
        queue.add(board);
        Board start = new Board(board);
        // PriorityQueue<Board> queue = new PriorityQueue<>(Comparator.comparingInt(Board::value));
        // queue.add(board);
        while (!queue.isEmpty()) {
            Board current = queue.poll();
            if (manager.solved(current)) {
                System.out.println(manager.toString(current));
                System.out.println("Solved with " + current.move + " moves");
                System.out.println("Frontier: " + queue.size());

                System.out.println("Recreating board");
                Board recreated = current.moveList.recreateBoard(manager, start);
                System.out.println(manager.toString(recreated));
                return;
            }
            int[] possibleMoves = manager.moves[current.empty];
            for (int i = 0; i < possibleMoves.length; i++) {
                int move = possibleMoves[i];
                if (move == current.lastMove) {
                    continue;
                }
                Board next = current;
                if (i == possibleMoves.length - 1) {
                    manager.makeMove(next, move);
                } else {
                    next = manager.createBoardByMove(current, move);
                }
                queue.add(next);
                if (queue.size() > 100000000) {
                    System.out.println("No solution found (queue size exceeded)");
                    System.exit(0);
                }
            }
        }
        System.out.println("No solution found (queue empty)");
    }
}
