package AiSD.ArrayMin;

import AiSD.Queue.Queue;
import AiSD.Stack.Stack;

public class ArrayQueueMin implements Queue {
    private Stack a;
    private Stack b;

    public ArrayQueueMin() {
        a = new ArrayStackMin();
        b = new ArrayStackMin();
    }

    @Override
    public void push(Object o) {
        a.push(o);
    }

    @Override
    public Object first() {
        if (b.size() == 0) {
            while (a.size() > 0) {
                b.push(a.pop());
            }
        }
        return b.top();
    }

    @Override
    public Object pop() {
        first();
        return b.pop();
    }

    @Override
    public int size() {
        return b.size();
    }

    @Override
    public boolean isEmpty() {
        return b.size() == 0;
    }

    @Override
    public Object min() {
        if (a.size() > 0 && b.size() > 0)
            return Math.min((int) a.min(), (int) b.min());
        if (a.size() > 0)
            return a.min();
        return b.min();
    }
}
