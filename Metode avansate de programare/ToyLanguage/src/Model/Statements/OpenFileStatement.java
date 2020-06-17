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
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenFileStatement implements IStmt {

    private Expr expr;

    public OpenFileStatement(Expr expr){
        this.expr = expr;
    }

    @Override
    public String toString(){
        return "open("+ this.expr.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, FileNotFoundException {
        Value v;
        v = this.expr.evaluate(state.getSymTable(), state.getHeap());
        if(v.getType().equals(new StringType())){
            StringValue string = (StringValue)v;
            if(!(state.getFileTable().isDefined(string))){
                BufferedReader bufferedReader = new BufferedReader(new FileReader(string.getVal()));
                state.getFileTable().update(string,bufferedReader);
            }
            else throw new MyException("File already exists");
        }
        else throw new MyException("Not string type");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type expression = expr.typecheck(typeEnv);
        if(expression.equals(new StringType()))
            return typeEnv;
        else throw new MyException("The parameter of open file is not a string!");
    }

}
