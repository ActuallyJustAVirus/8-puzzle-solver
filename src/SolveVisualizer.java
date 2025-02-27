import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JFrame;

public class SolveVisualizer {
    Solver solver;
    JFrame window;
    
    public SolveVisualizer(int x, int y) {
        solver = new Solver(x, y);
        window = new JFrame();
        window.add(new Canvas(solver));
        window.setSize(x * 100, y * 100);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        SolveVisualizer visualizer = new SolveVisualizer(4, 4);
        visualizer.solver.randomize();
        Thread thread = new Thread(() -> {
            visualizer.solver.solve();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
            System.exit(0);
        });
        thread.start();
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {}
            visualizer.window.repaint();
        }
    }
}

class Canvas extends JPanel {
    Solver solver;
    int x;
    int y;

    public Canvas(Solver solver) {
        this.solver = solver;
        this.x = solver.x;
        this.y = solver.y;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int width = getWidth();
        int height = getHeight();
        int cellWidth = width / x;
        int cellHeight = height / y;
        Board board = solver.board;
        for (int i = 0; i < x * y; i++) {
            int row = i / x;
            int col = i % x;
            int x = col * cellWidth;
            int y = row * cellHeight;
            g.drawRect(x, y, cellWidth, cellHeight);
            if (i == board.empty) {
                continue;
            }
            g.setColor(getColorForBrick(board.board[i]));
            g.fillRect(x + 1, y + 1, cellWidth - 1, cellHeight - 1);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(board.board[i]), x + cellWidth / 2, y + cellHeight / 2);
        }
    }

    private Color getColorForBrick(byte brick) {
        int min = Byte.MIN_VALUE;
        int max = min + x * y - 1;
        int range = max - min;
        int value = brick - min;
        int red = (int) (255 * ((double) value / range));
        int green = 255 - red;
        return new Color(red, green, 0);
    }
}