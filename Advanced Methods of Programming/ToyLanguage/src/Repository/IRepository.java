package Repository;

import Model.Exceptions.MyException;
import Model.ProgramState.ProgramState;

import java.util.List;

public interface IRepository {

    public void addProgram(ProgramState programState);
    public void logPrgStateExec(ProgramState programState) throws MyException;
    public List<ProgramState> getPrgList();
    public void setPrgList(List<ProgramState> list);
}
