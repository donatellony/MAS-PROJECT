package net.mas.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class PictureBeforeVerification extends Picture{
    private LocalDate addedToQueueDate;

    public PictureBeforeVerification(){}

    public PictureBeforeVerification(byte[] photo, Client owner){
        super(photo, owner);
    }

    public PictureBeforeVerification(byte[] photo, Client owner, VerificationOfPicture verification){
        super(photo, owner, verification);
        setAddedToQueueDate(LocalDate.now());
    }

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
