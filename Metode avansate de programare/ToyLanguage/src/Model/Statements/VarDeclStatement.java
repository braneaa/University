package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.Exceptions.MyException;
import Model.ProgramState.ProgramState;
import Model.Types.Type;
import Model.Values.Value;

public class VarDeclStatement implements IStmt {

    private String name;
    private Type type;

    public VarDeclStatement(String n, Type t){
        this.name = n;
        this.type = t;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, Value> tbl = state.getSymTable();
        if(tbl.isDefined(this.name)){
            throw new MyException("Variable already declared");
        }
        else tbl.update(this.name, this.type.defaultValue());

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.update(name, type);
        return typeEnv;
    }

    @Override
    public String toString() {
        return this.type.toString() + this.name;
    }
}
