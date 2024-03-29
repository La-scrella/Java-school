package Collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Reverse<E> implements Iterator<E>, Iterable<E> {
    private List<E> eList;
    private int position;

    public Reverse(List<E> list) {
        eList = new ArrayList<>(list);
        position = eList.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return position >= 0;
    }

    @Override
    public E next() {
        return eList.get(position--);
    }

    @Override
    public void remove() {
        eList.remove(position--);
    }

    @Override
    public void forEachRemaining(Consumer action) {
        while (hasNext()){
            action.accept(next());
        }
    }

    @Override
    public Iterator<E> iterator() {
        position = eList.size() - 1;
        return this;
    }
}
