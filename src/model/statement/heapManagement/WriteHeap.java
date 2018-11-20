package model.statement.heapManagement;
import model.PrgState;
import model.interfaces.IExpression;
import model.interfaces.IStmt;

public class WriteHeap implements IStmt {
    private String varName;
    private IExpression expr;

    public WriteHeap(String varName, IExpression expr) {
        this.varName = varName;
        this.expr = expr;
    }

    public PrgState execute(PrgState state) throws Exception {
        Integer address = state.getSymTable().get(varName);
        if(address == null)
            throw new Exception("Unknown variable expression\nError at: " + toString());
        int val = this.expr.eval(state.getSymTable(), state.getHeap());
        state.getHeap().writeAddr(address, val);
        return null;
    }

    public String toString() {
        return "writeHeap(" + this.varName + ", " + this.expr.toString() + ")";
    }
}
