package controller;
import model.PrgState;
import model.interfaces.MyIDictionary;
import model.utilities.Pair;
import repository.IRepo;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepo repo;
    private ExecutorService executor;

    public Controller(IRepo repo) {
        this.repo = repo;
    }

    private Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer,Integer> heap){
        return heap.entrySet().stream()
                .filter(e->symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List <PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream().filter(PrgState::isNotCompleted).collect(Collectors.toList());
    }

    private void oneStepForAll(List <PrgState> programsList) {
        List<Callable<PrgState>> callList = programsList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> {
                        return p.oneStep();
                    })
                ).collect(Collectors.toList());

        try {
            List<PrgState> newProgramsList = executor.invokeAll(callList).stream().map(future -> {
                        try {
                            return future.get();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        return null;
                    }
            ).filter(Objects::nonNull).collect(Collectors.toList());

            programsList.addAll(newProgramsList);

            programsList.forEach(prg -> {
                try {
                    repo.logPrgStateExec(prg);
                }catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            });
            repo.setList(programsList);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void allSteps() {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> programmesList = removeCompletedPrg(repo.getList());

        while (programmesList.size() > 0) {
            oneStepForAll(programmesList);

            //the heap is the same for all programmes and we will use the merged sym table
            MyIDictionary<String, Integer> mergedSymTable = repo.mergeSymTables();
            programmesList.get(0).getHeap().setContent(
                    conservativeGarbageCollector(
                            mergedSymTable.getContent().values(),
                            programmesList.get(0).getHeap().getContent())
            );

            programmesList = removeCompletedPrg(repo.getList());
        }

        executor.shutdown();

        //here in the repo is at least one programme
        repo.getList().get(0).getFileTable().getValues().stream().map(Pair::getSecond).forEach(bufferedReader -> {
                    try {
                        bufferedReader.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            );
        repo.setList(programmesList);
    }
}
