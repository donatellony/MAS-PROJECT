package net.mas.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
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

import java.util.*;
import java.util.function.Predicate;

public class WorkerVerificationsController {

    private IVerificationService verificationService;
    private VerificationLoadingService loadingService;

    public WorkerVerificationsController(Worker worker) {
        this.verificationService = new VerificationRepository();
        this.loadingService = VerificationLoadingService.getInstance();
    }

    public WorkerVerificationsController() {
        this.verificationService = new VerificationRepository();
        this.loadingService = VerificationLoadingService.getInstance();
    }

    private ObservableList<Verifier> verifiers = FXCollections.observableArrayList();
    private FilteredList<Verification> verifications;
    private ObservableList<Client> clients = FXCollections.observableArrayList();
    private ObservableList<String> types = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        onLoad();
    }

    public void onLoad() {

        //getting values after the loading
        Map<VerificationLoadingServiceResults, List<?>> results = loadingService.getValue();

        final List<Verification> verificationList = new ArrayList<>((List<Verification>) results.get(VerificationLoadingServiceResults.VERIFICATIONS));
        final Set<Verifier> verifierSet = new HashSet<>((List<Verifier>) results.get(VerificationLoadingServiceResults.VERIFIERS));
        final Set<Client> clientSet = new HashSet<>((List<Client>) results.get(VerificationLoadingServiceResults.CLIENTS));

        //creating dummies for combo boxes, so they won't throw exceptions
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

        //adding data to main holders
        verifiers.add(0, dummyVerifier);
        verifiers.addAll(verifierSet);
        verifications = new FilteredList<>(FXCollections.observableArrayList(verificationList));
        clients.add(0, dummyClient);
        clients.addAll(clientSet);
        System.out.println(clientSet);
        types.add(0, "");
        verificationList.forEach(verification -> {
            if(!types.contains(verification.getType()))
                types.add(verification.getType());
        });


        //setting up standard verification columns
        idCol.setCellValueFactory(new PropertyValueFactory<>("verificationId"));
        workerCol.setCellValueFactory(new PropertyValueFactory<>("verifier"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        resultCol.setCellValueFactory(new PropertyValueFactory<>("state"));

        //setting up more complicated, client column association is (Verification - Picture/Document - Client)
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


        //setting items to GUI
        mainDataTable.setItems(verifications);
        workerCmbBox.setItems(verifiers);
        clientCmbBox.setItems(clients);
        typeCmbBox.setItems(types);

        //giving filters their custom functionality
        ObjectProperty<Predicate<Verification>> clientFilter = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<Verification>> verifierFilter = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<Verification>> typeFilter = new SimpleObjectProperty<>();

        //client combo box filter
        clientFilter.bind(Bindings.createObjectBinding(() ->
                        verification -> {
                            Client value = clientCmbBox.getValue();
                            if (value == null || value.toString().equals(""))
                                return true;
                            if (verification instanceof VerificationOfDocument) {
                                return ((VerificationOfDocument) verification).getTarget().getOwner().getClientId() == value.getClientId();
                            } else {
                                return ((VerificationOfPicture) verification).getTarget().getOwner().getClientId() == value.getClientId();
                            }
                        },
                clientCmbBox.valueProperty())
        );

        //verifier combo box filter
        verifierFilter.bind(Bindings.createObjectBinding(() ->
                        verification -> {
                            Worker value = workerCmbBox.getValue();
                            if (value == null || value.toString().equals(""))
                                return true;
                            return verification.getVerifier().getWorkerId() == value.getWorkerId();
                        },
                workerCmbBox.valueProperty())
        );

        typeFilter.bind(Bindings.createObjectBinding(() ->
                        verification -> {
                            String value = typeCmbBox.getValue();
                            if (value == null || value.equals(""))
                                return true;
                            return value.equals(verification.getType());
                        },
                typeCmbBox.valueProperty()));

        //connecting two filters to work together
        verifications.predicateProperty().bind(Bindings.createObjectBinding(
                () -> clientFilter.get().and(verifierFilter.get()).and(typeFilter.get()),
                clientFilter, verifierFilter, typeFilter));

        //setting button at the top to come back at any moment
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
    private ComboBox<Client> clientCmbBox;

    @FXML
    private Button toWorkersBtn;

    @FXML
    private Button toVerificationsBtn;
}
