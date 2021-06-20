package net.mas.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import net.mas.Main;
import net.mas.entities.*;
import net.mas.repositories.VerificationRepository;
import net.mas.services.IVerificationService;
import net.mas.services.VerificationLoadingService;
import net.mas.services.VerificationLoadingServiceResults;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WorkerVerificationsController {

    private IVerificationService verificationService;
    private VerificationLoadingService loadingService;

    public WorkerVerificationsController() {
        this.verificationService = new VerificationRepository();
        this.loadingService = VerificationLoadingService.getInstance();
    }

    private ObservableList<Verifier> verifiers = FXCollections.observableArrayList();
    private FilteredList<Verification> verifications;
    private ObservableList<Client> clients = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        onLoad();
    }

    public void onLoad() {

        Map<VerificationLoadingServiceResults, List<?>> results = loadingService.getValue();

        final List<Verification> verificationList = new ArrayList<>((List<Verification>) results.get(VerificationLoadingServiceResults.VERIFICATIONS));
        final List<Verifier> verifierList = new ArrayList<>((List<Verifier>) results.get(VerificationLoadingServiceResults.VERIFIERS));
        final List<Client> clientList = new ArrayList<>((List<Client>) results.get(VerificationLoadingServiceResults.CLIENTS));

        Verifier dummyVerifier = new Verifier() {
            @Override
            public String toString() {
                return "";
            }
        };
        dummyVerifier.setWorkerId(-1);

        Client dummyClient = new Client() {
            @Override
            public String toString() {
                return "";
            }
        };
        dummyClient.setClientId(-1L);
        verifiers.add(0, dummyVerifier);
        verifiers.addAll(verifierList);
        verifications = new FilteredList<>(FXCollections.observableArrayList(verificationList));
        clients.add(0, dummyClient);
        clients.addAll(clientList);

        idCol.setCellValueFactory(new PropertyValueFactory<>("verificationId"));
        workerCol.setCellValueFactory(new PropertyValueFactory<>("verifier"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        resultCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        actionsCol.setCellValueFactory(new PropertyValueFactory<>("role"));

        clientCol.setCellFactory(
                new Callback<TableColumn<Verification, Number>, TableCell<Verification, Number>>() {
                    @Override
                    public TableCell<Verification, Number> call(TableColumn<Verification, Number> param) {
                        return new TableCell<Verification, Number>() {
                            @Override
                            protected void updateItem(Number item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    Verification value = getTableView().getItems().get(getIndex());
                                    if (value instanceof VerificationOfPicture) {
                                        VerificationOfPicture picValue = (VerificationOfPicture) value;
                                        setText(picValue.getTarget().getOwner().toString());
                                    }
                                    if (value instanceof VerificationOfDocument) {
                                        VerificationOfDocument docValue = (VerificationOfDocument) value;
                                        setText(docValue.getTarget().getOwner().toString());
                                    }
                                }
                            }
                        };
                    }
                }
        );

        mainDataTable.setItems(verifications);
        workerCmbBox.setItems(verifiers);
        clientCmbBox.setItems(clients);

        workerCmbBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.toString().equals("")) {
                verifications.setPredicate(null);
            } else {
                verifications.setPredicate(e -> e.getVerifier().getWorkerId() == newValue.getWorkerId());
            }
        });

        clientCmbBox.valueProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue.toString().equals("")) {
                verifications.setPredicate(null);
            } else {
                verifications.setPredicate(e -> {
                    if (e instanceof VerificationOfDocument) {
                        return ((VerificationOfDocument) e).getTarget().getOwner().getClientId() == newValue.getClientId();
                    } else {
                        return ((VerificationOfPicture) e).getTarget().getOwner().getClientId() == newValue.getClientId();
                    }
                });
            }
        }));

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
