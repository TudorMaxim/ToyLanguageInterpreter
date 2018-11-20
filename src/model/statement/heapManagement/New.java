package model.statement.heapManagement;

import model.PrgState;
import model.interfaces.IExpression;
import model.interfaces.IStmt;

public class New implements IStmt {
    private String varName;
    private IExpression expression;

    public New(String varName, IExpression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    public PrgState execute(PrgState state) throws Exception {
        int res = this.expression.eval(state.getSymTable(), state.getHeap());
        int loc = state.getHeap().allocate(res);
        state.getSymTable().put(varName, loc);
        return null;
    }

    public String toString() {
        return "new(" + this.varName + ", " + this.expression.toString() + ")";
    }
}
