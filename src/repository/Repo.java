package repository;
import model.PrgState;
import model.adt.MyDictionary;
import model.interfaces.MyIDictionary;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repo implements IRepo{
    private String logFile;
    private List <PrgState> prgStates;
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

    public List <PrgState> getList() {
        return this.prgStates;
    }

    public void setList(List <PrgState> list) {
        this.prgStates = list;
    }

    public void add(PrgState prg) {
        prgStates.add(prg);
    }

//    public PrgState getCurrentProgram() {
//        return this.prgStates.get(0);
//    }


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
        this.printWriter.append(state.toString());
        this.printWriter.flush();
        this.printWriter.close();
    }

    public MyIDictionary <String,Integer> mergeSymTables() {
        Map<String, Integer> mergedSymTable = new HashMap<String, Integer>();
        this.prgStates.forEach(program -> {
            mergedSymTable.putAll(program.getSymTable().getContent());
        });
        return new MyDictionary<String, Integer>(mergedSymTable);
    }
}
