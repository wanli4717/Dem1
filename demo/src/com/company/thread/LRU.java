package com.company.thread;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRU<K, V> extends LinkedHashMap<K, V> implements Map<K, V> {

    private int capacity;

    LRU(int capacity) {
        super(16, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest) {

        return size() > capacity;
    }
}

class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new LRU<>(5);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);

        map.get(3);
        map.put(6, 6);

        for(Iterator<Map.Entry<Integer,Integer>> it = map.entrySet().iterator(); it.hasNext();){
            System.out.println(it.next().getKey());
        }

    }
}
