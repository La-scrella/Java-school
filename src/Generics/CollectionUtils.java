package Generics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class CollectionUtils {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<T> source, T o) {
        return source.indexOf(o);
    }

    public static <T> List<T> limit(List<? extends T> source, int size) {
        ArrayList<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(source.get(i));
        }
        return list;
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        for (Iterator it = c2.iterator(); it.hasNext(); ) {
            removeFrom.remove(it.next());
        }
    }

    public static <T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {
        for (Iterator it = c2.iterator(); it.hasNext(); ) {
            if (!c1.contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean containsAny(List<? super T> c1, List<? extends T> c2) {
        for (Iterator it = c2.iterator(); it.hasNext(); ) {
            if (c1.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static <T extends Comparable<T>> List<T> range(List<? extends T> list, T min, T max) {
        return range(list, min, max, Comparator.naturalOrder());
    }

    public static <T> List<T> range(List<? extends T> list, T min, T max, Comparator<T> comparator) {
        if (comparator.compare(min, max) > 0) {
            T temp = min;
            min = max;
            max = temp;
        }
        List<T> newList = new ArrayList<>();
        for(Iterator it = list.iterator(); it.hasNext(); ) {
            T t = (T) it.next();
            if (comparator.compare(min, t) <= 0 && comparator.compare(max, t) >= 0) {
                newList.add(t);
            }
        }
        newList.sort(comparator);
        return newList;
//        list.sort(comparator);
//        return list.subList(list.indexOf(min), list.indexOf(max));
    }

}
