package net.mas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.mas.controllers.WorkerController;
import net.mas.entities.Worker;
import net.mas.utils.HibernateUtil;

public class Main extends Application {
    private Stage stage;

    private static Main instance;

    public Main(){
        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }

    public static void main(String[] args) {
//        HibernateUtil.addSeedData();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/moderatorPage.fxml"));
        Parent root = loader.load();
        stage = primaryStage;
        primaryStage.setTitle("Piesikot Moderator Page");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        WorkerController controller = loader.getController();
        controller.onLoad();
    }

    public void setVerificationsScene(Worker worker) {
        setVerificationsScene();
    }

    public void setVerificationsScene(){
        try {
            replaceScene("workerVerificationsPage.fxml");
            stage.setTitle("Piesikot Moderator Verifications Page");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Parent replaceScene(String fileName) throws Exception {
        Parent page = FXMLLoader.load(getClass().getResource("/views/" + fileName));
        stage.getScene().setRoot(page);
        return page;
    }
}


