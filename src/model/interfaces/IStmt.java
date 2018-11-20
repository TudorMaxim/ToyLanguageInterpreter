package model.interfaces;

import model.PrgState;

public interface IStmt {
    String toString();
    //IStmt duplicate();
    PrgState execute(PrgState prg) throws Exception;
}
