package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.Exceptions.MyException;
import Model.ProgramState.ProgramState;
import Model.Types.Type;

public class CompoundStatement implements IStmt {

    private IStmt first;
    private IStmt second;

    public CompoundStatement(IStmt first, IStmt second){
        this.first = first;
        this.second = second;
    }

    public IStmt getFirst() {
        return first;
    }

    public IStmt getSecond() {
        return second;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStmt> stack = state.getExeStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return second.typecheck(first.typecheck(typeEnv));
    }

    @Override
    public String toString() {
        return "("+this.first.toString() + ";"+this.second.toString() + ")";
    }
}
