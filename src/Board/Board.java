package Board;
import java.io.Serializable;

public class Board implements Serializable {
    public byte[] board;
    public int empty;
    public int move = 0;
    public int lastMove;
    public Move moveList;

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

    public int getMove() {
        return move;
    }
}
