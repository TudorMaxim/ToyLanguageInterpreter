package views;
import controller.Controller;
import model.Pair;
import model.PrgState;
import model.adt.MyDictionary;
import model.adt.MyList;
import model.adt.MyStack;
import model.commands.ExitCommand;
import model.commands.RunExample;
import model.expression.ArithmExpr;
import model.expression.ConstExpr;
import model.expression.VarExpr;
import model.interfaces.MyIDictionary;
import model.interfaces.MyIList;
import model.interfaces.MyIStack;
import model.statement.*;
import repository.IRepo;
import repository.Repo;

import java.io.BufferedReader;

public class Interpreter {
    public static void main(String argv[]) {
        IStmt ex1 = new CompStmt(
                new AssignStmt("v", new ConstExpr(2)),
                new PrintStmt(new VarExpr("v"))
        );
        MyIStack<IStmt> exeStack1 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> symTable1 = new MyDictionary<String, Integer>();
        MyIList<Integer> out1 = new MyList<Integer>();
        MyIDictionary <Integer, Pair<String, BufferedReader>> fileTable1 = new MyDictionary <Integer, Pair<String, BufferedReader>>();
        PrgState program1 = new PrgState(exeStack1, symTable1, out1, fileTable1, ex1);
        IRepo repository1 = new Repo(program1, "result.out");
        Controller ctrl1 = new Controller(repository1);

        IStmt ex2 = new CompStmt(
                new AssignStmt("a",
                        new ArithmExpr(
                                new ConstExpr(2),
                                '+',
                                new ArithmExpr(new ConstExpr(3), '*', new ConstExpr(5))
                        )
                ),
                new CompStmt(new AssignStmt("b",
                        new ArithmExpr(new VarExpr("a"),
                                '+',
                                new ConstExpr(1))),
                        new PrintStmt(new VarExpr("b"))
                )
        );
        MyIStack<IStmt> exeStack2 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> symTable2 = new MyDictionary<String, Integer>();
        MyIList<Integer> out2 = new MyList<Integer>();
        MyIDictionary <Integer, Pair<String, BufferedReader>> fileTable2 = new MyDictionary <Integer, Pair<String, BufferedReader>>();
        PrgState program2 = new PrgState(exeStack2, symTable2, out2, fileTable2, ex2);
        IRepo repository2 = new Repo(program2, "result.out");
        Controller ctrl2 = new Controller(repository2);

        IStmt ex3 = new CompStmt(
                new AssignStmt("a",
                        new ArithmExpr(new ConstExpr(2), '-',
                                new ConstExpr(2)
                        )
                ),
                new CompStmt(
                        new CondStmt(new VarExpr("a"),
                                new AssignStmt("v", new ConstExpr(2)),
                                new AssignStmt("v", new ConstExpr(3))),
                        new PrintStmt(new VarExpr("v")))
        );
        MyIStack<IStmt> exeStack3 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> symTable3 = new MyDictionary<String, Integer>();
        MyIList<Integer> out3 = new MyList<Integer>();
        MyIDictionary <Integer, Pair<String, BufferedReader>> fileTable3 = new MyDictionary <Integer, Pair<String, BufferedReader>>();
        PrgState program3 = new PrgState(exeStack3, symTable3, out3, fileTable3, ex3);
        IRepo repository3 = new Repo(program3, "result.out");
        Controller ctrl3 = new Controller(repository3);

        IStmt errorNotDefined = new PrintStmt(new VarExpr("X"));
        MyIStack<IStmt> exeStack4 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> symTable4 = new MyDictionary<String, Integer>();
        MyIList<Integer> out4 = new MyList<Integer>();
        MyIDictionary <Integer, Pair<String, BufferedReader>> fileTable4 = new MyDictionary <Integer, Pair<String, BufferedReader>>();
        PrgState program4 = new PrgState(exeStack4, symTable4, out4, fileTable4, errorNotDefined);
        IRepo repository4 = new Repo(program4, "result.out");
        Controller ctrl4 = new Controller(repository4);

        IStmt errorDivByZero = new PrintStmt(new ArithmExpr(new ConstExpr(5), '/', new ConstExpr(0)));
        MyIStack<IStmt> exeStack5 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> symTable5 = new MyDictionary<String, Integer>();
        MyIList<Integer> out5 = new MyList<Integer>();
        MyIDictionary <Integer, Pair<String, BufferedReader>> fileTable5 = new MyDictionary <Integer, Pair<String, BufferedReader>>();
        PrgState program5 = new PrgState(exeStack5, symTable5, out5, fileTable5, errorDivByZero);
        IRepo repository5 = new Repo(program5, "result.out");
        Controller ctrl5 = new Controller(repository5);

        /*
            openRFile(var_f,"test.in");
            readFile(var_f,var_c);print(var_c);
            (if var_c then readFile(var_f,var_c);print(var_c)
            else print(0));
            closeRFile(var_f)
         */
        IStmt ex6 = new CompStmt(
                new openRFile("var_f", "test.in"),
                new CompStmt(
                        new readFile( new VarExpr("var_f"), "var_c"),
                        new CompStmt(
                                new PrintStmt(new VarExpr("var_c")),
                                new CompStmt(
                                        new CondStmt(
                                                new VarExpr("var_c"),
                                                new CompStmt(
                                                        new readFile(new VarExpr("var_f"), "var_c"),
                                                        new PrintStmt(new VarExpr("var_c"))
                                                ),
                                                new PrintStmt(new ConstExpr(0))
                                        ),
                                        new closeRFile(new VarExpr("var_f"))
                                )
                        )
                )
        );

        MyIStack<IStmt> exeStack6 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> symTable6 = new MyDictionary<String, Integer>();
        MyIList<Integer> out6 = new MyList<Integer>();
        MyIDictionary <Integer, Pair<String, BufferedReader>> fileTable6 = new MyDictionary <Integer, Pair<String, BufferedReader>>();
        PrgState program6 = new PrgState(exeStack6, symTable6, out6, fileTable6, ex6);
        IRepo repository6 = new Repo(program6, "result.out");
        Controller ctrl6 = new Controller(repository6);

        IStmt ex7 = new CompStmt(
                new openRFile("var_f", "test.in"),
                new CompStmt(
                        new readFile( new VarExpr("var_f + 2"), "var_c"),
                        new CompStmt(
                                new PrintStmt(new VarExpr("var_c")),
                                new CompStmt(
                                        new CondStmt(
                                                new VarExpr("var_c"),
                                                new CompStmt(
                                                        new readFile(new VarExpr("var_f"), "var_c"),
                                                        new PrintStmt(new VarExpr("var_c"))
                                                ),
                                                new PrintStmt(new ConstExpr(0))
                                        ),
                                        new closeRFile(new VarExpr("var_f"))
                                )
                        )
                )
        );
        MyIStack<IStmt> exeStack7 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> symTable7 = new MyDictionary<String, Integer>();
        MyIList<Integer> out7 = new MyList<Integer>();
        MyIDictionary <Integer, Pair<String, BufferedReader>> fileTable7 = new MyDictionary <Integer, Pair<String, BufferedReader>>();
        PrgState program7 = new PrgState(exeStack7, symTable7, out7, fileTable7, ex7);
        IRepo repository7 = new Repo(program7, "result.out");
        Controller ctrl7 = new Controller(repository7);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),ctrl1));
        menu.addCommand(new RunExample("2",ex2.toString(),ctrl2));
        menu.addCommand(new RunExample("3",ex3.toString(),ctrl3));
        menu.addCommand(new RunExample("4", errorNotDefined.toString(), ctrl4));
        menu.addCommand(new RunExample("5", errorDivByZero.toString(), ctrl5));
        menu.addCommand(new RunExample("6", ex6.toString(), ctrl6));
        menu.addCommand(new RunExample("7", ex7.toString(), ctrl7));
        menu.show();
    }
}
