package model.expression;
import model.interfaces.IExpression;
import model.interfaces.MyIDictionary;
import model.interfaces.MyIHeap;

public class ConstExpr implements IExpression {
    private Integer value;

    public ConstExpr(Integer value) {
        this.value = value;
    }

    public int eval(MyIDictionary <String, Integer> symTable, MyIHeap<Integer> heap) {
        return value;
    }

    public String toString() {
        return value.toString();
    }

//    public IExpression duplicate() {
//        return new ConstExpr(new Integer(value));
//    }
}
