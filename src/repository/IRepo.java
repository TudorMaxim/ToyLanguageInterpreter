package repository;
import model.PrgState;
import model.interfaces.MyIDictionary;

import java.util.List;

public interface IRepo {
    void add(PrgState program);
    //PrgState getCurrentProgram();
    void logPrgStateExec(PrgState state) throws Exception;
    void setList (List<PrgState> list);
    List <PrgState> getList();
    MyIDictionary<String,Integer> mergeSymTables();
}
