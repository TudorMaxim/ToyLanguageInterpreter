package model.adt;
import model.interfaces.MyIList;
import java.util.ArrayList;
import java.util.Iterator;

public class MyList<T> implements MyIList<T>, Iterable<T> {
    ArrayList <T> V;
    public MyList() {
        V = new ArrayList<T>();
    }
    public MyList(ArrayList <T> V) {
        this.V = V;
    }

    public ArrayList <T> getList() {
        return this.V;
    }

    public void setList(ArrayList <T> W) {
        this.V = W;
    }

    public T get(int index) {
        return V.get(index);
    }

    public int getIndex(T val) {
        return V.indexOf(val);
    }

    public void add(T val) {
        V.add(val);
    }

    public T removeByIndex(int index) {
        return V.remove(index);
    }

    public boolean remove(T val) {
        return V.remove(val);
    }

    public int size() {
        return V.size();
    }

    public T find(T val) {
        int index = V.indexOf(val);
        if (index == -1) {
            return null;
        }
        return V.get(index);
    }
    public boolean contains(T val) {
        return V.contains(val);
    }

    public void clear() {
        V.clear();
    }

    public Iterator<T> iterator() {
        return V.iterator();
    }

    public String toString() {
        String ret = "";
        for (T e : V) {
            ret += e.toString();
        }
        return ret;
    }
}
