package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Expr;
import Model.ProgramState.ProgramState;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

import java.io.IOException;

public class HeapAllocationStatement implements IStmt {

    private String var_name;
    private Expr expr;

    public HeapAllocationStatement(String var_name, Expr expr){
        this.expr = expr;
        this.var_name = var_name;
    }

    @Override
    public String toString(){
        return "new( " + var_name + " " + expr.toString()+ " )";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        if (state.getSymTable().isDefined(var_name)) {
            Value value = state.getSymTable().search(var_name);
            if(value instanceof RefValue){
                Value v = expr.evaluate(state.getSymTable(), state.getHeap());
                if(v.getType().equals((((RefValue) value)).getLocationType())){
                    int location = state.getHeap().allocate(v);
                    state.getSymTable().update(var_name,new RefValue(location,v.getType()));

                }
                else throw new MyException("Expression value not equal to the location type value");
            }
            else throw new MyException("Value not reference type");
        }
        else throw new MyException("That is not a value in symbol table");
        return  null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.search(var_name);
        Type typexp = expr.typecheck(typeEnv);

        if (typevar.equals(new RefType(typexp))){
            return typeEnv;
        }
        else throw new MyException("NEW stmt: right hand and left hand side have different types");
    }
}
