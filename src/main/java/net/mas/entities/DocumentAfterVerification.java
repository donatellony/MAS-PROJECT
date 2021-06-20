package net.mas.entities;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class DocumentAfterVerification extends Document {

    public DocumentAfterVerification() {
    }

    public DocumentAfterVerification(byte[] document, Client owner, VerificationOfDocument verification) {
        super(document, owner, verification);
    }
}
