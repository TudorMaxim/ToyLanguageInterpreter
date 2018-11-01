package model.statement;
import model.PrgState;
import model.expression.IExpression;
import model.interfaces.MyIStack;

public class CondStmt implements IStmt {
    private IExpression expr;
    private IStmt Then;
    private IStmt Else;

    public CondStmt(IExpression expr, IStmt Then, IStmt Else) {
        this.expr = expr;
        this.Then = Then;
        this.Else = Else;
    }

    public String toString() {
        return "if (" + expr.toString() + ") then " + Then.toString() + " else " + Else.toString() + " end ";
    }

    public PrgState execute(PrgState state) throws Exception{
        MyIStack <IStmt> exeStack = state.getExeStack();
        if (this.expr.eval(state.getSymTable()) != 0) {
            exeStack.push(Then);
        } else {
            exeStack.push(Else);
        }
        return state;
    }

//    public IStmt duplicate() {
//        return new CondStmt(expr.duplicate(), Then.duplicate(), Else.duplicate());
//    }
}
