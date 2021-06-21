package net.mas.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import net.mas.Main;
import net.mas.entities.Worker;
import net.mas.repositories.WorkerRepository;

import java.util.List;

public class WorkerLoadingService extends Service<List<Worker>> {
    private static WorkerLoadingService instance;

    private WorkerLoadingService(){
        instance = this;
        setOnRunning(event -> Main.getInstance().setLoadingScene());
        setOnSucceeded(event -> {
            try {
                Main.getInstance().replaceScene("workersPage.fxml", "Piesikot Moderator Workers Page");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static WorkerLoadingService getInstance() {
        if(instance == null)
            new WorkerLoadingService();
        return instance;
    }

    private IWorkerService workerService = new WorkerRepository();

    @Override
    protected Task<List<Worker>> createTask() {
        return new Task<List<Worker>>() {
            @Override
            protected List<Worker> call() throws Exception {
                return workerService.getWorkers();
            }
        };
    }
}
