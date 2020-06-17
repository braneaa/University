package Model.ProgramState;

import Model.ADT.*;
import Model.Exceptions.MyException;
import Model.Statements.IStmt;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ProgramState {

        private MyIStack<IStmt> exeStack;
        private MyIDictionary<String, Value> symTable;
        private MyIList<Value> out;
        private MyIDictionary<StringValue, BufferedReader> fileTable;
        private MyIHeap<Value> heap;
        private IStmt originalProgram;
        private int id;
        private static int globalID = 1;

        public ProgramState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, IStmt prg, MyIDictionary<StringValue, BufferedReader> fileTable, MyIHeap<Value> heap){
            exeStack = stk;
            symTable = symtbl;
            out = ot;
            originalProgram = prg;
            this.fileTable = fileTable;
            this.heap = heap;
            this.exeStack.push(originalProgram);
            id = getGlobalID();
        }

        public ProgramState(IStmt originalProgram){
            this.exeStack = new MyStack<IStmt>();
            this.symTable = new MyDictionary<String, Value>();
            this.out = new MyList<Value>();
            this.fileTable = new MyDictionary<StringValue, BufferedReader>();
            this.heap = new MyHeap<Value>();
            this.exeStack.push(originalProgram);
            id = 1;
        }

        @Override
        public String toString() {
            return "Program State:{" +
                    "executionStack = " + exeStack.toString() + "\n"+
                    "symbolTable = " + symTable.toString() + "\n" +
                    "heap = " + heap.toString() + "\n" +
                    "output = " + out.toString() + "}";
        }

        public boolean isNotCompleted(){
            return !(exeStack.isEmpty());
        }

        public ProgramState executeOneStep() throws MyException, IOException {
            if(exeStack.isEmpty()) throw new MyException("prgstate stack is empty");
            IStmt crtStmt = exeStack.pop();
            return crtStmt.execute(this);
        }

        public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

        public void setFileTable(MyIDictionary<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

        public MyIList<Value> getOut(){
            return out;
        }

        public void setOut(MyIList<Value> output){
            this.out = output;
        }

        public MyIDictionary<String, Value> getSymTable() {
            return symTable;
        }

        public void setSymTable(MyIDictionary<String, Value> symTable) {
            this.symTable = symTable;
        }

        public MyIStack<IStmt> getExeStack() {
            return exeStack;
        }

        public void setExeStack(MyIStack<IStmt> exeStack) {
            this.exeStack = exeStack;
        }

        public MyIHeap<Value> getHeap() {
        return heap;
    }

        public void setHeap(MyIHeap<Value> heap) {
        this.heap = heap;
    }

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public synchronized static int getGlobalID(){
            globalID*=10;
            return globalID;
        }



}


