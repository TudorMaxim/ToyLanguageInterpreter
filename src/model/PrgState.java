package model;
import model.interfaces.*;
import model.utilities.Pair;
import java.io.BufferedReader;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Integer> symTable ;
    private MyIList<Integer> out;
    private MyIDictionary<Integer, Pair<String, BufferedReader>> fileTable;
    //(address  content) pair (address 0 <=> null)
    private MyIHeap<Integer> Heap;

    public PrgState( MyIStack <IStmt> stk,
                     MyIDictionary <String, Integer> symTable,
                     MyIList <Integer> outList,
                     MyIDictionary<Integer, Pair <String, BufferedReader> > fileTable,
                     MyIHeap <Integer> heap,
                     IStmt prg) {

        this.exeStack = stk;
        this.symTable = symTable;
        this.out = outList;
        this.fileTable = fileTable;
        this.Heap = heap;
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
    public MyIHeap<Integer> getHeap() {
        return this.Heap;
    }

    public String toString() {
        return "Exe Stack:\n" + exeStack.toString() +
                "Sym Table:\n" + symTable.toString() +
                "Out:\n" + out.toString() +
                "File Table:\n" + fileTable.toString() +
                "Heap:\n" + Heap.toString();
    }

}
