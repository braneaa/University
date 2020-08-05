package Model.Expressions;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Exceptions.MyException;
import Model.Types.Type;
import Model.Values.Value;

public class ValueExpression implements Expr {

    private Value value;

    public ValueExpression(Value v){
        this.value = v;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }



    @Override
    public Value evaluate(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws MyException {
        return value;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return value.getType();
    }
}
