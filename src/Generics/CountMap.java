package Generics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CountMap<E> implements ICountMap<E> {
    private Map<E, Integer> map;

    public CountMap() {
        map = new HashMap<>();
    }

    @Override
    public void add(E el) {
        if (map.containsKey(el)) {
            map.replace(el, map.get(el) + 1);
        } else {
            map.put(el, 1);
        }
    }

    @Override
    public int getCount(E el) {
        return map.containsKey(el) ? map.get(el) : 0;
    }

    @Override
    public int remove(E el) {
        if (map.containsKey(el)) {
            int count = map.get(el);
            map.remove(el);
            return count;
        } else
            return 0;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap source) {
        for (Iterator it = source.map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<E, Integer> sEl = (Map.Entry<E, Integer>) it.next();
            E key = sEl.getKey();
            Integer count = sEl.getValue();
            if (map.containsKey(key)) {
                map.replace(key, map.get(key) + count);
            } else {
                map.put(key, count);
            }
        }
    }

    @Override
    public Map toMap() {
        return new HashMap(map);
    }

    @Override
    public void toMap(Map destination) {
        destination.clear();
        for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
            Map.Entry<E, Integer> sEl = (Map.Entry<E, Integer>) it.next();
            destination.put(sEl.getKey(), sEl.getValue());
        }
    }
}
