package net.mas.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import net.mas.entities.Worker;
import net.mas.repositories.WorkerRepository;
import net.mas.services.WorkerService;

import java.util.List;

public class WorkerController {

    private WorkerService workerService;

    public WorkerController() {
        this.workerService = new WorkerRepository();
    }

    private ObservableList<Worker> workers = FXCollections.observableArrayList();

    public void onLoad() {
        List<Worker> workerList = workerService.getWorkers();

        workers.forEach(e -> System.out.println("IM WORKER!"));

        workers.addAll(workerList);
        mainDataTable.setItems(workers);
        idCol.setCellValueFactory();
    }

    @FXML
    private TableView<Worker> mainDataTable;

    @FXML
    private TableColumn<Worker, Long> idCol;

    @FXML
    private TableColumn<Worker, String> nameCol;

    @FXML
    private TableColumn<Worker, String> surnameCol;

    @FXML
    private TableColumn<Worker, String> roleCol;

    @FXML
    private TableColumn<?, ?> actionsCol;

}
