public class Board {
    byte[] board = {1, 2, 3, 4, 5, 6, 7, 8, 0};
    byte empty = 8;
    int move = 0;
    byte lastMove = -1;
    
    final static byte[][] moves = {
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
    final static byte[][] distances = {
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

    public Board() {
    }

    public Board(byte[] board, byte empty) {
        this.board = board;
        this.empty = empty;
    }

    public Board(Board board) {
        this.board = board.board.clone();
        this.empty = board.empty;
        this.move = board.move;
    }

    public void move(byte i) {
        board[empty] = board[i];
        board[i] = 0;
        lastMove = empty;
        empty = i;
        move++;
    }

    public byte[] possibleMoves() {
        byte[] possibleMoves = moves[empty];
        byte[] result = new byte[possibleMoves.length - 1];
        for (int i = 0, j = 0; i < possibleMoves.length; i++) {
            if (possibleMoves[i] == lastMove) {
                continue;
            }
            result[j++] = possibleMoves[i];
        }
        return result;
    }

    public void randomize() {
        for (int i = 0; i < 1000; i++) {
            byte[] possibleMoves = moves[empty];
            byte move = possibleMoves[(int) (Math.random() * possibleMoves.length)];
            move(move);
        }
        this.move = 0;
    }

    public int heuristics1() {
        int result = 8;
        for (int i = 0; i < board.length; i++) {
            if (i == empty) {
                continue;
            }
            if (board[i] == i + 1) {
                result--;
            }
        }
        return result;
    }

    public int heuristics2() {
        int result = 0;
        for (int i = 0; i < board.length; i++) {
            if (i == empty) {
                continue;
            }
            result += distances[i][board[i] - 1];
        }
        return result + move;
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
