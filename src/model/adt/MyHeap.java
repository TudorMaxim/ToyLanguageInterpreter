package model.adt;

import model.interfaces.MyIHeap;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<T> implements MyIHeap<T> {
    private int memory;
    private Map<Integer, T> heap;
    public MyHeap(Map<Integer, T> vals) {
        this.heap = vals;
    }
    @Override
    public int allocate(T value) {
        ++ this.memory;
        this.heap.put(this.memory, value);
        return memory;
    }

    @Override
    public T readAddr(int addr) {
        return this.heap.get(addr);
    }

    @Override
    public void writeAddr(int addr, T value) {
        this.heap.put(addr, value);
    }

    @Override
    public T deallocate(int addr) {
        return this.heap.remove(addr);
    }

    public String toString() {
        String ret = "";
        boolean ok = false;
        for(HashMap.Entry<Integer, T> entry : this.heap.entrySet()) {
            if(ok) {
                ret = ret + "\n";
            }
            ret += entry.getKey().toString() + " -> " + entry.getValue().toString();
            ok = true;
        }
        return ret;
    }

    public void setContent(Map <Integer,T> map) {
        this.heap = map;
    }

    public Map <Integer, T> getContent() {
        return this.heap;
    }
}
