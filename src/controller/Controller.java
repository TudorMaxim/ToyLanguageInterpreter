package controller;

import model.PrgState;
import model.interfaces.MyIStack;
import model.statement.IStmt;
import repository.IRepo;

public class Controller {
    private IRepo repo;

    public Controller(IRepo repo) {
        this.repo = repo;
    }

    public PrgState oneStep(PrgState program) throws Exception {
        MyIStack <IStmt> exeStack = program.getExeStack();
        IStmt currentState = exeStack.top();
        exeStack.pop();
        return currentState.execute(program);
    }

    public void allSteps() throws Exception {
        PrgState program = repo.getCurrentProgram();
        MyIStack <IStmt> exeStack = program.getExeStack();
        while (!exeStack.empty()) {
            oneStep(program);
            System.out.println(program.toString());
        }
    }
}
