package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Expr;
import Model.ProgramState.ProgramState;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFileStatement implements IStmt {

    private Expr expr;
    private String file;

    @Override
    public String toString(){
        return "read("+this.expr.toString() + "," + this.file.toString() + ")";
    }

    public ReadFileStatement(Expr expr, String file){
        this.expr = expr;
        this.file = file;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {

        if(state.getSymTable().isDefined(file)){
            if(state.getSymTable().search(file).getType().equals(new IntType())){
                Value v = expr.evaluate(state.getSymTable(), state.getHeap());
                if(v.getType().equals(new StringType())){
                    StringValue stringValue = (StringValue)v;
                    if(state.getFileTable().isDefined(stringValue)){
                        BufferedReader bufferedReader = state.getFileTable().search(stringValue);
                        String read = bufferedReader.readLine();
                        IntValue readValue;
                        if(read == null){
                            readValue = new IntValue(0);
                        }
                        else {
                            readValue = new IntValue(Integer.parseInt(read));
                        }
                        state.getSymTable().update(file, readValue);
                    }
                    else throw new MyException("Not entry associated with the File Table");
                }
                else throw new MyException("Expression didn't evaluate to a string");
            }
            else throw new MyException("Value type is not int");

        }
        else throw new MyException("File already defined");

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type expression = expr.typecheck(typeEnv);
        if(expression.equals(new StringType()))
            return typeEnv;
        else throw new MyException("The parameter of read file is not a string!");
    }
}
