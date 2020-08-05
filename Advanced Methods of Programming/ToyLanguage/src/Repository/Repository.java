package Repository;

import Model.Exceptions.MyException;
import Model.ProgramState.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{

    private List<ProgramState> programStates;
    private int index;
    private String logFilePath;

    public Repository(String file){
        this.programStates = new ArrayList<>();
        this.index = 0;
        this.logFilePath = file;
    }

    @Override
    public void addProgram(ProgramState programState) {
        this.programStates.add(programState);
    }


    @Override
    public void logPrgStateExec(ProgramState programState) throws MyException {
        try(PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath,true)))) {
            logFile.println(programState.toString() + '\n');
        }
        catch (IOException ioe){
            throw new MyException(ioe.getMessage());
        }
    }

    @Override
    public List<ProgramState> getPrgList() {
        return programStates;
    }

    @Override
    public void setPrgList(List<ProgramState> list) {
        programStates = list;
    }
}
