public class AStar extends BoardValueCalculator {
    final byte[][] distances;

    public AStar(int x, int y) {
        super(x, y);
        distances = calculateDistances();
    }

    @Override
    public int calculateValue(Board board) {
        int result = heuristics2(board);
        return result + board.move;
    }

    private byte[][] calculateDistances() {
        byte[][] distances = new byte[x * y][x * y];
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[i].length; j++) {
                distances[i][j] = (byte) (Math.abs(i % x - j % x) + Math.abs(i / x - j / x));
            }
        }
        return distances;
    }

    public int heuristics1(Board board) {
        int result = board.board.length - 1;
        for (int i = 0; i < board.board.length; i++) {
            if (i == board.empty) {
                continue;
            }
            if (board.board[i] == i - 128) {
                result--;
            }
        }
        return result;
    }

    public int heuristics2(Board board) {
        int result = 0;
        for (int i = 0; i < board.board.length; i++) {
            if (i == board.empty) {
                continue;
            }
            result += distances[i][board.board[i] + 128];
        }
        return result;
    }

}
