import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BoardFileManager {

    public static void main(String[] args) {
        // Board[] boards = new Board[100000];
        Solver solver = new Solver(3, 3);
        // for (int i = 0; i < boards.length; i++) {
        //     solver.randomize();
        //     boards[i] = new Board(solver.start);
        // }
        // saveBoardList(boards, "boards.ser");
        Board[] loaded = loadBoardList("boards.ser");
        long start = System.currentTimeMillis();
        for (int i = 0; i < loaded.length; i++) {
            solver.start = loaded[i];
            solver.board = new Board(loaded[i]);
            solver.solve();
        }
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + "ms");
    }

    public static void saveBoard(Board board, String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(board);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveBoardList(Board[] boards, String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(boards);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Board loadBoard(String filename) {
        Board board = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            board = (Board) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return board;
    }

    public static Board[] loadBoardList(String filename) {
        Board[] boards = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            boards = (Board[]) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return boards;
    }
}
