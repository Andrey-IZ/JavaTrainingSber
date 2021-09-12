package com.sber.javaschool.hometask6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

public class MyIterator<T> implements ListIterator<T>, Iterable<T> {
    private final List<T> list;
    private int position;

    public MyIterator(List<T> list) {
        if (list == null) {
            throw new NullPointerException("The list hasn't been initialized");
        }
        this.list = new ArrayList<>(list);
        toFront();
    }

    public void toBack() {
        position = list.size();
    }

    public void toFront() {
        position = -1;
    }

    @Override
    public boolean hasNext() {
        return position + 1 < list.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("The iterator has been reached to the end");
        }
        return list.get(++position);
    }

    @Override
    public boolean hasPrevious() {
        return position > 0;
    }

    @Override
    public T previous() {
        if (!hasPrevious()) {
            throw new IndexOutOfBoundsException("The iterator has been reached to the end");
        }
        return list.get(--position);
    }

    @Override
    public int nextIndex() throws IndexOutOfBoundsException {
        if (position + 1 >= list.size()) {
            throw new IndexOutOfBoundsException("The iterator has been reached to the end");
        }
        return position + 1;
    }

    @Override
    public int previousIndex() throws IndexOutOfBoundsException {
        if (position <= 0) {
            throw new IndexOutOfBoundsException("The iterator hasn't been moved from the begin");
        }
        return position - 1;
    }

    @Override
    public void remove() {
        if (position < 0) {
            throw new IllegalStateException("There are no elements to remove");
        }
        list.remove(position--);
    }

    @Override
    public void set(T t) {
        if (position < 0 || position >= list.size()) {
            throw new IndexOutOfBoundsException();
        }
        list.set(position, t);
    }

    @Override
    public void add(T t) {
        list.add(t);
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        if (action == null)
            throw new NullPointerException("The consumer hasn't been initialized");

        for (T t : this) {
            action.accept(t);
        }
    }
}
