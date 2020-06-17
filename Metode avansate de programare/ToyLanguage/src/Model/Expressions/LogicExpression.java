package Model.Expressions;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Exceptions.MyException;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

public class LogicExpression implements Expr {

    private Expr expr1,expr2;
    private String opr;

    @Override
    public Value evaluate(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws MyException {
        Value v1, v2;

        v1 = expr1.evaluate(table, heap);
        if(v1.getType().equals(new BoolType())){
            v2 = expr2.evaluate(table, heap);
            if(v2.getType().equals(new BoolType())){
                BoolValue b1 = (BoolValue)v1;
                BoolValue b2 = (BoolValue)v2;
                boolean n1,n2;
                n1 = b1.getValue();
                n2 = b2.getValue();
                if(opr.equals("and")){
                    return new BoolValue(n1 && n2);
                }
                if(opr.equals("or")){
                    return new BoolValue(n1 || n2);
                }
                else throw new MyException("Invalid operand");
            }
            else throw new MyException("Not boolean variable");
        }
        else throw new MyException("Not boolean variable");
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1,typ2;
        typ1=expr1.typecheck(typeEnv);
        typ2=expr2.typecheck(typeEnv);

        if(typ1.equals(new BoolType())){
            if(typ2.equals(new BoolType())) {
                return new BoolType();
            }
            else throw new MyException("first operand is not boolean");
        }
        else throw new MyException("second operand is not boolean");

    }

    @Override
    public String toString() {
        return this.expr1.toString() + " " + this.opr + " " + this.expr2.toString();
    }

    public LogicExpression(Expr expr1, Expr expr2, String opr){
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.opr = opr;
    }

}
