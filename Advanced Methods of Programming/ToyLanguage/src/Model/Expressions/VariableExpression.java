package Model.Expressions;

        import Model.ADT.MyIDictionary;
        import Model.ADT.MyIHeap;
        import Model.Exceptions.MyException;
        import Model.Types.Type;
        import Model.Values.Value;

public class VariableExpression implements Expr {

    private String i;

    @Override
    public Value evaluate(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws MyException {
        return table.search(i);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.search(i);
    }

    @Override
    public String toString() {
        return this.i;
    }

    public VariableExpression(String ii){
        this.i = ii;
    }
}
