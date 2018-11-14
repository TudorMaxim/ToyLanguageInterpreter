package model.adt;
import model.interfaces.MyIDictionary;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class MyDictionary <K,V> implements MyIDictionary<K, V> {
    HashMap <K, V> D;
    public MyDictionary() {
        D = new HashMap<K,V>();
    }
    public MyDictionary(HashMap<K,V> D) {
        this.D = D;
    }
    public HashMap<K, V> getDictionary() {
        return D;
    }
    public void setDictionary(HashMap<K, V> d) {
        D = d;
    }
    public V get(K key) {
        return D.get(key);
    }
    public void put(K key, V value) {
        D.put(key, value);
    }
    public V remove(K key) {
        return D.remove(key);
    }

    public Set<K> getKeys() {
        return D.keySet();
    }
    public Collection<V> getValues() {
        return D.values();
    }

    public int size() {
        return D.size();
    }

    public String toString() {
        return D.entrySet().stream().map(entry -> entry.getKey() + " --> " + entry.getValue() + "\n").reduce("", (acc, entry) -> acc + entry);
    }
}
