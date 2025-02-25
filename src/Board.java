public class Board {
    byte[] board;
    int empty;
    int move = 0;
    int lastMove;
    int x;
    int y;
    
    final int[][] moves;
    final byte[][] distances;

    public Board(int x, int y) {
        this.x = x;
        this.y = y;
        this.distances = calculateDistances();
        this.moves = calculateMoves();
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
        this.x = board.x;
        this.y = board.y;
        this.moves = board.moves;
        this.distances = board.distances;
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

    public void move(int i) {
        board[empty] = board[i];
        // board[i] = 0;
        lastMove = empty;
        empty = i;
        move++;
    }

    public int[] possibleMoves() {
        int[] possibleMoves = moves[empty];
        int[] result = new int[possibleMoves.length - 1];
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
            int[] possibleMoves = moves[empty];
            int move = possibleMoves[(int) (Math.random() * possibleMoves.length)];
            move(move);
        }
        this.move = 0;
    }

    public int heuristics1() {
        int result = board.length - 1;
        for (int i = 0; i < board.length; i++) {
            if (i == empty) {
                continue;
            }
            if (board[i] == i - 128) {
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
            result += distances[i][board[i] + 128];
        }
        return result + move;
    }

    public boolean solved() {
        return heuristics1() == 0;
    }

    @Override
    public String toString() {
        board[empty] = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            if (i % x == 0) {
                builder.append("\n");
            }
            builder.append(String.format("%3d", board[i]));
        }
        return builder.toString();
    }
}
