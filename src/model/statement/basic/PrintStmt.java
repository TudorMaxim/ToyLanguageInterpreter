package model.statement.basic;
import model.PrgState;
import model.interfaces.IExpression;
import model.interfaces.MyIDictionary;
import model.interfaces.MyIList;
import model.interfaces.IStmt;

public class PrintStmt implements IStmt {
    private IExpression expr;

    public PrintStmt(IExpression expr) {
        this.expr = expr;
    }

    public String toString() {
        return "print(" + expr.toString() + "); ";
    }
    public PrgState execute(PrgState state) throws Exception {
        MyIList <Integer> out = state.getOut();
        MyIDictionary <String, Integer> symTable = state.getSymTable();
        out.add(this.expr.eval(symTable, state.getHeap()));
        return null;
    }
//    public IStmt duplicate() {
//        return new PrintStmt(expr.duplicate());
//    }
}
