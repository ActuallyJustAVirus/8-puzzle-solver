public class Solver {
    BoardManager manager;
    BoardValueCalculator calculator;
    LinkedListArray queue;
    Board start;
    Board board;
    int x;
    int y;

    public Solver(int x, int y) {
        this.x = x;
        this.y = y;
        manager = new BoardManager(x, y);
        calculator = new AStar(x, y);
        queue = new LinkedListArray(1000);
        start = new Board(x, y);
        board = new Board(start);
    }

    public void setBoard(Board board) {
        this.board = board;
        this.start = new Board(board);
        queue.clear();
    }

    public void newBoard() {
        start = new Board(x, y);
        board = new Board(start);
        queue.clear();
    }

    public void randomize() {
        manager.randomize(board);
        start = new Board(board);
        queue.clear();
    }

    public boolean solve() {
        queue.add(board, calculator.calculateValue(board));
        while (!queue.isEmpty()) {
            board = queue.poll();
            if (manager.solved(board)) {
                return true;
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
                queue.add(next, calculator.calculateValue(next));
            }
        }
        return false;
    }

    public void printBoard() {
        System.out.println(manager.toString(board));
    }
}
