package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Expr;
import Model.ProgramState.ProgramState;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFileStatement implements IStmt {

    private Expr expr;

    @Override
    public String toString(){
        return "close(" + this.expr.toString() + ")";
    }

    public CloseFileStatement(Expr expr){
        this.expr = expr;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {

        Value v = expr.evaluate(state.getSymTable(), state.getHeap());
        if(v.getType().equals(new StringType())){
            StringValue stringValue = (StringValue)v;
            if(state.getFileTable().isDefined(stringValue)){
                BufferedReader bufferedReader = state.getFileTable().search(stringValue);
                bufferedReader.close();
                state.getFileTable().delete(stringValue);
            }
            else throw new MyException("File not defined");
        }
        else throw new MyException("Expression not string type");

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type expression = expr.typecheck(typeEnv);
        if(expression.equals(new StringType()))
            return typeEnv;
        else throw new MyException("The parameter of close file is not a string!");
    }
}
