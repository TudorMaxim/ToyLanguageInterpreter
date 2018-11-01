package repository;
import model.PrgState;
import java.util.ArrayList;

public class Repo implements IRepo{
    private ArrayList <PrgState> prgStates;

    public Repo() {
        this.prgStates = new ArrayList<PrgState>();
    }

    public Repo(ArrayList <PrgState> prgStates) {
        this.prgStates = prgStates;
    }

    public void add(PrgState prg) {
        prgStates.add(prg);
    }

    public PrgState getCurrentProgram() {
        return prgStates.get(0);
    }

    public void logPrgStateExec() {

    }
}
