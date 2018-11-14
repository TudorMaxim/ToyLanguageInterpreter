package repository;
import model.PrgState;

public interface IRepo {
    void add(PrgState program);
    PrgState getCurrentProgram();
    void logPrgStateExec(PrgState state) throws Exception;
}
