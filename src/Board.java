public class Board {
    byte[] board;
    int empty;
    int move = 0;
    int lastMove;
    int value;
    Move moveList;

    public Board(int x, int y) {
        this.board = new byte[x * y];
        byte brick = Byte.MIN_VALUE;
        for (int i = 0; i < board.length; i++) {
            board[i] = brick++;
        }
        this.empty = (byte) (x * y - 1);
        this.lastMove = empty;
    }

    public Board(Board board) {
        this.board = board.board.clone();
        this.empty = board.empty;
        this.move = board.move;
        this.lastMove = board.lastMove;
        this.value = board.value;
        this.moveList = board.moveList;
    }

    void move(int i) {
        board[empty] = board[i];
        // board[i] = 0;
        lastMove = empty;
        empty = i;
        move++;
        moveList = new Move(moveList, (byte) i);
    }
}
