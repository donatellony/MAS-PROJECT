package net.mas.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class WorkerVerificationsController {



    @FXML
    private TableView<?> mainDataTable;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private TableColumn<?, ?> workerCol;

    @FXML
    private TableColumn<?, ?> endDateCol;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> startDateCol;

    @FXML
    private TableColumn<?, ?> clientCol;

    @FXML
    private ChoiceBox<?> workerChBox;

    @FXML
    private TableColumn<?, ?> resultCol;

    @FXML
    private ChoiceBox<?> typeChBox;

    @FXML
    private TableColumn<?, ?> actionsCol;

    @FXML
    private ChoiceBox<?> clientChBox;
}
