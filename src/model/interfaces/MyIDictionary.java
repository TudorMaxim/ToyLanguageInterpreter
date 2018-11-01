package model.interfaces;

import java.util.Set;

public interface MyIDictionary <K, V> {
    V get(K key);
    void put(K key, V value);
    void remove(K key);
    Set<K> getKeys();
    int size();
    //MyIDictionary <K,V> duplicate();
}
