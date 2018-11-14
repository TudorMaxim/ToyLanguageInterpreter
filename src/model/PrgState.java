package model;
import model.adt.MyDictionary;
import model.interfaces.MyIDictionary;
import model.interfaces.MyIList;
import model.interfaces.MyIStack;
import model.statement.IStmt;
import java.io.BufferedReader;
import java.util.Collection;
import java.util.Set;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Integer> symTable ;
    private MyIList<Integer> out;
    private MyIDictionary<Integer, Pair <String, BufferedReader> > fileTable;

    //IStmt originalProgram;

    public PrgState(MyIStack <IStmt> stk, MyIDictionary <String, Integer> dict, MyIList <Integer> list,
                                            MyIDictionary<Integer, Pair <String, BufferedReader> > fileTable, IStmt prg) {
        this.exeStack = stk;
        this.symTable = dict;
        this.out = list;
        this.fileTable = fileTable;
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
    public MyIDictionary <Integer, Pair <String, BufferedReader> > getFileTable() {
        return this.fileTable;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Exe Stack:\n");
        builder.append(exeStack.toString());
        builder.append("Sym Table:\n");
        builder.append(symTable.toString());
        builder.append("Out:\n");
        builder.append(out.toString()) ;
        builder.append("File Table:\n");
        builder.append(fileTable.toString());
        return builder.toString();
    }

}
