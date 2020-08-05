package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.ADT.MyStack;
import Model.Exceptions.MyException;
import Model.ProgramState.ProgramState;
import Model.Types.Type;

import java.io.IOException;

public class ForkStatement implements IStmt {

    private IStmt stmt;

    public ForkStatement(IStmt s){
        this.stmt = s;
    }



    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        return new ProgramState(new MyStack<>(), state.getSymTable().clone(), state.getOut(), stmt, state.getFileTable(), state.getHeap());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        stmt.typecheck(typeEnv.clone());
        return typeEnv;
    }


    @Override
    public String toString(){return "fork("+this.stmt.toString()+")";}

}
