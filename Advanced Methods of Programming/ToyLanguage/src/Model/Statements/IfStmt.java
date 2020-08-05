package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Expr;
import Model.ProgramState.ProgramState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

import java.io.IOException;

public class IfStmt implements IStmt {

    private Expr expr;
    private IStmt statement1, statement2;

    public IfStmt(Expr expr, IStmt statement1, IStmt statement2){
        this.expr = expr;
        this.statement1 = statement1;
        this.statement2 = statement2;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        Value result = this.expr.evaluate(state.getSymTable(), state.getHeap());
        if(((BoolValue) result).getValue())
            this.statement1.execute(state);
        else
            this.statement2.execute(state);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp = expr.typecheck(typeEnv);
        if(typexp.equals(new BoolType())){
            statement1.typecheck(typeEnv);
            statement2.typecheck(typeEnv);
            return typeEnv.clone();
        }
        else throw new MyException("The condition of IF has not the type bool");
    }

    @Override
    public String toString() {
        return "If " + this.expr.toString() + " then " + this.statement1.toString() + " else "+this.statement2.toString();
    }
}
