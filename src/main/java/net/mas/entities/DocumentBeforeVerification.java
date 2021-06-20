package net.mas.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class DocumentBeforeVerification extends Document{
    private LocalDate addedToQueueDate;

    public DocumentBeforeVerification(){

    }

    public DocumentBeforeVerification(byte[] document, Client owner){
        super(document, owner);
    }

    public DocumentBeforeVerification(byte[] document, Client owner, VerificationOfDocument verification){
        super(document, owner, verification);
        setAddedToQueueDate(LocalDate.now());
    }

    @Basic
    public LocalDate getAddedToQueueDate() {
        return addedToQueueDate;
    }

    public void setAddedToQueueDate(LocalDate addedToQueueDate) {
        this.addedToQueueDate = addedToQueueDate;
    }
}
