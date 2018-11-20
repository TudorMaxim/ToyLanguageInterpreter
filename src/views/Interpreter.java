package views;
import controller.Controller;
import model.adt.MyHeap;
import model.interfaces.*;
import model.statement.heapManagement.New;
import model.statement.heapManagement.ReadHeap;
import model.statement.heapManagement.WriteHeap;
import model.utilities.Pair;
import model.PrgState;
import model.adt.MyDictionary;
import model.adt.MyList;
import model.adt.MyStack;
import model.commands.ExitCommand;
import model.commands.RunExample;
import model.expression.ArithmExpr;
import model.expression.ConstExpr;
import model.expression.VarExpr;
import model.statement.basic.AssignStmt;
import model.statement.basic.CompStmt;
import model.statement.basic.CondStmt;
import model.statement.basic.PrintStmt;
import model.statement.fileManagement.closeRFile;
import model.statement.fileManagement.openRFile;
import model.statement.fileManagement.readFile;
import repository.IRepo;
import repository.Repo;

import java.io.BufferedReader;
import java.util.HashMap;

public class Interpreter {
    public static void main(String argv[]) {
        IStmt ex1 = new CompStmt(
                new AssignStmt("v", new ConstExpr(2)),
                new PrintStmt(new VarExpr("v"))
        );
        MyIStack<IStmt> exeStack1 = new MyStack<>();
        MyIDictionary<String, Integer> symTable1 = new MyDictionary<>();
        MyIList<Integer> out1 = new MyList<>();
        MyIDictionary <Integer, Pair<String, BufferedReader>> fileTable1 = new MyDictionary <>();
        MyIHeap<Integer> Heap1 = new MyHeap<>(new HashMap<>());
        PrgState program1 = new PrgState(exeStack1, symTable1, out1, fileTable1, Heap1, ex1);
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
        MyIStack<IStmt> exeStack2 = new MyStack<>();
        MyIDictionary<String, Integer> symTable2 = new MyDictionary<>();
        MyIList<Integer> out2 = new MyList<>();
        MyIDictionary <Integer, Pair<String, BufferedReader>> fileTable2 = new MyDictionary <>();
        MyIHeap <Integer> Heap2 = new MyHeap<>(new HashMap<>());
        PrgState program2 = new PrgState(exeStack2, symTable2, out2, fileTable2, Heap2, ex2);
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
        MyIStack<IStmt> exeStack3 = new MyStack<>();
        MyIDictionary<String, Integer> symTable3 = new MyDictionary<>();
        MyIList<Integer> out3 = new MyList<>();
        MyIDictionary <Integer, Pair<String, BufferedReader>> fileTable3 = new MyDictionary<>();
        MyIHeap <Integer> Heap3 = new MyHeap<>(new HashMap<>());
        PrgState program3 = new PrgState(exeStack3, symTable3, out3, fileTable3, Heap3, ex3);
        IRepo repository3 = new Repo(program3, "result.out");
        Controller ctrl3 = new Controller(repository3);

        IStmt errorNotDefined = new PrintStmt(new VarExpr("X"));
        MyIStack<IStmt> exeStack4 = new MyStack<>();
        MyIDictionary<String, Integer> symTable4 = new MyDictionary<>();
        MyIList<Integer> out4 = new MyList<>();
        MyIDictionary <Integer, Pair<String, BufferedReader>> fileTable4 = new MyDictionary<>();
        MyIHeap <Integer> Heap4 = new MyHeap<>(new HashMap<>());
        PrgState program4 = new PrgState(exeStack4, symTable4, out4, fileTable4, Heap4, errorNotDefined);
        IRepo repository4 = new Repo(program4, "result.out");
        Controller ctrl4 = new Controller(repository4);

        IStmt errorDivByZero = new PrintStmt(new ArithmExpr(new ConstExpr(5), '/', new ConstExpr(0)));
        MyIStack<IStmt> exeStack5 = new MyStack<>();
        MyIDictionary<String, Integer> symTable5 = new MyDictionary<>();
        MyIList<Integer> out5 = new MyList<>();
        MyIHeap <Integer> Heap5 = new MyHeap<>(new HashMap<>());
        MyIDictionary <Integer, Pair<String, BufferedReader>> fileTable5 = new MyDictionary<>();
        PrgState program5 = new PrgState(exeStack5, symTable5, out5, fileTable5, Heap5, errorDivByZero);
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

        MyIStack<IStmt> exeStack6 = new MyStack<>();
        MyIDictionary<String, Integer> symTable6 = new MyDictionary<>();
        MyIList<Integer> out6 = new MyList<>();
        MyIDictionary <Integer, Pair<String, BufferedReader>> fileTable6 = new MyDictionary<>();
        MyIHeap <Integer> Heap6 = new MyHeap<>(new HashMap<>());
        PrgState program6 = new PrgState(exeStack6, symTable6, out6, fileTable6, Heap6, ex6);
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
        MyIStack<IStmt> exeStack7 = new MyStack<>();
        MyIDictionary<String, Integer> symTable7 = new MyDictionary<>();
        MyIList<Integer> out7 = new MyList<>();
        MyIDictionary <Integer, Pair<String, BufferedReader>> fileTable7 = new MyDictionary<>();
        MyIHeap <Integer> Heap7 = new MyHeap<>(new HashMap<>());
        PrgState program7 = new PrgState(exeStack7, symTable7, out7, fileTable7, Heap7, ex7);
        IRepo repository7 = new Repo(program7, "result.out");
        Controller ctrl7 = new Controller(repository7);

        //A4
        //v=10; new(v,20); new(a,22); print(v)
        IStmt ex8 = new CompStmt(
                new AssignStmt("v", new ConstExpr(10)),
                new CompStmt(
                        new New("v", new ConstExpr(20)),
                        new CompStmt(
                                new New("a", new ConstExpr(22)),
                                new PrintStmt(new VarExpr("v"))
                        )
                )
        );
        MyIStack<IStmt> exeStack8 = new MyStack<>();
        MyIDictionary<String, Integer> symTable8 = new MyDictionary<>();
        MyIList<Integer> out8 = new MyList<>();
        MyIDictionary <Integer, Pair<String, BufferedReader>> fileTable8 = new MyDictionary<>();
        MyIHeap <Integer> Heap8 = new MyHeap<>(new HashMap<>());
        PrgState program8 = new PrgState(exeStack8, symTable8, out8, fileTable8, Heap8, ex8);
        IRepo repository8 = new Repo(program8, "result.out");
        Controller ctrl8 = new Controller(repository8);

        //x=1; new(v,20); new(a,22); print(x); print(v);
        IStmt ex9 = new CompStmt(
                new AssignStmt("x", new ConstExpr(1)),
                new CompStmt(
                        new New("v", new ConstExpr(20)),
                        new CompStmt(
                                new New("a", new ConstExpr(22)),
                                new CompStmt(
                                        new PrintStmt(new VarExpr("x")),
                                        new PrintStmt(new VarExpr("v"))
                                )
                        )
                )
        );
        MyIStack<IStmt> exeStack9 = new MyStack<>();
        MyIDictionary<String, Integer> symTable9 = new MyDictionary<>();
        MyIList<Integer> out9 = new MyList<>();
        MyIDictionary <Integer, Pair<String, BufferedReader>> fileTable9 = new MyDictionary<>();
        MyIHeap <Integer> Heap9 = new MyHeap<>(new HashMap<>());
        PrgState program9 = new PrgState(exeStack9, symTable9, out9, fileTable9, Heap9, ex9);
        IRepo repository9 = new Repo(program9, "result.out");
        Controller ctrl9 = new Controller(repository9);

        //v=10;new(v,20);new(a,22);print(100+rH(v));print(100+rH(a))
        IStmt ex10 = new CompStmt(
                new AssignStmt("v", new ConstExpr(10)),
                new CompStmt(
                        new New("v", new ConstExpr(20)),
                        new CompStmt(
                                new New("a", new ConstExpr(22)),
                                new CompStmt(
                                        new PrintStmt(
                                                new ArithmExpr(
                                                    new ConstExpr(100),
                                                        '+',
                                                    new ReadHeap("v")
                                                )
                                        ),
                                        new PrintStmt(
                                                new ArithmExpr(
                                                        new ConstExpr(100),
                                                        '+',
                                                        new ReadHeap("a")
                                                )
                                        )
                                )
                        )
                )
        );
        MyIStack<IStmt> exeStack10 = new MyStack<>();
        MyIDictionary<String, Integer> symTable10 = new MyDictionary<>();
        MyIList<Integer> out10 = new MyList<>();
        MyIDictionary <Integer, Pair<String, BufferedReader>> fileTable10 = new MyDictionary<>();
        MyIHeap <Integer> Heap10 = new MyHeap<>(new HashMap<>());
        PrgState program10 = new PrgState(exeStack10, symTable10, out10, fileTable10, Heap10, ex10);
        IRepo repository10 = new Repo(program10, "result.out");
        Controller ctrl10 = new Controller(repository10);

        // v=10;new(v,20);new(a,22);wH(a,30);print(a);print(rH(a))
        IStmt ex11 = new CompStmt(
                new AssignStmt("v", new ConstExpr(10)),
                new CompStmt(
                        new New("v", new ConstExpr(20)),
                        new CompStmt(
                                new New("a", new ConstExpr(22)),
                                new CompStmt(
                                        new WriteHeap(
                                                "a",
                                                new ConstExpr(30)
                                        ),
                                        new CompStmt(
                                                new PrintStmt(new VarExpr("a")),
                                                new PrintStmt(new ReadHeap("a"))
                                        )
                                )
                        )
                )
        );
        MyIStack<IStmt> exeStack11 = new MyStack<>();
        MyIDictionary<String, Integer> symTable11 = new MyDictionary<>();
        MyIList<Integer> out11 = new MyList<>();
        MyIDictionary <Integer, Pair<String, BufferedReader>> fileTable11 = new MyDictionary<>();
        MyIHeap <Integer> Heap11 = new MyHeap<>(new HashMap<>());
        PrgState program11 = new PrgState(exeStack11, symTable11, out11, fileTable11, Heap11, ex11);
        IRepo repository11 = new Repo(program11, "result.out");
        Controller ctrl11 = new Controller(repository11);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand(0, "exit"));
        menu.addCommand(new RunExample(1, ex1.toString(), ctrl1));
        menu.addCommand(new RunExample(2, ex2.toString(), ctrl2));
        menu.addCommand(new RunExample(3, ex3.toString(), ctrl3));
        menu.addCommand(new RunExample(4, errorNotDefined.toString(), ctrl4));
        menu.addCommand(new RunExample(5, errorDivByZero.toString(), ctrl5));
        menu.addCommand(new RunExample(6, ex6.toString(), ctrl6));
        menu.addCommand(new RunExample(7, ex7.toString(), ctrl7));
        menu.addCommand(new RunExample(8, ex8.toString(), ctrl8));
        menu.addCommand(new RunExample(9, ex9.toString(), ctrl9));
        menu.addCommand(new RunExample(10, ex10.toString(), ctrl10));
        menu.addCommand(new RunExample(11, ex11.toString(), ctrl11));
        menu.show();
    }
}
