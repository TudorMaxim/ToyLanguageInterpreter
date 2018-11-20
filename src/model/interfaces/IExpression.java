package model.interfaces;

import model.interfaces.MyIDictionary;

public interface IExpression {
    int eval(MyIDictionary <String, Integer> symTable, MyIHeap<Integer> heap) throws Exception;
    String toString();
    // IExpression duplicate();
}
