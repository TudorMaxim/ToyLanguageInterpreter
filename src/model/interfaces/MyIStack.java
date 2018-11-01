package model.interfaces;

public interface MyIStack<T> {
    void push(T elem);
    T pop();
    T top();
    boolean empty();
    //MyIStack <T> duplicate();
}
