package net.mas.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.time.LocalDate;

@Entity
public class VerificationOfPicture extends Verification {
    private Picture target;

    public VerificationOfPicture(){

    }

    @Override
    @Transient
    public String getType() {
        return "Picture";
    }

    public VerificationOfPicture(LocalDate startDate, Verifier verifier ,Picture target){
        super(startDate, verifier);
        setTarget(target);
    }

    @OneToOne(mappedBy = "verification")
    public Picture getTarget() {
        return target;
    }

    public void setTarget(Picture target) {
        this.target = target;
    }
}
