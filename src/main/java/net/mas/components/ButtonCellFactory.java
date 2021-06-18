package net.mas.components;

import java.util.function.Consumer;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

enum ButtonType {CREATE, REMOVE, UPDATE, DELETE}

public class ButtonCellFactory<E> implements Callback<TableColumn<E, ?>, TableCell<E, ?>> {
    private String label;
    private Consumer<E> onAction;
    private ButtonType btnType;

    public ButtonCellFactory(String label, ButtonType btnType, Consumer<E> onAction) {
        this.label = label;
        this.onAction = onAction;
        this.btnType = btnType;
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