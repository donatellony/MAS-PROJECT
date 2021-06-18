package net.mas.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class DocumentBeforeVerification extends Document{
    private LocalDate addedToQueueDate;

    @Basic
    public LocalDate getAddedToQueueDate() {
        return addedToQueueDate;
    }

    public void setAddedToQueueDate(LocalDate addedToQueueDate) {
        this.addedToQueueDate = addedToQueueDate;
    }
}
