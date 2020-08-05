package Model.Expressions;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Exceptions.MyException;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class RelationalExpression implements Expr {

    private Expr expr1, expr2;
    private String string;

    public RelationalExpression(Expr expr1, Expr expr2, String string){
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.string = string;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws MyException {

        Value v1,v2;

        v1 = expr1.evaluate(table, heap);
        if(v1.getType().equals(new IntType())) {
            v2 = expr2.evaluate(table, heap);
            if(v2.getType().equals(new IntType())){
                int i1 = ((IntValue)v1).getVal();
                int i2 = ((IntValue)v2).getVal();
                switch (string){
                    case "<" :
                        return new BoolValue(i1 < i2);
                    case "<=":
                        return new BoolValue(i1 <= i2);
                    case "==" :
                        return new BoolValue(i1 == i2);
                    case "!=":
                        return new BoolValue(i1 != i2);
                    case ">":
                        return new BoolValue(i1 > i2);
                    case ">=":
                        return new BoolValue(i1 >= i2);
                    default:
                        throw new MyException("Unknown operand!");
                }
            }
            throw new MyException("Second parameter not Int");
        }
        throw new MyException("First parameter not Int");
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1,typ2;
        typ1=expr1.typecheck(typeEnv);
        typ2=expr2.typecheck(typeEnv);

        if(typ1.equals(new IntType())){
            if(typ2.equals(new IntType())){
                return new BoolType();
            }
            else throw new MyException("second operand is not an integer");
        }
        else throw new MyException("first operand is not an integer");
    }

    @Override
    public String toString(){
        return this.expr1.toString() + string + this.expr2.toString();
    }
}
