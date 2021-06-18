package net.mas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.mas.controllers.WorkerController;
import net.mas.entities.Worker;

public class Main extends Application {
    private Stage stage;

    public static void main(String[] args) {
//        HibernateUtil.addSeedData();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/moderatorPage.fxml"));
        Parent root = (Parent) loader.load();
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        WorkerController controller = loader.getController();
        controller.onLoad();
    }

    public static void setWorkerTransactionsScene(Worker worker) {

    }
}
