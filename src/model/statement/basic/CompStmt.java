package model.statement.basic;

import model.PrgState;
import model.interfaces.MyIStack;
import model.interfaces.IStmt;

public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt second;

    public CompStmt(IStmt first, IStmt second) {
        this.first = first;
        this.second = second;
    }

    public PrgState execute(PrgState state) {
        MyIStack <IStmt> stack = state.getExeStack();
        stack.push(second);
        stack.push(first);
        return state;
    }
    public String toString() {
        return first + " " + second + " ";
    }

//    public IStmt duplicate() {
//        return new CompStmt(first.duplicate(), second.duplicate());
//    }
}
