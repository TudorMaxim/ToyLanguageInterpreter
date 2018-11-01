package model.expression;

import model.interfaces.MyIDictionary;

public interface IExpression {
    int eval(MyIDictionary <String, Integer> symTable) throws Exception;
    String toString();
    // IExpression duplicate();
}
