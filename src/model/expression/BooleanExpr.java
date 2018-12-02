package model.expression;

import model.interfaces.IExpression;
import model.interfaces.MyIDictionary;
import model.interfaces.MyIHeap;

public class BooleanExpr implements IExpression {
    private IExpression expression1;
    private IExpression expression2;
    private String operator;

    public BooleanExpr (IExpression expression1, String operator, IExpression expression2) {
        this.expression1 = expression1;
        this.operator = operator;
        this.expression2 = expression2;
    }

    public int eval(MyIDictionary<String, Integer> symTable, MyIHeap<Integer> heap) throws Exception {
        int val1 = expression1.eval(symTable, heap);
        int val2 = expression2.eval(symTable, heap);
        switch (operator) {
            case "<":
                return val1 < val2 ? 1 : 0;
            case "<=":
                return val1 <= val2 ? 1 : 0;
            case ">":
                return val1 > val2 ? 1 : 0;
            case ">=":
                return val1 >= val2 ? 1 : 0;
            case "==":
                return val1 == val2 ? 1 : 0;
            case "!=":
                return val1 != val2 ? 1 : 0;
            default:
                throw new Exception("ERROR: Invalid operator " + operator + " in expression " + this.toString());

        }
    }

    public String toString() {
        return this.expression1 + " " + operator + " " + this.expression2;
    }
}
