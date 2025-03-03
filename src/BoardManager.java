public class BoardManager {
    final int x;
    final int y;
    
    final int[][] moves;

    public BoardManager(int x, int y) {
        this.x = x;
        this.y = y;
        this.moves = calculateMoves();
    }

    private int[][] calculateMoves() {
        int[][] moves = new int[x * y][];
        for (int i = 0; i < moves.length; i++) {
            int[] buffer = new int[4];
            int count = 0;
            if (i % x > 0) {
                buffer[count++] = i - 1;
            }
            if (i % x < x - 1) {
                buffer[count++] = i + 1;
            }
            if (i / x > 0) {
                buffer[count++] = i - x;
            }
            if (i / x < y - 1) {
                buffer[count++] = i + x;
            }
            int[] result = new int[count];
            System.arraycopy(buffer, 0, result, 0, count);
            moves[i] = result;
        }
        return moves;
    }

    public Board createBoardByMove(Board board, int i) {
        Board next = new Board(board);
        makeMove(next, i);
        return next;
    }

    public void makeMove(Board board, int i) {
        board.move(i);
    }

    public boolean solved(Board board) {
        for (int i = 0; i < board.board.length; i++) {
            if (i == board.empty) {
                continue;
            }
            if (board.board[i] != i - 128) {
                return false;
            }
        }
        return true;
    }

    public void randomize(Board board) {
        for (int i = 0; i < 1000; i++) {
            int[] possibleMoves = moves[board.empty];
            int move = possibleMoves[(int) (Math.random() * possibleMoves.length)];
            board.move(move);
        }
        board.move = 0;
        board.moveList = null;
        board.lastMove = board.empty;
    }

    public String toString(Board board) {
        // board[empty] = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < board.board.length; i++) {
            if (i % x == 0) {
                builder.append("\n");
            }
            if (i == board.empty) {
                builder.append(String.format("%3d", 0));
            } else {
                builder.append(String.format("%3d", board.board[i]));
            }
        }
        return builder.toString();
    }
}
