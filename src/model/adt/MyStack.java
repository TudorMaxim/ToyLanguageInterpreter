package model.adt;
import model.interfaces.MyIStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MyStack <T> implements MyIStack<T> {
    Stack <T> stk;
    public MyStack() {
        this.stk = new Stack<T>();
    }
    public MyStack(Stack <T> stk) {
        this.stk = stk;
    }
    public Stack<T> getStack() {
        return this.stk;
    }
    public void setTack(Stack <T> stk) {
        this.stk = stk;
    }
    public void push(T elem) {
        stk.push(elem);
    }
    public T pop() {
        return stk.pop();
    }

    public T top() {
        return stk.peek();
    }

    public boolean empty() {
        return stk.empty();
    }

//    public MyIStack<T> duplicate() {
//        return new MyStack<T>((Stack<T>) stk.clone());
//    }

    public String toString() {
        Object[] arr = stk.toArray();
        List<Object> L = Arrays.asList(arr);
        Collections.reverse(L);
        return L.stream().map(e -> e.toString() + "\n").reduce("", (acc, e) -> acc + e);
    }
}
