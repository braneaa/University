package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.Exceptions.MyException;
import Model.Expressions.Expr;
import Model.ProgramState.ProgramState;
import Model.Types.Type;
import Model.Values.Value;

public class AssignStatement implements IStmt {

    private String id;
    private Expr expr;

    public AssignStatement(String id, Expr expr){
        this.id = id;
        this.expr = expr;
    }

    @Override
    public String toString() {
        return id + "=" +expr.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        if(symTbl.isDefined(id)){
            Value val = expr.evaluate(symTbl, state.getHeap());
            Type typeId = (symTbl.search(id)).getType();
            if(val.getType().equals(typeId))
                symTbl.update(id, val);
            else throw new MyException("Declared type of variable " + id + " and type of the assigned expression do not match");
        }
        else throw new MyException("The used variable " + id + " was not declared before");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.search(id);
        Type typeexp = expr.typecheck(typeEnv);

        if(typevar.equals(typeexp)){
            return typeEnv;
        }
        else throw new MyException("Assignment: right hand side and left hand side have different types");
    }
}
