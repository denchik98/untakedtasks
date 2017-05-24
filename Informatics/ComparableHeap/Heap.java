package Informatics.ComparableHeap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Heap<T extends Comparable<T>> {
    private int heapSize;
    private List<T> data;
    private Comparator<T> comparator;

    public Heap() {
        data = new ArrayList<T>();
    }

    public Heap(Comparator comparator) {
        this();
        this.comparator = comparator;
    }

    private void heapBuild(List<T> list) {
        heapSize = list.size();
        for (int i = list.size() / 2; i >= 0; i--) {
            heapify(list, i);
        }
    }

    private void heapify(List<T> list, int i) {
        int left = left(i);
        int right = right(i);
        int min = i;

        if (left < heapSize && compare(list.get(i), list.get(left)) < 0)
            min = left;
        if (right < heapSize && compare(list.get(min), list.get(right)) < 0)
            min = right;
        if (i != min) {
            swap(i, min);
            heapify(list, min);
        }
    }

    private int compare(T a, T b) {
        if (comparator == null)
            return a.compareTo(b);
        return comparator.compare(a, b);
    }

    private void swap(int a, int b) {
        T t = (T) data.get(a);
        data.set(a, data.get(b));
        data.set(b, t);
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    public void sort() {
        heapBuild(data);
        while (heapSize > 1) {
            swap(0, heapSize - 1);
            heapSize--;
            heapify(data, 0);
        }
    }

    public void add(T t) {
        data.add(t);
        pushTop(size() - 1);
    }

    private void pushTop(int i) {
        int parent = (i - 1) / 2;
        if (i == 0)
            return;
        if (compare(data.get(parent), data.get(i)) < 0) {
            swap(i, parent);
            pushTop(parent);
        }
    }

    private int size() {
        return data.size();
    }

    public List<T> getData() {
        return data;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public void setHeapSize(int heapSize) {
        this.heapSize = heapSize;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Comparator<T> getComparator() {
        return comparator;
    }

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }
}
