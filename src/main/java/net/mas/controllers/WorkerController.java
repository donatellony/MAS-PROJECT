package net.mas.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.mas.components.ButtonType;
import net.mas.components.WorkerButtonCellFactory;
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

        workers.addAll(workerList);

        idCol.setCellValueFactory(new PropertyValueFactory<>("workerId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));

        actionsCol.setCellFactory(new WorkerButtonCellFactory(ButtonType.ROLE_BASED));

//        System.out.println(idCol.getCellData(0));
//        workers.forEach(e -> System.out.println(e.toString()));

        mainDataTable.setItems(workers);
    }

    @FXML
    private TableView<Worker> mainDataTable;

    @FXML
    private TableColumn<Worker, Number> idCol;

    @FXML
    private TableColumn<Worker, String> nameCol;

    @FXML
    private TableColumn<Worker, String> surnameCol;;

    @FXML
    private TableColumn<Worker, String> roleCol;

    @FXML
    private TableColumn actionsCol;

}
