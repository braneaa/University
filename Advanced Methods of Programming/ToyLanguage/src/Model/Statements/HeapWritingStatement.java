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

public class HeapWritingStatement implements IStmt {

    private String varName;
    private Expr expr;

    @Override
    public String toString(){
        return "wH("+ varName + " " + this.expr.toString() + ")";
    }

    public HeapWritingStatement(String v, Expr e){
        this.varName = v;
        this.expr = e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        if (state.getSymTable().isDefined(varName)) {
            Value value = state.getSymTable().search(this.varName);
            if (value instanceof RefValue) {
                int address = ((RefValue) value).getAddress();
                if (state.getHeap().get(address) != null) {
                    Value evalValue = this.expr.evaluate(state.getSymTable(), state.getHeap());
                    if (evalValue.getType().equals(((RefValue) value).getLocationType())) {
                        state.getHeap().put(address, evalValue);
                    } else throw new MyException("Incompatible type");
                } else throw new MyException("Address is not in the heap");

            } else throw new MyException("Value not reference type");
        }
        else throw new MyException("Address not defined");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.search(varName);
        Type typeExp = expr.typecheck(typeEnv);
        if(typeVar.equals(new RefType(typeExp)))
            return typeEnv;
        else
            throw new MyException("Heap write statement - different types");
    }
}