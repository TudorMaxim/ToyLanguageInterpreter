package model.utilities;

public class Pair <X, Y> {
    private X first;
    private Y second;

    public Pair(X f, Y s) {
        this.first = f;
        this.second = s;
    }

    public X getFirst() {
        return first;
    }

    public void setFirst(X first) {
        this.first = first;
    }

    public Y getSecond() {
        return second;
    }

    public void setSecond(Y second) {
        this.second = second;
    }

    public String toString() {
        return first.toString();
    }
}
