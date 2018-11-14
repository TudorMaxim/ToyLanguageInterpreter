package model.statement;
import model.Pair;
import model.PrgState;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class openRFile implements IStmt {
    private static int id = 0;
    private String descriptor, fileName;
    public openRFile(String descriptor, String fileName) {
        this.descriptor = descriptor;
        this.fileName = fileName;
    }

    public PrgState execute(PrgState prg) throws Exception {
        for(Pair<String, BufferedReader> act : prg.getFileTable().getValues()) {
            if (act.getFirst().equals(this.fileName)) {
                throw new Exception("FileAlreadyOpenedException at: " + this.toString() + "\nThe file " + this.fileName + " is already opened");
            }
        }

        File f = new File(this.fileName);
        if(!f.exists()) {
            throw new Exception("FileNotFoundException at: " + this.toString() + "\n" + "The file " + this.fileName + " does not exist");
        }
        int assignedKey = ++openRFile.id; /// static variable
        prg.getFileTable().put(assignedKey, new Pair<String, BufferedReader>(this.fileName, new BufferedReader(new FileReader(this.fileName))));
        prg.getSymTable().put(this.descriptor, assignedKey);
        return null;
    }
    public String toString() {
        return "openRFile(" + this.descriptor + ", " + this.fileName + ")";
    }
}
