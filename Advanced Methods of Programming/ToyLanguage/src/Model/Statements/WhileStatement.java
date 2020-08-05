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

public class WhileStatement implements IStmt {

    private Expr expr;
    private IStmt stmt;

    public WhileStatement(Expr expr, IStmt stmt){
        this.expr = expr;
        this.stmt = stmt;
    }

    @Override
    public String toString(){
        return "(while(" + expr.toString() + ")" + stmt.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        MyIDictionary<String, Value> symbolTable = state.getSymTable();
        Value result = expr.evaluate(symbolTable, state.getHeap());
        if(result.getType().equals(new BoolType())){
            BoolValue dResult = (BoolValue)result;
            if(dResult.getValue()){
                state.getExeStack().push(new WhileStatement(expr,stmt));
                state.getExeStack().push(stmt);
            }
        }
        else throw new MyException("Not boolean condition");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        MyIDictionary<String, Type> newEnv = typeEnv.clone();
        Type type = expr.typecheck(newEnv);
        if (type.equals(new BoolType()))
            return typeEnv;
        else
            throw new MyException("While statement - condition expression is not boolean");
    }
}
