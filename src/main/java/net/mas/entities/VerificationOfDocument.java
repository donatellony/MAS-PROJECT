package net.mas.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
public class VerificationOfDocument extends Verification{
    private Document target;

    public VerificationOfDocument(){

    }

    public VerificationOfDocument(LocalDate startDate, Verifier verifier, Document target){
        super(startDate, verifier);
        setTarget(target);
    }

    @OneToOne(mappedBy = "verification")
    public Document getTarget() {
        return target;
    }

    public void setTarget(Document target) {
        this.target = target;
    }
}
