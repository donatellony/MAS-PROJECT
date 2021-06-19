package net.mas.components;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import net.mas.Main;
import net.mas.entities.Verifier;
import net.mas.entities.Worker;

import java.util.function.Consumer;

public class WorkerButtonCellFactory extends ButtonCellFactory<Worker> {
    private ButtonType btnType;

    public WorkerButtonCellFactory(ButtonType btnType) {
        super();
        setBtnType(btnType);
    }

    public ButtonType getBtnType() {
        return btnType;
    }

    public void setBtnType(ButtonType btnType) {
        this.btnType = btnType;
    }

    @Override
    public TableCell<Worker, ?> call(TableColumn<Worker, ?> param) {
        return new TableCell<Worker, Object>() {
            @Override
            public void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Worker value = getTableView().getItems().get(getIndex());
                    if (btnType == ButtonType.DETAILS) {
                        setLabel("Details");
                    }
                    else if (btnType == ButtonType.UPDATE) {
                        setLabel("Edit");
                    }
                    else if (btnType == ButtonType.ROLE_BASED) {
                        if (value instanceof Verifier) {
                            setLabel("Verifications");
                            setOnAction(Main.getInstance()::setVerificationsScene);
                        } else {
                            setLabel("Logs");
                        }
                    }
                    Button btn = new Button(getLabel());
                    btn.setOnAction(event -> {
                        getOnAction().accept(value);
                    });
                    setGraphic(btn);
                }
                setText(null);
            }
        };
    }
}
