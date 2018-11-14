package model.statement;

import model.Pair;
import model.PrgState;
import model.expression.IExpression;

import java.io.BufferedReader;

public class closeRFile implements IStmt {
    private IExpression expression;

    public closeRFile(IExpression expression) {
        this.expression = expression;
    }

    public String toString() {
        return "closeRFile(" + this.expression.toString() + ")";
    }

    public PrgState execute(PrgState state) throws Exception {
        int fileDescriptor = this.expression.eval(state.getSymTable());
        Pair <String, BufferedReader> removedFile = state.getFileTable().remove(fileDescriptor);
        if(removedFile == null)
            throw new Exception("FileNotOpened Exception at: " + this.toString() + "\nThere is no opened file with fd = " + fileDescriptor);
        removedFile.getSecond().close();
        return null;
    }
}
