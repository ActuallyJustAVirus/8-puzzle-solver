public class ForceShowBoard extends Board {
    SolveVisualizer visualizer;

    public ForceShowBoard(Board board, SolveVisualizer visualizer) {
        super(board);
        this.visualizer = visualizer;
    }

    @Override
    void move(int i) {
        super.move(i);
        board[i] = 0;
        visualizer.window.repaint();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}
    }

    public static void main(String[] args) {
        SolveVisualizer visualizer = new SolveVisualizer(4, 4);
        // visualizer.solver.start = new ForceShowBoard(visualizer.solver.start, visualizer); 
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {}
                visualizer.window.repaint();
            }
        });
        thread.start();
        visualizer.solver.randomize();
        if (visualizer.solver.solve()) {
            System.out.println("Solved with " + visualizer.solver.board.move + " moves");
        } else {
            System.out.println("No solution found");
            System.exit(0);
        }
        thread.interrupt();
        ForceShowBoard forceShowBoard = new ForceShowBoard(visualizer.solver.start, visualizer);
        Board board = visualizer.solver.board;
        visualizer.solver.board = forceShowBoard;
        Board recreated = board.moveList.recreateBoard(visualizer.solver.manager, forceShowBoard);
        // System.out.println(manager.toString(recreated));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        System.exit(0);
    }
}
