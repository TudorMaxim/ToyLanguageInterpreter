package model.interfaces;

public interface MyIList<T> {
    T get(int index);
    int getIndex(T val);
    void add(T val);
    T removeByIndex(int index);
    boolean remove(T val);
    T find(T val);
    boolean contains(T val);
    int size();
    void clear();
    //MyIList <T> duplicate();
}
