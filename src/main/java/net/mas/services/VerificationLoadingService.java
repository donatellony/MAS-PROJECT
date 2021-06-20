package net.mas.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import net.mas.Main;
import net.mas.repositories.VerificationRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerificationLoadingService extends Service<Map<VerificationLoadingServiceResults, List<?>>> {
    private static VerificationLoadingService instance;

    private VerificationLoadingService(){
        instance = this;
        setOnRunning(event -> Main.getInstance().setLoadingScene());
        setOnSucceeded(event -> {
            try {
                Main.getInstance().replaceScene("workerVerificationsPage.fxml", "Piesikot Moderator Verifications Page");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static VerificationLoadingService getInstance() {
        if(instance == null)
            new VerificationLoadingService();
        return instance;
    }

    private IVerificationService verificationService = new VerificationRepository();

    @Override
    protected Task<Map<VerificationLoadingServiceResults, List<?>>> createTask() {
        return new Task<Map<VerificationLoadingServiceResults, List<?>>>() {
            @Override
            protected Map<VerificationLoadingServiceResults, List<?>> call() throws Exception {
                Map<VerificationLoadingServiceResults, List<?>> results = new HashMap<>();

                results.put(VerificationLoadingServiceResults.VERIFICATIONS, verificationService.getVerifications());
                results.put(VerificationLoadingServiceResults.VERIFIERS, verificationService.getVerifiersWithVerification());
                results.put(VerificationLoadingServiceResults.CLIENTS, verificationService.getClientsWithVerification());

                return results;
            }
        };
    }
}
