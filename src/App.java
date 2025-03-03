public class App {
    public static void main(String[] args) throws Exception {
        Solver solver = new Solver(4, 4);
        solver.randomize();
        solver.printBoard();
        if (solver.solve()) {
            System.out.println("Solved!");
        } else {
            System.out.println("Not solved!");
        }
        solver.printBoard();
    }
}
