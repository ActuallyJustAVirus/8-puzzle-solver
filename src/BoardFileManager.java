import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BoardFileManager {

    public static void main(String[] args) {
        BoardManager manager = new BoardManager(5, 5);
        Board[] boards = new Board[100];
        for (int i = 0; i < boards.length; i++) {
            boards[i] = manager.createRandomBoard();
        }
        saveBoardList(boards, "100"+manager.x+"x"+manager.y+".ser");
        // Solver solver = new Solver(3, 3);
        // Board[] loaded = loadBoardList("boards.ser");
        // long start = System.currentTimeMillis();
        // int totalMoves = 0;
        // for (int i = 0; i < loaded.length; i++) {
        //     solver.setBoard(loaded[i]);
        //     solver.solve();
        //     totalMoves += solver.board.move;
        // }
        // long end = System.currentTimeMillis();
        // System.out.println("Time: " + (end - start) + "ms");
        // System.out.println("Total moves: " + totalMoves);
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
