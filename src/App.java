public class App {
    public static void main(String[] args) throws Exception {
        int[][] cases = {
            {3, 3},
            {3, 4},
            {4, 4},
            {4, 5},
            {5, 5},
        };
        for (int[] c : cases) {
            Board[] boards = BoardFileManager.loadBoardList("100" + c[0] + "x" + c[1] + ".ser");
            int[] moves = new int[boards.length];
            Solver solver = new Solver(c[0], c[1]);
            long start = System.currentTimeMillis();
            int totalMoves = 0;
            int totalSolved = 0;
            for (int i = 0; i < boards.length; i++) {
                solver.setBoard(boards[i]);
                if (solver.solve()) {
                    totalSolved++;
                    moves[i] = solver.board.move;
                    totalMoves += solver.board.move;
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("Time: " + (end - start) + "ms");
            System.out.println("Total moves: " + totalMoves);
            System.out.println("Total solved: " + totalSolved);
        }
    }
}
