package model.expression;
import model.exceptions.ExprException;
import model.interfaces.IExpression;
import model.interfaces.MyIDictionary;
import model.interfaces.MyIHeap;

public class VarExpr implements IExpression {
    private String name;
    public VarExpr(String name) {
        this.name = name;
    }

    public int eval(MyIDictionary <String, Integer> symTable, MyIHeap<Integer> heap) throws Exception {
        Integer ret = symTable.get(name);
        if (ret == null) {
            throw new ExprException("Error: Variable " + name + " not defined");
        }
        return ret;
    }

    public String toString() {
        return name;
    }

//    public IExpression duplicate() {
//        return new VarExpr(new String(name));
//    }

}
