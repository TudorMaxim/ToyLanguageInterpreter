package controller;
import model.PrgState;
import repository.IRepo;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
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
//        programsList.forEach(prg -> {
//            try {
//                repo.logPrgStateExec(prg);
//            }catch (Exception ex) {
//                System.out.println(ex.getMessage());
//            }
//        });
//
//        List<Callable<PrgState>> callList = programsList.stream()
//                .map((PrgState p) -> (Callable<PrgState>)(() -> {
//                        return p.oneStep();
//                    })
//                ).collect(Collectors.toList());
//
//        try {
//            List<PrgState> newProgramsList = executor.invokeAll(callList).stream().map(future -> {
//                        try {
//                            return future.get();
//                        } catch (Exception ex) {
//                            System.out.println(ex.getMessage());
//                        }
//                        return null;
//                    }
//            ).filter(Objects::nonNull).collect(Collectors.toList());
//
//            programsList.addAll(newProgramsList);
//
//            programsList.forEach(prg -> {
//                try {
//                    repo.logPrgStateExec(prg);
//                }catch (Exception ex) {
//                    System.out.println(ex.getMessage());
//                }
//            });
//            repo.setList(programsList);
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }

        programsList.forEach(program -> {
            try {
                PrgState isFork = program.oneStep();
                if (isFork != null) {
                    repo.add(isFork);
                }
                program.getHeap().setContent(conservativeGarbageCollector(
                        program.getSymTable().getContent().values(),
                        program.getHeap().getContent()));
                repo.logPrgStateExec(program);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
    }
    public void allSteps() {
        //executor = Executors.newFixedThreadPool(2);
        List<PrgState> programmesList = removeCompletedPrg(repo.getList());

        while (programmesList.size() > 0) {
            System.out.println(programmesList.size());
            oneStepForAll(programmesList);
            programmesList = removeCompletedPrg(repo.getList());
        }
        //executor.shutdown();

//        programmesList.forEach(program -> program.getFileTable().getValues().stream().map(Pair::getSecond).forEach(bufferedReader -> {
//                try {
//                    bufferedReader.close();
//                } catch (IOException ex) {
//                    System.out.println(ex.getMessage());
//                }
//            })
//        );
    }
}
