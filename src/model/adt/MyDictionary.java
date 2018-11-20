package model.adt;
import model.interfaces.MyIDictionary;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyDictionary <K,V> implements MyIDictionary<K, V> {
    Map <K, V> D;
    public MyDictionary() {
        D = new HashMap<K,V>();
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

    public void setContent(Map <K,V> map) {
        this.D = map;
    }

    public Map <K,V> getContent() {
        return this.D;
    }
    public String toString() {
        return D.entrySet().stream().map(entry -> entry.getKey() + " --> " + entry.getValue() + "\n").reduce("", (acc, entry) -> acc + entry);
    }
}
