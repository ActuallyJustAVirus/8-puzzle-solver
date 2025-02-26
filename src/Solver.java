public class Solver {
    BoardManager manager;
    LinkedListArray queue;
    Board start;
    Board board;
    int x;
    int y;

    public Solver(int x, int y) {
        this.x = x;
        this.y = y;
        manager = new BoardManager(x, y);
        queue = new LinkedListArray(1000);
        start = new Board(x, y);
        board = new Board(start);
    }

    public void randomize() {
        start = new Board(x, y);
        manager.randomize(start);
        board = new Board(start);
    }

    public void solve() {
        queue.add(board);
        while (!queue.isEmpty()) {
            board = queue.poll();
            if (manager.solved(board)) {
                System.out.println(manager.toString(board));
                System.out.println("Solved with " + board.move + " moves");
                System.out.println("Frontier: " + queue.size());

                System.out.println("Recreating board");
                Board recreated = board.moveList.recreateBoard(manager, start);
                System.out.println(manager.toString(recreated));
                return;
            }
            int[] possibleMoves = manager.moves[board.empty];
            for (int i = 0; i < possibleMoves.length; i++) {
                int move = possibleMoves[i];
                if (move == board.lastMove) {
                    continue;
                }
                Board next = board;
                if (i == possibleMoves.length - 1) {
                    manager.makeMove(next, move);
                } else {
                    next = manager.createBoardByMove(board, move);
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

    public void printBoard() {
        System.out.println(manager.toString(board));
    }
}
