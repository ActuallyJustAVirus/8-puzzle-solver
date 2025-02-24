public class Board {
    int[] board = {1, 2, 3, 4, 5, 6, 7, 8, 0};
    int empty = 8;
    int move = 0;
    final static int[][] moves = {
        {1, 3},
        {0, 2, 4},
        {1, 5},
        {0, 4, 6},
        {1, 3, 5, 7},
        {2, 4, 8},
        {3, 7},
        {4, 6, 8},
        {5, 7}
    };
    final static int[][] distances = {
        {0, 1, 2, 1, 2, 3, 2, 3, 4},
        {1, 0, 1, 2, 1, 2, 3, 2, 3},
        {2, 1, 0, 3, 2, 1, 4, 3, 2},
        {1, 2, 3, 0, 1, 2, 1, 2, 3},
        {2, 1, 2, 1, 0, 1, 2, 1, 2},
        {3, 2, 1, 2, 1, 0, 3, 2, 1},
        {2, 3, 4, 1, 2, 3, 0, 1, 2},
        {3, 2, 3, 2, 1, 2, 1, 0, 1},
        {4, 3, 2, 3, 2, 1, 2, 1, 0}
    };

    int h1 = 0;
    boolean h1Calculated = false;
    int h2 = 0;
    boolean h2Calculated = false;

    public Board() {
    }

    public Board(int[] board, int empty) {
        this.board = board;
        this.empty = empty;
    }

    public Board(Board board) {
        this.board = board.board.clone();
        this.empty = board.empty;
        this.move = board.move;
    }

    public void move(int i) {
        board[empty] = board[i];
        // board[i] = 0;
        empty = i;
        move++;
        h1Calculated = false;
        h2Calculated = false;
    }

    public void randomize() {
        for (int i = 0; i < 100; i++) {
            int[] possibleMoves = moves[empty];
            int move = possibleMoves[(int) (Math.random() * possibleMoves.length)];
            move(move);
        }
        this.move = 0;
    }

    public int heuristics1() {
        if (h1Calculated) {
            return h1;
        }
        int result = 8;
        for (int i = 0; i < board.length; i++) {
            if (i == empty) {
                continue;
            }
            if (board[i] == i + 1) {
                result--;
            }
        }
        h1 = result;
        return result;
    }

    public int heuristics2() {
        if (h2Calculated) {
            return h2;
        }
        int result = 0;
        for (int i = 0; i < board.length; i++) {
            if (i == empty) {
                continue;
            }
            result += distances[i][board[i] - 1];
        }
        h2 = result;
        return result;
    }

    public boolean solved() {
        return heuristics1() == 0;
    }

    @Override
    public String toString() {
        board[empty] = 0;
        return String.format(
            "%d %d %d\n%d %d %d\n%d %d %d\n",
            board[0], board[1], board[2],
            board[3], board[4], board[5],
            board[6], board[7], board[8]
        );
    }

    public void printMoves() {
        System.out.println("Moves: " + move);
    }
}
