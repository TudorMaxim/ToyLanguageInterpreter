package model.statement.heapManagement;
import model.interfaces.IExpression;
import model.interfaces.MyIDictionary;
import model.interfaces.MyIHeap;

public class ReadHeap implements IExpression {
    private String varName;

    public ReadHeap(String varName) {
        this.varName = varName;
    }

    public int eval(MyIDictionary<String, Integer> symTable, MyIHeap<Integer> heap) throws Exception {
        Integer address = symTable.get(this.varName);
        if(address == null) {
            throw new Exception("Unknown variable exception\nError at ReadHeapExp: " + toString());
        }
        Integer heapVal = heap.readAddr(address);
        if(heapVal == null) {
            throw new Exception("There is no such memory address\nError at ReadHeap: " + toString());
        }
        return heapVal;
    }

    public String toString() {
        return "readHeap(" + this.varName + ")";
    }
}
