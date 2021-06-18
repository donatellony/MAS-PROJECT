package net.mas.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class PictureBeforeVerification extends Picture{
    private LocalDate addedToQueueDate;

    @Basic
    public LocalDate getAddedToQueueDate() {
        return addedToQueueDate;
    }

    public void setAddedToQueueDate(LocalDate addedToQueueDate) {
        this.addedToQueueDate = addedToQueueDate;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "verificationId", nullable = false)
    public VerificationOfPicture getVerification() {
        return super.getVerification();
    }

    public void setVerification(VerificationOfPicture verification) {
        super.setVerification(verification);
    }
}
