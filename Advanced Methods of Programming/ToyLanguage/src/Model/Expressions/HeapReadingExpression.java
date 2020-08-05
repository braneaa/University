package Model.Expressions;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Exceptions.MyException;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

public class HeapReadingExpression implements Expr {
    private Expr expr;

    public HeapReadingExpression(Expr expr){
        this.expr = expr;
    }

    public String toString(){
        return "rH(" + expr.toString() + ")";
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws MyException {
        Value value = this.expr.evaluate(table, heap);
        if(value instanceof RefValue){
            int address = ((RefValue)value).getAddress();
            Value valueFromHeap = heap.get(address);
            if(valueFromHeap != null){
                return valueFromHeap;
            }
            else throw new MyException("Address doesn't have a value");
        }
        else throw new MyException("Value if not reference type");
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ = expr.typecheck(typeEnv);
        if(typ instanceof RefType){
            RefType reft = (RefType)typ;
            return reft.getInner();
        }
        else throw new MyException("the rH argument is not a RefType");
    }

}
