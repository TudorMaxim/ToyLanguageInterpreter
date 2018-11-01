package views;
import controller.Controller;
import model.PrgState;
import model.adt.MyDictionary;
import model.adt.MyList;
import model.adt.MyStack;
import model.expression.ArithmExpr;
import model.expression.ConstExpr;
import model.expression.VarExpr;
import model.interfaces.MyIDictionary;
import model.interfaces.MyIList;
import model.interfaces.MyIStack;
import model.statement.*;
import repository.IRepo;
import repository.Repo;

import java.util.Scanner;

public class Interpreter {

    public static void menu() {
        System.out.println("Choose one of the programs");
        System.out.println("0 -> Exit");
        System.out.println("1 -> v = 2; Print(v);");
        System.out.println("2 -> a = 2 * 3 + 5; b = a + 1; Print(b)");
        System.out.println("3 -> a = 2 - 2; If a Then v = 2 Else v = 3); Print(v)");
        System.out.println("4 -> Print(X);");
        System.out.println("5 -> Print(5 / 0)");
    }
    public static void main(String argv[]) {
        boolean keepGoing = true;
        Scanner input = new Scanner(System.in);
        while (keepGoing) {
            menu();
            int option = input.nextInt();
            if (option == 0) {
                keepGoing = false;
            } else if (option == 1) {
                IStmt ex1 = new CompStmt(
                        new AssignStmt("v", new ConstExpr(2)),
                        new PrintStmt(new VarExpr("v"))
                );
                MyIStack<IStmt> exeStack1 = new MyStack<IStmt>();
                MyIDictionary<String, Integer> symTable1 = new MyDictionary<String, Integer>();
                MyIList<Integer> out1 = new MyList<Integer>();
                PrgState program1 = new PrgState(exeStack1, symTable1, out1, ex1);
                IRepo repository1 = new Repo();
                repository1.add(program1);
                Controller ctrl1 = new Controller(repository1);
                try {
                    ctrl1.allSteps();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (option == 2) {
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
                PrgState program2 = new PrgState(exeStack2, symTable2, out2, ex2);
                IRepo repository2 = new Repo();
                repository2.add(program2);
                Controller ctrl2 = new Controller(repository2);
                try {
                    ctrl2.allSteps();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (option == 3) {
                IStmt ex3 = new CompStmt(
                        new AssignStmt("a",
                                new ArithmExpr(new ConstExpr(2), '-',
                                        new ConstExpr(2))),
                        new CompStmt(
                                new CondStmt(new VarExpr("a"),
                                        new AssignStmt("v", new ConstExpr(2)),
                                        new AssignStmt("v", new ConstExpr(3))),
                                new PrintStmt(new VarExpr("v")))
                );
                MyIStack<IStmt> exeStack3 = new MyStack<IStmt>();
                MyIDictionary<String, Integer> symTable3 = new MyDictionary<String, Integer>();
                MyIList<Integer> out3 = new MyList<Integer>();
                PrgState program3 = new PrgState(exeStack3, symTable3, out3, ex3);
                IRepo repository3 = new Repo();
                repository3.add(program3);
                Controller ctrl3 = new Controller(repository3);
                try {
                    ctrl3.allSteps();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (option == 4) {
                IStmt errorNotDefined = new PrintStmt(new VarExpr("X"));
                MyIStack<IStmt> exeStack4 = new MyStack<IStmt>();
                MyIDictionary<String, Integer> symTable4 = new MyDictionary<String, Integer>();
                MyIList<Integer> out4 = new MyList<Integer>();
                PrgState program4 = new PrgState(exeStack4, symTable4, out4, errorNotDefined);
                IRepo repository4 = new Repo();
                repository4.add(program4);
                Controller ctrl4 = new Controller(repository4);
                try {
                    ctrl4.allSteps();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (option == 5) {
                IStmt errorDivByZero = new PrintStmt(new ArithmExpr(new ConstExpr(5), '/', new ConstExpr(0)));
                MyIStack<IStmt> exeStack5 = new MyStack<IStmt>();
                MyIDictionary<String, Integer> symTable5 = new MyDictionary<String, Integer>();
                MyIList<Integer> out5 = new MyList<Integer>();
                PrgState program5 = new PrgState(exeStack5, symTable5, out5, errorDivByZero);
                IRepo repository5 = new Repo();
                repository5.add(program5);
                Controller ctrl5 = new Controller(repository5);
                try {
                    ctrl5.allSteps();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                System.out.println("Invalid option");
            }
        }
    }
}
