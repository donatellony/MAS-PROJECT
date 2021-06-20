package net.mas.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;
import net.mas.Main;
import net.mas.entities.Client;
import net.mas.entities.Verification;
import net.mas.entities.Verifier;
import net.mas.entities.Worker;
import net.mas.repositories.VerificationRepository;
import net.mas.repositories.WorkerRepository;
import net.mas.services.IVerificationService;
import net.mas.services.IWorkerService;

import java.util.List;

public class WorkerVerificationsController {

    private IVerificationService verificationService;

    public WorkerVerificationsController() {
        this.verificationService = new VerificationRepository();
    }

    private ObservableList<Verifier> verifiers = FXCollections.observableArrayList();
    private ObservableList<Verification> verifications = FXCollections.observableArrayList();
    private ObservableList<Client> clients = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        onLoad();
    }

    public void onLoad() {
        List<Verifier> verifierList = verificationService.getVerifiersWithVerification();
        List<Verification> verificationList = verificationService.getVerifications();
        List<Client> clientList = verificationService.getClientsWithVerification();

        System.out.println(clientList);

        verifiers.addAll(verifierList);
        verifications.addAll(verificationList);
        clients.addAll(clientList);

//        System.out.println(workers);
//        System.out.println("CHECHKOX ADDING ITEMS");

        idCol.setCellValueFactory(new PropertyValueFactory<>("verificationId"));
        workerCol.setCellValueFactory(new PropertyValueFactory<>("verifier"));
        clientCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        resultCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        actionsCol.setCellValueFactory(new PropertyValueFactory<>("role"));

        workerCmbBox.setCellFactory(new Callback<ListView<Verifier>, ListCell<Verifier>>() {
            @Override
            public ListCell<Verifier> call(ListView<Verifier> param) {
                return new ListCell<Verifier>(){
                    @Override
                    protected void updateItem(Verifier item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item == null || empty){
                            setGraphic(null);
                        } else {
                            setText(item.getName() + " " + item.getSurname() + " (" + item.getWorkerId() + ")");
                        }
                    }
                };
            }
        });

//        clientCmbBox.setCellFactory(new Callback<ListView<Client>, ListCell<Client>>() {
//            @Override
//            public ListCell<Client> call(ListView<Client> param) {
//                return new ListCell<Client>(){
//                    @Override
//                    protected void updateItem(Client item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if(item == null || empty){
//                            setGraphic(null);
//                        } else {
//                            setText(item.getName() + " " + item.getSurname() + "(" + item.getClientId() + ")");
//                        }
//                    }
//                };
//            }
//        });
        clientCmbBox.setCellFactory(new Callback<ListView<Client>, ListCell<Client>>() {
            @Override
            public ListCell<Client> call(ListView<Client> param) {
                return new ListCell<Client>(){
                    @Override
                    protected void updateItem(Client item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item == null || empty){
                            setGraphic(null);
                        } else {
                            setText(item.getName() + " " + item.getSurname() + "(" + item.getClientId() + ")");
                        }
                    }
                };
            }
        });

//        actionsCol.setCellFactory(new WorkerButtonCellFactory(ButtonType.ROLE_BASED));

//        System.out.println(idCol.getCellData(0));
//        workers.forEach(e -> System.out.println(e.toString()));

        mainDataTable.setItems(verifications);
        workerCmbBox.setItems(verifiers);
        clientCmbBox.setItems(clients);

        toWorkersBtn.setOnAction(event -> Main.getInstance().setWorkersScene());
    }


    @FXML
    private TableView<Verification> mainDataTable;

    @FXML
    private TableColumn<Verification, String> typeCol;

    @FXML
    private TableColumn<Verification, Verifier> workerCol;

    @FXML
    private TableColumn<Verification, String> endDateCol;

    @FXML
    private TableColumn<Verification, Number> idCol;

    @FXML
    private TableColumn<Verification, String> startDateCol;

    @FXML
    private TableColumn<Verification, Number> clientCol;

    @FXML
    private ComboBox<Verifier> workerCmbBox;

    @FXML
    private TableColumn<Verification, String> resultCol;

    @FXML
    private ComboBox<String> typeCmbBox;

    @FXML
    private TableColumn<Verification, ?> actionsCol;

    @FXML
    private ComboBox<Client> clientCmbBox;

    @FXML
    private Button toWorkersBtn;

    @FXML
    private Button toVerificationsBtn;
}
