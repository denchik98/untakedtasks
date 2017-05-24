package AiSD.ArrayMin;

import AiSD.Stack.Pair;
import AiSD.Stack.Stack;

import java.util.ArrayList;
import java.util.List;

public class ArrayStackMin implements Stack{
    private List data;

    public ArrayStackMin() {
        data = new ArrayList();
    }

    @Override
    public void push(Object o) {
        if (data.size() == 0)
            data.add(new Pair(o, o));
        else {
            int p = (int) min();
            data.add(new Pair(o, Math.min((int) o, p)));
        }
    }

    @Override
    public Object top() {
        Pair p = (Pair) data.get(size() - 1);
        return p.getP1();
    }

    @Override
    public Object pop() {
        Object o = top();
        data.remove(size() - 1);
        return o;
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Object min() {
        Pair p =(Pair) data.get(size() - 1);
        return p.getP2();
    }

    @Override
    public void poop() {
        while (size() > 0) {
            pop();
        }
    }
}