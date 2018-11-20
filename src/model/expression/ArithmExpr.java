package model.expression;

import model.exceptions.ExprException;
import model.interfaces.IExpression;
import model.interfaces.MyIDictionary;
import model.interfaces.MyIHeap;

public class ArithmExpr implements IExpression {
    private char operator;
    private IExpression operand1, operand2;

    public ArithmExpr(IExpression operand1, char operator, IExpression operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
    }
    public int eval(MyIDictionary <String, Integer> symTable, MyIHeap<Integer> heap) throws Exception{
        if (operator == '+') {
            return operand1.eval(symTable, heap) + operand2.eval(symTable, heap);
        } else if (operator == '-') {
            return operand1.eval(symTable, heap) - operand2.eval(symTable, heap);
        } else if (operator == '*') {
            return operand1.eval(symTable, heap) * operand2.eval(symTable, heap);
        } else if (operator == '/') {
            if (operand2.eval(symTable, heap) == 0) {
                throw (new ExprException("Error: Division by 0"));
            }
            return operand1.eval(symTable, heap) / operand2.eval(symTable, heap);
        } else {
            throw new ExprException("Undefined operator " + operator);
        }
    }

    public String toString() {
        return operand1.toString() + " " + operator + " " + operand2.toString();
    }

//    public IExpression duplicate() {
//        char newOperator = operator;
//        return new ArithmExpr(operand1.duplicate(), newOperator, operand2.duplicate());
//    }
}
