package model.interfaces;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface MyIDictionary <K, V> {
    V get(K key);
    void put(K key, V value);
    V remove(K key);
    Set<K> getKeys();
    Collection<V> getValues();
    int size();
    Map <K,V> getContent();
    void setContent(Map<K, V> map);
    //MyIDictionary <K,V> duplicate();
}
