public class App {
    public static void main(String[] args) throws Exception {
        Solver solver = new Solver(4, 4);
        solver.randomize();
        solver.printBoard();
        solver.solve();        
    }
}
