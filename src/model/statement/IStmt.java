package model.statement;

import model.PrgState;
import model.expression.IExpression;

public interface IStmt {
    String toString();
    //IStmt duplicate();
    PrgState execute(PrgState prg) throws Exception;
}
