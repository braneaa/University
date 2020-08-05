package Controller;

import Model.ADT.MyIStack;
import Model.Exceptions.MyException;
import Model.ProgramState.ProgramState;
import Model.Statements.IStmt;
import Model.Values.RefValue;
import Model.Values.Value;
import Repository.IRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {

    private IRepository repo;
    private String programState;
    private ExecutorService executor;

    public Controller(IRepository r){
        this.repo = r;
    }



//    public String executeOneStepWerapper() throws MyException{
//        ProgramState prg = repo.getCurrentProgram();
//        if(!prg.getExeStack().isEmpty()){
//            executeOneStep(prg);
//            return prg.toString();
//        }
//
//        throw new MyException("The stack is empty!");
//    }


    private Map<Integer, Value> safeGarbageCollector(List<Integer> addressesFromSymbolTable, Map<Integer, Value> heap)
    {
        return heap.entrySet()
                .stream()
                .filter(e->addressesFromSymbolTable.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddrFromSymTable(Collection<Value> symTableValues, Collection<Value> heap){
        return  Stream.concat(
                heap.stream()
                        .filter(v-> v instanceof RefValue)
                        .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();}),
                symTableValues.stream()
                        .filter(v-> v instanceof RefValue)
                        .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();})
        )
                .collect(Collectors.toList());
    }


    /*public void executeAll() throws MyException, IOException {
        ProgramState prg = repo.getCurrentProgram();
        repo.logPrgStateExec();
        while (!prg.getExeStack().isEmpty()){
            //System.out.println(prg);
            this.executeOneStep(prg);
            prg.getHeap().setContent(safeGarbageCollector
                    (getAddrFromSymTable(prg.getSymTable().getContent().values(),
                            prg.getHeap().getContent().values()),prg.getHeap().getContent()));
            repo.logPrgStateExec();
        }
        System.out.println(prg);
    }*/

    public void oneStepForAll(List<ProgramState> programStates) throws InterruptedException, MyException, IOException {
        programStates.forEach(p-> {
            try {
                repo.logPrgStateExec(p);
            } catch (MyException e) {

            }
        });
        List<Callable<ProgramState>> callableList = programStates.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(()-> p.executeOneStep()))
                .collect(Collectors.toList());
        List<ProgramState> newProgramStates = executor.invokeAll(callableList)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(e -> e != null)
                .collect(Collectors.toList());


        programStates.addAll(newProgramStates);
        programStates.forEach(prog -> {
            try {
                repo.logPrgStateExec(prog);
            } catch (MyException e) {
                e.printStackTrace();
            }
        });
        repo.setPrgList(programStates);
    }

    public void allStep() throws InterruptedException, IOException, MyException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> prgList = removeCompletedPrg(repo.getPrgList());
        while (prgList.size() > 0){
            prgList.forEach(
                    p-> {p.getHeap().setContent(safeGarbageCollector(getAddrFromSymTable(p.getSymTable().getContent().values(),p.getHeap().getContent().values()),p.getHeap().getContent()));}
            );
            oneStepForAll(prgList);
            prgList = removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        repo.setPrgList(prgList);

    }

    public IRepository getRepo(){
        return this.repo;
    }

    public void addProgram(ProgramState programState)
    {
        this.repo.addProgram(programState);
    }

    public List<ProgramState> removeCompletedPrg(List<ProgramState> inPrgList){
        return inPrgList.stream().filter(p -> p.isNotCompleted()).collect(Collectors.toList());
    }



}
