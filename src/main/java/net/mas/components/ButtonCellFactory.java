package net.mas.components;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.util.function.Consumer;

public class ButtonCellFactory<E> implements Callback<TableColumn<E, ?>, TableCell<E, ?>> {
    private String label;
    private Consumer<E> onAction;


    //used by the WorkerButtonCellFactory
    protected ButtonCellFactory(){

    }

    //creates the column of cells with defined action and label
    public ButtonCellFactory(String label, Consumer<E> onAction) {
        this.label = label;
        this.onAction = onAction;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Consumer<E> getOnAction() {
        return onAction;
    }

    public void setOnAction(Consumer<E> onAction) {
        this.onAction = onAction;
    }

    @Override
    public TableCell<E, ?> call(TableColumn<E, ?> param) {
        return new TableCell<E, Object>() {
            @Override
            public void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Button btn = new Button(label);
                    btn.setOnAction(event -> {
                        E value = getTableView().getItems().get(getIndex());
                        onAction.accept(value);
                    });
                    setGraphic(btn);
                }
                setText(null);
            }
        };
    }
}