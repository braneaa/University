package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.Exceptions.MyException;
import Model.ProgramState.ProgramState;
import Model.Types.Type;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IStmt{
    ProgramState execute(ProgramState state) throws MyException, IOException;

    MyIDictionary<String, Type> typecheck(MyIDictionary<String ,Type> typeEnv) throws MyException;
}
