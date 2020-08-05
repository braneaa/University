package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIList;
import Model.Exceptions.MyException;
import Model.Expressions.Expr;
import Model.Expressions.HeapReadingExpression;
import Model.ProgramState.ProgramState;
import Model.Types.Type;
import Model.Values.Value;

public class PrintStatement implements IStmt {

    private Expr expr;

    public PrintStatement(Expr expr){
        this.expr = expr;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIList<Value> output = state.getOut();
        output.add(expr.evaluate(state.getSymTable(), state.getHeap()));
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        expr.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "print(" + expr.toString() + ")";
    }
}
