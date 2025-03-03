import java.util.LinkedList;

import Board.Board;

public class LinkedListArray {
    LinkedList<Board>[] array;
    int min;
    int size;
    int cap = 2500000;
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

    public void add(Board board, int value) {
        if (value >= removeThreshold) {
            return;
        }
        array[value].add(board);
        min = Math.min(min, value);
        size++;
        while (size > cap) {
            size -= array[--removeThreshold].size();
            if (size > cap) {
                continue;
            }
            array[removeThreshold].clear();
            System.gc();
            // System.out.println("Removed " + removeThreshold);
            // System.out.println("Size: " + size);
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

    public void clear() {
        removeThreshold = array.length;
        for (int i = 0; i < array.length; i++) {
            array[i].clear();
        }
        if (size > 100000) {
            System.gc();
        }
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}