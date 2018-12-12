package model.statement.threading;

import model.PrgState;
import model.adt.MyStack;
import model.interfaces.IStmt;

public class ForkStmt implements IStmt {
    private IStmt state;

    public ForkStmt (IStmt state) {
        this.state = state;
    }
    public PrgState execute(PrgState prg) throws Exception {
        return new PrgState(
            prg.getId() * 10,
                new MyStack<IStmt>(),
                prg.getSymTable().duplicate(),
                prg.getOut(),
                prg.getFileTable(),
                prg.getHeap(),
                state
        );
    }

    public String toString() {
        return "fork(" + state.toString() + ")";
    }
}
