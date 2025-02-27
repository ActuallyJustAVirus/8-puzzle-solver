public class Move {
    Move parent;
    byte move;

    public Move(Move parent, byte move) {
        this.parent = parent;
        this.move = move;
    }

    public Board recreateBoard(BoardManager manager, Board board) {
        if (parent == null) {
            if (board.getClass() == ForceShowBoard.class) {
                board.move(move);
                return board;
            }
            return manager.createBoardByMove(board, move);
        }
        Board newBoard = parent.recreateBoard(manager, board);
        newBoard.move(move);
        return newBoard;
    }
}
