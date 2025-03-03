import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Board.Board;
import Board.BoardManager;

public class BoardFileManager {

    public static void main(String[] args) {
        int[][] cases = {
            {3, 3},
            {3, 4},
            {4, 4},
            {4, 5},
            {5, 5},
        };
        for (int[] c : cases) {
            BoardManager manager = new BoardManager(c[0], c[1]);
            Board[] boards = new Board[100];
            for (int i = 0; i < boards.length; i++) {
                boards[i] = manager.createRandomBoard();
            }
            saveBoardList(boards, "100"+c[0]+"x"+c[1]+".ser");
        }
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
            out.writeObject(board.getBoard());
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
            byte[][] boardList = new byte[boards.length][];
            for (int i = 0; i < boards.length; i++) {
                boardList[i] = boards[i].getBoard();
            }
            out.writeObject(boardList);
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
            byte[] boardArray = (byte[]) in.readObject();
            board = new Board(boardArray);
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
            byte[][] boardList = (byte[][]) in.readObject();
            boards = new Board[boardList.length];
            for (int i = 0; i < boardList.length; i++) {
                boards[i] = new Board(boardList[i]);
            }
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return boards;
    }
}
