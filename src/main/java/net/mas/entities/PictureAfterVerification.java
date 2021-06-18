package net.mas.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PictureAfterVerification extends Picture{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "verificationId", nullable = false)
    public VerificationOfPicture getVerification() {
        return super.getVerification();
    }

    public void setVerification(VerificationOfPicture verification) {
        super.setVerification(verification);
    }
}
