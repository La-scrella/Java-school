package Generics;

import java.util.Map;

public interface ICountMap <E> {
    void add(E el);

    int getCount(E el);

    int remove(E el);

    int size();

    void addAll(CountMap source);

    Map toMap();

    void toMap(Map destination);
}
