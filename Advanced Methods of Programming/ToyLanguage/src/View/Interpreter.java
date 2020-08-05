package View;

import Controller.Controller;
import Model.ADT.*;
import Model.Exceptions.MyException;
import Model.ProgramState.ProgramState;
import Model.Statements.IStmt;
import Model.Values.*;
import Model.Statements.*;
import Model.Types.*;
import Model.Expressions.*;
import Repository.IRepository;
import Repository.Repository;

import java.awt.print.Printable;
import java.io.BufferedReader;

public interface Interpreter {

    public static void main(String[] args) throws InterruptedException {
        IStmt ex1= new CompoundStatement(new VarDeclStatement("v",new IntType()),
                new CompoundStatement(new AssignStatement("v",new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
        IStmt ex2 = new CompoundStatement( new VarDeclStatement("a",new IntType()),
                new CompoundStatement(new VarDeclStatement("b",new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntValue(2)),new ArithmeticExpression('*',new ValueExpression(new IntValue(3)),
                                new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignStatement("b",new ArithmeticExpression('+',new VariableExpression("a"),
                                        new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        IStmt ex3 = new CompoundStatement(new VarDeclStatement("a",new BoolType()),
                new CompoundStatement(new VarDeclStatement("v", new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true)))
                                , new CompoundStatement(new IfStmt(new VariableExpression("a"),new AssignStatement("v",new ValueExpression(new IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));
        IStmt ex4 = new CompoundStatement(
                new VarDeclStatement("varf",new StringType()),new CompoundStatement(
                new AssignStatement("varf",new ValueExpression(new StringValue("test.in"))),
                new CompoundStatement(
                        new OpenFileStatement(new VariableExpression("varf")),
                        new CompoundStatement(
                                new VarDeclStatement("varc",new IntType()),
                                new CompoundStatement(
                                        new ReadFileStatement(new VariableExpression("varf"),"varc"),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("varc")),new CompoundStatement(
                                                new ReadFileStatement(new VariableExpression("varf"),"varc") ,
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),new CloseFileStatement(new VariableExpression("varf"))))))))));

        /*IStmt ex5 = new CompoundStatement(new CompoundStatement(
                new VarDeclStatement("v",new IntType()),
                new CompoundStatement(
                        new AssignStatement("v",new ValueExpression(new IntValue(10))),
                        new WhileStatement(
                                new RelationalExpression(new VariableExpression("v"),new ValueExpression(new IntValue(0)),">"),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                        new AssignStatement( "v",new ArithmeticExpression('-',new VariableExpression("v"),new ValueExpression(new IntValue(1))))
                                )
                        )
                )
        ), new PrintStatement(new VariableExpression("v")));*/

        IStmt ex5 = new CompoundStatement(
                new VarDeclStatement("v",new IntType()),
                new CompoundStatement(
                        new AssignStatement("v",new ValueExpression(new IntValue(10))),
                        new WhileStatement(
                                new RelationalExpression(new VariableExpression("v"),new ValueExpression(new IntValue(0)),">"),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                        new AssignStatement( "v",new ArithmeticExpression('-',new VariableExpression("v"),new ValueExpression(new IntValue(1))))
                                )
                        )));


        IStmt ex6 = new CompoundStatement(
                new VarDeclStatement("v",new RefType(new IntType())),
                new CompoundStatement(
                        new HeapAllocationStatement("v",new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VarDeclStatement("a",new RefType(new RefType(new  IntType()))), new CompoundStatement(
                                new HeapAllocationStatement("a",new VariableExpression("v")),new CompoundStatement(
                                new HeapAllocationStatement("v",new ValueExpression(new IntValue(30))),
                                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression( new VariableExpression("a")))))))));
        IStmt ex7 = new CompoundStatement(
                new VarDeclStatement("v",new RefType(new IntType())),
                new CompoundStatement(
                        new HeapAllocationStatement("v",new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))), new CompoundStatement(
                                new VarDeclStatement("a",new RefType(new RefType(new  IntType()))), new CompoundStatement(
                                new HeapAllocationStatement("a",new VariableExpression("v")),new CompoundStatement(
                                new HeapWritingStatement("v",new ValueExpression(new IntValue(30))),
                                new PrintStatement(new ArithmeticExpression('+' ,new HeapReadingExpression(new HeapReadingExpression( new VariableExpression("a"))),new ValueExpression(new IntValue(5))))))))));



        // IStmt forked = new CompoundStatement(new HeapWritingStatement("a",new ValueExpression(new IntValue(30))),
        //       new CompoundStatement(new AssignStatement("v",new ValueExpression(new IntValue(32))),
        //             new CompoundStatement(new PrintStatement(new VariableExpression("v")),new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))));

        IStmt ex8 = new CompoundStatement(new VarDeclStatement("v", new BoolType()),
                new CompoundStatement(new VarDeclStatement("a",new RefType(new IntType())),
                        new CompoundStatement(new AssignStatement("v",new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new HeapAllocationStatement("a",new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(new ForkStatement(new CompoundStatement(new HeapWritingStatement("a",new ValueExpression(new IntValue(30))),
                                                new CompoundStatement(new AssignStatement("v",new ValueExpression(new IntValue(32))),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))),new CompoundStatement(new PrintStatement(new VariableExpression("v")),new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))
                        )));

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "Exit"));

        ProgramState prg1 = new ProgramState(ex1);
        IRepository repo1 = new Repository( "log1.txt");
        Controller ctrl1 = new Controller(repo1);
        ctrl1.addProgram(prg1);
        try {
            ex1.typecheck(new MyDictionary<String ,Type>());
            menu.addCommand(new RunExample("1",ex1.toString(),ctrl1));
        }
        catch (MyException e){
            System.out.println(e);
        }



        ProgramState prg2 = new ProgramState(ex2);
        IRepository repo2 = new Repository( "log2.txt");
        Controller ctrl2 = new Controller(repo2);
        ctrl2.addProgram(prg2);
        try {
            ex2.typecheck(new MyDictionary<String ,Type>());
            menu.addCommand(new RunExample("2",ex2.toString(),ctrl2));
        }
        catch (MyException e){
            System.out.println(e);
        }



        ProgramState prg3 = new ProgramState(ex3);
        IRepository repo3 = new Repository("log3.txt");
        Controller ctrl3 = new Controller(repo3);
        ctrl3.addProgram(prg3);
        try {
            ex3.typecheck(new MyDictionary<String ,Type>());
            menu.addCommand(new RunExample("3",ex3.toString(),ctrl3));
        }
        catch (MyException e){
            System.out.println(e);
        }



        ProgramState prg4 = new ProgramState(ex4);
        IRepository repo4 = new Repository("log4.txt");
        Controller ctrl4 = new Controller(repo4);
        ctrl4.addProgram(prg4);
        try {
            ex4.typecheck(new MyDictionary<String ,Type>());
            menu.addCommand(new RunExample("4",ex4.toString(),ctrl4));
        }
        catch (MyException e){
            System.out.println(e);
        }

        ProgramState prg5 = new ProgramState(ex5);
        IRepository repo5 = new Repository( "log5.txt");
        Controller ctrl5 = new Controller(repo5);
        ctrl5.addProgram(prg5);
        try {
            ex5.typecheck(new MyDictionary<String ,Type>());
            menu.addCommand(new RunExample("5",ex5.toString(),ctrl5));
        }
        catch (MyException e){
            System.out.println(e);
        }


        ProgramState prg6 = new ProgramState(ex6);
        IRepository repo6 = new Repository( "log6.txt");
        Controller ctrl6 = new Controller(repo6);
        ctrl6.addProgram(prg6);
        try {
            ex6.typecheck(new MyDictionary<String ,Type>());
            menu.addCommand(new RunExample("6",ex6.toString(),ctrl6));
        }
        catch (MyException e){
            System.out.println(e);
        }


        ProgramState prg7 = new ProgramState(ex7);
        IRepository repo7 = new Repository( "log7.txt");
        Controller ctrl7 = new Controller(repo7);
        ctrl7.addProgram(prg7);
        try {
            ex7.typecheck(new MyDictionary<String ,Type>());
            menu.addCommand(new RunExample("7",ex7.toString(),ctrl7));
        }
        catch (MyException e){
            System.out.println(e);
        }

        ProgramState prg8 = new ProgramState(ex8);
        IRepository repo8 = new Repository( "log8.txt");
        Controller ctrl8 = new Controller(repo8);
        ctrl8.addProgram(prg8);
        try {
            ex8.typecheck(new MyDictionary<String ,Type>());
            menu.addCommand(new RunExample("8",ex8.toString(),ctrl8));
        }
        catch (MyException e){
            System.out.println(e);
        }

        menu.show();

    }

}
