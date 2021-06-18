package net.mas.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class VerificationOfPicture extends Verification {
    private Picture target;

    @OneToOne(mappedBy = "verification")
    public Picture getTarget() {
        return target;
    }

    public void setTarget(Picture target) {
        this.target = target;
    }
}
