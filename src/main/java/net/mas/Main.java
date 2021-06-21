package net.mas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.mas.entities.Worker;
import net.mas.services.VerificationLoadingService;
import net.mas.services.WorkerLoadingService;

public class Main extends Application {
    private Stage stage;

    private VerificationLoadingService verificationLoadingService = VerificationLoadingService.getInstance();
    private WorkerLoadingService workerLoadingService = WorkerLoadingService.getInstance();

    private static Main instance;

    public Main() {
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/workersPage.fxml"));
        Parent root = loader.load();
        stage = primaryStage;
        stage.getIcons().add(new Image("/img/piesikot_logo.png"));
        primaryStage.setTitle("Piesikot Moderator Workers Page");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void setLoadingScene() {
        try {
            replaceScene("loading.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVerificationsScene(Worker worker) {
        setVerificationsScene();
    }

    public void setVerificationsScene() {
        verificationLoadingService.restart();
    }

    public void setWorkersScene() {
        workerLoadingService.restart();
    }

    private Parent replaceScene(String fileName) throws Exception {
        Parent page = FXMLLoader.load(getClass().getResource("/views/" + fileName));
        stage.getScene().setRoot(page);
        return page;
    }

    public Parent replaceScene(String fileName, String title) throws Exception {
        stage.setTitle(title);
        return replaceScene(fileName);
    }
}


