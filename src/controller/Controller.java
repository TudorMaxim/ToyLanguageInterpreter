package controller;

import model.PrgState;
import model.interfaces.MyIStack;
import model.interfaces.IStmt;
import repository.IRepo;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    private IRepo repo;

    public Controller(IRepo repo) {
        this.repo = repo;
    }

    private Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer,Integer> heap){
        return heap.entrySet().stream()
                .filter(e->symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private PrgState oneStep(PrgState program) throws Exception {
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
            program.getHeap().setContent(conservativeGarbageCollector(
                    program.getSymTable().getContent().values(),
                    program.getHeap().getContent()));
            System.out.println(program.toString());
            repo.logPrgStateExec(program);
        }
    }
}
