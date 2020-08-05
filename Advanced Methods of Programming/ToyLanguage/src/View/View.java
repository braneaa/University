//package View;
//
//import Controller.Controller;
//import Model.ADT.*;
//import Model.Exceptions.MyException;
//import Model.Expressions.ArithmeticExpression;
//import Model.Expressions.ValueExpression;
//import Model.Expressions.VariableExpression;
//import Model.ProgramState.ProgramState;
//import Model.Statements.*;
//import Model.Types.BoolType;
//import Model.Types.IntType;
//import Model.Values.BoolValue;
//import Model.Values.IntValue;
//import Model.Values.Value;
//
//import java.util.Scanner;
//
//public class View {
//
//    private Controller controller;
//    private Scanner scanner = new Scanner(System.in);
//    private IStmt ex1 = new CompoundStatement(new VarDeclStatement("v",new IntType()), new CompoundStatement(new AssignStatement("v",new ValueExpression(new IntValue(2))),new PrintStatement(new VariableExpression("v"))));
//    private IStmt ex2 = new CompoundStatement(new VarDeclStatement("a", new IntType()), new CompoundStatement(new VarDeclStatement("b",new IntType()),
//            new CompoundStatement(new AssignStatement("a", new ArithmeticExpression((char) 1, new ValueExpression(new IntValue(2)),new ArithmeticExpression((char) 3,new ValueExpression(new IntValue(3)),
//                    new ValueExpression(new IntValue(5))))), new CompoundStatement(new AssignStatement("b", new ArithmeticExpression((char) 1, new VariableExpression("a"),
//                    new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
//
////    private IStmt ex3 = new CompoundStatement(new VarDeclStatement("a",new BoolType()), new CompoundStatement(new VarDeclStatement("v",new IntType())), new CompoundStatement((new AssignStatement("a",new ValueExpression(new BoolValue(true))), new CompoundStatement(new IfStmt(new VariableExpression("a",new AssignStatement("v", new ValueExpression(new IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v")))))));
//
//    private IStmt ex3 = new CompoundStatement(new VarDeclStatement("a",new BoolType()), new CompoundStatement(new VarDeclStatement("v", new IntType()),new CompoundStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true)))
//            , new CompoundStatement(new IfStmt(new VariableExpression("a"),new AssignStatement("v",new ValueExpression(new IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));
//
//
//    public View(Controller ctrl){
//        this.controller = ctrl;
//        int printFlag = 1;
//    }
//
//    private void initProgram(IStmt statement){
//        MyIStack<IStmt> exeStack = new MyStack<IStmt>();
//        MyIDictionary<String, Value> symbolTable = new MyDictionary<String, Value>();
//        MyIList<Value> output = new MyList<Value>();
//        ProgramState programState = new ProgramState(exeStack, symbolTable, output, statement);
//        this.controller.addProgram(programState);
//    }
//
//    private void chooseProgram() throws MyException {
//        System.out.println("Available programs:\n" +
//                "Press: 1 for 'int v;v=2;print(v)'\n" +
//                "2 for 'int a;int b; a=2+3*5;b=a+1;Print(b)'\n" +
//                "3 for 'bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)'");
//        int option;
//        try {
//            option = Integer.valueOf(this.scanner.nextLine());
//            switch (option) {
//                case 1:
//                    this.initProgram(ex1);
//                    this.controller.executeAll();
//                    break;
//                case 2:
//                    this.initProgram(ex2);
//                    this.controller.executeAll();
//                    break;
//                case 3:
//                    this.initProgram(ex3);
//                    this.controller.executeAll();
//                    break;
//                default:
//                    throw new MyException("Wrong cmd.");
//            }
//        } catch (NumberFormatException e) {
//            System.out.println(e.getMessage());
//        }
//    }
////    private void chooseExecMode() throws MyException{
////        System.out.println("1 for one-step\n" +
////                "2 for all steps\n" +
////                "3 to change the print flag  (Print flag is currently set to" + this.printFlag+")\n" +
////                "0 to exit");
////        int option;
////        try {
////            option = Integer.valueOf(this.scanner.nextLine());
////            switch (option) {
////                case 1:
////                    this.controller.executeOneStep(this.programState);
////                    break;
////                case 2:
////                    this.controller.executeAll();
////                    throw new MyException("Exit");
////                case 3:
////                    this.printFlag=1-this.printFlag;
////                    break;
////                case 0:
////                    throw new MyException("Exit");
////                default:
////                    throw new MyException("Wrong cmd.");
////            }
////        }
////        catch(MyException exc){
////            throw exc;
////        }
////    }
//
//    public void run() {
//        try {
//            chooseProgram();
//        } catch (MyException e) {
//            System.out.println(e.getMessage());
//            if (e.getMessage().equals("Exit"))
//            return;
//        }
////        while (true){
////            try{
////                chooseExecMode();
////            }
////            catch (MyException e){
////                System.out.println(e.getMessage());
////                if (e.getMessage().equals("Exit"))
////                    break;
////            }
////        }
//    }
//
//}
