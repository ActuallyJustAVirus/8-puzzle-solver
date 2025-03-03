package ValueCalculators;
import Board.Board;

public abstract class BoardValueCalculator {
    final int x;
    final int y;
    public BoardValueCalculator(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public abstract int calculateValue(Board board);
}
