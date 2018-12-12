package model.statement.basic;

import model.PrgState;
import model.interfaces.IExpression;
import model.interfaces.MyIDictionary;
import model.interfaces.IStmt;

public class AssignStmt implements IStmt {
    String name;
    IExpression expr;

    public AssignStmt(String name, IExpression expr) {
        this.name = name;
        this.expr = expr;
    }

    public PrgState execute(PrgState state) throws Exception{
        MyIDictionary <String, Integer> symTable = state.getSymTable();
        Integer ans = this.expr.eval(symTable, state.getHeap());
        symTable.put(this.name, ans);
        return null;
    }

    public String toString() {
        return name.toString() + " = " + expr.toString() + "; ";
    }

//    public IStmt duplicate() {
//        return new AssignStmt(new String(name), expr.duplicate());
//    }
}
