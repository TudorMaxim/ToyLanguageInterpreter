package views;

import model.adt.MyDictionary;
import model.commands.Command;
import model.interfaces.MyIDictionary;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextMenu {
    private MyIDictionary<Integer, Command> cmds;
    public TextMenu() {
        this.cmds = new MyDictionary<>();
    }
    public void addCommand(Command cmd) {
        this.cmds.put(cmd.getKey(), cmd);
    }
    private void printMenu() {
        System.out.println("Available commands: ");
        for(Command cmd : this.cmds.getValues()) {
            String line = String.format("Command %s: %s", cmd.getKey().toString(), cmd.getDescription());
            System.out.println(line);
        }
    }

    public List<String> getCommandList() {
        List<String> l = new ArrayList<>();
        for(Command cmd: this.cmds.getValues())
            l.add(cmd.getDescription());
        return l;
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        boolean keepGoing = true;
        while(keepGoing) {
            printMenu();
            System.out.println("Input the command: ");
            Command cmd = cmds.get(scanner.nextInt());
            if(cmd == null) {
                System.out.println("Invalid command");
                continue;
            }
            cmd.execute();
        }
    }
}