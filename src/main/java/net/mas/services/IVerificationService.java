package net.mas.services;

import net.mas.entities.Client;
import net.mas.entities.Verification;
import net.mas.entities.Verifier;

import java.util.List;

public interface IVerificationService {
    List<Verifier> getVerifiersWithVerification();
    List<Client> getClientsWithVerification();
    List<Verification> getVerifications();
}
