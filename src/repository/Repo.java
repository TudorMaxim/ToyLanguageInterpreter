package repository;
import model.PrgState;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Repo implements IRepo{
    private String logFile;
    private ArrayList <PrgState> prgStates;
    private PrintWriter printWriter;
    private boolean firstTime;

    public Repo(PrgState prg, String logFile) {
        this.prgStates = new ArrayList<PrgState>();
        this.prgStates.add(prg);
        this.firstTime = true;
        this.printWriter = null;
        this.logFile = logFile;
    }

    public Repo(ArrayList <PrgState> prgStates, String logFile) {
        this.prgStates = prgStates;
        this.firstTime = true;
        this.printWriter = null;
        this.logFile = logFile;
    }

    public void add(PrgState prg) {
        prgStates.add(prg);
    }

    public PrgState getCurrentProgram() {
        return this.prgStates.get(0);
    }


    @Override
    public void logPrgStateExec(PrgState state) throws Exception {
        if(firstTime) {
            /// first, clear the content
            try {
                PrintWriter writer = new PrintWriter(new File(logFile));
                writer.print("");
                writer.flush();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            firstTime = false;
        }
        this.printWriter = new PrintWriter(new FileWriter(logFile, true));
        this.printWriter.println("ExeStack:");
        this.printWriter.println(state.getExeStack().toString());
        this.printWriter.println("SymTable:");
        this.printWriter.println(state.getSymTable().toString());
        this.printWriter.println("Out:");
        this.printWriter.println(state.getOut().toString());
        this.printWriter.println("FileTable:");
        this.printWriter.println(state.getFileTable().toString());
        this.printWriter.close();
    }
}
