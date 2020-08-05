package Model.Expressions;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Exceptions.MyException;
import Model.Types.Type;
import Model.Values.Value;

public interface Expr {
    Value evaluate(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws MyException;

    Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;

    @Override
    String toString();

}
