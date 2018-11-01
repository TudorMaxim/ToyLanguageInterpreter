package model;
import model.interfaces.MyIDictionary;
import model.interfaces.MyIList;
import model.interfaces.MyIStack;
import model.statement.IStmt;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Integer> symTable ;
    private MyIList<Integer> out;
    private MyIDictionary<Integer, String> fileTable;

    //IStmt originalProgram;

    public PrgState(MyIStack <IStmt> stk, MyIDictionary <String, Integer> dict, MyIList <Integer> list, IStmt prg) {
        this.exeStack = stk;
        this.symTable = dict;
        this.out = list;
        exeStack.push(prg);
    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }
    public MyIDictionary<String, Integer> getSymTable() {
        return symTable;
    }
    public MyIList<Integer> getOut() {
        return out;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Exe Stack:\n");
        builder.append(exeStack.toString());
        builder.append("Sym Table:\n");
        builder.append(symTable.toString());
        builder.append("Out:\n");
        builder.append(out.toString());
        return builder.toString();
    }

}
