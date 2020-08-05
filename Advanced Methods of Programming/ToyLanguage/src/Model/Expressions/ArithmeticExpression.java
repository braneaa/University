package Model.Expressions;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Exceptions.MyException;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class ArithmeticExpression implements Expr {

    private Expr expr1, expr2;
    private char opr;
    /*1 for +
      2 for -
      3 for *
      4 for /
     */

    public ArithmeticExpression(char opr, Expr expr1, Expr expr2){
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.opr = opr;
    }

    @Override
    public String toString() {
        return this.expr1.toString() + this.opr + this.expr2.toString();
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws MyException {
        Value value1, value2;
        value1 = expr1.evaluate(table,heap);
        if(value1.getType().equals(new IntType())){
            value2 = expr2.evaluate(table, heap);
            if(value2.getType().equals(new IntType())){
                IntValue i1 = (IntValue)value1;
                IntValue i2 = (IntValue)value2;
                int n1,n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if(opr == '+') return new IntValue(n1 + n2);
                if (opr == '-') return new IntValue(n1-n2);
                if (opr == '*') return new IntValue(n1*n2);
                if(opr == '/'){
                    if(n2 == 0) throw new MyException("Division by zero");
                    else return new IntValue(n1/n2);
                }
                else throw new  MyException("Invalid operand");
            }
            else throw new MyException("Second operand is not an integer");
        }
        else throw new MyException("First operand is not an integer");
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1,typ2;
        typ1=expr1.typecheck(typeEnv);
        typ2=expr2.typecheck(typeEnv);

        if(typ1.equals(new IntType())){
            if(typ2.equals(new IntType())){
                return new IntType();
            }
            else throw new MyException("second operand is not an integer");
        }
        else throw new MyException("first operand is not an integer");
    }
}
