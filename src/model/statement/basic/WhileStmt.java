package model.statement.basic;
import model.PrgState;
import model.interfaces.IExpression;
import model.interfaces.IStmt;
import model.interfaces.MyIStack;

public class WhileStmt implements IStmt {
    private IExpression condition;
    private IStmt body;

    public WhileStmt (IExpression condition, IStmt body) {
        this.condition = condition;
        this.body = body;
    }

    public PrgState execute(PrgState state) throws Exception {
        MyIStack<IStmt> exeStack = state.getExeStack();
        if(condition.eval(state.getSymTable(), state.getHeap()) != 0) {
            exeStack.push(this);
            exeStack.push(body);
        }
        return null;
    }

    public String toString() {
        return "while (" + this.condition.toString() + ") do " + this.body.toString() + " end";
    }

}
