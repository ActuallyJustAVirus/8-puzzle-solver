import java.util.LinkedList;

public class LinkedListArray {
    LinkedList<Board>[] array;
    int min;
    int size;
    int cap = 10000000;
    int removeThreshold;

    public LinkedListArray(int length) {
        array = new LinkedList[length];
        for (int i = 0; i < length; i++) {
            array[i] = new LinkedList<>();
        }
        min = length;
        removeThreshold = length;
        size = 0;
    }

    public void add(Board board) {
        int index = board.value;
        if (index >= removeThreshold) {
            return;
        }
        array[index].add(board);
        min = Math.min(min, index);
        size++;
        while (size > cap) {
            size -= array[--removeThreshold].size();
            array[removeThreshold].clear();
            // System.gc();
            System.out.println("Removed " + removeThreshold);
        }
    }

    public Board poll() {
        if (size == 0) {
            return null;
        }
        while (array[min].isEmpty()) {
            min++;
        }
        size--;
        return array[min].poll();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}