package model.statement.fileManagement;

import model.utilities.Pair;
import model.PrgState;
import model.interfaces.IExpression;
import model.interfaces.IStmt;

import java.io.BufferedReader;

public class readFile implements IStmt {
    private IExpression expression;
    private String var;

    public readFile(IExpression expression, String var) {
        this.expression = expression;
        this.var = var;
    }

    public PrgState execute(PrgState state) throws Exception {
        int fileDescriptor = this.expression.eval(state.getSymTable(), state.getHeap());
        Pair <String, BufferedReader> reader = state.getFileTable().get(fileDescriptor);
        if (reader == null) {
            throw new Exception("FileNotOpenedException at: " + this.toString() + "\nNo such file descriptor: " + String.valueOf(fileDescriptor));
        }
        String line = reader.getSecond().readLine();
        int value = 0;
        if(line != null) {
            value = Integer.valueOf(line);
        }
        state.getSymTable().put(this.var, value);
        return null;
    }

    public String toString() {
        return "readFile(" + this.expression.toString() + ", " + this.var + ")";
    }
}
